package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 7")
internal class Day07Test {
    @Nested
    inner class Example {
        private val day = Day07(resourceAsLines("day07_example"))

        @Test
        fun puzzle1() {
            assertEquals(95_437, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(24_933_642, day.puzzle2())
        }
    }

    @Nested
    @Tag("personal")
    inner class Problem {
        private val day = Day07(resourceAsLines("day07"))

        @Test
        fun puzzle1() {
            assertEquals(1_118_405, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(12_545_514, day.puzzle2())
        }
    }
}
