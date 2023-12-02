import kotlin.system.measureTimeMillis

fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { line ->
            "${line.first { it.isDigit() }}${line.last { it.isDigit() }}".toInt()
        }
    }

    fun part2(input: List<String>): Int {
        val digitMap = mapOf(
                "one" to '1',
                "two" to '2',
                "three" to '3',
                "four" to '4',
                "five" to '5',
                "six" to '6',
                "seven" to '7',
                "eight" to '8',
                "nine" to '9'
        )

        fun String.toDigitChar(): Char {
            return if (length == 1) first() else digitMap.getValue(key = this)
        }

        val digitRegex = Regex("[0-9]|one|two|three|four|five|six|seven|eight|nine")
        return input.sumOf { line ->
            val digits = digitRegex.findAll(input = line).flatMap { matchResult ->
                matchResult.groupValues
            }

            val firstDigit = digits.first().toDigitChar()
            val lastDigit = digits.last().toDigitChar()
            "$firstDigit$lastDigit".toInt()
        }
    }

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
