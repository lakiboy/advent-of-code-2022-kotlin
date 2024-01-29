package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsText
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 13")
internal class Day13Test {
    @Nested
    inner class Example {
        private val day = Day13(resourceAsText("day13_example"))

        @Test
        fun puzzle1() {
            assertEquals(13, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(140, day.puzzle2())
        }
    }

    @Nested
    @Tag("personal")
    inner class Problem {
        private val day = Day13(resourceAsText("day13"))

        @Test
        fun puzzle1() {
            assertEquals(6_101, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(21_909, day.puzzle2())
        }
    }
}
