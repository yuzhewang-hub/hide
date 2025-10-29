# 🚀 GitHub Actions 构建 - 完整指南

## ✅ 已完成
- ✅ Git 仓库已初始化
- ✅ 所有文件已提交
- ✅ GitHub Actions 配置已就绪

## 📋 接下来的步骤

### 第一步：创建 GitHub 仓库

#### 方法 1：在网站上创建（推荐）

1. **访问 GitHub**
   - 打开：https://github.com
   - 登录你的账号（如果没有，先注册一个免费的）

2. **创建新仓库**
   - 点击右上角的 `+` 号
   - 选择 "New repository"
   
3. **设置仓库信息**
   ```
   仓库名称：hide
   
   描述：字幕遮挡工具 - 用于观看视频时遮挡字幕
   
   可见性：
   • Private（推荐）- 只有你能看到
   • Public（公开） - 任何人都能看到代码
   
   重要：⚠️ 不要勾选
   ❌ Add a README file
   ❌ Add .gitignore
   ❌ Choose a license
   ```
   
4. **点击 "Create repository"**

---

### 第二步：推送代码到 GitHub

复制并执行以下命令：

```bash
# 添加远程仓库
git remote add origin https://github.com/你的用户名/hide.git

# 将 main 分支重命名为 master（如果仓库要求）
git branch -M main

# 推送代码
git push -u origin main
```

**注意**：将 `你的用户名` 替换为你的 GitHub 用户名

**示例**：
```bash
git remote add origin https://github.com/lijiadong/hide.git
git branch -M main
git push -u origin main
```

---

### 第三步：触发构建

1. **访问仓库页面**
   - 刷新仓库页面
   - 你会看到所有文件已上传

2. **打开 Actions**
   - 点击仓库顶部的 "Actions" 标签
   
3. **手动触发构建**
   - 在左侧选择 "构建字幕遮挡工具 APK"
   - 点击右侧的 "Run workflow" 下拉菜单
   - 点击绿色的 "Run workflow" 按钮
   
4. **等待构建**
   - 构建过程大约需要 3-5 分钟
   - 你可以看到实时的构建日志

---

### 第四步：下载 APK

1. **构建完成后**
   - 在 Actions 页面，找到刚完成的构建（显示绿色 ✅）
   - 点击进入构建详情

2. **下载 APK**
   - 在构建详情页面，找到 "Artifacts" 部分
   - 点击 "字幕遮挡工具-APK"
   - 下载 `app-debug.apk`

3. **或直接点击**
   ```
   APK 文件会在页面上显示，直接下载即可
   ```

---

### 第五步：安装到手机

#### 方法 A：通过 QQ/微信传输
1. 将下载的 APK 文件用 QQ 或微信发送到手机
2. 在手机上打开 QQ/微信，下载文件
3. 点击文件，选择 "安装"

#### 方法 B：通过 USB 传输
1. 用 USB 连接手机到电脑
2. 将 APK 文件复制到手机
3. 在手机上打开文件管理器
4. 找到并点击 APK 文件安装

#### 方法 C：通过云盘
1. 上传 APK 到百度云/OneDrive 等
2. 在手机上下载
3. 打开并安装

---

## ⚠️ 常见问题

### Q: 推送代码时要求登录？
A: 需要输入你的 GitHub 用户名和密码（或 token）

### Q: 如果提示未授权？
A: 可能需要使用 Personal Access Token
   - 设置：GitHub → Settings → Developer settings → Personal access tokens
   - 创建新 token，勾选 repo 权限
   - 用 token 替代密码

### Q: 构建失败怎么办？
A: 查看构建日志：
   - Actions 页面 → 点击失败的构建
   - 查看错误信息
   - 通常是代码问题，我可以帮你修复

### Q: 如何更新应用？
A: 修改代码后：
   ```bash
   git add .
   git commit -m "更新说明"
   git push
   ```
   然后在 Actions 页面手动触发构建

---

## 🎯 快速参考

### 推送代码到 GitHub
```bash
git remote add origin https://github.com/你的用户名/hide.git
git push -u origin main
```

### 每次更新后重新构建
1. 修改代码
2. 在 Actions 页面点击 "Run workflow"
3. 等待构建完成
4. 下载新的 APK

### APK 位置
```
GitHub Actions → 构建详情 → Artifacts → 字幕遮挡工具-APK
```

---

## 📝 现在就开始

1. ✅ 访问 https://github.com/new 创建仓库
2. ✅ 复制仓库 URL（例如：https://github.com/你的用户名/hide.git）
3. ✅ 执行上面的推送命令
4. ✅ 打开 Actions 页面触发构建
5. ✅ 等待 5 分钟后下载 APK
6. ✅ 安装到手机，开始使用！

**总共需要约 10 分钟** ⏱️


