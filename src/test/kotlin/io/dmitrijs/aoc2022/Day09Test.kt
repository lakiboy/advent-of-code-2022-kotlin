package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 9")
internal class Day09Test {
    @Nested
    inner class Example {
        private val exampleInput1 = """
            R 4
            U 4
            L 3
            D 1
            R 4
            D 1
            L 5
            R 2
        """.trimIndent().lines()

        private val exampleInput2 = """
            R 5
            U 8
            L 8
            D 3
            R 17
            D 10
            L 25
            U 20
        """.trimIndent().lines()

        @Test
        fun puzzle1() {
            assertEquals(13, Day09(exampleInput1).puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(1, Day09(exampleInput1).puzzle2())
            assertEquals(36, Day09(exampleInput2).puzzle2())
        }
    }

    @Nested
    @Tag("personal")
    inner class Problem {
        private val day = Day09(resourceAsLines("day09"))

        @Test
        fun puzzle1() {
            assertEquals(6_503, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(2_724, day.puzzle2())
        }
    }
}
