package com.wirecard.filestructure.gui;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestFile {

    public static void justForTest(String[] args){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\wulantika.nuraeni\\Downloads\\UATlogs\\CTL_SIAMMAKRO_271120190921_00.txt"));
//            File file = new File("C:\\Users\\wulantika.nuraeni\\Downloads\\UATlogs\\CTL_SIAMMAKRO_271120190921_00.txt");

            String line = "";
            int i = 0;
            Integer firstindex = 0 ;
            Integer lastindex = 0;

            List headerLengthList = new ArrayList();
            headerLengthList.add(1);
            headerLengthList.add(20);
            headerLengthList.add(20);
            headerLengthList.add(20);
            headerLengthList.add(8);
            headerLengthList.add(6);

            while((line = bufferedReader.readLine()) != null){
                String recordType = line.substring(0,1);
                if("H".equals(recordType)){
                    for(int n = 0; n < headerLengthList.size(); n++){
                        Integer length = (Integer)headerLengthList.get(n);
                        lastindex = firstindex + length;
                        String substring = line.substring(firstindex,lastindex);
                        System.out.println(substring);
                        firstindex = lastindex;
                    }
                }else if("D".equals(recordType)){

                }else if("F".equals(recordType)){

                }
//                System.out.println(line.substring(0,1));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioexception){
            ioexception.printStackTrace();
        }

    }
}
