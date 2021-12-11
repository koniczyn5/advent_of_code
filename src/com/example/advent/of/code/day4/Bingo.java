package com.example.advent.of.code.day4;

class Bingo {

    private BingoCell[][] board = new BingoCell[5][5];

    boolean isBingo() {
        int[] markedRows = new int[5];
        int[] markedColumns = new int[5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if(board[i][j].marked) {
                    markedRows[i]++;
                    markedColumns[j]++;
                    if(markedRows[i] == 5 || markedColumns[j] == 5) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    boolean checkNumber(int number) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if(board[i][j].value == number) {
                    board[i][j].marked = true;
                    return checkGivenRowAndColumn(i,j);
                }
            }
        }
        return false;
    }

    int sumOfUnmarked() {
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if(!board[i][j].marked) {
                    sum+=board[i][j].value;
                }
            }
        }
        return sum;
    }

    Bingo insertRow(String string, int rowNumber) {
        String[] numbers = string.trim().split("\\s+");
        for (int i = 0; i < 5; i++) {
            this.board[rowNumber][i] = new BingoCell(Integer.parseInt(numbers[i]));
        }
        return this;
    }

    private boolean checkGivenRowAndColumn(int row, int column) {
        int markedInRow = 0;
        int markedInColumn = 0;
        for (int i = 0; i < 5; i++) {
            if(board[row][i].marked) {
                markedInRow++;
            }
            if (board[i][column].marked) {
                markedInColumn++;
            }
        }
        return markedInRow == 5 || markedInColumn == 5;
    }

    class BingoCell {
        int value;
        boolean marked;

        BingoCell(int value) {
            this.value = value;
        }
    }
}
