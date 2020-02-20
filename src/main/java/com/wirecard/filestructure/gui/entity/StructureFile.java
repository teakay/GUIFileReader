package com.wirecard.filestructure.gui.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "structure_file")
public class StructureFile {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name="UUID",
            strategy="org.hibernate.id.UUIDGenerator"
    )
    @Column(name="sf_id",updatable=false,nullable=false)
    private String id;
    @Column(name="sf_name")
    private String structureName;
    @Column(name="sf_extension")
    private String extension;
    @Temporal(TemporalType.DATE)
    @Column(name="sf_created_date")
    private Date createdDate;

    public StructureFile(){

    }

    public StructureFile(String structureName, String extension, Date createdDate){
        this.structureName = structureName;
        this.extension = extension;
        this.createdDate = createdDate;
    }

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getStructureName() {
        return structureName;
    }

    public void setStructureName(String structureName) {
        this.structureName = structureName;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
