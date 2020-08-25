package edu.dwlx.mapper;

import edu.dwlx.entity.Answer;
import edu.dwlx.entity.Article;
import edu.dwlx.entity.Question;
import edu.dwlx.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    public int insertUser(User user);

    public void createFollowerTable(User user);

    public void createFollowingTable(User user);

    public void createCollectArticleTable(User user);

    public void createCollectAnswerTable(User user);

    public void createUserQuestionTable(User user);

    public void createUserAnswerTable(User user);

    public int updateUser(User user);

    public List<Answer> searchCollectAnswerByUid(Integer uid);

    public List<Article> searchCollectArticleByUid(Integer uid);

    public List<Answer> searchAnswerByUid(Integer uid);

    public List<Question> searchQuestionByUid(Integer uid);

    public List<User> searchFollowerByUid(Integer uid);

    public List<User> searchFollowingByUid(Integer uid);

    public User searchUserById(Integer uid);

    public User searchUserByName(String name);
}
