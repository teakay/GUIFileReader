package com.wirecard.filestructure.gui.controller;

import com.wirecard.filestructure.gui.entity.StructureFile;
import com.wirecard.filestructure.gui.entity.StructureFileDetail;
import com.wirecard.filestructure.gui.service.StructureFileService;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ParserController extends DefaultTableController {

    public Map parseFile(String filePathAndName, Map columnMap){
        Map returnMap = new HashMap();

        List headerLengthList = (List)columnMap.get("headerLength");
        List detailLengthList = (List)columnMap.get("detailLenght");
        List footerLengthList = (List)columnMap.get("footerLength");

        Vector header = new Vector();
        Vector detail = new Vector();
        Vector footer = new Vector();

        List detailList = new ArrayList();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePathAndName));
            String line = "";

            while ((line = bufferedReader.readLine())!=null){
                String recordType = line.substring(0,1);
                Integer firstindex = 0;
                Integer lastindex = 0;
                if("H".equals(recordType)){
                    int size = headerLengthList.size();
                    for(int n = 0; n < size; n++){
                        Integer length = (Integer)headerLengthList.get(n);
                        lastindex = firstindex + length;
                        String substring = line.substring(firstindex,lastindex);
                        firstindex = lastindex;
                        header.add(substring);
                    }
                }else if(!"H".equals(recordType) && !"F".equals(recordType)){
                    int size = detailLengthList.size();
                    for(int n = 0; n < size; n++){
                        Integer length = (Integer)detailLengthList.get(n);
                        lastindex = firstindex + length;
                        String substring = line.substring(firstindex,lastindex);
                        firstindex = lastindex;
                        detail.add(substring);
                    }
                    detailList.add(detail);
                    detail = new Vector();
                }else if("F".equals(recordType)){
                    int size = footerLengthList.size();
                    for(int n = 0; n < size; n++){
                        Integer length = (Integer)footerLengthList.get(n);
                        lastindex = firstindex + length;
                        String substring = line.substring(firstindex,lastindex);
                        firstindex = lastindex;
                        footer.add(substring);
                    }
                }
            }

            returnMap.put("header",header);
            returnMap.put("detail",detailList);
            returnMap.put("footer",footer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  returnMap;
    }

}
