import java.io.File

fun readInput(name: String) = File("src", "$name.txt").readLines()

fun readText(name: String) = File("src", "$name.txt").readText()

// Shorthand for printing output.
fun Any?.println() = println(this)
