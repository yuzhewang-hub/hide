# 字幕遮挡工具

一个用于Android系统的字幕遮挡工具，可以在观看视频时创建可拖拽、可调整大小的遮挡块来遮挡字幕。

## 功能特点

- ✅ 悬浮窗遮挡块，可在其他应用之上显示
- ✅ 可拖拽移动位置
- ✅ 可调整大小（长按右下角拖动）
- ✅ 可调节透明度（0-100%）
- ✅ 简洁易用的界面

## 使用说明

1. **首次使用**
   - 启动应用后，系统会提示授予悬浮窗权限
   - 必须授予此权限才能使用遮挡功能

2. **启动遮挡块**
   - 在主界面点击"启动字幕遮挡"开关或点击按钮
   - 屏幕会出现半透明的黑色遮挡块

3. **操作遮挡块**
   - **拖动移动**：手指在遮挡块上滑动即可移动位置
   - **调整大小**：长按右下角边缘并拖动可以调整大小
   - **调节透明度**：在主界面使用滑动条调节遮挡块的透明度

4. **关闭遮挡块**
   - 在主界面关闭开关或点击关闭按钮

## 技术实现

- **开发语言**：Java
- **最低支持**：Android 8.0 (API 26)
- **目标版本**：Android 13 (API 33)
- **主要技术**：
  - WindowManager 实现悬浮窗
  - Service 后台服务管理
  - 触摸事件处理实现拖拽和调整大小

## 项目结构

```
app/
├── src/main/
│   ├── java/com/example/subtitleblocker/
│   │   ├── MainActivity.java          # 主界面
│   │   ├── OverlayService.java        # 悬浮窗服务
│   │   └── OverlayView.java           # 悬浮窗视图
│   ├── res/
│   │   ├── layout/
│   │   │   └── activity_main.xml      # 主界面布局
│   │   ├── values/
│   │   │   ├── strings.xml             # 字符串资源
│   │   │   └── colors.xml              # 颜色资源
│   │   └── mipmap/                     # 应用图标
│   └── AndroidManifest.xml             # 应用清单
├── build.gradle                        # 模块构建配置
└── gradle.properties                   # Gradle属性
```

## 快速开始

### 🚀 快速打包 APK（3种方法）

#### 方法 1：使用构建脚本（最简单）
```bash
# 给脚本添加执行权限
chmod +x build_apk.sh

# 运行脚本
./build_apk.sh

# APK 生成在：app/build/outputs/apk/debug/app-debug.apk
```

#### 方法 2：使用 Android Studio
1. 打开 Android Studio
2. File → Open → 选择项目文件夹
3. Build → Build APK(s)
4. 等待构建完成，点击 "locate" 查看 APK

#### 方法 3：使用 Gradle 命令
```bash
./gradlew assembleDebug

# Windows 用户使用：
gradlew.bat assembleDebug
```

### 📱 安装到手机

**方法 A：USB 直接安装**
```bash
# 连接手机后
./gradlew installDebug
```

**方法 B：传输 APK 文件**
1. 找到生成的 APK：`app/build/outputs/apk/debug/app-debug.apk`
2. 通过 QQ/微信/网盘传输到手机
3. 在手机上打开文件管理器，点击 APK 安装

### ⚙️ 首次使用必读

1. **授予悬浮窗权限**
   - 首次打开应用会提示授权
   - 必须授予才能使用遮挡功能

2. **启动遮挡块**
   - 点击主界面开关或按钮
   - 黑色遮挡块出现在屏幕上

3. **调整遮挡块**
   - 拖动移动位置
   - 右下角长按调整大小
   - 滑动条调节透明度

详细安装教程请查看：[INSTALL.md](INSTALL.md)
快速开始指南请查看：[QUICK_START.md](QUICK_START.md)

## 注意事项

⚠️ **重要提示**：
- 需要授予"悬浮窗权限"才能使用
- 遮挡块会显示在所有应用之上，包括视频播放器
- 调整大小时确保不要遮挡视频内容本身
- 建议在使用前先调整好遮挡块的位置和大小

## 许可证

MIT License

## 开发信息

- 项目名称：字幕遮挡工具
- 包名：com.example.subtitleblocker
- 版本：1.0

