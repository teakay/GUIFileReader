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

            templateTable = new JTable();

            deleteButton =  new JButton();
            deleteButton.setText("Delete");

    }

    protected void localInitialization(){

    }
}
