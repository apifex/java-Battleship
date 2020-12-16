package battleship;

public class Field {
    protected String[][] gameField;
    int[][] shipsCoordinates = new int[5][5];
    Field(int size) {
        this.gameField = new String[size][size];
        InitializeGameField(size);
    }

    private void InitializeGameField(int size) {
        for (int i = 0; i < size; i++) {
            gameField[0][i] = Integer.toString(i);
            gameField[i][0] = Character.toString(i+64);
            gameField[0][0] = " ";
            for (int k = 1; k < size; k++){
                gameField[i][k] = "~";
            }
        }
    }

    protected boolean MakeShip(String shipType) {
        boolean status = false;
        String shipName;
        int shipSize;
        int shipCode;
        switch (shipType) {
            case "Aircraft Carrier":
                shipName = "Aircraft Carrier";
                shipSize = 5;
                shipCode = 0;
                break;
            case "Battleship":
                shipName = "Battleship";
                shipSize = 4;
                shipCode = 1;
                break;
            case "Submarine":
                shipName = "Submarine";
                shipSize = 3;
                shipCode = 2;
                break;
            case "Cruiser":
                shipName = "Cruiser";
                shipSize = 3;
                shipCode = 3;
                break;
            case "Destroyer":
                shipName = "Destroyer";
                shipSize = 2;
                shipCode = 4;
                break;
            default:
                shipName = "";
                shipSize = 0;
                shipCode = 0;
                break;
        }

        System.out.println("Enter the coordinates of the " + shipName + " (" + shipSize + " cells):");
        int[] coordinates = Main.ReadCoordinates();
        int inputShipSize = 0;
        int orientation = 0;
        if (coordinates[0] == coordinates[1] ) {
            inputShipSize = coordinates[3] - coordinates[2] + 1;
            orientation = 0; //horizontal
        } else if (coordinates[2] == coordinates[3]){
            inputShipSize = coordinates[1] - coordinates[0] + 1;
            orientation = 1; //vertical
        }

        if (isTooClose(coordinates[0], coordinates[2], orientation, shipSize)) {
            if (inputShipSize == shipSize) {
                if (orientation == 0) {
                    for (int i = 0; i < shipSize; i++) {
                        gameField[coordinates[0]][coordinates[2] + i] = "O";
                    }
                } else {
                    for (int i = 0; i < shipSize; i++) {
                        gameField[coordinates[0] + i][coordinates[2]] = "O";
                    }
                }
                shipsCoordinates[shipCode] = new int[]{coordinates[0],coordinates[1],coordinates[2],coordinates[3], shipSize};
                status = true;
            } else {System.out.println("Error! Wrong length of the " + shipName + "! Try again:");}
        }
        return status;
    }

    private boolean isTooClose(int startX, int startY, int orientation, int shipSize) {
        int minX = 0;
        int maxX = 0;
        int minY = 0;
        int maxY = 0;
        if (orientation == 0) {
            if (startX == 1) {
                minX = 1;
                maxX = 2;
            } else if(startX == 10) {
                minX = 9;
                maxX = 10;
            } else {
                minX = startX - 1;
                maxX = startX + 1;
            }

            if (startY == 1) {
                minY = 1;
                maxY = shipSize + 1;
            } else if (startY + shipSize - 1 == 10){
                minY = startY - 1;
                maxY = 10;
            } else {
                minY = startY - 1;
                maxY = startY + shipSize;
            }
        } else if (orientation == 1) {
            if (startX == 1) {
                minX = 1;
                maxX = shipSize + 1;
            } else if(startX + shipSize -1 == 10) {
                minX = startX - 1;
                maxX = 10;
            } else {
                minX = startX - 1;
                maxX = startX + shipSize;
            }

            if (startY == 1) {
                minY = 1;
                maxY = 2;
            } else if(startY == 10) {
                minY = 9;
                maxY = 10;
            } else {
                minY = startY - 1;
                maxY = startY + 1;
            }
        }

        int result = 0;
        for (int i = minX; i <= maxX; i++ ) {
            for (int k = minY; k <= maxY; k++) {
                if (!gameField[i][k].equals("~")) {
                    result++;
                }
            }
        }

        if (result > 0) System.out.println("Error! You placed it too close to another one. Try again:");

        return result <= 0;
    }

    protected void printField() {
        for(int i = 0; i < gameField.length; i++) {
            for (int k = 0; k < 11; k++) {
                System.out.print(gameField[i][k]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

}
