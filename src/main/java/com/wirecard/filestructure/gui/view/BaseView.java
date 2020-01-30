package com.wirecard.filestructure.gui.view;

import com.wirecard.filestructure.gui.controller.BaseController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseView extends JPanel {
    private BaseController controller;
    private List<Component> listComponent;

    public BaseView()
    {
        this.initialize();
    }

    public abstract void initialize();


    public void registerController(BaseController baseController){
        baseController.setActiveView(this);
        this.controller = baseController;
    }

//    public abstract void setController(BaseController baseController);

    public BaseController getController(){
        return this.controller;
    }

    public void addComponent(Component component){
        this.listComponent.add(component);

    }

    public List<Component>  getAllComponents(){
        return this.listComponent;
    }

    public void clearComponent(){
        this.listComponent = new ArrayList<Component>();
    }

}
