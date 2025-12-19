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
document.addEventListener('DOMContentLoaded', function () {
    // 标签切换功能
    const tabs = document.querySelectorAll('.tab');
    const tabContents = document.querySelectorAll('.tab-content');

    tabs.forEach(tab => {
        tab.addEventListener('click', () => {
            const tabId = tab.getAttribute('data-tab');

            // 移除所有标签的active类
            tabs.forEach(t => t.classList.remove('active'));
            // 隐藏所有内容
            tabContents.forEach(content => {
                content.style.display = 'none';
                content.classList.remove('active');
            });

            // 激活当前标签
            tab.classList.add('active');
            // 显示对应内容
            const activeContent = document.getElementById(tabId);
            activeContent.style.display = 'block';
            activeContent.classList.add('active');
        });
    });

    // 生成30天贡献图
    const contributionGraph = document.getElementById('contributionGraph');
    const levels = [0, 1, 2, 3, 4];

    for (let i = 0; i < 30; i++) {
        const cell = document.createElement('div');
        const randomLevel = levels[Math.floor(Math.random() * levels.length)];
        cell.className = `graph-cell level-${randomLevel}`;
        cell.title = `${30 - i} 天前: ${randomLevel === 0 ? '无活动' : `${randomLevel} 次贡献`}`;
        contributionGraph.appendChild(cell);
    }

    // 生成年度贡献图（简化的12个月版本）
    const yearlyGraph = document.getElementById('yearlyGraph');
    const months = ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'];

    months.forEach(month => {
        const monthContainer = document.createElement('div');
        monthContainer.style.display = 'flex';
        monthContainer.style.flexDirection = 'column';
        monthContainer.style.gap = '4px';
        monthContainer.style.alignItems = 'center';

        const monthLabel = document.createElement('div');
        monthLabel.textContent = month;
        monthLabel.style.fontSize = '11px';
        monthLabel.style.color = 'var(--github-text-secondary)';
        monthLabel.style.marginBottom = '4px';

        monthContainer.appendChild(monthLabel);

        // 每个月生成4周的贡献图
        for (let week = 0; week < 4; week++) {
            const weekContainer = document.createElement('div');
            weekContainer.style.display = 'flex';
            weekContainer.style.gap = '2px';

            // 每周生成7天的贡献图
            for (let day = 0; day < 7; day++) {
                const cell = document.createElement('div');
                const randomLevel = levels[Math.floor(Math.random() * levels.length)];
                cell.className = `graph-cell level-${randomLevel}`;
                cell.style.width = '10px';
                cell.style.height = '10px';
                weekContainer.appendChild(cell);
            }

            monthContainer.appendChild(weekContainer);
        }

        yearlyGraph.appendChild(monthContainer);
    });

    // 模拟项目卡片悬停效果
    const projectCards = document.querySelectorAll('.project-card');
    projectCards.forEach(card => {
        card.addEventListener('mouseenter', function () {
            this.style.boxShadow = '0 4px 12px rgba(0, 0, 0, 0.3)';
        });

        card.addEventListener('mouseleave', function () {
            this.style.boxShadow = 'none';
        });
    });

    // 模拟动态加载效果
    console.log('GitHub风格个人官网已加载');
});
</script>

<template>
    <div class="container">
        <!-- 头部导航 -->
        <header class="header">
            <div class="nav container">
                <div class="nav-logo">
                    <i class="fab fa-github"></i>
                    <span>个人官网</span>
                </div>
                <div class="nav-links">
                    <a href="#" class="nav-link">概览</a>
                    <a href="#" class="nav-link">项目</a>
                    <a href="#" class="nav-link">动态</a>
                    <a href="#" class="nav-link">简历</a>
                    <img src="https://avatars.githubusercontent.com/u/583231?v=4" alt="头像" class="avatar">
                </div>
            </div>
        </header>

        <!-- 主要内容 -->
        <main class="main-content container">
            <!-- 左侧边栏 - 个人资料 -->
            <aside class="sidebar">
                <div class="profile-card">
                    <div class="profile-header">
                        <img src="https://avatars.githubusercontent.com/u/583231?v=4" alt="个人头像" class="profile-avatar">
                        <div class="profile-info">
                            <h2>开发者姓名</h2>
                            <div class="profile-username">@dev-username</div>
                            <div class="profile-bio">全栈开发者 | Vue.js爱好者 | 开源贡献者</div>
                        </div>
                    </div>

                    <div class="profile-stats">
                        <div class="stat-item">
                            <span class="stat-count">24</span>
                            <span>项目</span>
                        </div>
                        <div class="stat-item">
                            <span class="stat-count">128</span>
                            <span>粉丝</span>
                        </div>
                        <div class="stat-item">
                            <span class="stat-count">56</span>
                            <span>关注</span>
                        </div>
                    </div>

                    <div class="profile-location">
                        <i class="fas fa-map-marker-alt"></i>
                        <span>中国 · 上海</span>
                    </div>
                    <div class="profile-email">
                        <i class="fas fa-envelope"></i>
                        <span>dev@example.com</span>
                    </div>
                </div>

                <div class="profile-card">
                    <h3 style="margin-bottom: 15px; font-size: 16px;">热门项目</h3>
                    <div style="margin-bottom: 10px;">
                        <a href="#"
                            style="color: var(--github-blue); text-decoration: none; font-size: 14px; display: block; margin-bottom: 8px;">
                            <i class="fas fa-book"></i> vue-admin-system
                        </a>
                        <div style="font-size: 12px; color: var(--github-text-secondary);">
                            基于Vue 3的管理系统
                        </div>
                    </div>
                    <div>
                        <a href="#"
                            style="color: var(--github-blue); text-decoration: none; font-size: 14px; display: block; margin-bottom: 8px;">
                            <i class="fas fa-book"></i> react-utility-hooks
                        </a>
                        <div style="font-size: 12px; color: var(--github-text-secondary);">
                            实用的React Hooks集合
                        </div>
                    </div>
                </div>
            </aside>

            <!-- 主要内容区域 -->
            <div class="content">
                <!-- 标签导航 -->
                <div class="tab-nav">
                    <button class="tab active" data-tab="projects">
                        项目 <span class="tab-count">8</span>
                    </button>
                    <button class="tab" data-tab="activity">
                        动态 <span class="tab-count">42</span>
                    </button>
                    <button class="tab" data-tab="contributions">
                        贡献 <span class="tab-count">365</span>
                    </button>
                </div>

                <!-- 项目部分 -->
                <div id="projects" class="tab-content active">
                    <div class="projects-grid">
                        <!-- 项目卡片 1 -->
                        <div class="project-card">
                            <div class="project-header">
                                <a href="#" class="project-title">vue-admin-system</a>
                                <span class="project-visibility">公开</span>
                            </div>
                            <p class="project-description">
                                基于Vue 3和Element Plus的后台管理系统，包含用户管理、权限控制、数据可视化等功能。
                            </p>
                            <div class="project-meta">
                                <div class="project-language">
                                    <span class="language-color vue"></span>
                                    <span>Vue</span>
                                </div>
                                <div>
                                    <i class="far fa-star"></i> 128
                                </div>
                                <div>
                                    <i class="fas fa-code-branch"></i> 3
                                </div>
                                <div>
                                    更新于 2 天前
                                </div>
                            </div>
                        </div>

                        <!-- 项目卡片 2 -->
                        <div class="project-card">
                            <div class="project-header">
                                <a href="#" class="project-title">react-utility-hooks</a>
                                <span class="project-visibility">公开</span>
                            </div>
                            <p class="project-description">
                                一套实用的React Hooks集合，包括useLocalStorage、useFetch、useDarkMode等常用Hook。
                            </p>
                            <div class="project-meta">
                                <div class="project-language">
                                    <span class="language-color javascript"></span>
                                    <span>JavaScript</span>
                                </div>
                                <div>
                                    <i class="far fa-star"></i> 89
                                </div>
                                <div>
                                    <i class="fas fa-code-branch"></i> 7
                                </div>
                                <div>
                                    更新于 1 周前
                                </div>
                            </div>
                        </div>

                        <!-- 项目卡片 3 -->
                        <div class="project-card">
                            <div class="project-header">
                                <a href="#" class="project-title">data-visualization-tool</a>
                                <span class="project-visibility">公开</span>
                            </div>
                            <p class="project-description">
                                基于D3.js和ECharts的数据可视化工具，支持多种图表类型和数据源导入。
                            </p>
                            <div class="project-meta">
                                <div class="project-language">
                                    <span class="language-color javascript"></span>
                                    <span>JavaScript</span>
                                </div>
                                <div>
                                    <i class="far fa-star"></i> 56
                                </div>
                                <div>
                                    <i class="fas fa-code-branch"></i> 2
                                </div>
                                <div>
                                    更新于 3 周前
                                </div>
                            </div>
                        </div>

                        <!-- 项目卡片 4 -->
                        <div class="project-card">
                            <div class="project-header">
                                <a href="#" class="project-title">portfolio-website</a>
                                <span class="project-visibility">公开</span>
                            </div>
                            <p class="project-description">
                                响应式个人作品集网站，使用Vue 3和Tailwind CSS构建，支持暗黑模式。
                            </p>
                            <div class="project-meta">
                                <div class="project-language">
                                    <span class="language-color html"></span>
                                    <span>HTML</span>
                                </div>
                                <div>
                                    <i class="far fa-star"></i> 42
                                </div>
                                <div>
                                    <i class="fas fa-code-branch"></i> 1
                                </div>
                                <div>
                                    更新于 1 个月前
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- 动态部分 -->
                <div id="activity" class="tab-content" style="display: none;">
                    <div class="contribution-graph">
                        <div class="graph-header">
                            <div class="graph-title">最近动态</div>
                            <div style="font-size: 14px; color: var(--github-text-secondary);">过去30天</div>
                        </div>
                        <div class="graph-container" id="contributionGraph">
                            <!-- 贡献图通过JavaScript生成 -->
                        </div>
                    </div>

                    <div class="activity-list">
                        <!-- 动态条目 1 -->
                        <div class="activity-item">
                            <div class="activity-icon">
                                <i class="fas fa-code-branch" style="color: var(--github-blue);"></i>
                            </div>
                            <div class="activity-content">
                                <div class="activity-text">
                                    在 <strong>vue-admin-system</strong> 仓库创建了新的分支 <strong>feature/user-roles</strong>
                                </div>
                                <div class="activity-time">3小时前</div>
                            </div>
                        </div>

                        <!-- 动态条目 2 -->
                        <div class="activity-item">
                            <div class="activity-icon">
                                <i class="far fa-star" style="color: var(--github-orange);"></i>
                            </div>
                            <div class="activity-content">
                                <div class="activity-text">
                                    给 <strong>tensorflow/models</strong> 仓库点了个星
                                </div>
                                <div class="activity-time">1天前</div>
                            </div>
                        </div>

                        <!-- 动态条目 3 -->
                        <div class="activity-item">
                            <div class="activity-icon">
                                <i class="fas fa-code-commit" style="color: var(--github-green);"></i>
                            </div>
                            <div class="activity-content">
                                <div class="activity-text">
                                    向 <strong>react-utility-hooks</strong> 仓库提交了代码：<em>修复useFetch的内存泄漏问题</em>
                                </div>
                                <div class="activity-time">2天前</div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- 贡献部分 -->
                <div id="contributions" class="tab-content" style="display: none;">
                    <div class="contribution-graph">
                        <div class="graph-header">
                            <div class="graph-title">贡献度图表</div>
                            <div style="font-size: 14px; color: var(--github-text-secondary);">过去一年</div>
                        </div>
                        <div class="graph-container" id="yearlyGraph">
                            <!-- 年度贡献图通过JavaScript生成 -->
                        </div>
                    </div>

                    <div style="margin-top: 30px;">
                        <h3 style="margin-bottom: 16px; font-size: 18px;">贡献统计</h3>
                        <div style="display: flex; gap: 30px; flex-wrap: wrap;">
                            <div style="text-align: center;">
                                <div style="font-size: 24px; font-weight: 600; color: var(--github-green);">1,248</div>
                                <div style="font-size: 14px; color: var(--github-text-secondary);">总提交</div>
                            </div>
                            <div style="text-align: center;">
                                <div style="font-size: 24px; font-weight: 600; color: var(--github-blue);">42</div>
                                <div style="font-size: 14px; color: var(--github-text-secondary);">Pull Requests</div>
                            </div>
                            <div style="text-align: center;">
                                <div style="font-size: 24px; font-weight: 600; color: var(--github-purple);">18</div>
                                <div style="font-size: 14px; color: var(--github-text-secondary);">问题报告</div>
                            </div>
                            <div style="text-align: center;">
                                <div style="font-size: 24px; font-weight: 600; color: var(--github-orange);">7</div>
                                <div style="font-size: 14px; color: var(--github-text-secondary);">仓库创建</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>

        <!-- 页脚 -->
        <footer class="footer container">
            <div>
                <i class="fab fa-github" style="font-size: 24px; margin-bottom: 10px;"></i>
                <div>© 2023 开发者姓名. 保留所有权利。</div>
                <div style="margin-top: 8px; font-size: 13px;">本页面设计灵感来自GitHub</div>
            </div>
            <div class="footer-links">
                <a href="#" class="footer-link">关于</a>
                <a href="#" class="footer-link">联系方式</a>
                <a href="#" class="footer-link">隐私政策</a>
                <a href="#" class="footer-link">使用条款</a>
            </div>
        </footer>
    </div>
</template>

<style>
:root {
    --github-bg: #121923;
    --github-header: #161b22;
    --github-border: #30363d;
    --github-text: #c9d1d9;
    --github-text-secondary: #8b949e;
    --github-blue: #58a6ff;
    --github-green: #238636;
    --github-purple: #8957e5;
    --github-orange: #f78166;
}

* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, sans-serif;
}

body {
    /* background-color: var(--github-bg);
    color: var(--github-text); */
    color: var(--github-text);
    background-color: var(--github-bg);

    /* color: #c9d1d9;
    background-color: #121923; */
    line-height: 1.5;
}

.container {
    max-width: 1280px;
    margin: 0 auto;
    padding: 0 20px;
}

/* 头部导航 */
.header {
    background-color: var(--github-header);
    border-bottom: 1px solid var(--github-border);
    position: sticky;
    top: 0;
    z-index: 100;
}

.nav {
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 12px 0;
}

.nav-logo {
    display: flex;
    align-items: center;
    gap: 10px;
    font-weight: 600;
    font-size: 20px;
}

.nav-logo i {
    color: var(--github-blue);
}

.nav-links {
    display: flex;
    gap: 20px;
    align-items: center;
}

.nav-link {
    color: var(--github-text);
    text-decoration: none;
    font-size: 14px;
    padding: 8px 12px;
    border-radius: 6px;
    transition: background-color 0.2s;
}

.nav-link:hover {
    background-color: rgba(255, 255, 255, 0.1);
}

.avatar {
    width: 32px;
    height: 32px;
    border-radius: 50%;
    border: 1px solid var(--github-border);
}

/* 主要内容布局 */
.main-content {
    display: flex;
    gap: 20px;
    padding: 20px 0;
}

/* 左侧边栏 - 个人资料 */
.sidebar {
    width: 25%;
    position: sticky;
    top: 80px;
    height: fit-content;
}

.profile-card {
    border: 1px solid var(--github-border);
    border-radius: 6px;
    padding: 20px;
    margin-bottom: 20px;
}

.profile-header {
    display: flex;
    gap: 15px;
    margin-bottom: 20px;
}

.profile-avatar {
    width: 80px;
    height: 80px;
    border-radius: 50%;
    border: 2px solid var(--github-border);
}

.profile-info h2 {
    font-size: 20px;
    margin-bottom: 5px;
}

.profile-username {
    color: var(--github-text-secondary);
    font-size: 16px;
    margin-bottom: 10px;
}

.profile-bio {
    font-size: 14px;
    margin-bottom: 15px;
}

.profile-stats {
    display: flex;
    gap: 15px;
    margin-bottom: 15px;
    font-size: 14px;
}

.stat-item {
    display: flex;
    align-items: center;
    gap: 5px;
    color: var(--github-text-secondary);
}

.stat-count {
    font-weight: 600;
    color: var(--github-text);
}

.profile-location,
.profile-email {
    display: flex;
    align-items: center;
    gap: 8px;
    margin-bottom: 8px;
    font-size: 14px;
    color: var(--github-text-secondary);
}

/* 主要内容区域 */
.content {
    width: 75%;
}

.tab-nav {
    display: flex;
    border-bottom: 1px solid var(--github-border);
    margin-bottom: 20px;
}

.tab {
    padding: 12px 20px;
    background: none;
    border: none;
    color: var(--github-text-secondary);
    cursor: pointer;
    font-size: 14px;
    position: relative;
    transition: color 0.2s;
}

.tab:hover {
    color: var(--github-text);
}

.tab.active {
    color: var(--github-text);
    font-weight: 500;
}

.tab.active::after {
    content: '';
    position: absolute;
    bottom: -1px;
    left: 0;
    right: 0;
    height: 2px;
    background-color: #f78166;
}

.tab-count {
    background-color: rgba(255, 255, 255, 0.15);
    border-radius: 20px;
    padding: 2px 8px;
    font-size: 12px;
    margin-left: 6px;
}

/* 项目卡片 */
.projects-grid {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
    gap: 16px;
    margin-bottom: 30px;
}

.project-card {
    border: 1px solid var(--github-border);
    border-radius: 6px;
    padding: 16px;
    transition: border-color 0.2s, transform 0.2s;
}

.project-card:hover {
    border-color: var(--github-blue);
    transform: translateY(-2px);
}

.project-header {
    display: flex;
    justify-content: space-between;
    align-items: flex-start;
    margin-bottom: 12px;
}

.project-title {
    font-weight: 600;
    font-size: 16px;
    color: var(--github-blue);
    text-decoration: none;
}

.project-title:hover {
    text-decoration: underline;
}

.project-visibility {
    border: 1px solid var(--github-border);
    border-radius: 12px;
    padding: 2px 8px;
    font-size: 12px;
    color: var(--github-text-secondary);
}

.project-description {
    font-size: 14px;
    margin-bottom: 16px;
    color: var(--github-text-secondary);
    line-height: 1.4;
}

.project-meta {
    display: flex;
    gap: 16px;
    font-size: 12px;
    color: var(--github-text-secondary);
}

.project-language {
    display: flex;
    align-items: center;
    gap: 4px;
}

.language-color {
    width: 12px;
    height: 12px;
    border-radius: 50%;
}

.javascript {
    background-color: #f1e05a;
}

.python {
    background-color: #3572a5;
}

.vue {
    background-color: #41b883;
}

.css {
    background-color: #563d7c;
}

.html {
    background-color: #e34c26;
}

/* 活动列表 */
.activity-list {
    border: 1px solid var(--github-border);
    border-radius: 6px;
    overflow: hidden;
}

.activity-item {
    padding: 16px;
    border-bottom: 1px solid var(--github-border);
    display: flex;
    gap: 12px;
}

.activity-item:last-child {
    border-bottom: none;
}

.activity-icon {
    width: 40px;
    height: 40px;
    border-radius: 50%;
    background-color: var(--github-header);
    display: flex;
    align-items: center;
    justify-content: center;
    flex-shrink: 0;
}

.activity-content {
    flex: 1;
}

.activity-text {
    font-size: 14px;
    margin-bottom: 4px;
}

.activity-time {
    font-size: 12px;
    color: var(--github-text-secondary);
}

/* 贡献图 */
.contribution-graph {
    border: 1px solid var(--github-border);
    border-radius: 6px;
    padding: 20px;
    margin-bottom: 30px;
}

.graph-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 16px;
}

.graph-title {
    font-weight: 600;
    font-size: 16px;
}

.graph-container {
    display: flex;
    gap: 4px;
    flex-wrap: wrap;
}

.graph-cell {
    width: 14px;
    height: 14px;
    border-radius: 2px;
    background-color: #161b22;
}

.graph-cell.level-0 {
    background-color: #161b22;
}

.graph-cell.level-1 {
    background-color: #0e4429;
}

.graph-cell.level-2 {
    background-color: #006d32;
}

.graph-cell.level-3 {
    background-color: #26a641;
}

.graph-cell.level-4 {
    background-color: #39d353;
}

/* 页脚 */
.footer {
    border-top: 1px solid var(--github-border);
    padding: 40px 0;
    margin-top: 40px;
    text-align: center;
    color: var(--github-text-secondary);
    font-size: 14px;
}

.footer-links {
    display: flex;
    justify-content: center;
    gap: 20px;
    margin-top: 15px;
}

.footer-link {
    color: var(--github-blue);
    text-decoration: none;
}

.footer-link:hover {
    text-decoration: underline;
}

/* 响应式设计 */
@media (max-width: 1024px) {
    .main-content {
        flex-direction: column;
    }

    .sidebar,
    .content {
        width: 100%;
    }

    .sidebar {
        position: static;
    }
}

@media (max-width: 768px) {
    .nav-links .nav-link:not(:last-child) {
        display: none;
    }

    .projects-grid {
        grid-template-columns: 1fr;
    }

    .profile-header {
        flex-direction: column;
        text-align: center;
    }
}
</style>
