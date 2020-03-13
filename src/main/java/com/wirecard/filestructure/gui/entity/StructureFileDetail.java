package com.wirecard.filestructure.gui.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Vector;

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

    @Column(name="sfd_sample")
    private String sampleValue;

    @Column(name="sfd_description")
    private String description;

    public StructureFileDetail(){

    }

    public StructureFileDetail(String id, StructureFile structureFile,  String detailType, Integer sequenceNo, String fieldName,
                               String mandatory, String dataType, Integer dataLength, String sampleValue, String description){
        this.id = id;
        this.structureFile = structureFile;
        this.detailType = detailType;
        this.dataType = dataType;
        this.sequenceNo = sequenceNo;
        this.fieldName = fieldName;
        this.mandatory = mandatory;
        this.dataLength = dataLength;
        this.sampleValue = sampleValue;
        this.description = description;
    }


    public StructureFileDetail(StructureFile structureFile,  String detailType, Integer sequenceNo, String fieldName,
                               String mandatory, String dataType, Integer dataLength, String sampleValue, String description){
        this.structureFile = structureFile;
        this.detailType = detailType;
        this.dataType = dataType;
        this.sequenceNo = sequenceNo;
        this.fieldName = fieldName;
        this.mandatory = mandatory;
        this.dataLength = dataLength;
        this.sampleValue = sampleValue;
        this.description = description;
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

    public void setDataLength(Integer dataLength) {
        this.dataLength = dataLength;
    }

    public Integer getDataLength() {
        return dataLength;
    }

    public String getSampleValue() {
        return sampleValue;
    }

    public void setSampleValue(String sampleValue) {
        this.sampleValue = sampleValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Vector getVectorData(){
       Vector vector =  new Vector();
       vector.add(0,getId());
       vector.add(1,getSequenceNo());
       vector.add(2,getFieldName());
       vector.add(3,getMandatory());
       vector.add(4,getDataType());
       vector.add(5,getDataLength());
       vector.add(6,getSampleValue());
       vector.add(7,getDescription());

       return  vector;
    }
    public Object[] getArrayObject(){
        Object[] obj = new Object[9];
        obj[0] = getId();
        obj[1] = getSequenceNo();
        obj[2] = getFieldName();
        obj[3] = getMandatory();
        obj[4] = getDataType();
        obj[5] = getDataLength();
        obj[6] = getSampleValue();
        obj[7] = getDescription();
        obj[8] = new Boolean(false);

        return obj;
    }
}
