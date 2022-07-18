package com.company;
import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Player 1, enter your name: "); //Ask player 1 for name
        String player1 = in.nextLine();

        String computer = "computer";


        char[][] ttt_board = new char[3][3]; //Board design, probably not needed because of JavaFX

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ttt_board[i][j] = '-';
            }
        }

        //Major part of code that sets up the game.
        boolean first_choice = true;
        boolean gameEnded = false;

        while (!gameEnded) {

            DrawBoard(ttt_board);

            //Assigns the symbol
            char symbol = ' ';
            if (first_choice) {
                symbol = 'x';
            } else {
                symbol = 'O';
            }

            //Keep track of the turn, tell which player plays
            if (first_choice) {
                System.out.println(player1);
            } else {
                System.out.println(computer);
            }

            // Get coordinates, probably not needed since we're using JavaFX
            int row = 0;
            int column = 0;

            while (true) {
                if (first_choice) {
                    System.out.print("Enter your row coordinate: 0, 1, or 2: ");
                    row = in.nextInt();
                    System.out.print("Enter your column coordinate: 0, 1, or 2: ");
                    column = in.nextInt();
                }
                else {
                    System.out.println("Computer's row coordinate:");
                    row = ComputerTurn();
                    System.out.println("Computer's column coordinate: ");
                    column = ComputerTurn();


                }

                // This needs to be converted to JavaFX
                if (row < 0 || column < 0 || row > 2 || column > 2) {
                    System.out.println("Out of bounds");
                } else if (ttt_board[row][column] != '-') {
                    System.out.println("You late");
                } else {
                    break;
                }
            }

            // Assign each symbol to players
            ttt_board[row][column] = symbol;


            // Check who has won
            if (Won(ttt_board) == 'x') {
                System.out.println(player1 + " has won");
                gameEnded = true;
            } else if (Won(ttt_board) == 'O') {
                System.out.println(computer + " has won");
                gameEnded = true;
            } else {
                if (Tie(ttt_board)) {
                    System.out.println("Tie Game");
                    gameEnded = true;
                } else {
                    first_choice = !first_choice;
                }
            }

        }
        DrawBoard(ttt_board);
    }

    public static int ComputerTurn() {
        Random rnd = new Random();
        int n1 = rnd.nextInt(3);
        return n1;
    }



    public static void DrawBoard(char[][] ttt_board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(ttt_board[i][j]);

            }
            System.out.println();
        }
    }

    //Function to check who has won
    public static char Won(char[][] ttt_board) {
        for (int i = 0; i < 3; i++) {
            if (ttt_board[i][0] == ttt_board[i][1] && ttt_board[i][1] ==
                    ttt_board[i][2] && ttt_board[i][0] != '-') {
                return ttt_board[i][0];
            }
        }

        //Check Columns
        for (int j = 0; j < 3; j++) {
            if (ttt_board[0][j] == ttt_board[1][j] && ttt_board[1][j] == ttt_board[2][j] && ttt_board[0][j] != '-') {
                return ttt_board[0][j];
            }
        }

        //Check Diagonals

        if (ttt_board[0][0] == ttt_board[1][1] && ttt_board[1][1] ==
                ttt_board[2][2] && ttt_board[0][0] != '-') {
            return ttt_board[0][0];
        }
        if (ttt_board[2][0] == ttt_board[1][1] && ttt_board[1][1] ==
                ttt_board[0][2] && ttt_board[2][0] != '-') {
            return ttt_board[2][0];
        }
        return '-';
    }

    //Check if there is a tie
    public static boolean Tie(char[][] ttt_board) {
        for (int i = 0; i<3; i++){
            for (int j = 0; j<3; j++) {
                if (ttt_board[i][j] == '-'){
                    return false;
                }
            }
        }
        return true;
    }
}
