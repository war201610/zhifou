package edu.dwlx.mapper;

import edu.dwlx.entity.Answer;
import edu.dwlx.entity.Question;
import edu.dwlx.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    User searchByName(String name);
    void insertUser(User user);
    User searchUserById(String uid);
    List<Answer> searchCollectAnswerByUid(int uid);
    List<Question> searchAskedQuestionByUid(int uid);
}
