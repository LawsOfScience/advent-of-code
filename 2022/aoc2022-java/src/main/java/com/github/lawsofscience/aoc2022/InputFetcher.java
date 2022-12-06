package com.github.lawsofscience.aoc2022;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class InputFetcher {
    private static final String INPUT_FOLDER_PATH = "../input";
    private final BufferedReader input;

    public InputFetcher(int day) throws FileNotFoundException {
        String path = System.out.format("%s/day%d.txt", INPUT_FOLDER_PATH, day).toString();
        FileReader inputFile = new FileReader(path);

        this.input = new BufferedReader(inputFile);
    }

    public BufferedReader getInput() {
        return this.input;
    }

}
