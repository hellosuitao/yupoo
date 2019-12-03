package org.ruixun.yupoo.controller;

import org.ruixun.yupoo.service.AlbumService;
import org.ruixun.yupoo.service.AudioService;
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

/*
* 作者：随涛
* 上传音频
* */
@Controller
public class AudioUpload {

    @Autowired
    private AudioService audioService;

    @RequestMapping("/audio/uploadaudio")/*视频上传*/
    @ResponseBody
    public Result uploadaudio(@RequestParam("file") MultipartFile file){
        String audioPath = audioService.addAudio(file);/*返回音频id */
        if(audioPath!=null){
            return ResultUtils.buildSuccess(audioPath);
        }
        return ResultUtils.buildFail("商品上传失败");
    }

    @RequestMapping("/deleteAudioById")
    public Result deleteAudioById(Long id){
        audioService.deleteAudioById(id);
        return ResultUtils.buildSuccess();
    }
}