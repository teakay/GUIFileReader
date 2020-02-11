package com.wirecard.filestructure.gui.controller;

import com.wirecard.filestructure.gui.view.AbstractViewPanel;
import com.wirecard.filestructure.gui.view.AddStructureFileView;
import com.wirecard.filestructure.gui.view.MainFrame;
import com.wirecard.filestructure.gui.view.StructureFileViewPanel;

import javax.swing.*;
import java.awt.event.ActionListener;

public class StructureFileController extends AbstractController {

    public static final String DATA_TABLE_SEARCH = "Search";
    public static final String DATA_ADD = "Add";

    public void doSearch(String query){
//        System.out.println("Query search : "+query);
        setModelProperty(DATA_TABLE_SEARCH,query);
    }

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

}
