package com.wirecard.filestructure.gui.model;

import java.sql.Struct;
import java.util.List;

public class FileStructure{
    private String fileExtension;
    private String structureName;
    private Structure header;
    private Structure footer;
    private List<Structure> detail;

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getStructureName() {
        return structureName;
    }

    public void setStructureName(String structureName) {
        this.structureName = structureName;
    }

    public Structure getHeader() {
        return header;
    }

    public void setHeader(Structure header) {
        this.header = header;
    }

    public Structure getFooter() {
        return footer;
    }

    public void setFooter(Structure footer) {
        this.footer = footer;
    }

    public List<Structure> getDetail() {
        return detail;
    }

    public void setDetail(List<Structure> detail) {
        this.detail = detail;
    }
}
