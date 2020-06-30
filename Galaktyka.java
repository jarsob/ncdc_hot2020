public class Galaktyka {

    public static void main(String[] args) {

        int telescopeSize, matrixWidth, matrixHeight, lightYearsCounter;
        int coordinateX, coordinateY;
        String orientation, telescopeSizeStr;
        int loopSize, numberOfStarsInRow;
        String paramFromArgs;

        //Fryderyk: Please, don't use the absurdly large telescope size

        try {

            //check if telescope size is valid
            paramFromArgs = args[0].trim();
            telescopeSizeStr=paramFromArgs.substring(0,paramFromArgs.length()-1);
            telescopeSize = Integer.parseInt(telescopeSizeStr);
            if (!(telescopeSize >= 1 && telescopeSize <= 10000)) {
                throw new Exception();
            }

            //check if shield option is valid
            orientation = paramFromArgs.substring(paramFromArgs.length()-1);
            if (!(orientation.equals("N") || orientation.equals("S")|| orientation.equals("W")|| orientation.equals("E"))) {
                throw new Exception();
            }

            //throw exception if there is redundant second element in args
            if (args.length > 1) {
                throw new Exception();
            }


            // matrix size depends on telescope size
            matrixWidth = telescopeSize + 2;
            matrixHeight = telescopeSize + 3;

            String[][] matrixOfStars = new String[matrixHeight][matrixWidth];

            // a matrix is always created in the "W" orientation
            // matrix is only printed according to orientation

            // filling up whole matrix with blanks
            for (int i = 0; i < matrixHeight; i++) {
                for (int j = 0; j < matrixWidth; j++) {
                    matrixOfStars[i][j] = " ";
                }
            }

            // filling up first row with stars
            for (int i = 0; i < matrixWidth; i++) {
                matrixOfStars[0][i] = "*";
            }
            // there is always one star in second row
            matrixOfStars[1][matrixWidth - 1] = "*";

//            System.out.println("telescope size: " + telescopeSize + ", orientation: " + orientation);

            coordinateY = 0;
            coordinateX = matrixWidth - 1;
            loopSize = telescopeSize + 2;
            numberOfStarsInRow = loopSize;

            // main loop, length = telescopeSize + 2
            for (int i = 0; i < loopSize; i++) {
                if (i % 4 == 0) {
                    // drawing down
                    for (int j = 0; j < numberOfStarsInRow; j++) {
                        coordinateY++;
                        matrixOfStars[coordinateY][coordinateX] = "*";
                    }
                    numberOfStarsInRow--;


                } else if (i % 4 == 1) {
                    // drawing left
                    for (int j = 0; j < numberOfStarsInRow; j++) {
                        coordinateX--;
                        matrixOfStars[coordinateY][coordinateX] = "*";
                    }
                    numberOfStarsInRow--;


                } else if (i % 4 == 2) {
                    // drawing up
                    for (int j = 0; j < numberOfStarsInRow; j++) {
                        coordinateY--;
                        matrixOfStars[coordinateY][coordinateX] = "*";
                    }
                    numberOfStarsInRow--;


                } else if (i % 4 == 3) {
                    // drawing right
                    for (int j = 0; j < numberOfStarsInRow; j++) {
                        coordinateX++;
                        matrixOfStars[coordinateY][coordinateX] = "*";
                    }
                    numberOfStarsInRow--;
                }

            }

            // printing in expected orientation
            switch (orientation) {
                case "W":
                    printWestOrientation(matrixWidth, matrixHeight, matrixOfStars);
                    break;
                case "E":
                    printEastOrientation(matrixWidth, matrixHeight, matrixOfStars);
                    break;
                case "N":
                    printNorthOrientation(matrixWidth, matrixHeight, matrixOfStars);
                    break;
                case "S":
                    printSouthOrientation(matrixWidth, matrixHeight, matrixOfStars);
                    break;
            }

            // calculating light years
            lightYearsCounter = 0;
            for (int i = 0; i < matrixHeight; i++) {
                for (int j = 0; j < matrixWidth; j++) {
                    if (matrixOfStars[i][j].equals(" ")) {
                        lightYearsCounter++;
                    }
                }
            }
//            System.out.println("light years: " + lightYearsCounter);
            System.out.println(lightYearsCounter);


        } catch (Exception e) {
            //at case any exception print "klops"
            System.out.println("klops");
        }

    }

    private static void printWestOrientation(int matrixWidth, int matrixHeight, String[][] matrixOfStars) {
        for (int i = 0; i < matrixHeight; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                System.out.print(matrixOfStars[i][j]);
            }
            System.out.println();
        }
    }

    private static void printEastOrientation(int matrixWidth, int matrixHeight, String[][] matrixOfStars) {
        for (int i = matrixHeight - 1; i >= 0; i--) {
            for (int j = matrixWidth - 1; j >= 0; j--) {
                System.out.print(matrixOfStars[i][j]);
            }
            System.out.println();
        }
    }

    private static void printNorthOrientation(int matrixWidth, int matrixHeight, String[][] matrixOfStars) {
        for (int j = 0; j < matrixWidth; j++) {
            for (int i = matrixHeight - 1; i >= 0; i--) {
                System.out.print(matrixOfStars[i][j]);
            }
            System.out.println();
        }
    }

    private static void printSouthOrientation(int matrixWidth, int matrixHeight, String[][] matrixOfStars) {
        for (int j = matrixWidth - 1; j >= 0; j--) {
            for (int i = 0; i < matrixHeight; i++) {
                System.out.print(matrixOfStars[i][j]);
            }
            System.out.println();
        }
    }
}