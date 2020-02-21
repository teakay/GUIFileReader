package com.wirecard.filestructure.gui.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "structure_file_detail")
public class StructureFileDetail {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name="UUID",
            strategy="org.hibernate.id.UUIDGenerator"
    )
    @Column(name="sfd_id",updatable=false,nullable=false)
    private String id;

    @Column(name="sf_id")
    private String headerId;

    @Column(name="sfd_detail_type")
    private String detailType;

    @Column(name="sfd_data_type")
    private String dataType;

    @Column(name="sfd_data_sequence")
    private Integer sequenceNo;

    @Column(name="sfd_data_field_name")
    private String fieldName;

    @Column(name="sfd_data_mandatory")
    private String mandatory;

    @Column(name="sfd_data_length")
    private Integer dataLength;

    public StructureFileDetail(){

    }

    public StructureFileDetail(String headerId,  String detailType, Integer sequenceNo, String fieldName,
                               String mandatory, String dataType, Integer dataLength){
        this.headerId = headerId;
        this.detailType = detailType;
        this.dataType = dataType;
        this.sequenceNo = sequenceNo;
        this.fieldName = fieldName;
        this.mandatory = mandatory;
        this.dataLength = dataLength;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeaderId() {
        return headerId;
    }

    public void setHeaderId(String headerId) {
        this.headerId = headerId;
    }

    public String getDetailType() {
        return detailType;
    }

    public void setDetailType(String detailType) {
        this.detailType = detailType;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Integer getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(Integer sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMandatory() {
        return mandatory;
    }

    public void setMandatory(String mandatory) {
        this.mandatory = mandatory;
    }

    public Integer getDataLength() {
        return dataLength;
    }

    public void setDataLength(Integer dataLength) {
        this.dataLength = dataLength;
    }
}
