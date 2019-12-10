package org.ruixun.yupoo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.ruixun.yupoo.bean.DelAudio;
import org.ruixun.yupoo.bean.DelPicture;
import org.ruixun.yupoo.bean.Users;
import org.ruixun.yupoo.service.*;
import org.ruixun.yupoo.utils.FindUser;
import org.ruixun.yupoo.utils.Result;
import org.ruixun.yupoo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/*
* 作者：suitao
* 上传音频
* */
@Controller
public class AudioUpload {

    @Autowired
    private AudioService audioService;
    @Autowired
    private DelAudioService delAudioService;

    @RequestMapping("/audio/uploadaudio")/*视频上传*/
    @ResponseBody
    public Result uploadaudio(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        Users user = FindUser.findUser(request);
        String audioPath = audioService.addAudio(file,user.getId());/*返回音频id */
        if(audioPath!=null){
            return ResultUtils.buildSuccess(audioPath);
        }
        return ResultUtils.buildFail("fail update");
    }

    @RequestMapping("/audio/delete")/*视频删除*/
    @ResponseBody
    public Result deleteAudioById(@RequestParam("id") Long id,HttpServletRequest request){
        Users user = FindUser.findUser(request);
        audioService.deleteAudioById(id,user.getId());
        return ResultUtils.buildSuccess();
    }

    //查询回收站所有图片进行判断，是否删除
    @ResponseBody
    @RequestMapping("/audio/findDel")
    public Result findDel(HttpServletRequest request){
        Users user = FindUser.findUser(request);
//        String s = audioService.checkDel(user.getId());
//        if (s!="success"){
//            return ResultUtils.buildFail("fail delete");
//        }
        return ResultUtils.buildSuccess();
    }

    //清空回收站视频
    @ResponseBody
    @RequestMapping("/audio/delAll")
    public  Result delAll(HttpServletRequest request) throws JsonProcessingException {
        Users user = FindUser.findUser(request);
        String s = audioService.deleteAll(user.getId());
        if (s!="success"){
            return ResultUtils.buildFail("delete fail");
        }
        return ResultUtils.buildSuccess();
    }

    //根据Id永久删除视频
    @ResponseBody
    @RequestMapping("/audio/delIds")
    public  Result delIds(@RequestParam("ids[]")  Long[] ids){
        if(ids==null){
            return ResultUtils.buildFail("audio id is null");
        }
        List<Long> list = Arrays.asList(ids);
        String s = audioService.delByIds(list);
        if (s!="success"){
            return ResultUtils.buildFail("delete fail");
        }
        return ResultUtils.buildSuccess();
    }

    //批量恢复音频
    @ResponseBody
    @RequestMapping("/audio/recover")
    public  Result  insertPics(@RequestParam("ids[]")  Long[] ids){
        if (ids==null){
            return  ResultUtils.buildFail("id is empty");
        }
        List<Long> list = Arrays.asList(ids);
        String s = delAudioService.recover(list);
        if (s!="success"){
            return ResultUtils.buildFail("picture restore fail");
        }
        return ResultUtils.buildSuccess();
    }
}