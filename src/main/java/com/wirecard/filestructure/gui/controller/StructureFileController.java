package com.wirecard.filestructure.gui.controller;

import com.wirecard.filestructure.gui.entity.StructureFile;
import com.wirecard.filestructure.gui.entity.StructureFileDetail;
import com.wirecard.filestructure.gui.service.StructureFileService;
import com.wirecard.filestructure.gui.view.*;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StructureFileController extends AbstractController {

    public static final String DATA_TABLE_SEARCH = "Search";
    public static final String DATA_ADD = "Add";
    public static final String DATA_DELETE = "Delete";
    private StructureFileService service;

//    public void doSearch(String query){
//        setModelProperty(DATA_TABLE_SEARCH,query);
//    }

    public void doAdd(AbstractViewPanel view){
        removeView(view);

        AddStructureFileView addView = new AddStructureFileView(this);
        addView(addView);

        MainFrame mainFrame = (MainFrame) SwingUtilities.getRoot(view);
        mainFrame.setContent(addView);
    }

    public void doBack(AbstractViewPanel view){
        removeView(view);
        StructureFileViewPanel structureView = new StructureFileViewPanel(this);
        addView(structureView);

        MainFrame mainFrame = (MainFrame) SwingUtilities.getRoot(view);
        mainFrame.setContent(structureView);
    }

    public void doDelete(List<Object[]> data){
        try {
            service.deleteById(data);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void doSaveData(AbstractViewPanel view, String structureName, String extension, List headerData, List detailData, List footerData){

        try{
            Map dataMap = new HashMap();

            dataMap.put("structureName",structureName);
            dataMap.put("extension",extension);
            dataMap.put("headerData",headerData);
            dataMap.put("detailData",detailData);
            dataMap.put("footerData",footerData);

            service.saveData(dataMap);
            removeView(view);

            StructureFileViewPanel structureView = new StructureFileViewPanel(this);
            addView(structureView);

            MainFrame mainFrame = (MainFrame) SwingUtilities.getRoot(view);
            mainFrame.setContent(structureView);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void doDetail(AbstractViewPanel view, String idData, String fileName){
        removeView(view);

        DetailStructureFileView detailView = new DetailStructureFileView(this);
        addView(detailView);
        List detailList = (List) service.getDetail(idData);

        Map map = new HashMap();
        StructureFile structureFile = ((StructureFileDetail) detailList.get(0)).getStructureFile();

        map.put("fileName", structureFile.getStructureName());
        map.put("fileExtension",structureFile.getExtension());

        for(int i = 0; i < detailList.size(); i++){
            map.put(((StructureFileDetail)detailList.get(i)).getDetailType(),((StructureFileDetail)detailList.get(i)).getArrayObject());
        }

        detailView.setData(map);

        MainFrame mainFrame = (MainFrame) SwingUtilities.getRoot(view);
        mainFrame.setContent(detailView);
    }

    public void doUpdate(AbstractViewPanel view, String structureName, String extension, List headerData, List detailData, List footerData){
        try{
            Map dataMap = new HashMap();

            dataMap.put("structureName",structureName);
            dataMap.put("extension",extension);
            dataMap.put("headerData",headerData);
            dataMap.put("detailData",detailData);
            dataMap.put("footerData",footerData);

            service.updateData(dataMap);
            removeView(view);

            StructureFileViewPanel structureView = new StructureFileViewPanel(this);
            addView(structureView);

            MainFrame mainFrame = (MainFrame) SwingUtilities.getRoot(view);
            mainFrame.setContent(structureView);
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public List getStructureFileList(){
        try {
            return service.getStructureFileList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList();
    }

}
