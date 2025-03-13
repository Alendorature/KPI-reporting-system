import request from '@/utils/request'

// 查询老师基本信息列表
export function index(query) {
  return request({
    url: '/common/index',
    method: 'get',
    params: query
  })
}