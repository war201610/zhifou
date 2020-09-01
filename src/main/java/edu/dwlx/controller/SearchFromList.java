package edu.dwlx.controller;

import edu.dwlx.entity.Answer;
import edu.dwlx.entity.Comment;
import edu.dwlx.entity.Question;

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

    public static Answer searchAnswer(int id, List<Answer> answerList) {
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
    public static Question searchQuestion(int id, List<Question> questionList) {
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

}
