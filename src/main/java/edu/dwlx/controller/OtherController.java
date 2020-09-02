package edu.dwlx.controller;

import edu.dwlx.entity.User;
import edu.dwlx.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class OtherController {

    private final UserService userService;

    @Autowired
    public OtherController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/zhifou/follow/wether")
    @ResponseBody
    public boolean followWether(int uid, int suid) {
        List<User> followingList = userService.searchFollowingByUid(uid);
        return SearchFromList.searchUser(suid, followingList)!=null;
    }

}
