package edu.dwlx.services;

import edu.dwlx.entity.Question;
import edu.dwlx.entity.User;
import edu.dwlx.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionMapper questionMapper;

    public void insertQuestion(Question question){
        if(questionMapper.searchQuestionByContentAndUid(question) != null){
            return;
        }
        questionMapper.insertQuestion(question);
//        System.out.println(question);
        Question question1 = questionMapper.searchQuestionByContentAndUid(question);
        question1.setId(question1.getId());
//        System.out.println(question1);
        questionMapper.updateQuestion(question1);
        questionMapper.createQuestionAnswerTable(question1);
        questionMapper.createQuestionCommentTable(question1);
        questionMapper.createQuestionFollowerTable(question1);
    }

    public void updateQuestion(Question question){
        questionMapper.updateQuestion(question);
    }

    public List<Question> searchQuestionByUid(int uid){
        return questionMapper.searchQuestionByUid(uid);
    }

    public List<Question> searchQuestionByTag(String tag){
        return questionMapper.searchQuestionByTag(tag);
    }

    public List<Question> searchQuestionByContent(String content){
        return questionMapper.searchQuestionByContent(content);
    }

    public Question searchQuestionById(int id){
        return questionMapper.searchQuestionById(id);
    }

    public Question searchQuestionByContentAndUid(Question question){
        return questionMapper.searchQuestionByContentAndUid(question);
    }

    public List<User> searchQuestionFollower(Question question){
        return questionMapper.searchQuestionFollower(question);
    }

    public void insertQuestionFollower(int questionId, int uid){
        questionMapper.insertQuestionFollower(questionId, uid);
    }

    public void deleteQuestionFollower(int questionId, int uid){
        questionMapper.deleteQuestionFollower(questionId, uid);
    }
}
