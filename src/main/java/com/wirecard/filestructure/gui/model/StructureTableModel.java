package com.wirecard.filestructure.gui.model;

import com.wirecard.filestructure.gui.utils.Constants;

import javax.swing.table.AbstractTableModel;

public class StructureTableModel extends AbstractTableModel {
    private String[] columnNames  = Constants.FILE_STRUCTURE_TABLE_COLUMN;
    private Object[][] data = Constants.FILE_STRUCTURE_DUMMY_DATA;

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
