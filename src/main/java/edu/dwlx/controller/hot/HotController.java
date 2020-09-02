package edu.dwlx.controller.hot;

import edu.dwlx.entity.Article;
import edu.dwlx.entity.Question;
import edu.dwlx.services.AnswerService;
import edu.dwlx.services.ArticleService;
import edu.dwlx.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/zhifou/hot")
public class HotController {
    @Autowired
    QuestionService questionService;
    @Autowired
    ArticleService articleService;
    @Autowired
    AnswerService answerService;

    private class Node implements Comparable<Node>{
        public int hotDegree;
        public int index;
        public Node(int hotDegree,int index){
            this.hotDegree = hotDegree;
            this.index = index;
        }

        @Override
        public int compareTo(Node o) {
            return new Integer(o.hotDegree).compareTo(hotDegree);
        }
    }

    @RequestMapping("/getHot")
    @ResponseBody
    public List getHot(){
        List<Question> questionList = questionService.getAllQuestion();
        List<Node> hotList = new LinkedList<>();
        Iterator<Question> questionIterator = questionList.iterator();
        Date current = new Date(System.currentTimeMillis());
        while(questionIterator.hasNext()){
            Question question = questionIterator.next();
            int hotDegree = (int) ((question.getViewCount() + question.getAgreeCount()*3 +
                                answerService.getAnswerCount(question.getAnswer())*5 + question.getCollectCount()*20 + 100)
                    / ((current.getTime() - question.getCreateDate().getTime()) /1000/3600/24 + 1));
            hotList.add(new Node(hotDegree, questionList.indexOf(question)));
            System.out.println(hotDegree);
        }

        Collections.sort(hotList);

        List<Question> list = new ArrayList<>();
        Iterator<Node> it = hotList.iterator();
        while(it.hasNext()){
            Node node = it.next();
            questionList.get(node.index).setViewCount(node.hotDegree);
            list.add(questionList.get(node.index));
        }

        return list;
    }

    @RequestMapping
    public String hot(){
        return "/zhifou/know/hot.html";
    }
}
