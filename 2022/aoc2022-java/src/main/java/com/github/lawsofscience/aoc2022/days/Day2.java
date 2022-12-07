package com.github.lawsofscience.aoc2022.days;

import com.github.lawsofscience.aoc2022.BadInputException;
import com.github.lawsofscience.aoc2022.InputFetcher;

import java.util.ArrayList;
import java.util.Arrays;

public class Day2 extends Day {
    public Day2(InputFetcher fetcher) {
        super(fetcher);
    }

    // These solutions could have been done better,
    // but for having not used Java nor really gotten into Streams,
    // I think this is alright.

    @Override
    public void solvePart1() {
        // Rock:        A  X
        // Paper:       B  Y
        // Scissors:    C  Z

        int result = Arrays.stream(this.inputFetcher
                .getInput()
                .trim()
                .split("\n"))
                .map(rounds -> {
                    Object[] parsedPlays = Arrays.stream(rounds.trim().split(" "))
                            .map(play -> switch (play) {
                                case "A", "X": yield Play.Rock;
                                case "B", "Y": yield Play.Paper;
                                case "C", "Z": yield Play.Scissors;
                                default: throw new BadInputException("Couldn't parse rock-paper-scissors input");
                            }).toArray();

                    // Chunking the plays into groups of two
                    // and forming new Rounds with them.
                    // There should always be an even number of plays
                    ArrayList<Round> parsedRounds = new ArrayList<>((parsedPlays.length / 2) + 1);
                    Play temp = null;
                    for (Object parsedPlay : parsedPlays) {
                        if (temp == null) {
                            temp = (Play) parsedPlay;
                        } else {
                            parsedRounds.add(new Round(temp, (Play) parsedPlay));
                        }
                    }

                    return parsedRounds;
                })
                .map(rounds -> rounds.stream().mapToInt(Round::getResult).sum())
                .toList().stream().mapToInt(Integer::intValue).sum();

        System.out.println("DAY 2 PART 1: " + result);
    }

    @Override
    public void solvePart2() {
        int result = Arrays.stream(this.inputFetcher
                        .getInput()
                        .trim()
                        .split("\n"))
                .map(rounds -> {
                    // This is definitely a bit dangerous...
                    // but I do want to do this with Streams so that
                    // I can see how this is similar between Java and Rust
                    Object[] parsedInputs = Arrays.stream(rounds.trim().split(" "))
                            .map(play -> switch (play) {
                                case "A": yield Play.Rock;
                                case "X": yield Result.Loss;
                                case "B": yield Play.Paper;
                                case "Y": yield Result.Draw;
                                case "C": yield Play.Scissors;
                                case "Z": yield Result.Win;
                                default: throw new BadInputException("Couldn't parse rock-paper-scissors input");
                            }).toArray();

                    // Chunking the plays into groups of two
                    // and forming new Rounds with them.
                    // There should always be an even number of plays
                    ArrayList<Round> parsedRounds = new ArrayList<>((parsedInputs.length / 2) + 1);
                    Play temp = null;
                    for (Object parsedInput : parsedInputs) {
                        if (temp == null) {
                            temp = (Play) parsedInput;
                        } else {
                            parsedRounds.add(new Round(temp, (Result) parsedInput));
                        }
                    }

                    return parsedRounds;
                })
                .map(rounds -> rounds.stream().mapToInt(Round::getResult).sum())
                .toList().stream().mapToInt(Integer::intValue).sum();

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
    Result result;

    Round(Play elf, Play me) {
        this.me = me;

        // We love beautiful giant switch statements...
        // If you can think of how to clean this up further, let me know
        this.result = switch(elf) {
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

    Round(Play elf, Result desiredResult) {
        this.result = desiredResult;

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
    }

    int getResult() { return me.value + result.value; }
}
