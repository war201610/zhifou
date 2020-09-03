package edu.dwlx.controller.collection;

import edu.dwlx.controller.SearchFromList;
import edu.dwlx.entity.Answer;
import edu.dwlx.services.AnswerService;
import edu.dwlx.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/zhifou/collection")
public class CollectionController {

    private final AnswerService answerService;
    private final UserService userService;

    @Autowired
    public CollectionController(AnswerService answerService, UserService userService) {
        this.answerService = answerService;
        this.userService = userService;
    }

    @RequestMapping("/{kind}/put")
    @ResponseBody
    public Boolean putCollection(int id, @PathVariable("kind") String kind,
                                @RequestParam(value = "qid", defaultValue = "0", required = false) int qid,
                                int uid, HttpServletResponse response) throws Exception {
        List<Answer> collectAnswerList = userService.searchCollectAnswerByUid(uid);
        for(Answer a : collectAnswerList) {
            if(SearchFromList.searchAnswer(id, collectAnswerList)!= null){
                System.out.println("未添加");
                return false;
            }
        }
        if(kind.equals("article"))
            userService.insertCollectArticle(uid, id);
        else if(kind.equals("answer")){
            Answer answer = SearchFromList.searchAnswer(id, answerService.searchAnswerByQuestionId(qid));
            if(answer==null){
                response.sendError(403, "参数错误");
                return false;
            }
            userService.insertCollectAnswer(uid, answer);
        } else
            return false;
        return true;
    }
}
