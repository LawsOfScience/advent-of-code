package com.github.lawsofscience.aoc2022.days;

import com.github.lawsofscience.aoc2022.BadInputException;
import com.github.lawsofscience.aoc2022.InputFetcher;
import com.google.common.collect.Lists;

import java.util.*;

public class Day5 extends Day {
    private HashMap<Integer, Stack<String>> crates;
    private List<Day5Instruction> instructions;

    @Override
    public void solvePart1() {
        parseInput();

        for (Day5Instruction instruction : instructions) {
            for (int count = 0; count < instruction.amount; count++) {
                crates.get(instruction.destinationPile - 1).push(crates.get(instruction.originPile - 1).pop());
            }
        }

        System.out.print("DAY 5 PART 1: ");
        crates.values().forEach(column -> System.out.print(column.peek()));
        System.out.println();
    }

    @Override
    public void solvePart2() {
        parseInput();

        for (Day5Instruction instruction : instructions) {
            Stack<String> temp = new Stack<>();

            for (int count = 0; count < instruction.amount; count++) {
                temp.push(
                        crates.get(instruction.originPile - 1).pop()
                );
            }
            for (int count = 0; count < instruction.amount; count++) {
                crates.get(instruction.destinationPile - 1).push(temp.pop());
            }
        }

        System.out.print("DAY 5 PART 2: ");
        crates.values().forEach(column -> System.out.print(column.peek()));
        System.out.println();
    }


    public Day5(InputFetcher fetcher) {
        super(fetcher);
    }

    private void parseInput() {
        String[] rawInstructions = this.inputFetcher.getInput().split("\n\n");
        this.instructions = Arrays.stream(rawInstructions[1].split("\n"))
                .map(instruction -> Arrays.stream(instruction.split(" "))
                        .filter(item -> item.matches("[0-9]+"))
                        .mapToInt(Integer::parseInt)
                        .toArray())
                .map(instruction -> new Day5Instruction(instruction[0], instruction[1], instruction[2]))
                .toList();

        /*
            WARNING: My not very elegant solution to parsing
            the actual crates in the input file is ahead.

            You may be in for a bit of a ride. I'll try to narrate it as we go.
        */

        String[] splitCrates = rawInstructions[0].split("\n");
        int splitCratesLength = splitCrates.length; // Need this so the last row with numbers can be removed

        // However, we also need that row for right now to
        // determine how many columns we need
        OptionalInt numColumns = Arrays.stream(splitCrates[splitCratesLength - 1].split(""))
                .filter(item -> item.matches("[0-9]+"))
                .mapToInt(Integer::parseInt)
                .max();
        if (numColumns.isEmpty()) { throw new BadInputException("Couldn't determine number of columns"); }

        // Making the columns...
        HashMap<Integer, Stack<String>> crateColumns = new HashMap<>(numColumns.getAsInt());
        for (int i = 0; i < numColumns.getAsInt(); i++) {
            crateColumns.put(i, new Stack<>()); // Pre-populate with all the columns we need
        }

        // NOW. To parse the crates
        List<List<String>> parsedRows = Arrays.stream(Arrays.copyOf(splitCrates, splitCratesLength - 1))
                .map(crateRow -> {
                    // Skip the first character in each row
                    // For example, if the row is "[A] [B]", we want to skip the
                    // first "[" so we can get the first crate letter directly
                    List<String> rowItems = Arrays.stream(crateRow.split("")).skip(1).toList();

                    // Crate letters appear every 4th character after
                    // shaving off the first character (the first "[")
                    List<List<String>> partitionedItems = Lists.partition(rowItems, 4);

                    // ...and now extract all the crate letters in each row
                    return partitionedItems.stream()
                            .map(row -> row.get(0))
                            .toList();
                }).toList();

        // Gotta reverse this so the crates get put into the
        // column stacks in the right order
        Lists.reverse(parsedRows).forEach(row -> {
            // Now to insert the crates into the columns
            // by going across each row and placing each entry
            // into its corresponding column
            for (int columnNum = 0; columnNum < row.size(); columnNum++) {
                String crate = row.get(columnNum);
                if (crate.equals(" ")) { continue; }

                crateColumns.get(columnNum).push(crate);
            }
        });

        this.crates = crateColumns; // And finally, we're done
    }
}

class Day5Instruction {
    int amount;
    int originPile;
    int destinationPile;

    Day5Instruction(int amount, int originPile, int destinationPile) {
        this.amount = amount;
        this.originPile = originPile;
        this.destinationPile = destinationPile;
    }
}
