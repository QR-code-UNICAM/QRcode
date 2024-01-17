package com.ruoyi.project.system.service;

public interface EmailService {
    public void sendMessageToEmail(String toAddress,String verifyCode);
}
