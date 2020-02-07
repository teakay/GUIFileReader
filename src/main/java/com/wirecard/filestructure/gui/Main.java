package com.wirecard.filestructure.gui;

import com.wirecard.filestructure.gui.controller.DefaultController;
import com.wirecard.filestructure.gui.model.DocumentModel;
import com.wirecard.filestructure.gui.model.TextElementModel;
import com.wirecard.filestructure.gui.view.PropertiesViewPanel;

import javax.swing.*;
import java.awt.*;

public class Main {

    /** Creates a new instance of Main */
    public Main() {

        // Create models
        TextElementModel textElementModel = new TextElementModel();
        DocumentModel documentModel = new DocumentModel();

        // Create controller
        DefaultController controller = new DefaultController();

        // Create view panels and connect them to controller
       // DisplayViewPanel displayViewPanel = new DisplayViewPanel(controller);
        PropertiesViewPanel propertiesViewPanel = new PropertiesViewPanel(controller);

        // Register views and models with controller
        //controller.addView(displayViewPanel);
        controller.addView(propertiesViewPanel);
        controller.addModel(textElementModel);
        controller.addModel(documentModel);

        // Set up test data for models
        textElementModel.initDefault();
        documentModel.initDefault();

        // Create GUI objects
        JFrame displayFrame = new JFrame("Display (View 1)");
       // displayFrame.getContentPane().add(displayViewPanel, BorderLayout.CENTER);
        displayFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        displayFrame.pack();

        JDialog propertiesDialog = new JDialog(displayFrame, "Properties (View 2)");
        propertiesDialog.setModal(false);
        propertiesDialog.getContentPane().add(propertiesViewPanel, BorderLayout.CENTER);
        propertiesDialog.pack();

        // Show GUI objects
        displayFrame.setVisible(true);
        propertiesDialog.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Main();
    }

}
