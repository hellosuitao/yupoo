package org.ruixun.yupoo.utils;

import org.ruixun.yupoo.bean.DelPicture;
import org.ruixun.yupoo.bean.Picture;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PictureUtils {
    public List<Picture> DelToPic(List<DelPicture> delPictures){
        List<Picture> list = new ArrayList<>();
        for (DelPicture delpicture:delPictures) {
            Picture picture = new Picture();
            System.out.println(delpicture);
            picture.setId(delpicture.getPid());
            picture.setAid(delpicture.getAid());
            picture.setName(delpicture.getName());
            picture.setPsize(delpicture.getPsize());
            picture.setPath(delpicture.getPath());
            picture.setUploadTime(delpicture.getUploadTime());
            picture.setUpdateTime(new Date());
            picture.setId(null);
            list.add(picture);
        }
        return list;
    }
}
