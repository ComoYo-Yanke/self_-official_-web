######## ① 开工（1 次/天） ########
git pull origin master                  # 最新代码
git checkout -b feat/dept-list          # 新建功能分支（分支名：feat/xxx 或 fix/xxx）

######## ② 写代码（无限循环） ########
yarn dev                                # 热重载开发
# ... 写代码 ...

######## ③ 提交（原子级，1~3 次/天） ########
git add .                               # 只添加相关文件
git commit -m "feat: 部门列表页面 + 新增按钮"   # 规范格式见下
# 如果想改上一次 → git commit --amend

######## ④ 合并（推送 + PR） ########
git push origin feat/dept-list          # 首次推送会提示创建 upstream，照抄即可
# 去 Gitee → 创建 Pull Request → 源分支 feat/dept-list → 目标 master
# 自己点「合并」即可（单人项目可直推 master：git checkout master && git merge feat/dept-list）

######## ⑤ 上线（CD） ########
git checkout master
git pull origin master
yarn build
rsync -avz --delete dist/ 云服务器:/var/www/html/   # 示例同步命令