@echo off
REM 字幕遮挡工具 - APK 构建脚本 (Windows)
REM 此脚本用于快速生成 APK 安装包

echo.
echo 🚀 开始构建字幕遮挡工具 APK...
echo.

REM 检查是否在项目根目录
if not exist "build.gradle" (
    echo ❌ 错误：请在项目根目录下运行此脚本
    pause
    exit /b 1
)

echo 📦 正在清理旧文件...
call gradlew.bat clean

echo.
echo 🔨 正在构建 Debug APK...
call gradlew.bat assembleDebug

REM 检查构建结果
if %errorlevel% == 0 (
    echo.
    echo ✅ 构建成功！
    echo.
    echo 📱 APK 位置：
    echo    %cd%\app\build\outputs\apk\debug\app-debug.apk
    echo.
    echo 📋 下一步操作：
    echo    1. 将 APK 传输到手机
    echo    2. 在手机上点击安装
    echo    3. 授予悬浮窗权限
    echo    4. 开始使用！
    echo.
) else (
    echo.
    echo ❌ 构建失败，请检查错误信息
)

pause

