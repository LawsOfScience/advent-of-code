package com.github.lawsofscience.aoc2022.days;

import com.github.lawsofscience.aoc2022.InputFetcher;

import java.io.BufferedReader;

public abstract class Day {
    protected BufferedReader input;
    protected boolean testMode;

    public Day(InputFetcher fetcher, boolean testMode) {
        this.input = fetcher.getInput();
        this.testMode = testMode;
    }

    public abstract void solvePart1();

    public abstract void solvePart2();
}
