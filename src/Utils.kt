import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

fun printPartResultWithExecutionTime(part: Int, result: Any, timeInMillis: Long) {
    check(part in 1..2) { "part should be either 1 or 2" }

    val partResult = "Part $part: $result"
    val executionTime = "Execution time: ${timeInMillis}ms"

    println(partResult)
    println(executionTime)
}
