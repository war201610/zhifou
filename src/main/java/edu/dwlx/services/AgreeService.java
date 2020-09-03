package edu.dwlx.services;

import edu.dwlx.mapper.AgreeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgreeService {
    @Autowired
    AgreeMapper agreeMapper;

    public boolean insertAgree(int uid, int questionId, int answerId){
        if(agreeMapper.searchAgree(uid, questionId, answerId) >= 1)
            return false;
        agreeMapper.insertAgree(uid, questionId, answerId);
        return true;
    }

    public void deleteAgree(int uid, int questionId, int answerId){
        agreeMapper.deleteAgree(uid, questionId, answerId);
    }
}
