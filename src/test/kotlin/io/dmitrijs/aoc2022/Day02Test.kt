package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 2")
internal class Day02Test {
    @Nested
    inner class Example {
        private val day = Day02(resourceAsLines("day02_example"))

        @Test
        fun puzzle1() {
            assertEquals(15, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(12, day.puzzle2())
        }
    }

    @Nested
    @Tag("personal")
    inner class Problem {
        private val day = Day02(resourceAsLines("day02"))

        @Test
        fun puzzle1() {
            assertEquals(14_163, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(12_091, day.puzzle2())
        }
    }
}
