package com.springstring.covariant;

public class CovariantOne extends Covariant {
    @Override
    CovariantOne print(){
        return this;
    }

    void messages(){
        System.out.println("Hello Bangladesh covariant return type");
    }

    public static void main(String[] args) {
        new CovariantOne().messages();
    }
}
