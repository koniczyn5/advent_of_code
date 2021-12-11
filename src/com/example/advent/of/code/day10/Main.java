package com.example.advent.of.code.day10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\Adam\\IdeaProjects\\advent_of_code\\resources\\day10\\input.txt");

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

        List<Long> autocompleteResults = new ArrayList<>();

        for (String line : inputs) {
            Stack<Character> openingBrackets = new Stack<>();
            boolean isLineCorrupted = false;
            //part 1
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '('
                        || line.charAt(i) == '['
                        || line.charAt(i) == '{'
                        || line.charAt(i) == '<') {
                    openingBrackets.push(line.charAt(i));
                } else if (line.charAt(i) == ')') {
                    if (openingBrackets.pop() != '(') {
                        resultPart1 += 3;
                        isLineCorrupted = true;
                        break;
                    }
                } else if (line.charAt(i) == ']') {
                    if (openingBrackets.pop() != '[') {
                        resultPart1 += 57;
                        isLineCorrupted = true;
                        break;
                    }
                } else if (line.charAt(i) == '}') {
                    if (openingBrackets.pop() != '{') {
                        resultPart1 += 1197;
                        isLineCorrupted = true;
                        break;
                    }
                } else if (line.charAt(i) == '>') {
                    if (openingBrackets.pop() != '<') {
                        resultPart1 += 25137;
                        isLineCorrupted = true;
                        break;
                    }
                }
            }

            //part 2
            if (!isLineCorrupted) {
                long autoCompleteResult = 0;
                while (!openingBrackets.empty()) {
                    Character bracket = openingBrackets.pop();
                    autoCompleteResult *= 5;
                    if (bracket == '(') {
                        autoCompleteResult += 1;
                    } else if (bracket == '[') {
                        autoCompleteResult += 2;
                    } else if (bracket == '{') {
                        autoCompleteResult += 3;
                    } else if (bracket == '<') {
                        autoCompleteResult += 4;
                    }
                }
                autocompleteResults.add(autoCompleteResult);
            }
        }

        Collections.sort(autocompleteResults);

        long resultPart2 = autocompleteResults.get((autocompleteResults.size() - 1) / 2);

        System.out.println(resultPart1);
        System.out.println(resultPart2);
    }
}
