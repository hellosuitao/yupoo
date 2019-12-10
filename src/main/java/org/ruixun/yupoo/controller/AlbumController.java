package org.ruixun.yupoo.controller;

import org.ruixun.yupoo.bean.*;
import org.ruixun.yupoo.dao.AlbumDao;
import org.ruixun.yupoo.service.*;
import org.ruixun.yupoo.utils.FindUser;
import org.ruixun.yupoo.utils.Result;
import org.ruixun.yupoo.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * 作者：suitao*/
@Controller
public class AlbumController {/*相册控制类*/

    private Logger logger = LoggerFactory.getLogger(AlbumController.class);

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private PictureService pictureService;
    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private MessagesService messagesService;
    @Autowired
    private AlbumCategoryService albumCategoryService;
    @Autowired
    private PicStatuService picStatuService;
    @Autowired
    private PicStatuService statuService;
    @Autowired
    private AudioService audioService;

    @RequestMapping("/findAlbumById")
    public String findAlbumById(Long id, HttpServletRequest request, Map<String, Object> map) throws UnknownHostException {
        Users user = findUser(request);
        if (user != null) {
            map.put("user", user);
        }

        String uri = "http://" + InetAddress.getLocalHost().getHostAddress() + ":" + request.getLocalPort();
        map.put("imgUrl", uri);
        statuService.loonumUp(id);
        Album album = albumService.findAlbumById(id);
        PicStatu byPid = statuService.findByPid(id);
//        List<Mess> mesByPid = messagesService.findMesByPid(id);
        Page<Messages> mesByPid = messagesService.findMes(id, 0);
        map.put("album", album);
        map.put("picStatu", byPid);
        map.put("Mess", mesByPid);
        return "product";

    }

    @RequestMapping("/album/findAlbumById")
    @ResponseBody
    public Result findAlbumById(Long id) {
        Album album = albumService.findAlbumById(id);
        if (album != null) {
            return ResultUtils.buildSuccess(album);
        }
        return ResultUtils.buildFail("no such product exist");
    }

    /*传入用户id 跳转到用户相册页面*/
    @RequestMapping(value = "/album/{uid}")/*根据用户id查询所有相册*/
    public String showAlbums(@PathVariable("uid") Long uid, ModelMap map) {
//        数据库中查相册
        List<Album> albums = albumService.findAlbumsByUid(1l);
        map.put("albums", albums);
        return "show_albums";
    }

    @PostMapping(value = "/album/addAlbum")/*添加商品*/
    @ResponseBody
    public Result addAlbum(@RequestBody @Valid Album album, BindingResult bindingResult) {
        if (album.getAlbumCategories().get(0).getId() == 0) {
            return ResultUtils.buildFail("Please select a category");
        }
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            StringBuilder sb = new StringBuilder();
            for (ObjectError error : errors) {
                String message = error.getDefaultMessage();
                sb.append(message + ",");
            }
            errors.forEach(error -> sb.append(error + ","));
            return ResultUtils.buildFail(sb.toString());
        }
        albumService.addAlbum(album);
        return ResultUtils.buildSuccess();
    }

    @RequestMapping("/album/showpictures/{id}")/*根据相册id查所有的照片*/
    public String showImages(@PathVariable("id") Long id, ModelMap map) {//相册id
        List<Picture> pirtures = pictureService.findPicturesByAid(id);
        Album album = albumService.findAlbumById(id);
        return "show_pictures";
    }

    /* @RequestMapping("/album/findByName")*//*根据相册名模糊查询相册*/
//    public String findByName( String name, ModelMap map) {
//        if (opname.equals("album")) {
//            List<Album> albums = albumService.findAlbumsByUidAndNameLike( '%' + name + '%');
//            if (albums != null) {
//                map.put("albums", albums);
//                return "show_albums";
//            }
//        } else {
//            List<Album> albums = albumService.findAlbumsByUid(1l);
//            List<Long> ids = new ArrayList<>();
//            if (albums != null) {
//                for (int i = 0; i < albums.size(); i++) {
//                    ids.add(albums.get(i).getId());
//                }
//                System.out.println("--------------------------------------");
//                List<Picture> pictures = pictureService.findPicturesByAidInAndNameLike(ids, '%' + name + '%');
//                map.put("pictures", pictures);
//                return "show_pictures_condition";
//            }
//        }
//        System.out.println(name);
//        return null;
//    }

//    @RequestMapping("/album/findByName")/*根据相册名模糊查询相册*/
//    public String findByName(String opname,String name,ModelMap map){
//        if(opname.equals("album")){
//            List<Album> albums = albumService.findAlbumsByUidAndNameLike('%'+name+'%');
//            if(albums!=null){
//                map.put("albums",albums);
//                return "show_albums";
//            }
//        }else{
//            List<Album> albums = albumService.findAlbumsByUid(1l);
//            List<Long> ids = new ArrayList<>();
//            if(albums!=null){
//                for (int i = 0; i < albums.size(); i++) {
//                    ids.add(albums.get(i).getId());
//                }
//                System.out.println("--------------------------------------");
//                List<Picture> pictures = pictureService.findPicturesByAidInAndNameLike(ids, '%'+name+'%');
//                map.put("pictures",pictures);
//                return "show_pictures_condition";
//            }
//        }
//        System.out.println(name);
//        return null;
//    }

    @RequestMapping("/album/changesort")/*修改排序方式*/
    public String changesort(String sortoption, ModelMap map) {
        /*设值相册排序方式 要有uid*/
        albumService.setSortalbum(1l, sortoption);
        List<Album> albums = albumService.findAlbumsByUid(1l);
        map.put("albums", albums);
        return "show_albums";
    }

    @RequestMapping("/album/deleteById")
    public String delete(@RequestParam("id") Long aid,
                         @RequestParam(value = "time", defaultValue = "0") String time,
                         @RequestParam(value = "categoryId", defaultValue = "0") Long categoryId,
                         @RequestParam(value = "selectBy", defaultValue = "0") String selectBy,
                         @RequestParam("inputValue") String inputValue,
                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                         @RequestParam(value = "size", defaultValue = "20") Integer size,
                         HttpServletRequest request,
                         ModelMap map) {
        albumService.deleteByAid(aid);
        Users user = FindUser.findUser(request);
        Page<Album> albumPage = albumService.findAlbumsByCondition2(time, categoryId, inputValue, selectBy, page, size,user.getId());
        if (albumPage != null) {
            List<Long> likes = new ArrayList<>();
            List<Long> messageSize = new ArrayList<>();
            List<Album> albums = albumPage.getContent();
            List<PicStatu> picStatuByPid = statuService.findPicStatuByPid(albumPage.getContent());
            map.put("likes", picStatuByPid);
            map.put("albumPage", albumPage);
            map.put("currentPage", page);
            map.put("totalPages", albumPage.getTotalPages());
            return "tableftl";
        }
        map.put("message", "no related products");
        return "tableftl";
    }

    @RequestMapping(value = "/album/findByCondition", method = RequestMethod.POST)
    /*组合条件查询*/
    public String findByCondition(@RequestParam(value = "time", defaultValue = "0") String time,
                                  @RequestParam(value = "categoryId", defaultValue = "0") Long categoryId,
                                  @RequestParam(value = "selectBy", defaultValue = "0") String selectBy,
                                  @RequestParam(value = "inputValue", defaultValue = "") String inputValue,
                                  @RequestParam(value = "page", defaultValue = "0") Integer page,
                                  @RequestParam(value = "size", defaultValue = "6") Integer size,
                                  HttpServletRequest request,
                                  ModelMap map) {
        System.out.println(page+"-----------------------------------------------");
        if (page <= 0) {
            page = 0;
        }
        Users user = FindUser.findUser(request);
        //如果是id查询  不需要分页
        if (selectBy.equals("id")) {
            Page<Album> albumPage = albumService.findAlbumsByCondition2("0", 0l, "0", "", 0, 6,user.getId());
            Album album = albumService.findAlbumById(Long.valueOf(inputValue));
            System.out.println(album);
            if (album != null&&album.getUid()==user.getId()) {
                PicStatu picStatu = statuService.findByPid(album.getId());
                map.put("likes", picStatu);
                map.put("album", album);
                map.put("currentPage", page);
                map.put("albumPage", albumPage);
                map.put("totalPages", albumPage.getTotalPages());
                return "tableftl";
            } else {
                map.put("message", "no data！！！");
                return "nodata";
            }
        }

        Page<Album> albumPage = albumService.findAlbumsByCondition2(time, categoryId, inputValue, selectBy, page, size,user.getId());
        if (albumPage.getContent().size() > 0) {
            List<Long> likes = new ArrayList<>();
            List<Long> messageSize = new ArrayList<>();
            List<Album> albums = albumPage.getContent();
            List<PicStatu> picStatuByPid = statuService.findPicStatuByPid(albumPage.getContent());
            map.put("likes", picStatuByPid);
            map.put("albumPage", albumPage);
            map.put("currentPage", page);
            map.put("totalPages", albumPage.getTotalPages());
            return "tableftl";
        } else {
            map.put("message", "no data！！！");
            return "nodata";
        }
    }

    @RequestMapping(value = "/album/deleteMany", method = RequestMethod.POST)
    //批量删除商品
    public String deleteMany(@RequestParam(value = "albumIds") List<Long> albumIds,
                             @RequestParam(value = "time", defaultValue = "0") String time,
                             @RequestParam(value = "categoryId", defaultValue = "0") Long categoryId,
                             @RequestParam(value = "selectBy", defaultValue = "0") String selectBy,
                             @RequestParam("inputValue") String inputValue,
                             @RequestParam(value = "page", defaultValue = "0") Integer page,
                             @RequestParam(value = "size", defaultValue = "20") Integer size,
                             HttpServletRequest request,
                             ModelMap map) {
        albumService.deleteByIds(albumIds);
        Users user = FindUser.findUser(request);
        Page<Album> albumPage = albumService.findAlbumsByCondition2(time, categoryId, inputValue, selectBy, page, size,user.getId());
        if (albumPage.getContent().size() > 0) {
            List<Long> likes = new ArrayList<>();
            List<Long> messageSize = new ArrayList<>();
            List<Album> albums = albumPage.getContent();
            List<PicStatu> picStatuByPid = statuService.findPicStatuByPid(albumPage.getContent());
            map.put("likes", picStatuByPid);
            map.put("albumPage", albumPage);
            map.put("currentPage", page);
            map.put("totalPages", albumPage.getTotalPages());
            return "tableftl";
        } else {
            map.put("message", "no data！！！");
            return "nodata";
        }
    }

    //批量上架
    @RequestMapping("/album/upshelf")
    @ResponseBody
    public Result upshelf(@RequestParam("albumIds") List<Long> albumIds) {
        System.out.println(albumIds);
        albumService.upshelf(albumIds);
        return ResultUtils.buildSuccess();
    }

    //批量下架
    @RequestMapping("/album/downshelf")
    @ResponseBody
    public Result updown(@RequestParam("albumIds") List<Long> albumIds) {
        System.out.println(albumIds);
        albumService.downshelf(albumIds);
        return ResultUtils.buildSuccess();
    }

    @RequestMapping(value = "/album/findAll")
    public String findAll(HttpServletRequest request
            , @RequestParam(value = "page", defaultValue = "0") Integer page
            , @RequestParam(value = "size", defaultValue = "6") Integer size
            , @RequestParam(value = "condition", defaultValue = "createDate") String condition
            , @RequestParam(value = "albumCategory", defaultValue = "0") String albumCategory, ModelMap map) {
        Users user = FindUser.findUser(request);
        List<AlbumCategory> albumCategories = albumCategoryService.findAllSecond(user.getId(),false);
        map.put("albumCategories", albumCategories);
        Page<Album> albumPage = albumService.findAll(page, size, "id", albumCategory,user.getId());
        System.out.println(albumPage.getContent());
        if (albumPage.getContent().size() > 0) {
            List<Long> messageSize = new ArrayList<>();
            List<Album> albums = albumPage.getContent();
            List<PicStatu> picStatuByPid = statuService.findPicStatuByPid(albumPage.getContent());
            map.put("likes", picStatuByPid);
            map.put("messageSize", messageSize);
            map.put("albumPage", albumPage);
            map.put("currentPage", page);
            map.put("totalPages", albumPage.getTotalPages());
            System.out.println(map);
            return "table";
        } else {
            map.put("message", "no data！！！");
            return "nodata";
        }
    }

    @RequestMapping("/album/showAdd")
    public String showAdd(ModelMap map,HttpServletRequest request) {
        Users user = FindUser.findUser(request);
        List<AlbumCategory> albumCategories = albumCategoryService.findAllSecond(user.getId(),false);
        if(albumCategories!=null&&albumCategories.size()>0){
            map.put("albumCategories", albumCategories);
            return "form";
        }
        return "form";
    }

    @RequestMapping(value = "/album/findAllByCategoryId")
    @ResponseBody
    //根据分类查
    public Result findAllByCategoryId(@RequestParam(value = "page", defaultValue = "0") Integer page
            , @RequestParam(value = "size", defaultValue = "6") Integer size
            , @RequestParam(value = "condition", defaultValue = "createDate") String condition
            , @RequestParam(value = "albumCategory", defaultValue = "0") String albumCategory) {
        Page<Album> albumPage = albumService.findAll(page, size, "id", albumCategory,1l);
        if (albumPage != null) {
            return ResultUtils.buildSuccess(albumPage);
        }
        return ResultUtils.buildFail("没有数据");
    }

    @RequestMapping(value = "/index")
    public String findAll(@RequestParam(value = "page", defaultValue = "0") Integer page
            , @RequestParam(value = "size", defaultValue = "6") Integer size
            , @RequestParam(value = "condition", defaultValue = "id") String condition
            , @RequestParam(value = "albumCategory", defaultValue = "0") String albumCategory, HttpServletRequest request, Map<String, Object> map) throws UnknownHostException {
        page = page > 0 ? page : 0;
        Users user = findUser(request);
        if (user != null) {
            map.put("user", user);
            /*suitao*/
            String uri = "http://" + InetAddress.getLocalHost().getHostAddress() + ":" + request.getLocalPort();
            map.put("imgUrl", uri);
            Page<Album> albumPage = albumService.findAll(page, 6, "id", albumCategory,user.getId());
            List<AlbumCategory> all = albumCategoryService.findAll();
            List<PicStatu> picStatuByPid = statuService.findPicStatuByPid(albumPage.getContent());

            System.out.println(albumPage.getContent());
            map.put("albumPage", albumPage);
            map.put("albumCategory", all);
            map.put("picStatuByPidS", picStatuByPid);
//            System.out.println(albumPage.getPageable());
//            System.out.println(albumPage.getSize());
            return "index";
        }
        String uri = "http://" + InetAddress.getLocalHost().getHostAddress() + ":" + request.getLocalPort();
        map.put("imgUrl", uri);
        Page<Album> albumPage = albumService.findAll(page, 6, "id", albumCategory,0l);
        List<AlbumCategory> albumCategories = albumCategoryService.findAllSecond(user.getId(),false);
        List<PicStatu> picStatuByPid = statuService.findPicStatuByPid(albumPage.getContent());

            System.out.println(albumPage.getContent());
            map.put("albumPage", albumPage);
            map.put("albumCategory", albumCategories);
            map.put("picStatuByPidS", picStatuByPid);
//            System.out.println(albumPage.getPageable());
//            System.out.println(albumPage.getSize());
        return "index";



    }

    @RequestMapping("/findByName")/*根据相册名模糊查询相册*/
    public String findByName(String name, HttpServletRequest request, Map<String, Object> map) throws UnknownHostException {
        String uri = "http://" + InetAddress.getLocalHost().getHostAddress() + ":" + request.getLocalPort();
        map.put("imgUrl", uri);
        Users user = findUser(request);
        if (user != null) {
            map.put("user", user);
        }
        Page<Album> albums = albumService.findAlbumsByUidAndNameLike('%' + name + '%');

        if (albums != null) {
            map.put("albumPage", albums);
            List<AlbumCategory> albumCategories = albumCategoryService.findAllSecond(user.getId(),false);
            List<PicStatu> picStatuByPid = statuService.findPicStatuByPid(albums.getContent());
            map.put("picStatuByPidS", picStatuByPid);
            map.put("albumCategory", albumCategories);
            map.put("albumCategoryId", 1);
            return "category";
        }

        return "false";
    }

    @RequestMapping(value = "/findAllByCategoryId")
    //根据分类查
    public String findAllByCategoryId(@RequestParam(value = "page", defaultValue = "0") Integer page

            , @RequestParam(value = "size", defaultValue = "6") Integer size
            , @RequestParam(value = "condition", defaultValue = "createDate") String condition
            , @RequestParam(value = "albumCategory", defaultValue = "0") String albumCategory, HttpServletRequest request, Map<String, Object> map) throws UnknownHostException {
        page = page > 0 ? page : 0;
        String uri = "http://" + InetAddress.getLocalHost().getHostAddress() + ":" + request.getLocalPort();
        map.put("imgUrl", uri);
        Users user = findUser(request);
        if (user != null) {
            map.put("user", user);
        }
        Page<Album> albumPage = albumService.findAll(page, size, "id", albumCategory,1l);
        List<AlbumCategory> albumCategories = albumCategoryService.findAllSecond(user.getId(),false);
        List<PicStatu> picStatuByPid = statuService.findPicStatuByPid(albumPage.getContent());
        map.put("picStatuByPidS", picStatuByPid);
        map.put("albumCategory", albumCategories);
        map.put("albumPage", albumPage);
        map.put("albumCategoryId", albumCategory);
        return "category";

    }

    @RequestMapping("/album/updateAlbum")
    @ResponseBody
    public Result updateAlbum(@RequestBody Album album) {
        albumService.updateAlbum(album);
        return ResultUtils.buildSuccess();
    }


    @RequestMapping("/album/editAlbum")
    public String editALbum(@RequestParam("id") Long id, ModelMap map, HttpServletRequest request) throws UnknownHostException {
        Users user = FindUser.findUser(request);
        Album album = albumService.findAlbumById(id);
        List<Picture> pictures = pictureService.findPicturesByAid(id);
        List<AlbumCategory> albumCategories = albumCategoryService.findAllSecond(user.getId(),false);
        List<Audio> audios = audioService.findAudioById(id);
        String beforeAudios ="";
        for (Audio audio : audios) {
            beforeAudios=beforeAudios+audio.getPath()+",";
        }
        map.put("beforePics", album.getPictures());
        map.put("beforeAudios", beforeAudios);
        map.put("album", album);
        map.put("pictures", pictures);
        map.put("albumCategories", albumCategories);
        map.put("audios",audios);
        return "editAlbum";
    }

    public Users findUser(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String sesionName = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    sesionName = cookie.getValue();
                }
            }
            Users user = (Users) request.getSession().getAttribute(sesionName);
            return user;
        }
        return null;
    }


}
