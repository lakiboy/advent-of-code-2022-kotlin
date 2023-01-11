package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsText
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 6")
internal class Day06Test {
    private val problemInput = resourceAsText("day06")

    @Nested
    @DisplayName("Puzzle 1")
    inner class Puzzle1 {
        @Test
        fun `solves example`() {
            assertEquals(5, Day06("bvwbjplbgvbhsrlpgdmjqwftvncz").puzzle1())
            assertEquals(6, Day06("nppdvjthqldpwncqszvftbrmjlhg").puzzle1())
            assertEquals(10, Day06("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg").puzzle1())
        }

        @Test
        fun `solves problem`() {
            assertEquals(1_566, Day06(problemInput).puzzle1())
        }
    }

    @Nested
    @DisplayName("Puzzle 2")
    inner class Puzzle2 {
        @Test
        fun `solves example`() {
            assertEquals(23, Day06("bvwbjplbgvbhsrlpgdmjqwftvncz").puzzle2())
            assertEquals(23, Day06("nppdvjthqldpwncqszvftbrmjlhg").puzzle2())
            assertEquals(29, Day06("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg").puzzle2())
        }

        @Test
        fun `solves problem`() {
            assertEquals(2_265, Day06(problemInput).puzzle2())
        }
    }
}
