package editor;


public class PlainSearcher implements Searcher {

    private final int idxLastFound;
    private final int idxEnd;
    private final boolean found;

    public PlainSearcher() {
        this.idxLastFound = 0;
        this.idxEnd = 0;
        this.found = false;
    }

    private PlainSearcher(int idxLastFound, int idxEnd, boolean found) {
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
    public Searcher searchPattern(String text, String toFind) {
        int idxLastFound = text.indexOf(toFind);
        int idxEnd = idxLastFound + toFind.length();

        if (idxLastFound != -1) {
            return new PlainSearcher(idxLastFound, idxEnd, true);
        } else {
            return new PlainSearcher();
        }
    }

    @Override
    public Searcher searchBackward(String text, String toFind, int idxLastFound) {
        int idxTextFound = text.substring(0, idxLastFound).lastIndexOf(toFind);
        int indexLastFound = idxTextFound != -1 ? idxTextFound : idxLastFound;
        int idxEnd = indexLastFound + toFind.length();

        return new PlainSearcher(indexLastFound, idxEnd, true);
    }

    @Override
    public Searcher searchForward(String text, String toFind, int idxLastFound) {
        int idxTextFound = text.indexOf(toFind, idxLastFound + toFind.length());
        int indexLastFound = idxTextFound != -1 ? idxTextFound : idxLastFound;
        int idxEnd = indexLastFound + toFind.length();

        return new PlainSearcher(indexLastFound, idxEnd, true);
    }
}
