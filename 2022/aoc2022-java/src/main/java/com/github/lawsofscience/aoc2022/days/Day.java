package com.github.lawsofscience.aoc2022.days;

import com.github.lawsofscience.aoc2022.InputFetcher;

public abstract class Day {
    protected InputFetcher inputFetcher;

    public Day(InputFetcher fetcher) {
        this.inputFetcher = fetcher;
    }

    public abstract void solvePart1();

    public abstract void solvePart2();
}
