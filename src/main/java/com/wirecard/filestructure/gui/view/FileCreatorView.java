package com.wirecard.filestructure.gui.view;

import com.wirecard.filestructure.gui.controller.AbstractController;
import com.wirecard.filestructure.gui.controller.FileCreatorController;
import com.wirecard.filestructure.gui.entity.StructureFile;
import com.wirecard.filestructure.gui.entity.Template;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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


        templateComboBox.addItem("- Select -");

        structureComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String selectedStructure = (String) e.getItem();
                if(!selectedStructure.equals("- Select -")) {

                    templateComboBox.removeAllItems();
                    templateComboBox.addItem("- Select -");

                    List<Template> templateList = controller.getTemplateListByStructure(selectedStructure);
                    for (int i = 0; i < templateList.size(); i++) {
                        Template template = (Template) templateList.get(i);
                        templateComboBox.addItem(template.getName());
                    }
                }

            }
        });
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(structureComboBox.getSelectedItem().equals("- Select -")) {
                    MainFrame mainFrame = (MainFrame) SwingUtilities.getRoot(startButton);
                    JOptionPane.showMessageDialog(mainFrame,
                            "Please Select Structure",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }else {
                    String structureName = (String) structureComboBox.getSelectedItem();
                    String templateName = (String) templateComboBox.getSelectedItem();

                    Map columnMap = controller.getColumnFromStructure(structureName);
                    Map dataMap = controller.getTemplateDetailByName(templateName);
                    List headerDataList = (List)dataMap.get("header");
                    List detailDataList = (List)dataMap.get("detail");
                    List footerDataList = (List)dataMap.get("footer");

                    headerModel = new DefaultTableModel();
                    List headerList = (List) columnMap.get("header");
                    Vector rowDataHeader = new Vector();
                    for (int i = 0; i < headerList.size(); i++) {
                        headerModel.addColumn(headerList.get(i));
                        rowDataHeader.add(null);
                    }
                    if(dataMap.isEmpty()) {
                        headerModel.addRow(rowDataHeader);
                    }else{
                        for(int i = 0; i < headerDataList.size(); i++){
                            headerModel.addRow((Vector)headerDataList.get(i));
                        }
                    }
                    headerTable.setModel(headerModel);
                    if (headerModel.getColumnCount() > 8) {
                        headerTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    } else {
                        headerTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                    }

                    detailModel = new DefaultTableModel();
                    List detailList = (List) columnMap.get("detail");
                    Vector rowDataDetail = new Vector();
                    for (int i = 0; i < detailList.size(); i++) {
                        detailModel.addColumn(detailList.get(i));
                        rowDataDetail.add(null);
                    }
                    if(dataMap.isEmpty()) {
                        detailModel.addRow(rowDataDetail);
                    }else{
                        for(int i = 0; i < detailDataList.size(); i++){
                            detailModel.addRow((Vector)detailDataList.get(i));
                        }
                    }
                    detailTable.setModel(detailModel);
                    if (detailModel.getColumnCount() > 8) {
                        detailTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    } else {
                        detailTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                    }

                    footerModel = new DefaultTableModel();
                    List footerList = (List) columnMap.get("footer");
                    Vector rowDataFooter = new Vector();
                    for (int i = 0; i < footerList.size(); i++) {
                        footerModel.addColumn(footerList.get(i));
                        rowDataFooter.add(null);
                    }
                    if(dataMap.isEmpty()) {
                        footerModel.addRow(rowDataFooter);
                    }else{
                        for(int i = 0; i < footerDataList.size(); i++){
                            footerModel.addRow((Vector)footerDataList.get(i));
                        }
                    }
                    footerTable.setModel(footerModel);
                    if (footerModel.getColumnCount() > 8) {
                        footerTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    } else {
                        footerTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                    }
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
                removeRow(detailTable);
            }
        });

        saveFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (headerTable.getModel().getRowCount() == 0 || detailTable.getModel().getRowCount() == 0 || footerTable.getModel().getRowCount() == 0) {
                    MainFrame mainFrame = (MainFrame) SwingUtilities.getRoot(saveFileButton);
                    JOptionPane.showMessageDialog(mainFrame,
                            "Please Fill data",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
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
                    controller.saveAsFile((String) structureComboBox.getSelectedItem(), headerTable, detailTable, footerTable, filePath);
                }
            }
        });
    }
}
