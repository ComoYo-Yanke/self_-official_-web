import request from './request.js'

export const fileAPI = {
    // 获取文件列表
    getFiles() {
        return request.get('/files');
    },

    // 删除文件
    deleteFile(id) {
        return request.delete(`/files/${id}`);
    },

    // 上传文件
    uploadFile(formData) {
        return request.post('/files/upload', formData);
    },

    testDepts() {
        console.log("test!!!!!");
        return request.get('/depts');
    }
}