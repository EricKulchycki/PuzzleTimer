package sep.myapplication.objects;

//Assigns numeric values from 0-17 to each move that you can do on a Rubik's cube
public enum Moves {
    L(0),Lp(1),R(2),Rp(3),F(4),Fp(5),B(6),Bp(7),U(8),Up(9),D(10),Dp(11),L2(12),R2(13),F2(14),D2(15),U2(16),B2(17);

    int numVal;

    //Constructor
    Moves(int num) {
        this.numVal = num;
    }

    //returns the numeric value for a move
    public int getNumVal(){
        return numVal;
    }

    public static Moves valueOf(int i) {
        for (Moves m : Moves.values()) {
            if (m.numVal == i) return m;
        }

        return null;
    }
}
