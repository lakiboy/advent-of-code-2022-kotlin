package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 24")
internal class Day24Test {
    @Nested
    inner class Example {
        private val day = Day24(resourceAsLines("day24_example"))

        @Test
        fun puzzle1() {
            assertEquals(18, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(54, day.puzzle2())
        }
    }

    @Nested
    @Tag("personal")
    inner class Problem {
        private val day = Day24(resourceAsLines("day24"))

        @Test
        fun puzzle1() {
            assertEquals(311, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(869, day.puzzle2())
        }
    }
}
