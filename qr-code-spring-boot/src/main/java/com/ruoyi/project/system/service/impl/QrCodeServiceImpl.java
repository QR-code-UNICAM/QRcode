package com.ruoyi.project.system.service.impl;

import com.ruoyi.project.system.domain.QrCode;
import com.ruoyi.project.system.mapper.QrCodeMapper;
import com.ruoyi.project.system.service.IQrCodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @className: QrCodeServiceImpl
 * @description: 二维码实现类
 * @author: liuxianhua
 * @time: 2023-12-30 23:09
 */

@Service
public class QrCodeServiceImpl implements IQrCodeService {

    @Resource
    private QrCodeMapper qrCodeMapper;

    @Override
    public List<QrCode> selectQrcodeByUserList(QrCode qrCode) {

        return qrCodeMapper.selectQrcodeByUserList(qrCode);

    }

    @Override
    public int insertQrcode(QrCode qrCode) {

        int row = qrCodeMapper.insertQrcode(qrCode);

        return row;
    }
}
