package com.wirecard.filestructure.gui.view;

import com.wirecard.filestructure.gui.controller.ParserController;
import com.wirecard.filestructure.gui.entity.StructureFile;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class ParserView extends AbstractViewPanel {

    private JLabel titleLabel;
    private JComboBox structureList;
    private JTextField fileNameTextField;
    private JButton browserFileButton;
    private JButton parseFileButton;
    private JTable headerTable;
    private JTable detailTable;
    private JButton addButton;
    private JButton deleteButton;
    private JTable footerTable;
    private JButton saveAsTemplateButton;
    private JButton saveFileButton;
    private ParserController controller;
    private File selectedFile;

    public ParserView(ParserController controller){
        this.controller = controller;
        initComponent();
        localInitialization();
    }

    private void initComponent(){
        titleLabel = new JLabel("Parse File");
        titleLabel.setFont(new Font("Arial",Font.BOLD,18));
        titleLabel.setOpaque(true);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBackground(Color.GRAY);

        JLabel chooseStructureLabel = new JLabel();
        chooseStructureLabel.setText("Structure");
        structureList = new JComboBox();

        JLabel chooseFileLabel = new JLabel();
        chooseFileLabel.setText("File");
        fileNameTextField = new JTextField(20);
        browserFileButton = new JButton();
        browserFileButton.setText("Browse");

        parseFileButton = new JButton();
        parseFileButton.setText("Start Parsing");

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
        headerTable.setFillsViewportHeight(true);
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

        addButton  = new JButton();
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
        footerTable.setFillsViewportHeight(true);
        JScrollPane footerScrollPane = new JScrollPane(footerTable);

        saveAsTemplateButton = new JButton();
        saveAsTemplateButton.setText("Save as Template");

        saveFileButton = new JButton();
        saveFileButton.setText("Save File");

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(titleLabel, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(chooseStructureLabel)
                            .addComponent(chooseFileLabel)
                    )
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(structureList,0,100,200)
                            .addGroup(layout.createSequentialGroup()
                                    .addComponent(fileNameTextField,0,100,200)
                                    .addComponent(browserFileButton)
                            )
                    )
                )
                .addComponent(parseFileButton)
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
                .addGroup(layout.createSequentialGroup()
                    .addComponent(saveAsTemplateButton)
                    .addComponent(saveFileButton)
                )
            )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(titleLabel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(chooseStructureLabel)
                    .addComponent(structureList)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(chooseFileLabel)
                        .addComponent(fileNameTextField)
                        .addComponent(browserFileButton)
                )
                .addComponent(parseFileButton)
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
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(saveAsTemplateButton)
                        .addComponent(saveFileButton)
                )
        );


    }
    private void localInitialization(){
        try {
            List<StructureFile> list = controller.getStructureList();
            structureList.addItem("- Select -");
            for (int i = 0; i < list.size(); i++) {
                StructureFile sf = (StructureFile) list.get(i);
                structureList.addItem(sf.getStructureName());
            }
        }catch (Exception ex){
            displayErrorMessage(ex);
        }

        browserFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                int returnValue = jfc.showOpenDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    selectedFile = jfc.getSelectedFile();
                    fileNameTextField.setText(selectedFile.getAbsolutePath());
                }
            }
        });

        parseFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (isDataValid()) {
                        String structureName = (String) structureList.getSelectedItem();
                        Map columnMap = controller.getColumnFromStructure(structureName);

                        Map parseMap = controller.parseFile(fileNameTextField.getText(), columnMap);

                        DefaultTableModel headerModel = new DefaultTableModel();
                        List headerList = (List) columnMap.get("header");
                        for (int i = 0; i < headerList.size(); i++) {
                            headerModel.addColumn(headerList.get(i));
                        }
                        headerModel.addRow((Vector) parseMap.get("header"));
                        headerTable.setModel(headerModel);
                        if (headerModel.getColumnCount() > 8) {
                            headerTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                        } else {
                            headerTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                        }

                        DefaultTableModel detailModel = new DefaultTableModel();
                        List detailList = (List) columnMap.get("detail");
                        for (int i = 0; i < detailList.size(); i++) {
                            detailModel.addColumn(detailList.get(i));
                        }
                        List detailDataList = (List) parseMap.get("detail");
                        for (int n = 0; n < detailDataList.size(); n++) {
                            detailModel.addRow((Vector) detailDataList.get(n));
                        }
                        detailTable.setModel(detailModel);
                        if (detailModel.getColumnCount() > 8) {
                            detailTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                        } else {
                            detailTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                        }

                        DefaultTableModel footerModel = new DefaultTableModel();
                        List footerList = (List) columnMap.get("footer");
                        for (int i = 0; i < footerList.size(); i++) {
                            footerModel.addColumn(footerList.get(i));
                        }
                        footerModel.addRow((Vector) parseMap.get("footer"));
                        footerTable.setModel(footerModel);
                        if (footerModel.getColumnCount() > 8) {
                            footerTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                        } else {
                            footerTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                        }
                    }
                }catch(Exception ex){
                    displayErrorMessage(ex);
                }
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector vector = new Vector();
                for(int i = 0; i < detailTable.getModel().getColumnCount(); i++)
                {
                    vector.add(null);
                }
                ((DefaultTableModel) detailTable.getModel()).addRow(vector);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               removeRow(detailTable);
            }
        });

        saveAsTemplateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isDataValid() && isParsingDataNotEmpty()) {
                    if (headerTable.isEditing()) {
                        headerTable.getCellEditor().stopCellEditing();
                    }
                    if (detailTable.isEditing()) {
                        detailTable.getCellEditor().stopCellEditing();
                    }
                    if (footerTable.isEditing()) {
                        footerTable.getCellEditor().stopCellEditing();
                    }
                    String templateName = JOptionPane.showInputDialog("Enter Template Name :");
                    try {
                        controller.saveAsTemplate((String) structureList.getSelectedItem(), headerTable, detailTable, footerTable, templateName);

                        MainFrame mainFrame = (MainFrame) SwingUtilities.getRoot(parseFileButton);
                        JOptionPane.showMessageDialog(mainFrame,
                                "Successfully saved as template",
                                "Success",
                                JOptionPane.PLAIN_MESSAGE);

                    }catch (Exception ex){
                        displayErrorMessage(ex);
                    }
                }
                controller.goToTemplateView(getContainer());
            }
        });

        saveFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isDataValid() && isParsingDataNotEmpty()) {
                    if (headerTable.isEditing()) {
                        headerTable.getCellEditor().stopCellEditing();
                    }
                    if (detailTable.isEditing()) {
                        detailTable.getCellEditor().stopCellEditing();
                    }
                    if (footerTable.isEditing()) {
                        footerTable.getCellEditor().stopCellEditing();
                    }

                    JFrame parentFrame = new JFrame();
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Specify a file to Save");

                    int userSelection = fileChooser.showSaveDialog(parentFrame);
                    String filePath = "";
                    if (userSelection == JFileChooser.APPROVE_OPTION) {
                        File fileToSave = fileChooser.getSelectedFile();
                        filePath = fileToSave.getAbsolutePath();
                    }
                    try {
                        controller.saveAsFile((String) structureList.getSelectedItem(), headerTable, detailTable, footerTable, filePath);

                    }catch (Exception ex){
                        displayErrorMessage(ex);
                    }
                }
            }
        });
    }

    private boolean isDataValid(){
        boolean isDataValid =  false;

        if(structureList.getSelectedItem().equals("- Select -")){
            MainFrame mainFrame = (MainFrame) SwingUtilities.getRoot(parseFileButton);
            JOptionPane.showMessageDialog(mainFrame,
                    "Please Choose Structure",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }else if(fileNameTextField.getText().equals("") ) {
            MainFrame mainFrame = (MainFrame) SwingUtilities.getRoot(parseFileButton);
            JOptionPane.showMessageDialog(mainFrame,
                    "File Must Not Be Empty",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);

        }else{
            isDataValid = true;
        }
        return isDataValid;
    }

    private boolean isParsingDataNotEmpty(){
        boolean isDataValid =  false;
        if(headerTable.getModel().getRowCount() == 0 || detailTable.getModel().getRowCount() == 0 || footerTable.getModel().getRowCount() == 0) {
            MainFrame mainFrame = (MainFrame) SwingUtilities.getRoot(parseFileButton);
            JOptionPane.showMessageDialog(mainFrame,
                    "Please Fill data",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }else{
            isDataValid = true;
        }
        return  isDataValid;
    }
}
