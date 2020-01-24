package com.wirecard.filestructure.gui.view;

import com.wirecard.filestructure.gui.utils.Constants;

import javax.swing.*;
import java.awt.*;

public class StructureFileView extends JPanel {
    private JTable structureTable;
    private GridBagConstraints gbc;
    private int col;
    private int row;
    public StructureFileView(){
        initialize();
    }
    public void initialize(){
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        col = 0;
        row = 0;

        initSearchComponent();
        initAddButton();
        initTable();
        initPaging();

    }

    private void initSearchComponent(){
        JTextField textField = new JTextField(10);
        textField.setSize(100,30);
        gbc.gridx = col;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        add(textField,gbc);

        JButton searchButton = new JButton();
        col += 2;
        gbc.gridx = col;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0,10,0,0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        searchButton.setText("Search");
        add(searchButton, gbc);
    }

    private void initAddButton(){
        JButton addButton = new JButton();
        col += 2;
        gbc.gridx = col;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        addButton.setText("Add");
        add(addButton,gbc);

    }
    private void initTable(){
        col = 0;
        Object[][] data = {
                {"CTL File", "txt","24/01/2020"},
                {"Pay File","txt","24/01/2020"}
        };

        structureTable = new JTable(data, Constants.STRUCTURE_FILE_LIST_COLUMN);
        gbc.gridx = col;
        gbc.gridy = ++row;
        gbc.gridwidth = 6;
        gbc.insets = new Insets(20,0,0,0);
        add(new JScrollPane(structureTable),gbc);

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
       // gbc.weightx = 1;
        gbc.gridwidth = 1;
        add(next,gbc);
    }
    public JTable getStructureTable(){
        return this.structureTable;
    }
}
