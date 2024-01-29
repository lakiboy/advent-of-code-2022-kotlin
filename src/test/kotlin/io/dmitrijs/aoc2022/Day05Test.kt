package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsText
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 5")
internal class Day05Test {
    @Nested
    inner class Example {
        private val day = Day05(resourceAsText("day05_example"), 3)

        @Test
        fun puzzle1() {
            assertEquals("CMZ", day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals("MCD", day.puzzle2())
        }
    }

    @Nested
    @Tag("personal")
    inner class Problem {
        private val day = Day05(resourceAsText("day05"), 9)

        @Test
        fun puzzle1() {
            assertEquals("VQZNJMWTR", day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals("NLCDCLVMQ", day.puzzle2())
        }
    }
}
