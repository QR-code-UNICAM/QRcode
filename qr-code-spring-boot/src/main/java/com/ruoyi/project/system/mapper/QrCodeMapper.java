package com.ruoyi.project.system.mapper;


import com.ruoyi.project.system.domain.QrCode;
import com.ruoyi.project.system.domain.SysConfig;

import java.util.List;

/**
 * @InterfaceName：QrCodeMapper
 * @InterfaceDesc: 二维码Mapper
 *
 * @Author: liuxianhua
 * @Time: 2023-12-30 22:13
 */
public interface QrCodeMapper {

    /**
     * 通过user查询二维码
     *
     * @param qrCode 实体类
     * @return 二维码
     */
    public List<QrCode> selectQrcodeByUserList(QrCode qrCode);


    /**
     * 新增参数配置
     *
     * @param qrCode 二维码相关信息
     * @return 结果
     */
    public int insertQrcode(QrCode qrCode);
}
