package com.openclassrooms.arista

import com.openclassrooms.arista.data.repository.UserRepository
import com.openclassrooms.arista.domain.model.User
import com.openclassrooms.arista.domain.usecase.GetUserUsecase
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetUserUseCaseTest {

    private lateinit var userRepository: UserRepository
    private lateinit var getUserUseCase: GetUserUsecase

    @Before
    fun setUp() {
        userRepository = mockk()
        getUserUseCase = GetUserUsecase(userRepository)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `when repository returns user, use case should return it`() = runBlocking {
        // Arrange
        val fakeUser = User("John Doe", "john.doe@exemple.com")
        coEvery { userRepository.getUser() } returns Result.success(fakeUser)

        // Act
        val result = getUserUseCase.execute()

        // Assert
        assertEquals(fakeUser, result.getOrNull())
    }



}