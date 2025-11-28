import request from './request.js'

export const productAPI = {
    // 获取产品列表
    getProducts() {
        return request.get('/products')
    },

    // 获取单个产品
    getProduct(id) {
        return request.get(`/products/${id}`)
    },

    // 创建产品
    createProduct(data) {
        return request.post('/products', data)
    },

    // 更新产品
    updateProduct(id, data) {
        return request.put(`/products/${id}`, data)
    },

    // 删除产品
    deleteProduct(id) {
        return request.delete(`/products/${id}`)
    }
}