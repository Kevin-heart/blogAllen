package com.lrm.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 项目名称：blog
 * 类 名 称：IndexController
 * 类 描 述：请求处理层（web层）
 * 创建时间：2020/7/1 7:43 下午
 * 创 建 人：huanghao
 */

/**
 * 控制层
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
//        int i = 9/0;
//        String blog = null;
//        if(blog == null){
//             throw new NotFoundException("博客不存在!");
//        }
//        System.out.println("--------index-------");
        return "index";
    }

    @GetMapping("/blog")
    public String blog(){

        return "blog";
    }
}
