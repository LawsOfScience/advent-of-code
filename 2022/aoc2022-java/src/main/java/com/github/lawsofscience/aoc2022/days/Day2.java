package com.github.lawsofscience.aoc2022.days;

import com.github.lawsofscience.aoc2022.BadInputException;
import com.github.lawsofscience.aoc2022.InputFetcher;

import java.util.Arrays;
import java.util.List;

public class Day2 extends Day {
    public Day2(InputFetcher fetcher) {
        super(fetcher);
    }

    // These solutions could have been done better,
    // but for having not used Java nor really gotten into Streams,
    // I think this is alright.
    // EDIT: much prettier now, probably still room for improvement

    @Override
    public void solvePart1() {
        // Rock:        A  X
        // Paper:       B  Y
        // Scissors:    C  Z

        int result = Arrays.stream(this.inputFetcher.getInput().trim().split("\n"))
                .map(Round::parseStringAsRound)
                .mapToInt(Round::getResult)
                .sum();

        System.out.println("DAY 2 PART 1: " + result);
    }

    @Override
    public void solvePart2() {
        int result = Arrays.stream(this.inputFetcher.getInput().trim().split("\n"))
                .map(Round::parseStringAsRound)
                .map(Round::changeToDay2Parsing)
                .mapToInt(Round::getResult)
                .sum();

        System.out.println("DAY 2 PART 2: " + result);
    }
}

enum Play {
    Rock(1),
    Paper(2),
    Scissors(3);

    final int value;

    Play(int value) { this.value = value; }
}

enum Result {
    Loss(0),
    Draw(3),
    Win(6);

    final int value;

    Result(int value) { this.value = value; }
}

class Round {
    Play me;
    Play elf;
    Result result;

    Round(Play elf, Play me) {
        this.me = me;
        this.elf = elf;

        // We love beautiful giant switch statements...
        // If you can think of how to clean this up further, let me know
        this.result = switch(this.elf) {
            case Rock -> switch(this.me) {
                case Rock -> Result.Draw;
                case Paper -> Result.Win;
                case Scissors -> Result.Loss;
            };
            case Paper -> switch(this.me) {
                case Rock -> Result.Loss;
                case Paper -> Result.Draw;
                case Scissors -> Result.Win;
            };
            case Scissors -> switch(this.me) {
                case Rock -> Result.Win;
                case Paper -> Result.Loss;
                case Scissors -> Result.Draw;
            };

        };
    }

    static Round parseStringAsRound(String input) {
        List<Play> plays = Arrays.stream(input.split(" ")).map(play -> switch(play) {
            case "A", "X": yield Play.Rock;
            case "B", "Y": yield Play.Paper;
            case "C", "Z": yield Play.Scissors;
            default: throw new BadInputException("Couldn't parse rock-paper-scissors input");
        }).toList();
        return new Round(plays.get(0), plays.get(1));
    }

    Round changeToDay2Parsing() {
        // In day 2, we learn that the letter parsed as this.me
        // is actually representative of how the round needs to end,
        // so we need to update this.result accordingly
        this.result = switch(this.me) {
            case Rock -> Result.Loss;
            case Paper -> Result.Draw;
            case Scissors -> Result.Win;
        };

        // And now we need to re-calculate this.me, since we need to
        // base what we play off of how the round needs to end according
        // to the guide
        this.me = switch(this.result) {
            case Loss -> switch(elf) {
                case Rock -> Play.Scissors;
                case Paper -> Play.Rock;
                case Scissors -> Play.Paper;
            };
            case Draw -> switch(elf) {
                case Rock -> Play.Rock;
                case Paper -> Play.Paper;
                case Scissors -> Play.Scissors;
            };
            case Win -> switch(elf) {
                case Rock -> Play.Paper;
                case Paper -> Play.Scissors;
                case Scissors -> Play.Rock;
            };
        };

        return this;
    }

    int getResult() { return me.value + result.value; }
}
