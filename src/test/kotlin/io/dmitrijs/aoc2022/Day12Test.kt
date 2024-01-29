package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 12")
internal class Day12Test {
    @Nested
    inner class Example {
        private val day = Day12(resourceAsLines("day12_example"))

        @Test
        fun puzzle1() {
            assertEquals(31, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(29, day.puzzle2())
        }
    }

    @Nested
    @Tag("personal")
    inner class Problem {
        private val day = Day12(resourceAsLines("day12"))

        @Test
        fun puzzle1() {
            assertEquals(361, day.puzzle1())
            assertEquals(361, day.puzzle1Dijkstra())
        }

        @Test
        fun puzzle2() {
            assertEquals(354, day.puzzle2())
            assertEquals(354, day.puzzle2Dijkstra())
        }
    }
}
