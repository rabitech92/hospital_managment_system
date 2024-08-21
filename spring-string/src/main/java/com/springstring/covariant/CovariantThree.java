package com.springstring.covariant;

public class CovariantThree extends CovariantTwo{

    CovariantThree print(){
        return this;
    }
    void messages(){
        System.out.println("Hello Bangladesh Covariant Three");
    }
}
