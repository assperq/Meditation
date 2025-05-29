package com.w1nkkkk.meditation

import com.google.common.truth.Truth.assertThat
import com.w1nkkkk.meditation.domain.account.AccountModel
import com.w1nkkkk.meditation.domain.auth.AuthRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class RegistrationUnitTesting {
    private val repository : AuthRepository = mockk()
    private val rightUser = AccountModel(
        name = "Kirill",
        dayCount = 12,
        achievements = mapOf(),
        emotion_state = 65
    )

    @Test
    fun `Empty Email Create User`() = runBlocking {
        val params = arrayOf(String(), "123456")
        every { runBlocking { repository.createUser(params[0], params[1]) } } returns AccountModel()
        assertThat(repository.createUser(params[0], params[1])).isNotEqualTo(rightUser)
    }

    @Test
    fun `Right Email and Password Create User`() = runBlocking {
        val params = arrayOf("email@email.com", "123456")
        every { runBlocking { repository.createUser(params[0], params[1]) } } returns rightUser
        assertThat(repository.createUser(params[0], params[1])).isEqualTo(rightUser)
    }

    @Test
    fun `Email Without Simbol and Password Create User`() = runBlocking {
        val params = arrayOf("emailemail.com", "123456")
        every { runBlocking { repository.createUser(params[0], params[1]) } } returns AccountModel()
        assertThat(repository.createUser(params[0], params[1])).isNotEqualTo(rightUser)
    }

    @Test
    fun `Right Email and Small Password Create User`() = runBlocking {
        val params = arrayOf("emailemail.com", "123")
        every { runBlocking { repository.createUser(params[0], params[1]) } } returns AccountModel()
        assertThat(repository.createUser(params[0], params[1])).isNotEqualTo(rightUser)
    }

    @Test
    fun `Empty Email SingIn`() = runBlocking {
        val params = arrayOf(String(), "123456")
        every { runBlocking { repository.singIn(params[0], params[1]) } } returns AccountModel()
        assertThat(repository.singIn(params[0], params[1])).isNotEqualTo(rightUser)
    }

    @Test
    fun `Right Email and Password SingIn`() = runBlocking {
        val params = arrayOf("email@email.com", "123456")
        every { runBlocking { repository.singIn(params[0], params[1]) } } returns rightUser
        assertThat(repository.singIn(params[0], params[1])).isEqualTo(rightUser)
    }

    @Test
    fun `Email Without Simbol and Password SingIn`() = runBlocking {
        val params = arrayOf("emailemail.com", "123456")
        every { runBlocking { repository.singIn(params[0], params[1]) } } returns AccountModel()
        assertThat(repository.singIn(params[0], params[1])).isNotEqualTo(rightUser)
    }
}