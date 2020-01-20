package com.wirecard.filestructure.gui;

import com.wirecard.filestructure.gui.controller.BaseController;
import com.wirecard.filestructure.gui.view.MainViewPanel;

import javax.swing.*;

public class FileStructureGUI {
    public static void main(String[] args){

        BaseController baseController = new BaseController();

        MainViewPanel mainView = new MainViewPanel();
        mainView.setController(baseController);

        JFrame mainFrame = new JFrame("File Structure Reader GUI");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.getContentPane().add(mainView.getSplitPane());
        mainFrame.pack();
        mainFrame.setVisible(true);


    }
}
