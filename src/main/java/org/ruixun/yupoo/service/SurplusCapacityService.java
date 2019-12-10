package org.ruixun.yupoo.service;

import org.ruixun.yupoo.bean.SurplusCapacity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/*
* 作者：suitao*/
public interface SurplusCapacityService {/*剩余容量类*/

    SurplusCapacity findSurplusCapacityById(Long id);/*根据用户id查剩余容量*/

    String addPictures(MultipartFile[] file, Long size, Long id,Long aid) throws IOException;/*上传图片*/
}
