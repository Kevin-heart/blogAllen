package com.lrm.blog.web.admin;

import com.lrm.blog.po.Type;
import com.lrm.blog.service.TypeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;

/**
 * 项目名称：blog
 * 类 名 称：TypeController
 * 类 描 述：TODO
 * 创建时间：2020/7/3 5:18 下午
 * 创 建 人：huanghao
 */

/**
 * 分类管理的控制层
 */
@Api(description = "分类管理模块")
@Controller
@RequestMapping("/admin") //后端请求路径是全局
public class TypeController {

    @Autowired //首先要根据Service调用方法注入
    private TypeService typeService;

    //1:分页列表的展示页面方法请求路径
    @GetMapping("/types")
   //根据Pageable的参数设置默认的值比如分页的数据排序需要注解方式指定@Pageable分页的大小 一页放多少数据 默认是10条 指定排序sort可以传递多个值根据主键id排序还可以按照倒序的排序
    public String types(@PageableDefault(size = 5,sort = {"id"},
                                     direction = Sort.Direction.DESC)
                                    Pageable pageable,
                        Model model){//Pageable是根据我们前端页面构造好的参数它自动封装SpringBoot自动封装好我们实现分页的方法
        //model前端定义的模型 然后把我们查询的东西放到前端模型里面展示
        //注入的Service方法有一个listType方法这个方法里要传递Pageable参数
        model.addAttribute("page",typeService.listType(pageable));

        //返回admin/types页面
        return "admin/types";
    }

    //2:定义新增的页面
    @GetMapping("/types/input")
    //通过这种方式我们的前端验证就可用了
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    //4:实现编辑的方法
    @GetMapping("/types/{id}/input")
    //注解接收到id的值  跳转到编辑的页面
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("type",typeService.getType(id));
        //返回到admin/types-input新增页面
        return "admin/types-input";
    }

    //3:提交的方法
    @PostMapping("/types") //@Valid是配合@NotBlank校验是否为空 BindingResult一定要和要校验的参数挨着，不然没效果
    //我们调用这个post方法传过来TypeController对象因为在types页面有name的值如果我们传过来对象就会把name接过来的值封装起来到Type直接调用Service方法就行保存
    /**
     * 新增业务层的校验
     * @Valid这里代码要校验这个Type对象,接收这个Type校验之后的结果
     * 校验之后的方法由result接收校验之后的结果
     */
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes){
        //如果我们拿到这个Type然后根据这个getName的值去查数据库里面是不是有没有这个Type存在如果有那我们校验不通过
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null){
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        //判读如果校验出错不成功有错误
        if (result.hasErrors()){
            //那么返回到我们的新增页面
            return "admin/types-input";
        }
        //接收过来的值 返回Type
        Type t = typeService.saveType(type);
        //判断t==null那么我们就判断
        if (t == null){
            //==null接收过来的值没保存成功
            //通过调用attriutes的方法 实现拿取到message的值
            attributes.addFlashAttribute("message","新增失败");
        } else { //如果不为null的话我希望给个消息提示
            //如果不为null的时候我们不成功
            attributes.addFlashAttribute("message","新增成功");
        }
        //返回页面 重定向到admin页面
        return "redirect:/admin/types";
    }

    //5:更新的方法修改到我们到Controller控制层实现这个方法调用Service方法
    @PostMapping("/types/{id}") //@Valid是配合@NotBlank校验是否为空
    public String editPost(@Valid Type type, BindingResult result,@PathVariable Long id, RedirectAttributes attributes) {
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null) {
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        if (result.hasErrors()) {
            return "admin/types-input";
        }
        Type t = typeService.updateType(id,type);
        if (t == null ) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        //返回页面 重定向到admin页面
        return "redirect:/admin/types";
    }

    //6:构建删除方法
    @GetMapping("/types/{id}/delete")//调用types传个id删除delete操作
    //接收这个Long id 请求@PathVariable    还可以添加校验的方法
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        //直接调用typeService这个接口的方法 进行删除操作
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功!");
        //返回重定向到admin/分类管理的页面
        return "redirect:/admin/types";
    }
}
