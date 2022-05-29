@file:Suppress("FunctionName", "PropertyName")

package org.wrongwrong

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Level
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State
import kotlin.random.Random
import kotlin.reflect.KFunction
import kotlin.reflect.KParameter

fun __zero() {}
fun __one(a0: Int = 0) {}
fun __five(a0: Int = 0, a1: Int = 1, a2: Int = 2, a3: Int = 3, a4: Int = 4) {}
fun __twenty(
    a00: Int = 0, a01: Int = 1, a02: Int = 2, a03: Int = 3, a04: Int = 4,
    a05: Int = 5, a06: Int = 6, a07: Int = 7, a08: Int = 8, a09: Int = 9,
    a10: Int = 10, a11: Int = 11, a12: Int = 12, a13: Int = 13, a14: Int = 14,
    a15: Int = 15, a16: Int = 16, a17: Int = 17, a18: Int = 18, a19: Int = 19
) {}

@State(Scope.Benchmark)
open class Measurement {
    private val _zero = ::__zero
    private val _one = ::__one
    private val _five = ::__five
    private val _twenty = ::__twenty

    var oneArgs: Map<KParameter, Int> = emptyMap()
    var fiveArgs: Map<KParameter, Int> = emptyMap()
    var twentyArgs: Map<KParameter, Int> = emptyMap()

    private fun KFunction<*>.generateArgs(): Map<KParameter, Int> = this.parameters.associateWith { Random.nextInt() }

    @Setup(Level.Trial)
    fun setup() {
        oneArgs = _one.generateArgs()
        fiveArgs = _five.generateArgs()
        twentyArgs = _twenty.generateArgs()
    }

    @Benchmark
    fun zero() = _zero.callBy(emptyMap())

    @Benchmark
    fun oneWithDefault() = _one.callBy(emptyMap())

    @Benchmark
    fun oneWithoutDefault() = _one.callBy(oneArgs)

    @Benchmark
    fun fiveWithDefault() = _five.callBy(emptyMap())

    @Benchmark
    fun fiveWithoutDefault() = _five.callBy(fiveArgs)

    @Benchmark
    fun twentyWithDefault() = _twenty.callBy(emptyMap())

    @Benchmark
    fun twentyWithoutDefault() = _twenty.callBy(twentyArgs)
}
