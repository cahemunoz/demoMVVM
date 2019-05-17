package com.outsmart.baseprojectkotlin._snippets

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import org.junit.Test
import kotlin.random.*


class RxThreadingDemoTest {

    @Test
    fun boundedAndUnboundedThreadPools() {
        /*
            Schedulers.io()  -> unbounded with reuse
            Schedulers.computation()  -> bounded by number os processor (cores)
            Schedulers.newThread() -> unbounded without reuse
            Schedulers.from(Executor executor) -> custom backed by executor p.ex: Scheduler.from(Executors.newFixedThreadPool(n))
            AndroidSchedulers.mainThread()  -> bounded it's the UI  android thread
            Schedulers.single() -> bounded, backed by a single thread executing tasks sequentially in the order requested.
            Schedulers.trampoline() -> executes task like a fifo, use the caller thread, useful simulating recursion.

            Be careful when use unbounded threadpools
        */

        Flowable.just("long", "longer", "longest")
            .doOnNext {
                println("Processing item on thread " + Thread.currentThread().name)
            }
            .map { it.length }
            .subscribe {
                println("item length: $it")
            }
    }


    @Test
    fun executeOnBackgroundThread() {
        Flowable.just("long", "longer", "longest")
            //.subscribeOn(Schedulers.newThread())
            .doOnNext {
                println("Processing item on thread " + Thread.currentThread().name)
            }
            //.subscribeOn(Schedulers.newThread())
            .map { it.length }
            .subscribeOn(Schedulers.newThread())
            .subscribe {
                println("item length: $it received on: " + Thread.currentThread().name)
            }
        Thread.sleep(3000)
        // results of the background thread work are returned on the same thread
    }

    @Test
    fun multipleSubscribeOn() {
        Flowable.just("long", "longer", "longest")
            .subscribeOn(Schedulers.computation())
            .doOnNext {
                println("Processing item on thread " + Thread.currentThread().name)
            }
            .subscribeOn(Schedulers.io())
            .map { it.length }
            .subscribeOn(Schedulers.newThread())
            .subscribe {
                println("item length: $it received on: " + Thread.currentThread().name)
            }
        Thread.sleep(3000)
        // only the first one will be used and the following ones will be ignored unless the subscribeOn()
    }

    @Test
    fun placementObserveOn() {
        Flowable.just("long", "longer", "longest")
            .subscribeOn(Schedulers.computation())
            //.observeOn(Schedulers.single())
            .doOnNext {
                println("Processing item on thread " + Thread.currentThread().name)
            }
            .map { it.length }
            .filter { it == 6 }
            .observeOn(Schedulers.single())
            .subscribe {
                println("item length: $it received on: " + Thread.currentThread().name)
            }
        Thread.sleep(3000)
        // placement of observeOn() matters

    }

    @Test
    fun multipleObserveOn() {
        Flowable.just("long", "longer", "longest")
            .doOnNext {
                println("Processing item on thread " + Thread.currentThread().name)
            }
            .observeOn(Schedulers.computation())
            .map { it.length }
            .doOnNext {
                println("Processing item length on thread " + Thread.currentThread().name)
            }
            .observeOn(Schedulers.io())
            .map { it.toString() }
            .subscribeOn(Schedulers.newThread())
            .map { it.length }
            .subscribe {
                println("item length: $it received on: " + Thread.currentThread().name)
            }
        Thread.sleep(3000)
    }


    @Test
    fun doingThingsAsync() {
        Flowable.just("long", "longer", "longest")
            .flatMap {
                longOperation(it)
                    .doOnNext { println("Processing item on thread " + Thread.currentThread().name) }
                    .subscribeOn(Schedulers.newThread())
            }
            .subscribe {
                println("item length: $it received on: " + Thread.currentThread().name)
            }
        Thread.sleep(5000)
    }


    private fun longOperation(v: String): Flowable<Int> {
        val random = Random(2342346)
        return try {
            Thread.sleep(random.nextInt(3) * 1000L)
            Flowable.just(v.length)
        } catch (e: InterruptedException) {
            e.printStackTrace()
            Flowable.empty<Int>()
        }
    }


    @Test
    fun backpressure() {
        // Observable vs Flowable
        // Flowable suports backpressure prevents buffer overflow
        val flow = Flowable.create<Int>({
            var count = 0
            while (count < 10000) {
                it.onNext(count++)
            }
            it.onComplete()
        }, BackpressureStrategy.LATEST)


        flow
            .subscribeOn(Schedulers.computation())
            .observeOn(Schedulers.single())
            .subscribe {
                println(it)
            }
        Thread.sleep(5000)
    }

}