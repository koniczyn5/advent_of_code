package com.example.advent.of.code.day5;

import java.util.ArrayList;
import java.util.List;

class Line {

    private Point start;
    private Point end;

    Line(int startX, int startY, int endX, int endY) {
        this.start = new Point(startX, startY);
        this.end = new Point(endX, endY);
    }

    Point getStart() {
        return start;
    }

    Point getEnd() {
        return end;
    }

    boolean isVerticalOrHorizontal() {
        return start.getX() == end.getX() || start.getY() == end.getY();
    }

    boolean isDiagonal() {
        int xDistance = Math.abs(end.getX() - start.getX());
        int yDistance = Math.abs(end.getY() - start.getY());
        return xDistance == yDistance;
    }

    List<Point> getPointsForVerticalOrHorizontalLine() {
        List<Point> results = new ArrayList<>();
        if (start.getX() == end.getX()) {
            int lower = Math.min(start.getY(), end.getY());
            int higher = Math.max(start.getY(), end.getY());
            for (int i = lower; i <= higher; i++) {
                results.add(new Point(start.getX(), i));
            }
        } else if (start.getY() == end.getY()) {
            int lower = Math.min(start.getX(), end.getX());
            int higher = Math.max(start.getX(), end.getX());
            for (int i = lower; i <= higher; i++) {
                results.add(new Point(i, start.getY()));
            }
        }
        return results;
    }

    List<Point> getPointsForDiagonalLine() {
        List<Point> results = new ArrayList<>();

        int distance = Math.abs(start.getX() - end.getX());
        for (int i = 0; i <= distance ; i++) {
            int pointX = start.getX() < end.getX() ? start.getX()+i : start.getX()-i;
            int pointY = start.getY() < end.getY() ? start.getY()+i : start.getY()-i;
            results.add(new Point(pointX, pointY));
        }

        return results;
    }

}
