package org.ruixun.yupoo.controller;

import org.ruixun.yupoo.bean.*;
import org.ruixun.yupoo.service.MessagesService;
import org.ruixun.yupoo.service.PicStatuService;
import org.ruixun.yupoo.service.PictureService;
import org.ruixun.yupoo.utils.MessUtils;
import org.ruixun.yupoo.utils.Result;
import org.ruixun.yupoo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/*
 * 作者：罗闽秋
 * 商品全部信息封装
 * */
@RestController
@RequestMapping("/final")
public class FinalController {
    @Autowired
    private PictureService pictureService;
    @Autowired
    private PicStatuService picStatuService;
    @Autowired
    private MessagesService messagesService;

    @RequestMapping("/formPicture/{id}")
    public Result selectFrom(@PathVariable("id") Long id){
        if(id==null){
            return ResultUtils.buildFail("id为空");
        }
        FromPicture fromPicture = new FromPicture();
        MessUtils messUtils = new MessUtils();
        Picture picture= pictureService.findPictureById(id);
        PicStatu picStatus = picStatuService.findByPid(picture.getId());
        List<Mess> pid = messagesService.findMesByPid(picture.getId());
        fromPicture.setId(picture.getId());
        fromPicture.setAid(picture.getAid());
        fromPicture.setPath(picture.getPath());
        fromPicture.setName(picture.getName());
        fromPicture.setPsize(picture.getPsize());
        fromPicture.setUpdateTime(picture.getUpdateTime());
        fromPicture.setUploadTime(picture.getUploadTime());
        fromPicture.setLooknum(picStatus.getLooknum());
        fromPicture.setUonum(picStatus.getUpnum());
        fromPicture.setMess(pid);
        return ResultUtils.buildSuccess(fromPicture);
    }

}
