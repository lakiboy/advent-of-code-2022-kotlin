package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 10")
internal class Day10Test {
    private val exampleInput = resourceAsLines("day10_example")
    private val problemInput = resourceAsLines("day10")

    @Nested
    @DisplayName("Puzzle 1")
    inner class Puzzle1 {
        @Test
        fun `solves example`() {
            assertEquals(13_140, Day10(exampleInput).puzzle1())
        }

        @Test
        fun `solves problem`() {
            assertEquals(13_720, Day10(problemInput).puzzle1())
        }
    }

    @Nested
    @DisplayName("Puzzle 2")
    inner class Puzzle2 {
        @Test
        fun `solves example`() {
            val output = """
                ##..##..##..##..##..##..##..##..##..##..
                ###...###...###...###...###...###...###.
                ####....####....####....####....####....
                #####.....#####.....#####.....#####.....
                ######......######......######......####
                #######.......#######.......#######.....
            """.trimIndent()

            assertEquals(output, Day10(exampleInput).puzzle2())
        }

        @Test
        fun `solves problem`() {
            // FBURHZCH
            val output = """
                ####.###..#..#.###..#..#.####..##..#..#.
                #....#..#.#..#.#..#.#..#....#.#..#.#..#.
                ###..###..#..#.#..#.####...#..#....####.
                #....#..#.#..#.###..#..#..#...#....#..#.
                #....#..#.#..#.#.#..#..#.#....#..#.#..#.
                #....###...##..#..#.#..#.####..##..#..#.
            """.trimIndent()

            assertEquals(output, Day10(problemInput).puzzle2())
        }
    }
}
