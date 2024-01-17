package com.ruoyi.project.system.service;

import com.ruoyi.project.system.domain.QrCode;
import com.ruoyi.project.system.domain.SysConfig;

import java.util.List;

/**

 * @InterfaceName：IQrCodeService
 * @InterfaceDesc:
 *
 * @Author: liuxianhua
 * @Time: 2023-12-30 23:03
 */
public interface IQrCodeService {

    /**
     * 查询用户二维码列表
     *
     * @param qrCode 二维码
     * @return 二维码集合
     */
    public List<QrCode> selectQrcodeByUserList(QrCode qrCode);

    /**
     * 用户生成二维码存储
     *
     * @param qrCode 二维码
     * @return 二维码集合
     */
    public int insertQrcode(QrCode qrCode);
}
