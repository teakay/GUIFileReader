package com.wirecard.filestructure.gui.model;

import com.wirecard.filestructure.gui.controller.StructureFileController;

public class StructureFileModel extends AbstractModel {

    private String text;

    public String getText(){
        return this.text;
    }
    public void setSearch(String text){
        String oldSearch = this.text;
        this.text = text;

        firePropertyChange(StructureFileController.DATA_TABLE_SEARCH, oldSearch, text);
    }
}
