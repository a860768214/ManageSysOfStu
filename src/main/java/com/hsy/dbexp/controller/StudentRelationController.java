package com.hsy.dbexp.controller;

import com.hsy.dbexp.dao.StudentInfoDOMapper;
import com.hsy.dbexp.dao.StudentRelationDOMapper;
import com.hsy.dbexp.dataobject.StudentRelationDO;
import com.hsy.dbexp.response.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("dbexp/src")
@CrossOrigin
public class StudentRelationController
{
    @Autowired
    private StudentRelationDOMapper studentRelationDOMapper;

    @PostMapping("/insert")
    public CommonReturnType insert(@RequestParam("did") String did,
                                   @RequestParam("sid") String sid)
    {
        try
        {
            StudentRelationDO studentRelationDO = new StudentRelationDO();
            studentRelationDO.setDepartmentId(Integer.parseInt("did"));
            studentRelationDO.setStudentId(Integer.parseInt("sid"));

            studentRelationDOMapper.insert(studentRelationDO);
            return CommonReturnType.create(studentRelationDO);
        }catch (Exception e)
        {
            return CommonReturnType.create(null, "unknown_error");
        }
    }

    @PostMapping("/update")
    public CommonReturnType update(@RequestParam("rid") String rid,
                                   @RequestParam("did") String did,
                                   @RequestParam("sid") String sid)
    {
        try
        {
            StudentRelationDO studentRelationDO = studentRelationDOMapper.selectByPrimaryKey(Integer.parseInt(rid));
            studentRelationDO.setDepartmentId(Integer.parseInt("did"));
            studentRelationDO.setStudentId(Integer.parseInt("sid"));

            studentRelationDOMapper.updateByPrimaryKey(studentRelationDO);
            return CommonReturnType.create(studentRelationDO);
        }catch (Exception e)
        {
            return CommonReturnType.create(null, "unknown_error");
        }
    }

    @PostMapping("/delete")
    public CommonReturnType delete(@RequestParam("rid") String rid)
    {
        try
        {
            StudentRelationDO studentRelationDO = studentRelationDOMapper.selectByPrimaryKey(Integer.parseInt(rid));
            studentRelationDOMapper.deleteByPrimaryKey(Integer.parseInt("rid"));
            return CommonReturnType.create(studentRelationDO);
        }catch (Exception e)
        {
            return CommonReturnType.create(null, "unknown_error");
        }
    }

    @GetMapping("/select")
    public CommonReturnType select(@RequestParam("rid") String rid)
    {
        try
        {
            return CommonReturnType.create(studentRelationDOMapper.selectByPrimaryKey(Integer.parseInt(rid)));
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
            return CommonReturnType.create(studentRelationDOMapper.selectAll());
        }catch (Exception e)
        {
            return CommonReturnType.create(null, "unknown_error");
        }
    }
}
