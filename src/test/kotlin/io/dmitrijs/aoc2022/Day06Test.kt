package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsText
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 6")
internal class Day06Test {
    @Nested
    inner class Example {
        @Test
        fun puzzle1() {
            assertEquals(5, Day06("bvwbjplbgvbhsrlpgdmjqwftvncz").puzzle1())
            assertEquals(6, Day06("nppdvjthqldpwncqszvftbrmjlhg").puzzle1())
            assertEquals(10, Day06("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg").puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(23, Day06("bvwbjplbgvbhsrlpgdmjqwftvncz").puzzle2())
            assertEquals(23, Day06("nppdvjthqldpwncqszvftbrmjlhg").puzzle2())
            assertEquals(29, Day06("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg").puzzle2())
        }
    }

    @Nested
    @Tag("personal")
    inner class Problem {
        private val day = Day06(resourceAsText("day06"))

        @Test
        fun puzzle1() {
            assertEquals(1_566, day.puzzle1())
        }

        @Test
        fun puzzle2() {
            assertEquals(2_265, day.puzzle2())
        }
    }
}
