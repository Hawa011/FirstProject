package edu.bsu.cs;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RevisionParser {

    public List<Revision> parse(InputStream input) {
        DocumentContext document = JsonPath.parse(input);

        List<Map<String, Object>> revisionData =
                document.read("$.query.pages.*.revisions[*]");

        return revisionData.stream()
                .map(revision -> new Revision(
                        String.valueOf(revision.get("user")),
                        String.valueOf(revision.get("timestamp"))))
                .collect(Collectors.toList());
    }
}
