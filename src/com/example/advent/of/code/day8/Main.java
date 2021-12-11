package com.example.advent.of.code.day8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\Adam\\IdeaProjects\\advent_of_code\\resources\\day8\\input.txt");

        List<String> inputs = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                inputs.add(st);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int resultPart1 = 0;
        long resultPart2 = 0;

        for (String line : inputs) {
            String[] numberStrings = new String[10];
            String[] numberPatterns = line.substring(0, line.lastIndexOf("|") - 1).split(" ", 10);
            String[] outputNumbersStr = line.substring(line.lastIndexOf("|") + 1).trim().split(" ");

            List<String> char5Patterns = new ArrayList<>();
            List<String> char6Patterns = new ArrayList<>();

            //Decode unique numbers
            for (String pattern : numberPatterns) {
                if (pattern.length() == 2) {
                    numberStrings[1] = pattern;
                }
                if (pattern.length() == 4) {
                    numberStrings[4] = pattern;
                }
                if (pattern.length() == 3) {
                    numberStrings[7] = pattern;
                }
                if (pattern.length() == 7) {
                    numberStrings[8] = pattern;
                }
                if (pattern.length() == 5) {
                    char5Patterns.add(pattern);
                }
                if (pattern.length() == 6) {
                    char6Patterns.add(pattern);
                }
            }

            //Decode 5 bar numbers
            for (String pattern : char5Patterns) {
                if (containsAllChars(pattern, numberStrings[7])) {
                    numberStrings[3] = pattern;
                } else if (getNumberOfMismatches(pattern, numberStrings[4]) == 2) {
                    numberStrings[5] = pattern;
                } else {
                    numberStrings[2] = pattern;
                }
            }

            //Decode 6 bar numbers
            for (String pattern : char6Patterns) {
                if (containsAllChars(pattern, numberStrings[4])) {
                    numberStrings[9] = pattern;
                } else if (containsAllChars(pattern, numberStrings[7])) {
                    numberStrings[0] = pattern;
                } else {
                    numberStrings[6] = pattern;
                }
            }

            int outputNumber = 0;

            for (String number : outputNumbersStr) {
                outputNumber *= 10;
                if ((number.length() != 5) && (number.length() != 6)) {
                    resultPart1++;
                }
                for (int i = 0; i < 10; i++) {
                    if (numberStrings[i].length() == number.length() && containsAllChars(number, numberStrings[i])) {
                        outputNumber += i;
                        break;
                    }
                }
            }
            resultPart2 += outputNumber;
        }

        System.out.println(resultPart1);
        System.out.println(resultPart2);
    }

    private static boolean containsAllChars(String pattern, String searchChars) {
        for (int i = 0; i < searchChars.length(); i++) {
            if (!pattern.contains(searchChars.substring(i, i + 1))) {
                return false;
            }
        }
        return true;
    }

    private static int getNumberOfMismatches(String pattern, String searchChars) {
        int result = 0;
        for (int i = 0; i < pattern.length(); i++) {
            if (!searchChars.contains(pattern.substring(i, i + 1))) {
                result++;
            }
        }
        return result;
    }
}
