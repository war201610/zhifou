package edu.dwlx.mapper;

import edu.dwlx.entity.Comment;
import edu.dwlx.entity.Question;
import edu.dwlx.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
//@Repository
public interface QuestionMapper {
    void insertQuestion(Question question);

    void createQuestionCommentTable(Question question);

    void createQuestionAnswerTable(Question question);

    void createQuestionFollowerTable(Question question);

    void updateQuestion(Question question);

    List<Question> searchQuestionByUid(@Param("uid") Integer uid);

    List<Question> searchQuestionByTag(@Param("tag") String tag);

    List<Question> searchQuestionByContent(@Param("content") String content);

    Question searchQuestionById(@Param("id") Integer id);

    Question searchQuestionByContentAndUid(Question question);

    List<User> searchQuestionFollower(Question question);

//    void insertQuestionComment(@Param("questionId")Integer questionId, @Param("comment")Comment comment);
//
//    void deleteQuestionComment(@Param("questionId")Integer questionId, @Param("comment")Comment comment);
//
//    void insertQuestionAnswer();
//
//    void deleteQuestionAnswer();

    void insertQuestionFollower(@Param("questionId")int questionId, @Param("uid")int uid);

    void deleteQuestionFollower(@Param("questionId")int questionId, @Param("uid")int uid);
}
