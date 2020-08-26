package edu.dwlx.mapper;

import edu.dwlx.entity.Answer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AnswerMapper {

    void insertAnswer(Answer answer);

    void updateAnswer(Answer answer);

    void deleteAnswer(Answer answer);

    void createAnswerCommentTable(@Param("id") Integer id);

    List<Answer> searchAnswerByQuestionId(@Param("questionId") Integer questionId);

//    List<Answer> searchAnswerByUid(Answer answer);

    Answer searchAnswerByContentAndUid(Answer answer);

    List<Answer> searchAnswerByTableName(@Param("tableName") String tableName);
}
