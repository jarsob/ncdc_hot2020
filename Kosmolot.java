public class Kosmolot {
    public static void main(String[] args) {

        String rocketSizeStr;
        int lengthOfStars, rocketSize;
        String mustHaveShieldStr;
        boolean mustHaveShield;

        try {

            //check if rocket size is valid
            rocketSizeStr = args[0].trim();
            rocketSize = Integer.parseInt(rocketSizeStr);
            if (!(rocketSize >= 1 && rocketSize <= 75)) {
                throw new Exception();
            }

            //check if shield option is valid
            mustHaveShieldStr = args[1].trim();
            if (!(mustHaveShieldStr.equals("N") || mustHaveShieldStr.equals("Y"))) {
                throw new Exception();
            }
            mustHaveShield = mustHaveShieldStr.equals("Y");

            //throw exception if there is redundant third parameter
            if (args.length > 2) {
                throw new Exception();
            }

            StringBuilder rocketElement = new StringBuilder();
            StringBuilder rocketLine = new StringBuilder();

            String basicElement = "*";
            String[] shieldElement = {"\\", "/"};
            String specialShieldElement = ">";

            // main loop, 2 * rocketSize - 1 = number of lines to print
            for (int i = 1; i < 2 * rocketSize; i++) {

                // length of stars block in each line
                lengthOfStars = rocketSize - Math.abs(rocketSize - i);

                //filling up rocket element with stars
                for (int j = 0; j < lengthOfStars; j++) {
                    rocketElement.append(basicElement);
                }

                // for shield change last star to slash/backslash, not in the longest line
                if (mustHaveShield && i != rocketSize) {
                    rocketElement.replace(rocketElement.length() - 1, rocketElement.length(), i < rocketSize ? shieldElement[0] : shieldElement[1]);
                }

                //completing rocket element with blanks
                for (int j = 0; j < rocketSize - lengthOfStars; j++) {
                    rocketElement.append(" ");
                }

                //joining rocket elements to complete one line
                for (int j = 0; j < rocketSize; j++) {
                    rocketLine.append(rocketElement);
                }


                if (mustHaveShield) {
                    //for shield replace first sign
                    rocketLine.replace(0, 1, specialShieldElement);

                    //for shield replace last sign in the longest line
                    if (i == rocketSize) {
                        rocketLine.replace(rocketLine.length() - 1, rocketLine.length(), specialShieldElement);
                    }
                }

                //print one whole line
                System.out.print(rocketLine);

                //clear rocketElement and rocketLine
                rocketElement.setLength(0);
                rocketLine.setLength(0);

                //print blank line
                System.out.println();

            }

        } catch (Exception e) {
            //at case any exception print "klops"
            System.out.println("klops");
        }

    }
}
