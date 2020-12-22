package com.hsy.dbexp.dao;

import com.hsy.dbexp.controller.viewobject.DepartmentVO;
import com.hsy.dbexp.dataobject.DepartmentDO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentDOMapper {
    int deleteByPrimaryKey(Integer departmentId);

    int insert(DepartmentDO record);

    int insertSelective(DepartmentDO record);

    DepartmentDO selectByPrimaryKey(Integer departmentId);

    DepartmentDO selectByName(String name);

    List<DepartmentDO> selectAll();

    int updateByPrimaryKeySelective(DepartmentDO record);

    int updateByPrimaryKey(DepartmentDO record);

    List<DepartmentVO> countNum(String dname);
}