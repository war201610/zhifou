package edu.dwlx.mapper;

import edu.dwlx.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuestionMapper {
    public void insertQuestion(Question question);

    public void updateQuestion(Question question);

    public List<Question> searchQuestionByUid(@Param("uid") Integer uid);

    public List<Question> searchQuestionByTag(@Param("tag") String tag);
}
