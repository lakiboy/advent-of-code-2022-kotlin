package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 15")
internal class Day15Test {
    @Nested
    inner class Example {
        private val day = Day15(resourceAsLines("day15_example"))

        @Test
        fun puzzle1() {
            assertEquals(26, day.puzzle1(10))
        }

        @Test
        fun puzzle2() {
            assertEquals(56_000_011L, day.puzzle2(20))
        }
    }

    @Nested
    @Tag("personal")
    inner class Problem {
        private val day = Day15(resourceAsLines("day15"))

        @Test
        fun puzzle1() {
            assertEquals(4_873_353, day.puzzle1(2_000_000))
        }

        @Test
        fun puzzle2() {
            assertEquals(11_600_823_139_120L, day.puzzle2(4_000_000))
        }
    }
}
