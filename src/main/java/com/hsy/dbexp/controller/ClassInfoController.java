package com.hsy.dbexp.controller;

import com.hsy.dbexp.dao.ClassInfoDOMapper;
import com.hsy.dbexp.dataobject.ClassInfoDO;
import com.hsy.dbexp.dataobject.DepartmentDO;
import com.hsy.dbexp.response.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("dbexp/cic")
@CrossOrigin
public class ClassInfoController
{
    @Autowired
    private ClassInfoDOMapper classInfoDOMapper;



    @PostMapping("/insert")
    public CommonReturnType insert(@RequestParam("class_name") String cname)
    {
        try
        {
            if(classInfoDOMapper.selectByName(cname)!=null)
            {
                return CommonReturnType.create(null, "already_exist");
            }

            ClassInfoDO classInfoDO = new ClassInfoDO();
            classInfoDO.setClassName(cname);

            classInfoDOMapper.insert(classInfoDO);
            return CommonReturnType.create(classInfoDO);
        }catch (Exception e)
        {
            return CommonReturnType.create(null, "unknown_error");
        }
    }

    @PostMapping("/update")
    public CommonReturnType update(@RequestParam("class_id") String cid,
                                   @RequestParam("class_name") String cname)
    {
        try
        {
            ClassInfoDO classInfoDO = classInfoDOMapper.selectByPrimaryKey(Integer.parseInt(cid));
            classInfoDO.setClassName(cname);
            classInfoDOMapper.updateByPrimaryKey(classInfoDO);
            return CommonReturnType.create(classInfoDO);
        }catch (Exception e)
        {
            return CommonReturnType.create(null, "unknown_error");
        }
    }

    @PostMapping("/delete")
    public CommonReturnType delete(@RequestParam("class_id") String cid)
    {
        try
        {
            ClassInfoDO classInfoDO = classInfoDOMapper.selectByPrimaryKey(Integer.parseInt(cid));
            classInfoDOMapper.deleteByPrimaryKey(Integer.parseInt(cid));
            return CommonReturnType.create(classInfoDO);
        }catch (Exception e)
        {
            return CommonReturnType.create(null, "unknown_error");
        }
    }

    @GetMapping("/select")
    public CommonReturnType select(@RequestParam("class_id") String cid)
    {
        try
        {
            return CommonReturnType.create(classInfoDOMapper.selectByPrimaryKey(Integer.parseInt(cid)));
        }catch (Exception e)
        {
            return CommonReturnType.create(null, "unknown_error");
        }
    }

    @GetMapping("/list")
    public CommonReturnType list()
    {
        try
        {
            return CommonReturnType.create(classInfoDOMapper.selectAll());
        }catch (Exception e)
        {
            return CommonReturnType.create(null, "unknown_error");
        }
    }

    @GetMapping("/getclassgrade")
    public CommonReturnType getClassGrade(@RequestParam("cname") String cname)
    {
        try
        {
            return CommonReturnType.create(classInfoDOMapper.getClassGarde(cname));
        }catch (Exception e)
        {
            return CommonReturnType.create(null, "unknown_error");
        }
    }
}
