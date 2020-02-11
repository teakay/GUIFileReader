package com.wirecard.filestructure.gui.model;

import com.wirecard.filestructure.gui.utils.Constants;

import javax.swing.table.AbstractTableModel;

public class InstructionFileTableModel extends AbstractTableModel {
    private String[] columnNames  = Constants.INSTRUCTION_FILE_TABLE_COLUMN;
    private Object[][] data = {{null,null,null,null,null}};

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
            return Integer.class;
        }else if(col == 1){
            return String.class;
        }else if(col == 2){
            return String.class;
        }else if(col == 3){
            return Integer.class;
        }else if(col == 4){
            return Boolean.class;
        }
        else{
            return String.class;
        }
    }

    public void addRow(Object[][] data){
    }
}
