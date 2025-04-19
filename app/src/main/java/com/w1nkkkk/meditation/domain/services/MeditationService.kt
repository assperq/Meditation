package com.w1nkkkk.meditation.domain.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.CountDownTimer
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.w1nkkkk.meditation.R
import com.w1nkkkk.meditation.domain.history.HistoryModel
import com.w1nkkkk.meditation.domain.mode.MeditationMode
import com.w1nkkkk.meditation.presentation.MainActivity
import com.w1nkkkk.meditation.presentation.component.DateObject
import com.w1nkkkk.meditation.presentation.component.history.HistoryViewModel
import com.w1nkkkk.meditation.toLocalDate
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class MeditationService : Service() {
    private lateinit var mediaPlayer: MediaPlayer
    private var timer: CountDownTimer? = null

    private val NOTIFICATION_ID = 101
    private val CHANNEL_ID = "meditation_channel"

    private val notificationId = 1
    private val notificationManager by lazy {
        getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    }

//    @Inject
//    lateinit var preferencesRepository: PreferencesPresenter

    @Inject
    lateinit var historyViewModel: HistoryViewModel

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val duration = (MainActivity.preferences.value.meditaitionTime.value.toLong() * 60) * 1000
        val mode : MeditationMode =
            (intent!!.getParcelableExtra<MeditationMode>(
                MeditationMode.parcelableName)) as MeditationMode

        mediaPlayer = MediaPlayer.create(this, mode.musicResource)
        mediaPlayer.isLooping = true

        updateNotification(duration, duration)

        timer = object : CountDownTimer(duration, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                updateNotification(duration, millisUntilFinished)
            }

            override fun onFinish() {
                sendCompletionNotification()
                updatePreferences()
                addHistoryItem(mode)
                mediaPlayer.stop()
                stopSelf()
            }
        }.start()

        mediaPlayer.start()

        return START_STICKY
    }

    private fun updateNotification(totalDuration: Long, remainingMillis: Long) {
        val remainingMinutes = TimeUnit.MILLISECONDS.toMinutes(remainingMillis)
        val remainingSeconds = TimeUnit.MILLISECONDS.toSeconds(remainingMillis) -
                TimeUnit.MINUTES.toSeconds(remainingMinutes)

        val progress = ((totalDuration - remainingMillis).toFloat() / totalDuration.toFloat() * 100).toInt()

        val notification = NotificationCompat.Builder(this, "meditation_channel")
            .setContentText(String.format("Осталось: %02d:%02d", remainingMinutes, remainingSeconds))
            .setContentTitle("")
            .setSmallIcon(R.drawable.ic_profile_icon)
            .setOnlyAlertOnce(true)
            .setProgress(100, progress, false)
            .setOngoing(true)
            .build()

        startForeground(notificationId, notification)
    }

    private fun sendCompletionNotification() {
        val notification = NotificationCompat.Builder(this, "meditation_channel")
            .setContentTitle("Медитация завершена")
            .setContentText("Все окончено")
            .setSmallIcon(R.drawable.ic_profile_icon)
            .setAutoCancel(true)
            .setOnlyAlertOnce(false)
            .build()

        notificationManager.notify(notificationId, notification)
    }

    private fun updatePreferences() {
        val today = DateObject.convertLongToTime(DateObject.currentTimeToLong())
//        CoroutineScope(Dispatchers.IO).launch {
//            preferencesRepository.setPreferences(Preferences(
//                MainActivity.preferences.value.meditaitionTime,
//                false,
//                today
//            ))
//        }
    }

    private fun addHistoryItem(mode: MeditationMode) {
        historyViewModel.addHistoryItem(
            HistoryModel(
                date = DateObject.convertLongToTime(DateObject.currentTimeToLong())
                    .toLocalDate(),
                type = mode.getName(this@MeditationService),
                count = "${MainActivity.preferences.value.meditaitionTime.value.toInt()} minutes"
            )
        )
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Meditation Service",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Channel for meditation service"
            }
            getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        }
    }

    private fun buildNotification(): Notification {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Медитация")
            .setContentText("Идёт сеанс медитации")
            .setSmallIcon(R.drawable.ic_profile_icon)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setOngoing(true)
            .build()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
        mediaPlayer.release()
    }

    companion object {
        const val EXTRA_DURATION = "duration_extra"
    }
}