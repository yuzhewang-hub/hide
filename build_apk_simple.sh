#!/bin/bash

echo "🚀 开始构建字幕遮挡工具 APK..."
echo ""
echo "⚠️  注意：此项目需要使用 Android Studio 来构建"
echo ""

# 检查 Android Studio 是否安装
if command -v studio &> /dev/null || [ -d "/Applications/Android Studio.app" ]; then
    echo "✅ 检测到 Android Studio"
    echo ""
    echo "📋 请按以下步骤操作："
    echo ""
    echo "1. 打开 Android Studio"
    echo "2. 选择 'Open' 或 'File → Open'"
    echo "3. 选择项目文件夹: $(pwd)"
    echo "4. 等待 Gradle 同步完成"
    echo "5. 点击菜单 Build → Build APK(s)"
    echo "6. 构建完成后点击 'locate' 查看 APK"
    echo ""
    echo "APK 文件位置：app/build/outputs/apk/debug/app-debug.apk"
    echo ""
else
    echo "❌ 未找到 Android Studio"
    echo ""
    echo "请选择一个方案："
    echo ""
    echo "方案 1：安装 Android Studio（推荐）"
    echo "   下载地址：https://developer.android.com/studio"
    echo "   安装后按照上面的步骤操作"
    echo ""
    echo "方案 2：使用在线构建服务"
    echo "   - AppCenter (需要账号)：https://appcenter.ms"
    echo "   - GitHub Actions（需要GitHub）"
    echo ""
    echo "方案 3：手动构建（需要安装 Android SDK）"
    echo "   1. 安装 Android SDK"
    echo "   2. 配置环境变量"
    echo "   3. 运行构建命令"
    echo ""
fi

echo "⚠️  或者，我可以为你提供预编译的 APK 文件..."
echo "   需要我生成吗？"

