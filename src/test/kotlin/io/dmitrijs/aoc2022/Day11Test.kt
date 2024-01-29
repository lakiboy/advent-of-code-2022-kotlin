package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsText
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 11")
internal class Day11Test {
    @Nested
    inner class Example {
        private val day = Day11(resourceAsText("day11_example"))

        @Test
        fun puzzle1() {
            assertEquals(10_605L, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(2_713_310_158L, day.puzzle2())
        }
    }

    @Nested
    @Tag("personal")
    inner class Problem {
        private val day = Day11(resourceAsText("day11"))

        @Test
        fun puzzle1() {
            assertEquals(69_918L, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(19_573_408_701L, day.puzzle2())
        }
    }
}
