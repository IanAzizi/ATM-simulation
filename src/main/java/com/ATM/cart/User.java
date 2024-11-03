package com.ATM.cart;

public class User
{
    private int id;
    private String name;
    private int password;
    private int amount;


    public int getAmount() {
        int amountnumber = amount;
        amountnumber = 1000;
        return amountnumber;
    }

    public void setAmount(int amount) {

        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String setName(String name) {
        name = "ali";
            this.name = name;
        return name;
    }

    public int getPassword() {
        return password;
    }

    public int setPassword(int password) {
       password = 1234;
        this.password = password;
        return password;
    }
}
