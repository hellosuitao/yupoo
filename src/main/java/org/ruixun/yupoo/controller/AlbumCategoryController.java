package org.ruixun.yupoo.controller;

import org.ruixun.yupoo.bean.AlbumCategory;
import org.ruixun.yupoo.service.AlbumCategoryService;
import org.ruixun.yupoo.utils.Result;
import org.ruixun.yupoo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/albumCategory")
public class AlbumCategoryController {
    @Autowired
    private AlbumCategoryService albumCategoryService;

    @RequestMapping("/addAlbumCategory")
    @ResponseBody
    public Result addAlbumCategory(@RequestBody @Valid AlbumCategory albumCategory, BindingResult bindingResult) {
        System.out.println(albumCategory);
        if(bindingResult.hasErrors()){
            List<ObjectError> errors = bindingResult.getAllErrors();
            StringBuilder sb =new StringBuilder();
          /*  for (ObjectError error : errors) {
                String message = error.getDefaultMessage();
                sb.append(message+",");
            }*/
            errors.forEach(error->sb.append(error+","));
            return ResultUtils.buildFail(sb.toString());
        }

        Long id = albumCategoryService.saveAlbumCategory(albumCategory);
        if(id!=null){
            System.out.println(id);
            return ResultUtils.buildSuccess(id);
        }
        return ResultUtils.buildFail("add fail");

    }

        @RequestMapping("/findAll")
    public Result findAll() {
            System.out.println(albumCategoryService.findAll()+"\n\n\n\n\n\n");

            return ResultUtils.buildSuccess(albumCategoryService.findAll());
    }

    @RequestMapping("/deleteAlbumCategory")
    public Result deleteAlbumCategory(Long id) {
        albumCategoryService.delectTable(id);
        return ResultUtils.buildSuccess();
    }


}
