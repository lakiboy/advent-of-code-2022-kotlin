package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsText
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 22")
internal class Day22Test {
    @Nested
    inner class Example {
        private val day = Day22(resourceAsText("day22_example"))

        @Test
        fun puzzle1() {
            assertEquals(6_032, day.puzzle1())
        }
    }

    @Nested
    @Tag("personal")
    inner class Problem {
        private val day = Day22(resourceAsText("day22"))

        @Test
        fun puzzle1() {
            assertEquals(117_054, day.puzzle1())
        }
    }
}
