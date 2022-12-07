package com.github.lawsofscience.aoc2022;

import com.github.lawsofscience.aoc2022.days.Day1;
import com.github.lawsofscience.aoc2022.days.Day2;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        /*
        Day1 day1 = new Day1(new InputFetcher(1, false));
        day1.solvePart1();
        day1.solvePart2();
        */
        Day2 day2 = new Day2(new InputFetcher(2, false));
        day2.solvePart1();
        day2.solvePart2();
    }
}
