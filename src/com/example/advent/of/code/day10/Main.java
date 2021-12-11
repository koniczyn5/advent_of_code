package com.example.advent.of.code.day10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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

        List<Character> openingBrackets = List.of('(','[','{','<');
        Map<Character, Character> closingToOpening = Map.of(
                ')', '(',
                ']', '[',
                '}', '{',
                '>', '<');
        Map<Character, Integer> closingBracketToPoint = Map.of(
                ')', 3,
                ']', 57,
                '}', 1197,
                '>', 25137);
        Map<Character, Integer> openingBracketToPoint = Map.of(
                '(', 1,
                '[', 2,
                '{', 3,
                '<', 4);

        int resultPart1 = 0;
        List<Long> autocompleteResults = new ArrayList<>();

        for (String line : inputs) {
            Stack<Character> brackets = new Stack<>();
            boolean isLineCorrupted = false;
            //part 1
            for (int i = 0; i < line.length(); i++) {
                if (openingBrackets.contains(line.charAt(i))) {
                    brackets.push(line.charAt(i));
                } else if (brackets.pop() != closingToOpening.get(line.charAt(i))) {
                    resultPart1 += closingBracketToPoint.get(line.charAt(i));
                    isLineCorrupted = true;
                    break;
                }
            }

            //part 2
            if (!isLineCorrupted) {
                long autoCompleteResult = 0;
                while (!brackets.empty()) {
                    autoCompleteResult *= 5;
                    autoCompleteResult += openingBracketToPoint.get(brackets.pop());
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
