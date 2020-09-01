package edu.dwlx.controller.agree;

import edu.dwlx.entity.Answer;
import edu.dwlx.entity.Comment;
import edu.dwlx.entity.Question;
import edu.dwlx.entity.User;
import edu.dwlx.services.AnswerService;
import edu.dwlx.services.CommentService;
import edu.dwlx.services.QuestionService;
import edu.dwlx.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/zhifou/agree")
public class AgreeController {

    @Autowired
    UserService userService;
    @Autowired
    QuestionService questionService;
    @Autowired
    AnswerService answerService;
    @Autowired
    CommentService commentService;

    @RequestMapping
    @ResponseBody
    //kind 点赞的类型, uid 点赞的用户, agreed 被点赞的内容
    //回答点赞更新需要更新回答的点赞和用户的赞同
    //问题点赞更新需要更新问题的点赞和用户的赞同
    //评论点赞更新需要更新评论的点赞

    public boolean agree(String kind, int uid, int qid,
                         @RequestParam(value="cid", defaultValue = "0", required=true) int cid,
                         @RequestParam(value="aid", defaultValue = "0", required=true)int aid,
                         HttpServletRequest request, Model model) {
        model.addAttribute("user", request.getSession().getAttribute("user"));
        String exceptionName;
        String exceptionContent;
        Question question = questionService.searchQuestionById(qid);
        User user = userService.searchUserById(uid);
        Answer answer;
        Comment comment;
        switch (kind) {
            case "answer": {
                List<Answer> answerList = answerService.searchAnswerByQuestionId(qid);
                answer = searchAnswer(aid, answerList);
                try {
                    int answerAgreenum = answer.getAgree() + 1;
                    answer.setAgree(answerAgreenum);
                } catch (Exception e) {
                    exceptionName = "空指针异常";
                    exceptionContent = "找不到回答";
                    request.getRequestDispatcher("/exceptionHandler?exceptionName=" +
                            exceptionName + "&exceptionContent=" + exceptionContent);
                    return false;
                }
                int idAgree = answer.getUid();//回答对应的用户id
                userService.getAgree(idAgree);
                //更新数据库
                answerService.updateAnswer(answer);
                break;
            }
            case "question": {
                try {
                    int questionAgree = question.getAgreeCount();
                    question.setAgreeCount(questionAgree + 1);
                } catch (Exception e) {
                    exceptionName = "空指针异常";
                    exceptionContent = "找不到问题";
                    request.getRequestDispatcher("/exceptionHandler?exceptionName=" +
                            exceptionName + "&exceptionContent=" + exceptionContent);
                    return false;
                }
                userService.getAgree(uid);
                questionService.updateQuestion(question);
                break;
            }
            case "comment": {
                List<Answer> answerList = answerService.searchAnswerByQuestionId(qid);
                answer = searchAnswer(aid, answerList);
                List<Comment> commentList = commentService.searchCommentByTableName(answer.getComment());
                comment = searchComment(cid, commentList);
                comment.setAgree(comment.getAgree());
                commentService.updateComment(comment, answer.getComment());
            }
        }
        return true;
    }
    public Comment searchComment(int id, List<Comment> commentList) {
        int size = commentList.size();
        int left = 0;
        int right = size - 1;
        int mid;
        while(left <= right) {
            mid = left + (right - left)/2;
            if(commentList.get(mid).getId() == id)
                return commentList.get(mid);
            else if(commentList.get(mid).getId() > id)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return null;
    }

    public Answer searchAnswer(int id, List<Answer> answerList) {
        int size = answerList.size();
        int left = 0;
        int right = size - 1;
        int mid;
        while(left <= right) {
            mid = left + (right - left)/2;
            if(answerList.get(mid).getId() == id)
                return answerList.get(mid);
            else if(answerList.get(mid).getId() > id)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return null;
    }
}
