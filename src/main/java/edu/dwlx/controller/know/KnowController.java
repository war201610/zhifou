package edu.dwlx.controller.know;

import edu.dwlx.controller.SearchFromList;
import edu.dwlx.entity.Answer;
import edu.dwlx.entity.Question;
import edu.dwlx.entity.User;
import edu.dwlx.services.AnswerService;
import edu.dwlx.services.QuestionService;
import edu.dwlx.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/zhifou/know")
public class KnowController {

    private final int PAGE_LENGTH = 6;

    private final UserService userService;
    private final QuestionService questionService;
    private final AnswerService answerService;

    @Autowired
    public KnowController(UserService userService, QuestionService questionService,
                          AnswerService answerService) {
        this.userService = userService;
        this.answerService = answerService;
        this.questionService = questionService;
    }

    //首页显示推荐
    @RequestMapping
    public String toKnow() throws Exception {
        return "/zhifou/know/know.html";
    }

    class KV {
        int key;
        String value;

        public KV(int key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "KV{" +
                    "key=" + key +
                    ", value='" + value + '\'' +
                    '}';
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
    @RequestMapping("/info")
    @ResponseBody
    //做多添加12个收藏相关推荐
    //根据用户收藏和的内容对应自身或者问题的标签统计对应标签的数量, 选取前三个, 在数据库中查找含有相关标签的内容, 按照浏览数排序
    public Map<String , Object> getRecommendList() {
        List<Question> allQuestionList = questionService.getAllQuestion();
        if(allQuestionList.size() < PAGE_LENGTH * 2) {
            Map<String, Object> map = new HashMap<>();
            map.put("questionList", allQuestionList);
            List<Integer> sizeList = new ArrayList<>();
            for(Question q : allQuestionList)
                sizeList.add(answerService.getAnswerCount(q.getAnswer()));
            map.put("answerCountList", sizeList);
            System.out.println(allQuestionList.size());
            return map;
        }
        User user = userService.searchUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Answer> collectAnswerList = userService.searchCollectAnswerByUid(user.getUid());
        List<KV> countList = new ArrayList<>();//长度为6, 从0到5对应时尚, 数码, 视频, 电影, 科学, 运动
        countList.add(new KV(0, "时尚"));
        countList.add(new KV(0, "数码"));
        countList.add(new KV(0, "视频"));
        countList.add(new KV(0, "电影"));
        countList.add(new KV(0, "科学"));
        countList.add(new KV(0, "运动"));
        for(Answer a : collectAnswerList) {
            Question collectQuestion = questionService.searchQuestionById(a.getQuestionId());
            System.out.println("question tags : " + collectQuestion.getTag());
            String[] tags = collectQuestion.getTag().split("#");//第一个内容tags[0]为空
            for(String tag : tags) {
                switch (tag) {
                    case "时尚" : {
                        countList.get(0).setKey(countList.get(0).getKey() + 1);
                        break;
                    }
                    case "数码" : {
                        countList.get(1).setKey(countList.get(1).getKey() + 1);
                        break;
                    }
                    case "视频" : {
                        countList.get(2).setKey(countList.get(2).getKey() + 1);
                        break;
                    }
                    case "电影" : {
                        countList.get(3).setKey(countList.get(3).getKey() + 1);
                        break;
                    }
                    case "科学" : {
                        countList.get(4).setKey(countList.get(4).getKey() + 1);
                        break;
                    }
                    case "运动" : {
                        countList.get(5).setKey(countList.get(5).getKey() + 1);
                        break;
                    }
                }
            }
        }
        Collections.sort(countList, (o1, o2) -> {
            if(o1.getKey() > o2.getKey())
                return -1;
            else if(o1.getKey() < o2.getKey())
                return 1;
            return 0;
        });
        System.out.println("kind: ");
        System.out.println(countList);
        List<Question> questionList1 = questionService.searchQuestionByTag(countList.get(0).getValue());
        List<Question> questionList2 = questionService.searchQuestionByTag(countList.get(1).getValue());
        List<Question> questionList3 = questionService.searchQuestionByTag(countList.get(2).getValue());
        List<Question> finalList = new ArrayList<>();
        int i = 0;
        for(Question q : questionList1) {
            if(i > 3)
                break;
            if(SearchFromList.searchQuestion(q.getId(), finalList)==null)
                finalList.add(q);
            else
                i--;
            i++;
        }
        i = 0;
        for(Question q : questionList2) {
            if(i > 3)
                break;
            if(SearchFromList.searchQuestion(q.getId(), finalList)==null)
                finalList.add(q);
            else
                i--;
            i++;
        }
        i = 0;
        for(Question q : questionList3) {
            if(i > 3)
                break;
            if(SearchFromList.searchQuestion(q.getId(), finalList)==null)
                finalList.add(q);
            else
                i--;
            i++;
        }
        System.out.println("questionList1: ");
        for(Question q : questionList1)
            System.out.println(q);
        System.out.println("questionList2: ");
        for(Question q : questionList2)
            System.out.println(q);
        System.out.println("questionList3: ");
        for(Question q : questionList3)
            System.out.println(q);
        System.out.println("finaList: ");
        for(Question q : finalList)
            System.out.println(q);

        //补充列表
        int size = finalList.size();
        if(size < 12) {
            while(true) {
                int allSize = allQuestionList.size();
                int limit = (allSize - allSize%10);
                for(Question q : allQuestionList){
                    if(finalList.size() >= PAGE_LENGTH * 2)
                        break;
                    if(Math.random() < 1.0/limit)
                        if(SearchFromList.searchQuestion(q.getId(), finalList)==null)
                            finalList.add(q);
                }
                if(finalList.size() >= PAGE_LENGTH * 2)
                    break;
            }
        } else {
            while(true) {
                allQuestionList = questionService.getAllQuestion();
                int allSize = allQuestionList.size();
                int limit = (allSize - allSize%10);
                for(Question q : allQuestionList){
                    if(finalList.size() >= PAGE_LENGTH * 3)
                        break;
                    if(Math.random() < 1.0/limit)
                        if(SearchFromList.searchQuestion(q.getId(), finalList)==null)
                            finalList.add(q);
                }
                if(finalList.size() >= PAGE_LENGTH * 3)
                    break;
            }

        }

        Collections.sort(finalList, new Comparator<Question>() {
            @Override
            public int compare(Question o1, Question o2) {
                if(o1.getCreateDate().after(o2.getCreateDate()))
                    return -1;
                else if(o2.getCreateDate().after(o1.getCreateDate()))
                    return 1;
                return 0;
            }
        });

        System.out.println("finalList modified: ");
        for(Question q : finalList)
            System.out.println(q);

        Map<String, Object> map = new HashMap<>();
        map.put("questionList", finalList);
        List<Integer> sizeList = new ArrayList<>();
        for(Question q : finalList)
            sizeList.add(answerService.getAnswerCount(q.getAnswer()));
        map.put("answerCountList", sizeList);
        System.out.println(finalList.size());
        return map;
    }
}
