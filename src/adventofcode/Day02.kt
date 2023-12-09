package adventofcode

import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

@State(Scope.Benchmark)
@Fork(1)
@Warmup(iterations = 0)
@Measurement(iterations = 10, time = 2, timeUnit = TimeUnit.SECONDS)
class Day02 {

    var input: List<String> = emptyList()

    @Setup
    fun setup() {
        input = readInput("Day02")
    }

    @Benchmark
    fun part1(): Int {
        val totalRedCubes = 12
        val totalGreenCubes = 13
        val totalBlueCubes = 14

        return input.sumOf { line ->
            val gameId: Int
            val sets: String
            line.split(':').let { game ->
                gameId = game[0].removePrefix(prefix = "Game ").toInt()
                sets = game[1].filter { it.isLetterOrDigit() }
            }

            var isGamePossible = false
            val digits = StringBuilder()
            var index = 0
            while (index in sets.indices) {
                val char = sets[index]
                when {
                    char.isDigit() -> {
                        digits.append(char)
                        index++
                        continue
                    }
                    else -> {
                        when (char) {
                            'r' -> {
                                val redCubes = digits.toInt().also { digits.clear() }
                                if (redCubes > totalRedCubes) {
                                    isGamePossible = false
                                    break
                                }
                                isGamePossible = true
                                index = indexOfNextDigit(currentIndex = index, sets = sets)
                            }

                            'g' -> {
                                val greenCubes = digits.toInt().also { digits.clear() }
                                if (greenCubes > totalGreenCubes) {
                                    isGamePossible = false
                                    break
                                }
                                isGamePossible = true
                                index = indexOfNextDigit(currentIndex = index, sets = sets)
                            }

                            'b' -> {
                                val blueCubes = digits.toInt().also { digits.clear() }
                                if (blueCubes > totalBlueCubes) {
                                    isGamePossible = false
                                    break
                                }
                                isGamePossible = true
                                index = indexOfNextDigit(currentIndex = index, sets = sets)
                            }
                        }
                    }
                }
            }
            return@sumOf if (isGamePossible) gameId else 0
        }
    }

    private fun indexOfNextDigit(currentIndex: Int, sets: String): Int {
        val indexOfNextDigit = sets.substring(startIndex = currentIndex).indexOfFirst { it.isDigit() }
        return if (indexOfNextDigit != -1) {
            currentIndex + indexOfNextDigit
        } else {
            indexOfNextDigit
        }
    }

    private fun StringBuilder.toInt() = toString().toInt()

    @Benchmark
    fun part2(): Int {
        return input.size
    }
}

fun main() {
    val day02 = Day02()

    day02.input = readInput("Day02_test_1")
    check(day02.part1() == 8)

    day02.input = readInput("Day02_test_2")
    check(day02.part2() == 281)

    day02.setup()
    println("Part 1: ${day02.part1()}")
    println("Part 2: ${day02.part2()}")
}
