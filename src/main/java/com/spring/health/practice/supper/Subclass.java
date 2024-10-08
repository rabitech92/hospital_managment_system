package com.spring.health.practice.supper;

public class Subclass extends SuperKeyword {

    public String id ="201";
    public String name = "Sina islam";
    public String phone = "01303107821";
    public String email = "abc@gmail.com";

    void print(){
        System.out.println("ID :" + super.id+" Name :"+super.name+"Phone :"+super.phone+" Email : "+ super.email);
        System.out.println("ID :"+this.id+" Name "+this.name+" Phone "+this.phone+" Email :"+this.email);
    }



}
