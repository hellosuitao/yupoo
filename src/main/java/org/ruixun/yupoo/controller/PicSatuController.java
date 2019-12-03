package org.ruixun.yupoo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.ruixun.yupoo.bean.PicStatu;
import org.ruixun.yupoo.service.PicStatuService;
import org.ruixun.yupoo.utils.JsonUtils;
import org.ruixun.yupoo.utils.Result;
import org.ruixun.yupoo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

/*
 * 作者：罗闽秋*/
@Controller
public class PicSatuController {
    @Autowired
    private PicStatuService picStatuService;
    @ResponseBody
    @RequestMapping("/updateLuo")
    public PicStatu updateStatu(@Valid PicStatu picStatu, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<ObjectError> errors = bindingResult.getAllErrors();
            StringBuilder sb =new StringBuilder();
            errors.forEach(error->sb.append(error+","));
            return null;
        }
        String s = picStatuService.updateLuo(picStatu);
        PicStatu byPid = picStatuService.findByPid(picStatu.getPid());
        return byPid;
    }

    //点赞方法
    @RequestMapping("/likeup")
    @ResponseBody
    public String likeUp(@RequestParam("pid") Long pid, @RequestParam("isup") Boolean isup) throws JsonProcessingException {
        System.out.println(pid+"-=----------------------------------"+isup);
        System.out.println("\n\n\n\n\n\n");
        if(pid==null && isup==null){
            return "";
        }
        System.out.println(isup);
        if (!isup){
            String s = picStatuService.likenumup(pid);
        }else {
            String s = picStatuService.likeNumDown(pid);
        }
        PicStatu byPid = picStatuService.findByPid(pid);
        return JsonUtils.objectTOJson(byPid.getUpnum());
    }

}
