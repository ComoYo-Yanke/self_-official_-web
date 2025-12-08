<script setup>
import { ref, reactive, computed } from 'vue'
import { productAPI } from '@/api/products'
import { authAPI } from '@/api/auth'
import { fileAPI } from '@/api/file'

const testDepts = async () => {
    try{
        const response = await fileAPI.testDepts();
        console.log("测试接口返回:" + response);
    }catch(err){
        console.error("测试接口错误:" + err);
        
    }
}
window.addEventListener('load', testDepts);

// 登录认证

// 产品数据


// 文件数据

</script>

<template>
    <body>
        
    </body>
</template>

<style>
</style>
