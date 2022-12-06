package com.github.lawsofscience.aoc2022.days;

import com.github.lawsofscience.aoc2022.InputFetcher;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class Day1 extends Day {
    public Day1(InputFetcher fetcher, boolean testMode) {
        super(fetcher, testMode);
    }

    private IntStream getCalorieSums() {
        return Arrays.stream(this.inputFetcher
                .getInput()
                .split("\n\n"))
                .map(elf -> elf.lines().mapToInt(Integer::parseInt).sum())
                .mapToInt(Integer::intValue);
    }

    @Override
    public void solvePart1() {
        OptionalInt max = getCalorieSums().max();

        if (max.isPresent()) {
            System.out.format("DAY 1 PART 1: %d\n", max.getAsInt());
        } else {
            System.out.println("DAY 1 PART 1: Couldn't get max value");
        }
    }

    @Override
    public void solvePart2() {
        // Calling getCalorieSums() twice is a bit inefficient...
        // but necessary since the stream can't be stored and operated on multiple times
        long calorieSumsLen = getCalorieSums().count();
        int result = getCalorieSums().sorted().skip(calorieSumsLen - 3).limit(3).sum();
        System.out.format("DAY 1 PART 2: %d\n", result);
    }
}
