package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 14")
internal class Day14Test {
    @Nested
    inner class Example {
        private val day = Day14(resourceAsLines("day14_example"))

        @Test
        fun puzzle1() {
            assertEquals(24, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(93, day.puzzle2())
        }
    }

    @Nested
    @Tag("personal")
    inner class Problem {
        private val day = Day14(resourceAsLines("day14"))

        @Test
        fun puzzle1() {
            assertEquals(779, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(27_426, day.puzzle2())
        }
    }
}
