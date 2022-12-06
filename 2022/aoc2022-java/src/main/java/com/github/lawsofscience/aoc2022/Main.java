package com.github.lawsofscience.aoc2022;

import com.github.lawsofscience.aoc2022.days.Day1;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Day1 day1 = new Day1(new InputFetcher(1), false);
        day1.solvePart1();
        day1.solvePart2();
    }
}
