import request from '@/utils/request'
import { getRefreshToken } from '@/utils/auth'
import service from '@/utils/request'


// 生成二维码
export function createCode(formData) {
  return request({
    url: '/system/qrcode/create',
    method: 'post',
    data: formData
  })
}

// 获取二维码历史记录
export function codeList(email) {
  return request({
    url: '/system/qrcode/qrcodelist',
    method: 'post',
    // 在 data 中传递参数
    data: {
      user: email
    }
  })
}


// 获取邮箱登录验证码
export function sendSmsCode(email) {
  return request({
    url: '/code?email=' + email,
    method: 'get'

  })
}

// 邮箱验证码登录
export function smsLogin(email, code) {
  return request({
    url: '/login',
    method: 'post',
    data: {
      email,
      code
    }
  })
}

// 刷新访问令牌
export function refreshToken() {
  return service({
    url: '/system/auth/refresh-token?refreshToken=' + getRefreshToken(),
    method: 'post'
  })
}

// ========== OAUTH 2.0 相关 ==========

export function getAuthorize(clientId) {
  return request({
    url: '/system/oauth2/authorize?clientId=' + clientId,
    method: 'get'
  })
}

export function authorize(responseType, clientId, redirectUri, state,
  autoApprove, checkedScopes, uncheckedScopes) {
  // 构建 scopes
  const scopes = {}
  for (const scope of checkedScopes) {
    scopes[scope] = true
  }
  for (const scope of uncheckedScopes) {
    scopes[scope] = false
  }
  // 发起请求
  return service({
    url: '/system/oauth2/authorize',
    headers: {
      'Content-type': 'application/x-www-form-urlencoded'
    },
    params: {
      response_type: responseType,
      client_id: clientId,
      redirect_uri: redirectUri,
      state: state,
      auto_approve: autoApprove,
      scope: JSON.stringify(scopes)
    },
    method: 'post'
  })
}

// 获取验证图片  以及token
export function reqGet(data) {
  return request({
    url: 'system/captcha/get',
    method: 'post',
    data
  })
}

// 滑动或者点选验证
export function reqCheck(data) {
  return request({
    url: '/system/captcha/check',
    method: 'post',
    data
  })
}

export class socialBindLogin {
}
