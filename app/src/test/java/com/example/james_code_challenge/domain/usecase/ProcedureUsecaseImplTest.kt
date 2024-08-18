package com.example.james_code_challenge.domain.usecase

import com.example.james_code_challenge.data.repository.ProcedureRepository
import com.example.james_code_challenge.mock.MockData
import com.example.james_code_challenge.util.Result
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ProcedureUsecaseImplTest {

    private val procedureRepoMock = mockk<ProcedureRepository>(relaxed = true)

    private lateinit var usecase: ProcedureUsecaseImpl

    private val procedureId = "procedureId"

    @Before
    fun setup() {
//        MockKAnnotations.init(this)
        usecase = ProcedureUsecaseImpl(procedureRepoMock)
    }

    @Test
    fun `WHEN getProcedureList() THEN return success`() = runBlocking {
        // given
        val expectedProcedures = listOf(MockData.procedureMock)
        coEvery { procedureRepoMock.getProcedureList() } returns flowOf(
            Result.Success(
                expectedProcedures
            )
        )

        // when
        val result = usecase.getProcedureList().first()

        // then
        assert(result is Result.Success)
        assertEquals(expectedProcedures, (result as Result.Success).data)
    }

    @Test
    fun `WHEN getProcedureList() THEN return error`() = runBlocking {
        // given
        val expectedError = Exception("Error")
        coEvery { procedureRepoMock.getProcedureList() } returns flowOf(Result.Error(expectedError))

        // when
        val result = usecase.getProcedureList().first()

        // then
        assert(result is Result.Error)
        assertEquals(expectedError, (result as Result.Error).exception)
    }

    @Test
    fun `WHEN getProcedureDetail() THEN return success`() = runBlocking {
        // given
        val expectedProcedureDetail = MockData.procedureDetailMock
        coEvery { procedureRepoMock.getProcedureDetail(procedureId) } returns flowOf(
            Result.Success(
                expectedProcedureDetail
            )
        )

        // when
        val result = usecase.getProcedureDetail(procedureId).first()

        // then
        assert(result is Result.Success)
        assertEquals(expectedProcedureDetail, (result as Result.Success).data)
    }

    @Test
    fun `WHEN getProcedureDetail() THEN return error`() = runBlocking {
        // given
        val expectedError = Exception("Error")
        coEvery { procedureRepoMock.getProcedureDetail(procedureId) } returns flowOf(
            Result.Error(
                expectedError
            )
        )

        // when
        val result = usecase.getProcedureDetail(procedureId).first()

        // then
        assert(result is Result.Error)
        assertEquals(expectedError, (result as Result.Error).exception)
    }

}