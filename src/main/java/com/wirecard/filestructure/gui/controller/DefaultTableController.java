package com.wirecard.filestructure.gui.controller;

import com.wirecard.filestructure.gui.entity.StructureFile;
import com.wirecard.filestructure.gui.entity.StructureFileDetail;
import com.wirecard.filestructure.gui.service.StructureFileService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultTableController extends AbstractController {
    private StructureFileService structureFileService;

    public List<StructureFile> getStructureList(){
        try {
            return structureFileService.getStructureFileListModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList();
    }

    public Map getColumnFromStructure(String structureName){
        Map resultMap = new HashMap();
        try {
            List structureDetailList = structureFileService.getStructureFileAndDetailByStructureName(structureName);
            List headerList = new ArrayList();
            List detailList = new ArrayList();
            List footerList = new ArrayList();

            List headerLengthList = new ArrayList();
            List detailLengthList = new ArrayList();
            List footerLengthList = new ArrayList();

            for(int i = 0; i < structureDetailList.size(); i++){
                StructureFileDetail sfd = (StructureFileDetail)structureDetailList.get(i);
                if("header".equals(sfd.getDetailType())){
                    headerList.add(sfd.getSequenceNo()-1,sfd.getFieldName());
                    headerLengthList.add(sfd.getSequenceNo()-1,sfd.getDataLength());
                }else if("detail".equals(sfd.getDetailType())){
                    detailList.add(sfd.getSequenceNo()-1, sfd.getFieldName());
                    detailLengthList.add(sfd.getSequenceNo()-1,sfd.getDataLength());
                }else if("footer".equals(sfd.getDetailType())){
                    footerList.add(sfd.getSequenceNo()-1, sfd.getFieldName());
                    footerLengthList.add(sfd.getSequenceNo()-1,sfd.getDataLength());
                }
            }

            resultMap.put("header",headerList);
            resultMap.put("detail",detailList);
            resultMap.put("footer",footerList);

            resultMap.put("headerLength",headerLengthList);
            resultMap.put("detailLenght",detailLengthList);
            resultMap.put("footerLength",footerLengthList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }
}
