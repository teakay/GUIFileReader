package com.wirecard.filestructure.gui.model;

import javax.swing.table.AbstractTableModel;

public class FileStructureTableModel extends AbstractTableModel {
    private String[] columnNames  = {"Structure name","File extension", "Created date"," "};
    private Object[][] data = {
            {"CTL File","txt","28-01-2020",false},
            {"Pay File","txt","30-01-2020",false}
    };

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    public String getColumnName(int col){
        return columnNames[col];
    }

    public Class getColumnClass(int col){
       if(col == 0){
           return String.class;
       }else if(col == 1){
           return String.class;
       }else if(col == 2){
           return String.class;
       }else if(col == 3){
           return Boolean.class;
       }else{
           return String.class;
       }

    }
}
