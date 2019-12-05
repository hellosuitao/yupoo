package org.ruixun.yupoo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.ruixun.yupoo.bean.DelAudio;
import org.ruixun.yupoo.bean.DelPicture;
import org.ruixun.yupoo.bean.Users;
import org.ruixun.yupoo.service.AudioService;
import org.ruixun.yupoo.service.DelAudioService;
import org.ruixun.yupoo.service.DelPictureService;
import org.ruixun.yupoo.service.PicStatuService;
import org.ruixun.yupoo.utils.FindUser;
import org.ruixun.yupoo.utils.Result;
import org.ruixun.yupoo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/*suitao*/
@Controller
@RequestMapping("/delAudio")
public class DelAudioContonller {
    @Autowired
    private AudioService audioService;
    @Autowired
    private DelAudioService delAudioService;

    //查询所有回收站视频进行分页
    @RequestMapping("/findAll")
    public  String findAll(@RequestParam(value = "page",defaultValue = "0") Integer page,
                           @RequestParam(value = "size",defaultValue = "3") Integer size,
                           Map<String,Object> map,HttpServletRequest request){
        Users user = FindUser.findUser(request);
        if (page == null){
            return "recyclebinAudio";
        }
        if (page == -1) {
            page=0;
        }
        /*suitao*/
        Page<DelAudio> delAudios = delAudioService.findAll(page,size,user.getId());
        if(delAudios==null){
            map.put("dels",delAudios);
            return  "recyclebinAudio";
        }
        map.put("dels",delAudios);
        return  "recyclebinAudio";
    }

    //清空回收站
    @ResponseBody
    @RequestMapping("/delAll")
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
    @RequestMapping("/delIds")
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

    //批量恢复视频
    @ResponseBody
    @RequestMapping("/insertAudios")
    public  Result  insertPics(@RequestParam("ids[]")  Long[] ids){
        System.out.println("insert===================================");
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
