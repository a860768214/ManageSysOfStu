package com.hsy.dbexp.dao;

import com.hsy.dbexp.controller.viewobject.AvgGradeVO;
import com.hsy.dbexp.dataobject.GradeDO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeDOMapper {
    int deleteByPrimaryKey(Integer gid);

    int deleteBySid(Integer sid);

    int deleteByCid(Integer cid);

    int insert(GradeDO record);

    int insertSelective(GradeDO record);

    GradeDO selectByPrimaryKey(Integer gid);

    List<GradeDO> selectAll();

    int updateByPrimaryKeySelective(GradeDO record);

    int updateByPrimaryKey(GradeDO record);

    List<AvgGradeVO> getAvgGrade(String cname);
}