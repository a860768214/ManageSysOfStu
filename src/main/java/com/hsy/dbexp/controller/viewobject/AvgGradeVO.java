package com.hsy.dbexp.controller.viewobject;

public class AvgGradeVO
{
    private String cname;

    private String dname;

    private double avgGrade;

    @Override
    public String toString()
    {
        return "AvgGradeVO{" + "cname='" + cname + '\'' + ", dname='" + dname + '\'' + ", avgGrade=" + avgGrade + '}';
    }

    public String getCname()
    {
        return cname;
    }

    public void setCname(String cname)
    {
        this.cname = cname;
    }

    public String getDname()
    {
        return dname;
    }

    public void setDname(String dname)
    {
        this.dname = dname;
    }

    public double getAvgGrade()
    {
        return avgGrade;
    }

    public void setAvgGrade(double avgGrade)
    {
        this.avgGrade = avgGrade;
    }
}
