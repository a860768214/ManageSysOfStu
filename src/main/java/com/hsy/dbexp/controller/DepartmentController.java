package com.hsy.dbexp.controller;

import com.hsy.dbexp.dao.DepartmentDOMapper;
import com.hsy.dbexp.dao.GradeDOMapper;
import com.hsy.dbexp.dao.StudentInfoDOMapper;
import com.hsy.dbexp.dao.StudentRelationDOMapper;
import com.hsy.dbexp.dataobject.DepartmentDO;
import com.hsy.dbexp.dataobject.GradeDO;
import com.hsy.dbexp.dataobject.StudentRelationDO;
import com.hsy.dbexp.response.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("dbexp/dc")
@CrossOrigin
public class DepartmentController
{
    @Autowired
    private DepartmentDOMapper departmentDOMapper;

    @Autowired
    private StudentInfoDOMapper studentInfoDOMapper;

    @Autowired
    private StudentRelationDOMapper studentRelationDOMapper;

    @PostMapping(value = "/insert")
    public CommonReturnType insert(@RequestParam("department_name") String dname)
    {
        try
        {
            if(departmentDOMapper.selectByName(dname)!=null)
            {
                return CommonReturnType.create(null, "already_exist");
            }

            DepartmentDO departmentDO = new DepartmentDO();
            departmentDO.setDepartmentName(dname);

            departmentDOMapper.insert(departmentDO);
            return CommonReturnType.create(departmentDO);
        }catch (Exception e)
        {
            return CommonReturnType.create(null, "unknown_error");
        }
    }

    @PostMapping("/update")
    public CommonReturnType update(@RequestParam("department_id") String did,
                                   @RequestParam("department_name") String dname)
    {
        try
        {
            DepartmentDO departmentDO = departmentDOMapper.selectByPrimaryKey(Integer.parseInt(did));
            departmentDO.setDepartmentName(dname);
            departmentDOMapper.updateByPrimaryKey(departmentDO);
            return CommonReturnType.create(departmentDO);
        }catch (Exception e)
        {
            return CommonReturnType.create(null, "unknown_error");
        }
    }
    @PostMapping("/delete")
    public CommonReturnType delete(@RequestParam("department_id") String did)
    {
        try
        {

            DepartmentDO departmentDO = departmentDOMapper.selectByPrimaryKey(Integer.parseInt(did));
            List<StudentRelationDO> studentRelationDOS = studentRelationDOMapper.selectByDid(Integer.parseInt(did));

            for(StudentRelationDO t:studentRelationDOS)
            {
                t.setDepartmentId(0);
                studentRelationDOMapper.updateByPrimaryKey(t);
            }

            departmentDOMapper.deleteByPrimaryKey(Integer.parseInt(did));
            return CommonReturnType.create(departmentDO);
        }catch (Exception e)
        {
            return CommonReturnType.create(null, "unknown_error");
        }
    }

    @GetMapping("/select")
    public CommonReturnType select(@RequestParam("department_id") String did)
    {
        try
        {
            return CommonReturnType.create(departmentDOMapper.selectByPrimaryKey(Integer.parseInt(did)));
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
            return CommonReturnType.create(departmentDOMapper.selectAll());
        }catch (Exception e)
        {
            return CommonReturnType.create(null, "unknown_error");
        }
    }

    @GetMapping("/totalnum")
    public CommonReturnType countNum(@RequestParam("department_name") String dname)
    {
        try
        {
            return CommonReturnType.create(departmentDOMapper.countNum(dname));
        }catch (Exception e)
        {
            return CommonReturnType.create(null, "unknown_error");
        }
    }
}
