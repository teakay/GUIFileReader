package com.wirecard.filestructure.gui.view;

import com.wirecard.filestructure.gui.controller.FileStructureController;
import com.wirecard.filestructure.gui.model.FileStructureTableModel;

import javax.swing.*;
import java.awt.*;

public class StructureFileView extends BaseView {
    private JTable structureTable;
    private GridBagConstraints gbc;
    private int col;
    private int row;

    public StructureFileView(){
    }
    public void initialize(){
        registerController(new FileStructureController());

        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        col = 0;
        row = 0;

        initSearchComponent();
        initTable();
        initAddButton();
        initDeleteButton();
        initPaging();

    }

    private void initSearchComponent(){
        JTextField textField = new JTextField(15);
        gbc.gridx = col;
        gbc.gridy = row;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0,0,0,0);
        add(textField,gbc);

        JButton searchButton = new JButton();
        col += 2;
        gbc.gridx = col;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0,10,0,0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        searchButton.setText("Search");
        searchButton.setActionCommand("Search");
        searchButton.addActionListener(this.getController());
        add(searchButton, gbc);
    }

    private void initDeleteButton(){
        JButton deleteButton = new JButton();
        gbc.gridx = ++col;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10,10,0,0);
        deleteButton.setText("Delete");
        deleteButton.setActionCommand("Delete");
        deleteButton.addActionListener(this.getController());
        add(deleteButton,gbc);

    }
    private void initAddButton(){
        JButton addButton = new JButton();
        col = 0;
        gbc.gridx = col;
        gbc.gridy = ++row;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10,0,0,0);
        addButton.setText("Add");
        addButton.setActionCommand("Add");
        addButton.addActionListener(this.getController());
        add(addButton,gbc);

    }
    private void initTable(){
        col = 0;


        structureTable = new JTable(new FileStructureTableModel());
        structureTable.setFillsViewportHeight(true);

        gbc.gridx = col;
        gbc.gridy = ++row;
        gbc.gridwidth = 6;
        gbc.insets = new Insets(20,0,0,0);

        JScrollPane scrollPane = new JScrollPane(structureTable);
        structureTable.setFillsViewportHeight(true);

       add(scrollPane,gbc);

    }
    private void initPaging(){
        col = 0;
        ++row;
        JLabel prev = new JLabel("<html><a href=''>prev</a></html>");
        prev.setForeground(Color.BLUE.darker());
        prev.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        gbc.ipady = 0;
        gbc.gridx = col;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        add(prev,gbc);

        JLabel next = new JLabel("<html><a href=''>next</a></html>");
        next.setForeground(Color.BLUE.darker());
        next.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        gbc.ipady = 0;
        gbc.gridx = ++col;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        add(next,gbc);
    }
    public JTable getStructureTable(){
        return this.structureTable;
    }
}
