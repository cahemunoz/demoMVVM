package com.outsmart.baseprojectkotlin.data_sources

import com.outsmart.baseprojectkotlin.data_sources._base.RetrofitGenerator
import com.outsmart.baseprojectkotlin.data_sources.user_remote.GithubUserApiModel
import com.outsmart.baseprojectkotlin.data_sources.user_remote.RetrofitUserApi
import com.outsmart.baseprojectkotlin.data_sources.user_remote.RetrofitUserDataSource
import com.outsmart.baseprojectkotlin.di.mainModule
import io.reactivex.Single
import org.junit.*
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.standalone.inject
import org.koin.test.KoinTest
import org.koin.test.declareMock
import org.mockito.BDDMockito.given
import java.lang.RuntimeException

class RetrofitUserDataSourceTest: KoinTest {

    private val userDataSource: RetrofitUserDataSource by inject()

    @Before
    fun setup() {
        startKoin(listOf(mainModule, module {
            single { RetrofitUserDataSource(get()) }
            single { get<RetrofitGenerator>().createRepository(RetrofitUserApi::class.java)}
        }))
    }

    @After
    fun teardown() {
        stopKoin()
    }

    @Test
    fun shouldRetrieveUsers() {
        userDataSource.findAllUsers()
            .test()
            .assertValue {
                it.size > 0
            }
            .dispose()
    }

    @Test
    fun `should fail when a network error was happened`() {
        declareMock<RetrofitUserApi> {
            given(this.findAllUsers()).willReturn(Single.error<List<GithubUserApiModel>> { RuntimeException("network_error") })
        }

        userDataSource.findAllUsers()
            .test()
            .assertNoValues()
            .assertErrorMessage("network_error")
            .assertNotComplete()
            .assertTerminated()
            .dispose()
    }

    @Test
    fun `should map response model to user model`() {
        val githubUser = GithubUserApiModel()
        githubUser.login = "cahemunoz"
        githubUser.url = "cahemunoz@gmail.com"
        val list = mutableListOf(githubUser)

        declareMock<RetrofitUserApi> {
            given(this.findAllUsers()).willReturn(Single.just(list))
        }


        userDataSource.findAllUsers()
            .test()
            .assertValue { it.first().username == "cahemunoz" }
            .assertValue { it.first().email == "cahemunoz@gmail.com" }
            .assertComplete()
            .dispose()

    }

}