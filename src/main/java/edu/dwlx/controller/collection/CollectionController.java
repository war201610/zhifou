package edu.dwlx.controller.collection;

import edu.dwlx.entity.Answer;
import edu.dwlx.entity.Article;
import edu.dwlx.services.AnswerService;
import edu.dwlx.services.ArticleService;
import edu.dwlx.services.UserService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/zhifou/collection")
public class CollectionController {

    @Autowired
    AnswerService answerService;
    @Autowired
    UserService userService;
    @Autowired
    ArticleService articleService;

    @RequestMapping("/{kind}/put")
    @ResponseBody
    public Boolean putColection(int id, @PathVariable("kind") String kind,
                                @RequestParam(value = "qid", defaultValue = "0", required = false) int qid,
                                int uid) {
        if(kind.equals("article"))
            userService.insertCollectArticle(uid, id);
        else if(kind.equals("answer")){
            Answer answer = searchAnswer(id, answerService.searchAnswerByQuestionId(qid));
            userService.insertCollectAnswer(uid, answer);
        } else
            return false;
        return true;
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
                right = mid - 1;
            else
                left = mid + 1;
        }
        return null;
    }

}
