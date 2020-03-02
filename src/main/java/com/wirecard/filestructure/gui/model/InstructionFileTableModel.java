package com.wirecard.filestructure.gui.model;

import com.wirecard.filestructure.gui.utils.Constants;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class InstructionFileTableModel extends AbstractTableModel {
    private String[] columnNames  = Constants.INSTRUCTION_FILE_TABLE_COLUMN;
    private List<Vector> data = new ArrayList<Vector>();

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
        return data.get(rowIndex).get(columnIndex);
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
            return String.class;
        }else if(col == 7){
            return String.class;
        }else{
            return String.class;
        }
    }

    public void addRow(Vector dataRow){
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

    public void deleteRow(List dataRow){
        data.removeAll(dataRow);
        fireTableDataChanged();
    }

    public void removeRow(int[] row){
        List selectedList = new ArrayList();
        for(int i = 0; i < row.length; i++){
            selectedList.add(data.get(row[i]));
        }
        data.removeAll(selectedList);
        fireTableDataChanged();
    }

    public Vector getRowData(int row){
        return data.get(row);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        if(columnIndex == 0){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        data.get(rowIndex).set(columnIndex,aValue);
        fireTableDataChanged();
    }

    public List<Vector> getAllData(){
        return this.data;
    }

}
