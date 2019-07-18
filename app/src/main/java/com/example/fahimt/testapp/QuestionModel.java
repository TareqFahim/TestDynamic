package com.example.fahimt.testapp;

public class QuestionModel {
    int id;
    String QType;
    String QScentence;
    boolean Mandatory;
    String Answer;
    Integer[] parentId;
    String condition;

    public QuestionModel(int id, String QType, String QScentence, boolean mandatory, String answer, Integer[] parentId, String condition) {
        this.id = id;
        this.QType = QType;
        this.QScentence = QScentence;
        Mandatory = mandatory;
        Answer = answer;
        this.parentId = parentId;
        this.condition = condition;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setQType(String QType) {
        this.QType = QType;
    }

    public void setQScentence(String QScentence) {
        this.QScentence = QScentence;
    }

    public void setMandatory(boolean mandatory) {
        Mandatory = mandatory;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public void setParentId(Integer[] parentId) {
        this.parentId = parentId;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public int getId() {

        return id;
    }

    public String getQType() {
        return QType;
    }

    public String getQScentence() {
        return QScentence;
    }

    public boolean isMandatory() {
        return Mandatory;
    }

    public String getAnswer() {
        return Answer;
    }

    public Integer[] getParentId() {
        return parentId;
    }

    public String getCondition() {
        return condition;
    }
}
