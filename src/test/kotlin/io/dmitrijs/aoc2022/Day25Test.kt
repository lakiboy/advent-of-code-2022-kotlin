package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 25")
internal class Day25Test {
    @Nested
    inner class Example {
        private val day = Day25(resourceAsLines("day25_example"))

        @Test
        fun puzzle1() {
            assertEquals("2=-1=0", day.puzzle1())
        }
    }

    @Nested
    @Tag("personal")
    inner class Problem {
        private val day = Day25(resourceAsLines("day25"))

        @Test
        fun puzzle1() {
            assertEquals("2-21=02=1-121-2-11-0", day.puzzle1())
        }
    }
}
