package com.github.lawsofscience.aoc2022;

import com.github.lawsofscience.aoc2022.days.Day1;
import com.github.lawsofscience.aoc2022.days.Day2;
import com.github.lawsofscience.aoc2022.days.Day3;
import com.github.lawsofscience.aoc2022.days.Day4;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        /*
        Day1 day1 = new Day1(new InputFetcher(1, false));
        day1.solvePart1();
        day1.solvePart2();

        Day2 day2 = new Day2(new InputFetcher(2, false));
        day2.solvePart1();
        day2.solvePart2();

        Day3 day3 = new Day3(new InputFetcher(3, false));
        day3.solvePart1();
        day3.solvePart2();
         */

        Day4 day4 = new Day4(new InputFetcher(4, false));
        day4.solvePart1();
        day4.solvePart2();
    }
}
