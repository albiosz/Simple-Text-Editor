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

    JTextField fileNameTextField;
    JButton saveButton;
    JButton loadButton;
    JTextArea textArea;

    public TextEditor() {

        super("Text Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(300, 300));
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);

        JPanel topPanel = new JPanel(new BorderLayout());
        setMargin(topPanel, 15,15, 5, 15);
        add(topPanel, BorderLayout.NORTH);

        fileNameTextField = setFileName(topPanel);

        JPanel centerPanel = new JPanel(new BorderLayout());
        add(centerPanel, BorderLayout.CENTER);
        textArea =  setCenter(centerPanel);

        JPanel buttonsPanel = new JPanel();
        topPanel.add(buttonsPanel, BorderLayout.EAST);
        saveButton = setSaveButton(buttonsPanel);
        loadButton = setLoadButton(buttonsPanel);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = setFileMenu(menuBar);
        JMenuItem saveItem = setSaveItem(fileMenu);
        JMenuItem loadItem = setLoadItem(fileMenu);
        fileMenu.addSeparator();
        JMenuItem exitItem = setExitItem(fileMenu);

        setVisible(true);
    }

    private JMenuItem setSaveItem(JMenu fileMenu) {

        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.setName("MenuSave");
        fileMenu.add(saveItem);
        saveItem.addActionListener(this::saveFile);

        return saveItem;
    }

    private JMenuItem setLoadItem(JMenu fileMenu) {

        JMenuItem loadItem = new JMenuItem("Load");
        loadItem.setName("MenuLoad");
        fileMenu.add(loadItem);
        loadItem.addActionListener(this::loadFile);

        return loadItem;
    }

    private JMenuItem setExitItem(JMenu fileMenu) {
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setName("MenuExit");
        fileMenu.add(exitItem);
        exitItem.addActionListener(actionEvent -> {
            dispose();
        });

        return exitItem;
    }

    private JMenu setFileMenu(JMenuBar menuBar) {

        JMenu fileMenu = new JMenu("File");
        fileMenu.setName("MenuFile");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(fileMenu);


        return fileMenu;
    }

    private JTextField setFileName(JPanel topPanel) {

        JTextField textField = new JTextField();
        textField.setName("FilenameField");
        textField.setMinimumSize(new Dimension(200, 200));
        Font font = textField.getFont().deriveFont(Font.PLAIN, 17);
        textField.setFont(font);
        topPanel.add(textField);
        return textField;
    }

    private JButton setLoadButton(JPanel buttonsPanel) {

        JButton loadButton = new JButton("Load");
        loadButton.setName("LoadButton");
        loadButton.setBounds(0,0,0,0);
        buttonsPanel.add(loadButton);

        loadButton.addActionListener(this::loadFile);

        return loadButton;
    }

    private void loadFile(ActionEvent actionEvent) {
        try (FileReader input = new FileReader(fileNameTextField.getText())) {
            textArea.read(input, null);
        } catch (Exception ex) {
            textArea.setText("");
            System.err.println("Error: " + ex.getMessage());
        }
    }

    private JButton setSaveButton(JPanel buttonsPanel) {

        JButton saveButton = new JButton("Save");
        saveButton.setName("SaveButton");
        saveButton.setBounds(0,0,0,0);
        buttonsPanel.add(saveButton);

        saveButton.addActionListener(this::saveFile);

        return saveButton;
    }

    private void saveFile(ActionEvent actionEvent) {
        try (FileWriter writer = new FileWriter(fileNameTextField.getText())) {
            textArea.write(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private JTextArea setCenter(JPanel centerPanel) {

        setMargin(centerPanel, 0, 15, 15, 15);

        JTextArea textArea = new JTextArea();
        textArea.setName("TextArea");
        setMargin(textArea, 10,10, 10, 10);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setName("ScrollPane");
        centerPanel.add(scrollPane);
        add(centerPanel, BorderLayout.CENTER);
        return textArea;
    }


    public static void setMargin(JComponent aComponent, int aTop,
                                 int aRight, int aBottom, int aLeft) {

        Border border = aComponent.getBorder();

        Border marginBorder = new EmptyBorder(new Insets(aTop, aLeft,
                aBottom, aRight));//from   w ww. j  a va2s.  c  o m
        aComponent.setBorder(border == null ? marginBorder
                : new CompoundBorder(marginBorder, border));
    }
}
