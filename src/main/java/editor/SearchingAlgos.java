package editor;

public class SearchingAlgos {

    final Searcher searcher;

    public SearchingAlgos() {
        searcher = new PlainSearcher();
    }

    private SearchingAlgos(Searcher searcher) {
        this.searcher = searcher;
    }

    public int getIdxLastFound() {
        return searcher.getIdxLastFound();
    }

    public int getIdxEnd() {
        return searcher.getIdxEnd();
    }


    public SearchingAlgos searchPattern(String text, String toFind, boolean regex) {
//        TODO: 18/05/2021 uncomment to check parallel processing
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        Searcher newSearcher;
        if (regex) {
            newSearcher = new RegexSearcher();
        } else {
            newSearcher = new PlainSearcher();
        }
        return new SearchingAlgos(newSearcher.searchPattern(text, toFind));
    }

    public SearchingAlgos searchBackward(String text, String toFind, boolean regex) {
        if (!searcher.getFound()) {
            return searchPattern(text, toFind, regex);
        }

        Searcher newSearcher;
        if (regex) {
            newSearcher = new RegexSearcher();
        } else {
            newSearcher = new PlainSearcher();
        }
        return new SearchingAlgos(newSearcher.searchBackward(text, toFind, searcher.getIdxLastFound()));
    }

    public SearchingAlgos searchForward(String text, String toFind, boolean regex) {
        if (!searcher.getFound()) {
            return searchPattern(text, toFind, regex);
        }

        Searcher newSearcher;
        if (regex) {
            newSearcher = new RegexSearcher();
        } else {
            newSearcher = new PlainSearcher();
        }
        return new SearchingAlgos(newSearcher.searchForward(text, toFind, searcher.getIdxLastFound()));
    }
}
