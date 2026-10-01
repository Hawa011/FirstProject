package edu.bsu.cs;

import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RevisionParserTest {

    @Test
    void parsesFirstRevision() {
        InputStream input = getClass().getResourceAsStream(
                "/wikipedia-response.json");

        assertNotNull(input);

        List<Revision> revisions =
                new RevisionParser().parse(input);

        assertEquals(3, revisions.size());
        assertEquals(
                "EditorOne",
                revisions.get(0).getUsername());
        assertEquals(
                "2026-09-28T10:30:00Z",
                revisions.get(0).getTimestamp());
    }

    @Test
    void detectsMissingPage() {
        InputStream input = getClass().getResourceAsStream(
                "/wikipedia-missing-page.json");

        assertNotNull(input);

        boolean missing =
                new RevisionParser().isPageMissing(input);

        assertEquals(true, missing);
    }
}