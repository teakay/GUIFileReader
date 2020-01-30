package com.wirecard.filestructure.gui.controller;

import com.wirecard.filestructure.gui.model.FileStructure;
import com.wirecard.filestructure.gui.model.FileStructureTableModel;

public class FileStructureController extends BaseController {
    private FileStructureTableModel model;

    public FileStructureController(){
        this.model = new FileStructureTableModel();
    }
//    public FileStructureController(Object model) {
//        super(model);
//    }
    public void doSearch(){

        System.out.println("Search file structure : "+this.model.getValueAt(0,0));
    }
    public void doAdd(){
        System.out.println("Add file structure : "+this.model.getValueAt(0,1));
    }
    public void doDelete(){
        System.out.println("Delete file structure : "+this.model.getValueAt(0,2));
    }
}
