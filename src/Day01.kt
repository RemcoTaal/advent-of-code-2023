fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { line ->
            "${line.first { it.isDigit() }}${line.last { it.isDigit() }}".toInt()
        }
    }

    fun part2(input: List<String>): Int {
        val wordToDigitMap = mapOf(
                "one" to "1",
                "two" to "2",
                "three" to "3",
                "four" to "4",
                "five" to "5",
                "six" to "6",
                "seven" to "7",
                "eight" to "8",
                "nine" to "9"
        )

        val digits = wordToDigitMap.keys.sortedBy { it.length } + wordToDigitMap.values
        val result = input.sumOf { line ->
            val firstDigit = line.findAnyOf(strings = digits)!!.second.let { wordOrDigit ->
                if (wordOrDigit.length == 1) wordOrDigit else wordToDigitMap[wordOrDigit]
            }
            val lastDigit = line.findLastAnyOf(strings = digits)!!.second.let { wordOrDigit ->
                if (wordOrDigit.length == 1) wordOrDigit else wordToDigitMap[wordOrDigit]
            }
            (firstDigit + lastDigit).toInt()
        }
        return result
    }

    val testPart1Input = readInput("Day01_part1_test")
    check(part1(testPart1Input) == 142)

    val testPart2Input = readInput("Day01_part2_test")
    check(part2(testPart2Input) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
