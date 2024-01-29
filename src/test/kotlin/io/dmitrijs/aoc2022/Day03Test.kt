package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 3")
internal class Day03Test {
    @Nested
    inner class Example {
        private val day = Day03(resourceAsLines("day03_example"))

        @Test
        fun puzzle1() {
            assertEquals(157, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(70, day.puzzle2())
        }
    }

    @Nested
    @Tag("personal")
    inner class Problem {
        private val day = Day03(resourceAsLines("day03"))

        @Test
        fun puzzle1() {
            assertEquals(8_394, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(2_413, day.puzzle2())
        }
    }
}
