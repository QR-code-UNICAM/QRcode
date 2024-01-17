package com.ruoyi.project.system.domain.dto;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;




    public class CreateCode implements Serializable {

        /**
         * 用户邮箱
         */
        private String user;

        /**
         * 二维码类型
         */
        private String flag;

        /**
         * 二维码链接、文本
         */
        private String text;





        /**
         * 二维码相关图片
         */
        private MultipartFile logoFile ;

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public MultipartFile getLogoFile() {
            return logoFile;
        }

        public void setLogoFile(MultipartFile logoFile) {
            this.logoFile = logoFile;
        }

        @Override
        public String toString() {
            return "createCode{" +
                    "user='" + user + '\'' +
                    ", flag='" + flag + '\'' +
                    ", text='" + text + '\'' +
                    ", logoFile='" + logoFile + '\'' +
                    '}';
        }
    }
