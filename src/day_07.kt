private data class File(private val name: String, val size: Int)

private data class Dir(
    private val name: String,
    private val dirs: MutableSet<Dir> = mutableSetOf(),
    private val files: MutableSet<File> = mutableSetOf(),
) {
    val size get(): Int = dirs.sumOf { it.size } + files.sumOf { it.size }

    fun addDir(name: String) = dirs.add(Dir(name))

    fun addFile(name: String, size: Int) = files.add(File(name, size))

    fun getDir(name: String) = dirs.first { name == it.name }

    fun flatten(): Sequence<Dir> {
        val current = this

        return sequence {
            yield(current)
            current.dirs.forEach { child -> yieldAll(child.flatten()) }
        }
    }

    companion object {
        fun root() = Dir("/")
    }
}

private fun readFs(lines: List<String>): Dir {
    val rootDir = Dir.root()
    val pushd = mutableListOf<Dir>()

    pushd.add(rootDir)

    lines.drop(1).forEach { line ->
        val workDir = pushd.last()
        when {
            line.startsWith("dir") -> workDir.addDir(line.substring(4))
            line.startsWith("\$ ls") -> Unit
            line.startsWith("\$ cd ..") -> pushd.removeLast()
            line.startsWith("\$ cd") -> pushd.add(workDir.getDir(line.substring(5)))
            else -> workDir.addFile(line.substringAfter(" "), line.substringBefore(" ").toInt())
        }
    }

    return rootDir
}

private fun puzzle1(lines: List<String>) = readFs(lines)
    .flatten()
    .map { it.size }
    .filter { it <= 100_000 }
    .sum()

private fun puzzle2(lines: List<String>): Int {
    val fs = readFs(lines)

    val taken = fs.size
    val total = 70_000_000
    val required = 30_000_000
    val free = total - taken
    val allocate = required - free

    return fs.flatten().map { it.size }.sorted().first { it >= allocate }
}

fun main() {
    val testInput = readInput("day_07_input_test")
    check(puzzle1(testInput) == 95437)
    check(puzzle2(testInput) == 24933642)

    val input = readInput("day_07_input")
    puzzle1(input).println()
    puzzle2(input).println()
}
