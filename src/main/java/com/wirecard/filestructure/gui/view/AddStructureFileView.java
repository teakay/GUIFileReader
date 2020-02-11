package com.wirecard.filestructure.gui.view;

import com.wirecard.filestructure.gui.controller.StructureFileController;
import com.wirecard.filestructure.gui.model.InstructionFileTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

public class AddStructureFileView extends AbstractViewPanel {

    private StructureFileController controller;
    private GridBagConstraints gbc;
    private JTable headerTable;
    private JTable detailTable;
    private JTable footerTable;
    private JButton backButton;
    private JButton saveButton;
    private JButton deleteButton1;
    private JButton addButton1;
    private JButton deleteButton2;
    private JButton addButton2;
    private JButton deleteButton3;
    private JButton addButton3;

    public AddStructureFileView(StructureFileController controller){
        this.controller = controller;
        initComponent();
        localInitialization();
    }
    private void initComponent() {
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        int row =  0;

        JLabel nameLabel = new JLabel("Structure File Name");
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0,10,10,10);
        add(nameLabel, gbc);

        JTextField nameTextField = new JTextField(15);
        gbc.ipady = 10;
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(10,10,10,10);
        add(nameTextField, gbc);

        JLabel label = new JLabel("Header");
        gbc.ipady = 0;
        gbc.gridx = 0;
        gbc.gridy = ++row;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 3;
        add(label, gbc);

        headerTable = new JTable(new InstructionFileTableModel());
        gbc.gridx = 0;
        gbc.gridy = ++row;
        gbc.gridwidth = 3;
        headerTable.setFillsViewportHeight(true);
        headerTable.setPreferredScrollableViewportSize( new Dimension( 450, 160 ) );
        JScrollPane scrollPane = new JScrollPane(headerTable);
        add(scrollPane,gbc);

        addButton1 = new JButton();
        addButton1.setText("Add");
        gbc.gridx = 0;
        gbc.gridy = ++row;
        gbc.gridwidth = 1;
        add(addButton1,gbc);

        JLabel detailLabel = new JLabel("Detail");
        gbc.gridx = 0;
        gbc.gridy = ++row;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 3;
        add(detailLabel, gbc);

        detailTable = new JTable(new InstructionFileTableModel());
        gbc.gridx = 0;
        gbc.gridy = ++row;
        gbc.gridwidth = 3;
        detailTable.setFillsViewportHeight(true);
        detailTable.setPreferredScrollableViewportSize( new Dimension( 450, 160 ) );

        JScrollPane scrollPaneDetail = new JScrollPane(detailTable);
        add(scrollPaneDetail,gbc);

        JLabel footerLabel = new JLabel("Footer");
        gbc.gridx = 0;
        gbc.gridy = ++row;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 3;
        add(footerLabel, gbc);

        footerTable = new JTable(new InstructionFileTableModel());
        gbc.gridx = 0;
        gbc.gridy = ++row;
        gbc.gridwidth = 3;
        footerTable.setFillsViewportHeight(true);
        footerTable.setPreferredScrollableViewportSize( new Dimension( 450, 160 ) );

        JScrollPane scrollPaneFooter = new JScrollPane(footerTable);
        add(scrollPaneFooter,gbc);

        backButton = new JButton();
        backButton.setText("Back");
        gbc.gridx = 0;
        gbc.gridy = ++row;
        gbc.gridwidth = 1;
        add(backButton,gbc);

        saveButton = new JButton();
        saveButton.setText("Save");
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10,10,10,10);
        add(saveButton,gbc);
    }

    private void localInitialization(){
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.doBack(getContainer());
            }
        });

        addButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
    }

    private AbstractViewPanel getContainer(){
        return this;
    }
    @Override
    public void modelPropertyChange(PropertyChangeEvent evt) {

    }
}
