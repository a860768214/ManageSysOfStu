package com.hsy.dbexp.controller.viewobject;

public class ClassGradeVO
{
    private String cname;

    private String sname;

    private double grade;


    @Override
    public String toString()
    {
        return "ClassGradeVO{" + "cname='" + cname + '\'' + ", sname='" + sname + '\'' + ", grade=" + grade + '}';
    }

    public String getCname()
    {
        return cname;
    }

    public void setCname(String cname)
    {
        this.cname = cname;
    }

    public String getSname()
    {
        return sname;
    }

    public void setSname(String sname)
    {
        this.sname = sname;
    }

    public double getGrade()
    {
        return grade;
    }

    public void setGrade(double grade)
    {
        this.grade = grade;
    }
}
