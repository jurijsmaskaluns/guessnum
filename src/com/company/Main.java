package com.company;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    static File leaderBoardFile = new File("leader-board.txt");

    public static void main(String[] args) {
        long t = System.currentTimeMillis();
        System.out.println("time: " + t / 1000);
        ArrayList<GameResult> leaderbord = new ArrayList<>(); // hranilishe vsego
        loadLeaderBoard(leaderbord);
        try {
            String answer;
            do {
                System.out.print("Enter your name: ");
                String name = scanner.next();
                GameResult r = doGame(name);
                if (r != null) {
                    leaderbord.add(r);
                }
                System.out.println("Once more?");
                answer = askAnswer();
            } while (answer.equalsIgnoreCase("yes"));
        } catch (NoSuchElementException e1) {
//            System.out.println("good bye 2");
        }
//        leaderbord.sort(Comparator.comparingDouble(g -> g.time)); // sortirovka spiska po vremeni
        leaderbord.sort(Comparator.<GameResult>comparingInt(g -> g.attempts).<GameResult>thenComparingDouble(g -> g.time));
        PrintLeaderBoard(leaderbord);
        saveLeaderBoard(leaderbord);
        System.out.println("good bye");
    }

    private static void loadLeaderBoard(ArrayList<GameResult> leaderbord) {
        if (!leaderBoardFile.exists()) {
            return;
        }
        try (Scanner in = new Scanner(leaderBoardFile)) {
            while (in.hasNext()) {
                GameResult r = new GameResult();
                r.userName = in.next();
                r.attempts = in.nextInt();
                r.time = Double.parseDouble(in.next());
                leaderbord.add(r);
            }
        } catch (IOException e) {
            System.out.println("Something wrong while reading file");
        }
    }

    private static void saveLeaderBoard(ArrayList<GameResult> leaderbord) {
        try (PrintWriter out = new PrintWriter(leaderBoardFile)) { //fail otkroetsja i avtomatom v konce zakroetsja
            for (GameResult r : leaderbord) {
                out.printf("%-20s %d %f\n", r.userName, r.attempts, r.time);
            }
        } catch (IOException e) {
            System.out.println("something wrong while writing file");
        }
    }

    private static void PrintLeaderBoard(ArrayList<GameResult> leaderbord) {

        for (int o = 0; o < Math.min(leaderbord.size(), 5); o++) {
            GameResult r = leaderbord.get(o);
            System.out.println(r.userName + "\t" + r.attempts + "\t Time(sec.): " + r.time);
        }

//        int o = 0;
//        for (GameResult r : leaderbord) {
//            System.out.println(r.userName + "\t" + r.attempts + "\t Time(sec.): " + r.time);
//            o=o+1;
//            if(o==5){
//                break;
//            }
//        }
    }

    private static GameResult doGame(String userName) {
        System.out.println("I think of number from 1 to 100. Try to guess it " + userName);
        GameResult result = new GameResult(); // sozdajotsja peremennaja drugogo klassa
        result.userName = userName;
        int myNum = random.nextInt(100) + 1;
        System.out.println(myNum);
        double t1 = System.currentTimeMillis();
        for (int i = 1; i <= 10; i++) {
            System.out.print("попытка номер: " + i);
            System.out.println(", Введи число: ");
            int userNum = askNumber();
            if (userNum > myNum) {
                System.out.println("Твоё число больше");
            } else if (userNum == myNum) {
                System.out.println("Бинго !");
                result.attempts = i;
                double t2 = System.currentTimeMillis();
                result.time = (t2 - t1) / 1000;
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
}