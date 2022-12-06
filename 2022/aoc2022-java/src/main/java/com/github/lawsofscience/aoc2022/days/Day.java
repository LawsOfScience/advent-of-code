package com.github.lawsofscience.aoc2022.days;

import com.github.lawsofscience.aoc2022.InputFetcher;

public abstract class Day {
    protected InputFetcher inputFetcher;
    protected boolean testMode;

    public Day(InputFetcher fetcher, boolean testMode) {
        this.inputFetcher = fetcher;
        this.testMode = testMode;
    }

    public abstract void solvePart1();

    public abstract void solvePart2();
}
