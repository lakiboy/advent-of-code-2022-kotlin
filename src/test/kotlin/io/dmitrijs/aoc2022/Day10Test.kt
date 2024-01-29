package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 10")
internal class Day10Test {
    @Nested
    inner class Example {
        private val day = Day10(resourceAsLines("day10_example"))

        @Test
        fun puzzle1() {
            assertEquals(13_140, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            val output = """
                ##..##..##..##..##..##..##..##..##..##..
                ###...###...###...###...###...###...###.
                ####....####....####....####....####....
                #####.....#####.....#####.....#####.....
                ######......######......######......####
                #######.......#######.......#######.....
            """.trimIndent()

            assertEquals(output, day.puzzle2())
        }
    }

    @Nested
    @Tag("personal")
    inner class Problem {
        private val day = Day10(resourceAsLines("day10"))

        @Test
        fun puzzle1() {
            assertEquals(13_720, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            // FBURHZCH
            val output = """
                ####.###..#..#.###..#..#.####..##..#..#.
                #....#..#.#..#.#..#.#..#....#.#..#.#..#.
                ###..###..#..#.#..#.####...#..#....####.
                #....#..#.#..#.###..#..#..#...#....#..#.
                #....#..#.#..#.#.#..#..#.#....#..#.#..#.
                #....###...##..#..#.#..#.####..##..#..#.
            """.trimIndent()

            assertEquals(output, day.puzzle2())
        }
    }
}
