package com.lrm.blog.web.admin;

import com.lrm.blog.po.Blog;
import com.lrm.blog.po.User;
import com.lrm.blog.service.BlogService;
import com.lrm.blog.service.TagService;
import com.lrm.blog.service.TypeService;
import com.lrm.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * 项目名称：blog
 * 类 名 称：BlogController
 * 类 描 述：TODO
 * 创建时间：2020/7/3 2:35 下午
 * 创 建 人：huanghao
 */

/**
 * 控制器层处理博客管理blogs的业务
 */
@Controller
@RequestMapping("/admin") //全局请求路径
public class BlogController {

    private static final String INPUT = "admin/blogs-input";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT_LIST = "redirect:/admin/blogs";

    //注入我们调用好的 BlogService 方法
    @Autowired
    private BlogService blogService;

    //注入 TypeService
    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    //1:定义一个方法访问博客管理页面
    @GetMapping("/blogs")//请求路径
    //加入试图模型 传入定义的方法
    public String blogs(@PageableDefault
                                (size = 3, sort = {"updateTime"},
                                        direction = Sort.Direction.DESC) Pageable pageable,
                        BlogQuery blog, Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        return LIST;
    }

    //2:定义一个方法查询博客管理页面
    @PostMapping("/blogs/search")//请求路径
    //加入试图模型 传入定义的方法
    public String search(@PageableDefault
                                 (size = 3, sort = {"updateTime"},
                                         direction = Sort.Direction.DESC) Pageable pageable,
                         BlogQuery blog, Model model) {

        model.addAttribute("page", blogService.listBlog(pageable, blog));
        return "admin/blogs :: blogList";
    }

    //3:请求到 admin/input 页面博客发布
    @GetMapping("/blogs/input")
    public String input(Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
        model.addAttribute("blog", new Blog());
        return INPUT;
    }

    //4:博客新增保存的方法
    @PostMapping("/blogs")
    //传入 blog 对象
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session) {
        //当前登录用户到 User 去取值
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));

        Blog b = blogService.saveBlog(blog);
        if (b == null) {
            attributes.addFlashAttribute("message", "操作成功");
        } else {
            attributes.addFlashAttribute("message", "操作失败");
        }
        return REDIRECT_LIST;

    }
}
