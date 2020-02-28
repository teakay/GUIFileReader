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

    @Column(name="t_name")
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name="t_created_date")
    private Date createdDate;

    public Template(){}

    public Template(String name, Date createdDate){
        this.name = name;
        this.createdDate = createdDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
