package com.w1nkkkk.meditation.data.datasource.remote

object DocumentFormer {
    fun formUserDocument(name : String) : HashMap<String, Any> {
        return hashMapOf(
            FirestoreFields.name to name,
            FirestoreFields.dayCount to 0,
            FirestoreFields.achievements to hashMapOf(
                "name" to ""
            ),
            FirestoreFields.emotionState to 0
        )
    }
}

object FirestoreFields {
    val name = "name"
    val dayCount = "day_count"
    val achievements = "achievements"
    val emotionState = "emotion_state"
}

object FirestoreCollections {
    val users = "users"
    val admins = "admins"
}