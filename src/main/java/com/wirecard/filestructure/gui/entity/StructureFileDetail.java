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

    @ManyToOne
    @JoinColumn(name="sf_id")
    private StructureFile structureFile;

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

    public StructureFileDetail(StructureFile structureFile,  String detailType, Integer sequenceNo, String fieldName,
                               String mandatory, String dataType, Integer dataLength){
        this.structureFile = structureFile;
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

    public StructureFile getStructureFile() {
        return structureFile;
    }

    public void setStructureFile(StructureFile structureFile) {
        this.structureFile = structureFile;
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

    public Object[] getArrayObject(){
        Object[] obj = new Object[7];
        obj[0] = getId();
        obj[1] = getSequenceNo();
        obj[2] = getFieldName();
        obj[3] = getMandatory();
        obj[4] = getDataType();
        obj[5] = getDataLength();
        obj[6] = new Boolean(false);

        return obj;
    }
}
