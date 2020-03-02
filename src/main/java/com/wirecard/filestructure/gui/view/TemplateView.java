package com.wirecard.filestructure.gui.view;

import com.wirecard.filestructure.gui.controller.TemplateController;
import com.wirecard.filestructure.gui.entity.Template;

import javax.swing.*;
import java.awt.*;

public class TemplateView extends AbstractViewPanel {

    //view samain dengan StructureFileViewPanel
    // - list template
    // - detail template
    // - delete function
    private TemplateController controller;
    private JLabel searchLabel;
    private JTextField searchTextField;
    private JButton searchButton;
    private JTable templateTable;
    private JButton deleteButton;

    public TemplateView(TemplateController controller){
        this.controller = controller;

        initComponent();
        localInitialization();
    }

    protected void initComponent(){
            JLabel titleLabel = new JLabel("Template");
            titleLabel.setOpaque(true);
            titleLabel.setForeground(Color.WHITE);
            titleLabel.setBackground(Color.GRAY);

            searchLabel = new JLabel("Find what : ");
            searchTextField = new JTextField(20);
            searchButton = new JButton();
            searchButton.setText("Search");

            JLabel tableLabel = new JLabel();
            tableLabel.setText("Template List");
            tableLabel.setOpaque(true);
            tableLabel.setBackground(Color.LIGHT_GRAY);

            templateTable = new JTable();
            templateTable.setFillsViewportHeight(true);
            JScrollPane templateScrollPane = new JScrollPane(templateTable);

            deleteButton =  new JButton();
            deleteButton.setText("Delete");

            GroupLayout layout = new GroupLayout(this);
            setLayout(layout);
            layout.setAutoCreateGaps(true);
            layout.setAutoCreateContainerGaps(true);

            layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(titleLabel,0,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(searchLabel)
                                .addComponent(searchTextField,0,100,200)
                                .addComponent(searchButton)
                        )
                        .addComponent(tableLabel,0,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(templateScrollPane)
                        .addComponent(deleteButton)
                )
            );

            layout.setVerticalGroup(layout.createSequentialGroup()
                    .addComponent(titleLabel)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(searchLabel)
                            .addComponent(searchTextField)
                            .addComponent(searchButton)
                    )
                    .addComponent(tableLabel)
                    .addComponent(templateScrollPane)
                    .addComponent(deleteButton)
            );

    }

    protected void localInitialization(){

    }
}
