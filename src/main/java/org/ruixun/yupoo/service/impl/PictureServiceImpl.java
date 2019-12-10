package org.ruixun.yupoo.service.impl;

import org.ruixun.yupoo.bean.Album;
import org.ruixun.yupoo.bean.DelPicture;
import org.ruixun.yupoo.bean.Picture;
import org.ruixun.yupoo.dao.AlbumDao;
import org.ruixun.yupoo.dao.DelPictureRepository;
import org.ruixun.yupoo.dao.PictureDao;
import org.ruixun.yupoo.service.PictureService;
import org.ruixun.yupoo.utils.StaticProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

/*
* 作者：suitao*/
@Service
@Transactional
public class PictureServiceImpl implements PictureService {/*图片service*/
    @Autowired
    private PictureDao pictureDao;
    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private DelPictureRepository delPictureRepository;
    @Autowired
    private StaticProperties staticProperties;
    @Override
    public Picture findPictureById(Long id) {
        return pictureDao.findPictureById(id);
    }

    @Override
    public List<Picture> findPicturesByAid(Long aid) {
        return pictureDao.findPicturesByAid(aid);
    }

    @Override
    public void deleteById(Long id,Long userId) {
        /*添加到回收站*/
        Picture picture = pictureDao.findPictureById(id);
        Long aid = picture.getAid();
        Album album = albumDao.findAlbumById(aid);
        String pictures = album.getPictures();
        pictures =  pictures.replaceAll(picture.getPath()+",","");
        albumDao.updatePictures(pictures,aid);
        delPictureRepository.save(new DelPicture(aid,id,picture.getName(),picture.getPath(),picture.getPsize(),userId,picture.getUploadTime(),new Date()));
        pictureDao.deleteById(id);
    }

    @Override
    public List<Picture> findPicturesByAidInAndNameLike(List<Long> ids, String name) {

        return pictureDao.findPicturesByAidInAndNameLike(ids,name);
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        pictureDao.deleteByIdIn(ids);
    }

    @Override
    public void deleteByAids(List<Long> aids) {
        pictureDao.deleteByAidIn(aids);
    }

    @Override
    public String setWaterMark(MultipartFile[] file,List<Long> picIds) {
        String waterfile = file[0].getOriginalFilename();
            picIds.forEach(picid->{
                Picture picture = pictureDao.findPictureById(picid);
                String pictureUri = picture.getPath();
                String uuidName = pictureUri.split(staticProperties.getStaticport())[1];
                File newFile = new File(staticProperties.getPicturepath() + uuidName);
                String wei = uuidName.substring(uuidName.indexOf("."));
                File originalPic = new File(staticProperties.getPicturepath()+uuidName);
                /*新增图片水印*/
                OutputStream os = null;
                try {
                    //获取源图片对象
                    Image srcImg = ImageIO.read(originalPic);
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
                    Image iconImage = ImageIO.read(file[file.length - 1].getInputStream());
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
                    ImageIO.write(buffImg, wei.substring(1, wei.length()), os);
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
            });
            return "success";
        }
}
