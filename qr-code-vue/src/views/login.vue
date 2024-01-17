<template xmlns="">
  <div class="container">
    <!-- 登录区域 -->
    <div class="content">
      <!-- 配图 -->
      <div class="pic"></div>
      <!-- 表单 -->
      <div class="field">
        <!-- [移动端]标题 -->
        <h2 class="mobile-title">
          <h3 class="title">QRCODE</h3>
        </h2>

        <!-- 表单 -->
        <div class="form-cont">
          <el-tabs class="form" v-model="loginForm.loginType" style=" float:none;">
            <el-tab-pane label="Email Verification Login" name="sms">
            </el-tab-pane>
        
          </el-tabs>
          <div>
            <el-form ref="loginForm" :model="loginForm" :rules="LoginRules" class="login-form">

              <!-- 邮箱验证码登录 -->
              <div v-if="loginForm.loginType === 'sms'">
                <el-form-item prop="email">
                  <el-input v-model="loginForm.email" type="text" auto-complete="off" placeholder="Enter your email">
                    <svg-icon slot="prefix" icon-class="email" class="el-input__icon input-icon"/>
                  </el-input>
                </el-form-item>
                <el-form-item prop="emailCode">
                  <el-input v-model="loginForm.emailCode" type="text" auto-complete="off" placeholder="Email Verification Code"
                            class="sms-login-mobile-code-prefix">
                    <template>
                      <svg-icon slot="prefix" icon-class="email" class="el-input__icon input-icon"/>
                    </template>
                    <template slot="append">
                      <span v-if="mobileCodeTimer <= 0" class="getMobileCode" @click="getSmsCode" style="cursor: pointer;">Get verification code</span>
                      <span v-if="mobileCodeTimer > 0" class="getMobileCode">{{ mobileCodeTimer }}seconds</span>
                    </template>
                  </el-input>
                </el-form-item>
              </div>

              <!-- 下方的登录按钮 -->
              <el-form-item style="width:100%;">
                <el-button :loading="loading" size="medium" type="primary" style="width:100%;"
                    @click.native.prevent="SmsLogin">
                  <span v-if="!loading">Log In</span>
                  <span v-else>Logging In...</span>
                </el-button>
              </el-form-item>

            </el-form>
          </div>
        </div>
      </div>
    </div>

    <!-- 图形验证码 -->
<!--    <Verify ref="verify" :captcha-type="'blockPuzzle'" :img-size="{width:'400px',height:'200px'}"-->
<!--            @success="handleLogin" />-->

    <!-- footer -->
    <div class="footer">
      Copyright © 2023-2024 QRCODE All Rights Reserved.
    </div>
  </div>
</template>

<script>
import {sendSmsCode, smsLogin} from "@/api/login";
import {getTenantIdByName} from "@/api/system/tenant";
import {SystemUserSocialTypeEnum} from "@/utils/constants";
import {getCaptchaEnable, getTenantEnable} from "@/utils/ruoyi";
import {
  getPassword,
  getRememberMe, getTenantName,
  getUsername,
  removePassword, removeRememberMe, removeTenantName,
  removeUsername,
  setPassword, setRememberMe, setTenantId, setTenantName, setToken,
  setUsername
} from "@/utils/auth";

import Verify from '@/components/Verifition/Verify';
import {resetUserPwd} from "@/api/system/user";

export default {
  name: "Login",
  components: {
    Verify
  },
  data() {
    return {
      codeUrl: "",
      captchaEnable: false,
      tenantEnable: false,
      mobileCodeTimer: 0,
      loginForm: {
        loginType: "sms",
        username: "admin",
        password: "admin123",
        captchaVerification: "",
        email: "",
        emailCode: "",
        rememberMe: false,
        tenantName: "QRCODE",
      },
      scene: 21,

      LoginRules: {
        username: [
          {required: true, trigger: "blur", message: "Username cannot be empty"}
        ],
        password: [
          {required: true, trigger: "blur", message: "Password cannot be empty"}
        ],
        email: [
          {required: true, trigger: "blur", message: "Email cannot be empty"},
          {
            validator: function (rule, value, callback) {
              if (/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(value) === false) {
                callback(new Error("Invalid email format"));
              } else {
                callback();
              }
            }, trigger: "blur"
          }
        ]
      },
      loading: false,
      redirect: undefined,
      // 枚举
      // SysUserSocialTypeEnum: SystemUserSocialTypeEnum,
    };
  },
  created() {
    // 租户开关
    // this.tenantEnable = getTenantEnable();
    // if (this.tenantEnable) {
    //   getTenantIdByName(this.loginForm.tenantName).then(res => { // 设置租户
    //     const tenantId = res.data;
    //     if (tenantId && tenantId >= 0) {
    //       setTenantId(tenantId)
    //     }
    //   });
    // }
    // 验证码开关
    // this.captchaEnable = getCaptchaEnable();
    // 重定向地址
    // this.redirect = this.$route.query.redirect ? decodeURIComponent(this.$route.query.redirect) : undefined;
    // this.getCookie();
  },
  methods: {
    // 邮箱登录
    SmsLogin() {
      const email = this.loginForm.email.trim()
      const emailCode = this.loginForm.emailCode
      return new Promise((resolve, reject) => {
        smsLogin(email,emailCode).then(res => {
          console.log(res)
          console.log(res.msg)
          if (res.msg === 'SUCCESS'){
            this.$router.push({path:'/qrcode',query:{email}});

          }else {
            console.error("Incorrect verification code. Please retrieve it again~")
            this.$notify.error({
              title: 'Error',
              message: 'Incorrect verification code. Please retrieve it again.~'
            });
          }

        }).catch(error => {
          reject(error)
        })
      })
    },
    getCode() {
      // 情况一，未开启：则直接登录
      if (!this.captchaEnable) {
        this.handleLogin({})
        return;
      }

      // 情况二，已开启：则展示验证码；只有完成验证码的情况，才进行登录
      // 弹出验证码
      this.$refs.verify.show()
    },
    getCookie() {
      const username = getUsername();
      const password = getPassword();
      const rememberMe = getRememberMe();
      const tenantName = getTenantName();
      this.loginForm = {
        ...this.loginForm,
        username: username ? username : this.loginForm.username,
        password: password ? password : this.loginForm.password,
        rememberMe: rememberMe ? getRememberMe() : false,
        tenantName: tenantName ? tenantName : this.loginForm.tenantName,
      };
    },
    handleLogin(captchaParams) {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true;
          // // 设置 Cookie
          // if (this.loginForm.rememberMe) {
          //   setUsername(this.loginForm.username)
          //   setPassword(this.loginForm.password)
          //   setRememberMe(this.loginForm.rememberMe)
          //   setTenantName(this.loginForm.tenantName)
          // } else {
          //   removeUsername()
          //   removePassword()
          //   removeRememberMe()
          //   removeTenantName()
          // }
          this.loginForm.captchaVerification = captchaParams.captchaVerification
          // 发起登陆
          // console.log("发起登录", this.loginForm);
          this.$store.dispatch(this.loginForm.loginType === "sms" ? "SmsLogin" : "Login", this.loginForm).then(() => {
            this.$router.push({path: this.redirect || "/"}).catch(() => {
            });
          }).catch(() => {
            this.loading = false;
          });
        }
      });
    },

    // async doSocialLogin(socialTypeEnum) {
    //   // 设置登录中
    //   this.loading = true;
    //   let tenant = false;
    //   if (this.tenantEnable) {
    //     await this.$prompt('请输入租户名称', "提示", {
    //       confirmButtonText: "确定",
    //       cancelButtonText: "取消"
    //     }).then(async ({value}) => {
    //       await getTenantIdByName(value).then(res => {
    //         const tenantId = res.data;
    //         tenant = true
    //         if (tenantId && tenantId >= 0) {
    //           setTenantId(tenantId)
    //         }
    //       });
    //     }).catch(() => {
    //       // 取消登录按钮 loading状态
    //       this.loading = false;
    //
    //       return false
    //     });
    //   } else {
    //     tenant = true
    //   }
    //  if(tenant){
    //    // 计算 redirectUri
    //    const redirectUri = location.origin + '/social-login?'
    //      + encodeURIComponent('type=' + socialTypeEnum.type + '&redirect=' + (this.redirect || "/")); // 重定向不能丢
    //    // const redirectUri = 'http://127.0.0.1:48080/api/gitee/callback';
    //    // const redirectUri = 'http://127.0.0.1:48080/api/dingtalk/callback';
    //    // 进行跳转
    //    socialAuthRedirect(socialTypeEnum.type, encodeURIComponent(redirectUri)).then((res) => {
    //      // console.log(res.url);
    //      window.location.href = res.data;
    //    });
    //  }
    // },
    /** ========== 以下为升级短信登录 ========== */
    getSmsCode() {
      if (this.mobileCodeTimer > 0) return;
      this.$refs.loginForm.validate(valid => {
        if (!valid) return;
        sendSmsCode(this.loginForm.email).then(res => {
          this.$modal.msgSuccess("获取验证码成功")
          this.mobileCodeTimer = 60;
          let msgTimer = setInterval(() => {
            this.mobileCodeTimer = this.mobileCodeTimer - 1;
            if (this.mobileCodeTimer <= 0) {
              clearInterval(msgTimer);
            }
          }, 1000);
        });
      });
    }
  }
};
</script>
<style lang="scss" scoped>
@import "~@/assets/styles/login.scss";


.oauth-login {
  display: flex;
  align-items: center;
  cursor:pointer;
}
.oauth-login-item {
  display: flex;
  align-items: center;
  margin-right: 10px;
}
.oauth-login-item img {
  height: 25px;
  width: 25px;
}
.oauth-login-item span:hover {
  text-decoration: underline red;
  color: red;
}
.sms-login-mobile-code-prefix {
  :deep(.el-input__prefix) {
    top: 22%;
  }
}
</style>
