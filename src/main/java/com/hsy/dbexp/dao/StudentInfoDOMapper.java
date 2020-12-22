package com.hsy.dbexp.dao;

import com.hsy.dbexp.dataobject.StudentInfoDO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentInfoDOMapper {
    int deleteByPrimaryKey(Integer studentId);

    int insert(StudentInfoDO record);

    int insertSelective(StudentInfoDO record);

    StudentInfoDO selectByPrimaryKey(Integer studentId);

    StudentInfoDO selectByName(String name);

    List<StudentInfoDO> selectAll();

    int updateByPrimaryKeySelective(StudentInfoDO record);

    int updateByPrimaryKey(StudentInfoDO record);
}