package org.ruixun.yupoo.service.impl;

import org.ruixun.yupoo.dao.DelPictureRepository;
import org.ruixun.yupoo.service.DelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.util.List;

@Service
@Transactional
public class DelServiceImpl implements DelService {
    @Autowired
    private DelPictureRepository delPictureRepository;
    //删除本地图片
    @Override
    public void delPicByPath(String path) {
        /*作者：suitao*/
        /*target 路径*/
        String filePath =new File(this.getClass().getResource("/").getPath()).getAbsolutePath()+"\\static"+path;
        File file = new File(filePath);
        file.delete();
        /*项目路径*/
        String newPathName = new File("").getAbsolutePath()+"/src/main/resources/static"+path;
        File file1 = new File(newPathName);
        file1.delete();
//        File file = new File(path);
//        file.delete();
    }
}
