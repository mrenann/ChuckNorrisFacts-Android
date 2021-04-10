package com.mrenann.chucknorris_challenge_android.repository

import com.mrenann.chucknorris_challenge_android.api.ResponseAPI
import com.mrenann.chucknorris_challenge_android.viewModel.FactsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class FactsRepositoryTest {
    private val mainThreadSurrogate = newSingleThreadContext("FactsRepository Test thread")
    private lateinit var repository: FactsRepository

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        repository = FactsRepository()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `Está pegando o filme da API corretamente`(): Unit = runBlocking {
        launch(Dispatchers.Main) {
            val response:ResponseAPI = repository.getFacts("God")

            Assert.assertTrue(response is ResponseAPI.Success)
        }
    }

    @Test
    fun `Está aparecedo um erro por conta do total de caracteres`(): Unit = runBlocking {
        launch(Dispatchers.Main) {
            val response:ResponseAPI.Error = repository.getFacts("De") as ResponseAPI.Error

            Assert.assertEquals("Query must be more than 3 chars and less than 120 chars",response.message)
        }
    }

    @Test
    fun `Não Obteve nenhum resultado`(): Unit = runBlocking {
        launch(Dispatchers.Main) {
            val response:ResponseAPI.Error = repository.getFacts("Godness") as ResponseAPI.Error

            Assert.assertEquals("No Facts matched your search",response.message)
        }
    }

}