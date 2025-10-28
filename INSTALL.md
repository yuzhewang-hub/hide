# 安装和使用指南

## 📦 方法一：直接构建APK安装包（推荐）

### 步骤 1：准备Android开发环境

1. **安装 Android Studio**
   - 下载地址：https://developer.android.com/studio
   - 选择适合你操作系统的版本（macOS/Windows/Linux）
   - 安装时确保勾选 "Android SDK" 和 "Android SDK Platform"

2. **配置环境**
   - 打开 Android Studio
   - 完成初始设置向导
   - 等待 SDK 组件下载完成

### 步骤 2：打开项目

1. 打开 Android Studio
2. 选择 **File → Open**
3. 选择 `hide` 文件夹
4. 等待 Gradle 同步完成（首次可能需要下载依赖）

### 步骤 3：生成 APK 安装包

#### 方法 A：通过 Android Studio（最简单）

1. 点击菜单栏 **Build → Build Bundle(s) / APK(s) → Build APK(s)**
2. 等待构建完成
3. 完成后会弹出提示，点击 "locate"
4. APK 文件位置：`app/build/outputs/apk/debug/app-debug.apk`

#### 方法 B：使用 Gradle 命令行

在项目根目录下执行：

```bash
# 在 macOS/Linux 上
./gradlew assembleDebug

# 在 Windows 上
gradlew.bat assembleDebug
```

APK 生成位置：`app/build/outputs/apk/debug/app-debug.apk`

### 步骤 4：安装到手机

#### 选项 A：通过 USB 连接（推荐）

1. **启用手机开发者选项**
   - 打开手机的"设置"
   - 找到"关于手机"
   - 连续点击"版本号"7次直到提示"您已成为开发者"
   
2. **启用 USB 调试**
   - 返回设置
   - 进入"系统"→"开发者选项"
   - 开启"USB调试"

3. **连接并安装**
   - 用 USB 线连接手机和电脑
   - 手机点击允许 USB 调试
   - 在 Android Studio 中选择你的设备
   - 点击运行按钮（绿色播放图标）

#### 选项 B：直接传输 APK

1. 将生成的 `app-debug.apk` 传输到手机
   - 方法：USB传输、QQ文件、微信文件、网盘等
2. 在手机上打开文件管理器
3. 找到 APK 文件并点击安装
4. 允许"未知来源"安装（如提示）

## 📦 方法二：生成正式签名版本（可选）

如果需要生成可发布的正式版本：

### 步骤 1：创建签名密钥

在 Android Studio 中：
1. 点击 **Build → Generate Signed Bundle / APK**
2. 选择 **APK**
3. 如果没有密钥，点击 "Create new..." 创建
4. 填写密钥信息并保存

### 步骤 2：配置签名

在 `app/build.gradle` 的 `android` 块中添加：

```gradle
signingConfigs {
    release {
        storeFile file("你的密钥文件路径")
        storePassword "你的密钥密码"
        keyAlias "你的密钥别名"
        keyPassword "你的密钥别名密码"
    }
}

buildTypes {
    release {
        signingConfig signingConfigs.release
        minifyEnabled false
    }
}
```

### 步骤 3：生成 Release APK

```bash
./gradlew assembleRelease
```

APK 位置：`app/build/outputs/apk/release/app-release.apk`

## 📱 使用方法

### 首次使用

1. **启动应用**
   - 打开"字幕遮挡工具"

2. **授予权限**
   - 首次启动会提示授予"悬浮窗权限"
   - 点击"去设置"或手动在系统设置中开启
   - 确保授权，否则无法使用

3. **启动遮挡块**
   - 在主页点击"启动字幕遮挡"开关
   - 屏幕会出现黑色遮挡块

### 日常使用

1. **调节透明度**
   - 在主界面拖动"透明度"滑动条
   - 范围：0%（完全透明）到 100%（完全不透明）

2. **移动遮挡块**
   - 手指放在遮挡块上滑动即可移动位置
   - 可以移到屏幕任意位置

3. **调整大小**
   - 长按遮挡块右下角
   - 拖动调整宽度和高度
   - 适合你的字幕位置

4. **关闭遮挡块**
   - 返回应用主界面
   - 关闭"启动字幕遮挡"开关

## ⚠️ 注意事项

1. **权限提示**
   - 必须授予悬浮窗权限才能正常使用
   - 不同手机品牌设置位置可能不同
   - 通常是：设置 → 应用 → 悬浮窗（显示在其他应用上层）

2. **使用建议**
   - 在视频播放前先调整好遮挡块的位置和大小
   - 注意不要遮挡视频内容本身
   - 可以根据不同视频调整透明度

3. **性能优化**
   - 遮挡块使用硬件加速，不会影响视频播放
   - 如果感觉卡顿，可以重启应用

## 🐛 常见问题

**Q: 为什么看不到遮挡块？**
A: 检查是否授予了悬浮窗权限，在设置中开启"显示在其他应用上层"权限。

**Q: 遮挡块无法拖动？**
A: 确保没有同时开启其他悬浮窗工具，关闭所有键盘等悬浮应用再试。

**Q: 如何卸载？**
A: 在系统设置中找到"字幕遮挡工具"，点击"卸载"即可。

## 📞 技术支持

如果遇到问题：
1. 确保 Android 版本在 8.0 以上
2. 检查是否授予了所有必要权限
3. 重启手机后重试

---

**祝您使用愉快！** 🎬

