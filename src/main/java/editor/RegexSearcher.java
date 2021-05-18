package editor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexSearcher implements Searcher {

    private final int idxLastFound;
    private final int idxEnd;
    private final boolean found;

    public RegexSearcher() {
        this.idxLastFound = 0;
        this.idxEnd = 0;
        this.found = false;
    }

    private RegexSearcher(int idxLastFound, int idxEnd, boolean found) {
        this.idxLastFound = idxLastFound;
        this.idxEnd = idxEnd;
        this.found = found;
    }

    @Override
    public Searcher searchPattern(String text, String toFind) {
        Pattern pattern = Pattern.compile(toFind);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            int indexLastFound = matcher.start();
            int indexEnd = matcher.end();
            return new RegexSearcher(indexLastFound, indexEnd, true);
        } else {
            return new RegexSearcher();
        }
    }

    @Override
    public Searcher searchBackward(String text, String toFind, int idxLastFound) {
        Pattern pattern = Pattern.compile(toFind);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find(idxLastFound)) {
            int indexLastFound = matcher.start();
            int indexEnd = matcher.end();
            return new RegexSearcher(indexLastFound, indexEnd, true);
        } else {
            return new RegexSearcher(this.idxLastFound, this.idxEnd, this.found);
        }
    }

    @Override
    public Searcher searchForward(String text, String toFind, int idxLastFound) {
        Pattern pattern = Pattern.compile(toFind.substring(idxEnd));
        Matcher matcher = pattern.matcher(text);
        if (matcher.find(idxLastFound)) {
            int indexLastFound = idxEnd + matcher.start();
            int indexEnd = idxEnd + matcher.end();
            return new RegexSearcher(indexLastFound, indexEnd, true);
        } else {
            return new RegexSearcher(this.idxLastFound, this.idxEnd, this.found);
        }

    }

    @Override
    public int getIdxLastFound() {
        return idxLastFound;
    }

    @Override
    public int getIdxEnd() {
        return idxEnd;
    }
}