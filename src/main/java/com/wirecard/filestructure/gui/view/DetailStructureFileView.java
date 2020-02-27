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
import java.util.Map;

public class DetailStructureFileView extends  AbstractViewPanel  {

    private StructureFileController controller;
    private GridBagConstraints gbc;
    private JLabel titleLabel;
    private JLabel headerId;
    private JTextField nameTextField;
    private JTextField productTextField;
    private JTextField projectTextField;
    private JComboBox fileExtensionList;
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

    public DetailStructureFileView(StructureFileController controller){
        this.controller = controller;

        headerTableModel = new InstructionFileTableModel();
        detailTableModel = new InstructionFileTableModel();
        footerTableModel = new InstructionFileTableModel();

        initComponentNew();
        localInitialization();
    }
    private void initComponentNew(){

        JLabel titleLabel = new JLabel("Detail File Structure");
        titleLabel.setFont(new Font("Arial",Font.BOLD,18));
        titleLabel.setBackground(Color.GRAY);
        titleLabel.setOpaque(true);
        titleLabel.setForeground(Color.WHITE);

        headerId = new JLabel();
        headerId.setText("");
        headerId.setVisible(false);

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
                        .addComponent(titleLabel,0,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
                        .addComponent(headerId)
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
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(addButton1)
                                .addComponent(deleteButton1)
                        )
                        .addComponent(detailLabel,0,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
                        .addComponent(scrollPaneDetail)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(addButton2)
                                .addComponent(deleteButton2)
                        )
                        .addComponent(footerLabel,0,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
                        .addComponent(scrollPaneFooter)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(addButton3)
                                .addComponent(deleteButton3)
                        )
                )
                .addGroup(layout.createSequentialGroup()
                        .addComponent(backButton)
                        .addComponent(saveButton)
                )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(titleLabel)
                .addComponent(headerId)
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
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(addButton1)
                        .addComponent(deleteButton1)
                )
                .addComponent(detailLabel)
                .addComponent(scrollPaneDetail)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(addButton2)
                        .addComponent(deleteButton2)
                )
                .addComponent(footerLabel)
                .addComponent(scrollPaneFooter)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(addButton3)
                        .addComponent(deleteButton3)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(backButton)
                        .addComponent(saveButton)
                )
        );
    }

    public void localInitialization(){
        headerTable.removeColumn(headerTable.getColumnModel().getColumn(0));
        detailTable.removeColumn(detailTable.getColumnModel().getColumn(0));
        footerTable.removeColumn(footerTable.getColumnModel().getColumn(0));

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.doBack(getContainer());
            }
        });

        addButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                headerTableModel.addRow(new Object[]{null,headerTableModel.getRowCount()+1,null,null,null,null,null,null,false});
            }
        });
        deleteButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List selectedData = new ArrayList();

                for(int row = 0; row < headerTable.getRowCount(); row++){
                    Boolean checked = (Boolean) headerTable.getValueAt(row,7);
                    if(checked){
                        Object[] data = headerTableModel.getRowData(row);
                        selectedData.add(data);
                    }
                }
                if(selectedData.isEmpty()){
                    headerTableModel.deleteRow();
                }else {
                    headerTableModel.deleteRow(selectedData);
                }
            }
        });

        addButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detailTableModel.addRow(new Object[]{null,detailTableModel.getRowCount()+1,null,null,null,null,null,null,false});
            }
        });

        deleteButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List selectedData = new ArrayList();

                for(int row = 0; row < detailTable.getRowCount(); row++){
                    Boolean checked = (Boolean) detailTable.getValueAt(row,7);
                    if(checked){
                        Object[] data = detailTableModel.getRowData(row);
                        selectedData.add(data);
                    }
                }
                if(selectedData.isEmpty()){
                    detailTableModel.deleteRow();
                }else {
                    detailTableModel.deleteRow(selectedData);
                }
            }
        });

        addButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                footerTableModel.addRow(new Object[]{null,footerTableModel.getRowCount()+1,null,null,null,null,null,null,false});
            }
        });
        deleteButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List selectedData = new ArrayList();

                for(int row = 0; row < footerTable.getRowCount(); row++){
                    Boolean checked = (Boolean) footerTable.getValueAt(row,7);
                    if(checked){
                        Object[] data = footerTableModel.getRowData(row);
                        selectedData.add(data);
                    }
                }
                if(selectedData.isEmpty()){
                    footerTableModel.deleteRow();
                }else {
                    footerTableModel.deleteRow(selectedData);
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.doUpdate(getContainer(),headerId.getText(),
                        nameTextField.getText(), productTextField.getText(), projectTextField.getText(),
                        headerTableModel.getAllData(),detailTableModel.getAllData(), footerTableModel.getAllData());
            }
        });
    }

    public void setData(Map data){
        nameTextField.setText((String)data.get("fileName"));
        headerId.setText((String)data.get("parentId"));
        productTextField.setText((String)data.get("productName"));
        projectTextField.setText((String)data.get("projectName"));

        if("txt".equals((String)data.get("fileExtension"))) {
            fileExtensionList.setSelectedIndex(0);
        }else if("csv".equals((String)data.get("fileExtension"))){
            fileExtensionList.setSelectedIndex(1);
        }
        fileExtensionList.setEnabled(false);

        headerTableModel.addRow((List)data.get("header"));
        detailTableModel.addRow((List)data.get("detail"));
        footerTableModel.addRow((List)data.get("footer"));
    }
    private AbstractViewPanel getContainer(){
        return this;
    }

}
