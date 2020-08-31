package edu.dwlx.mapper;

import edu.dwlx.entity.Answer;
import edu.dwlx.entity.Article;
import edu.dwlx.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserMapper {

    void insertUser(User user);

    void createFollowerTable(User user);

    void createFollowingTable(User user);

    void createCollectArticleTable(User user);

    void createCollectAnswerTable(User user);

    void createUserAnswerTable(User user);

    int updateUser(User user);

    List<Answer> searchCollectAnswerByUid(Integer uid);

    List<Article> searchCollectArticleByUid(Integer uid);

    List<Answer> searchAnswerByUid(Integer uid);

    List<User> searchFollowerByUid(@Param("uid")Integer uid);

    List<User> searchFollowingByUid(@Param("uid")Integer uid);

    User searchUserById(Integer uid);

    User searchUserByName(String name);

    void insertFollower(@Param("uid")Integer uid, @Param("followerId") Integer followerId);

    void deleteFollower(@Param("uid")Integer uid, @Param("followerId") Integer followerId);

    void insertFollowing(@Param("uid")Integer uid, @Param("followingId") Integer followingId);

    void deleteFollowing(@Param("uid")Integer uid, @Param("followingId") Integer followingId);

    void insertCollectArticle(@Param("uid")Integer uid, @Param("articleId") Integer articleId);

    void deleteCollectArticle(@Param("uid")Integer uid, @Param("articleId") Integer articleId);

    void insertCollectAnswer(Map<String, Object> map);  //稍微有点疑问，等待检验

    void deleteCollectAnswer(Map<String, Object> map);  //稍微有点疑问，等待检验

    void insertUserAnswer(Answer answer);

    void deleteUserAnswer(Answer answer);

    void getAgree(@Param("uid")int uid);
}
