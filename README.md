# Advent of Code 2022 in Kotlin

[![CircleCI](https://dl.circleci.com/status-badge/img/gh/lakiboy/advent-of-code-2022-kotlin.svg?style=svg&circle-token=0104223da0789fd7cbd9f2a2d030f91c76845550)](https://dl.circleci.com/status-badge/redirect/gh/lakiboy/advent-of-code-2022-kotlin/tree/main)

My solutions (WIP) for the [Advent of Code 2022](https://adventofcode.com/2022) puzzles in [Kotlin](https://kotlinlang.org).

The ultimate goal:

- to play around with Kotlin and improve [Standard library](https://kotlinlang.org/api/latest/jvm/stdlib/) knowledge;
- to revisit some algorithms and stay in shape;
- to evaluate [kotlinx.benchmark](https://github.com/Kotlin/kotlinx-benchmark);
- and most importantly to have fun.

## Puzzles

| Day | Title                    | Task                                         | Input                                | Solution                                               | Test                                                     |
|-----|--------------------------|----------------------------------------------|--------------------------------------|--------------------------------------------------------|----------------------------------------------------------|
| 1   | Calorie Counting         | [Docs](https://adventofcode.com/2022/day/1)  | [Text](src/main/resources/day01.txt) | [Source](src/main/kotlin/io/dmitrijs/aoc2022/Day01.kt) | [Test](src/test/kotlin/io/dmitrijs/aoc2022/Day01Test.kt) |
| 2   | Rock Paper Scissors      | [Docs](https://adventofcode.com/2022/day/2)  | [Text](src/main/resources/day02.txt) | [Source](src/main/kotlin/io/dmitrijs/aoc2022/Day02.kt) | [Test](src/test/kotlin/io/dmitrijs/aoc2022/Day02Test.kt) |
| 3   | Rucksack Reorganization  | [Docs](https://adventofcode.com/2022/day/3)  | [Text](src/main/resources/day03.txt) | [Source](src/main/kotlin/io/dmitrijs/aoc2022/Day03.kt) | [Test](src/test/kotlin/io/dmitrijs/aoc2022/Day03Test.kt) |
| 4   | Camp Cleanup             | [Docs](https://adventofcode.com/2022/day/4)  | [Text](src/main/resources/day04.txt) | [Source](src/main/kotlin/io/dmitrijs/aoc2022/Day04.kt) | [Test](src/test/kotlin/io/dmitrijs/aoc2022/Day04Test.kt) |
| 5   | Supply Stacks            | [Docs](https://adventofcode.com/2022/day/5)  | [Text](src/main/resources/day05.txt) | [Source](src/main/kotlin/io/dmitrijs/aoc2022/Day05.kt) | [Test](src/test/kotlin/io/dmitrijs/aoc2022/Day05Test.kt) |
| 6   | Tuning Trouble           | [Docs](https://adventofcode.com/2022/day/6)  | [Text](src/main/resources/day06.txt) | [Source](src/main/kotlin/io/dmitrijs/aoc2022/Day06.kt) | [Test](src/test/kotlin/io/dmitrijs/aoc2022/Day06Test.kt) |
| 7   | No Space Left On Device  | [Docs](https://adventofcode.com/2022/day/7)  | [Text](src/main/resources/day07.txt) | [Source](src/main/kotlin/io/dmitrijs/aoc2022/Day07.kt) | [Test](src/test/kotlin/io/dmitrijs/aoc2022/Day07Test.kt) |
| 8   | Treetop Tree House       | [Docs](https://adventofcode.com/2022/day/8)  | [Text](src/main/resources/day08.txt) | [Source](src/main/kotlin/io/dmitrijs/aoc2022/Day08.kt) | [Test](src/test/kotlin/io/dmitrijs/aoc2022/Day08Test.kt) |
| 9   | Rope Bridge              | [Docs](https://adventofcode.com/2022/day/9)  | [Text](src/main/resources/day09.txt) | [Source](src/main/kotlin/io/dmitrijs/aoc2022/Day09.kt) | [Test](src/test/kotlin/io/dmitrijs/aoc2022/Day09Test.kt) |
| 10  | Cathode-Ray Tube         | [Docs](https://adventofcode.com/2022/day/10) | [Text](src/main/resources/day10.txt) | [Source](src/main/kotlin/io/dmitrijs/aoc2022/Day10.kt) | [Test](src/test/kotlin/io/dmitrijs/aoc2022/Day10Test.kt) |
| 11  | Monkey in the Middle     | [Docs](https://adventofcode.com/2022/day/11) | [Text](src/main/resources/day11.txt) | [Source](src/main/kotlin/io/dmitrijs/aoc2022/Day11.kt) | [Test](src/test/kotlin/io/dmitrijs/aoc2022/Day11Test.kt) |
| 12  | Hill Climbing Algorithm  | [Docs](https://adventofcode.com/2022/day/12) | [Text](src/main/resources/day12.txt) | [Source](src/main/kotlin/io/dmitrijs/aoc2022/Day12.kt) | [Test](src/test/kotlin/io/dmitrijs/aoc2022/Day12Test.kt) |
| 13  | Distress Signal          | [Docs](https://adventofcode.com/2022/day/13) | [Text](src/main/resources/day13.txt) | [Source](src/main/kotlin/io/dmitrijs/aoc2022/Day13.kt) | [Test](src/test/kotlin/io/dmitrijs/aoc2022/Day13Test.kt) |
| 14  | Regolith Reservoir       | [Docs](https://adventofcode.com/2022/day/14) | [Text](src/main/resources/day14.txt) | [Source](src/main/kotlin/io/dmitrijs/aoc2022/Day14.kt) | [Test](src/test/kotlin/io/dmitrijs/aoc2022/Day14Test.kt) |
| 15  | Beacon Exclusion Zone    | [Docs](https://adventofcode.com/2022/day/15) | [Text](src/main/resources/day15.txt) | [Source](src/main/kotlin/io/dmitrijs/aoc2022/Day15.kt) | [Test](src/test/kotlin/io/dmitrijs/aoc2022/Day15Test.kt) |
| 17  | Pyroclastic Flow         | [Docs](https://adventofcode.com/2022/day/17) | [Text](src/main/resources/day17.txt) | [Source](src/main/kotlin/io/dmitrijs/aoc2022/Day17.kt) | [Test](src/test/kotlin/io/dmitrijs/aoc2022/Day17Test.kt) |
| 18  | Boiling Boulders         | [Docs](https://adventofcode.com/2022/day/18) | [Text](src/main/resources/day18.txt) | [Source](src/main/kotlin/io/dmitrijs/aoc2022/Day18.kt) | [Test](src/test/kotlin/io/dmitrijs/aoc2022/Day18Test.kt) |
| 20  | Grove Positioning System | [Docs](https://adventofcode.com/2022/day/20) | [Text](src/main/resources/day20.txt) | [Source](src/main/kotlin/io/dmitrijs/aoc2022/Day20.kt) | [Test](src/test/kotlin/io/dmitrijs/aoc2022/Day20Test.kt) |
| 21  | Monkey Math              | [Docs](https://adventofcode.com/2022/day/21) | [Text](src/main/resources/day21.txt) | [Source](src/main/kotlin/io/dmitrijs/aoc2022/Day21.kt) | [Test](src/test/kotlin/io/dmitrijs/aoc2022/Day21Test.kt) |
