package org.ruixun.yupoo.controller;

import org.ruixun.yupoo.bean.Picture;
import org.ruixun.yupoo.service.AlbumService;
import org.ruixun.yupoo.service.PictureService;
import org.ruixun.yupoo.service.SurplusCapacityService;
import org.ruixun.yupoo.utils.Result;
import org.ruixun.yupoo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;

/*
 * 作者：suitao.....*/
@Controller
public class PictureUpload {/*上传图片*/
    @Autowired
    private SurplusCapacityService surplusCapacityService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private PictureService pictureService;

    @RequestMapping("/picture/upload")/*图片上传*/
    //此id为商品id
    @ResponseBody
    public Result upload(MultipartFile[] file, HttpServletRequest request) throws Exception {
        String picturess = surplusCapacityService.addPictures(file, 0l, 1l, Long.valueOf(0l));
        return ResultUtils.buildSuccess(picturess);
    }
}