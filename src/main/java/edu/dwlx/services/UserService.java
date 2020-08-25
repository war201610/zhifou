package edu.dwlx.services;

import edu.dwlx.entity.Answer;
import edu.dwlx.entity.Article;
import edu.dwlx.entity.Question;
import edu.dwlx.entity.User;
import edu.dwlx.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        System.out.println("自定义登陆控制");
//        User user = userMapper.searchUserByName(username);
//        return user;
//    }

    public int insertUser(User user){
        if(userMapper.searchUserByName(user.getName()) != null){
            return -1;
        }

        userMapper.insertUser(user);
        User user1 = userMapper.searchUserByName(user.getName());
        user1.setUid(user1.getUid());
        userMapper.updateUser(user1);
        System.out.println(user1);
        userMapper.createFollowerTable(user1);
        userMapper.createFollowingTable(user1);
        userMapper.createCollectArticleTable(user1);
        userMapper.createCollectAnswerTable(user1);
        userMapper.createUserQuestionTable(user1);
        userMapper.createUserAnswerTable(user1);

        return 1;
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

    public List<Question> searchQuestionByUid(Integer uid){
        return userMapper.searchQuestionByUid(uid);
    }

    public List<User> searchFollowerByUid(Integer uid){
        return userMapper.searchFollowerByUid(uid);
    }

    public List<User> searchFollowingByUid(Integer uid){
        return userMapper.searchFollowingByUid(uid);
    }


}
