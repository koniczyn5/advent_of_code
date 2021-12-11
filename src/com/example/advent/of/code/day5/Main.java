package com.example.advent.of.code.day5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\Adam\\IdeaProjects\\advent_of_code\\resources\\day5\\input.txt");

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

        List<Line> lines = new ArrayList<>();

        for (String input : inputs) {
            String[] line = input.split(",| -> ");
            int[] lineIntsArray = Arrays.stream(line).mapToInt(Integer::parseInt).toArray();
            lines.add(new Line(lineIntsArray[0], lineIntsArray[1], lineIntsArray[2], lineIntsArray[3]));
        }

        Map<Point, Integer> mapOfVents = new HashMap<>();

        lines.stream()
                .filter(Line::isVerticalOrHorizontal)
                .flatMap(line -> line.getPointsForVerticalOrHorizontalLine().stream())
                .forEach(point -> mapOfVents.merge(point, 1, (integer, integer2) -> integer+integer2));

        int resultPart1 = (int) mapOfVents.values().stream().filter(integer -> integer >= 2).count();
        System.out.println(resultPart1);

        lines.stream()
                .filter(Line::isDiagonal)
                .filter(line -> !line.getStart().equals(line.getEnd()))
                .flatMap(line -> line.getPointsForDiagonalLine().stream())
                .forEach(point -> mapOfVents.merge(point, 1, (integer, integer2) -> integer+integer2));

        int resultPart2 = (int) mapOfVents.values().stream().filter(integer -> integer >= 2).count();
        System.out.println(resultPart2);
    }
}
