package edu.dwlx.services;

import edu.dwlx.entity.Answer;
import edu.dwlx.entity.Article;
import edu.dwlx.entity.User;
import edu.dwlx.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("自定义登陆控制");
        User user = userMapper.searchUserByName(username);
        return user;
    }

    public int insertUser(User user){
        if(userMapper.searchUserByName(user.getName()) != null){
            return -1;
        }

        int flag = userMapper.insertUser(user);
        User user1 = userMapper.searchUserByName(user.getName());
        user1.setUid(user1.getUid());
        userMapper.updateUser(user1);
//        System.out.println(user1);
        userMapper.createFollowerTable(user1);
        userMapper.createFollowingTable(user1);
        userMapper.createCollectArticleTable(user1);
        userMapper.createCollectAnswerTable(user1);
        userMapper.createUserAnswerTable(user1);

        return flag;
    }

    public int updateUser(User user){
        return userMapper.updateUser(user);
    }

    public User searchUserById(int uid){
        return userMapper.searchUserById(uid);
    }

    public User searchUserByName(String name){
        return userMapper.searchUserByName(name);
    }

    public List<Answer> searchCollectAnswerByUid(Integer uid){
        return userMapper.searchCollectAnswerByUid(uid);
    }

    public List<Article> searchCollectArticleByUid(Integer uid){
        return userMapper.searchCollectArticleByUid(uid);
    }

    public List<Answer> searchAnswerByUid(Integer uid){
        return userMapper.searchAnswerByUid(uid);
    }

//    public List<Question> searchQuestionByUid(Integer uid){
//        return userMapper.searchQuestionByUid(uid);
//    }

    public List<User> searchFollowerByUid(Integer uid){
        return userMapper.searchFollowerByUid(uid);
    }

    public List<User> searchFollowingByUid(Integer uid){
        return userMapper.searchFollowingByUid(uid);
    }

    public void insertFollower(Integer uid, Integer followerId){
        userMapper.insertFollower(uid, followerId);
    }

    public void deleteFollower(Integer uid, Integer followerId){
        userMapper.deleteFollower(uid, followerId);
    }

    public void insertFollowing(Integer uid, Integer followingId){
        userMapper.insertFollowing(uid, followingId);
    }

    public void deleteFollowing(Integer uid, Integer followingId){
        userMapper.deleteFollowing(uid, followingId);
    }

    public void insertCollectArticle(Integer uid, Integer articleId){
        userMapper.insertCollectArticle(uid, articleId);
    }

    public void deleteCollectArticle(Integer uid, Integer articleId){
        userMapper.deleteCollectArticle(uid, articleId);
    }

    public void insertCollectAnswer(Integer uid, Answer answer){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("uid", uid);                    //收藏者uid
        map.put("id", answer.getId());
        map.put("answerUid", answer.getUid());  //回答者uid
        map.put("questionId", answer.getQuestionId());
        map.put("content", answer.getContent());
        map.put("agree", answer.getAgree());
        map.put("comment", answer.getComment());
        map.put("collection_count", answer.getCollection());
        map.put("createDate", answer.getCreateDateDate());

        userMapper.insertCollectAnswer(map);
    }

    public void deleteCollectAnswer(Integer uid, Answer answer){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("uid", uid);
        map.put("id", answer.getId());
        map.put("questionId", answer.getQuestionId());

        userMapper.deleteCollectAnswer(map);
    }

    public void insertUserAnswer(Answer answer){
        userMapper.insertUserAnswer(answer);
    }

    public void deleteUserAnswer(Answer answer){
        userMapper.deleteUserAnswer(answer);
    }

    public void getAgree(int uid){
        userMapper.getAgree(uid);
    }
}
