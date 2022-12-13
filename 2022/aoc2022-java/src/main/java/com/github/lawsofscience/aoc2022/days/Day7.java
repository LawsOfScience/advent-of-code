package com.github.lawsofscience.aoc2022.days;

import com.github.lawsofscience.aoc2022.InputFetcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Day7 extends Day {
    public Day7(InputFetcher fetcher) {
        super(fetcher);
    }

    @Override
    public void solvePart1() {
        ArrayList<String> input =
                new ArrayList<>(this.inputFetcher.getInput().lines().skip(1).toList());
        Folder root = new Folder(null);

        int solution = readFileStructure(input, root);

        System.out.println("DAY 7 PART 1: " + solution);
    }

    @Override
    public void solvePart2() {
        ArrayList<String> input =
                new ArrayList<>(this.inputFetcher.getInput().lines().skip(1).toList());
        Folder root = new Folder(null);

        int driveSize = 70_000_000;
        int minSpaceNeeded = 30_000_000;
        readFileStructure(input, root);
        correctFolderSizes(root);

        int freeSpace = driveSize - root.size;
        int spaceToFree = minSpaceNeeded - freeSpace;

        int smallestDirThatCanBeDeletedToFreeEnoughSpace = Integer.MAX_VALUE;
        LinkedList<Folder> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Folder current = queue.pop();
            if (current.size == spaceToFree) {
                smallestDirThatCanBeDeletedToFreeEnoughSpace = current.size;
                break;
            } else if (current.size > spaceToFree && current.size < smallestDirThatCanBeDeletedToFreeEnoughSpace) {
                smallestDirThatCanBeDeletedToFreeEnoughSpace = current.size;
            }

            queue.addAll(current.childFolders.values());
        }

        System.out.println("DAY 7 PART 2: " + smallestDirThatCanBeDeletedToFreeEnoughSpace);
    }

    private int readFileStructure(ArrayList<String> input, Folder root) {
        Folder current = root;
        int solution = 0;

        for (String line : input) {
            String[] components = line.split(" ");

            switch (components[0]) {
                case ("dir") -> current.childFolders.put(components[1], new Folder(current));
                case ("$") -> {
                    if (components[1].equals("ls")) {
                        continue;
                    }
                    // Encountered CD command
                    if (components[2].equals("..")) {
                        current.calculateSize();

                        if (current.size <= 100_000) {
                            solution += current.size;
                        }

                        current = current.parent;
                    } else {
                        current = current.childFolders.get(components[2]);
                    }
                }
                default -> current.childFiles.put(components[1], new File(Integer.parseInt(components[0])));
            }
        }
        return solution;
    }

    private void correctFolderSizes(Folder root) {
        for (Folder child : root.childFolders.values()) {
            correctFolderSizes(child);
        }

        root.calculateSize();
    }

    private static class Folder {
        Folder parent;
        HashMap<String, Folder> childFolders;
        HashMap<String, File> childFiles;
        int size;

        Folder(Folder parent) {
            this.parent = parent;

            this.childFolders = new HashMap<>();
            this.childFiles = new HashMap<>();
            this.size = 0;
        }

        void calculateSize() {
            this.size = 0;
            for (Folder child : childFolders.values()) {
                this.size += child.size;
            }
            for (File child : childFiles.values()) {
                this.size += child.size;
            }
        }
    }

    private static class File {
        int size;

        File(int size) {
            this.size = size;
        }
    }

}
