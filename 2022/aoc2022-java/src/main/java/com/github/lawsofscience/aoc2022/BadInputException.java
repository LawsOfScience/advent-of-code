package com.github.lawsofscience.aoc2022;

public class BadInputException extends RuntimeException {
    private final String message;

    public BadInputException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BadInputException{" +
                "message='" + message + '\'' +
                '}';
    }
}
