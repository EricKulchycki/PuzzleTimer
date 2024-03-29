package sep.myapplication.business;


import sep.myapplication.objects.Moves;

public class ScrambleGenerator {

    //Generates a random number between 0 and 17 (18 possible numbers)
    public static int genRanNum() {

        double ranNum = Math.random() * 17 + 1;
        return (int) ranNum;

    }


    //Generates 25 random moves
    public String genScramble(int numMoves) {

        Moves[] scrambleA = new Moves[numMoves];
        String scramble = "";

        for(int i = 0; i < numMoves; i++) {

            int num = genRanNum();
            Moves move = Moves.valueOf(num);
            scrambleA[i] = move;
        }

        scramble = checkForRedundancy(scrambleA);

        return scramble;
    }


    //Goes through each move in the scramble and check for two moves being of the same layer.
    public static String checkForRedundancy(Moves[] scramble) {

        String retScramble = "";


        //loop through last 24 elements in the scramble
        //check last two elements
        for(int i = 1; i < scramble.length; i++) {
            //If it's the second spot then we can't check last two
            if(i != 1) {
                String bef = "";
                String aft = "";
                bef += scramble[i-1];
                aft += scramble[i];

                 if(bef.substring(0,1).equals(aft.substring(0,1))) {
                     Moves newMove = genNewMove();
                     scramble[i] = newMove;
                     checkForRedundancy(scramble);
                 }
            }
        }

        for(int x = 0; x < scramble.length; x++) {
            retScramble += scramble[x];
        }

        retScramble = translateScramble(retScramble);

        return retScramble;
    }

    public static Moves genNewMove() {

        int num = genRanNum();
        Moves move = Moves.valueOf(num);

        return move;
    }

    public static String translateScramble(String oldScramble) {

        String retScramble = null;

        if(oldScramble != null) {

            retScramble = "";

            for (int i = 0; i < oldScramble.length(); i++) {
                if (oldScramble.charAt(i) == 'p') {
                    retScramble += "' ";
                } else if (oldScramble.charAt(i) == '2') {
                    retScramble += oldScramble.charAt(i);
                    retScramble += " ";
                } else {
                    if ((i < oldScramble.length() - 1) && ((oldScramble.charAt(i + 1) == '2') || (oldScramble.charAt(i + 1) == 'p'))) {
                        retScramble += oldScramble.charAt(i);
                    } else {
                        retScramble += oldScramble.charAt(i) + " ";
                    }

                }
            }
        }

        return retScramble;
    }




}
