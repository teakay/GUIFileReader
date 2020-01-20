package com.wirecard.filestructure.gui.model;

public class Structure {
    private int sequenceNo;
    private String fieldName;
    private String mandatory;
    private String dataType;
    private int length;
    String valueSample;

    public int getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(int sequenceNo) {
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

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getValueSample() {
        return valueSample;
    }

    public void setValueSample(String valueSample) {
        this.valueSample = valueSample;
    }
}
