package com.wirecard.filestructure.gui.view;

import com.wirecard.filestructure.gui.controller.StructureFileController;

import java.beans.PropertyChangeEvent;

public class DetailStructureFileView extends  AbstractViewPanel  {

    private StructureFileController controller;

    public DetailStructureFileView(StructureFileController controller){
        this.controller = controller;

        initComponent();
        localInitialization();
    }

    public void initComponent(){

    }

    public void localInitialization(){

    }

    @Override
    public void modelPropertyChange(PropertyChangeEvent evt) {

    }
}
