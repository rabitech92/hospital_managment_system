package com.springstring.covariant;

public class CovariantFinal extends CovariantFour{
    public static void main(String[] args) {
        CovariantTwo two = new CovariantTwo();
        two.print().messages();

        CovariantThree three  = new CovariantThree();
        three.print().messages();

        CovariantFour four = new CovariantFour();
        four.print().messages();
    }
}
