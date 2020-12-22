package com.hsy.dbexp.dao;

import com.hsy.dbexp.dataobject.StudentRelationDO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRelationDOMapper {
    int deleteByPrimaryKey(Integer rid);

    int insert(StudentRelationDO record);

    int insertSelective(StudentRelationDO record);

    StudentRelationDO selectByPrimaryKey(Integer rid);

    List<StudentRelationDO> selectAll();

    int updateByPrimaryKeySelective(StudentRelationDO record);

    int updateByPrimaryKey(StudentRelationDO record);
}