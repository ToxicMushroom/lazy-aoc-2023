fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { line ->
            val number = line.find { it.isDigit() }!! + "" + line.findLast { it.isDigit() }!!
            number.toInt()
        }
    }

//    fun part2(input: List<String>): Int {
//        val debug = input.map { line ->
//            val firstRealDigit = line.indexOfFirst { it.isDigit() }.takeIf { it > -1 } ?: Int.MAX_VALUE
//            val firstFakeDigit = line.indexOfAny(numbers).takeIf { it > -1 } ?: Int.MAX_VALUE
//            val lastRealDigit = line.indexOfLast { it.isDigit() }.takeIf { it > -1 } ?: Int.MIN_VALUE
//            val lastFakeDigit = line.lastIndexOfAny(numbers).takeIf { it > -1 } ?: Int.MIN_VALUE
//            var twoDigits = ""
//            if (firstFakeDigit < firstRealDigit) {
//                val first3Letters = line.substring(firstFakeDigit, firstFakeDigit + 3)
//                twoDigits += numbers.indexOfFirst { number -> number.startsWith(first3Letters) } + 1
//            } else {
//                twoDigits += line[firstRealDigit]
//            }
//
//            if (lastFakeDigit > lastRealDigit) {
//                val first3Letters = line.substring(lastFakeDigit, lastFakeDigit + 3)
//                twoDigits += numbers.indexOfLast { number -> number.startsWith(first3Letters) } + 1
//            } else {
//                twoDigits += line[lastRealDigit]
//            }
//
//            twoDigits.toInt()
//        }
//        println(debug)
//        return debug.sum()
//    }

    val numbers = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

    val validDigits = Regex("(?<wordDigit>one|two|three|four|five|six|seven|eight|nine)|(?<digit>\\d)")
    fun wordToDigitConverter(digitOrWord: String): String = numbers.indexOf(digitOrWord)
        .takeIf { it >= 0 }
        ?.let { (it + 1).toString() } ?: digitOrWord
    /* Take 2 with a regex cuz the first solution was unreadable lol */
    fun part2(input: List<String>): Int {
        val debug = input.map { line ->
            val allMatches = validDigits.findAll(line)
            val firstMatch = allMatches.sortedBy { it.range.first }.first().value
            val lastMatch = allMatches.sortedByDescending { it.range.first }.first().value
            val firstDigit = wordToDigitConverter(firstMatch)
            val lastDigit = wordToDigitConverter(lastMatch)
            (firstDigit + lastDigit).toInt()
        }
        println(debug)
        return debug.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    val testInput2 = readInput("Day01_test2")
    check(part1(testInput) == 142)
    check(part2(testInput2) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
