# GitHub风格个人官网API接口文档

## 文档信息

- **版本**: v1.0
- **最后更新**: 2025-12-19
- **维护者**: CoMoYo
- **项目名称**: GitHub风格个人官网

## 基础信息
- **项目类型**: 前后端分离的个人作品展示平台
- **设计风格**: GitHub深色主题风格
- **核心功能**: 个人资料展示、项目作品管理、活动动态展示、贡献度统计

### 服务地址
```
- 开发环境: 
  前端: http://127.0.0.1:5173
  后端: http://127.0.0.1:8080
- 生产环境: 
```

### 通用约定
- **数据格式**: JSON
- **字符编码**: UTF-8
- **时区**: UTC+8
- **日期格式**: ISO 8601 (YYYY-MM-DDTHH:mm:ssZ)
- **分页格式**: 统一使用page/limit模式

### 认证方式
- **认证类型**: Bearer Token (JWT)
- **Token位置**: Authorization Header
- **Token格式**: `Bearer {token}`
- **Token存储**: localStorage (github_style_auth_token)

---

## 响应格式规范

### 成功响应
```json
{
  "code": 200,
  "message": "success",
  "data": {},
  "timestamp": "2024-01-20T10:00:00Z"
}
```

### 错误响应
```json
{
  "code": 400,
  "message": "错误描述",
  "errors": {
    "fieldName": "字段错误信息"
  },
  "timestamp": "2024-01-20T10:00:00Z"
}
```

### 分页响应
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "list": [],
    "pagination": {
      "page": 1,
      "limit": 10,
      "total": 100,
      "pages": 10,
      "hasNext": true
    }
  }
}
```

---

## 1. 认证管理

### 1.1 用户登录

**接口说明**: 用户登录获取访问令牌

- **URL**: `POST /api/auth/login`
- **认证**: 不需要
- **限流**: 10次/分钟
- **前端对应**: 登录模态框

**请求头**:
```http
Content-Type: application/json
```

**请求体**:
```json
{
  "username": "string, required, 用户名或邮箱",
  "password": "string, required, 密码"
}
```

**响应成功** (200):
```json
{
  "code": 200,
  "message": "登录成功",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "user": {
      "id": 1,
      "username": "admin",
      "name": "开发者姓名",
      "email": "dev@example.com",
      "avatar": "https://avatars.githubusercontent.com/u/583231?v=4",
      "bio": "全栈开发者 | Vue.js爱好者 | 开源贡献者",
      "location": "中国 · 上海",
      "github": "https://github.com/ComoYo-Yanke",
      "bilibili": "https://space.bilibili.com/489802543",
      "role": "admin",
      "projectsCount": 24,
      "createdAt": "2024-01-01T00:00:00Z"
    }
  },
  "timestamp": "2024-01-20T10:00:00Z"
}
```

**错误响应**:
- 400: 请求参数错误 (用户名或密码为空)
- 401: 用户名或密码错误
- 403: 账户被禁用
- 429: 登录尝试过于频繁
- 500: 服务器内部错误

---

### 1.2 用户注册

**接口说明**: 新用户注册

- **URL**: `POST /api/auth/register`
- **认证**: 不需要
- **限流**: 5次/小时
- **前端对应**: 注册模态框

**请求头**:
```http
Content-Type: application/json
```

**请求体**:
```json
{
  "username": "string, required, 用户名(3-20字符,字母数字下划线)",
  "email": "string, required, 邮箱地址",
  "password": "string, required, 密码(至少6位)",
  "fullName": "string, optional, 全名",
  "bio": "string, optional, 个人简介"
}
```

**响应成功** (201):
```json
{
  "code": 200,
  "message": "注册成功",
  "data": {
    "userId": 1,
    "username": "newuser",
    "email": "newuser@example.com"
  },
  "timestamp": "2024-01-20T10:00:00Z"
}
```

**错误响应**:
- 400: 参数验证失败
- 409: 用户名或邮箱已存在
- 429: 注册请求过于频繁

---

### 1.3 Token验证

**接口说明**: 验证Token有效性

- **URL**: `GET /api/auth/validate`
- **认证**: 需要 (Bearer Token)
- **前端对应**: 页面加载时自动验证

**请求头**:
```http
Authorization: Bearer {token}
```

**响应成功** (200):
```json
{
  "code": 200,
  "message": "Token有效",
  "data": {
    "valid": true,
    "user": {
      "id": 1,
      "username": "admin",
      "role": "admin"
    },
    "expiresIn": 3600
  },
  "timestamp": "2024-01-20T10:00:00Z"
}
```

**错误响应**:
- 401: Token无效或已过期
- 403: Token权限不足

---

### 1.4 用户退出

**接口说明**: 用户退出登录

- **URL**: `POST /api/auth/logout`
- **认证**: 需要
- **前端对应**: 用户下拉菜单的"退出登录"按钮

**请求头**:
```http
Authorization: Bearer {token}
Content-Type: application/json
```

**响应成功** (200):
```json
{
  "code": 200,
  "message": "退出登录成功",
  "data": null,
  "timestamp": "2024-01-20T10:00:00Z"
}
```

---

## 2. 用户管理

### 2.1 获取用户资料

**接口说明**: 获取当前登录用户的详细资料

- **URL**: `GET /api/user/profile`
- **认证**: 需要
- **前端对应**: 左侧边栏个人资料卡片

**请求头**:
```http
Authorization: Bearer {token}
```

**响应成功** (200):
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "user": {
      "id": 1,
      "username": "dev-username",
      "name": "开发者姓名",
      "avatar": "https://avatars.githubusercontent.com/u/583231?v=4",
      "bio": "全栈开发者 | Vue.js爱好者 | 开源贡献者",
      "location": "中国 · 上海",
      "email": "dev@example.com",
      "github": "https://github.com/ComoYo-Yanke",
      "bilibili": "https://space.bilibili.com/489802543",
      "role": "admin",
      "stats": {
        "projects": 24,
        "followers": 128,
        "following": 56
      },
      "socialLinks": [
        {
          "platform": "github",
          "url": "https://github.com/ComoYo-Yanke",
          "username": "ComoYo-Yanke"
        },
        {
          "platform": "bilibili",
          "url": "https://space.bilibili.com/489802543",
          "username": "个人空间"
        }
      ],
      "createdAt": "2024-01-01T00:00:00Z",
      "updatedAt": "2024-01-20T10:00:00Z"
    }
  },
  "timestamp": "2024-01-20T10:00:00Z"
}
```

---

### 2.2 更新用户资料

**接口说明**: 更新用户个人信息

- **URL**: `PUT /api/user/profile`
- **认证**: 需要
- **前端对应**: 管理员设置页面

**请求头**:
```http
Authorization: Bearer {token}
Content-Type: application/json
```

**请求体**:
```json
{
  "name": "string, optional, 显示名称",
  "bio": "string, optional, 个人简介",
  "location": "string, optional, 所在地",
  "email": "string, optional, 邮箱地址",
  "github": "string, optional, GitHub链接",
  "bilibili": "string, optional, B站链接",
  "avatar": "string, optional, 头像URL"
}
```

**响应成功** (200):
```json
{
  "code": 200,
  "message": "用户信息更新成功",
  "data": {
    "user": {
      "id": 1,
      "username": "dev-username",
      "name": "更新后的姓名",
      "bio": "更新后的个人简介",
      "location": "中国 · 北京",
      "email": "newemail@example.com",
      "github": "https://github.com/newusername",
      "bilibili": "https://space.bilibili.com/newid",
      "avatar": "https://avatars.githubusercontent.com/u/newid",
      "updatedAt": "2024-01-20T10:00:00Z"
    }
  },
  "timestamp": "2024-01-20T10:00:00Z"
}
```

---

## 3. 项目管理

### 3.1 获取项目列表

**接口说明**: 获取用户的公开项目列表

- **URL**: `GET /api/projects`
- **认证**: 不需要（公开信息）
- **前端对应**: 项目标签页的项目卡片
- **分页支持**: 支持

**查询参数**:
| 参数名 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|------|
| page | number | 否 | 1 | 页码 |
| limit | number | 否 | 12 | 每页数量 |
| sort | string | 否 | updated | 排序: updated, created, stars |
| visibility | string | 否 | all | 可见性: all, public, private |

**响应成功** (200):
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "list": [
      {
        "id": 1,
        "name": "vue-admin-system",
        "title": "Vue后台管理系统",
        "description": "基于Vue 3和Element Plus的后台管理系统，包含用户管理、权限控制、数据可视化等功能。",
        "visibility": "public",
        "language": "Vue",
        "languageColor": "#41b883",
        "stars": 128,
        "forks": 3,
        "updatedAt": "2024-01-18T14:30:00Z",
        "createdAt": "2023-11-15T09:20:00Z",
        "topics": ["vue", "admin", "element-plus", "dashboard"],
        "homepage": "https://admin.example.com",
        "repoUrl": "https://github.com/username/vue-admin-system",
        "hasDemo": true,
        "demoUrl": "https://demo.example.com"
      },
      {
        "id": 2,
        "name": "react-utility-hooks",
        "title": "React实用Hooks集合",
        "description": "一套实用的React Hooks集合，包括useLocalStorage、useFetch、useDarkMode等常用Hook。",
        "visibility": "public",
        "language": "JavaScript",
        "languageColor": "#f1e05a",
        "stars": 89,
        "forks": 7,
        "updatedAt": "2024-01-11T10:15:00Z",
        "createdAt": "2023-10-20T16:45:00Z",
        "topics": ["react", "hooks", "utilities", "frontend"]
      }
    ],
    "pagination": {
      "page": 1,
      "limit": 12,
      "total": 24,
      "pages": 2,
      "hasNext": true,
      "hasPrev": false
    }
  },
  "timestamp": "2024-01-20T10:00:00Z"
}
```

---

### 3.2 获取热门项目

**接口说明**: 获取用户的热门项目（按star数排序）

- **URL**: `GET /api/projects/hot`
- **认证**: 不需要
- **前端对应**: 左侧边栏的热门项目区域

**查询参数**:
| 参数名 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|------|
| limit | number | 否 | 5 | 返回数量 |

**响应成功** (200):
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "projects": [
      {
        "id": 1,
        "name": "vue-admin-system",
        "title": "Vue后台管理系统",
        "description": "基于Vue 3的管理系统",
        "stars": 128,
        "language": "Vue"
      },
      {
        "id": 2,
        "name": "react-utility-hooks",
        "title": "React实用Hooks集合",
        "description": "实用的React Hooks集合",
        "stars": 89,
        "language": "JavaScript"
      }
    ]
  },
  "timestamp": "2024-01-20T10:00:00Z"
}
```

---

### 3.3 创建新项目

**接口说明**: 创建新项目（仅管理员）

- **URL**: `POST /api/projects`
- **认证**: 需要（管理员权限）
- **前端对应**: 管理员后台的项目管理

**请求头**:
```http
Authorization: Bearer {token}
Content-Type: application/json
```

**请求体**:
```json
{
  "name": "string, required, 项目名称",
  "title": "string, required, 项目标题",
  "description": "string, required, 项目描述",
  "visibility": "string, required, 可见性: public, private",
  "language": "string, required, 主要语言",
  "languageColor": "string, optional, 语言颜色代码",
  "homepage": "string, optional, 项目主页",
  "repoUrl": "string, optional, 仓库地址",
  "demoUrl": "string, optional, 演示地址",
  "topics": "array, optional, 项目标签"
}
```

**响应成功** (201):
```json
{
  "code": 200,
  "message": "项目创建成功",
  "data": {
    "project": {
      "id": 25,
      "name": "new-project",
      "title": "新项目",
      "description": "项目描述",
      "visibility": "public",
      "language": "JavaScript",
      "languageColor": "#f1e05a",
      "stars": 0,
      "forks": 0,
      "createdAt": "2024-01-20T10:00:00Z",
      "updatedAt": "2024-01-20T10:00:00Z"
    }
  },
  "timestamp": "2024-01-20T10:00:00Z"
}
```

---

### 3.4 更新项目信息

**接口说明**: 更新项目信息（仅管理员）

- **URL**: `PUT /api/projects/{id}`
- **认证**: 需要（管理员权限）

**请求头**:
```http
Authorization: Bearer {token}
Content-Type: application/json
```

**请求体**:
```json
{
  "title": "string, optional, 项目标题",
  "description": "string, optional, 项目描述",
  "visibility": "string, optional, 可见性",
  "language": "string, optional, 主要语言",
  "homepage": "string, optional, 项目主页",
  "demoUrl": "string, optional, 演示地址",
  "topics": "array, optional, 项目标签"
}
```

---

### 3.5 删除项目

**接口说明**: 删除项目（仅管理员）

- **URL**: `DELETE /api/projects/{id}`
- **认证**: 需要（管理员权限）

---

## 4. 活动动态管理

### 4.1 获取活动动态

**接口说明**: 获取用户的活动记录

- **URL**: `GET /api/activities`
- **认证**: 不需要（公开信息）
- **前端对应**: 动态标签页的活动列表
- **分页支持**: 支持

**查询参数**:
| 参数名 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|------|
| page | number | 否 | 1 | 页码 |
| limit | number | 否 | 20 | 每页数量 |
| type | string | 否 | all | 类型: all, commit, star, fork, issue |

**响应成功** (200):
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "list": [
      {
        "id": 1,
        "type": "branch",
        "icon": "code-branch",
        "color": "#58a6ff",
        "content": "在 <strong>vue-admin-system</strong> 仓库创建了新的分支 <strong>feature/user-roles</strong>",
        "projectId": 1,
        "projectName": "vue-admin-system",
        "timestamp": "2024-01-20T07:30:00Z",
        "timeAgo": "3小时前"
      },
      {
        "id": 2,
        "type": "star",
        "icon": "star",
        "color": "#f78166",
        "content": "给 <strong>tensorflow/models</strong> 仓库点了个星",
        "externalRepo": "tensorflow/models",
        "timestamp": "2024-01-19T14:20:00Z",
        "timeAgo": "1天前"
      },
      {
        "id": 3,
        "type": "commit",
        "icon": "code-commit",
        "color": "#238636",
        "content": "向 <strong>react-utility-hooks</strong> 仓库提交了代码：<em>修复useFetch的内存泄漏问题</em>",
        "projectId": 2,
        "projectName": "react-utility-hooks",
        "commitHash": "a1b2c3d4",
        "commitMessage": "修复useFetch的内存泄漏问题",
        "timestamp": "2024-01-18T09:15:00Z",
        "timeAgo": "2天前"
      }
    ],
    "pagination": {
      "page": 1,
      "limit": 20,
      "total": 42,
      "pages": 3,
      "hasNext": true
    }
  },
  "timestamp": "2024-01-20T10:00:00Z"
}
```

---

### 4.2 创建活动记录

**接口说明**: 创建新的活动记录（仅管理员）

- **URL**: `POST /api/activities`
- **认证**: 需要（管理员权限）

**请求体**:
```json
{
  "type": "string, required, 活动类型",
  "content": "string, required, 活动内容",
  "projectId": "number, optional, 关联项目ID",
  "externalRepo": "string, optional, 外部仓库",
  "commitHash": "string, optional, 提交哈希",
  "commitMessage": "string, optional, 提交信息",
  "timestamp": "string, optional, 活动时间"
}
```

---

## 5. 贡献度统计

### 5.1 获取贡献度图表数据

**接口说明**: 获取贡献度热力图数据

- **URL**: `GET /api/contributions`
- **认证**: 不需要（公开信息）
- **前端对应**: 贡献标签页的贡献度图表

**查询参数**:
| 参数名 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|------|
| year | number | 否 | 当前年份 | 统计年份 |
| type | string | 否 | yearly | 类型: yearly, monthly |

**响应成功** (200):
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "year": 2024,
    "totalContributions": 1560,
    "dailyData": [
      {
        "date": "2024-01-01",
        "count": 3,
        "level": 1
      },
      {
        "date": "2024-01-02",
        "count": 0,
        "level": 0
      },
      // ... 更多日期数据
    ],
    "monthlySummary": [
      {
        "month": "2024-01",
        "count": 45,
        "average": 1.45
      },
      {
        "month": "2023-12",
        "count": 68,
        "average": 2.19
      }
    ],
    "streak": {
      "currentStreak": 5,
      "longestStreak": 28,
      "startDate": "2024-01-15"
    }
  },
  "timestamp": "2024-01-20T10:00:00Z"
}
```

---

### 5.2 获取贡献统计概要

**接口说明**: 获取贡献统计数字

- **URL**: `GET /api/contributions/summary`
- **认证**: 不需要
- **前端对应**: 贡献标签页的统计数字卡片

**响应成功** (200):
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "totalCommits": 1248,
    "totalPullRequests": 42,
    "totalIssues": 18,
    "totalRepos": 7,
    "currentYearCommits": 156,
    "currentYearPullRequests": 12,
    "mostActiveDay": "Tuesday",
    "mostActiveHour": 14,
    "lastUpdated": "2024-01-20T10:00:00Z"
  },
  "timestamp": "2024-01-20T10:00:00Z"
}
```

---

## 6. 系统接口

### 6.1 健康检查

**接口说明**: 服务健康状态检查

- **URL**: `GET /api/health`
- **认证**: 不需要

**响应成功** (200):
```json
{
  "code": 200,
  "message": "服务正常",
  "data": {
    "status": "UP",
    "timestamp": "2024-01-20T10:00:00Z",
    "version": "1.0.0",
    "database": "connected",
    "uptime": "3d 5h 12m"
  },
  "timestamp": "2024-01-20T10:00:00Z"
}
```

---

### 6.2 测试接口

**接口说明**: 接口连通性测试

- **URL**: `GET /api/depts`
- **认证**: 不需要
- **前端对应**: 页面加载时的测试调用

**响应成功** (200):
```json
{
  "code": 200,
  "message": "接口连接正常",
  "data": {
    "timestamp": "2024-01-20T10:00:00Z",
    "status": "OK",
    "environment": "development"
  },
  "timestamp": "2024-01-20T10:00:00Z"
}
```

---

### 6.3 获取系统统计

**接口说明**: 获取系统整体统计数据

- **URL**: `GET /api/stats`
- **认证**: 不需要

**响应成功** (200):
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "userStats": {
      "totalUsers": 1,
      "activeUsers": 1,
      "newUsersToday": 0
    },
    "projectStats": {
      "totalProjects": 24,
      "publicProjects": 24,
      "privateProjects": 0,
      "projectsByLanguage": {
        "JavaScript": 12,
        "Vue": 6,
        "Python": 4,
        "HTML": 2
      }
    },
    "activityStats": {
      "totalActivities": 1560,
      "activitiesToday": 3,
      "activitiesThisWeek": 24
    },
    "systemStats": {
      "apiRequestsToday": 124,
      "apiRequestsTotal": 12568,
      "uptime": "99.8%"
    }
  },
  "timestamp": "2024-01-20T10:00:00Z"
}
```

---

## 7. 文件管理

### 7.1 上传头像

**接口说明**: 上传用户头像

- **URL**: `POST /api/upload/avatar`
- **认证**: 需要
- **Content-Type**: multipart/form-data

**请求参数**:
| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| avatar | file | 是 | 头像图片文件 |

**响应成功** (200):
```json
{
  "code": 200,
  "message": "头像上传成功",
  "data": {
    "url": "/uploads/avatars/avatar_123456.jpg",
    "filename": "avatar_123456.jpg",
    "size": 12345,
    "mimeType": "image/jpeg"
  },
  "timestamp": "2024-01-20T10:00:00Z"
}
```

---

## 数据模型

### 用户模型 (User)
```javascript
{
  id: number,           // 用户ID
  username: string,     // 用户名
  name: string,        // 显示名称
  email: string,       // 邮箱
  avatar: string,      // 头像URL
  bio: string,         // 个人简介
  location: string,    // 所在地
  github: string,      // GitHub链接
  bilibili: string,    // B站链接
  role: string,        // 角色: admin, user
  stats: object,       // 统计信息
  createdAt: string,   // 创建时间
  updatedAt: string    // 更新时间
}
```

### 项目模型 (Project)
```javascript
{
  id: number,           // 项目ID
  name: string,        // 项目名称
  title: string,       // 项目标题
  description: string, // 项目描述
  visibility: string,  // 可见性: public, private
  language: string,    // 主要语言
  languageColor: string, // 语言颜色
  stars: number,       // star数
  forks: number,       // fork数
  topics: array,       // 项目标签
  homepage: string,    // 项目主页
  repoUrl: string,     // 仓库地址
  demoUrl: string,     // 演示地址
  createdAt: string,   // 创建时间
  updatedAt: string    // 更新时间
}
```

### 活动模型 (Activity)
```javascript
{
  id: number,           // 活动ID
  type: string,        // 活动类型
  icon: string,        // 图标名称
  color: string,       // 颜色代码
  content: string,     // 活动内容
  projectId: number,   // 关联项目ID
  projectName: string, // 项目名称
  externalRepo: string,// 外部仓库
  commitHash: string,  // 提交哈希
  commitMessage: string, // 提交信息
  timestamp: string,   // 活动时间
  timeAgo: string      // 相对时间
}
```

---

## 状态码说明

### HTTP状态码
| 状态码 | 说明 |
|--------|------|
| 200 | 请求成功 |
| 201 | 创建成功 |
| 400 | 请求参数错误 |
| 401 | 未认证或Token无效 |
| 403 | 权限不足 |
| 404 | 资源不存在 |
| 409 | 资源冲突 |
| 422 | 参数验证失败 |
| 429 | 请求过于频繁 |
| 500 | 服务器内部错误 |

### 业务错误码
| 错误码 | 说明 |
|--------|------|
| 1001 | 用户名或密码错误 |
| 1002 | 用户不存在 |
| 1003 | 用户被禁用 |
| 1004 | Token已过期 |
| 1005 | Token无效 |
| 1006 | 验证码错误 |
| 2001 | 项目不存在 |
| 2002 | 项目名称已存在 |
| 2003 | 无权访问该项目 |
| 3001 | 文件上传失败 |
| 3002 | 文件类型不支持 |
| 3003 | 文件大小超过限制 |
| 4001 | 活动记录不存在 |
| 5001 | 系统维护中 |

---

## 更新日志

### v1.0 (2025-12-19)
- 初始版本发布
- 实现GitHub风格个人官网核心功能
- 包含认证、用户管理、项目管理、活动动态、贡献统计等接口
- 支持前后端分离架构

### 计划功能
- v1.1: 支持多用户系统
- v1.2: 添加评论和互动功能
- v1.3: 支持主题切换（浅色/深色）
- v1.4: 添加API文档在线浏览

---

## 使用示例

### 前端API调用示例
```javascript
// 登录示例
async function login(username, password) {
  try {
    const response = await fetch('/api/auth/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ username, password })
    });
    
    const result = await response.json();
    if (result.code === 200) {
      // 保存token和用户信息
      localStorage.setItem('github_style_auth_token', result.data.token);
      localStorage.setItem('github_style_user_data', JSON.stringify(result.data.user));
      return result.data;
    } else {
      throw new Error(result.message);
    }
  } catch (error) {
    console.error('登录失败:', error);
    throw error;
  }
}

// 获取项目列表示例
async function getProjects(page = 1, limit = 12) {
  try {
    const token = localStorage.getItem('github_style_auth_token');
    const headers = {
      'Content-Type': 'application/json'
    };
    
    if (token) {
      headers['Authorization'] = `Bearer ${token}`;
    }
    
    const response = await fetch(`/api/projects?page=${page}&limit=${limit}`, {
      method: 'GET',
      headers: headers
    });
    
    const result = await response.json();
    if (result.code === 200) {
      return result.data;
    } else {
      throw new Error(result.message);
    }
  } catch (error) {
    console.error('获取项目失败:', error);
    throw error;
  }
}
```

---

## 注意事项

1. **Token安全**: Token应存储在安全的地方，避免XSS攻击
2. **权限控制**: 管理员操作需要验证用户角色
3. **数据验证**: 所有输入数据都需要在服务端验证
4. **文件上传**: 限制文件类型和大小，防止恶意上传
5. **频率限制**: 对敏感接口实施频率限制
6. **错误处理**: 统一的错误处理机制
7. **日志记录**: 记录重要操作日志

---

**文档维护**: 本文档应与后端API实现保持一致，接口变更时需及时更新文档。

**问题反馈**: 如有接口问题或建议，请通过GitHub Issues反馈。