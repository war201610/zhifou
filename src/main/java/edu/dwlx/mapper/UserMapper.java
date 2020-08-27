package edu.dwlx.mapper;

import edu.dwlx.entity.Answer;
import edu.dwlx.entity.Article;
import edu.dwlx.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    int insertUser(User user);

    void createFollowerTable(User user);

    void createFollowingTable(User user);

    void createCollectArticleTable(User user);

    void createCollectAnswerTable(User user);

    void createUserAnswerTable(User user);

    int updateUser(User user);

    List<Answer> searchCollectAnswerByUid(Integer uid);

    List<Article> searchCollectArticleByUid(Integer uid);

    List<Answer> searchAnswerByUid(Integer uid);

//    List<Question> searchQuestionByUid(Integer uid);

    List<User> searchFollowerByUid(Integer uid);

    List<User> searchFollowingByUid(Integer uid);

    User searchUserById(Integer uid);

    User searchUserByName(String name);
}
