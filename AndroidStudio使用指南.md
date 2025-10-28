# Android Studio 使用指南

## 🎯 最简单的方法：使用 Android Studio 构建

### 第一步：安装 Android Studio

1. **下载 Android Studio**
   - 访问：https://developer.android.com/studio
   - 选择适合你系统的版本（macOS/Windows/Linux）
   - 下载并安装

2. **安装配置**
   - 打开 Android Studio
   - 选择 "Do not import settings"（首次使用）
   - 等待组件下载完成（可能需要 10-20 分钟）
   - 点击 "Finish" 完成设置

### 第二步：打开项目

1. 打开 Android Studio
2. 点击 "Open" 或 "File → Open"
3. 选择项目文件夹：`/Users/lijiadong/Documents/file/hide`
4. 等待项目加载

**首次打开时会自动下载 Gradle 和依赖**（需要几分钟）

### 第三步：构建 APK

#### 方法 1：直接构建 APK（推荐）
1. 点击顶部菜单 **Build → Build Bundle(s) / APK(s) → Build APK(s)**
2. 等待构建完成（1-3 分钟）
3. 构建完成后会弹出提示，点击 **"locate"**
4. 在文件浏览器中打开 APK 所在文件夹
5. APK 文件：`app-debug.apk`

#### 方法 2：运行并直接安装
1. 用 USB 连接手机
2. 在手机上启用 USB 调试（开发者选项）
3. 点击 Android Studio 顶部的运行按钮 ▶️
4. 选择你的手机设备
5. 应用会自动安装到手机

### 第四步：安装到手机

**方法 A：传输 APK 文件**
```
1. 找到生成的文件：
   app/build/outputs/apk/debug/app-debug.apk

2. 发送到手机：
   - QQ 文件助手
   - 微信文件传输
   - USB 传输
   - 网盘（百度云、OneDrive 等）

3. 在手机上安装：
   - 打开文件管理器
   - 找到 app-debug.apk
   - 点击安装
   - 如果提示"未知来源"，去设置允许安装
```

**方法 B：直接安装（需要 USB）**
```
1. 连接手机到电脑
2. 在手机上启用 USB 调试
3. 运行 Android Studio 的安装命令
4. 或直接点击运行按钮 ▶️
```

## ⚡ 快速命令（如果熟悉命令行）

如果 Android Studio 安装完成，可以在项目根目录运行：

```bash
# 在 Android Studio 的 Terminal 中
./gradlew assembleDebug

# 或使用完整路径
cd ~/Android/Sdk && ./gradlew assembleDebug --project-dir=/Users/lijiadong/Documents/file/hide
```

## 🎬 完整流程

1. **准备** → 安装 Android Studio
2. **打开** → 在 Android Studio 中打开项目
3. **构建** → Build → Build APK(s)
4. **传输** → 将 APK 发送到手机
5. **安装** → 在手机上安装 APK
6. **使用** → 打开应用，授予权限，开始使用！

## 📱 手机设置（如果需要 USB 调试）

### 启用开发者选项
1. 打开手机"设置"
2. 找到"关于手机"或"系统 → 关于手机"
3. 连续点击"版本号" 7 次
4. 提示"您已成为开发者"

### 启用 USB 调试
1. 返回设置主界面
2. 进入"开发者选项"
3. 开启"USB 调试"
4. 用 USB 连接电脑
5. 手机上点击"允许 USB 调试"

## ❓ 常见问题

**Q: 构建很慢怎么办？**
A: 首次构建需要下载 SDK 和依赖，后面会快很多。可以等待或使用更快的网络。

**Q: 提示需要 Android SDK？**
A: 在 Android Studio 中：
   - Tools → SDK Manager
   - 安装 Android SDK Platform 和 Build Tools

**Q: 找不到 APK 文件？**
A: 路径通常是：`项目目录/app/build/outputs/apk/debug/app-debug.apk`
   在 Android Studio 的 Project 窗口中可以找到

**Q: 手机无法安装？**
A: 检查设置中是否允许安装"未知来源"应用

## 💡 提示

- 首次使用需要 20-30 分钟（下载组件）
- 后面构建只需要 1-3 分钟
- 建议使用稳定的网络环境
- 保持 Android Studio 更新以获取最新工具

---

**需要帮助？查看其他文档：**
- `使用说明.md` - 应用使用教程
- `INSTALL.md` - 详细安装指南
- `README.md` - 项目介绍

