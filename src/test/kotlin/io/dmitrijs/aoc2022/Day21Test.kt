package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 21")
internal class Day21Test {
    @Nested
    inner class Example {
        private val day = Day21(resourceAsLines("day21_example"))

        @Test
        fun puzzle1() {
            assertEquals(152L, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(301L, day.puzzle2())
        }
    }

    @Nested
    @Tag("personal")
    inner class Problem {
        private val day = Day21(resourceAsLines("day21"))

        @Test
        fun puzzle1() {
            assertEquals(87_457_751_482_938L, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(3_221_245_824_363L, day.puzzle2())
        }
    }
}
