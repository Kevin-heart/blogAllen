package com.lrm.blog.web.admin;

import com.lrm.blog.po.User;
import com.lrm.blog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;

/**
 * 后台的控制层请求处理层
 */
//后台登录的Controller控制层
@Api(description = "后台注册登录")
@Controller
@RequestMapping("/admin") //全局访问用GET请求的请求路径
public class LoginController {

    @Autowired//注入UserService拿到业务逻辑处理层
    private UserService userService;

    //1:定义一个方法跳转到后台登录页面
    @ApiOperation(value = "跳转到后台页面的方法")
    @GetMapping
    public String loginPage(){

        return "admin/login";
    }

    //2:定义跳转到登录页面之后登录页面提交的时候 我们就调用该方法把username用户名和密码传过来 然后处理判断
    //登录方法使用post方式提交
    @ApiOperation(value = "根据姓名和密码使用 post 提交")
    @PostMapping("/login")//请求的路径/admin/login到登录之后接收处理判断
    //通过@RuquestParam接收拿到的参数
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes) {
        /**
         * 使用Model拿到用户名和密码是不规范的
         * model只能放到转发域请求里面
         */
        //通过checkUser把username,password传递过来
        User user = userService.checkUser(username, password);

        //判断从客户拿取对象如果user不等等null的时候说明用户和密码是正确的
        //这样的话呢把查到的用户user对象放到Session里面来
        if (user != null) {
            user.setPassword(null);//处理登录密码设置为null不要把登录成功的密码传到前台在页面拿到密码不安全
            session.setAttribute("user", user); //拿到user登录成功
            //登录成功返回后台index首 页面
            return "admin/index";
        } else {//那么就是用户名或密码不对
            attributes.addFlashAttribute("message", "用户名和密码错误");
            return "redirect:/admin";//使用重定向到登录页面下经过这个方法跳转到admin
        }
    }
        //3:这个方法就是用户登录登出注销当前登录的用户
        @ApiOperation(value = "用户登录登出注销当前登录的用户")
        @GetMapping("/logout")
        public String logout(HttpSession session){

            //如果该用户注销登出那么就把session的内容清空
            session.removeAttribute("user");

            //返回定义的一个方法的页面
            return "redirect:/admin";
        }
    }

