package edu.dwlx.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AgreeMapper {
    boolean insertAgree(@Param("uid")int uid, @Param("questionId")int questionId, @Param("answerId")int answerId);

    void deleteAgree(@Param("uid")int uid, @Param("questionId")int questionId, @Param("answerId")int answerId);

    int searchAgree(@Param("uid")int uid, @Param("questionId")int questionId, @Param("answerId")int answerId);
}
