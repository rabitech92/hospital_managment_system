package com.springstring.covariant;

public class CovariantFour extends CovariantThree{

    CovariantFour print()
    {
        return this;
    }

    @Override
    void messages() {
        System.out.println("Hello Bangladesh Covariant Four");
    }
}
