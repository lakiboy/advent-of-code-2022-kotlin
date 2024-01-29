package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 23")
internal class Day23Test {
    @Nested
    inner class Example {
        private val day = Day23(resourceAsLines("day23_example"))

        @Test
        fun puzzle1() {
            assertEquals(110, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(20, day.puzzle2())
        }
    }

    @Nested
    @Tag("personal")
    inner class Problem {
        private val day = Day23(resourceAsLines("day23"))

        @Test
        fun puzzle1() {
            assertEquals(4_195, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(1_069, day.puzzle2())
        }
    }
}
