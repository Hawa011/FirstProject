package edu.bsu.cs;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class WikipediaClient {

    public InputStream getArticleRevisions(String articleTitle)
            throws IOException, InterruptedException {

        String encodedTitle =
                URLEncoder.encode(articleTitle, StandardCharsets.UTF_8);

        String url = "https://en.wikipedia.org/w/api.php"
                + "?action=query"
                + "&format=json"
                + "&prop=revisions"
                + "&titles=" + encodedTitle
                + "&rvprop=user|timestamp"
                + "&rvlimit=15"
                + "&redirects=1";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("User-Agent", "FirstProject/1.0")
                .build();

        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<InputStream> response =
                client.send(request,
                        HttpResponse.BodyHandlers.ofInputStream());

        return response.body();
    }
}
