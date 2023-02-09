package io.dmitrijs.aoc2022

class Day19(input: List<String>) {
    private val blueprints = input.map { it.toBlueprint() }.onEach { println(it) }

    fun puzzle1() = blueprints.foldIndexed(0) { index, acc, blueprint ->
        acc + (index + 1) * simulate(blueprint)
    }

    private fun simulate(blueprint: Blueprint): Int {
        return 1
    }

    private data class Factory(val ore: Int, val clay: Int, val obsidian: Int, val geode: Int)

    private data class Blueprint(val costs: Map<Material, Costs>)

    private data class Costs(val materials: Map<Material, Int>)

    private enum class Material {
        ORE, CLAY, OBSIDIAN, GEODE;

        companion object {
            fun of(value: String) = valueOf(value.uppercase())
        }
    }

    private fun String.toBlueprint() = split(": ")
        .last()
        .trim('.')
        .split(". ")
        .associate {
            val (target, count1, source1, count2, source2) =
                """^Each (\w+?) robot costs (\d+) (\w+?)(?: and (\d+) (\w+?))?$""".toRegex()
                    .find(it)
                    ?.groupValues
                    ?.drop(1)
                    ?: emptyList()

            val materials = buildList {
                add(Material.of(source1) to count1.toInt())
                if (count2.isNotEmpty() && source2.isNotEmpty()) {
                    add(Material.of(source2) to count2.toInt())
                }
            }.toMap()

            Material.of(target) to Costs(materials)
        }.let { costs -> Blueprint(costs) }
}
