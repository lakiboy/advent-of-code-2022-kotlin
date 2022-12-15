import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

fun readInput(name: String) = File("src", "$name.txt").readLines()

fun readText(name: String) = File("src", "$name.txt").readText()

// Shorthand for printing output.
fun Any?.println() = println(this)

// Converts string to md5 hash.
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')
