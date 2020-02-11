package com.wirecard.filestructure.gui.controller;

import com.wirecard.filestructure.gui.model.StructureFileModel;
import com.wirecard.filestructure.gui.utils.Constants;
import com.wirecard.filestructure.gui.view.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainController implements ListSelectionListener {

    public void valueChanged(ListSelectionEvent e) {

        if(e.getValueIsAdjusting()) {
            JList list = (JList) e.getSource();
            String selectedMenu = Constants.MENU[list.getSelectedIndex()];

            MainFrame mainFrame = (MainFrame) SwingUtilities.getRoot(list);

            if (Constants.MENU[0].equals(selectedMenu)) {
                StructureFileController controller = new StructureFileController();
                StructureFileViewPanel view = new StructureFileViewPanel(controller);
                controller.addView(view);
                controller.addModel(new StructureFileModel());

                mainFrame.setContent(view);

            } else if (Constants.MENU[1].equals(selectedMenu)) {
                TemplateView templateView = new TemplateView();
                mainFrame.setContent(templateView);
            } else if (Constants.MENU[2].equals(selectedMenu)) {
                ParserView parserView = new ParserView();
                mainFrame.setContent(parserView);
            } else if (Constants.MENU[3].equals(selectedMenu)) {
                FileCreatorView fileCreatorView = new FileCreatorView();
                mainFrame.setContent(fileCreatorView);
            } else {
                JLabel label = new JLabel();
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setText("This is content panel");

                mainFrame.setContent(label);
            }
        }
    }

}