import com.openclassrooms.arista.data.repository.ExerciseRepository
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.model.ExerciseCategory
import com.openclassrooms.arista.domain.usecase.GetAllExercisesUseCase
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.time.LocalDateTime

@RunWith(JUnit4::class)
class GetAllExercisesUseCaseTest {

    private lateinit var exerciseRepository: ExerciseRepository
    private lateinit var getAllExercisesUseCase: GetAllExercisesUseCase


    @Before
    fun setUp() {
        exerciseRepository = mockk()
        getAllExercisesUseCase = GetAllExercisesUseCase(exerciseRepository)
    }


    @After
    fun tearDown() {
        clearAllMocks()
    }


    @Test
    fun `when repository returns exercises, use case should return them`() = runBlocking {
        // Arrange
        val fakeExercises = listOf(
            Exercise(
                startTime = LocalDateTime.now(),
                duration = 30,
                category = ExerciseCategory.Running,
                intensity = 5
            ),
            Exercise(
                startTime = LocalDateTime.now().plusHours(1),
                duration = 45,
                category = ExerciseCategory.Riding,
                intensity = 7
            )
        )
        coEvery { exerciseRepository.getAllExercises() } returns Result.success(fakeExercises)


        // Act
        val result = getAllExercisesUseCase.execute()


        // Assert
        assertEquals(fakeExercises,result.getOrNull())
    }


    @Test
    fun `when repository returns empty list, use case should return empty list`() = runBlocking {
        // Arrange
        coEvery{exerciseRepository.getAllExercises()} returns (Result.success(emptyList()))


        // Act
        val result = getAllExercisesUseCase.execute()


        // Assert
        assertTrue(result.getOrNull() == emptyList<Exercise>())
    }


}