package com.wirecard.filestructure.gui.service;

import com.wirecard.filestructure.gui.entity.StructureFile;
import com.wirecard.filestructure.gui.utils.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class StructureFileService {

    private static String QUERY_GET_ALL_STRUCTURE_FILE_LIST = "from StructureFile s order by s.createdDate";
    private static String QUERY_DELETE_ROW_BY_ID = "delete from StructureFile s where s.id = :id";

    public static List getStructureFileList(){
        List<Object[]> returnList = new ArrayList<Object[]>();

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query q = session.createQuery(QUERY_GET_ALL_STRUCTURE_FILE_LIST);
            List resultList = q.list();

            for (int i = 0; i < resultList.size(); i++) {
                StructureFile structureFile = (StructureFile) resultList.get(i);
                Object[] dataList = new Object[5];
                dataList[0] = structureFile.getId();
                dataList[1] = structureFile.getStructureName();
                dataList[2] = structureFile.getExtension();
                dataList[3] = structureFile.getCreatedDate();
                dataList[4] = new Boolean(false);

                returnList.add(dataList);
            }
            session.getTransaction().commit();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return returnList;

    }

    public static void deleteById(String id){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query q = session.createQuery(QUERY_DELETE_ROW_BY_ID);
            q.setString("id", id);
            q.executeUpdate();

            session.getTransaction().commit();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}
