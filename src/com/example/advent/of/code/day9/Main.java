package com.example.advent.of.code.day9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\Adam\\IdeaProjects\\advent_of_code\\resources\\day9\\input.txt");

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
        int width = inputs.get(0).length();
        int[][] higherNeighboursCount = new int[inputs.size()][width];

        int resultPart2;
        List<Basin> completedBasins = new ArrayList<>();
        List<Basin> previousRowBasins = new ArrayList<>();

        for (int i = 0; i < inputs.size(); i++) {
            List<Basin> currentRowBasins = new ArrayList<>();
            Basin partialBasin = new Basin(0, width);
            for (int j = 0; j < width; j++) {
                //part1 solution
                if (i == 0) {
                    higherNeighboursCount[i][j]++;
                }
                if (j == 0) {
                    higherNeighboursCount[i][j]++;
                }
                int currentPoint = Integer.parseInt(inputs.get(i).substring(j, j + 1));
                if (i == inputs.size() - 1) {
                    higherNeighboursCount[i][j]++;
                } else {
                    int nextPoint = Integer.parseInt(inputs.get(i + 1).substring(j, j + 1));
                    if (currentPoint < nextPoint) {
                        higherNeighboursCount[i][j]++;
                    } else if (currentPoint > nextPoint) {
                        higherNeighboursCount[i + 1][j]++;
                    }
                }
                if (j == width - 1) {
                    higherNeighboursCount[i][j]++;
                } else {
                    int nextPoint = Integer.parseInt(inputs.get(i).substring(j + 1, j + 2));
                    if (currentPoint < nextPoint) {
                        higherNeighboursCount[i][j]++;
                    } else if (currentPoint > nextPoint) {
                        higherNeighboursCount[i][j + 1]++;
                    }
                }
                if (higherNeighboursCount[i][j] == 4) {
                    resultPart1 += (currentPoint + 1);
                }
                //part2 solution
                if (currentPoint == 9) {
                    if (partialBasin.getBasinSize() != 0) {
                        currentRowBasins.add(partialBasin);
                        partialBasin = new Basin(0, width);
                    }
                } else {
                    partialBasin.addToBasinSize(1);
                    partialBasin.getCurrentRowBasinPoints()[j] = true;
                }
            }
            if (i == 0) {
                previousRowBasins = currentRowBasins;
            } else {
                List<Basin> updatedCurrentRowBasins = new ArrayList<>();
                for (Basin currentBasin : currentRowBasins) {
                    Basin updatedCurrentBasin = new Basin(currentBasin.getBasinSize(), width);
                    updatedCurrentBasin.setCurrentRowBasinPoints(currentBasin.getCurrentRowBasinPoints());
                    for (Basin prevBasin : previousRowBasins) {
                        for (int j = 0; j < width; j++) {
                            if (currentBasin.getCurrentRowBasinPoints()[j] == prevBasin.getCurrentRowBasinPoints()[j] && currentBasin.getCurrentRowBasinPoints()[j]) {
                                if (prevBasin.getInBasinRef() != null) {
                                    prevBasin.getInBasinRef().addToBasinSize(currentBasin.getBasinSize());
                                    for (int k = 0; k < width; k++) {
                                        if (currentBasin.getCurrentRowBasinPoints()[k]) {
                                            prevBasin.getInBasinRef().getCurrentRowBasinPoints()[k] = true;
                                        }
                                    }
                                    updatedCurrentBasin.resetBasinSize();
                                } else {
                                    prevBasin.setInBasinRef(updatedCurrentBasin);
                                    updatedCurrentBasin.addToBasinSize(prevBasin.getBasinSize());
                                }
                                break;
                            }
                        }
                    }
                    if (updatedCurrentBasin.getBasinSize() != 0) {
                        updatedCurrentRowBasins.add(updatedCurrentBasin);
                    }
                }
                for (Basin prevBasin : previousRowBasins) {
                    if (prevBasin.getInBasinRef() == null) {
                        completedBasins.add(prevBasin);
                    }
                }
                previousRowBasins = updatedCurrentRowBasins;
            }
        }
        for (Basin prevBasin : previousRowBasins) {
            if (prevBasin.getInBasinRef() == null) {
                completedBasins.add(prevBasin);
            }
        }

        List<Integer> basinSizes = completedBasins.stream().map(Basin::getBasinSize).sorted(Collections.reverseOrder()).collect(Collectors.toList());
        resultPart2 = basinSizes.get(0) * basinSizes.get(1) * basinSizes.get(2);

        System.out.println(resultPart1);
        System.out.println(resultPart2);
    }
}
