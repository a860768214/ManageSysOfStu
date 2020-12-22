package com.hsy.dbexp.dao;

import com.hsy.dbexp.controller.viewobject.ClassGradeVO;
import com.hsy.dbexp.dataobject.ClassInfoDO;
import com.hsy.dbexp.dataobject.DepartmentDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassInfoDOMapper {
    int deleteByPrimaryKey(Integer classId);

    int insert(ClassInfoDO record);

    int insertSelective(ClassInfoDO record);

    ClassInfoDO selectByPrimaryKey(Integer classId);

    ClassInfoDO selectByName(String name);

    int updateByPrimaryKeySelective(ClassInfoDO record);

    int updateByPrimaryKey(ClassInfoDO record);

    List<ClassInfoDO> selectAll();

    List<ClassGradeVO> getClassGarde( String cname);
}