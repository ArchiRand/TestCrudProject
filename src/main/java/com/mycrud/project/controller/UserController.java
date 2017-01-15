package com.mycrud.project.controller;

import com.mycrud.project.model.User;
import com.mycrud.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public String listUsers(@RequestParam(value="page", required = false) Long page, Model model) {
        if (null == page)
            page = 1L;
        model.addAttribute("user", new User());
        model.addAttribute("searchByName", new User());
        model.addAttribute("listUsers", userService.getUsers(page));
        model.addAttribute("page", page);
        return "users";
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    @RequestMapping(value = "/users/edit", method = RequestMethod.POST)
    public String editUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }

    @RequestMapping("/remove/{id}")
    public String removeUser(@PathVariable("id") int id) {
        userService.removeUser(id);
        return "redirect:/users";
    }

    @RequestMapping("edit/{id}")
    public String editUser(@PathVariable("id") int id, @RequestParam(value="page", required = false) Long page, Model model) {
        if (null == page) page = 1L;
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("searchByName", new User());
        model.addAttribute("listUsers", userService.getUsers(page));
        model.addAttribute("page", page);
        return "edituser";
    }

    @RequestMapping("userdata/{id}")
    public String userData(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "userdata";
    }

    @RequestMapping("adduser")
    public String createUser(@ModelAttribute User user) {
        return "adduser";
    }


    @RequestMapping(value="search", method = RequestMethod.POST)
    public String searchResults(@ModelAttribute("searchByName") User user, Model model) {
        List<User> searchResult = userService.getUsers(user.getName());
        model.addAttribute("listUsers", searchResult);
        return "search";
    }

}
