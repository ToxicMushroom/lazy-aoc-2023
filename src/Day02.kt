import kotlin.math.max

fun main() {
    val bagLimit = mapOf("red" to 12, "green" to 13, "blue" to 14)

    fun parts(input: List<String>): Pair<Int, Int> {
        return input.fold(0 to 0) { (accIds, accPowers), game ->
            val id = game.removePrefix("Game ").takeWhile { it.isDigit() }.toInt()
            val sets = game.removePrefix("Game $id: ").split("; ")

            val smallestBag = mutableMapOf("red" to 0, "green" to 0, "blue" to 0)
            sets.forEach { set ->
                val coloredBlocks = set.split(", ").associate {
                    val colorBlock = it.split(" ")
                    colorBlock[1] to colorBlock[0].toInt()
                }
                smallestBag.replaceAll { k, v -> max(coloredBlocks[k] ?: 0, v) }
            }

            val allBlocksInLimit = smallestBag.all { bagLimit[it.key]!! >= it.value }
            val bagPower = smallestBag.values.fold(1) { acc: Int, i: Int -> acc * i }

            val iterId = if (allBlocksInLimit) id else 0
            (accIds + iterId) to (accPowers + bagPower)
        }
    }

    fun part1(input: List<String>): Int {
        return parts(input).first
    }

    fun part2(input: List<String>): Int {
        return parts(input).second
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")

    check(part1(testInput) == 8)
    check(part2(testInput) == 2286)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
