package com.ruoyi.project.system.controller;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.ruoyi.framework.redis.RedisCache;
import com.ruoyi.project.system.domain.ResponseResult;
import com.ruoyi.project.system.domain.dto.EmailLoginBody;
import com.ruoyi.project.system.service.EmailService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.security.LoginBody;
import com.ruoyi.framework.security.service.SysLoginService;
import com.ruoyi.framework.security.service.SysPermissionService;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.system.domain.SysMenu;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.system.service.ISysMenuService;

import javax.annotation.Resource;

/**
 * 登录验证
 * 
 * @author ruoyi
 */
@RestController
public class SysLoginController
{
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    //设置验证码过期时间
    private final Integer EMAIL_EXPIRED_TIME = 5;

    @Resource RedisCache redisCache;

    @Autowired
    private EmailService emailService;


    @GetMapping("/code")
    public ResponseResult sendMessageToEmail(@RequestParam("email") String email) {
        if (StringUtils.isAnyBlank(email)) {
            return new ResponseResult(200, "邮箱为空");
        }

        String key = "email_code_" + email;

//        // 从redis中查看有没有该邮箱的验证码
        String verifyCode = redisCache.getCacheObject(key);
        if (!StringUtils.isAnyBlank(verifyCode)) {
            System.out.println("验证码已发送=>" + verifyCode);
        }

        //生成6位随机验证码
       verifyCode = UUID.randomUUID().toString().substring(0,6);

        emailService.sendMessageToEmail(verifyCode, email);
//        // 将该验证码存入redis
        redisCache.setCacheObject( key,
                verifyCode,
                EMAIL_EXPIRED_TIME,
                TimeUnit.MINUTES);


        return new ResponseResult(200,"验证码已发送");
    }

    @PostMapping("/login")
    public ResponseResult emaillogin(@RequestBody EmailLoginBody emailLoginBody)
    {

        System.out.println("传过来的对象：====》"+emailLoginBody);

      String login =   loginService.emaillogin(emailLoginBody.getEmail(),emailLoginBody.getCode() );

        System.out.println("登录状态=================="+login);
        return new  ResponseResult(200,login);
    }

    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login1")
    public AjaxResult login(@RequestBody LoginBody loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }


    /**
     * 获取用户信息
     * 
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 获取路由信息
     * 
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters()
    {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        return AjaxResult.success(menuService.buildMenus(menus));
    }
}
