package com.wirecard.filestructure.gui.controller;

import com.wirecard.filestructure.gui.model.FileStructure;
import com.wirecard.filestructure.gui.model.FileStructureTableModel;

import java.awt.event.ActionEvent;

public class FileStructureController extends BaseController {
    private FileStructureTableModel tableModel;
    public FileStructureController(){
        this.tableModel = new FileStructureTableModel();
    }

    public void doSearch(){

        System.out.println("Search file structure : "+this.tableModel.getValueAt(0,0));
    }
    public void doAdd(){
        System.out.println("Add file structure : "+this.tableModel.getValueAt(0,1));
    }
    public void doDelete(){
        System.out.println("Delete file structure : "+this.tableModel.getValueAt(0,2));
    }

    @Override
    public void doAction(ActionEvent e) {
        if("Search".equals(e.getActionCommand())){
            doSearch();
        }else if("Delete".equals(e.getActionCommand())){
            doDelete();
        }else if("Add".equals(e.getActionCommand())){
            doAdd();
        }
    }
}
