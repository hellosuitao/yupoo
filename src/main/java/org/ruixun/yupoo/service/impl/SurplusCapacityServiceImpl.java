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
    public String addPictures(MultipartFile[] file, Long totalSize, Long id, Long aid) {
//        Album album = albumDao.findAlbumById(aid);
//        String pictures = album.getPictures();
        String pictures = "";
        for (int i = 0; i < file.length; i++) {
            String originalFilename = file[i].getOriginalFilename();
            String name = file[i].getOriginalFilename().substring(0, originalFilename.indexOf("."));
            String wei = originalFilename.substring(originalFilename.indexOf("."), originalFilename.length());
            String newPicName = UUID.randomUUID().toString().replaceAll("-", "") + wei;
//                String uri = "http://"+InetAddress.getLocalHost().getHostAddress()+":8080";
//                String path = "http://39.100.254.53:8090/" + newPicName;
            String path = staticProperties.getPictureuri() + staticProperties.getStaticport() + newPicName;
//                String filePath =this.getClass().getResource("/static/image/").getPath()+newPicName;
//                System.out.println(filePath+"----------------");
            pictures = pictures + path + ",";
            File newFile = new File(staticProperties.getPicturepath() + newPicName);
//                public static MultipartFile markImageByText(String logoText,
//                        Integer degree,
//                        Color color,
//                        String formatName,
//                        MultipartFile file){
//                    if (file == null){
//                        return null;
//                    }

            try {
                //获取源图片
                Image srcImage = ImageIO.read(file[i].getInputStream());
                BufferedImage bufferedImage = new BufferedImage(srcImage.getWidth(null),
                        srcImage.getHeight(null),
                        BufferedImage.TYPE_INT_RGB);
                //获取画笔
                Graphics2D graphics = bufferedImage.createGraphics();
                //设置对线段的锯齿状边缘处理
                graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                graphics.drawImage(srcImage.getScaledInstance(
                        srcImage.getWidth(null),
                        srcImage.getHeight(null),
                        Image.SCALE_SMOOTH),
                        0, 0, null);
                //设置水印旋转
//                    if (degree != null) {
//                        graphics.rotate(Math.toRadians(degree), bufferedImage.getWidth() / 2, bufferedImage.getHeight() / 2);
//                    }
                //设置颜色
                graphics.setColor(Color.black);
                //设置字体
                int fontSize = 60;
                graphics.setFont(new Font("微软雅黑", Font.BOLD, fontSize));
                //设置水印透明
                graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.3f));
                //绘制字符串
                graphics.drawString("test",
                        bufferedImage.getWidth() - fontSize * ("hellolyj".length() + 1),
                        bufferedImage.getHeight() - fontSize);
                graphics.dispose();

                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);
                ImageIO.write(bufferedImage, wei.substring(1,wei.length()), imOut);
                InputStream inputStream = new ByteArrayInputStream(bs.toByteArray());

                OutputStream outStream = new FileOutputStream(staticProperties.getPicturepath()+newPicName);
                IOUtils.copy(inputStream, outStream);
                inputStream.close();
                outStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
//                }


//                file[i].transferTo(newFile);

            /*存到项目文件image*/
            //当前项目image文件路径
//                String newPathName = new File("").getAbsolutePath()+"/src/main/resources/static/image/"+newPicName;
//                File newFile1 = new File(newPathName);
//                FileUtils.copyFile(new File(filePath),newFile1)
            Picture picture = new Picture();
            picture.setAid(aid);
            picture.setName(name);
            picture.setPath(path);
            picture.setPsize(file[i].getSize());
            picture.setUpdateTime(new Date());
            pictureDao.save(picture);
        }
        return pictures;
//        albumDao.updatePictures(pictures,aid);
    }
}
