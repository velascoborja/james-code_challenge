package com.example.james_code_challenge.data.repository

import com.example.james_code_challenge.mock.MockData
import com.example.james_code_challenge.data.model.Procedure
import com.example.james_code_challenge.services.api.ProcedureApi
import com.example.james_code_challenge.util.Result
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.net.HttpURLConnection.HTTP_BAD_REQUEST

class ProcedureRepositoryImplTest {

    private val procedureApiMock = mockk<ProcedureApi>(relaxed = true)
    private lateinit var procedureRepository: ProcedureRepository

    private val mockProcedure = MockData.procedureMock

    @Before
    fun setUp() {
        procedureRepository = ProcedureRepositoryImpl(procedureApiMock)
    }

    @Test
    fun `getProcedureList returns Success when API call is successful`() = runBlocking {
        // given
        val mockList = listOf(mockProcedure, mockProcedure.copy(uuid="uuid"))
        coEvery { procedureApiMock.getProcedureList() } returns Response.success(mockList)

        // when
        val result = procedureRepository.getProcedureList().first()

        // then
        assert(result is Result.Success)
        val successResult = result as Result.Success
        assertEquals(successResult.data, mockList)
    }

    @Test
    fun `getProcedureList returns Error when API call fails with HTTP error code`() = runBlocking {
        // given

        coEvery { procedureApiMock.getProcedureList() } throws HttpException(Response.error<List<Procedure>>(HTTP_BAD_REQUEST, "".toResponseBody()))

        // when
        val result = procedureRepository.getProcedureList().first()

        // then
        assert(result is Result.Error)
        val errorResult = result as Result.Error
        val isCorrectErrorCode = errorResult.exception.message?.contains(HTTP_BAD_REQUEST.toString())
        assertTrue(isCorrectErrorCode!!)
    }

    @Test
    fun `getProcedureList returns Error when API call throws an exception`() = runBlocking {
        val exception = Throwable("Network Error")
        coEvery { procedureApiMock.getProcedureList() } throws exception

        val result = procedureRepository.getProcedureList().first()

        assert(result is Result.Error)
        val errorResult = result as Result.Error
        assert(errorResult.exception == exception)
    }

    @Test
    fun `getProcedureList returns Error when API call as Result is null`() = runBlocking {
        val exception = Throwable("Network Error")
        coEvery { procedureApiMock.getProcedureList() } throws exception

        val result = procedureRepository.getProcedureList().first() // TODO SEE NULL CHECK

        assert(result is Result.Error)
        val errorResult = result as Result.Error
        assert(errorResult.exception == exception)
    }
}