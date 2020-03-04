package com.wirecard.filestructure.gui.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="template_detail")
public class TemplateDetail {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "td_id", updatable = false, nullable = false)
    private String id;

    @ManyToOne
    @JoinColumn(name = "t_id")
    private Template template;

    @Column(name = "td_data_type")
    private String dataType;

    @Column(name = "td_data_row_no")
    private Integer rowNo;

    @Column(name = "td_data_sequence_no")
    private Integer dataSequenceNo;

    @Column(name = "td_field_name")
    private String fieldName;

    @Column(name = "td_field_value")
    private String fieldValue;

    public TemplateDetail(){

    }
    public TemplateDetail(Template template, String dataType, Integer rowNo, Integer dataSequenceNo, String fieldName, String fieldValue){
        this.template = template;
        this.dataType = dataType;
        this.rowNo = rowNo;
        this.dataSequenceNo = dataSequenceNo;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Integer getRowNo() {
        return rowNo;
    }

    public void setRowNo(Integer rowNo) {
        this.rowNo = rowNo;
    }

    public Integer getDataSequenceNo() {
        return dataSequenceNo;
    }

    public void setDataSequenceNo(Integer dataSequenceNo) {
        this.dataSequenceNo = dataSequenceNo;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }
}
