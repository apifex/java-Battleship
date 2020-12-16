package battleship;

import java.util.Scanner;

public class Player {
    Scanner sc = new Scanner(System.in);
    String name;
    int shipsSunken = 0;

    Player(String name) {
        this.name = name;
    }

    protected void placeShips(Field field) {
        String[] ships = {"Aircraft Carrier", "Battleship", "Submarine", "Cruiser", "Destroyer"};
        System.out.println(name + ", place your ships on the game field");
        field.printField();
        for (String ship: ships) {
            boolean makeStatus;
            do{
                makeStatus = field.MakeShip(ship);
            } while (!makeStatus);
            field.printField();
        }
        System.out.println("Press Enter and pass the move to another player");
    }

    protected int makeShoot(Field field, Field fieldShots) {
        int resultOfShoot = 0;
        System.out.println();
        String shot = sc.next();
        int shotX = shot.charAt(0) - 64;
        int shotY = Integer.parseInt(shot.substring(1));
        System.out.println();

        if (shotX >= 1 && shotX <= 10) {
            if (shotY >= 1 && shotY <= 10) {
                if (field.gameField[shotX][shotY].equals("O") || field.gameField[shotX][shotY].equals("X")) {
                    field.gameField[shotX][shotY] = "X";
                    fieldShots.gameField[shotX][shotY] = "X";
                    String message = "You hit a ship!";
                    boolean sunken = false;
                    for (int i = 0; i < 5; i++) {
                        if (shotX >= field.shipsCoordinates[i][0] && shotX <= field.shipsCoordinates[i][1]) {
                            if (shotY >= field.shipsCoordinates[i][2] && shotY <= field.shipsCoordinates[i][3]) {
                                field.shipsCoordinates[i][4]--;
                                resultOfShoot = 1;
                                if (field.shipsCoordinates[i][4] == 0) sunken = true;
                            }
                        }
                    }
                    if (sunken) {
                        shipsSunken++;
                        if (shipsSunken == 5) {
                            message = "You sank the last ship. You won. Congratulations!";
                            resultOfShoot = 3;
                        } else {
                            message = "You sank a ship!";
                            resultOfShoot = 2;
                        }
                    }
                    System.out.println(message);
                } else {
                    field.gameField[shotX][shotY] = "M";
                    fieldShots.gameField[shotX][shotY] = "M";
                    System.out.println("Yous missed. Try again");
                }
            } else {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
                resultOfShoot = 4;
            }
        } else {
            System.out.println("Error! You entered the wrong coordinates! Try again:");
            resultOfShoot = 4;
        }
        System.out.println("Press Enter and pass the move to another player");
        return resultOfShoot;
    }
}
