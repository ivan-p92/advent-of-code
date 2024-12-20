package net.iplantevin.aoc.aoc2024

import io.kotest.matchers.shouldBe
import net.iplantevin.aoc.common.Point
import net.iplantevin.aoc.util.AdventOfCodeTest
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test


class AdventOfCode2024Test : AdventOfCodeTest(2024) {

    @Test
    fun `day 1 - part 1`() {
        Day1.part1(
            """
            3   4
            4   3
            2   5
            1   3
            3   9
            3   3
        """.trimIndent()
        ) shouldBe 11L

        timing { Day1.part1(input(1)) shouldBe 2086478L }
    }

    @Test
    fun `day 1 - part 2`() {
        Day1.part2(
            """
            3   4
            4   3
            2   5
            1   3
            3   9
            3   3
        """.trimIndent()
        ) shouldBe 31L

        timing { Day1.part2(input(1)) shouldBe 24941624L }
    }

    @Test
    fun `day 2 - part 1`() {
        Day2.part1(
            """
                7 6 4 2 1
                1 2 7 8 9
                9 7 6 2 1
                1 3 2 4 5
                8 6 4 4 1
                1 3 6 7 9
            """.trimIndent()
        ) shouldBe 2

        timing { Day2.part1(input(2)) shouldBe 663 }
    }

    @Test
    fun `day 2 - part 2`() {
        Day2.part2(
            """
                7 6 4 2 1
                1 2 7 8 9
                9 7 6 2 1
                1 3 2 4 5
                8 6 4 4 1
                1 3 6 7 9
            """.trimIndent()
        ) shouldBe 4

        timing { Day2.part2(input(2)) shouldBe 692 }
    }

    @Test
    fun `day 3 - part 1`() {
        Day3.part1(
            """
                xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))
                """.trimIndent()
        ) shouldBe 161

        timing { Day3.part1(input(3)) shouldBe 188741603 }
    }

    @Test
    fun `day 3 - part 2`() {
        Day3.part2(
            """
                xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))
                """.trimIndent()
        ) shouldBe 48

        timing { Day3.part2(input(3)) shouldBe 67269798 }
    }

    @Test
    fun `day 4 - part 1`() {
        Day4.part1(
            """
                MMMSXXMASM
                MSAMXMSMSA
                AMXSXMAAMM
                MSAMASMSMX
                XMASAMXAMM
                XXAMMXXAMA
                SMSMSASXSS
                SAXAMASAAA
                MAMMMXMMMM
                MXMXAXMASX
                """.trimIndent()
        ) shouldBe 18

        timing { Day4.part1(input(4)) shouldBe 2464 }
    }

    @Test
    fun `day 4 - part 2`() {
        Day4.part2(
            """
            .M.S......
            ..A..MSMS.
            .M.S.MAA..
            ..A.ASMSM.
            .M.S.M....
            ..........
            S.S.S.S.S.
            .A.A.A.A..
            M.M.M.M.M.
            ..........
            """.trimIndent()
        ) shouldBe 9

        timing { Day4.part2(input(4)) shouldBe 1982 }
    }

    @Test
    fun `day 5 - part 1`() {
        Day5.part1(
            """
                47|53
                97|13
                97|61
                97|47
                75|29
                61|13
                75|53
                29|13
                97|29
                53|29
                61|53
                97|53
                61|29
                47|13
                75|47
                97|75
                47|61
                75|61
                47|29
                75|13
                53|13
    
                75,47,61,53,29
                97,61,53,29,13
                75,29,13
                75,97,47,61,53
                61,13,29
                97,13,75,29,47
                """.trimIndent()
        ) shouldBe 143

        timing { Day5.part1(input(5)) shouldBe 6267 }
    }

    @Test
    fun `day 5 - part 2`() {
        Day5.part2(
            """
                47|53
                97|13
                97|61
                97|47
                75|29
                61|13
                75|53
                29|13
                97|29
                53|29
                61|53
                97|53
                61|29
                47|13
                75|47
                97|75
                47|61
                75|61
                47|29
                75|13
                53|13
    
                75,47,61,53,29
                97,61,53,29,13
                75,29,13
                75,97,47,61,53
                61,13,29
                97,13,75,29,47
                """.trimIndent()
        ) shouldBe 123

        timing { Day5.part2(input(5)) shouldBe 5184 }
    }

    @Test
    fun `day 6 - part 1`() {
        Day6.part1(
            """
                ....#.....
                .........#
                ..........
                ..#.......
                .......#..
                ..........
                .#..^.....
                ........#.
                #.........
                ......#...
                """.trimIndent()
        ) shouldBe 41

        timing { Day6.part1(input(6)) shouldBe 4580 }
    }

    @Test
    fun `day 6 - part 2`() {
        Day6.part2(
            """
            ....#.....
            .........#
            ..........
            ..#.......
            .......#..
            ..........
            .#..^.....
            ........#.
            #.........
            ......#...
            """.trimIndent()
        ) shouldBe 6

        timing { Day6.part2(input(6)) shouldBe 1480 }
    }

    @Test
    fun `day 7 - part 1`() {
        Day7.part1(
            """
                190: 10 19
                3267: 81 40 27
                83: 17 5
                156: 15 6
                7290: 6 8 6 15
                161011: 16 10 13
                192: 17 8 14
                21037: 9 7 18 13
                292: 11 6 16 20
                """.trimIndent()
        ) shouldBe 3749L

        timing { Day7.part1(input(7)) shouldBe 2314935962622L }
    }

    @Test
    fun `day 7 - part 2`() {
        Day7.part2(
            """
            190: 10 19
            3267: 81 40 27
            83: 17 5
            156: 15 6
            7290: 6 8 6 15
            161011: 16 10 13
            192: 17 8 14
            21037: 9 7 18 13
            292: 11 6 16 20
            """.trimIndent()
        ) shouldBe 11387L

        timing { Day7.part2(input(7)) shouldBe 401477450831495L }
    }

    @Test
    fun `day 8 - part 1`() {
        Day8.part1(
            """
            ..........
            ..........
            ..........
            ....a.....
            ........a.
            .....a....
            ..........
            ......A...
            ..........
            ..........
            """.trimIndent()
        ) shouldBe 4

        Day8.part1(
            """
            ............
            ........0...
            .....0......
            .......0....
            ....0.......
            ......A.....
            ............
            ............
            ........A...
            .........A..
            ............
            ............
            """.trimIndent()
        ) shouldBe 14

        timing { Day8.part1(input(8)) shouldBe 400 }
    }

    @Test
    fun `day 8 - part 2`() {
        Day8.part2(
            """
            T.........
            ...T......
            .T........
            ..........
            ..........
            ..........
            ..........
            ..........
            ..........
            ..........
            """.trimIndent()
        ) shouldBe 9

        Day8.part2(
            """
            ............
            ........0...
            .....0......
            .......0....
            ....0.......
            ......A.....
            ............
            ............
            ........A...
            .........A..
            ............
            ............
            """.trimIndent()
        ) shouldBe 34

        timing { Day8.part2(input(8)) shouldBe 1280 }
    }

    @Test
    fun `day 9 - part 1`() {
        Day9.part1(
            """2333133121414131402""".trimIndent()
        ) shouldBe 1928

        timing { Day9.part1(input(9)) shouldBe 6331212425418 }
    }

    @Test
    fun `day 9 - part 2`() {
        Day9.part2(
            """2333133121414131402""".trimIndent()
        ) shouldBe 2858

        timing { Day9.part2(input(9)) shouldBe 6363268339304 }
    }

    @Test
    fun `day 10 - part 1`() {
        Day10.part1(
            """
            ...0...
            ...1...
            ...2...
            6543456
            7.....7
            8.....8
            9.....9
            """.trimIndent()
        ) shouldBe 2

        Day10.part1(
            """
            ..90..9
            ...1.98
            ...2..7
            6543456
            765.987
            876....
            987....
            """.trimIndent()
        ) shouldBe 4

        Day10.part1(
            """
            10..9..
            2...8..
            3...7..
            4567654
            ...8..3
            ...9..2
            .....01
            """.trimIndent()
        ) shouldBe 3

        Day10.part1(
            """
            89010123
            78121874
            87430965
            96549874
            45678903
            32019012
            01329801
            10456732
            """.trimIndent()
        ) shouldBe 36

        timing { Day10.part1(input(10)) shouldBe 638 }
    }

    @Test
    fun `day 10 - part 2`() {
        Day10.part2(
            """
            .....0.
            ..4321.
            ..5..2.
            ..6543.
            ..7..4.
            ..8765.
            ..9....
            """.trimIndent()
        ) shouldBe 3

        Day10.part2(
            """
            ..90..9
            ...1.98
            ...2..7
            6543456
            765.987
            876....
            987....
            """.trimIndent()
        ) shouldBe 13

        Day10.part2(
            """
            012345
            123456
            234567
            345678
            4.6789
            56789.
            """.trimIndent()
        ) shouldBe 227

        Day10.part2(
            """
            89010123
            78121874
            87430965
            96549874
            45678903
            32019012
            01329801
            10456732
            """.trimIndent()
        ) shouldBe 81

        timing { Day10.part2(input(10)) shouldBe 1289 }
    }

    @Test
    fun `day 11`() {
        Day11.solve(
            """125 17""".trimIndent(),
            6
        ) shouldBe 22

        timing { Day11.solve(input(11), 25) shouldBe 184927 }
        timing { Day11.solve(input(11), 75) shouldBe 220357186726677 }
    }

    @Test
    fun `day 12 - part 1`() {
        Day12.part1(
            """
            AAAA
            BBCD
            BBCC
            EEEC
            """.trimIndent()
        ) shouldBe 140

        Day12.part1(
            """
            OOOOO
            OXOXO
            OOOOO
            OXOXO
            OOOOO
            """.trimIndent()
        ) shouldBe 772

        Day12.part1(
            """
            RRRRIICCFF
            RRRRIICCCF
            VVRRRCCFFF
            VVRCCCJFFF
            VVVVCJJCFE
            VVIVCCJJEE
            VVIIICJJEE
            MIIIIIJJEE
            MIIISIJEEE
            MMMISSJEEE
            """.trimIndent()
        ) shouldBe 1930

        timing { Day12.part1(input(12)) shouldBe 1471452L }
    }

    @Test
    fun `day 12 - part 2`() {
        Day12.part2(
            """
            AAAA
            BBCD
            BBCC
            EEEC
            """.trimIndent()
        ) shouldBe 80

        Day12.part2(
            """
            OOOOO
            OXOXO
            OOOOO
            OXOXO
            OOOOO
            """.trimIndent()
        ) shouldBe 436

        Day12.part2(
            """
            EEEEE
            EXXXX
            EEEEE
            EXXXX
            EEEEE
            """.trimIndent()
        ) shouldBe 236

        Day12.part2(
            """
            AAAAAA
            AAABBA
            AAABBA
            ABBAAA
            ABBAAA
            AAAAAA
            """.trimIndent()
        ) shouldBe 368

        Day12.part2(
            """
            RRRRIICCFF
            RRRRIICCCF
            VVRRRCCFFF
            VVRCCCJFFF
            VVVVCJJCFE
            VVIVCCJJEE
            VVIIICJJEE
            MIIIIIJJEE
            MIIISIJEEE
            MMMISSJEEE
            """.trimIndent()
        ) shouldBe 1206

        Day12.part2(
            """
            AAAAAAAA
            AACBBDDA
            AACBBAAA
            ABBAAAAA
            ABBADDDA
            AAAADADA
            AAAAAAAA
            """.trimIndent()
        ) shouldBe 946

        timing { Day12.part2(input(12)) shouldBe 863366 }
    }

    @Test
    fun `day 13 - part 1`() {
        Day13.part1(
            """
            Button A: X+94, Y+34
            Button B: X+22, Y+67
            Prize: X=8400, Y=5400

            Button A: X+26, Y+66
            Button B: X+67, Y+21
            Prize: X=12748, Y=12176

            Button A: X+17, Y+86
            Button B: X+84, Y+37
            Prize: X=7870, Y=6450

            Button A: X+69, Y+23
            Button B: X+27, Y+71
            Prize: X=18641, Y=10279
            """.trimIndent()
        ) shouldBe listOf(280, null, 200, null)

        timing { Day13.part1(input(13)).sumOf { it ?: 0 } shouldBe 28059 }
    }

    @Test
    fun `day 13 - part 2`() {
        Day13.part2(
            """
            Button A: X+94, Y+34
            Button B: X+22, Y+67
            Prize: X=8400, Y=5400

            Button A: X+26, Y+66
            Button B: X+67, Y+21
            Prize: X=12748, Y=12176

            Button A: X+17, Y+86
            Button B: X+84, Y+37
            Prize: X=7870, Y=6450

            Button A: X+69, Y+23
            Button B: X+27, Y+71
            Prize: X=18641, Y=10279
            """.trimIndent()
        ) shouldBe listOf(null, 459236326669, null, 416082282239)

        timing { Day13.part2(input(13)).sumOf { it ?: 0 } shouldBe 102255878088512 }
    }

    @Test
    fun `day 14 - part 1`() {
        Day14.part1(
            """
            p=0,4 v=3,-3
            p=6,3 v=-1,-3
            p=10,3 v=-1,2
            p=2,0 v=2,-1
            p=0,0 v=1,3
            p=3,0 v=-2,-2
            p=7,6 v=-1,-3
            p=3,0 v=-1,-2
            p=9,3 v=2,3
            p=7,3 v=-1,2
            p=2,4 v=2,-3
            p=9,5 v=-3,-3
            """.trimIndent(),
            11, 7
        ) shouldBe 12

        timing { Day14.part1(input(14), 101, 103) shouldBe 228457125 }
    }

    @Test
    fun `day 14 - part 2`() {
        timing { Day14.part2(input(14)) shouldBe 6493 }
    }

    @Test
    fun `day 15 - part 1`() {
        Day15.part1(
            """
            ########
            #..O.O.#
            ##@.O..#
            #...O..#
            #.#.O..#
            #...O..#
            #......#
            ########

            <^^>>>vv<v>>v<<
            """.trimIndent()
        ) shouldBe 2028

        Day15.part1(
            """
            ##########
            #..O..O.O#
            #......O.#
            #.OO..O.O#
            #..O@..O.#
            #O#..O...#
            #O..O..O.#
            #.OO.O.OO#
            #....O...#
            ##########

            <vv>^<v^>v>^vv^v>v<>v^v<v<^vv<<<^><<><>>v<vvv<>^v^>^<<<><<v<<<v^vv^v>^
            vvv<<^>^v^^><<>>><>^<<><^vv^^<>vvv<>><^^v>^>vv<>v<<<<v<^v>^<^^>>>^<v<v
            ><>vv>v^v^<>><>>>><^^>vv>v<^^^>>v^v^<^^>v^^>v^<^v>v<>>v^v^<v>v^^<^^vv<
            <<v<^>>^^^^>>>v^<>vvv^><v<<<>^^^vv^<vvv>^>v<^^^^v<>^>vvvv><>>v^<<^^^^^
            ^><^><>>><>^^<<^^v>>><^<v>^<vv>>v>>>^v><>^v><<<<v>>v<v<v>vvv>^<><<>^><
            ^>><>^v<><^vvv<^^<><v<<<<<><^v<<<><<<^^<v<^^^><^>>^<v^><<<^>>^v<v^v<v^
            >^>>^v>vv>^<<^v<>><<><<v<<v><>v<^vv<<<>^^v^>^^>>><<^v>>v^v><^^>>^<>vv^
            <><^^>^^^<><vvvvv^v<v<<>^v<v>v<<^><<><<><<<^^<<<^<<>><<><^^^>^^<>^>v<>
            ^^>vv<^v^v<vv>^<><v<^v>^^^>>>^^vvv^>vvv<>>>^<^>>>>>^<<^v>^vvv<>^<><<v>
            v^^>>><<^^<>>^v^<v^vv<>v^<<>^<^v^v><^<<<><<^<v><v<>vv>>v><v^<vv<>v^<<^
            """.trimIndent()
        ) shouldBe 10092

        timing { Day15.part1(input(15)) shouldBe 1429911L }
    }

    @Test
    fun `day 15 - part 2`() {
        Day15.part2(
            """
            #######
            #...#.#
            #.....#
            #..OO@#
            #..O..#
            #.....#
            #######

            <vv<<^^<<^^
            """.trimIndent()
        )

        Day15.part2(
            """
            ##########
            #..O..O.O#
            #......O.#
            #.OO..O.O#
            #..O@..O.#
            #O#..O...#
            #O..O..O.#
            #.OO.O.OO#
            #....O...#
            ##########

            <vv>^<v^>v>^vv^v>v<>v^v<v<^vv<<<^><<><>>v<vvv<>^v^>^<<<><<v<<<v^vv^v>^
            vvv<<^>^v^^><<>>><>^<<><^vv^^<>vvv<>><^^v>^>vv<>v<<<<v<^v>^<^^>>>^<v<v
            ><>vv>v^v^<>><>>>><^^>vv>v<^^^>>v^v^<^^>v^^>v^<^v>v<>>v^v^<v>v^^<^^vv<
            <<v<^>>^^^^>>>v^<>vvv^><v<<<>^^^vv^<vvv>^>v<^^^^v<>^>vvvv><>>v^<<^^^^^
            ^><^><>>><>^^<<^^v>>><^<v>^<vv>>v>>>^v><>^v><<<<v>>v<v<v>vvv>^<><<>^><
            ^>><>^v<><^vvv<^^<><v<<<<<><^v<<<><<<^^<v<^^^><^>>^<v^><<<^>>^v<v^v<v^
            >^>>^v>vv>^<<^v<>><<><<v<<v><>v<^vv<<<>^^v^>^^>>><<^v>>v^v><^^>>^<>vv^
            <><^^>^^^<><vvvvv^v<v<<>^v<v>v<<^><<><<><<<^^<<<^<<>><<><^^^>^^<>^>v<>
            ^^>vv<^v^v<vv>^<><v<^v>^^^>>>^^vvv^>vvv<>>>^<^>>>>>^<<^v>^vvv<>^<><<v>
            v^^>>><<^^<>>^v^<v^vv<>v^<<>^<^v^v><^<<<><<^<v><v<>vv>>v><v^<vv<>v^<<^
            """.trimIndent()
        ) shouldBe 9021

        timing { Day15.part2(input(15)) shouldBe 1453087 }
    }

    @Test
    fun `day 16 - part 1`() {
        Day16.part1(
            """
            ###############
            #.......#....E#
            #.#.###.#.###.#
            #.....#.#...#.#
            #.###.#####.#.#
            #.#.#.......#.#
            #.#.#####.###.#
            #...........#.#
            ###.#.#####.#.#
            #...#.....#.#.#
            #.#.#.###.#.#.#
            #.....#...#.#.#
            #.###.#.#.#.#.#
            #S..#.....#...#
            ###############
            """.trimIndent()
        ) shouldBe 7036

        Day16.part1(
            """
            #################
            #...#...#...#..E#
            #.#.#.#.#.#.#.#.#
            #.#.#.#...#...#.#
            #.#.#.#.###.#.#.#
            #...#.#.#.....#.#
            #.#.#.#.#.#####.#
            #.#...#.#.#.....#
            #.#.#####.#.###.#
            #.#.#.......#...#
            #.#.###.#####.###
            #.#.#...#.....#.#
            #.#.#.#####.###.#
            #.#.#.........#.#
            #.#.#.#########.#
            #S#.............#
            #################
            """.trimIndent()
        ) shouldBe 11048

        timing { Day16.part1(input(16)) shouldBe 99460 }
    }

    @Test
    fun `day 16 - part 2`() {
        Day16.part2(
            """
            ###############
            #.......#....E#
            #.#.###.#.###.#
            #.....#.#...#.#
            #.###.#####.#.#
            #.#.#.......#.#
            #.#.#####.###.#
            #...........#.#
            ###.#.#####.#.#
            #...#.....#.#.#
            #.#.#.###.#.#.#
            #.....#...#.#.#
            #.###.#.#.#.#.#
            #S..#.....#...#
            ###############
            """.trimIndent()
        ) shouldBe 45

        Day16.part2(
            """
            #################
            #...#...#...#..E#
            #.#.#.#.#.#.#.#.#
            #.#.#.#...#...#.#
            #.#.#.#.###.#.#.#
            #...#.#.#.....#.#
            #.#.#.#.#.#####.#
            #.#...#.#.#.....#
            #.#.#####.#.###.#
            #.#.#.......#...#
            #.#.###.#####.###
            #.#.#...#.....#.#
            #.#.#.#####.###.#
            #.#.#.........#.#
            #.#.#.#########.#
            #S#.............#
            #################
            """.trimIndent()
        ) shouldBe 64

        timing { Day16.part2(input(16)) shouldBe 500 }
    }

    @Test
    fun `day 17 - part 1`() {
        Day17.part1(
            """
            Register A: 0
            Register B: 0
            Register C: 9

            Program: 2,6
            """.trimIndent()
        ) shouldBe ""

        Day17.part1(
            """
            Register A: 10
            Register B: 0
            Register C: 0

            Program: 5,0,5,1,5,4
            """.trimIndent()
        ) shouldBe "0,1,2"

        Day17.part1(
            """
            Register A: 2024
            Register B: 0
            Register C: 0

            Program: 0,1,5,4,3,0
            """.trimIndent()
        ) shouldBe "4,2,5,6,7,7,7,7,3,1,0"

        Day17.part1(
            """
            Register A: 729
            Register B: 0
            Register C: 0

            Program: 0,1,5,4,3,0
            """.trimIndent()
        ) shouldBe "4,6,3,5,6,3,5,2,1,0"

        timing(100) { Day17.part1(input(17)) shouldBe "7,1,5,2,4,0,7,6,1" }
    }

    @Test
    fun `day 17 - part 2`() {
        timing { Day17.part2(input(17)) shouldBe 37222273957364 }
    }

    @Test
    fun `day 18 - part 1`() {
        Day18.part1(
            """
            5,4
            4,2
            4,5
            3,0
            2,1
            6,3
            2,4
            1,5
            0,6
            3,3
            2,6
            5,1
            1,2
            5,5
            2,5
            6,5
            1,4
            0,4
            6,4
            1,1
            6,1
            1,0
            0,5
            1,6
            2,0
            """.trimIndent(),
            Point(6, 6), 12
        ) shouldBe 22

        timing { Day18.part1(input(18), Point(70, 70), 1024) shouldBe 364 }
    }

    @Test
    fun `day 18 - part 2`() {
        Day18.part2(
            """
            5,4
            4,2
            4,5
            3,0
            2,1
            6,3
            2,4
            1,5
            0,6
            3,3
            2,6
            5,1
            1,2
            5,5
            2,5
            6,5
            1,4
            0,4
            6,4
            1,1
            6,1
            1,0
            0,5
            1,6
            2,0
            """.trimIndent(),
            Point(6, 6), 12
        ) shouldBe "6,1"

        timing { Day18.part2(input(18), Point(70, 70), 1024) shouldBe "52,28" }
    }

    @Test
    fun `day 19 - part 1`() {
        Day19.part1(
            """
            r, wr, b, g, bwu, rb, gb, br

            brwrr
            bggr
            gbbr
            rrbgbr
            ubwu
            bwurrg
            brgr
            bbrgwb
            """.trimIndent()
        ) shouldBe 6

        timing { Day19.part1(input(19)) shouldBe 300 }
    }

    @Test
    fun `day 19 - part 2`() {
        Day19.part2(
            """
            r, wr, b, g, bwu, rb, gb, br

            brwrr
            bggr
            gbbr
            rrbgbr
            ubwu
            bwurrg
            brgr
            bbrgwb
            """.trimIndent()
        ) shouldBe 16

        timing { Day19.part2(input(19)) shouldBe 624802218898092 }
    }

    @Test
    fun `day 20 - part 1`() {
        val input = """
            ###############
            #...#...#.....#
            #.#.#.#.#.###.#
            #S#...#.#.#...#
            #######.#.#.###
            #######.#.#...#
            #######.#.###.#
            ###..E#...#...#
            ###.#######.###
            #...###...#...#
            #.#####.#.###.#
            #.#...#.#.#...#
            #.#.#.#.#.#.###
            #...#...#...###
            ###############
            """.trimIndent()
        Day20.part1(input, 8) shouldBe 14
        Day20.part1(input, 12) shouldBe 8
        Day20.part1(input, 38) shouldBe 3

        timing { Day20.part1(input(20)) shouldBe 1438 }
    }

    @Test
    fun `day 20 - part 2`() {
        timing { Day20.part2(input(20)) shouldBe 1026446 }
    }

    @Disabled
    @Test
    fun `day 21 - part 1`() {
        Day21.part1(
            """""".trimIndent()
        ) shouldBe 0

        timing { Day21.part1(input(21)) shouldBe 0 }
    }

    @Disabled
    @Test
    fun `day 21 - part 2`() {
        Day21.part2(
            """""".trimIndent()
        ) shouldBe 0

        timing { Day21.part2(input(21)) shouldBe 0 }
    }

    @Disabled
    @Test
    fun `day 22 - part 1`() {
        Day22.part1(
            """""".trimIndent()
        ) shouldBe 0

        timing { Day22.part1(input(22)) shouldBe 0 }
    }

    @Disabled
    @Test
    fun `day 22 - part 2`() {
        Day22.part2(
            """""".trimIndent()
        ) shouldBe 0

        timing { Day22.part2(input(22)) shouldBe 0 }
    }

    @Disabled
    @Test
    fun `day 23 - part 1`() {
        Day23.part1(
            """""".trimIndent()
        ) shouldBe 0

        timing { Day23.part1(input(23)) shouldBe 0 }
    }

    @Disabled
    @Test
    fun `day 23 - part 2`() {
        Day23.part2(
            """""".trimIndent()
        ) shouldBe 0

        timing { Day23.part2(input(23)) shouldBe 0 }
    }

    @Disabled
    @Test
    fun `day 24 - part 1`() {
        Day24.part1(
            """""".trimIndent()
        ) shouldBe 0

        timing { Day24.part1(input(24)) shouldBe 0 }
    }

    @Disabled
    @Test
    fun `day 24 - part 2`() {
        Day24.part2(
            """""".trimIndent()
        ) shouldBe 0

        timing { Day24.part2(input(24)) shouldBe 0 }
    }

    @Disabled
    @Test
    fun `day 25 - part 1`() {
        Day25.part1(
            """""".trimIndent()
        ) shouldBe 0

        timing { Day25.part1(input(25)) shouldBe 0 }
    }

    @Disabled
    @Test
    fun `day 25 - part 2`() {
        Day25.part2(
            """""".trimIndent()
        ) shouldBe 0

        timing { Day25.part2(input(25)) shouldBe 0 }
    }
}
