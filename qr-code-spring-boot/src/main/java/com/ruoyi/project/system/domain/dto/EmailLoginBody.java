package com.ruoyi.project.system.domain.dto;

import java.io.Serializable;

public class EmailLoginBody implements Serializable {

    /**
     * 用户名
     */
    private String email;



    /**
     * 验证码
     */
    private String code;


    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }



    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    @Override
    public String toString() {
        return "EmailLoginBody{" +
                "email='" + email + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
