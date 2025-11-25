package com.openclassrooms.arista

import com.openclassrooms.arista.data.repository.SleepRepository
import com.openclassrooms.arista.domain.model.Sleep
import com.openclassrooms.arista.domain.usecase.GetAllSleepsUseCase
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
import java.time.LocalDateTime

@RunWith(JUnit4::class)
class GetAllSleepsUseCase {

    private lateinit var sleepRepository: SleepRepository
    private lateinit var getAllSleepsUseCase: GetAllSleepsUseCase

    @Before
    fun setUp(){
        sleepRepository = mockk()
        getAllSleepsUseCase = GetAllSleepsUseCase(sleepRepository)
    }

    @After
    fun tearDown(){
        clearAllMocks()
    }

    @Test
    fun `when repository returns sleeps, use case should return them`() = runBlocking {
        // Arrange
        val fakeSleeps = listOf(
            Sleep(LocalDateTime.now().minusDays(1), 7, 8),
            Sleep(LocalDateTime.now().minusDays(2), 6, 5),
            Sleep(LocalDateTime.now().minusDays(3), 8, 9)
        )
        coEvery { sleepRepository.allSleeps() } returns Result.success(fakeSleeps)

        // Act
        val result = getAllSleepsUseCase.execute()

        // Assert
        assertEquals(fakeSleeps, result.getOrNull())

    }

    @Test
    fun `when repository returns empty list, use case should return empty list`() = runBlocking {
        //Arrange
        coEvery { sleepRepository.allSleeps() } returns Result.success(emptyList())

        // Act
        val result = getAllSleepsUseCase.execute()

        // Assert
        assertEquals(emptyList<Sleep>(), result.getOrNull())

    }

}