package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsText
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 17")
internal class Day17Test {
    @Nested
    inner class Example {
        private val day = Day17(resourceAsText("day17_example"))

        @Test
        fun puzzle1() {
            assertEquals(3_068, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(1_514_285_714_288L, day.puzzle2())
        }
    }

    @Nested
    @Tag("personal")
    inner class Problem {
        private val day = Day17(resourceAsText("day17"))

        @Test
        fun puzzle1() {
            assertEquals(3_106, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(1_537_175_792_495L, day.puzzle2())
        }
    }
}
