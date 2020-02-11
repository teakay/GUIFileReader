package com.wirecard.filestructure.gui.view;

import com.wirecard.filestructure.gui.controller.StructureFileController;
import com.wirecard.filestructure.gui.model.StructureTableModel;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

public class StructureFileViewPanel extends  AbstractViewPanel {

    private StructureFileController controller;
    private GridBagConstraints gbc;
    private JTextField searchText;
    private JTable structureFileTable;
    private TableRowSorter<TableModel> rowSorter;
    private JButton searchButton;
    private JButton addButton;
    private JButton deleteButton;

    public StructureFileViewPanel(StructureFileController controller){
        this.controller = controller;
        initComponent();
        localInitialization();
    }

    private void initComponent(){
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        JLabel label = new JLabel(" ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0,80,10,80);
        add(label,gbc);

        searchText =  new JTextField(18);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0,0,10,0);
        add(searchText,gbc);

        searchButton = new JButton();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(0,0,10,0);
        searchButton.setText("Search");
        add(searchButton,gbc);

        structureFileTable = new JTable(new StructureTableModel());
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;

        rowSorter = new TableRowSorter<TableModel>(structureFileTable.getModel());
        structureFileTable.setRowSorter(rowSorter);

        int numOfVisibleRows = 10;
        int rows = structureFileTable.getRowHeight() * numOfVisibleRows;
        Dimension d = new Dimension( 450, rows );

        structureFileTable.setPreferredScrollableViewportSize( d );
        structureFileTable.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(structureFileTable);
        add(scrollPane,gbc);

        addButton = new JButton();
        addButton.setText("Add");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(addButton,gbc);

        deleteButton = new JButton();
        deleteButton.setText("Delete");
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(deleteButton,gbc);


    }

    public AbstractViewPanel getContainer(){
        return this;
    }
    private void localInitialization(){
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.doSearch(searchText.getText().toString());
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.doAdd(getContainer());
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    @Override
    public void modelPropertyChange(PropertyChangeEvent evt) {
        if(StructureFileController.DATA_TABLE_SEARCH.equals(evt.getPropertyName())){
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText.getText()));
        }
    }
}
