package com.wirecard.filestructure.gui.view;

import com.wirecard.filestructure.gui.controller.StructureFileController;
import com.wirecard.filestructure.gui.model.StructureTableModel;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
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
    private GridBagConstraints gbc;
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

        initComponent();
        localInitialization();
    }

    private void initComponent(){
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        int row = 0;

        JLabel titleLabel = new JLabel("List of created file structure");
        titleLabel.setFont(new Font("Arial",Font.BOLD,18));
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0,0,20,0);
        add(titleLabel,gbc);

        JLabel label = new JLabel(" ");
        gbc.gridx = 0;
        gbc.gridy = ++row;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0,80,10,80);
        add(label,gbc);

        searchText =  new JTextField(18);
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0,0,10,0);
        add(searchText,gbc);

        searchButton = new JButton();
        gbc.gridx = 2;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(0,0,10,0);
        searchButton.setText("Search");
        add(searchButton,gbc);

        structureFileTable = new JTable(structureTableModel);
        gbc.gridx = 0;
        gbc.gridy = ++row;
        gbc.ipadx = 0;
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

        JLabel label2 = new JLabel(" ");
        gbc.gridx = 0;
        gbc.gridy = ++row;
        gbc.gridwidth = 1;
        add(label2,gbc);

        addButton = new JButton();
        addButton.setText("Add");
        gbc.ipadx = 0;
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        add(addButton,gbc);

        deleteButton = new JButton();
        deleteButton.setText("Delete");
        gbc.gridx = 2;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        add(deleteButton,gbc);


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
                    Boolean checked = (Boolean) structureFileTable.getValueAt(row,3);
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
//                    System.out.println("row selected id : " + (String) structureTableModel.getValueAt(row,0));
                    controller.doDetail(getContainer(), (String) structureTableModel.getValueAt(row,0), (String)structureTableModel.getValueAt(row,1));
                }
            }
        });
    }

    @Override
    public void modelPropertyChange(PropertyChangeEvent evt) {

    }
}
