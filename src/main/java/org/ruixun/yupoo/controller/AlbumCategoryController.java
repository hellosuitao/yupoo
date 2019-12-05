package org.ruixun.yupoo.controller;

import org.ruixun.yupoo.bean.AlbumCategory;
import org.ruixun.yupoo.bean.Users;
import org.ruixun.yupoo.service.AlbumCategoryService;
import org.ruixun.yupoo.utils.FindUser;
import org.ruixun.yupoo.utils.JsonUtils;
import org.ruixun.yupoo.utils.Result;
import org.ruixun.yupoo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/albumCategory")
public class AlbumCategoryController {
    @Autowired
    private AlbumCategoryService albumCategoryService;

    //添加分类
    @RequestMapping("/addAlbumCategory")
    @ResponseBody
    public Result addAlbumCategory(@RequestBody @Valid AlbumCategory albumCategory,
                                   BindingResult bindingResult,
                                   HttpServletRequest request) {
        Users user = FindUser.findUser(request);
        albumCategory.setUserId(user.getId());
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            StringBuilder sb = new StringBuilder();
            errors.forEach(error -> sb.append(error + ","));
            return ResultUtils.buildFail(sb.toString());
        }

        Long id = albumCategoryService.saveAlbumCategory(albumCategory);
        if (id != null) {
            return ResultUtils.buildSuccess(id);
        }
        return ResultUtils.buildFail("Fail add");

    }

    //查询所有分类
    @RequestMapping("/findAll")
    public String findAll(HttpServletRequest request, ModelMap map) {
        Users user = FindUser.findUser(request);
        List<AlbumCategory> albumCategories = albumCategoryService.findAll(user.getId());
        albumCategories.forEach(System.out::println);
        map.put("albumCategories",albumCategories);
        return "category_manager";
    }

    @RequestMapping("/deleteById")
    @ResponseBody
    public Result deleteById(@RequestParam("categoryId")Long categoryId){
        albumCategoryService.delectTable(categoryId);
        return ResultUtils.buildSuccess();
    }

    @RequestMapping("/deleteByIds")
    @ResponseBody
    public Result deleteByIds(@RequestParam("categoryIds")Long categoryIds){
        albumCategoryService.delectTable(categoryIds);
        return ResultUtils.buildSuccess();
    }

    @RequestMapping("/findByParentId")
    @ResponseBody
    public Result findByParentId(@RequestParam(value = "parentId")Long parentId){
        List<AlbumCategory> albumCategoryByParentId = albumCategoryService.findAlbumCategoryByParentId(parentId);
        if(albumCategoryByParentId!=null&&albumCategoryByParentId.size()>0){
            return ResultUtils.buildSuccess(albumCategoryByParentId);
        }
        return ResultUtils.buildFail("No Data！");
    }

    //删除分类
    @ResponseBody
    @RequestMapping("/deleteAlbumCategory")
    public Result deleteAlbumCategory(Long id) {
        albumCategoryService.delectTable(id);
        return ResultUtils.buildSuccess();
    }

    //新增方法
    //多级目录
    @RequestMapping("/testLi")
    public String returnTest(@RequestParam(name = "parentId",defaultValue ="0") Long id, Map<String, Object> map) {
        List<AlbumCategory> albumCategory = albumCategoryService.findAlbumCategoryByParentId(id);
        map.put("albumCategoryList", albumCategory);

        return "test";
    }
    @RequestMapping("/testLi1")
    public String returnLi(@RequestParam(name = "parentId",defaultValue ="0") Long id, Map<String, Object> map) {
        List<AlbumCategory> albumCategory = albumCategoryService.findAlbumCategoryByParentId(id);
        map.put("albumCategoryList", albumCategory);
        return "test1";
    }
    //修改类名
    @RequestMapping("/updateName")
    @ResponseBody
    public Result updateName(@RequestBody AlbumCategory albumCategory){
        albumCategoryService.updataName(albumCategory.getName(), albumCategory.getId());
        return ResultUtils.buildSuccess();
    }
}
