package org.ruixun.yupoo.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author suitao
 * @description
 */
@Component
@PropertySource(value = {"classpath:staticconfig.properties"})
public class StaticProperties {
    @Value("${sc.picturepath}")
    private String picturepath;
    @Value("${sc.audiopath}")
    private String audiopath;
    @Value("${sc.pictureuri}")
    private String pictureuri;
    @Value("${sc.audiouri}")
    private String audiouri;
    @Value("${sc.staticport}")
    private String staticport;

    @Override
    public String toString() {
        return "StaticProperties{" +
                "picturepath='" + picturepath + '\'' +
                ", audiopath='" + audiopath + '\'' +
                ", pictureuri='" + pictureuri + '\'' +
                ", audiouri='" + audiouri + '\'' +
                ", staticport='" + staticport + '\'' +
                '}';
    }

    public String getPicturepath() {
        return picturepath;
    }

    public void setPicturepath(String picturepath) {
        this.picturepath = picturepath;
    }

    public String getAudiopath() {
        return audiopath;
    }

    public void setAudiopath(String audiopath) {
        this.audiopath = audiopath;
    }

    public String getPictureuri() {
        return pictureuri;
    }

    public void setPictureuri(String pictureuri) {
        this.pictureuri = pictureuri;
    }

    public String getAudiouri() {
        return audiouri;
    }

    public void setAudiouri(String audiouri) {
        this.audiouri = audiouri;
    }

    public String getStaticport() {
        return staticport;
    }

    public void setStaticport(String staticport) {
        this.staticport = staticport;
    }

    public StaticProperties() {
    }
}
