package com.wirecard.filestructure.gui.view;

import com.wirecard.filestructure.gui.controller.StructureFileController;
import com.wirecard.filestructure.gui.model.InstructionFileTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

public class AddStructureFileView extends AbstractViewPanel {

    private StructureFileController controller;
    private GridBagConstraints gbc;
    private JTextField nameTextField;
    private JComboBox fileExtensionList;
    private JTextField productTextField;
    private JTextField projectTextField;
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
    private InstructionFileTableModel headerTableModel;
    private InstructionFileTableModel detailTableModel;
    private InstructionFileTableModel footerTableModel;

    public AddStructureFileView(StructureFileController controller){
        this.controller = controller;

        headerTableModel = new InstructionFileTableModel();
        detailTableModel = new InstructionFileTableModel();
        footerTableModel = new InstructionFileTableModel();

        initComponentNew();
        localInitialization();
    }
    private void initComponentNew(){

        JLabel titleLabel = new JLabel("Add File Structure");
        titleLabel.setFont(new Font("Arial",Font.BOLD,18));

        JLabel nameLabel = new JLabel("File Structure Name");
        nameTextField = new JTextField(15);

        JLabel extensionLabel = new JLabel("File Extension");
        fileExtensionList = new JComboBox();
        fileExtensionList.addItem("txt");
        fileExtensionList.addItem("csv");

        JLabel productLabel = new JLabel("Product Name");
        productTextField = new JTextField();

        JLabel projectLabel = new JLabel("Project Name");
        projectTextField = new JTextField();

        JLabel headerLabel = new JLabel("Header");
        headerLabel.setBackground(Color.LIGHT_GRAY);
        headerLabel.setOpaque(true);
        headerTable = new JTable(headerTableModel);
        headerTable.setFillsViewportHeight(true);
        headerTable.setPreferredScrollableViewportSize( new Dimension( 450, 160 ) );
        JScrollPane scrollPane = new JScrollPane(headerTable);
        addButton1 = new JButton();
        addButton1.setText("Add");
        deleteButton1 = new JButton();
        deleteButton1.setText("Delete");

        JLabel detailLabel = new JLabel("Detail");
        detailLabel.setBackground(Color.LIGHT_GRAY);
        detailLabel.setOpaque(true);
        detailTable = new JTable(detailTableModel);
        detailTable.setFillsViewportHeight(true);
        detailTable.setPreferredScrollableViewportSize( new Dimension( 450, 160 ) );
        JScrollPane scrollPaneDetail = new JScrollPane(detailTable);
        addButton2 = new JButton();
        addButton2.setText("Add");
        deleteButton2 = new JButton();
        deleteButton2.setText("Delete");

        JLabel footerLabel = new JLabel("Footer");
        footerLabel.setBackground(Color.LIGHT_GRAY);
        footerLabel.setOpaque(true);
        footerTable = new JTable(footerTableModel);
        footerTable.setFillsViewportHeight(true);
        footerTable.setPreferredScrollableViewportSize( new Dimension( 450, 160 ) );
        JScrollPane scrollPaneFooter = new JScrollPane(footerTable);
        addButton3 = new JButton();
        addButton3.setText("Add");
        deleteButton3 = new JButton();
        deleteButton3.setText("Delete");

        backButton = new JButton();
        backButton.setText("Back");
        saveButton = new JButton();
        saveButton.setText("Save");


        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(titleLabel)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(nameLabel)
                                        .addComponent(extensionLabel)
                                        .addComponent(productLabel)
                                        .addComponent(projectLabel)
                                )
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(nameTextField,0,100,200)
                                        .addComponent(fileExtensionList,0,100,200)
                                        .addComponent(productTextField,0,100,200)
                                        .addComponent(projectTextField,0,100,200)
                                )
                            )
                        .addComponent(headerLabel,0,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
                        .addComponent(scrollPane)
                        .addComponent(detailLabel,0,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
                        .addComponent(scrollPaneDetail)
                        .addComponent(footerLabel,0,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
                        .addComponent(scrollPaneFooter)
                )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(titleLabel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(nameLabel)
                        .addComponent(nameTextField)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(extensionLabel)
                        .addComponent(fileExtensionList)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(productLabel)
                        .addComponent(productTextField)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(projectLabel)
                        .addComponent(projectTextField)
                )
                .addComponent(headerLabel)
                .addComponent(scrollPane)
                .addComponent(detailLabel)
                .addComponent(scrollPaneDetail)
                .addComponent(footerLabel)
                .addComponent(scrollPaneFooter)
        );
    }

    private void initComponent() {
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        int row =  0;

        JLabel titleLabel = new JLabel("Add File Structure");
        titleLabel.setFont(new Font("Arial",Font.BOLD,18));
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0,0,20,0);
        add(titleLabel,gbc);

        JLabel nameLabel = new JLabel("File Structure Name");
        gbc.gridx = 0;
        gbc.gridy = ++row;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0,10,10,10);
        add(nameLabel, gbc);

        nameTextField = new JTextField(15);
        gbc.ipady = 10;
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10,0,10,10);
        add(nameTextField, gbc);

        JLabel extensionLabel = new JLabel("File Extension");
        gbc.gridx = 0;
        gbc.gridy = ++row;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0,10,10,10);
        add(extensionLabel, gbc);

        fileExtensionList = new JComboBox();
        fileExtensionList.addItem("txt");
        fileExtensionList.addItem("csv");
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10,0,10,10);
        add(fileExtensionList, gbc);


        JLabel label = new JLabel("Header");
        gbc.ipady = 0;
        gbc.gridx = 0;
        gbc.gridy = ++row;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0,10,0,80);
        add(label, gbc);

        headerTable = new JTable(headerTableModel);
        gbc.gridx = 0;
        gbc.gridy = ++row;
        gbc.gridwidth = 3;
        headerTable.setFillsViewportHeight(true);
        headerTable.setPreferredScrollableViewportSize( new Dimension( 450, 160 ) );
        JScrollPane scrollPane = new JScrollPane(headerTable);
        add(scrollPane,gbc);


        addButton1 = new JButton();
        addButton1.setText("Add");
        gbc.gridx = 1;
        gbc.gridy = ++row;
        gbc.gridwidth = 1;
        add(addButton1,gbc);

        deleteButton1 = new JButton();
        deleteButton1.setText("Delete");
        gbc.gridx = 2;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        add(deleteButton1,gbc);

        JLabel detailLabel = new JLabel("Detail");
        gbc.gridx = 0;
        gbc.gridy = ++row;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 3;
        add(detailLabel, gbc);

        detailTable = new JTable(detailTableModel);
        gbc.gridx = 0;
        gbc.gridy = ++row;
        gbc.gridwidth = 3;
        detailTable.setFillsViewportHeight(true);
        detailTable.setPreferredScrollableViewportSize( new Dimension( 450, 160 ) );

        JScrollPane scrollPaneDetail = new JScrollPane(detailTable);
        add(scrollPaneDetail,gbc);

        addButton2 = new JButton();
        addButton2.setText("Add");
        gbc.gridx = 1;
        gbc.gridy = ++row;
        gbc.gridwidth = 1;
        add(addButton2,gbc);

        deleteButton2 = new JButton();
        deleteButton2.setText("Delete");
        gbc.gridx = 2;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        add(deleteButton2,gbc);

        JLabel footerLabel = new JLabel("Footer");
        gbc.gridx = 0;
        gbc.gridy = ++row;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 3;
        add(footerLabel, gbc);

        footerTable = new JTable(footerTableModel);
        gbc.gridx = 0;
        gbc.gridy = ++row;
        gbc.gridwidth = 3;
        footerTable.setFillsViewportHeight(true);
        footerTable.setPreferredScrollableViewportSize( new Dimension( 450, 160 ) );

        JScrollPane scrollPaneFooter = new JScrollPane(footerTable);
        add(scrollPaneFooter,gbc);

        addButton3 = new JButton();
        addButton3.setText("Add");
        gbc.gridx = 1;
        gbc.gridy = ++row;
        gbc.gridwidth = 1;
        add(addButton3,gbc);

        deleteButton3 = new JButton();
        deleteButton3.setText("Delete");
        gbc.gridx = 2;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        add(deleteButton3,gbc);

        JSeparator separator = new JSeparator();
        separator.setOrientation(SwingConstants.HORIZONTAL);
        gbc.gridx = 0;
        gbc.gridy = ++row;
        gbc.gridwidth = 3;
        add(separator,gbc);

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
                headerTableModel.addRow(new Object[]{null,headerTableModel.getRowCount()+1,null,null,null,null,false});
            }
        });
        deleteButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                headerTableModel.deleteRow();
            }
        });

        addButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detailTableModel.addRow(new Object[]{null,detailTableModel.getRowCount()+1,null,null,null,null,false});
            }
        });

        deleteButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detailTableModel.deleteRow();
            }
        });

        addButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                footerTableModel.addRow(new Object[]{null,footerTableModel.getRowCount()+1,null,null,null,null,false});
            }
        });
        deleteButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                footerTableModel.deleteRow();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.doSaveData(getContainer(),nameTextField.getText(), (String)fileExtensionList.getSelectedItem(),headerTableModel.getAllData(), detailTableModel.getAllData(), footerTableModel.getAllData());
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
