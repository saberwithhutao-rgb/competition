# Smart Campus API 接口文档

## 基础信息

- **项目名称**: Smart Campus 智慧校园系统
- **基础URL**: `http://localhost:8080` (默认端口)
- **响应格式**: JSON
- **统一响应结构**:
```json
{
  "code": 1,
  "msg": "success",
  "data": {}
}
```

## 接口列表

### 1. 竞赛管理模块

#### 1.1 获取竞赛列表
**接口地址**: `GET /competitions`

**请求参数**:
| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| name | string | 否 | 竞赛名称模糊查询 |
| type | string | 否 | 竞赛类型 |
| status | string | 否 | 竞赛状态 |
| level | string | 否 | 竞赛等级 |

**请求示例**:
```bash
GET /competitions?name=数学&type=academic&status=registration_open&level=national
```

**响应示例**:
```json
{
  "code": 1,
  "msg": "success",
  "data": [
    {
      "id": 1,
      "name": "全国大学生数学建模竞赛",
      "description": "面向全国大学生的年度学术科技竞赛",
      "competitionTime": "2024-09-15",
      "duration": "4天",
      "location": "线上/线下",
      "tags": ["数学", "建模", "创新"],
      "status": "registration_open",
      "statusText": "报名中",
      "type": "academic",
      "level": "national",
      "registrationDeadline": "2024-08-30",
      "organizer": "中国工业与应用数学学会",
      "contact": "math_model@csiam.org.cn",
      "requirements": "在校大学生，三人一组",
      "createdAt": "2024-01-15",
      "updatedAt": "2024-01-20",
      "officialWebsite": "http://www.mcm.edu.cn"
    }
  ]
}
```

#### 1.2 获取竞赛详情及规则
**接口地址**: `GET /competitions/{id}`

**路径参数**:
| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| id | integer | 是 | 竞赛ID |

**请求示例**:
```bash
GET /competitions/1
```

**响应示例**:
```json
{
  "code": 1,
  "msg": "success",
  "data": [
    {
      "id": 1,
      "competitionId": 1,
      "category": "eligibility",
      "title": "参赛资格要求",
      "content": "1. 参赛者须为在校大学生...",
      "sortOrder": 1,
      "required": true,
      "createdAt": "2024-01-15T10:30:00",
      "updatedAt": "2024-01-15T10:30:00"
    }
  ]
}
```

### 2. 院校管理模块

#### 2.1 获取院校列表
**接口地址**: `GET /universities`

**请求示例**:
```bash
GET /universities
```

**响应示例**:
```json
{
  "code": 1,
  "msg": "success",
  "data": [
    {
      "id": 1,
      "name": "清华大学",
      "shortName": "清华",
      "code": "10003",
      "province": "北京市",
      "city": "北京市",
      "is985": true,
      "is211": true,
      "isDoubleFirstClass": true,
      "hasDoctorate": true,
      "hasMaster": true,
      "institutionType": "综合类",
      "tags": "[\"985\",\"双一流\",\"教育部直属\"]",
      "officialWebsite": "https://www.tsinghua.edu.cn",
      "createdAt": "2024-01-15T10:00:00",
      "updatedAt": "2024-01-15T10:00:00"
    }
  ]
}
```

#### 2.2 收藏/取消收藏院校
**接口地址**: `POST /universities`

**请求头**:
| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| X-User-Id | integer | 是 | 当前用户ID |

**请求参数**:
| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| universityId | integer | 是 | 院校ID |

**请求示例**:
```bash
POST /universities 
-H "X-User-Id: 123" 
-d "universityId=1"
```

**响应示例**:
```json
{
  "code": 1,
  "msg": "success",
  "data": null
}
```

#### 2.3 检查院校收藏状态
**接口地址**: `GET /universities/check`

**请求参数**:
| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| universityId | integer | 是 | 院校ID |

**请求头**:
| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| X-User-Id | integer | 是 | 当前用户ID |

**请求示例**:
```bash
GET /universities/check?universityId=1 
-H "X-User-Id: 123"
```

**响应示例**:
```json
{
  "code": 1,
  "msg": "success",
  "data": true
}
```

#### 2.4 获取用户收藏的院校ID列表
**接口地址**: `GET /universities/university-ids`

**请求头**:
| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| X-User-Id | integer | 是 | 当前用户ID |

**请求示例**:
```bash
GET /universities/university-ids 
-H "X-User-Id: 123"
```

**响应示例**:
```json
{
  "code": 1,
  "msg": "success",
  "data": [1, 2, 3]
}
```

#### 2.5 获取用户收藏的院校详细列表
**接口地址**: `GET /universities/list`

**请求头**:
| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| X-User-Id | integer | 是 | 当前用户ID |

**请求示例**:
```bash
GET /universities/list 
-H "X-User-Id: 123"
```

**响应示例**:
```json
{
  "code": 1,
  "msg": "success",
  "data": [
    {
      "id": 1,
      "userId": 123,
      "universityId": 1,
      "createdAt": "2024-01-15T10:30:00"
    }
  ]
}
```

#### 2.6 获取院校被收藏次数
**接口地址**: `GET /universities/count`

**请求参数**:
| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| universityId | integer | 是 | 院校ID |

**请求示例**:
```bash
GET /universities/count?universityId=1
```

**响应示例**:
```json
{
  "code": 1,
  "msg": "success",
  "data": 156
}
```

### 3. AI智能对话模块

#### 3.1 OpenAI聊天接口
**接口地址**: `POST /ai/chat/openai`

**请求参数**:
| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| message | string | 是 | 用户发送的消息内容 |
| chanId | string | 是 | 对话通道ID，用于区分不同对话历史 |

**请求示例**:
```bash
POST /ai/chat/openai?message=我想了解计算机科学专业&chanId=user123_session1
```

**响应示例**:
```
Content-Type: text/html; charset=utf-8

作为一名专业的职业导航咨询助手，我很乐意为您介绍计算机科学专业...
```

**功能说明**:
- 基于阿里云DashScope平台的Qwen大模型
- 支持流式响应，实时返回AI回复内容
- 具备对话记忆功能，可通过chanId维护对话上下文
- 预设系统角色为"职业导航咨询助手小职"
- 模型配置：temperature=0.6，使用qwen-max模型

### 4. 能力标签模块

> ⚠️ **注意**: 能力标签模块接口在控制器中已声明但尚未实现具体业务逻辑，暂不可用。

## AI模块配置说明

### 系统配置
- **AI平台**: 阿里云DashScope
- **基础URL**: `https://dashscope.aliyuncs.com/compatible-mode`
- **模型**: qwen-max
- **温度参数**: 0.6
- **系统角色**: 职业导航咨询助手小职

### 环境变量
需要在环境变量中设置 `API_KEY` 来配置阿里云DashScope的访问密钥。

## 数据模型定义

### Competition (竞赛实体)
| 字段名 | 类型 | 说明 |
|--------|------|------|
| id | Integer | 主键 |
| name | String | 比赛名称 |
| description | String | 描述 |
| competitionTime | LocalDate | 比赛时间 |
| duration | String | 持续时间 |
| location | String | 地点 |
| tags | List<String> | 标签 |
| status | String | 状态 |
| statusText | String | 状态文本 |
| type | String | 类型 |
| level | String | 等级 |
| registrationDeadline | LocalDate | 报名截止时间 |
| organizer | String | 组织者 |
| contact | String | 联系方式 |
| requirements | String | 要求 |
| createdAt | LocalDate | 创建时间 |
| updatedAt | LocalDate | 更新时间 |
| officialWebsite | String | 官方网站 |

### CompetitionRule (竞赛规则实体)
| 字段名 | 类型 | 说明 |
|--------|------|------|
| id | Integer | 主键 |
| competitionId | Integer | 对应竞赛ID |
| category | String | 规则分类 |
| title | String | 规则标题 |
| content | String | 规则内容 |
| sortOrder | Integer | 排序 |
| required | Boolean | 是否必需 |
| createdAt | LocalDateTime | 创建时间 |
| updatedAt | LocalDateTime | 更新时间 |

### University (院校实体)
| 字段名 | 类型 | 说明 |
|--------|------|------|
| id | Integer | 主键 |
| name | String | 学校全称 |
| shortName | String | 简称 |
| code | String | 教育部院校代码 |
| province | String | 所在省份 |
| city | String | 所在城市 |
| is985 | Boolean | 是否985高校 |
| is211 | Boolean | 是否211高校 |
| isDoubleFirstClass | Boolean | 是否双一流建设高校 |
| hasDoctorate | Boolean | 是否有博士点 |
| hasMaster | Boolean | 是否有硕士点 |
| institutionType | String | 院校类型 |
| tags | String | 标签(JSON字符串) |
| officialWebsite | String | 学校官方网站 |
| createdAt | LocalDateTime | 记录创建时间 |
| updatedAt | LocalDateTime | 记录最后更新时间 |

### UserFavoriteUniversity (用户收藏院校)
| 字段名 | 类型 | 说明 |
|--------|------|------|
| id | Integer | 主键 |
| userId | Integer | 用户ID |
| universityId | Integer | 院校ID |
| createdAt | LocalDateTime | 收藏时间 |

### UserAbilityDimension (用户能力维度)
| 字段名 | 类型 | 说明 |
|--------|------|------|
| id | Long | 主键 |
| userId | Long | 用户ID |
| reasoning | Integer | 推理能力 |
| calculation | Integer | 计算能力 |
| memory | Integer | 记忆能力 |
| creativity | Integer | 创造能力 |
| spatial | Integer | 空间想象能力 |
| observation | Integer | 观察能力 |
| createdAt | LocalDateTime | 创建时间 |
| updatedAt | LocalDateTime | 更新时间 |

### AbilityRade (能力等级)
| 字段名 | 类型 | 说明 |
|--------|------|------|
| name | String | 固定值:"自身竞赛能力" |
| value | List<Integer> | 能力值列表 |

## 当前可用接口状态

### ✅ 已完整实现的模块
1. **竞赛管理模块**
   - 竞赛列表查询 `/competitions` (GET)
   - 竞赛详情及规则查询 `/competitions/{id}` (GET)

2. **院校管理模块**
   - 院校列表查询 `/universities` (GET)
   - 院校收藏管理 `/universities/toggle` (POST)
   - 收藏状态检查 `/universities/check` (GET)
   - 用户收藏院校ID列表 `/universities/university-ids` (GET)
   - 用户收藏院校详细列表 `/universities/list` (GET)
   - 院校被收藏次数统计 `/universities/count` (GET)

3. **AI智能对话模块**
   - OpenAI聊天接口 `/ai/chat/openai` (POST)

### ⚠️ 待完善模块
- **能力标签模块**: 控制器已声明但业务逻辑未实现

## 错误处理

常见错误类型：
- `BindingException`: 参数绑定异常，通常是Mapper XML中参数名与接口不匹配
- `PSQLException`: PostgreSQL数据库相关错误，如字段不存在
- `IllegalArgumentException`: 参数验证失败，如ID为空或小于等于0

## 注意事项

1. 所有接口均采用RESTful风格设计
2. 使用统一的响应格式，包含code、msg、data三个字段
3. 时间参数使用 `LocalDate` 或 `LocalDateTime` 类型
4. 数据库字段使用下划线命名法，Java实体使用驼峰命名法
5. MyBatis配置已开启驼峰命名转换
6. 用户身份通过请求头 `X-User-Id` 传递
7. 参数验证在服务层进行，无效参数会抛出 `IllegalArgumentException`
8. 建议在生产环境中添加JWT认证授权机制
9. 收藏相关接口具有幂等性，重复操作不会产生副作用
10. AI模块需要配置有效的API_KEY环境变量才能正常使用
11. AI聊天接口返回text/html格式的流式响应