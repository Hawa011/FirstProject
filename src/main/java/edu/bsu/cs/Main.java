package edu.bsu.cs;
import java.io.IOException;
import java.io.InputStream;
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
            WikipediaResult result =
                    new RevisionParser().parseResult(response);
            if (result.isMissing()) {
                System.out.println("No page found.");
                return;
            }
            if (result.isRedirect()) {
                System.out.println("Redirected.");
            }
            System.out.println("Recent changes:");
            for (Revision revision : result.getRevisions()) {
                System.out.println(
                        "Username: " + revision.getUsername()
                        	+ " | Timestamp: "
                                + revision.getTimestamp());

            }
        } catch (IOException | InterruptedException exception) {
            System.out.println("Network error.");
        }
    }
}
