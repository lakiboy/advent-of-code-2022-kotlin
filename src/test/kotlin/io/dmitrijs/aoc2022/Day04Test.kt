package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 4")
internal class Day04Test {
    @Nested
    inner class Example {
        private val day = Day04(resourceAsLines("day04_example"))

        @Test
        fun puzzle1() {
            assertEquals(2, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(4, day.puzzle2())
        }
    }

    @Nested
    @Tag("personal")
    inner class Problem {
        private val day = Day04(resourceAsLines("day04"))

        @Test
        fun puzzle1() {
            assertEquals(424, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(804, day.puzzle2())
        }
    }
}
