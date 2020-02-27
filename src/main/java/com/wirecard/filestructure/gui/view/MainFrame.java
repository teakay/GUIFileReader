package com.wirecard.filestructure.gui.view;

import com.wirecard.filestructure.gui.controller.MainController;
import com.wirecard.filestructure.gui.utils.Constants;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import java.awt.*;

public class MainFrame extends JFrame {
    private ListModel listModel;
    private Component currentContent;
    private JList menuList;
    private JScrollPane menuScrollPane;
    private JScrollPane contentScrollPane;
    private JPanel contentPanel;
    private JSplitPane splitPane;

    public MainFrame() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        contentScrollPane = new JScrollPane();
        contentPanel = new JPanel();

        initMenu();

        JLabel label = new JLabel();
        label.setName("ContentLabel");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setText("Please select menu to the left to start");

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,menuScrollPane,currentContent);

        setContentPanel(label);

        add(splitPane);
        setMinimumSize(new Dimension(800,600));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("PCash File Structure Parser and Creator");
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        setDefaultLookAndFeelDecorated(true);
    }

    private void initMenu(){
        menuList = new JList(Constants.MENU);
        menuList.setName("menuList");
        menuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        menuList.addListSelectionListener(new MainController());
        menuScrollPane = new JScrollPane(menuList);
    }

    public void setContentPanel(Component component){
        if(currentContent != null){
            splitPane.remove(1);
        }else {
            currentContent = component;
        }
        splitPane.setRightComponent(component);
        splitPane.revalidate();
        splitPane.repaint();


    }

    public void setContentScrollPane(Component component){
        currentContent = component;
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().add(component);

        if(currentContent != null){
            splitPane.remove(1);
        }else {
            currentContent = component;
        }
        splitPane.setRightComponent(scrollPane);
        splitPane.revalidate();
        splitPane.repaint();
    }
}
