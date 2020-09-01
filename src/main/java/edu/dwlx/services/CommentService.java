package edu.dwlx.services;

import edu.dwlx.entity.Comment;
import edu.dwlx.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentService {

    @Autowired
    CommentMapper commentMapper;

    private Map<String, Object> getMap(Comment comment, String tableName){
        Map<String, Object> map = new HashMap();
        map.put("tableName", tableName);
        map.put("id", comment.getId());
        map.put("uid", comment.getUid());
        map.put("content", comment.getContent());
        map.put("agree", comment.getAgree());
        map.put("toWho", comment.getToWho());
        map.put("createDate", comment.getCreateDate());

        return map;
    }

    public void insertComment(Comment comment, String tableName){

        commentMapper.insertComment(comment, tableName);
    }

    public void deleteComment(Comment comment, String tableName){
        commentMapper.deleteComment(comment, tableName);
    }

    public List<Comment>searchCommentByTableName(String tableName){
        return commentMapper.searchCommentByTableName(tableName);
    }

    public void updateComment(Comment comment, String tableName){
        Map<String, Object> map = getMap(comment, tableName);
        commentMapper.updateComment(map);
    }

    public int getCollectCount(String tableName){
        return commentMapper.getCommentCount(tableName);
    }
}
