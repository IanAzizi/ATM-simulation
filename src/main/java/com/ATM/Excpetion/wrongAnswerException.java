package com.ATM.Excpetion;

public class wrongAnswerException extends Exception {
        wrongAnswerException(String message) {
            System.out.println("wrongAnswerException: " + message);
        }

}
