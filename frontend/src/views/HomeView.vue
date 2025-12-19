<script setup>
import { ref, reactive, computed } from 'vue'
import { productAPI } from '@/api/products'
import { authAPI } from '@/api/auth'
import { fileAPI } from '@/api/file'

const testDepts = async () => {
    try {
        const response = await fileAPI.testDepts();
        console.log("测试接口返回:" + response);
    } catch (err) {
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
        <section class="container">
            <div class="left-box">
                <div class="header-icon">
                </div>
                <div class="left-position">
                    <div class="position-content">china</div>
                    <div class="position-content">Sisa</div>
                </div>
                <div class="left-status"></div>
                <div class="left-time-point"></div>
            </div>
            <div class="right-box"></div>
        </section>
    </body>
</template>

<style>
    * {
            height: 100%;
            width: 100%;
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            overflow: hidden;
            background-size: cover;
            background-repeat: no-repeat;
        }

</style>
