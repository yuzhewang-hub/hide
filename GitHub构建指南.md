# 🚀 使用 GitHub 自动构建 APK

## 这个方法的好处
✅ 不需要安装 Android Studio
✅ 不需要配置本地环境
✅ 完全自动化
✅ 免费使用

---

## 步骤 1：上传代码到 GitHub

### 方案 A：使用 GitHub Desktop（最简单）

1. **下载 GitHub Desktop**
   - 访问：https://desktop.github.com
   - 下载并安装

2. **设置账户**
   - 登录你的 GitHub 账号
   - 如果没有，先注册一个（免费）

3. **创建仓库**
   - 点击 "File → Add Local Repository"
   - 选择文件夹：`/Users/lijiadong/Documents/file/hide`
   - 点击 "Publish repository"
   - 选择私有或公开（建议私有）

4. **推送代码**
   - 在 GitHub Desktop 中点击 "Push origin"
   - 代码已上传到 GitHub

### 方案 B：使用命令行

```bash
# 进入项目目录
cd /Users/lijiadong/Documents/file/hide

# 初始化 Git
git init
git add .
git commit -m "Initial commit: 字幕遮挡工具"

# 在 GitHub 网站创建新仓库，然后执行
git remote add origin https://github.com/你的用户名/hide.git
git push -u origin main
```

---

## 步骤 2：构建 APK

1. **访问你的 GitHub 仓库页面**
   - 打开你的仓库 URL（例如：https://github.com/你的用户名/hide）

2. **触发构建**
   - 点击 "Actions" 标签
   - 在左侧选择 "构建字幕遮挡工具 APK"
   - 点击右侧的 "Run workflow" 按钮
   - 点击绿色的 "Run workflow" 确认

3. **等待构建**
   - 可以看到构建进度
   - 通常需要 3-5 分钟

4. **下载 APK**
   - 构建完成后，在构建页面找到 "Artifacts"
   - 点击下载 "字幕遮挡工具-APK.zip"
   - 解压后得到 `app-debug.apk`

---

## 步骤 3：安装到手机

1. **传输 APK**
   - 将 `app-debug.apk` 通过 QQ/微信发送到手机
   - 或通过 USB 传输

2. **安装**
   - 在手机上打开文件
   - 点击安装
   - 允许"未知来源"安装（如提示）

3. **使用**
   - 打开应用
   - 授予悬浮窗权限
   - 开始使用！

---

## 🎯 完整流程

```
创建 GitHub 仓库
    ↓
推送代码
    ↓
GitHub Actions 自动构建（5分钟）
    ↓
下载 APK
    ↓
安装到手机
    ↓
开始使用！
```

**总时间：约 10-15 分钟**

---

## 💡 小贴士

### 如果构建失败
- 检查代码是否有语法错误
- 查看 Actions 页面并提供 log

### 每次修改代码后
- 只需推送代码到 GitHub
- 构建会自动触发
- 下载新的 APK

### 公开 vs 私有仓库
- **私有**：只有你能看到
- **公开**：任何人都能看到代码
- 建议使用私有仓库

---

## 📞 需要帮助？

如果遇到问题：
1. 查看构建日志（Actions 页面）
2. 确保所有文件都已上传
3. 检查是否遵循了所有步骤

---

**这个方法完全免费，而且最省事！** 🎉

