package edu.dwlx.controller;

import edu.dwlx.entity.Answer;
import edu.dwlx.entity.Comment;
import edu.dwlx.entity.Question;
import edu.dwlx.entity.User;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SearchFromList {
    public static Comment searchComment(int id, List<Comment> commentList) {
        int size = commentList.size();
        int left = 0;
        int right = size - 1;
        int mid;
        while(left <= right) {
            mid = left + (right - left)/2;
            if(commentList.get(mid).getId() == id)
                return commentList.get(mid);
            else if(commentList.get(mid).getId() > id)
                right = mid - 1;
            else
                left = mid + 1;
        }
        return null;
    }

    //qid为回答对应问题id
    public static Answer searchAnswer(int id, int qid, List<Answer> answerList) {
        int size = answerList.size();
        int left = 0;
        int right = size - 1;
        int mid;
        while(left <= right) {
            mid = left + (right - left)/2;
            if(answerList.get(mid).getId() == id){
                if(qid != -1){
                    if(qid==answerList.get(mid).getQuestionId())
                        return answerList.get(mid);
                    else
                        return null;
                } else
                    return answerList.get(mid);
            }
            else if(answerList.get(mid).getId() > id)
                right = mid - 1;
            else
                left = mid + 1;
        }
        return null;
    }
    //如果出现重复查看排序是不是排反了
    public static Question searchQuestion(int id, List<Question> questionList) {
        Collections.sort(questionList, (o1, o2) -> {
            if(o1.getId() > o2.getId())
                return 1;
            else if(o1.getId() < o2.getId())
                return -1;
            else
                return 0;
        });
        int size = questionList.size();
        int left = 0;
        int right = size - 1;
        int mid;
        while(left <= right) {
            mid = left + (right - left)/2;
            if(questionList.get(mid).getId() == id)
                return questionList.get(mid);
            else if(questionList.get(mid).getId() > id)
                right = mid - 1;
            else
                left = mid + 1;
        }
        return null;
    }
    public static User searchUser(int id, List<User> userList) {
        Collections.sort(userList, (o1, o2) -> {
            if(o1.getUid() > o2.getUid())
                return -1;
            else if(o1.getUid() < o2.getUid())
                return 1;
            else
                return 0;
        });
        int size = userList.size();
        int left = 0;
        int right = size - 1;
        int mid;
        while(left <= right) {
            mid = left + (right - left)/2;
            if(userList.get(mid).getUid() == id)
                return userList.get(mid);
            else if(userList.get(mid).getUid() > id)
                right = mid - 1;
            else
                left = mid + 1;
        }
        return null;
    }

}
