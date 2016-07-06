package battleship;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Guanqing Hao & Minquan Wang
 */

public class BattleshipGame {
    private Ocean ocean;
    private Scanner sc;
    int[] coordinates;

    /**
     * constructor for BattleShipGame
     */
    public BattleshipGame() {
        // define a new ocean and a instance to store the previous shot coordinates
        ocean = new Ocean();
        coordinates = new int[2];
    }

    /**
     * prints the game menu and info
     */
    public void print(int select) {
        String info;
        switch (select) {
            case 1:
                info = "Welcome to the World of Battleship created by Minquan & Guanqing!";
                break;
            case 2:
                info = "Enter coordinates to fire, e.g. '0 9': ";
                break;
            case 3:
                info = "fired at coordinates: (" + coordinates[0] + ", " + coordinates[1] + ")\n" + "Shots fired: "
                      + ocean.getShotsFired() + ", Shots hit: " + ocean.getHitCount() + ", Ships sunk: " + ocean.getShipsSunk();
                break;
            case 4:
                info = "Congratulations! You win!";
                break;
            case 99:
                info = "Invalid input. Please re-enter:";
                break;
            case 100:
                info = "--------------------------------------------";
                break;
            case 101:
                info = "\n============================================";
                break;
            default:
                info = "Error selection";
                break;
        }
        System.out.println(info);
    }

    /**
     * check if the input is valid
     */
    public boolean checkValidInput(String input) {
        ArrayList<String> numList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            numList.add("" + i);
        }
        String[] coordinates = input.split(" ");
        //returns false if there are not 2 strings
        if (coordinates.length != 2) {
            return false;
        }
        //returns false if any of the strings is not a single digit number
        for (String str : coordinates) {
            if (!numList.contains(str)) {
                return false;
            }
        }
        return true;
    }

    /**
     * get the coordinates to shoot at from the String input
     *
     * @return int[] coordinates
     */
    public int[] getCoordinates(String input) {
        int[] coordinates = new int[2];
        String[] strList = input.split(" ");
        int row = Integer.parseInt(strList[0]);
        int column = Integer.parseInt(strList[1]);
        coordinates[0] = row;
        coordinates[1] = column;
        return coordinates;
    }

    /**
     * play the battleship game
     */
    public void play() {
        print(101);
        print(1);
        ocean.placeAllShipsRandomly();
        boolean isGameOver = ocean.isGameOver();
        sc = new Scanner(System.in);

        //print the ocean and start the game
        ocean.print();
        while (!isGameOver) {
            print(2);
            String input = sc.nextLine();

            //check if input is valid
            while (!checkValidInput(input)) {
                print(99);
                input = sc.nextLine();
            }

            //get coordinates and fire
            coordinates = getCoordinates(input);
            int row = coordinates[0];
            int column = coordinates[1];
            ocean.shootAt(row, column);
            //availableSpot[row][column] = false;
            isGameOver = ocean.isGameOver();
            ocean.print();
            print(3);
            print(100);
        }
        //print info saying you win
        print(4);
    }

    /**
     * main method for the battleship game
     */
    public static void main(String[] args) {

        BattleshipGame battleshipGame = new BattleshipGame();
        battleshipGame.play();
        System.out.println("Start a new game? y/n");
        Scanner sc = new Scanner(System.in);
        String isPlay = sc.next();
        while (!isPlay.equals("y") && !isPlay.equals("n")) {
            System.out.println("please input 'y' or 'n'!");
            isPlay = sc.next();
        }
        while (isPlay.equals("y")) {
            battleshipGame = new BattleshipGame();
            battleshipGame.play();
            System.out.println("Start a new game? y/n");
            isPlay = sc.next();
            while (!isPlay.equals("y") && !isPlay.equals("n")) {
                System.out.println("please input 'y' or 'n'!");
                isPlay = sc.next();
            }

        }
        System.out.println("Game Over! Thanks for playing!");
        sc.close();
    }

}
