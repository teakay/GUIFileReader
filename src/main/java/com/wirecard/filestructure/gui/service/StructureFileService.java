package com.wirecard.filestructure.gui.service;

import com.wirecard.filestructure.gui.entity.StructureFile;
import com.wirecard.filestructure.gui.entity.StructureFileDetail;
import com.wirecard.filestructure.gui.utils.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class StructureFileService {

    private static String QUERY_GET_ALL_STRUCTURE_FILE_LIST = "from StructureFile s order by s.createdDate";
    private static String QUERY_DELETE_ROW_BY_ID = "delete from StructureFile s where s.id = :id";
    private static String QUERY_DELETE_DETAIL_ROW_BY_ID = "delete from StructureFileDetail sd where sd.headerId = :headerId";

    public static List getStructureFileList() throws Exception {
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
            session.close();
        }catch (Exception e){
            throw e;
        }
        return returnList;

    }

    public static void deleteById(List<Object[]> data) throws Exception {
        try {
            for(int i = 0; i < data.size(); i++) {
                String id = (String)data.get(i)[0];
                Session session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();

                Query q = session.createQuery(QUERY_DELETE_ROW_BY_ID);
                q.setString("id", id);
                q.executeUpdate();

                Query q2 = session.createQuery(QUERY_DELETE_DETAIL_ROW_BY_ID);
                q2.setString("headerId", id);
                q2.executeUpdate();

                session.getTransaction().commit();
                session.close();
            }
        }catch (Exception e){
            throw e;
        }
    }

    public static void saveData(Map dataMap) throws Exception {
        try {
            String structureName = (String) dataMap.get("structureName");
            String extension = (String) dataMap.get("extension");

            StructureFile structureFile = new StructureFile(structureName, extension, new Date());

            List<Object[]> headerData = (List<Object[]>) dataMap.get("headerData");
            List<Object[]> detailData = (List<Object[]>) dataMap.get("detailData");
            List<Object[]> footerData = (List<Object[]>) dataMap.get("footerData");

            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            session.save(structureFile);
            session.flush();
            session.clear();

            for (int i = 0; i < headerData.size(); i++) {
                StructureFileDetail sfd = new StructureFileDetail((String) structureFile.getId(), "header", (Integer) headerData.get(i)[1], (String) headerData.get(i)[2],
                        (String) headerData.get(i)[3], (String) headerData.get(i)[4], (Integer) headerData.get(i)[5]);
                session.save(sfd);
                if (i % 10 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            for (int i = 0; i < detailData.size(); i++) {
                StructureFileDetail sfd = new StructureFileDetail((String) structureFile.getId(), "detail", (Integer) detailData.get(i)[1], (String) detailData.get(i)[2],
                        (String) detailData.get(i)[3], (String) detailData.get(i)[4], (Integer) detailData.get(i)[5]);
                session.save(sfd);
                if (i % 10 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            for (int i = 0; i < footerData.size(); i++) {
                StructureFileDetail sfd = new StructureFileDetail((String) structureFile.getId(), "footer", (Integer) footerData.get(i)[1], (String) footerData.get(i)[2],
                        (String) footerData.get(i)[3], (String) footerData.get(i)[4], (Integer) footerData.get(i)[5]);
                session.save(sfd);
                if (i % 10 == 0) {
                    session.flush();
                    session.clear();
                }
            }

            session.getTransaction().commit();
            session.close();
        }catch (Exception e){
            throw e;
        }
    }
}
