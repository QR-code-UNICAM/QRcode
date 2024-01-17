package com.ruoyi.project.system.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import java.io.Serializable;
import java.util.Date;

/**
 * @className: QrCode
 * @description: 二维码实体类
 * @author: liuxianhua
 * @time: 2023-12-30 21:52
 */

public class QrCode implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 参数主键 */
    @Excel(name = "参数主键", cellType = Excel.ColumnType.NUMERIC)
    private int id;

    /** 二维码 */

    @Excel(name = "二维码")
    private String qrcode;

    /** 用户 */
    @Excel(name = "用户")
    private String user;

    /** 创建时间 */
    @Excel(name = "创建时间")
    private Date creattime;

    /** 二维码信息 */
    @Excel(name = "二维码信息")
    private String content;

    /** 二维码类型 */
    @Excel(name = "二维码类型")
    private String qrcodetype;

    /** 拓展列1 */
    @Excel(name = "拓展列1")
    private String col1;

    /** 拓展列2 */
    @Excel(name = "拓展列2")
    private String col2;

    /** 拓展列3 */
    @Excel(name = "拓展列3")
    private String col3;


    public QrCode() {
    }

    public QrCode(int id, String qrcode, String user, Date creattime, String content, String qrcodetype, String col1, String col2, String col3) {
        this.id = id;
        this.qrcode = qrcode;
        this.user = user;
        this.creattime = creattime;
        this.content = content;
        this.qrcodetype = qrcodetype;
        this.col1 = col1;
        this.col2 = col2;
        this.col3 = col3;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getCreattime() {
        return creattime;
    }

    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getQrcodetype() {
        return qrcodetype;
    }

    public void setQrcodetype(String qrcodetype) {
        this.qrcodetype = qrcodetype;
    }

    public String getCol1() {
        return col1;
    }

    public void setCol1(String col1) {
        this.col1 = col1;
    }

    public String getCol2() {
        return col2;
    }

    public void setCol2(String col2) {
        this.col2 = col2;
    }

    public String getCol3() {
        return col3;
    }

    public void setCol3(String col3) {
        this.col3 = col3;
    }

    @Override
    public String toString() {
        return "QrCode{" +
                "id=" + id +
                ", qrcode='" + qrcode + '\'' +
                ", user='" + user + '\'' +
                ", creattime=" + creattime +
                ", content='" + content + '\'' +
                ", qrcodetype='" + qrcodetype + '\'' +
                ", col1='" + col1 + '\'' +
                ", col2='" + col2 + '\'' +
                ", col3='" + col3 + '\'' +
                '}';
    }
}