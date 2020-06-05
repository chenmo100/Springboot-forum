package com.lrm.web.admin;

import com.lrm.po.User;
import com.lrm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * Created by limi on 2017/10/15.
 */
@Controller
@RequestMapping("/admin")
public class LoginController {


    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage() {
        return "admin/login";
    }


    @PostMapping("/login")/*登录验证*/
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes) {
        User user = userService.checkUser(username, password);
        if (user != null) {
            user.setPassword(null);
            session.setAttribute("user",user);
            System.out.println("-------我是分割线------------");
            System.out.println(session.getAttribute("user"));
            return "admin/index";
        } else {
            attributes.addFlashAttribute("message", "用户名和密码错误");
            return "redirect:/admin";
        }
    }

    @PostMapping("/registered")/*注册账号*/
    public String registered(){
        return "admin/registered";
    }

    @PostMapping("/registeredOk")
    public String registeredOk(@RequestParam String avatar,
                               @RequestParam String email,
                               @RequestParam String nickname,
                               @RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String confirmed,
                               RedirectAttributes attributes) {
        if (!password.equals(confirmed)){
            attributes.addFlashAttribute("message","两次密码输入不一致,重新注册");
            return "redirect:/admin";
        }
        else {
            userService.addUser(avatar,email,nickname,username,password);
            attributes.addFlashAttribute("message","注册成功");
            return "redirect:/admin";
        }

    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
