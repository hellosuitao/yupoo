package org.ruixun.yupoo.controller;

import org.ruixun.yupoo.bean.Album;
import org.ruixun.yupoo.bean.Picture;
import org.ruixun.yupoo.bean.Users;
import org.ruixun.yupoo.service.AlbumService;
import org.ruixun.yupoo.service.PictureService;
import org.ruixun.yupoo.utils.FindUser;
import org.ruixun.yupoo.utils.Result;
import org.ruixun.yupoo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/*
* 作者：suitao*/
@Controller
public class PictureController {/*图片控制类*/
    @Autowired
    private PictureService pictureService;
    @Autowired
    private AlbumService albumService;


    @RequestMapping("/picture/setCoverPath")
    @ResponseBody
    public Result setCoverPath(@RequestParam("id") Long id, @RequestParam("aid")Long aid){
        Picture pic = pictureService.findPictureById(id);
        albumService.setCoverpath(aid,pic.getPath());
        return ResultUtils.buildSuccess();
    }

    @RequestMapping("/picture/delete")/*删除图片*/
    @ResponseBody
    public Result delete(@RequestParam("id") Long id, @RequestParam("aid") Long aid, HttpServletRequest request){
        Users user = FindUser.findUser(request);
        pictureService.deleteById(id,aid,user.getId());
        return ResultUtils.buildSuccess();
    }

    @RequestMapping("/picture/changesort")/*改变图片排序方式*/
    public String changesort(String albumid,String sortoption,ModelMap map){
        /*设值照片排序方式 要有uid*/
        albumService.setSortpicture(Long.valueOf(albumid),sortoption);
        List<Album> albums = albumService.findAlbumsByUid(1l);
        map.put("albums",albums);
        return "show_albums";
    }

}
