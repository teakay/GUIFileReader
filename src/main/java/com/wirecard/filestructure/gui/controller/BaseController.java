package com.wirecard.filestructure.gui.controller;

import com.wirecard.filestructure.gui.model.BaseModel;
import com.wirecard.filestructure.gui.utils.Constants;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BaseController implements ActionListener, ListSelectionListener {
    BaseModel baseModel;
    private boolean activeView;

    public BaseController(BaseModel model){
        this.baseModel = model;
    }

    public void setActiveView(boolean isActive){
        this.activeView = isActive;
    }

    public boolean getActiveview(){
        return this.activeView;
    }

    public void actionPerformed(ActionEvent e) {
        String methodAction = e.getActionCommand();

    }

    public void valueChanged(ListSelectionEvent e) {
        JList list = (JList) e.getSource();
        // label.setText(Constants.MENU[list.getSelectedIndex()]);
    }
}
