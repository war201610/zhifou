package edu.dwlx.services;

import edu.dwlx.entity.Question;
import edu.dwlx.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionMapper questionMapper;

    public void insertQuestion(){

    }

    public List<Question> searchQuestionByUid(int uid){
        return questionMapper.searchQuestionByUid(uid);
    }
}
