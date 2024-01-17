package com.ruoyi.project.system.util;

import com.github.hui.quick.plugin.base.ColorUtil;
import com.github.hui.quick.plugin.qrcode.wrapper.QrCodeGenWrapper;
import com.github.hui.quick.plugin.qrcode.wrapper.QrCodeOptions;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.checkerframework.checker.units.qual.C;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class QrCodeUtil {

    public static String normal(String text) throws IOException, WriterException {
        return QrCodeGenWrapper.of(text).asString();
    }

    public static String logo(String text, InputStream logoFile) throws IOException, WriterException {
        return QrCodeGenWrapper.of(text)
                .setLogo(logoFile)
                .setLogoRate(7)
                .setLogoStyle(QrCodeOptions.LogoStyle.ROUND)
                .asString();
    }

    public static String color(String text, int r,int g,int b) throws IOException, WriterException {

        Color color = new Color(r,g,b);

        return QrCodeGenWrapper.of(text)
                .setDrawPreColor(color)
                .asString();
    }

    public static String bg(String text, InputStream bgFile) throws IOException, WriterException {
        return QrCodeGenWrapper.of(text)
                .setBgImg(bgFile)
                .setBgStyle(QrCodeOptions.BgImgStyle.PENETRATE)
                .setBgH(500)
                .setBgW(500)
                .setW(500)
                .setH(500)
                .asString();
    }

    public static String style(String text) throws IOException, WriterException {
        return QrCodeGenWrapper.of(text)
                .setBgH(500)
                .setBgW(500)
                .setW(500)
                .setH(500)
                .setDrawEnableScale(true)
                .setDrawStyle(QrCodeOptions.DrawStyle.CIRCLE)
                .asString();
    }

    public static String fill(String text, InputStream bgFile) throws IOException, WriterException {
        return QrCodeGenWrapper.of(text)
                .setW(500)
                .setH(500)
                .setDrawEnableScale(true)
                .setErrorCorrection(ErrorCorrectionLevel.H)
                .setDrawStyle(QrCodeOptions.DrawStyle.IMAGE)
                .addImg(1,1,bgFile)
                .asString();
    }

    public static String gif(String text, InputStream bgFile) throws IOException, WriterException {
        return QrCodeGenWrapper.of(text)
                .setW(500)
                .setH(500)
                .setBgImg(bgFile)
                .setBgOpacity(0.5f)
                .setPicType("gif")
                .asString();
    }







}

