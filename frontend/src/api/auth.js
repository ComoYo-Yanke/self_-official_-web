import request from './request.js'

export const authAPI = {
    // 用户登录
    login(credentials) {
        return request.post('/auth/login', credentials)
    },

    // 用户退出
    logout() {
        return request.post('/auth/logout')
    },

    // 获取用户信息
    getProfile() {
        return request.get('/user/profile')
    }
}