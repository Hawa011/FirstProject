package edu.bsu.cs;

import java.util.List;

public class WikipediaResult {

    private final List<Revision> revisions;
    private final boolean missing;
    private final boolean redirect;

    public WikipediaResult(
            List<Revision> revisions,
            boolean missing,
            boolean redirect) {

        this.revisions = revisions;
        this.missing = missing;
        this.redirect = redirect;
    }

    public List<Revision> getRevisions() {
        return revisions;
    }

    public boolean isMissing() {
        return missing;
    }

    public boolean isRedirect() {
        return redirect;
    }
}
