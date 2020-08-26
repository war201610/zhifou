package edu.dwlx.mapper;

import edu.dwlx.entity.Question;
import edu.dwlx.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuestionMapper {
    void insertQuestion(Question question);

    void createQuestionCommentTable(Question question);

    void createQuestionAnswerTable(Question question);

    void createQuestionFollowerTable(Question question);

    void updateQuestion(Question question);

    List<Question> searchQuestionByUid(@Param("uid") Integer uid);

    List<Question> searchQuestionByTag(@Param("tag") String tag);

    Question searchQuestionById(@Param("id") Integer id);

    Question searchQuestionByContentAndUid(Question question);

    List<User> searchQuestionFollower(Question question);
}
