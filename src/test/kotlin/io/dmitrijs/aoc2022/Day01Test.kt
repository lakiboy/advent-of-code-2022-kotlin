package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 1")
internal class Day01Test {
    @Nested
    inner class Example {
        private val day = Day01(resourceAsLines("day01_example"))

        @Test
        fun puzzle1() {
            assertEquals(24_000, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(45_000, day.puzzle2())
        }
    }

    @Nested
    @Tag("personal")
    inner class Problem {
        private val day = Day01(resourceAsLines("day01"))

        @Test
        fun puzzle1() {
            assertEquals(69_206, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(197_400, day.puzzle2())
        }
    }
}
