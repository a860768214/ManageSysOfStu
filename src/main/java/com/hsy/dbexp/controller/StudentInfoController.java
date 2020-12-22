package com.hsy.dbexp.controller;

import com.hsy.dbexp.dao.DepartmentDOMapper;
import com.hsy.dbexp.dao.GradeDOMapper;
import com.hsy.dbexp.dao.StudentInfoDOMapper;
import com.hsy.dbexp.dao.StudentRelationDOMapper;
import com.hsy.dbexp.dataobject.DepartmentDO;
import com.hsy.dbexp.dataobject.StudentInfoDO;
import com.hsy.dbexp.dataobject.StudentRelationDO;
import com.hsy.dbexp.response.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("dbexp/sc")
@CrossOrigin
public class StudentInfoController
{
    @Autowired
    private StudentInfoDOMapper studentInfoDOMapper;

    @Autowired
    private DepartmentDOMapper departmentDOMapper;

    @Autowired
    private StudentRelationDOMapper studentRelationDOMapper;

    @Autowired
    private GradeDOMapper gradeDOMapper;

    @PostMapping("/insert")
    public CommonReturnType insert(@RequestParam("sname") String sname,
                                   @RequestParam("dname") String dname)
    {
        try
        {
            DepartmentDO departmentDO = departmentDOMapper.selectByName(dname);
            if(departmentDO==null)
            {
                return CommonReturnType.create(null, "dept_not_exist");
            }

            StudentInfoDO studentInfoDO = new StudentInfoDO();
            studentInfoDO.setStudentName(sname);
            studentInfoDOMapper.insert(studentInfoDO);

            StudentRelationDO studentRelationDO = new StudentRelationDO();
            studentRelationDO.setStudentId(studentInfoDO.getStudentId());
            studentRelationDO.setDepartmentId(departmentDO.getDepartmentId());

            studentRelationDOMapper.insert(studentRelationDO);

            return CommonReturnType.create(studentInfoDO);
        }catch (Exception e)
        {
            e.printStackTrace();
            return CommonReturnType.create(null, "unknown_error");
        }
    }

    @PostMapping("/update")
    public CommonReturnType update(@RequestParam("sid") String sid,
                                   @RequestParam("sname") String sname)
    {
        try
        {
            StudentInfoDO studentInfoDO = studentInfoDOMapper.selectByPrimaryKey(Integer.parseInt(sid));
            studentInfoDO.setStudentName(sname);
            studentInfoDOMapper.updateByPrimaryKey(studentInfoDO);
            return CommonReturnType.create(studentInfoDO);
        }catch (Exception e)
        {
            return CommonReturnType.create(null, "unknown_error");
        }
    }
    @PostMapping("/delete")
    public CommonReturnType delete(@RequestParam("sid") String sid)
    {
        try
        {
            StudentInfoDO studentInfoDO = studentInfoDOMapper.selectByPrimaryKey(Integer.parseInt(sid));

            studentRelationDOMapper.deleteBySid(Integer.parseInt(sid));
            gradeDOMapper.deleteBySid(Integer.parseInt(sid));

            studentInfoDOMapper.deleteByPrimaryKey(Integer.parseInt(sid));
            return CommonReturnType.create(studentInfoDO);
        }catch (Exception e)
        {
            return CommonReturnType.create(null, "unknown_error");
        }
    }

    @GetMapping("/select")
    public CommonReturnType select(@RequestParam("sid") String sid)
    {
        try
        {
            return CommonReturnType.create(studentInfoDOMapper.selectByPrimaryKey(Integer.parseInt(sid)));
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
            return CommonReturnType.create(studentInfoDOMapper.selectAll());
        }catch (Exception e)
        {
            return CommonReturnType.create(null, "unknown_error");
        }
    }
}
