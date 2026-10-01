package edu.bsu.cs;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a Wikipedia article title: ");

        String articleTitle = scanner.nextLine().trim();

        if (articleTitle.isEmpty()) {
            System.out.println("No page requested.");
            return;
        }

        System.out.println("You requested: " + articleTitle);
    }
}
