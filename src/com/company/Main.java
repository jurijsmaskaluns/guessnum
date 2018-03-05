package com.company;


import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            String answer;
            do {
                Random random = new Random();

                System.out.println("I think of number from 1 to 100. Try to guess it.");
                int myNum = random.nextInt(100) + 1;
                System.out.println(myNum);
                int i;
                for (i = 1; i <= 10; i++) {
                    System.out.print("попытка номер: " + i);
                    System.out.println(", Введи число: ");
                    int userNum = askNumber();
                    if (userNum > myNum) {
                        System.out.println("Твоё число больше");
                    } else if (userNum == myNum) {
                        System.out.println("Бинго !");
                        break;
                    } else {
                        System.out.println("Твоё число меньше");
                    }
                }
                if (i == 11) {
                    System.out.println("You lost");
                }
                System.out.println("Once more?");
                answer = askAnswer();
            } while (answer.equalsIgnoreCase("yes"));

            System.out.println("good bye 1");
        } catch (NoSuchElementException e1) {
            System.out.println("good bye 2");
        }
    }

    static String askAnswer() {

        for (; ; ) {
            String str = scanner.next();
            if ("yes".equalsIgnoreCase(str) || "no".equalsIgnoreCase(str)) {
                return str;
            }
            System.out.print("Write yes or no ");
        }
    }

    static int askNumber() {
        for (; ; ) { //beskone4nij cikl
            try {
                int num = scanner.nextInt();
                if (num <= 100 && num >= 1) {
                    return num;
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong type");
                scanner.next(); //
            }
            System.out.print("try again: ");
        }
    }
    //    static int askNumber() {
//        int num;
//        do {
//            num = scanner.nextInt();
//            if (num < 1 || num > 100) {
//                System.out.print("try again: ");
//            }
//        } while (num < 1 || num > 100);
//        return num;
//    }
}