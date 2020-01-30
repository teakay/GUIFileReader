package com.wirecard.filestructure.gui.view;

import com.wirecard.filestructure.gui.controller.MainController;
import com.wirecard.filestructure.gui.utils.Constants;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private ListModel listModel;
    private Component currentContent;
    private JList menuList;
    private JScrollPane menuScrollPane;
    private JScrollPane contentScrollPane;
    private JSplitPane splitPane;

    public MainFrame(){
        contentScrollPane = new JScrollPane();

        //TODO : if banner needed
        //BufferedImage myPicture = ImageIO.read(new File("path-to-file"));
        //JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        //add(picLabel);

        initMenu();

        JLabel label = new JLabel();
        label.setName("ContentLabel");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setText("Please select menu to the left to start");

        setContent(label);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,menuScrollPane,contentScrollPane);

        add(splitPane);
        setSize(605,660);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("File Structure Parser and Creator");

    }

    private void initMenu(){
        menuList = new JList(Constants.MENU);
        menuList.setName("menuList");
        menuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        menuList.addListSelectionListener(new MainController());
        //menuList.setSelectedIndex(0);
        menuScrollPane = new JScrollPane(menuList);
    }

    public void setContent(Component component){
        if(currentContent != null){
            contentScrollPane.remove(currentContent);
        }
        currentContent = component;
        contentScrollPane.getViewport().add(component);
        contentScrollPane.revalidate();
        contentScrollPane.repaint();
    }
}
