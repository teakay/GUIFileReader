package com.wirecard.filestructure.gui.view;

import com.wirecard.filestructure.gui.controller.AbstractController;
import com.wirecard.filestructure.gui.controller.FileCreatorController;
import com.wirecard.filestructure.gui.entity.StructureFile;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class FileCreatorView extends AbstractViewPanel {
    // top : pilihan use template or use structure (isi dari nol)
    // down : - generate table sesuai template / structure yg di pilih
    //        - save as file

    private FileCreatorController controller;
    private JComboBox templateComboBox;
    private JComboBox structureComboBox;
    private JButton startButton;
    private JButton addButton;
    private JButton deleteButton;
    private JButton saveFileButton;
    private JTable headerTable;
    private JTable detailTable;
    private JTable footerTable;
    private DefaultTableModel headerModel;
    private DefaultTableModel detailModel;
    private DefaultTableModel footerModel;

    public FileCreatorView(FileCreatorController controller){
        this.controller = controller;
        initComponent();
        localInitialization();
    }

    public void initComponent(){
        JLabel titleLabel = new JLabel();
        titleLabel.setText("Create File");
        titleLabel.setOpaque(true);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBackground(Color.GRAY);
        titleLabel.setFont(new Font("Arial",Font.BOLD,18));

        JLabel chooseTemplateLabel = new JLabel();
        chooseTemplateLabel.setText("Template Data");
        templateComboBox = new JComboBox();

        JLabel chooseStructureLabel = new JLabel();
        chooseStructureLabel.setText("Structure");
        structureComboBox = new JComboBox();

        startButton = new JButton();
        startButton.setText("Start");

        JLabel headerLabel = new JLabel();
        headerLabel.setText("Header");
        headerLabel.setOpaque(true);
        headerLabel.setBackground(Color.LIGHT_GRAY);

        headerTable = new JTable(){
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);
                int rendererWidth = component.getPreferredSize().width;
                TableColumn tableColumn = getColumnModel().getColumn(column);
                tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
                return component;
            }
        };
        JScrollPane headerScrollPane = new JScrollPane(headerTable);

        JLabel detailLabel = new JLabel();
        detailLabel.setText("Detail");
        detailLabel.setOpaque(true);
        detailLabel.setBackground(Color.LIGHT_GRAY);

        detailTable = new JTable(){
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);
                int rendererWidth = component.getPreferredSize().width;
                TableColumn tableColumn = getColumnModel().getColumn(column);
                tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
                return component;
            }
        };
        JScrollPane detailScrollPane = new JScrollPane(detailTable);

        addButton = new JButton();
        addButton.setText("Add");

        deleteButton = new JButton();
        deleteButton.setText("Delete");

        JLabel footerLabel = new JLabel();
        footerLabel.setText("Footer");
        footerLabel.setOpaque(true);
        footerLabel.setBackground(Color.LIGHT_GRAY);

        footerTable = new JTable(){
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);
                int rendererWidth = component.getPreferredSize().width;
                TableColumn tableColumn = getColumnModel().getColumn(column);
                tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
                return component;
            }
        };
        JScrollPane footerScrollPane = new JScrollPane(footerTable);

        saveFileButton = new JButton();
        saveFileButton.setText("Save File");

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(titleLabel,0,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(chooseStructureLabel)
                            .addComponent(chooseTemplateLabel)
                    )
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(structureComboBox,0,100,200)
                            .addComponent(templateComboBox,0,100,200)
                    )
                )
                    .addComponent(startButton)
                    .addComponent(headerLabel,0,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
                    .addComponent(headerScrollPane)
                    .addComponent(detailLabel,0,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
                    .addComponent(detailScrollPane)
                    .addGroup(layout.createSequentialGroup()
                            .addComponent(addButton)
                            .addComponent(deleteButton)
                    )
                    .addComponent(footerLabel,0,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
                    .addComponent(footerScrollPane)
                    .addComponent(saveFileButton)
            )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(titleLabel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(chooseStructureLabel)
                        .addComponent(structureComboBox)
                )

                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(chooseTemplateLabel)
                        .addComponent(templateComboBox)
                )

                .addComponent(startButton)
                .addComponent(headerLabel)
                .addComponent(headerScrollPane)
                .addComponent(detailLabel)
                .addComponent(detailScrollPane)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(addButton)
                        .addComponent(deleteButton)
                )
                .addComponent(footerLabel)
                .addComponent(footerScrollPane)
                .addComponent(saveFileButton)
        );
    }
    public void localInitialization(){
        List<StructureFile> list = controller.getStructureList();
        structureComboBox.addItem("- Select -");
        for(int i = 0; i < list.size(); i++){
            StructureFile sf = (StructureFile) list.get(i);
            structureComboBox.addItem(sf.getStructureName());
        }

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String structureName = (String)structureComboBox.getSelectedItem();

                Map columnMap = controller.getColumnFromStructure(structureName);

                headerModel = new DefaultTableModel();
                List headerList = (List)columnMap.get("header");
                Vector rowDataHeader = new Vector();
                for(int i = 0; i < headerList.size(); i++) {
                    headerModel.addColumn(headerList.get(i));
                    rowDataHeader.add(null);
                }
                headerModel.addRow(rowDataHeader);
                headerTable.setModel(headerModel);
                if(headerModel.getColumnCount() > 8) {
                    headerTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                }else {
                    headerTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                }

                detailModel = new DefaultTableModel();
                List detailList = (List)columnMap.get("detail");
                Vector rowDataDetail = new Vector();
                for(int i = 0; i < detailList.size(); i++) {
                    detailModel.addColumn(detailList.get(i));
                    rowDataDetail.add(null);
                }
                detailModel.addRow(rowDataDetail);
                detailTable.setModel(detailModel);
                if(detailModel.getColumnCount() > 8) {
                    detailTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                }else {
                    detailTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                }

                footerModel = new DefaultTableModel();
                List footerList = (List)columnMap.get("footer");
                Vector rowDataFooter = new Vector();
                for(int i = 0; i < footerList.size(); i++) {
                    footerModel.addColumn(footerList.get(i));
                    rowDataFooter.add(null);
                }
                footerModel.addRow(rowDataFooter);
                footerTable.setModel(footerModel);
                if(footerModel.getColumnCount() > 8) {
                    footerTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                }else {
                    footerTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                }
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int col = detailModel.getColumnCount();
                Vector dataRow = new Vector(col);
                detailModel.addRow(dataRow);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               int[] row = detailTable.getSelectedRows();
               for(int i = 0; i <= row.length; i++){
                   detailModel.removeRow(row[i] - 1);
               }
            }
        });

        saveFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(headerTable.isEditing()){
                    headerTable.getCellEditor().stopCellEditing();
                }
                if(detailTable.isEditing()){
                    detailTable.getCellEditor().stopCellEditing();
                }
                if(footerTable.isEditing()){
                    footerTable.getCellEditor().stopCellEditing();
                }

                JFrame parentFrame = new JFrame();
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Specify a file to Save");

                int userSelection = fileChooser.showSaveDialog(parentFrame);
                String filePath = "";
                if(userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    filePath = fileToSave.getAbsolutePath();
                }
                controller.saveAsFile((String)structureComboBox.getSelectedItem(),headerTable, detailTable, footerTable, filePath);
            }
        });
    }
}
