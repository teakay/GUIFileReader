package com.wirecard.filestructure.gui.model;

import com.wirecard.filestructure.gui.utils.Constants;

import javax.jws.Oneway;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class InstructionFileTableModel extends AbstractTableModel {
    private String[] columnNames  = Constants.INSTRUCTION_FILE_TABLE_COLUMN;
    private List<Object[]> data = new ArrayList<Object[]>();

    public InstructionFileTableModel(){
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex)[columnIndex];
    }

    public String getColumnName(int col){
        return columnNames[col];
    }

    public Class getColumnClass(int col){
        if(col == 0){
            return String.class;
        }else if(col == 1){
            return Integer.class;
        }else if(col == 2){
            return String.class;
        }else if(col == 3){
            return String.class;
        }else if(col == 4){
            return String.class;
        }else if(col == 5){
            return Integer.class;
        }else if(col == 6){
            return Boolean.class;
        }
        else{
            return String.class;
        }
    }

    public void addRow(Object[] dataRow){
        data.add(dataRow);
       fireTableDataChanged();
    }

    public void addRow(List dataList){
        data.addAll(dataList);
        fireTableDataChanged();
    }

    public void deleteRow(){
        data.remove(getRowCount()-1);
        fireTableDataChanged();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        if(columnIndex == 0 || columnIndex == 1){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        data.get(rowIndex)[columnIndex] = aValue;
        fireTableDataChanged();
    }

    public List<Object[]> getAllData(){
        return this.data;
    }

}
