package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 8")
internal class Day08Test {
    @Nested
    inner class Example {
        private val day = Day08(resourceAsLines("day08_example"))

        @Test
        fun puzzle1() {
            assertEquals(21, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(8, day.puzzle2())
        }
    }

    @Nested
    @Tag("personal")
    inner class Problem {
        private val day = Day08(resourceAsLines("day08"))

        @Test
        fun puzzle1() {
            assertEquals(1_823, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(211_680, day.puzzle2())
        }
    }
}
