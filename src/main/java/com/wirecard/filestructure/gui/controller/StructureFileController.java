package com.wirecard.filestructure.gui.controller;

import com.wirecard.filestructure.gui.service.StructureFileService;
import com.wirecard.filestructure.gui.view.AbstractViewPanel;
import com.wirecard.filestructure.gui.view.AddStructureFileView;
import com.wirecard.filestructure.gui.view.MainFrame;
import com.wirecard.filestructure.gui.view.StructureFileViewPanel;

import javax.swing.*;
import java.util.List;

public class StructureFileController extends AbstractController {

    public static final String DATA_TABLE_SEARCH = "Search";
    public static final String DATA_ADD = "Add";
    public static final String DATA_DELETE = "Delete";

//    public void doSearch(String query){
//        setModelProperty(DATA_TABLE_SEARCH,query);
//    }

    public void doAdd(AbstractViewPanel view){
        removeView(view);

        AddStructureFileView addView = new AddStructureFileView(this);
        addView(addView);

        MainFrame mainFrame = (MainFrame) SwingUtilities.getRoot(view);
        mainFrame.setContent(addView);
    }

    public void doBack(AbstractViewPanel view){
        removeView(view);
        StructureFileViewPanel structureView = new StructureFileViewPanel(this);
        addView(structureView);

        MainFrame mainFrame = (MainFrame) SwingUtilities.getRoot(view);
        mainFrame.setContent(structureView);
    }

    public void doDelete(Object[] data){
        StructureFileService.deleteById((String)data[0]);
    }

    public List getStructureFileList(){
        return StructureFileService.getStructureFileList();
    }

}
