# 个人产品主页 API 接口文档

## 文档信息

- **版本**: v1.0
- **最后更新**: 2025-11-19
- **维护者**: CoMoYo

## 基础信息
方式: 前后端分离
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
- **时区**: UTC
- **日期格式**: ISO 8601 (YYYY-MM-DDTHH:mm:ssZ)

### 认证方式
- **认证类型**: Bearer Token (JWT)
- **Token位置**: Authorization Header
- **Token格式**: `Bearer {token}`

---

## 响应格式规范

### 成功响应 Result success
```json
{
  "code": 200,
  "message": "success",
  "data": {},
  "timestamp": "2024-01-20T10:00:00Z"
}
```

### 错误响应 Result error
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
      "pages": 10
    }
  }
}
```

---

## 1. 认证管理

### 1.1 用户登录

**接口说明**: 管理员登录获取访问令牌

- **URL**: `POST /auth/login`
- **认证**: 不需要
- **限流**: 10次/分钟

**请求头**:
```http
Content-Type: application/json
```

**请求体**:
```json
{
  "username": "string, required, 用户名",
  "password": "string, required, 密码"
}
```

**响应成功** (200):
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "user": {
      "id": 1,
      "username": "admin",
      "name": "管理员",
      "email": "admin@example.com"
    }
  },
  "timestamp": "2024-01-20T10:00:00Z"
}
```

**错误响应**:
- 400: 请求参数错误
- 401: 用户名或密码错误
- 429: 请求过于频繁
- 403: 拒绝当前IP访问

---

### 1.2 用户退出

**接口说明**: 用户退出登录

- **URL**: `POST /auth/logout`
- **认证**: 需要
- **限流**: 无

**请求头**:
```http
Authorization: Bearer {token}
Content-Type: application/json
```

**响应成功** (200):
```json
{
  "code": 200,
  "message": "success",
  "data": null,
  "timestamp": "2024-01-20T10:00:00Z"
}
```

---

## 2. 产品管理

### 2.1 获取产品列表

**接口说明**: 分页获取所有产品列表

- **URL**: `GET /products`
- **认证**: 不需要
- **缓存**: 5分钟

**查询参数**:

| 参数名 | 类型 | 必填 | 默认值 | 说明 | 示例 |
|--------|------|------|--------|------|------|
| page | integer | 否 | 1 | 页码 | `1` |
| limit | integer | 否 | 10 | 每页数量 | `10` |
| keyword | string | 否 | - | 搜索关键词 | `"笔记"` |
| sort | string | 否 | `createdAt` | 排序字段 | `"downloadCount"` |
| order | string | 否 | `desc` | 排序方向 | `"asc"` |

**响应成功** (200):
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "products": [
      {
        "id": 1,
        "name": "快速笔记应用",
        "description": "一个简洁高效的笔记工具",
        "coverImage": "/images/product1.jpg",
        "fileUrl": "/files/product1.zip",
        "fileSize": 4520000,
        "version": "v1.0.0",
        "downloadCount": 123,
        "createdAt": "2024-01-15T10:00:00Z",
        "updatedAt": "2024-01-15T10:00:00Z"
      }
    ],
    "pagination": {
      "page": 1,
      "limit": 10,
      "total": 25,
      "pages": 3
    }
  },
  "timestamp": "2024-01-20T10:00:00Z"
}
```

---

### 2.2 获取产品详情

**接口说明**: 根据ID获取单个产品详情

- **URL**: `GET /products/{id}`
- **认证**: 不需要
- **缓存**: 10分钟

**路径参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| id | integer | 是 | 产品ID |

**响应成功** (200):
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "product": {
      "id": 1,
      "name": "快速笔记应用",
      "description": "一个简洁高效的笔记工具",
      "coverImage": "/images/product1.jpg",
      "fileUrl": "/files/product1.zip",
      "fileSize": 4520000,
      "version": "v1.0.0",
      "downloadCount": 123,
      "createdAt": "2024-01-15T10:00:00Z",
      "updatedAt": "2024-01-15T10:00:00Z"
    }
  },
  "timestamp": "2024-01-20T10:00:00Z"
}
```

**错误响应**:
- 404: 产品不存在

---

### 2.3 创建产品

**接口说明**: 创建新产品

- **URL**: `POST /products`
- **认证**: 需要
- **限流**: 5次/分钟

**请求头**:
```http
Authorization: Bearer {token}
Content-Type: multipart/form-data
```

**请求参数**:

| 参数名 | 类型 | 必填 | 说明 | 限制 |
|--------|------|------|------|------|
| name | string | 是 | 产品名称 | 2-100字符 |
| description | string | 是 | 产品描述 | 10-500字符 |
| file | file | 是 | 产品文件 | ≤100MB, zip/rar/7z |
| coverImage | file | 否 | 封面图片 | ≤10MB, jpg/png |

**响应成功** (201):
```json
{
  "code": 201,
  "message": "产品创建成功",
  "data": {
    "product": {
      "id": 5,
      "name": "新产品",
      "description": "产品描述",
      "coverImage": "/images/new-product.jpg",
      "fileUrl": "/files/new-product.zip",
      "fileSize": 5120000,
      "version": "v1.0.0",
      "downloadCount": 0,
      "createdAt": "2024-01-20T10:00:00Z",
      "updatedAt": "2024-01-20T10:00:00Z"
    }
  },
  "timestamp": "2024-01-20T10:00:00Z"
}
```

**错误响应**:
- 400: 文件格式不支持/文件过大
- 409: 产品名称已存在

---

### 2.4 更新产品

**接口说明**: 更新产品信息

- **URL**: `PUT /products/{id}`
- **认证**: 需要

**路径参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| id | integer | 是 | 产品ID |

**请求头**:
```http
Authorization: Bearer {token}
Content-Type: application/json
```

**请求体**:
```json
{
  "name": "string, optional, 产品名称",
  "description": "string, optional, 产品描述"
}
```

**响应成功** (200):
```json
{
  "code": 200,
  "message": "产品更新成功",
  "data": {
    "product": {
      "id": 1,
      "name": "更新后的名称",
      "description": "更新后的描述",
      "coverImage": "/images/product1.jpg",
      "fileUrl": "/files/product1.zip",
      "fileSize": 4520000,
      "version": "v1.0.0",
      "downloadCount": 123,
      "createdAt": "2024-01-15T10:00:00Z",
      "updatedAt": "2024-01-20T10:00:00Z"
    }
  },
  "timestamp": "2024-01-20T10:00:00Z"
}
```

---

### 2.5 删除产品

**接口说明**: 删除产品

- **URL**: `DELETE /products/{id}`
- **认证**: 需要

**路径参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| id | integer | 是 | 产品ID |

**请求头**:
```http
Authorization: Bearer {token}
```

**响应成功** (200):
```json
{
  "code": 200,
  "message": "产品删除成功",
  "data": null,
  "timestamp": "2024-01-20T10:00:00Z"
}
```

---

### 2.6 下载产品

**接口说明**: 下载产品文件并增加下载计数

- **URL**: `POST /products/{id}/download`
- **认证**: 不需要
- **限流**: 10次/分钟/IP

**路径参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| id | integer | 是 | 产品ID |

**响应成功** (200):
```json
{
  "code": 200,
  "message": "下载成功",
  "data": {
    "downloadUrl": "/files/product1.zip",
    "downloadCount": 124
  },
  "timestamp": "2024-01-20T10:00:00Z"
}
```

---

## 3. 文件管理

### 3.1 获取文件列表

**接口说明**: 分页获取文件列表（仅管理员）

- **URL**: `GET /files`
- **认证**: 需要

**查询参数**:

| 参数名 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|------|
| page | integer | 否 | 1 | 页码 |
| limit | integer | 否 | 20 | 每页数量 |

**响应成功** (200):
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "files": [
      {
        "id": 1,
        "originalName": "product-v1.0.0.zip",
        "filename": "abc123.zip",
        "filePath": "/uploads/abc123.zip",
        "fileSize": 4520000,
        "mimeType": "application/zip",
        "downloadCount": 45,
        "createdAt": "2024-01-15T10:00:00Z"
      }
    ],
    "pagination": {
      "page": 1,
      "limit": 20,
      "total": 15,
      "pages": 1
    }
  },
  "timestamp": "2024-01-20T10:00:00Z"
}
```

---

### 3.2 删除文件

**接口说明**: 删除文件（仅管理员）

- **URL**: `DELETE /files/{id}`
- **认证**: 需要

**路径参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| id | integer | 是 | 文件ID |

**响应成功** (200):
```json
{
  "code": 200,
  "message": "文件删除成功",
  "data": null,
  "timestamp": "2024-01-20T10:00:00Z"
}
```

---

### 3.3 下载文件

**接口说明**: 下载文件

- **URL**: `GET /files/download/{id}`
- **认证**: 不需要
- **响应**: 文件流

**路径参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| id | integer | 是 | 文件ID |

**响应头**:
```http
Content-Type: application/octet-stream
Content-Disposition: attachment; filename="filename.zip"
Content-Length: 4520000
```

---

## 4. 用户管理

### 4.1 获取用户信息

**接口说明**: 获取当前登录用户信息

- **URL**: `GET /user/profile`
- **认证**: 需要

**响应成功** (200):
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "user": {
      "id": 1,
      "username": "admin",
      "name": "张明",
      "email": "zhangming@example.com",
      "bio": "全栈开发者 | 热爱开源 & 独立产品",
      "avatar": "/images/avatar.jpg",
      "github": "https://github.com/username",
      "bilibili": "https://space.bilibili.com/123456",
      "createdAt": "2024-01-01T00:00:00Z",
      "updatedAt": "2024-01-15T10:00:00Z"
    }
  },
  "timestamp": "2024-01-20T10:00:00Z"
}
```

---

### 4.2 更新用户信息

**接口说明**: 更新用户信息

- **URL**: `PUT /user/profile`
- **认证**: 需要

**请求头**:
```http
Authorization: Bearer {token}
Content-Type: application/json
```

**请求体**:
```json
{
  "name": "string, optional, 显示名称",
  "email": "string, optional, 邮箱地址",
  "bio": "string, optional, 个人简介",
  "github": "string, optional, GitHub链接",
  "bilibili": "string, optional, B站链接"
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
      "username": "admin",
      "name": "新姓名",
      "email": "newemail@example.com",
      "bio": "新的个人简介",
      "avatar": "/images/avatar.jpg",
      "github": "https://github.com/newusername",
      "bilibili": "https://space.bilibili.com/654321",
      "createdAt": "2024-01-01T00:00:00Z",
      "updatedAt": "2024-01-20T10:00:00Z"
    }
  },
  "timestamp": "2024-01-20T10:00:00Z"
}
```

---

## 5. 数据统计

### 5.1 获取下载统计

**接口说明**: 获取产品下载统计数据

- **URL**: `GET /stats/downloads`
- **认证**: 需要

**查询参数**:

| 参数名 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|------|
| period | string | 否 | `7d` | 统计周期: 7d, 30d, 90d |

**响应成功** (200):
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "totalDownloads": 1560,
    "downloadsByProduct": [
      {
        "productId": 1,
        "productName": "快速笔记应用",
        "downloadCount": 450
      },
      {
        "productId": 2,
        "productName": "代码片段管理器",
        "downloadCount": 320
      }
    ],
    "recentDownloads": [
      {
        "date": "2024-01-15",
        "count": 23
      },
      {
        "date": "2024-01-14",
        "count": 18
      }
    ]
  },
  "timestamp": "2024-01-20T10:00:00Z"
}
```

---

## 6. 系统接口

### 6.1 健康检查

**接口说明**: 服务健康状态检查

- **URL**: `GET /health`
- **认证**: 不需要

**响应成功** (200):
```json
{
  "code": 200,
  "message": "服务正常",
  "data": {
    "status": "UP",
    "timestamp": "2024-01-20T10:00:00Z",
    "version": "1.0.0"
  },
  "timestamp": "2024-01-20T10:00:00Z"
}
```

---

### 6.2 测试接口

**接口说明**: 接口连通性测试

- **URL**: `GET /depts`
- **认证**: 不需要

**响应成功** (200):
```json
{
  "code": 200,
  "message": "接口连接正常",
  "data": {
    "timestamp": "2024-01-20T10:00:00Z",
    "status": "OK"
  },
  "timestamp": "2024-01-20T10:00:00Z"
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
| 401 | 未认证 |
| 403 | 权限不足 |
| 404 | 资源不存在 |
| 409 | 资源冲突 |
| 429 | 请求过于频繁 |
| 500 | 服务器内部错误 |

### 业务错误码

| 错误码 | 说明 |
|--------|------|
| 1001 | 用户名或密码错误 |
| 1002 | 用户不存在 |
| 1003 | Token已过期 |
| 1004 | Token无效 |
| 2001 | 产品不存在 |
| 2002 | 产品名称已存在 |
| 2003 | 文件上传失败 |
| 3001 | 文件不存在 |
| 3002 | 文件删除失败 |
| 4001 | 权限不足 |

---

## 数据字典

### 产品状态
```json
{
  "active": "正常",
  "disabled": "禁用"
}
```

### 文件类型
```json
{
  "application/zip": "ZIP压缩包",
  "application/x-rar-compressed": "RAR压缩包",
  "application/x-7z-compressed": "7Z压缩包",
  "image/jpeg": "JPEG图片",
  "image/png": "PNG图片"
}
```

---

## 更新日志

### v1.0 (2024-01-20)
- 初始版本发布
- 包含认证、产品、文件、用户管理接口

---

## 联系我们

如有问题请联系开发团队或查看在线文档。

**文档维护**: 确保本文档与代码实现保持同步。