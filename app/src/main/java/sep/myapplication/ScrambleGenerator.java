package sep.myapplication;

import java.util.*;



class ScrambleGenerator {

    //Generates a random number between 0 and 17 (18 possible numbers)
    public static int genRanNum() {

        double ranNum = Math.random() * 17 + 1;
        int ranNum2 = (int) ranNum;

        return ranNum2;
    }


    //Generates 25 random moves
    public static String genScramble() {
        String scramble = "";

        for(int i = 0; i < 25; i++) {

            int num = genRanNum();
            Moves move = Moves.valueOf(num);

            scramble += move;
        }

        return scramble;
    }


    public static boolean checkForRedundancy(String scramble, int i) {

        boolean redundant = false;




        return redundant;
    }




}
