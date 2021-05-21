package editor;

import javax.swing.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SearchingButtonsWithListeners extends SearchingButtons {

    private JTextArea textArea;
    private JTextField textField;
    private SearchingAlgos searchAlgos;
    private ExecutorService executorService;

    public SearchingButtonsWithListeners() {
        super();
        this.searchAlgos = new SearchingAlgos();
        this.executorService = Executors.newSingleThreadScheduledExecutor();
    }

    public void setAreasWithText(JTextArea textArea, JTextField textField) {
        this.textArea = textArea;
        this.textField = textField;
    }

    public void attachListenerSearchButton() {
        if (textArea == null || textField == null) {
            throw new NullPointerException();
        }

        searchButton.addActionListener(al -> {
            executorService.execute(() -> {
                synchronized (textArea) {
                    String textToProcess = textArea.getText();
                    String toFind = textField.getText();
                    searchAlgos = searchAlgos.searchPattern(textToProcess, toFind, regexCheckBox.isSelected());
                    highlightText(textArea, searchAlgos.getIdxLastFound(), searchAlgos.getIdxEnd());
                }
            });
        });
    }

    public void attachListenerBackwardButton() {
        if (textArea == null || textField == null) {
            throw new NullPointerException();
        }

        backwardButton.addActionListener(al -> {
            executorService.execute(() -> {
                synchronized (textArea) {
                    String textToProcess = textArea.getText();
                    String toFind = textField.getText();
                    searchAlgos = searchAlgos.searchBackward(textToProcess, toFind, regexCheckBox.isSelected());
                    highlightText(textArea, searchAlgos.getIdxLastFound(), searchAlgos.getIdxEnd());
                }
            });
        });
    }

    public void attachListenerForwardButton() {
        if (textArea == null || textField == null) {
            throw new NullPointerException();
        }

        forwardButton.addActionListener(al -> {
            executorService.execute(() -> {
                synchronized (textArea) {
                    String textToProcess = textArea.getText();
                    String toFind = textField.getText();
                    searchAlgos = searchAlgos.searchForward(textToProcess, toFind, regexCheckBox.isSelected());
                    highlightText(textArea, searchAlgos.getIdxLastFound(), searchAlgos.getIdxEnd());
                }
            });
        });
    }

    private void highlightText(JTextArea textArea, int idxStart, int idxEnd) {
        textArea.setCaretPosition(idxStart);
        textArea.select(idxStart, idxEnd);
        textArea.grabFocus();
    }
}
