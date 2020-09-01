package edu.dwlx.mapper;

import edu.dwlx.entity.Question;
import edu.dwlx.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
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

    void getAgree(@Param("questionId")int questionId);              //问题的点赞数增加

    void increasedTraffic(@Param("questionId")int questionId);      //问题的浏览量增加

    void increasedCollection(@Param("questionId")int questionId);   //问题的收藏量增加

    void insertQuestionFollower(@Param("questionId")int questionId, @Param("uid")int uid);

    void deleteQuestionFollower(@Param("questionId")int questionId, @Param("uid")int uid);

    List<Question> getAllQuestion();
}
