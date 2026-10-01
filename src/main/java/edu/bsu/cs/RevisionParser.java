package edu.bsu.cs;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RevisionParser {

    public WikipediaResult parseResult(InputStream input) {
        DocumentContext document = JsonPath.parse(input);

        boolean missing = pathExists(
                document,
                "$.query.pages.*.missing");

        boolean redirect = pathExists(
                document,
                "$.query.redirects");

        List<Revision> revisions = new ArrayList<>();

        if (!missing) {
            List<Map<String, Object>> revisionData =
                    document.read("$.query.pages.*.revisions[*]");

            for (Map<String, Object> revision : revisionData) {
                revisions.add(new Revision(
                        String.valueOf(revision.get("user")),
                        String.valueOf(revision.get("timestamp"))));
            }
        }

        return new WikipediaResult(
                revisions,
                missing,
                redirect);
    }

    public List<Revision> parse(InputStream input) {
        return parseResult(input).getRevisions();
    }

    private boolean pathExists(
            DocumentContext document,
            String path) {

        try {
            List<Object> values = document.read(path);
            return !values.isEmpty();
        } catch (PathNotFoundException exception) {
            return false;
        }
    }
}