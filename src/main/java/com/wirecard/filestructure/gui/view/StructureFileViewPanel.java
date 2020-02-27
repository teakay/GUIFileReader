package com.wirecard.filestructure.gui.view;

import com.wirecard.filestructure.gui.controller.StructureFileController;
import com.wirecard.filestructure.gui.model.StructureTableModel;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

public class StructureFileViewPanel extends  AbstractViewPanel {

    private StructureFileController controller;
    private JTextField searchText;
    private JTable structureFileTable;
    private TableRowSorter<TableModel> rowSorter;
    private JButton searchButton;
    private JButton addButton;
    private JButton deleteButton;
    private StructureTableModel structureTableModel;

    public StructureFileViewPanel(StructureFileController controller){
        this.controller = controller;
        structureTableModel = new StructureTableModel();

        initComponentNew();
        localInitialization();
    }

    public void initComponentNew(){
        JLabel titleLabel = new JLabel("File Structure");
        titleLabel.setFont(new Font("Arial",Font.BOLD,18));
        titleLabel.setBackground(Color.GRAY);
        titleLabel.setOpaque(true);
        titleLabel.setForeground(Color.WHITE);

        JLabel label = new JLabel("Find what :");

        searchText =  new JTextField(18);
        searchButton = new JButton();
        searchButton.setText("Search");

        JLabel tableLabel = new JLabel("File Structure Listing");
        tableLabel.setBackground(Color.LIGHT_GRAY);
        tableLabel.setOpaque(true);

        structureFileTable = new JTable(structureTableModel);
        rowSorter = new TableRowSorter<TableModel>(structureFileTable.getModel());
        structureFileTable.setRowSorter(rowSorter);
        structureFileTable.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(structureFileTable);

        addButton = new JButton();
        addButton.setText("Add");

        deleteButton = new JButton();
        deleteButton.setText("Delete");

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(titleLabel,0,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                        .addComponent(label)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(searchText,0,100,200)
                                                        .addComponent(searchButton)
                                                )
                                                .addComponent(tableLabel,0,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
                                                .addComponent(scrollPane)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(addButton)
                                                        .addComponent(deleteButton)
                                                )
                                        )
                        )

                )

        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(titleLabel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(label)
                    .addComponent(searchText)
                    .addComponent(searchButton)
                )
                .addComponent(tableLabel)
                .addComponent(scrollPane)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(addButton)
                        .addComponent(deleteButton)
                )
        );
    }

    public AbstractViewPanel getContainer(){
        return this;
    }

    private void localInitialization(){
        structureTableModel.addRow(controller.getStructureFileList());
        structureFileTable.removeColumn(structureFileTable.getColumnModel().getColumn(0)); //hide column ID

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText.getText()));
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
                List selectedData = new ArrayList();

                for(int row = 0; row < structureFileTable.getRowCount(); row++){
                    Boolean checked = (Boolean) structureFileTable.getValueAt(row,5);
                    if(checked){
                        Object[] data = structureTableModel.getRowData(row);
                       selectedData.add(data);
                    }
                }

                controller.doDelete(selectedData);
                structureTableModel.deleteRow(selectedData);
            }
        });
        structureFileTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTable table = (JTable) e.getSource();
                if(e.getClickCount() == 2){
                    int row = table.getSelectedRow();
                    controller.doDetail(getContainer(), (String) structureTableModel.getValueAt(row,0), (String)structureTableModel.getValueAt(row,1));
                }
            }
        });
    }

}
