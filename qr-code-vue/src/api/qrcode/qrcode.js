import request from '@/utils/request'
import { getRefreshToken } from '@/utils/auth'
import service from '@/utils/request'



// 生成二维码
export function createCode(text,flag,user) {
  return request({
    url: '/system/qrcode/create',
    method: 'post',
    data: {
      text, flag,user
    }
  })
}

// 获取二维码历史记录
export function codeList() {
  return request({
    url: '/system/qrcode/qrcodelist',
    method: 'post'
  })
}
