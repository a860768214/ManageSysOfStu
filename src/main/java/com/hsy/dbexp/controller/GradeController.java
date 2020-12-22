package com.hsy.dbexp.controller;

import com.hsy.dbexp.controller.viewobject.AvgGradeVO;
import com.hsy.dbexp.controller.viewobject.GradeVO;
import com.hsy.dbexp.controller.viewobject.StudentVO;
import com.hsy.dbexp.dao.ClassInfoDOMapper;
import com.hsy.dbexp.dao.GradeDOMapper;
import com.hsy.dbexp.dao.StudentInfoDOMapper;
import com.hsy.dbexp.dataobject.ClassInfoDO;
import com.hsy.dbexp.dataobject.GradeDO;
import com.hsy.dbexp.response.CommonReturnType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("dbexp/gc")
@CrossOrigin
public class GradeController
{
    @Autowired
    private GradeDOMapper gradeDOMapper;

    @Autowired
    private ClassInfoDOMapper classInfoDOMapper;

    @Autowired
    private StudentInfoDOMapper studentInfoDOMapper;

    @PostMapping("/insert")
    public CommonReturnType insert(@RequestParam("cname") String classname,
                                   @RequestParam("cgrade") String cgrade,
                                   @RequestParam("sid") String sid)
    {
        try
        {
            ClassInfoDO classInfoDO = classInfoDOMapper.selectByName(classname);
            if(classInfoDO==null)
            {
                return CommonReturnType.create(null, "class_not_exist");
            }

            if(studentInfoDOMapper.selectByPrimaryKey(Integer.parseInt(sid))==null)
            {
                return CommonReturnType.create(null, "student_not_exist");
            }

            GradeDO gradeDO = new GradeDO();
            gradeDO.setClassName(classname);
            gradeDO.setClassGrade(new Float(cgrade));
            gradeDO.setClassId(classInfoDO.getClassId());
            gradeDO.setStudentId(Integer.parseInt(sid));

            gradeDOMapper.insert(gradeDO);
            return CommonReturnType.create(gradeDO);
        }catch (Exception e)
        {
            return CommonReturnType.create(null, "unknown_error");
        }
    }

    @PostMapping("/update")
    public CommonReturnType update(@RequestParam("gid") String gid,
                                   @RequestParam("cgrade") String cgrade)
    {
        try
        {
            System.out.println(gid+":"+cgrade);
            GradeDO gradeDO = gradeDOMapper.selectByPrimaryKey(Integer.parseInt(gid));
            gradeDO.setClassGrade(new Float(cgrade));

            gradeDOMapper.updateByPrimaryKey(gradeDO);
            return CommonReturnType.create(gradeDO);
        }catch (Exception e)
        {
            return CommonReturnType.create(null, "unknown_error");
        }
    }

    @PostMapping("/delete")
    public CommonReturnType delete(@RequestParam("gid") String gid)
    {
        try
        {
            GradeDO gradeDO = gradeDOMapper.selectByPrimaryKey(Integer.parseInt(gid));
            gradeDOMapper.deleteByPrimaryKey(Integer.parseInt(gid));
            return CommonReturnType.create(gradeDO);
        }catch (Exception e)
        {
            return CommonReturnType.create(null, "unknown_error");
        }
    }

    @GetMapping("/select")
    public CommonReturnType select(@RequestParam("gid") String gid)
    {
        try
        {
            return CommonReturnType.create(gradeDOMapper.selectByPrimaryKey(Integer.parseInt(gid)));
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
            List<GradeDO> gradeDOS = gradeDOMapper.selectAll();

            return CommonReturnType.create(convertToStudentVO(gradeDOS));
        }catch (Exception e)
        {
            return CommonReturnType.create(null, "unknown_error");
        }
    }

    @GetMapping("/getavg")
    public CommonReturnType getAvgGrade(@RequestParam("cname") String cname)
    {
        try
        {
            List<AvgGradeVO> avgGrade = gradeDOMapper.getAvgGrade(cname);
            for(AvgGradeVO t:avgGrade)
            {
                t.setCname(cname);
            }

            return CommonReturnType.create(avgGrade);
        }catch (Exception e)
        {
            return CommonReturnType.create(null, "unknown_error");
        }
    }

    private List<GradeVO> convertToStudentVO(List<GradeDO> gradeDOS)
    {
        List<GradeVO> gradeVOS=new ArrayList<>();

        for(GradeDO t:gradeDOS)
        {
            GradeVO gradeVO = new GradeVO();
            gradeVO.setClassGrade(t.getClassGrade());
            gradeVO.setClassName(t.getClassName());
            gradeVO.setGid(t.getGid());
            gradeVO.setStudentInfoDO(studentInfoDOMapper.selectByPrimaryKey(t.getStudentId()));

            gradeVOS.add(gradeVO);
        }
        return gradeVOS;
    }
}
