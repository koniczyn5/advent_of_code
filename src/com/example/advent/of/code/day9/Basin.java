package com.example.advent.of.code.day9;

class Basin {

    private int basinSize;
    private boolean[] currentRowBasinPoints;
    private Basin inBasinRef;

    Basin(int basinSize, int rowWidth) {
        this.basinSize = basinSize;
        currentRowBasinPoints = new boolean[rowWidth];
    }

    int getBasinSize() {
        return basinSize;
    }

    boolean[] getCurrentRowBasinPoints() {
        return currentRowBasinPoints;
    }

    void setCurrentRowBasinPoints(boolean[] currentRowBasinPoints) {
        this.currentRowBasinPoints = currentRowBasinPoints;
    }

    void addToBasinSize(int size) {
        basinSize += size;
    }

    void resetBasinSize() {
        basinSize = 0;
    }

    Basin getInBasinRef() {
        return inBasinRef;
    }

    void setInBasinRef(Basin inBasinRef) {
        this.inBasinRef = inBasinRef;
    }
}
