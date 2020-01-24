package com.wirecard.filestructure.gui.controller;

import com.wirecard.filestructure.gui.utils.Constants;
import com.wirecard.filestructure.gui.view.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainController implements ListSelectionListener {

    public void valueChanged(ListSelectionEvent e) {
        JList list = (JList) e.getSource();
        String selectedMenu = Constants.MENU[list.getSelectedIndex()];

        MainFrame mainFrame = (MainFrame)SwingUtilities.getRoot(list);

        //TODO : set to each related view (structure file, template, parser, creator)
        //TODO : create new controller for each view and related model. Register model to each controller
        if(Constants.MENU[0].equals(selectedMenu)){
            //structure file
            StructureFileView structureFileView = new StructureFileView();
            mainFrame.setContent(structureFileView);
        }else if(Constants.MENU[1].equals(selectedMenu)){
            //template
            TemplateView templateView = new TemplateView();
            mainFrame.setContent(templateView);
        }else if(Constants.MENU[2].equals(selectedMenu)){
            //parse file
            ParserView parserView = new ParserView();
            mainFrame.setContent(parserView);
        }else if(Constants.MENU[3].equals(selectedMenu)){
            //create file
            FileCreatorView fileCreatorView = new FileCreatorView();
            mainFrame.setContent(fileCreatorView);
        }else{
            JLabel label = new JLabel();
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setText("This is content panel");

            mainFrame.setContent(label);
        }
    }

}