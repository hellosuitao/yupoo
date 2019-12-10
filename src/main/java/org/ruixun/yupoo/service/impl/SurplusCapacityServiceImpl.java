package org.ruixun.yupoo.service.impl;

import org.apache.commons.io.IOUtils;
import org.ruixun.yupoo.bean.Picture;
import org.ruixun.yupoo.bean.SurplusCapacity;
import org.ruixun.yupoo.dao.AlbumDao;
import org.ruixun.yupoo.dao.PictureDao;
import org.ruixun.yupoo.dao.SurplusCapacityDao;
import org.ruixun.yupoo.service.SurplusCapacityService;
import org.ruixun.yupoo.utils.StaticProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Date;
import java.util.UUID;

/*
 * 作者：suitao*/
@Service
@Transactional
public class SurplusCapacityServiceImpl implements SurplusCapacityService {/*剩余容量service*/
    @Autowired
    private SurplusCapacityDao surplusCapacityDao;
    @Autowired
    private AlbumDao albumDao;

    @Autowired
    private PictureDao pictureDao;
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private StaticProperties staticProperties;

    //    private String uri ="/user/jar/image/";
//    private String uri = "D://image/";
    @Override
    public SurplusCapacity findSurplusCapacityById(Long id) {
        return surplusCapacityDao.findSurplusCapacityById(id);
    }

    @Override
    /*用户id  商品aid*/
    public String addPictures(MultipartFile[] file, Long totalSize, Long id, Long aid) throws IOException {
        String pictures = "";
        String waterfile = file[file.length-1].getOriginalFilename();
        if(waterfile.substring(0, waterfile.indexOf(".")).equals("7cfe7b6ef45d4db2994e81b2c5ea5abcwater")){
            for (int i = 0; i < file.length-1; i++) {
                String originalFilename = file[i].getOriginalFilename();
                String name = file[i].getOriginalFilename().substring(0, originalFilename.indexOf("."));
                String wei = originalFilename.substring(originalFilename.indexOf("."), originalFilename.length());
                String newPicName = UUID.randomUUID().toString().replaceAll("-", "") + wei;
                String path = staticProperties.getPictureuri() + staticProperties.getStaticport() + newPicName;
                pictures = pictures + path + ",";
                File newFile = new File(staticProperties.getPicturepath() + newPicName);
                /*新增图片水印*/
                OutputStream os = null;
                try {
                    //获取源图片对象
                    Image srcImg = ImageIO.read(file[i].getInputStream());
                    BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),
                            srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
                    // 得到画笔对象
                    // Graphics g= buffImg.getGraphics();
                    Graphics2D g = buffImg.createGraphics();
                    // 设置对线段的锯齿状边缘处理
                    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                            RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                    g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg
                            .getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);
                    Image iconImage = ImageIO.read(file[file.length-1].getInputStream());
                    int newHeight;
                    int newWidth;
                    //判断源图片大小
                    if (buffImg.getHeight() <= buffImg.getWidth()) {
                        //获取缩放后印章的高度
                        newHeight = (int) Math.round(buffImg.getHeight() * 0.25);
                        //获取印章原高度
                        int height = iconImage.getHeight(null);
                        //获取缩放比例
                        double x = height / newHeight;
                        //获取缩放后的印章宽度
                        newWidth = (int) Math.round(iconImage.getWidth(null) / x);
                    } else {
                        //获取缩放后的印章宽度
                        newWidth = (int) Math.round(buffImg.getWidth() * 0.15);
                        //获取印章原宽度
                        int width = iconImage.getWidth(null);
                        //获取缩放比例
                        double x = width / newWidth;
                        //获取缩放后的印章高度
                        newHeight = (int) Math.round(iconImage.getHeight(null) / x);
                    }
                    //得到缩放后的印章对象
                    Image newIconImage = iconImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                    // 得到Image对象。
                    //Image img = imgIcon.getImage();
                    float alpha = 1f; // 透明度
                    g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
                            alpha));
                    // 表示水印图片的位置
                    g.drawImage(newIconImage, buffImg.getWidth() - newIconImage.getWidth(null),
                            buffImg.getHeight() - newIconImage.getHeight(null), null);

                    g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
                    g.dispose();
                    os = new FileOutputStream(newFile);
                    // 生成图片
                    ImageIO.write(buffImg,wei.substring(1,wei.length()), os);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (null != os) {
                            os.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                Picture picture = new Picture();
                picture.setAid(aid);
                picture.setName(name);
                picture.setPath(path);
                picture.setPsize(file[i].getSize());
                picture.setUpdateTime(new Date());
                pictureDao.save(picture);
            }
            return pictures;
        }else {
            for (int i = 0; i < file.length; i++) {
                String originalFilename = file[i].getOriginalFilename();
                String name = file[i].getOriginalFilename().substring(0, originalFilename.indexOf("."));
                String wei = originalFilename.substring(originalFilename.indexOf("."), originalFilename.length());
                String newPicName = UUID.randomUUID().toString().replaceAll("-", "") + wei;
                String path = staticProperties.getPictureuri() + staticProperties.getStaticport() + newPicName;
                pictures = pictures + path + ",";
                File newFile = new File(staticProperties.getPicturepath() + newPicName);
                file[i].transferTo(newFile);
                Picture picture = new Picture();
                picture.setAid(aid);
                picture.setName(name);
                picture.setPath(path);
                picture.setPsize(file[i].getSize());
                picture.setUpdateTime(new Date());
                pictureDao.save(picture);
            }
            return pictures;
        }
//        albumDao.updatePictures(pictures,aid);
}
}
