package com.github.lawsofscience.aoc2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class InputFetcher {
    private static final String INPUT_FOLDER_PATH = "../input";
    private final String input;

    public InputFetcher(int day, boolean getTestInput) throws IOException {
        String path = String.format("%s/day%d.txt", INPUT_FOLDER_PATH, day);
        if (getTestInput) {
            path = String.format("%s/day%d_ex.txt", INPUT_FOLDER_PATH, day);
        }

        this.input = Files.readString(Path.of(path));
    }

    public String getInput() {
        return this.input;
    }

}
