create table user(
                     id int unsigned KEY comment "用户id",
                     username varchar(20) comment "用户名",
                     password varchar(32)  comment "密码",
                     name varchar(10) comment "姓名-昵称",
                     avatarURL varchar(255) comment "头像url",
                     bio varchar(255) comment "个人简介",
                     location varchar(168) comment "位置",
                     email varchar(50) comment  "邮箱",
                     github varchar(80) comment "githubURL",
                     bilibili varchar(150) comment "bilibiliURL",
                     role tinyint unsigned comment  "角色 1 管理员 2 普通用户 3 测试用户",
                     created_at datetime comment "创建时间",
                     updated_at datetime comment "更新时间"
);