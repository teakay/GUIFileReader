package com.wirecard.filestructure.gui.controller;

import com.wirecard.filestructure.gui.model.BaseModel;
import com.wirecard.filestructure.gui.utils.Constants;
import com.wirecard.filestructure.gui.view.MainFrame;
import com.wirecard.filestructure.gui.view.MainViewPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.lang.reflect.Method;
import java.util.List;

public class MainController implements ListSelectionListener {

    public void valueChanged(ListSelectionEvent e) {
        JList list = (JList) e.getSource();
        String selectedMenu = Constants.MENU[list.getSelectedIndex()];

        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setText(selectedMenu);

        MainFrame mainFrame = (MainFrame)SwingUtilities.getRoot(list);
        mainFrame.setContent(label); //TODO : set to each related view (structure file, template, parser, creator)
        //TODO : create new controller for each view and related model. Register model to each controller

    }

}