package editor;

public interface Searcher {

    Searcher searchPattern(String text, String toFind);

    Searcher searchBackward(String text, String toFind, int idxLastFind);

    Searcher searchForward(String text, String toFind, int idxLastFind);

    int getIdxLastFound();

    int getIdxEnd();

    boolean getFound();
}

