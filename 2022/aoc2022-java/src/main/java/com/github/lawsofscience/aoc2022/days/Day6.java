package com.github.lawsofscience.aoc2022.days;

import com.github.lawsofscience.aoc2022.InputFetcher;

import java.util.HashSet;
import java.util.LinkedList;

public class Day6 extends Day {
    public Day6(InputFetcher fetcher) {
        super(fetcher);
    }

    // Using a LinkedList for this since doubly-linked lists (like Java's)
    // provide O(1) addition and removal to either end.
    // This doesn't need to make any modifications to the middle of the
    // list, so...yeah.

    @Override
    public void solvePart1() {
        char[] input = this.inputFetcher.getInput().toCharArray();

        // Populate with first 4 characters
        LinkedList<Character> fourCharacterSet = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            fourCharacterSet.addLast(input[i]);
        }

        int charsUntilStartMarker = searchCharacters(input, fourCharacterSet);
        System.out.println("DAY 6 PART 1: " + charsUntilStartMarker);
    }

    @Override
    public void solvePart2() {
        char[] input = this.inputFetcher.getInput().toCharArray();

        // Populate with first 14 characters
        LinkedList<Character> fourteenCharacterSet = new LinkedList<>();
        for (int i = 0; i < 14; i++) {
            fourteenCharacterSet.addLast(input[i]);
        }

        int charsUntilStartMarker = searchCharacters(input, fourteenCharacterSet);
        System.out.println("DAY 6 PART 2: " + charsUntilStartMarker);
    }

    private int searchCharacters(char[] rawInput, LinkedList<Character> currentCharacters) {
        int charsUntilStartMarker = -1;
        for (int i = 4; i < rawInput.length; i++) {
            if (!hasDuplicateChar(currentCharacters)) {
                charsUntilStartMarker = i;
                break;
            }

            // Advance list by one and check again
            // Could be made more efficient by noting how far it can go
            // until it gets rid of a known duplicate...
            currentCharacters.removeFirst();
            currentCharacters.addLast(rawInput[i]);
        }

        return charsUntilStartMarker;
    }

    private static boolean hasDuplicateChar(LinkedList<Character> input) {
        HashSet<Character> charSet = new HashSet<>(input.size() + 1);
        for (char item : input) {
            if (!charSet.add(item)) {
                return true;
            }
        }
        return false;
    }
}
