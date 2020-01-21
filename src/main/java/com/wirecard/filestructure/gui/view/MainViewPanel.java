package com.wirecard.filestructure.gui.view;

import com.wirecard.filestructure.gui.controller.BaseController;
import com.wirecard.filestructure.gui.utils.Constants;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class MainViewPanel extends BaseView{
    private JList menuList;
    private JSplitPane splitPane;
    private JLabel label;

    public void setController(BaseController baseController) {
       menuList.addListSelectionListener(baseController);
    }

    public void initialize(){
        menuList = new JList(Constants.MENU);
        menuList.setName("menuList");
        menuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        menuList.setSelectedIndex(0);

        JScrollPane menuScrollPane = new JScrollPane(menuList);

        label = new JLabel();
        label.setName("ContentLabel");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setText("This is content panel");
        addComponent(label);

        JScrollPane contentScrollPane = new JScrollPane(label);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,menuScrollPane,contentScrollPane);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(150);

        menuScrollPane.setMinimumSize( new Dimension(100,50));
        contentScrollPane.setMinimumSize( new Dimension(200,50));

        splitPane.setMinimumSize(new Dimension(1000,200));
    }

    public JSplitPane getSplitPane(){
        return splitPane;
    }

    public void setContentLabel(String value){
        this.label.setText(value);
    }


}
