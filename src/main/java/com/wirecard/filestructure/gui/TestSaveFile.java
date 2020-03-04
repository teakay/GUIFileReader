package com.wirecard.filestructure.gui;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TestSaveFile {

    public static void justForTest(String[] args){
        JFrame parentFrame = new JFrame();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to Save");

        int userSelection = fileChooser.showSaveDialog(parentFrame);

        if(userSelection == JFileChooser.APPROVE_OPTION){
            File fileToSave = fileChooser.getSelectedFile();
            System.out.println("Save as file : " + fileToSave.getAbsolutePath() );
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave.getAbsolutePath()));
                int pad = 20;
                String hello = "Hello";
                String pad1st = String.format("%-"+pad+"s","First line");
                String pad2nd = String.format("%"+pad+"s","Second Line");
                StringBuffer strBuffer = new StringBuffer();
                strBuffer.append(hello).append("\n");
                strBuffer.append(pad1st).append("\n");
                strBuffer.append(pad2nd).append("\n");

                writer.write(strBuffer.toString());
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
