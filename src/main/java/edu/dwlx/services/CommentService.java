package edu.dwlx.services;

import edu.dwlx.entity.Comment;
import edu.dwlx.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentMapper commentMapper;

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
        commentMapper.updateComment(comment, tableName);
    }

    public int getCollectCount(String tableName){
        return commentMapper.getCommentCount(tableName);
    }
}
