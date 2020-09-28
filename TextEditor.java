package editor;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;


public class TextEditor extends JFrame {

    final int WIDTH = 500;
    final int HEIGHT = 500;

    JPanel topPanel;
    JTextField fileName;
    JPanel buttonsPanel;
    JButton saveButton;
    JButton loadButton;

    JMenuBar menuBar;
    JMenu fileMenu;

    JPanel editorPanel;
    JTextArea textArea;

    public TextEditor() {
        super("Text Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(300, 300));
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);

        // Top Panel (file name, buttons) - setup
        setTopPanel();

        // File name area - setup
        setFileName();

        // Buttons - setup
        setButtonsPanel();
        setSaveButton();
        setLoadButton();

        // Menu bar - setup
        setMenuBar();
        setFileMenu();
        setSaveItem();
        setLoadItem();
        fileMenu.addSeparator();
        setExitItem();

        // Editor Panel - setup
        setEditorPanel();

        setVisible(true);
    }

    private void setMenuBar() {
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
    }

    private void setButtonsPanel() {
        buttonsPanel = new JPanel();
        topPanel.add(buttonsPanel, BorderLayout.EAST);
    }

    private void setTopPanel() {
        topPanel = new JPanel(new BorderLayout());
        tools.setMargin(topPanel, 15, 15, 5, 15);
        add(topPanel, BorderLayout.NORTH);
    }

    private void setEditorPanel() {
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

    private void setSaveItem() {
        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.setName("MenuSave");
        fileMenu.add(saveItem);
        saveItem.addActionListener(this::saveFile);
    }

    private void setLoadItem() {
        JMenuItem loadItem = new JMenuItem("Load");
        loadItem.setName("MenuLoad");
        fileMenu.add(loadItem);
        loadItem.addActionListener(this::loadFile);
    }

    private void setExitItem() {
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setName("MenuExit");
        fileMenu.add(exitItem);
        exitItem.addActionListener(actionEvent -> {
            dispose();
        });
    }

    private void setFileMenu() {
        fileMenu = new JMenu("File");
        fileMenu.setName("MenuFile");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(fileMenu);
    }

    private void setFileName() {
        JTextField textField = new JTextField();
        textField.setName("FilenameField");
        textField.setMinimumSize(new Dimension(200, 200));
        Font font = textField.getFont().deriveFont(Font.PLAIN, 17);
        textField.setFont(font);
        topPanel.add(textField);
    }

    private void setLoadButton() {
        loadButton = new JButton("Load");
        loadButton.setName("LoadButton");
        loadButton.setBounds(0, 0, 0, 0);
        buttonsPanel.add(loadButton);

        loadButton.addActionListener(this::loadFile);
    }

    private void loadFile(ActionEvent actionEvent) {
        try (FileReader input = new FileReader(fileName.getText())) {
            textArea.read(input, null);
        } catch (Exception ex) {
            textArea.setText("");
            System.err.println("Error: " + ex.getMessage());
        }
    }

    private void setSaveButton() {
        saveButton = new JButton("Save");
        saveButton.setName("SaveButton");
        saveButton.setBounds(0, 0, 0, 0);
        buttonsPanel.add(saveButton);

        saveButton.addActionListener(this::saveFile);
    }

    private void saveFile(ActionEvent actionEvent) {
        try (FileWriter writer = new FileWriter(fileName.getText())) {
            textArea.write(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
