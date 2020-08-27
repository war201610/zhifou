package edu.dwlx.mapper;

import edu.dwlx.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {
    void insertComment(@Param("comment")Comment comment, @Param("tableName")String tableName);

    void deleteComment(@Param("comment")Comment comment, @Param("tableName")String tableName);

    List<Comment> searchCommentByTableName(@Param("tableName") String tableName);

    void updateComment(@Param("comment")Comment comment, @Param("tableName")String tableName);
}
