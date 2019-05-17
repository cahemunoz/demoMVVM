package com.outsmart.baseprojectkotlin._snippets


import io.reactivex.Completable
import io.reactivex.Single
import org.junit.Test
import java.lang.Exception
import java.util.concurrent.TimeUnit

data class UserModel(val username: String, val email: String)

class RxTestingExample {

    private fun createUser(): Single<UserModel> =
        Single.create<UserModel> {
            val user = UserModel("cahemunoz", "cahemunoz@gmail.com")
            it.onError(Exception("upaaa"))
            it.onSuccess(user)
        }.delay(1L, TimeUnit.SECONDS)

    private fun saveUser(user: UserModel):Completable = Completable.create {
        try {
            println(user.username)
            println(user.email)
            throw Exception("Deu ruim")
            it.onComplete()
        } catch (e: Exception) {
            it.onError(Exception("d.ruim", e))
        }
    }.delay(1L, TimeUnit.SECONDS)


    @Test
    fun shouldComposeObservablesCorrectly() {
        val test = createUser()
            .flatMapCompletable(this::saveUser)
            .test()

        test.awaitTerminalEvent(30, TimeUnit.SECONDS)
        test.assertError(Exception::class.java)
            .assertError {
                it.message == "upaaa"
            }
        test.assertNoValues()
        test.dispose()
    }


    @Test
    fun shouldCreateUser() {
        val test = createUser().test()
        test.awaitTerminalEvent()
        test.assertComplete()
            .assertNoErrors()
            .assertValue {
                it.username == "cahemunoz"
            }
            .dispose()
    }
}