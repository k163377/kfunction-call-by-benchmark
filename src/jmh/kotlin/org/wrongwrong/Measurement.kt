@file:Suppress("FunctionName", "PropertyName")

package org.wrongwrong

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State

@State(Scope.Benchmark)
open class KClassBenchMark {
    data class Data(val foo: Int, val bar: Int, val baz: Int, val qux: Int, val quux: Int)

    @Benchmark
    fun getKClass() = Data::class

    @Benchmark
    fun getJClass1() = Data::javaClass

    @Benchmark
    fun getJClass2() = Data::class.java
}
