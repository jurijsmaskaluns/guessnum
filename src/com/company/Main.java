package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String answer;
        do {
            Random random = new Random();
            Scanner scanner = new Scanner(System.in);
            System.out.println("I think of number from 1 to 100. Try to guess it.");
            int myNum = random.nextInt(100) + 1;
            System.out.println(myNum);
            int i;
            for (i = 1; i < 11; i++) {
                System.out.print("попытка номер: " + i);
                System.out.println(", Введи число: ");
                int userNum = scanner.nextInt();
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
            answer = scanner.next();
        } while (answer.equals("yes"));

        System.out.println("good bye");

    }
}
