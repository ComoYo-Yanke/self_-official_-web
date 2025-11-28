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

// 管理员模式
const isAdminMode = ref(false)
// 产品数据
const products = ref([
    {
        id: 1,
        name: 'LiMind',
        description: '一款免费的极简思维导图工具。',
        image: '/static/LiMind.png',
        downloadUrl: '#',
        size: '45.2 MB',
        version: 'v2.1.0'
    },
    {
        id: 2,
        name: 'CodeSnippet Manager',
        description: '智能代码片段管理工具，支持多语言高亮和团队协作，提升开发效率的得力助手。',
        image: 'https://images.unsplash.com/photo-1555066931-4365d14bab8c?w=400&h=200&fit=crop',
        downloadUrl: '#',
        size: '28.7 MB',
        version: 'v1.4.2'
    },
    {
        id: 3,
        name: 'Design System Kit',
        description: '完整的设计系统组件库，包含100+精心设计的UI组件，帮助团队保持设计一致性。',
        image: 'https://images.unsplash.com/photo-1561070791-2526d30994b5?w=400&h=200&fit=crop',
        downloadUrl: '#',
        size: '15.3 MB',
        version: 'v3.0.1'
    },
    {
        id: 4,
        name: 'API Dashboard',
        description: '实时API监控和分析面板，提供详细的性能指标和错误追踪，保障服务稳定性。',
        image: 'https://images.unsplash.com/photo-1551288049-bebda4e38f71?w=400&h=200&fit=crop',
        downloadUrl: '#',
        size: '62.8 MB',
        version: 'v1.2.3'
    }
])

// 文件数据
const files = ref([
    {
        id: 1,
        name: 'QuickNote-Pro-v2.1.0.dmg',
        size: '45.2 MB',
        uploadTime: '2024-01-15',
        downloadUrl: '#'
    },
    {
        id: 2,
        name: 'CodeSnippet-Manager-Windows.exe',
        size: '28.7 MB',
        uploadTime: '2024-01-10',
        downloadUrl: '#'
    },
    {
        id: 3,
        name: 'Design-System-Kit-v3.0.1.zip',
        size: '15.3 MB',
        uploadTime: '2024-01-08',
        downloadUrl: '#'
    }
])

// 新产品表单
const newProduct = reactive({
    name: '',
    description: '',
    file: null
})

// 计算属性
const canAddProduct = computed(() => {
    return newProduct.name.trim() &&
        newProduct.description.trim() &&
        newProduct.file
})

// 方法
const toggleAdminMode = () => {
    isAdminMode.value = !isAdminMode.value
}

const handleFileSelect = (event) => {
    newProduct.file = event.target.files[0]
}

const addProduct = () => {
    if (!canAddProduct.value) return

    const newId = Math.max(...products.value.map(p => p.id)) + 1

    products.value.push({
        id: newId,
        name: newProduct.name,
        description: newProduct.description,
        image: 'https://images.unsplash.com/photo-1551650975-87deedd944c3?w=400&h=200&fit=crop',
        downloadUrl: '#',
        size: `${(newProduct.file.size / (1024 * 1024)).toFixed(1)} MB`,
        version: 'v1.0.0'
    })

    // 同时添加到文件列表
    files.value.unshift({
        id: Math.max(...files.value.map(f => f.id)) + 1,
        name: newProduct.file.name,
        size: `${(newProduct.file.size / (1024 * 1024)).toFixed(1)} MB`,
        uploadTime: new Date().toISOString().split('T')[0],
        downloadUrl: '#'
    })

    // 重置表单
    newProduct.name = ''
    newProduct.description = ''
    newProduct.file = null
    event.target.value = ''
}

const deleteProduct = (id) => {
    if (confirm('确定要删除这个产品吗？')) {
        products.value = products.value.filter(p => p.id !== id)
    }
}

const deleteFile = (id) => {
    if (confirm('确定要删除这个文件吗？')) {
        files.value = files.value.filter(f => f.id !== id)
    }
}
</script>

<template>
    <div class="home">
        <!-- 导航栏 -->
        <nav class="navbar">
            <div class="container navbar-content">
                <a href="#" class="logo">
                    <svg width="50" height="50" viewBox="0 0 200 200" xmlns="http://www.w3.org/2000/svg">
                        <!-- 背景圆形 -->
                        <circle cx="100" cy="100" r="90" fill="#f8f9fa" stroke="#e9ecef" stroke-width="2" />

                        <!-- 主要图标 - 简约的几何组合 -->
                        <g transform="translate(100, 100)">
                            <!-- 中心六边形 -->
                            <polygon points="0,-35 30,-17.5 30,17.5 0,35 -30,17.5 -30,-17.5" fill="none"
                                stroke="#2563eb" stroke-width="3" stroke-linejoin="round" />

                            <!-- 内部圆环 -->
                            <circle cx="0" cy="0" r="18" fill="none" stroke="#3b82f6" stroke-width="2.5" />

                            <!-- 中心点 -->
                            <circle cx="0" cy="0" r="6" fill="#1d4ed8" />

                            <!-- 四个方向的小圆点 -->
                            <circle cx="0" cy="-25" r="3" fill="#60a5fa" />
                            <circle cx="25" cy="0" r="3" fill="#60a5fa" />
                            <circle cx="0" cy="25" r="3" fill="#60a5fa" />
                            <circle cx="-25" cy="0" r="3" fill="#60a5fa" />
                        </g>

                        <!-- 外圈的微妙装饰元素 -->
                        <g transform="translate(100, 100)" opacity="0.3">
                            <circle cx="0" cy="-60" r="2" fill="#94a3b8" />
                            <circle cx="42" cy="-42" r="2" fill="#94a3b8" />
                            <circle cx="60" cy="0" r="2" fill="#94a3b8" />
                            <circle cx="42" cy="42" r="2" fill="#94a3b8" />
                            <circle cx="0" cy="60" r="2" fill="#94a3b8" />
                            <circle cx="-42" cy="42" r="2" fill="#94a3b8" />
                            <circle cx="-60" cy="0" r="2" fill="#94a3b8" />
                            <circle cx="-42" cy="-42" r="2" fill="#94a3b8" />
                        </g>
                    </svg>
                    <span>Dev</span><span class="logo-accent">Portfolio</span>
                </a>
                <button class="btn btn-outline" @click="toggleAdminMode">
                    {{ isAdminMode ? '退出管理' : '管理后台' }}
                </button>
            </div>
        </nav>

        <!-- Hero区域 -->
        <section class="hero">
            <div class="container hero-content">
                <div class="profile">
                    <img src="/static/hero-bg-img.png" alt="Profile" class="profile-avatar">
                    <h1 class="profile-name">CoMoYo-Yanke</h1>
                    <p class="profile-bio">全栈开发者 & 产品设计师 | 专注于创造优雅的数字体验</p>
                    <div class="profile-contact">
                        <a href="https://space.bilibili.com/489802543?spm_id_from=333.1007.0.0" target="_blank"
                            class="contact-link">
                            <span>Bilibili</span>
                        </a>
                        <a href="https://github.com/CoMoYo-Yanke" target="_blank" class="contact-link">
                            <span>GitHub</span>
                        </a>
                        <a href="https://gitee.com/yanke_4_0/" target="_blank" class="contact-link">
                            <span>Gitee</span>
                        </a>
                    </div>
                </div>
            </div>
        </section>

        <!-- 产品展示 -->
        <section class="section">
            <div class="container">
                <h2 class="section-title">我的作品</h2>
                <p class="section-subtitle">
                    这里展示了我独立开发的一些产品和工具，每个项目都倾注了心血和热情
                </p>

                <div class="products-grid">
                    <div v-for="product in products" :key="product.id" class="product-card">
                        <img :src="product.image" :alt="product.name" class="product-image">
                        <div class="product-content">
                            <h3 class="product-title">{{ product.name }}</h3>
                            <p class="product-description">{{ product.description }}</p>
                            <div class="product-meta">
                                <span class="product-size">{{ product.size }}</span>
                                <div class="product-actions">
                                    <a :href="product.downloadUrl" class="btn btn-primary btn-sm" download>
                                        下载
                                    </a>
                                    <button v-if="isAdminMode" class="btn btn-danger btn-sm"
                                        @click="deleteProduct(product.id)">
                                        删除
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- 管理员上传区域 -->
                <div v-if="isAdminMode" class="upload-area mt-8">
                    <h3 class="upload-title">上传新产品</h3>
                    <div class="upload-form">
                        <div class="form-group">
                            <label class="form-label">产品名称</label>
                            <input v-model="newProduct.name" type="text" class="form-input" placeholder="输入产品名称">
                        </div>
                        <div class="form-group">
                            <label class="form-label">产品描述</label>
                            <textarea v-model="newProduct.description" class="form-textarea"
                                placeholder="描述产品功能和特点"></textarea>
                        </div>
                        <div class="form-group">
                            <label class="form-label">产品文件</label>
                            <input type="file" class="form-file" @change="handleFileSelect"
                                accept=".zip,.rar,.7z,.tar.gz">
                        </div>
                        <button class="btn btn-primary" @click="addProduct" :disabled="!canAddProduct">
                            添加产品
                        </button>
                    </div>
                </div>
            </div>
        </section>

        <!-- 文件管理（仅管理员可见） -->
        <section v-if="isAdminMode" class="section files-section">
            <div class="container">
                <h2 class="section-title">文件管理</h2>
                <p class="section-subtitle">
                    管理所有已上传的文件和资源
                </p>

                <div class="files-table">
                    <table>
                        <thead>
                            <tr>
                                <th>文件名</th>
                                <th>大小</th>
                                <th>上传时间</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="file in files" :key="file.id">
                                <td>{{ file.name }}</td>
                                <td>{{ file.size }}</td>
                                <td>{{ file.uploadTime }}</td>
                                <td>
                                    <div class="flex gap-2">
                                        <a :href="file.downloadUrl" class="btn btn-outline btn-sm" download>
                                            下载
                                        </a>
                                        <button class="btn btn-danger btn-sm" @click="deleteFile(file.id)">
                                            删除
                                        </button>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </section>

        <!-- 页脚 -->
        <footer class="section bg-gray-50">
            <div class="container text-center">
                <p class="text-gray-600">
                    © 2025 CoMoYo-Yanke. 保留所有权利.
                    <span class="text-primary-500">用心创造，分享价值</span>
                </p>
            </div>
        </footer>
    </div>
</template>

<style scoped>
/* 基础样式 */
.container {
    max-width: 1200px;
    margin: 0 auto;
    padding: 0 20px;
}

/* 导航栏 */
.navbar {
    background: #fff;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    position: sticky;
    top: 0;
    z-index: 100;
}

.nav-content {
    display: flex;
    justify-content: space-between;
    align-items: center;
    height: 60px;
}

.logo {
    color: #333;
    font-size: 1.5rem;
}

.admin-btn {
    background: #007bff;
    color: white;
    border: none;
    padding: 8px 16px;
    border-radius: 5px;
    cursor: pointer;
}

/* Hero区域 */
.hero {
    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
    color: white;
    padding: 80px 0;
    text-align: center;
}

.avatar {
    width: 120px;
    height: 120px;
    border-radius: 50%;
    margin-bottom: 20px;
    border: 4px solid white;
}

.hero h1 {
    font-size: 2.5rem;
    margin-bottom: 10px;
}

.hero p {
    font-size: 1.2rem;
    margin-bottom: 20px;
    opacity: 0.9;
}

.contact a {
    color: white;
    margin: 0 10px;
    text-decoration: none;
}

/* 产品区域 */
.products {
    padding: 80px 0;
    background: #f8f9fa;
}

.products h2 {
    text-align: center;
    font-size: 2rem;
    margin-bottom: 40px;
    color: #333;
}

.products-grid {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
    gap: 30px;
    margin-bottom: 40px;
}

.product-card {
    background: white;
    padding: 30px;
    border-radius: 10px;
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
    position: relative;
}

.product-card h3 {
    color: #333;
    margin-bottom: 15px;
}

.product-card p {
    color: #666;
    margin-bottom: 20px;
    line-height: 1.6;
}

.product-footer {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.download-btn {
    background: #28a745;
    color: white;
    padding: 8px 16px;
    border-radius: 5px;
    text-decoration: none;
}

.delete-btn {
    background: #dc3545;
    color: white;
    border: none;
    padding: 5px 10px;
    border-radius: 3px;
    cursor: pointer;
    margin-top: 10px;
}

/* 管理员面板 */
.admin-panel {
    background: white;
    padding: 30px;
    border-radius: 10px;
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
}

.admin-panel h3 {
    margin-bottom: 20px;
    color: #333;
}

.upload-form {
    display: flex;
    flex-direction: column;
    gap: 15px;
}

.upload-form input,
.upload-form textarea {
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 5px;
    font-size: 16px;
}

.upload-form textarea {
    min-height: 100px;
    resize: vertical;
}

.upload-form button {
    background: #007bff;
    color: white;
    border: none;
    padding: 12px;
    border-radius: 5px;
    cursor: pointer;
    font-size: 16px;
}

/* 页脚 */
footer {
    background: #333;
    color: white;
    text-align: center;
    padding: 30px 0;
}

/* 响应式 */
@media (max-width: 768px) {
    .container {
        padding: 0 15px;
    }

    .hero h1 {
        font-size: 2rem;
    }

    .products-grid {
        grid-template-columns: 1fr;
    }
}
</style>