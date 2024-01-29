package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsListOfLong
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 20")
internal class Day20Test {
    @Nested
    inner class Example {
        private val day = Day20(resourceAsListOfLong("day20_example"))

        @Test
        fun puzzle1() {
            assertEquals(3L, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(1_623_178_306L, day.puzzle2())
        }
    }

    @Nested
    @Tag("personal")
    inner class Problem {
        private val day = Day20(resourceAsListOfLong("day20"))

        @Test
        fun puzzle1() {
            assertEquals(4_426L, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(8_119_137_886_612L, day.puzzle2())
        }
    }
}
