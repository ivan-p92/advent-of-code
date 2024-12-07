package net.iplantevin.aoc.aoc2023

import io.kotest.matchers.shouldBe
import net.iplantevin.aoc.common.Direction.*
import net.iplantevin.aoc.common.Point
import net.iplantevin.aoc.util.AdventOfCodeTest
import org.junit.jupiter.api.Test

class AdventOfCode2023Test : AdventOfCodeTest(2023) {

    @Test
    fun `day 1 - part 1`() {
        Day1.part1(
            """1abc2
pqr3stu8vwx
a1b2c3d4e5f
treb7uchet"""
        ) shouldBe 142

        timing { Day1.part1(input(1)) shouldBe 54877 }
    }

    @Test
    fun `day 1 - part 2`() {
        Day1.part2(
            """two1nine
eightwothree
abcone2threexyz
xtwone3four
4nineeightseven2
zoneight234
7pqrstsixteen"""
        ) shouldBe 281

        timing { Day1.part2(input(1)) shouldBe 54100 }
    }

    @Test
    fun `day 2 - part 1`() {
        Day2.part1(
            """Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green""",
            12, 13, 14
        ) shouldBe 8

        timing { Day2.part1(input(2), 12, 13, 14) shouldBe 2795 }
    }

    @Test
    fun `day 2 - part 2`() {
        Day2.part2(
            """Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"""
        ) shouldBe 2286

        timing { Day2.part2(input(2)) shouldBe 75561 }
    }

    @Test
    fun `day 3 - part 1`() {
        Point(0, 1).neighbours() shouldBe listOf(
            Point(x = -1, y = 0),
            Point(x = 0, y = 0),
            Point(x = 1, y = 0),
            Point(x = -1, y = 1),
            Point(x = 1, y = 1),
            Point(x = -1, y = 2),
            Point(x = 0, y = 2),
            Point(x = 1, y = 2)
        )
        Day3.part1(
            """467..114..
...*......
..35..633.
......#...
617*......
.....+.58.
..592.....
......755.
...${'$'}.*....
.664.598.."""
        ) shouldBe 4361

        timing { Day3.part1(input(3)) shouldBe 535351 }
    }

    @Test
    fun `day 3 - part 2`() {
        Day3.part2(
            """467..114..
...*......
..35..633.
......#...
617*......
.....+.58.
..592.....
......755.
...${'$'}.*....
.664.598.."""
        ) shouldBe 467835

        timing { Day3.part2(input(3)) shouldBe 87287096 }
    }

    @Test
    fun `day 4 - part 1`() {
        Day4.part1(
            """Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"""
        ) shouldBe 13

        timing { Day4.part1(input(4)) shouldBe 25183 }
    }

    @Test
    fun `day 4 - part 2`() {
        Day4.part2(
            """Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"""
        ) shouldBe 30

        timing { Day4.part2(input(4)) shouldBe 5667240 }
    }

    @Test
    fun `day 5 - part 1`() {
        Day5.part1(
            """seeds: 79 14 55 13

seed-to-soil map:
50 98 2
52 50 48

soil-to-fertilizer map:
0 15 37
37 52 2
39 0 15

fertilizer-to-water map:
49 53 8
0 11 42
42 0 7
57 7 4

water-to-light map:
88 18 7
18 25 70

light-to-temperature map:
45 77 23
81 45 19
68 64 13

temperature-to-humidity map:
0 69 1
1 0 69

humidity-to-location map:
60 56 37
56 93 4"""
        ) shouldBe 35

        timing { Day5.part1(input(5)) shouldBe 107430936L }
    }

    @Test
    fun `day 5 - part 2`() {
        Day5.part2(
            """seeds: 79 14 55 13

seed-to-soil map:
50 98 2
52 50 48

soil-to-fertilizer map:
0 15 37
37 52 2
39 0 15

fertilizer-to-water map:
49 53 8
0 11 42
42 0 7
57 7 4

water-to-light map:
88 18 7
18 25 70

light-to-temperature map:
45 77 23
81 45 19
68 64 13

temperature-to-humidity map:
0 69 1
1 0 69

humidity-to-location map:
60 56 37
56 93 4"""
        ) shouldBe 46

        timing { Day5.part2(input(5)) shouldBe 23738616L }
    }

    @Test
    fun `day 6 - part 1`() {
        val finalInput = """
            Time:        41     77     70     96
            Distance:   249   1362   1127   1011
        """.trimIndent()

        Day6.part1(
            """Time:      7  15   30
    Distance:  9  40  200"""
        ) shouldBe 288

        timing { Day6.part1(finalInput) shouldBe 771628 }
    }

    @Test
    fun `day 6 - part 2`() {
        val finalInput = """
            Time:        41777096
            Distance:   249136211271011
        """.trimIndent()

        Day6.part2(
            """Time:      71530
    Distance:  940200"""
        ) shouldBe 71503

        timing { Day6.part2(finalInput) shouldBe 27363861 }
    }

    @Test
    fun `day 7 - part 1`() {
        Day7.part1(
            """
                    32T3K 765
                    T55J5 684
                    KK677 28
                    KTJJT 220
                    QQQJA 483
                """.trimIndent()
        ) shouldBe 6440

        timing { Day7.part1(input(7)) shouldBe 248569531L }
    }

    @Test
    fun `day 7 - part 2`() {
        Day7.part2(
            """
                    32T3K 765
                    T55J5 684
                    KK677 28
                    KTJJT 220
                    QQQJA 483
                """.trimIndent()
        ) shouldBe 5905

        timing { Day7.part2(input(7)) shouldBe 250382098L }
    }

    @Test
    fun `day 8 - part 1`() {
        Day8.part1(
            """
                    RL
    
                    AAA = (BBB, CCC)
                    BBB = (DDD, EEE)
                    CCC = (ZZZ, GGG)
                    DDD = (DDD, DDD)
                    EEE = (EEE, EEE)
                    GGG = (GGG, GGG)
                    ZZZ = (ZZZ, ZZZ)
                """.trimIndent()
        ) shouldBe 2

        Day8.part1(
            """
                    LLR
    
                    AAA = (BBB, BBB)
                    BBB = (AAA, ZZZ)
                    ZZZ = (ZZZ, ZZZ)
                """.trimIndent()
        ) shouldBe 6

        timing { Day8.part1(input(8)) shouldBe 20221L }
    }

    @Test
    fun `day 8 - part 2`() {
        Day8.part2(
            """
                    LR
    
                    11A = (11B, XXX)
                    11B = (XXX, 11Z)
                    11Z = (11B, XXX)
                    22A = (22B, XXX)
                    22B = (22C, 22C)
                    22C = (22Z, 22Z)
                    22Z = (22B, 22B)
                    XXX = (XXX, XXX)
                """.trimIndent()
        ) shouldBe 6

        timing { Day8.part2(input(8)) shouldBe 14616363770447L }
    }

    @Test
    fun `day 9 - part 1`() {
        Day9.part1(
            """
                0 3 6 9 12 15
                1 3 6 10 15 21
                10 13 16 21 30 45
            """.trimIndent()
        ) shouldBe 114

        timing { Day9.part1(input(9)) shouldBe 1987402313L }
    }

    @Test
    fun `day 9 - part 2`() {
        Day9.part2(
            """
                0 3 6 9 12 15
                1 3 6 10 15 21
                10 13 16 21 30 45
            """.trimIndent()
        ) shouldBe 2

        timing { Day9.part2(input(9)) shouldBe 900L }
    }

    @Test
    fun `day 10 - part 1`() {
        Day10.part1(
            """
                    -L|F7
                    7S-7|
                    L|7||
                    -L-J|
                    L|-JF
                """.trimIndent(),
            SOUTH
        ) shouldBe 4

        Day10.part1(
            """
                    7-F7-
                    .FJ|7
                    SJLL7
                    |F--J
                    LJ.LJ
                """.trimIndent(),
            SOUTH
        ) shouldBe 8

        timing { Day10.part1(input(10), SOUTH) shouldBe 7086 }
    }

    @Test
    fun `day 10 - part 2`() {
        Day10.part2(
            """
                    ...........
                    .S-------7.
                    .|F-----7|.
                    .||.....||.
                    .||.....||.
                    .|L-7.F-J|.
                    .|..|.|..|.
                    .L--J.L--J.
                    ...........
                """.trimIndent(),
            SOUTH
        ) shouldBe 4

        Day10.part2(
            """
                    .F----7F7F7F7F-7....
                    .|F--7||||||||FJ....
                    .||.FJ||||||||L7....
                    FJL7L7LJLJ||LJ.L-7..
                    L--J.L7...LJS7F-7L7.
                    ....F-J..F7FJ|L7L7L7
                    ....L7.F7||L7|.L7L7|
                    .....|FJLJ|FJ|F7|.LJ
                    ....FJL-7.||.||||...
                    ....L---J.LJ.LJLJ...
                """.trimIndent(),
            EAST
        ) shouldBe 8

        Day10.part2(
            """
                    FF7FSF7F7F7F7F7F---7
                    L|LJ||||||||||||F--J
                    FL-7LJLJ||||||LJL-77
                    F--JF--7||LJLJ7F7FJ-
                    L---JF-JLJ.||-FJLJJ7
                    |F|F-JF---7F7-L7L|7|
                    |FFJF7L7F-JF7|JL---7
                    7-L-JL7||F7|L7F-7F7|
                    L.L7LFJ|||||FJL7||LJ
                    L7JLJL-JLJLJL--JLJ.L
                """.trimIndent(),
            WEST
        ) shouldBe 10

        timing { Day10.part2(input(10), NORTH) shouldBe 317 }
    }

    @Test
    fun `day 11 - part 1`() {
        Day11.part1(
            """
                    ...#......
                    .......#..
                    #.........
                    ..........
                    ......#...
                    .#........
                    .........#
                    ..........
                    .......#..
                    #...#.....
                """.trimIndent(),
            1
        ) shouldBe 374

        timing { Day11.part1(input(11), 1) shouldBe 10033566 }
    }

    @Test
    fun `day 11 - part 2`() {
        Day11.part1(
            """
                    ...#......
                    .......#..
                    #.........
                    ..........
                    ......#...
                    .#........
                    .........#
                    ..........
                    .......#..
                    #...#.....
                """.trimIndent(),
            9
        ) shouldBe 1030

        Day11.part1(
            """
                    ...#......
                    .......#..
                    #.........
                    ..........
                    ......#...
                    .#........
                    .........#
                    ..........
                    .......#..
                    #...#.....
                """.trimIndent(),
            99
        ) shouldBe 8410

        timing { Day11.part1(input(11), 999_999) shouldBe 560822911938L }
    }

    @Test
    fun `day 12 - part 1`() {
        Day12.part1(
            """
                    ???.### 1,1,3
                    .??..??...?##. 1,1,3
                    ?#?#?#?#?#?#?#? 1,3,1,6
                    ????.#...#... 4,1,1
                    ????.######..#####. 1,6,5
                    ?###???????? 3,2,1
                """.trimIndent()
        ) shouldBe 21

        timing { Day12.part1(input(12)) shouldBe 7047 }
    }

    @Test
    fun `day 12 - part 2`() {
        Day12.part2(
            """
                    ???.### 1,1,3
                    .??..??...?##. 1,1,3
                    ?#?#?#?#?#?#?#? 1,3,1,6
                    ????.#...#... 4,1,1
                    ????.######..#####. 1,6,5
                    ?###???????? 3,2,1
                """.trimIndent()
        ) shouldBe 525152

        timing { Day12.part2(input(12)) shouldBe 17391848518844L }
    }

    @Test
    fun `day 13 - part 1`() {
        Day13.solve(
            """
                #.##..##.
                ..#.##.#.
                ##......#
                ##......#
                ..#.##.#.
                ..##..##.
                #.#.##.#.

                #...##..#
                #....#..#
                ..##..###
                #####.##.
                #####.##.
                ..##..###
                #....#..#
            """.trimIndent()
        ) shouldBe 405

        timing { Day13.solve(input(13)) shouldBe 37113 }
    }

    @Test
    fun `day 13 - part 2`() {
        Day13.solve(
            """
                #.##..##.
                ..#.##.#.
                ##......#
                ##......#
                ..#.##.#.
                ..##..##.
                #.#.##.#.

                #...##..#
                #....#..#
                ..##..###
                #####.##.
                #####.##.
                ..##..###
                #....#..#
            """.trimIndent(),
            true
        ) shouldBe 400

        timing { Day13.solve(input(13), true) shouldBe 30449 }
    }

    @Test
    fun `day 14 - part 1`() {
        Day14.part1(
            """
                    O....#....
                    O.OO#....#
                    .....##...
                    OO.#O....O
                    .O.....O#.
                    O.#..O.#.#
                    ..O..#O..O
                    .......O..
                    #....###..
                    #OO..#....
                """.trimIndent()
        ) shouldBe 136

        timing { Day14.part1(input(14)) shouldBe 110565 }
    }

    @Test
    fun `day 14 - part 2`() {
        Day14.part2(
            """
                    O....#....
                    O.OO#....#
                    .....##...
                    OO.#O....O
                    .O.....O#.
                    O.#..O.#.#
                    ..O..#O..O
                    .......O..
                    #....###..
                    #OO..#....
                """.trimIndent()
        ) shouldBe 64

        timing { Day14.part2(input(14)) shouldBe 89845 }
    }

    @Test
    fun `day 15 - part 1`() {
        Day15.part1("rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7") shouldBe 1320

        timing { Day15.part1(input(15)) shouldBe 514639 }
    }

    @Test
    fun `day 15 - part 2`() {
        Day15.part2("rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7") shouldBe 145

        timing { Day15.part2(input(15)) shouldBe 279470 }
    }

    @Test
    fun `day 16 - part 1`() {
        Day16.part1(
            """
                .|...\....
                |.-.\.....
                .....|-...
                ........|.
                ..........
                .........\
                ..../.\\..
                .-.-/..|..
                .|....-|.\
                ..//.|....
            """.trimIndent()
        ) shouldBe 46

        timing { Day16.part1(input(16)) shouldBe 8116 }
    }

    @Test
    fun `day 16 - part 2`() {
        Day16.part2(
            """
                .|...\....
                |.-.\.....
                .....|-...
                ........|.
                ..........
                .........\
                ..../.\\..
                .-.-/..|..
                .|....-|.\
                ..//.|....
            """.trimIndent()
        ) shouldBe 51

        timing { Day16.part2(input(16)) shouldBe 8383 }
    }

    @Test
    fun `day 17 - part 1`() {
        Day17.part1(
            """
                2413432311323
                3215453535623
                3255245654254
                3446585845452
                4546657867536
                1438598798454
                4457876987766
                3637877979653
                4654967986887
                4564679986453
                1224686865563
                2546548887735
                4322674655533
            """.trimIndent()
        ) shouldBe 102

        timing { Day17.part1(input(17)) shouldBe 1013 }
    }

    @Test
    fun `day 17 - part 2`() {
        Day17.part2(
            """
                2413432311323
                3215453535623
                3255245654254
                3446585845452
                4546657867536
                1438598798454
                4457876987766
                3637877979653
                4654967986887
                4564679986453
                1224686865563
                2546548887735
                4322674655533
            """.trimIndent()
        ) shouldBe 94

        Day17.part2(
            """
                111111111111
                999999999991
                999999999991
                999999999991
                999999999991
            """.trimIndent()
        ) shouldBe 71

        timing { Day17.part2(input(17)) shouldBe 1215 }
    }

    @Test
    fun `day 18 - part 1`() {
        Day18.part1(
            """
                R 6 (#70c710)
                D 5 (#0dc571)
                L 2 (#5713f0)
                D 2 (#d2c081)
                R 2 (#59c680)
                D 2 (#411b91)
                L 5 (#8ceee2)
                U 2 (#caa173)
                L 1 (#1b58a2)
                U 2 (#caa171)
                R 2 (#7807d2)
                U 3 (#a77fa3)
                L 2 (#015232)
                U 2 (#7a21e3)
            """.trimIndent()
        ) shouldBe 62

        timing { Day18.part1(input(18)) shouldBe 48503 }
    }

    @Test
    fun `day 18 - part 2`() {
        Day18.part2(
            """
                R 6 (#70c710)
                D 5 (#0dc571)
                L 2 (#5713f0)
                D 2 (#d2c081)
                R 2 (#59c680)
                D 2 (#411b91)
                L 5 (#8ceee2)
                U 2 (#caa173)
                L 1 (#1b58a2)
                U 2 (#caa171)
                R 2 (#7807d2)
                U 3 (#a77fa3)
                L 2 (#015232)
                U 2 (#7a21e3)
            """.trimIndent()
        ) shouldBe 952408144115

        timing { Day18.part2(input(18)) shouldBe 148442153147147 }
    }

    @Test
    fun `day 19 - part 1`() {
        Day19.part1(
            """
                px{a<2006:qkq,m>2090:A,rfg}
                pv{a>1716:R,A}
                lnx{m>1548:A,A}
                rfg{s<537:gd,x>2440:R,A}
                qs{s>3448:A,lnx}
                qkq{x<1416:A,crn}
                crn{x>2662:A,R}
                in{s<1351:px,qqz}
                qqz{s>2770:qs,m<1801:hdj,R}
                gd{a>3333:R,R}
                hdj{m>838:A,pv}
    
                {x=787,m=2655,a=1222,s=2876}
                {x=1679,m=44,a=2067,s=496}
                {x=2036,m=264,a=79,s=2244}
                {x=2461,m=1339,a=466,s=291}
                {x=2127,m=1623,a=2188,s=1013}
            """.trimIndent()
        ) shouldBe 19114

        timing { Day19.part1(input(19)) shouldBe 367602 }
    }
}
