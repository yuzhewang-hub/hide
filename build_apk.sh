#!/bin/bash

# 字幕遮挡工具 - APK 构建脚本
# 此脚本用于快速生成 APK 安装包

echo "🚀 字幕遮挡工具 - APK 构建脚本"
echo ""
echo "⚠️  构建此项目需要使用 Android Studio"
echo ""

# 检查 gradle wrapper 是否存在
if [ ! -f "gradlew" ]; then
    echo "❌ Gradle wrapper 未找到"
    echo ""
    echo "请查看详细说明："
    echo "   - AndroidStudio使用指南.md"
    echo "   - 使用说明.md"
    echo ""
    echo "或安装 Android Studio 后重新运行此脚本"
    exit 1
fi

# 尝试构建
echo "📦 开始构建..."
echo ""

# 执行构建
./gradlew assembleDebug

# 检查结果
if [ $? -eq 0 ]; then
    echo ""
    echo "✅ 构建成功！"
    echo ""
    echo "📱 APK 位置："
    echo "   $(pwd)/app/build/outputs/apk/debug/app-debug.apk"
    echo ""
    echo "📋 下一步："
    echo "   1. 打开上面的路径"
    echo "   2. 将 app-debug.apk 发送到手机"
    echo "   3. 在手机上安装 APK"
    echo "   4. 打开应用，授予悬浮窗权限"
    echo "   5. 开始使用！"
    echo ""
else
    echo ""
    echo "❌ 构建失败"
    echo ""
    echo "可能的原因："
    echo "   1. 缺少 Android SDK"
    echo "   2. 需要安装 Android Studio"
    echo ""
    echo "📖 请查看完整教程："
    echo "   - AndroidStudio使用指南.md"
    echo "   - 使用说明.md"
    exit 1
fi

