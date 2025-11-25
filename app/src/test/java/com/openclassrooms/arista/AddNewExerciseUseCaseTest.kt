package com.openclassrooms.arista

import com.openclassrooms.arista.data.repository.ExerciseRepository
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.model.ExerciseCategory
import com.openclassrooms.arista.domain.usecase.AddNewExerciseUseCase
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.time.LocalDateTime

@RunWith(JUnit4::class)
class AddNewExerciseUseCaseTest {

    private lateinit var exerciseRepository: ExerciseRepository
    private lateinit var addNewExerciseUseCase: AddNewExerciseUseCase

    @Before
    fun setUp() {
        exerciseRepository = mockk()
        addNewExerciseUseCase = AddNewExerciseUseCase(exerciseRepository)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `when repository returns success, use case should return success`() = runBlocking {
        // Arrange
        val fakeExercise = Exercise(
            startTime = LocalDateTime.now().plusHours(1),
            duration = 45,
            category = ExerciseCategory.Riding,
            intensity = 7
        )
        coEvery { exerciseRepository.addExercise(any()) } returns Result.success(true)

        // Act
        val result = addNewExerciseUseCase.execute(fakeExercise)

        // Assert
        assert(result.isSuccess)
    }

    @Test
    fun `when repository returns failure, use case should return failure`() = runBlocking {
        // Arrange
        val fakeExercise = Exercise(
            startTime = LocalDateTime.now().plusHours(1),
            duration = 45,
            category = ExerciseCategory.Riding,
            intensity = 7
        )
        coEvery { exerciseRepository.addExercise(any()) } returns Result.failure(exception = Exception())

        // Act
        val result = addNewExerciseUseCase.execute(fakeExercise)

        // Assert
        assert(result.isFailure)
    }
}

