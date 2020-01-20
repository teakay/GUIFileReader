package com.wirecard.filestructure.gui.view;

import com.wirecard.filestructure.gui.controller.BaseController;

import javax.swing.*;
import java.awt.*;

public abstract class BaseView extends JPanel {
    private BaseController controller;

    public BaseView(){
        this.initialize();
    }

    public abstract void initialize();


    public void registerController(BaseController baseController){
        this.controller = baseController;
        this.setController(baseController);
    }

    public abstract void setController(BaseController baseController);

    public BaseController getController(){
        return this.controller;
    }

    public Object getAllComponent(){
        Component[] component = this.getComponents();
        return component;
    }
}
