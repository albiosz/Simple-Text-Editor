package editor;

import javax.swing.*;
import java.awt.*;

import static editor.TextEditor.iconsPath;

public class SearchingButtons {

    protected JPanel searchButtonsPanel;
    protected JButton searchButton;
    protected JButton backwardButton;
    protected JButton forwardButton;
    protected JCheckBox regexCheckBox;

    public SearchingButtons() {
        this.searchButtonsPanel = new JPanel();

        setupSearchButton();
        setupBackwardButton();
        setupForwardButton();
        setupRegexCheckBox();
    }

    public JPanel getSearchButtonsPanel() {
        return searchButtonsPanel;
    }

    private void setupSearchButton() {
        ImageIcon icon = new ImageIcon(iconsPath + "search_icon.png");

        searchButton = new JButton();
        searchButton.setBounds(0, 0, 0, 0);
        searchButton.setPreferredSize(
                new Dimension(icon.getIconWidth() + 8, icon.getIconHeight() + 5)
        );
        searchButton.setIcon(icon);
        searchButton.setName("StartSearchButton");
        searchButtonsPanel.add(searchButton, BorderLayout.EAST);
    }

    private void setupBackwardButton() {
        ImageIcon icon = new ImageIcon(iconsPath + "backward_icon.png");

        backwardButton = new JButton();
        backwardButton.setBounds(0, 0, 0, 0);
        backwardButton.setPreferredSize(
                new Dimension(icon.getIconWidth() + 8, icon.getIconHeight() + 5)
        );
        backwardButton.setIcon(icon);
        backwardButton.setName("PreviousMatchButton");
        searchButtonsPanel.add(backwardButton, BorderLayout.EAST);

    }

    private void setupForwardButton() {
        ImageIcon icon = new ImageIcon(iconsPath + "forward_icon.png");

        forwardButton = new JButton();
        forwardButton.setBounds(0, 0, 0, 0);
        forwardButton.setPreferredSize(
                new Dimension(icon.getIconWidth() + 8, icon.getIconHeight() + 5)
        );
        forwardButton.setIcon(icon);
        forwardButton.setName("NextMatchButton");
        searchButtonsPanel.add(forwardButton, BorderLayout.EAST);

    }

    private void setupRegexCheckBox() {
        regexCheckBox = new JCheckBox("Use regex");
        searchButtonsPanel.add(regexCheckBox, BorderLayout.EAST);
    }
}
