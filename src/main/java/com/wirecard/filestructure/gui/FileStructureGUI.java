package com.wirecard.filestructure.gui;

import com.wirecard.filestructure.gui.controller.BaseController;
import com.wirecard.filestructure.gui.controller.MainController;
import com.wirecard.filestructure.gui.model.BaseModel;
import com.wirecard.filestructure.gui.view.MainViewPanel;

import javax.swing.*;

public class FileStructureGUI {
    public static void main(String[] args){

        BaseModel baseModel = new BaseModel();
        MainController controller = new MainController(baseModel);

        MainViewPanel mainView = new MainViewPanel();
        mainView.registerController(controller);

        JFrame mainFrame = new JFrame("File Structure Reader GUI");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.getContentPane().add(mainView.getSplitPane());
        mainFrame.pack();
        mainFrame.setVisible(true);


    }
}
