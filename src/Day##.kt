import kotlin.system.measureTimeMillis

fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val partOneTestInput = readInput("Day01_part1_test")
    check(part1(partOneTestInput) == 142)

    val partTwoTestInput = readInput("Day01_part2_test")
    check(part2(partTwoTestInput) == 281)

    val input = readInput("Day01")

    val partOneResult: Int
    val partOneTimeInMillis = measureTimeMillis {
        partOneResult = part1(input)
    }
    printResultWithExecutionTime(part = 1, result = partOneResult, timeInMillis = partOneTimeInMillis)

    val partTwoResult: Int
    val partTwoTimeInMillis = measureTimeMillis {
        partTwoResult = part2(input)
    }
    printResultWithExecutionTime(part = 2, result = partTwoResult, timeInMillis = partTwoTimeInMillis)
}
