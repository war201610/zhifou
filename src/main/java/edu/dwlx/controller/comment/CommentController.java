package edu.dwlx.controller.comment;

import edu.dwlx.entity.Comment;
import edu.dwlx.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/zhifou/comment")
public class CommentController {
/*1. 评论表内容
请求方式：post
请求地址：zhifou/comment
请求参数：{ "comment": 评论表名 }
请求数据：评论信息
2. 获取总评论的条数
请求方式：get
请求地址：zhifou/comment/评论表名/number
请求数据：评论表内的评论条数。
3. 发表评论(分问题, 文章, 回答)
请求url: /zhifou/comment/question/问题id/add
/zhifou/comment/article/文章id/add
/zhifou/comment/answer/回答id/add
请求方式: post
请求参数: int uid; 用户id
         int towho; 评论是回答谁的, 如果是评论文章, 问题或回答的就	                                         		         填-1, 如果是回复另一个人评论的就填用户id
         String comment; 评论内容
返回参数: Comment comment; 从数据库中查询的评论
*/
    @Autowired
    CommentMapper commentMapper;
    //评论表内容
    @RequestMapping
    public List<Comment> getCommentList(String comment) {
        return commentMapper.searchCommentByTableName(comment);
    }
    //获取总评论的条数
    @RequestMapping("/{table}/number")
    public int getCommentNumber(@PathVariable("table") String comment) {
        return commentMapper.searchCommentByTableName(comment).size();
    }

}
