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
    public int getIdxLastFound() {
        return idxLastFound;
    }

    @Override
    public int getIdxEnd() {
        return idxEnd;
    }

    @Override
    public boolean getFound() {
        return this.found;
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

        Matcher matcher;
        try {
            matcher = pattern.matcher(text).region(0, idxLastFound-1);
        } catch (IndexOutOfBoundsException ex) {
            return new RegexSearcher(idxLastFound, idxLastFound + toFind.length(), true);
        }

        if (matcher.find()) {
            int indexLastFound = matcher.start();
            int indexEnd = matcher.end();
            while (matcher.find()) {
                indexLastFound = matcher.start();
                indexEnd = matcher.end();
            }
            return new RegexSearcher(indexLastFound, indexEnd, true);
        } else {
            return new RegexSearcher(idxLastFound, idxLastFound + toFind.length(), true);
        }
    }

    @Override
    public Searcher searchForward(String text, String toFind, int idxLastFound) {
        Pattern pattern = Pattern.compile(toFind);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find(idxLastFound + 1)) {
            int indexLastFound = matcher.start();
            int indexEnd = matcher.end();
            return new RegexSearcher(indexLastFound, indexEnd, true);
        } else {
            return new RegexSearcher(idxLastFound, idxLastFound + toFind.length(), true);
        }

    }
}