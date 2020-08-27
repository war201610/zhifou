package edu.dwlx.controller.comment;

import edu.dwlx.entity.Comment;
import edu.dwlx.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/zhifou/comment")
public class CommentController {

    @Autowired
    CommentMapper commentMapper;
//    @RequestMapping
//    public List<Comment> getCommentList(String comment) {
//        return commentMapper.
//    }

}
