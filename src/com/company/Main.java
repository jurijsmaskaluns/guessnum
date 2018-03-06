package com.company;


import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();


    public static void main(String[] args) {
        ArrayList<GameResult> leaderbord = new ArrayList<>(); // hranilishe vsego
        try {
            String answer;
            do {
                System.out.print("Enter your name: ");
                String name = scanner.next();
                GameResult r = doGame(name);
                if (r != null) {
                    leaderbord.add(r);
//                    System.out.print(r.userName +" ");
//                    System.out.println(r.attempts);
                }
                System.out.println("Once more?");
                answer = askAnswer();
            } while (answer.equalsIgnoreCase("yes"));
//            System.out.println("good bye 1");
        } catch (NoSuchElementException e1) {
            System.out.println("good bye 2");
        }
        for (GameResult r : leaderbord)
        {
            System.out.println(r.userName + "\t" + r.attempts);
        }
        System.out.println("good bye");
    }

    private static GameResult doGame(String userName) {
        System.out.println("I think of number from 1 to 100. Try to guess it " + userName);
        GameResult result = new GameResult(); // sozdajotsja peremennaja drugogo klassa
        result.userName = userName;
        int myNum = random.nextInt(100) + 1;
        System.out.println(myNum);
        for (int i = 1; i <= 10; i++) {
            System.out.print("попытка номер: " + i);
            System.out.println(", Введи число: ");
            int userNum = askNumber();
            if (userNum > myNum) {
                System.out.println("Твоё число больше");
            } else if (userNum == myNum) {
                System.out.println("Бинго !");
                result.attempts = i;
                return result;
            } else {
                System.out.println("Твоё число меньше");
            }
        }
        System.out.println("проиграл ! число было " + myNum);
        return null;
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