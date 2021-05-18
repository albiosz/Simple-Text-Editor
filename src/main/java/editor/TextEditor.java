package editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.*;

public class TextEditor extends JFrame {

    public static final String iconsPath = "src/main/resources/icons/";

    final int WIDTH = 600;
    final int HEIGHT = 600;

    JPanel topPanel;
    JTextField searchField;
    JPanel fileButtonsPanel;
    JButton saveButton;
    JButton loadButton;

    JMenuBar menuBar;
    JMenu fileMenu;

    JPanel editorPanel;
    JTextArea textArea;

    JFileChooser fileChooser;

    SearchingButtonsWithListeners searchingButtons;

    String textToFind;
    int idxLastFound;

    public TextEditor() {
        super("Text Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(600, 600));
        setSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(null);

        // Top Panel (file name, buttons) - setup
        setupTopPanel();

        // File buttons - setup
        setupButtonsPanel();
        setupSaveButton();
        setupLoadButton();

        // File name area - setup
        setupSearchField();

        // Search buttons - setup

        // Menu bar - setup
        setupMenuBar();
        setupFileMenu();
        setupSaveItem();
        setupLoadItem();
        fileMenu.addSeparator();
        setupExitItem();

        // Editor Panel - setup
        setupEditorPanel();

        // Listeners - setup
        attachListeners();

        setVisible(true);

        textToFind = "";
        idxLastFound = -1;
    }

    private void setupMenuBar() {
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
    }

    private void setupButtonsPanel() {
        fileButtonsPanel = new JPanel();
        topPanel.add(fileButtonsPanel, BorderLayout.WEST);
        searchingButtons = new SearchingButtonsWithListeners();
        topPanel.add(searchingButtons.getSearchButtonsPanel(), BorderLayout.EAST);
    }

    private void attachListeners() {
        searchingButtons.setAreasWithText(textArea, searchField);
        searchingButtons.attachListenerSearchButton();
        searchingButtons.attachListenerBackwardButton();
        searchingButtons.attachListenerForwardButton();
    }

    private void setupTopPanel() {
        topPanel = new JPanel(new BorderLayout());
        tools.setMargin(topPanel, 15, 15, 5, 15);
        add(topPanel, BorderLayout.NORTH);
    }

    private void setupEditorPanel() {
        editorPanel = new JPanel(new BorderLayout());
        tools.setMargin(editorPanel, 0, 15, 15, 15);
        add(editorPanel, BorderLayout.CENTER);

        textArea = new JTextArea();
        textArea.setName("TextArea");
        tools.setMargin(textArea, 10, 10, 10, 10);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setName("ScrollPane");
        editorPanel.add(scrollPane);

    }

    private void setupSaveItem() {
        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.setName("MenuSave");
        fileMenu.add(saveItem);
        saveItem.addActionListener(this::saveFile);
    }

    private void setupLoadItem() {
        JMenuItem loadItem = new JMenuItem("Load");
        loadItem.setName("MenuLoad");
        fileMenu.add(loadItem);
        loadItem.addActionListener(this::loadFile);
    }

    private void setupExitItem() {
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setName("MenuExit");
        fileMenu.add(exitItem);
        exitItem.addActionListener(actionEvent -> {
            dispose();
        });
    }

    private void setupFileMenu() {
        fileMenu = new JMenu("File");
        fileMenu.setName("MenuFile");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(fileMenu);
    }

    private void setupSearchField() {
        searchField = new JTextField();
        searchField.setName("searchFieldField");
        searchField.setMinimumSize(new Dimension(200, 200));
        Font font = searchField.getFont().deriveFont(Font.PLAIN, 20);
        searchField.setFont(font);
        topPanel.add(searchField);
    }

    private void setupLoadButton() {
        ImageIcon icon = new ImageIcon(iconsPath + "load_icon.png");

        loadButton = new JButton();
        loadButton.setBounds(0, 0, 0, 0);
        loadButton.setPreferredSize(
                new Dimension(icon.getIconWidth() + 8, icon.getIconHeight() + 5)
        );
        loadButton.setIcon(icon);
        loadButton.setName("LoadButton");
        fileButtonsPanel.add(loadButton);
        loadButton.addActionListener(this::loadFile);
    }

    private void setupSaveButton() {
        ImageIcon icon = new ImageIcon(iconsPath + "save_icon.png");

        saveButton = new JButton();
        saveButton.setBounds(0, 0, 0, 0);
        saveButton.setPreferredSize(
                new Dimension(icon.getIconWidth() + 8, icon.getIconHeight() + 5)
        );
        saveButton.setIcon(icon);
        saveButton.setName("SaveButton");
        fileButtonsPanel.add(saveButton);
        saveButton.addActionListener(this::saveFile);
    }



    private void loadFile(ActionEvent actionEvent) {
        fileChooser = new JFileChooser(".//text_files//");
        fileChooser.showOpenDialog(null);
        File inputFile = fileChooser.getSelectedFile();
        try (FileReader inputFileReader =  new FileReader(inputFile)) {
            textArea.read(inputFileReader, null);
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }

    }

    private void saveFile(ActionEvent actionEvent) {
        fileChooser = new JFileChooser(".//text_files//");
        fileChooser.showSaveDialog(null);
        File outputFile = fileChooser.getSelectedFile();
        try (FileWriter outputFileReader = new FileWriter(outputFile)) {
            textArea.write(outputFileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
