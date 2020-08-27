package edu.dwlx.services;

import edu.dwlx.entity.Answer;
import edu.dwlx.mapper.AnswerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
    @Autowired
    private AnswerMapper answerMapper;

    public void insertAnswer(Answer answer){
        answerMapper.insertAnswer(answer);
        Answer answer1 = answerMapper.searchAnswerByContentAndUid(answer);
        answer1.setComment(answer1.getId() + "_answer_comment");
        answerMapper.updateAnswer(answer1);
        createAnswerCommentTable(answer1);
    }

    public void createAnswerCommentTable(Answer answer){
        answerMapper.createAnswerCommentTable(answer.getId());
    }

    public void updateAnswer(Answer answer){
        answerMapper.updateAnswer(answer);
    }

    public void deleteAnswer(Answer answer){
        answerMapper.deleteAnswer(answer);
    }

    public List<Answer> searchAnswerByQuestionId(int questionId) {
        return answerMapper.searchAnswerByQuestionId(questionId);
    }

    public List<Answer> searchAnswerByTableName(String tableName){
        return answerMapper.searchAnswerByTableName(tableName);
    }
}