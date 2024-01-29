package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 18")
internal class Day18Test {
    @Nested
    inner class Example {
        private val day = Day18(resourceAsLines("day18_example"))

        @Test
        fun puzzle1() {
            assertEquals(64, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(58, day.puzzle2())
        }
    }

    @Nested
    @Tag("personal")
    inner class Problem {
        private val day = Day18(resourceAsLines("day18"))

        @Test
        fun puzzle1() {
            assertEquals(4_364, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(2_508, day.puzzle2())
        }
    }
}
