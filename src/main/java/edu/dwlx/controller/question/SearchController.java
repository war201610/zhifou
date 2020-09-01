package edu.dwlx.controller.question;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/zhifou/search")
public class SearchController {
    @RequestMapping
    public String toSearch() {
        return "/zhifou/question/search.html";
    }
}
