package com.github.lawsofscience.aoc2022.days;

import com.github.lawsofscience.aoc2022.InputFetcher;
import com.google.common.collect.Range;

import java.util.Arrays;

public class Day4 extends Day {
    public Day4(InputFetcher fetcher) {
        super(fetcher);
    }

    @Override
    public void solvePart1() {
        long result = Arrays.stream(this.inputFetcher.getInput().split("\n"))
                .map(JobPair::new)
                .filter(JobPair::isOneRangeEnclosed)
                .count();

        System.out.println("DAY 4 PART 1: " + result);
    }

    @Override
    public void solvePart2() {
        long result = Arrays.stream(this.inputFetcher.getInput().split("\n"))
                .map(JobPair::new)
                .filter(JobPair::doRangesOverlap)
                .count();

        System.out.println("DAY 4 PART 2: " + result);
    }
}

class JobPair {
    Range<Integer> firstRange;
    Range<Integer> secondRange;

    JobPair(String input) {
        String[] jobs = input.split(",");

        firstRange = Range.closed(
                Integer.parseInt(jobs[0].split("-")[0]),
                Integer.parseInt(jobs[0].split("-")[1])
        );
        secondRange = Range.closed(
                Integer.parseInt(jobs[1].split("-")[0]),
                Integer.parseInt(jobs[1].split("-")[1])
        );
    }

    boolean isOneRangeEnclosed() {
        return (firstRange.encloses(secondRange) || secondRange.encloses(firstRange));
    }

    boolean doRangesOverlap() {
        return (firstRange.isConnected(secondRange));
    }
}
