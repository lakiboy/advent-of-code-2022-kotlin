package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsText
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 11")
internal class Day11Test {
    private val exampleInput = resourceAsText("day11_example")
    private val problemInput = resourceAsText("day11")

    @Nested
    @DisplayName("Puzzle 1")
    inner class Puzzle1 {
        @Test
        fun `solves example`() {
            assertEquals(10_605L, Day11(exampleInput).puzzle1())
        }

        @Test
        fun `solves problem`() {
            assertEquals(69_918L, Day11(problemInput).puzzle1())
        }
    }

    @Nested
    @DisplayName("Puzzle 2")
    inner class Puzzle2 {
        @Test
        fun `solves example`() {
            assertEquals(2_713_310_158L, Day11(exampleInput).puzzle2())
        }

        @Test
        fun `solves problem`() {
            assertEquals(19_573_408_701L, Day11(problemInput).puzzle2())
        }
    }
}
