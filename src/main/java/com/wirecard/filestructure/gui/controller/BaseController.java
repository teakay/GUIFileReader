package com.wirecard.filestructure.gui.controller;

import com.wirecard.filestructure.gui.view.BaseView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class BaseController implements ActionListener {

    BaseView activeView;
    public BaseController(){
    }

    public void setActiveView(BaseView activeView){
        this.activeView = activeView;
    }

    public BaseView getActiveView(){
        return this.activeView;
    }

    public void actionPerformed(ActionEvent e) {
        this.doAction(e);
    }

    public abstract void doAction(ActionEvent e);

}
