package com.wirecard.filestructure.gui.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="template")
public class Template {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID", strategy="org.hibernate.id.UUIDGenerator")
    @Column(name="t_id",updatable = false,nullable = false)
    private String id;

    @ManyToOne
    @JoinColumn(name="sf_id")
    private StructureFile structureFile;

    @Column(name="t_name")
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name="t_created_date")
    private Date createdDate;

    public Template(){}

    public Template(String name, StructureFile structureFile, Date createdDate){
        this.name = name;
        this.structureFile = structureFile;
        this.createdDate = createdDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public StructureFile getStructureFile() {
        return structureFile;
    }

    public void setStructureFile(StructureFile structureFile) {
        this.structureFile = structureFile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
