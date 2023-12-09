package adventofcode

import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

@State(Scope.Benchmark)
@Fork(1)
@Warmup(iterations = 0)
@Measurement(iterations = 10, time = 2, timeUnit = TimeUnit.SECONDS)
class Day01 {

    var input: List<String> = emptyList()

    @Setup
    fun setup() {
        input = readInput("Day01")
    }

    @Benchmark
    fun part1(): Int {
        return input.sumOf { line ->
            "${line.first { it.isDigit() }}${line.last { it.isDigit() }}".toInt()
        }
    }

    @Benchmark
    fun part2(): Int {
        val digitRegex = Regex("(?=([0-9]|one|two|three|four|five|six|seven|eight|nine))")
        return input.sumOf { line ->
            val digits = digitRegex.findAll(input = line).flatMap { matchResult ->
                matchResult.groupValues.filterNot { it.isBlank() }
            }

            val firstDigit = digits.first().toDigitChar()
            val lastDigit = digits.last().toDigitChar()
            "$firstDigit$lastDigit".toInt()
        }
    }

    private fun String.toDigitChar(): Char {
        return if (length == 1) first() else when(this) {
            "one" -> '1'
            "two" -> '2'
            "three" -> '3'
            "four" -> '4'
            "five" -> '5'
            "six" -> '6'
            "seven" -> '7'
            "eight" -> '8'
            "nine" -> '9'
            else -> error("unknown spelled out digit: $this")
        }
    }
}

fun main() {
    val day01 = Day01()

    day01.input = readInput("Day01_test_1")
    check(day01.part1() == 142)

    day01.input = readInput("Day01_test_2")
    check(day01.part2() == 281)

    day01.setup()
    println("Part 1: ${day01.part1()}")
    println("Part 2: ${day01.part2()}")
}
