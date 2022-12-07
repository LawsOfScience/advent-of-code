package com.github.lawsofscience.aoc2022.days;

import com.github.lawsofscience.aoc2022.BadInputException;
import com.github.lawsofscience.aoc2022.InputFetcher;
import com.google.common.collect.Lists;

import java.util.*;

public class Day3 extends Day {

    private static final ArrayList<String> itemList = new ArrayList<>(
            Arrays.asList("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".split("")));

    public Day3(InputFetcher fetcher) {
        super(fetcher);
    }

    @Override
    public void solvePart1() {
        int result = Arrays.stream(this.inputFetcher.getInput().split("\n"))
                .mapToInt(backpack -> {
                    int middle = backpack.length() / 2;
                    String[][] compartments = {
                            backpack.substring(0, middle).split(""),
                            backpack.substring(middle).split("")
                    };
                    HashSet<String> compartmentSet = new HashSet<>(Arrays.asList(compartments[0]));

                    String duplicateItem = "%";
                    for (String item : compartments[1]) {
                        if (compartmentSet.contains(item)) {
                            duplicateItem = item;
                            break;
                        }
                    }

                    return getItemValue(duplicateItem);
                })
                .sum();

        System.out.format("DAY 3 PART 1: %d\n", result);
    }

    @Override
    public void solvePart2() {
        List<String> bagList = Arrays.asList(this.inputFetcher.getInput().split("\n"));
        int result = Lists.partition(bagList, 3).stream()
                .mapToInt(elfGroup -> {
                    // Each elf group will have 3 items
                    HashSet<String> firstElfItemSet =
                            new HashSet<>(Arrays.asList(elfGroup.get(0).split("")));
                    HashSet<String> secondElfItemSet =
                            new HashSet<>(Arrays.asList(elfGroup.get(1).split("")));
                    HashSet<String> thirdElfItemSet =
                            new HashSet<>(Arrays.asList(elfGroup.get(2).split("")));

                    // Intersect all of the sets to
                    firstElfItemSet.retainAll(secondElfItemSet);
                    firstElfItemSet.retainAll(thirdElfItemSet);
                    String duplicateItem = (String) firstElfItemSet.toArray()[0];

                    return getItemValue(duplicateItem);
                })
                .sum();

        System.out.format("DAY 3 PART 2: %d\n", result);
    }

    private static int getItemValue(String item) {
        if (item.equals("%")) { throw new BadInputException("No duplicate item found"); }
        return itemList.indexOf(item) + 1;
    }
}
