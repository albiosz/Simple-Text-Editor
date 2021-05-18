package editor;

public interface Searcher {

    public Searcher searchPattern(String text, String toFind);

    public Searcher searchBackward(String text, String toFind, int idxLastFind);

    public Searcher searchForward(String text, String toFind, int idxLastFind);

    public int getIdxLastFound();

    public int getIdxEnd();
}

