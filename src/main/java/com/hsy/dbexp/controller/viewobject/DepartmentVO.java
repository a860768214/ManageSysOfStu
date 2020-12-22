package com.hsy.dbexp.controller.viewobject;

public class DepartmentVO
{
    private String name;

    private String totalNum;

    @Override
    public String toString()
    {
        return "DepartmentVO{" + "name='" + name + '\'' + ", totalNum='" + totalNum + '\'' + '}';
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getTotalNum()
    {
        return totalNum;
    }

    public void setTotalNum(String totalNum)
    {
        this.totalNum = totalNum;
    }
}
