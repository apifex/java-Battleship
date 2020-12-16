package battleship;

import java.util.Scanner;

public class Main {
    static boolean end = false;
    public static void main(String[] args) {

        Field field1 = new Field(11);
        Field field2 = new Field(11);
        Field fieldShots1 = new Field(11);
        Field fieldShots2 = new Field(11);

        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");

        player1.placeShips(field1);
        clearScreen();
        player2.placeShips(field2);
        clearScreen();

        do {
            int makeShoot;
            fieldShots1.printField();
            System.out.println("---------------------");
            field1.printField();
            System.out.println(player1.name + ", it's your turn:");
            makeShoot = player1.makeShoot(field2, fieldShots1);
            if (makeShoot == 3) break;
            clearScreen();
            fieldShots2.printField();
            System.out.println("---------------------");
            field2.printField();
            System.out.println(player2.name + ", it's your turn:");
            makeShoot = player2.makeShoot(field1, fieldShots2);
            clearScreen();
            if (makeShoot == 3) end = true;
        } while (!end);
    }

        static void clearScreen(){
            Scanner sc = new Scanner(System.in);
            sc.nextLine();
            System.out.println("\033[H\033[2J");
        }
    static int[] ReadCoordinates() {
        int[] coordinates;
        boolean status = false;
        do {
            Scanner sc = new Scanner(System.in);
            String inputA = sc.next();
            String inputB = sc.next();
            int startX = inputA.charAt(0) - 64;
            int endX = inputB.charAt(0) - 64;
            int startY = Integer.parseInt(inputA.substring(1));
            int endY = Integer.parseInt(inputB.substring(1));
            int holder;
            if (startX > endX) {
                holder = startX;
                startX = endX;
                endX = holder;
            }
            if (startY > endY) {
                holder = startY;
                startY = endY;
                endY = holder;
            }

            boolean checkIsInRange = false;
            if (startX >= 1 && startX <= 10) {
                if (endX <= 10) {
                    if (startY >= 1 && startY <= 10) {
                        if (endY <= 10) {
                            checkIsInRange = true;
                        }
                    }
                }
            }
            boolean checkIsInLine;
            checkIsInLine = (startX == endX) || (startY == endY);
            if (checkIsInRange && checkIsInLine) {
                coordinates = new int[]{startX, endX, startY, endY};
                status = true;
            } else {
                System.out.println("Error! Wrong ship location! Try again:");
                coordinates = new int[]{0};
            }
        } while (!status);

        return coordinates;
    }

}

