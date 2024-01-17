package com.ruoyi.project.system.controller;

import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.project.system.domain.QrCode;
import com.ruoyi.project.system.domain.ResponseResult;
import com.ruoyi.project.system.service.IQrCodeService;
import com.ruoyi.project.system.util.QrCodeUtil;
import org.checkerframework.checker.units.qual.C;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.awt.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/system/qrcode")
public class QrcodeController {

    @Resource
    private IQrCodeService qrCodeService;

    @Log(title = "生成二维码")
    @PostMapping("/create")
    public ResponseResult createQrcode(@RequestParam(value = "file",required = false) MultipartFile file,
    //public ResponseResult createQrcode(
                                       @RequestParam String flag,
                                       @RequestParam String text,
                                       @RequestParam String user,
                                       @RequestParam int[] color){

        //MultipartFile file = null;
        System.out.println("生成二维码接收的参数： file===>"+ file);
        System.out.println("生成二维码接收的参数： flag===》  "+ flag);
        System.out.println("生成二维码接收的参数： text===》  "+ text);
        System.out.println("生成二维码接收的参数： user===》  "+ user);
        System.out.println("生成二维码接收的参数： color===》  "+ color);

        int r = color[0];
        int g = color[1];
        int b = color[2];

        String qrCode = " ";

        try {
            if(file == null){
                if("normal".equals(flag)){
                    //return ZxingUtil.createImage(text,null);
                    qrCode =  QrCodeUtil.normal(text);
                }else if("color".equals(flag)){
                    qrCode =  QrCodeUtil.color(text,r,g,b);
                }
                else if("style".equals(flag)){
                    qrCode =  QrCodeUtil.style(text);
                }

            }else{
                if("normal".equals(flag)){
                    //return ZxingUtil.createImage(text,file.getInputStream());
                    qrCode =  QrCodeUtil.logo(text,file.getInputStream());
                }
                if("background".equals(flag)){
                    //return ZxingUtil.createImage(text,file.getInputStream());
                    qrCode =  QrCodeUtil.bg(text,file.getInputStream());
                }

                if("logo".equals(flag)){
                    //return ZxingUtil.createImage(text,file.getInputStream());
                    qrCode =  QrCodeUtil.logo(text,file.getInputStream());
                }

                if("imageFill".equals(flag)){
                    //return ZxingUtil.createImage(text,file.getInputStream());
                    qrCode =  QrCodeUtil.fill(text,file.getInputStream());
                }
                if("gif".equals(flag)){
                    //return ZxingUtil.createImage(text,file.getInputStream());
                    qrCode =  QrCodeUtil.gif(text,file.getInputStream());
                }

            }

            QrCode qrCode1 = new QrCode();
            qrCode1.setQrcode(qrCode);
            qrCode1.setContent(text);
            qrCode1.setQrcodetype(flag);
            qrCode1.setUser(user);
            qrCode1.setCreattime(new Date());

            System.out.println("存储的对象 ==== 》  "+ qrCode1);

            int insertQrcode = qrCodeService.insertQrcode(qrCode1);

            if (insertQrcode > 0){
                System.out.println("生成的二维码已存储到数据库");
            }


            return new ResponseResult(200,"创建的二维码",qrCode1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseResult(500,"qrcode");
    }




    @Log(title = "用户生成二维码集合")
    @PostMapping("/qrcodelist")
    public ResponseResult qrCodeList( @RequestBody QrCode qrCode) {

        QrCode qrcode = new QrCode();

        qrcode.setUser(qrCode.getUser());

    List<QrCode> qrCodeList =  qrCodeService.selectQrcodeByUserList(qrcode);


    return new ResponseResult(200,"历史生成记录",qrCodeList);
    }



}
