package de.materna.game;

import java.util.Scanner;

public class Test {

    public static void main(String[] args) {

int chocolate=1;

        while (true) {

            System.out.println(

                            "-".repeat(30) + "\n" +
                            "|"+"_".repeat(10)+"*** Candy Lord ***"+"_".repeat(10)+"\n" +
                            "|"+"_".repeat(30)+"\n" +
                            "| cholocate: " + chocolate+ " ".repeat(10)+                          "\n" +
                            "| Vine gumy:                                                         \n" +
                            "| Oreos:                                                                   \n" +
                            "| sdfsdf:                                                              \n" +
                            "| dsfsdf:                                                                     \n" +
                            "|______________________________________________________");

            if (chocolate == 0) {
                System.out.println("game over");
                return;
            }
            chocolate=new Scanner(System.in).nextInt();
        }
    }
}
