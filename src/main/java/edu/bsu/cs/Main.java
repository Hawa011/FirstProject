package edu.bsu.cs;

import java.io.InputStream;
import java.util.List;
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

        try {
            WikipediaClient client = new WikipediaClient();

            InputStream response =
                    client.getArticleRevisions(articleTitle);

            RevisionParser parser = new RevisionParser();
            List<Revision> revisions = parser.parse(response);

            System.out.println();
            System.out.println("Recent changes:");

            for (Revision revision : revisions) {
                System.out.println(
                       "Username:" + revision.getUsername()
                                + " | TimeStamp: "
                                + revision.getTimestamp());
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}