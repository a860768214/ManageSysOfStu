package com.hsy.dbexp.dataobject;

public class GradeDO {
    private Integer gid;

    private String className;

    private Float classGrade;

    private Integer classId;

    private Integer studentId;

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public Float getClassGrade() {
        return classGrade;
    }

    public void setClassGrade(Float classGrade) {
        this.classGrade = classGrade;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}