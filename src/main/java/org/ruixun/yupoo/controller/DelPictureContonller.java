package org.ruixun.yupoo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.ruixun.yupoo.bean.DelAudio;
import org.ruixun.yupoo.bean.DelPicture;
import org.ruixun.yupoo.bean.Users;
import org.ruixun.yupoo.service.DelAudioService;
import org.ruixun.yupoo.service.DelPictureService;
import org.ruixun.yupoo.service.PicStatuService;
import org.ruixun.yupoo.utils.FindUser;
import org.ruixun.yupoo.utils.Result;
import org.ruixun.yupoo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;

/*
 * 作者：罗闽秋*/
@Controller
@RequestMapping("/delPic")
public class DelPictureContonller {
    @Autowired
    private DelPictureService delPictureService;
    @Autowired
    private PicStatuService picStatuService;
    @Autowired
    private DelAudioService delAudioService;

    //查询所有回收站图片进行分页
    @RequestMapping("/findAll")
    public  String findAll(@RequestParam(value = "page",defaultValue = "0") Integer page,
                           @RequestParam(value = "size",defaultValue = "3") Integer size,
                           Map<String,Object> map,HttpServletRequest request){
        Users user = FindUser.findUser(request);
        if (page == null){
            return "recyclebin";
        }
        if (page == -1) {
            page=0;
        }
        /*suitao*/
        Page<DelPicture> all = delPictureService.findAll(page, size, user.getId());
        map.put("dels",all);
        return  "recyclebin";
    }

    //查询回收站所有图片进行判断，是否删除
    @ResponseBody
    @RequestMapping("/findDel")
    public Result findDel(HttpServletRequest request){
        Users user = FindUser.findUser(request);
        String s = delPictureService.checkDel();
        if (s!="success"){
            return ResultUtils.buildFail("删除失败");
        }
        return ResultUtils.buildSuccess();
    }

    //清空回收站
    @ResponseBody
    @RequestMapping("/delAll")
    public  Result delAll() throws JsonProcessingException {
        String s = delPictureService.delAll();
        if (s!="success"){
            return ResultUtils.buildFail("delete fail");
        }
        return ResultUtils.buildSuccess();
    }

    //根据Id永久删除图片
    @ResponseBody
    @RequestMapping("/delIds")
    public  Result delIds(@RequestParam("ids[]")  Long[] ids){
        if(ids==null){
          return ResultUtils.buildFail("picture id is null");
        }
        List<Long> list = Arrays.asList(ids);
        String s = delPictureService.delByPids(list);
        if (s!="success"){
            return ResultUtils.buildFail("delete fail");
        }
        return ResultUtils.buildSuccess();
    }

    //批量恢复照片
    @ResponseBody
    @RequestMapping("/insertPics")
    public  Result  insertPics(@RequestParam("ids[]")  Long[] ids){
        if (ids==null){
            return  ResultUtils.buildFail("id is null");
        }
        List<Long> list = Arrays.asList(ids);
        List<DelPicture> delPictures = delPictureService.findByPids(list);
        String s = delPictureService.insertPic(delPictures);
        if (s!="success"){
            return ResultUtils.buildFail("picture restore fail");
        }
        return ResultUtils.buildSuccess();
    }
}
