package com.hsy.dbexp.controller.viewobject;

import com.hsy.dbexp.dataobject.StudentInfoDO;

public class GradeVO
{
    private Integer gid;

    private String className;

    private Float classGrade;

    private StudentInfoDO studentInfoDO;

    public Integer getGid()
    {
        return gid;
    }

    public void setGid(Integer gid)
    {
        this.gid = gid;
    }

    public String getClassName()
    {
        return className;
    }

    public void setClassName(String className)
    {
        this.className = className;
    }

    public Float getClassGrade()
    {
        return classGrade;
    }

    public void setClassGrade(Float classGrade)
    {
        this.classGrade = classGrade;
    }

    public StudentInfoDO getStudentInfoDO()
    {
        return studentInfoDO;
    }

    public void setStudentInfoDO(StudentInfoDO studentInfoDO)
    {
        this.studentInfoDO = studentInfoDO;
    }
}
