package com.wirecard.filestructure.gui.view;

import com.wirecard.filestructure.gui.controller.ParserController;
import com.wirecard.filestructure.gui.entity.StructureFile;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Map;

public class ParserView extends AbstractViewPanel {
    // top : - pilihan structure mana
    //       - pilihan file yg mau diparsing
    // down : - generate table sesuai structure yg di pilih
    //        - button save as template dan save in your directory

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
    private JButton backButton;
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
        chooseStructureLabel.setText("Choose Structure");
        structureList = new JComboBox();

        JLabel chooseFileLabel = new JLabel();
        chooseFileLabel.setText("Choose File");
        fileNameTextField = new JTextField(20);
        browserFileButton = new JButton();
        browserFileButton.setText("Browse");

        parseFileButton = new JButton();
        parseFileButton.setText("Start Parsing");

        JLabel headerLabel = new JLabel();
        headerLabel.setText("Header");
        headerLabel.setOpaque(true);
        headerLabel.setBackground(Color.LIGHT_GRAY);

        headerTable = new JTable();
        headerTable.setFillsViewportHeight(true);
        JScrollPane headerScrollPane = new JScrollPane(headerTable);

        JLabel detailLabel = new JLabel();
        detailLabel.setText("Detail");
        detailLabel.setOpaque(true);
        detailLabel.setBackground(Color.LIGHT_GRAY);

        detailTable = new JTable();
        detailTable.setFillsViewportHeight(true);
        JScrollPane detailScrollPane = new JScrollPane(detailTable);

        JLabel footerLabel = new JLabel();
        footerLabel.setText("Footer");
        footerLabel.setOpaque(true);
        footerLabel.setBackground(Color.LIGHT_GRAY);

        footerTable = new JTable();
        footerTable.setFillsViewportHeight(true);
        JScrollPane footerScrollPane = new JScrollPane(footerTable);

        backButton = new JButton();
        backButton.setText("Back");

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
                .addComponent(footerLabel,0,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
                .addComponent(footerScrollPane)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(backButton)
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
                .addComponent(footerLabel)
                .addComponent(footerScrollPane)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(backButton)
                        .addComponent(saveAsTemplateButton)
                        .addComponent(saveFileButton)
                )
        );


    }
    private void localInitialization(){
        List<StructureFile> list = controller.getStructureList();
        for(int i = 0; i < list.size(); i++){
            StructureFile sf = (StructureFile) list.get(i);
            structureList.addItem(sf.getStructureName());
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
                String structureName = (String)structureList.getSelectedItem();
                Map columnMap = controller.getColumnFromStructure(structureName);
//                controller.parseFile();

                DefaultTableModel headerModel = new DefaultTableModel();
                List headerList = (List)columnMap.get("header");
                for(int i = 0; i < headerList.size(); i++) {
                    headerModel.addColumn(headerList.get(i));
                }
                headerTable.setModel(headerModel);

                DefaultTableModel detailModel = new DefaultTableModel();
                List detailList = (List)columnMap.get("detail");
                for(int i = 0; i < detailList.size(); i++) {
                    detailModel.addColumn(detailList.get(i));
                }
                detailTable.setModel(detailModel);

                DefaultTableModel footerModel = new DefaultTableModel();
                List footerList = (List)columnMap.get("footer");
                for(int i = 0; i < footerList.size(); i++) {
                    footerModel.addColumn(footerList.get(i));
                }
                footerTable.setModel(footerModel);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        saveAsTemplateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        saveFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
