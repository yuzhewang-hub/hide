package com.example.subtitleblocker;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Color;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

public class OverlayView extends FrameLayout {
    private WindowManager.LayoutParams params;
    private WindowManager windowManager;
    private OverlayPreferences preferences;
    private float initialX, initialY;
    private int initialTouchX, initialTouchY;
    private int initialWidth, initialHeight;
    private boolean isResizing = false;
    private int resizeEdge = -1; // 0:右下角, 1:左下角, 2:左上角, 3:右上角, 4:右边缘, 5:左边缘, 6:下边缘, 7:上边缘
    private static final int DRAG_THRESHOLD = 10;
    private static final int EDGE_THRESHOLD = 40; // 边缘触摸阈值
    private static final int MIN_WIDTH = 100;
    private static final int MIN_HEIGHT = 50;
    private boolean isLocked = false;

    public OverlayView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        preferences = new OverlayPreferences(context);

        // 创建一个黑色背景的视图
        View backgroundView = new View(context);
        backgroundView.setBackgroundColor(Color.BLACK);
        addView(backgroundView, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));

        // 设置窗口参数
        params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                        | WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                PixelFormat.TRANSLUCENT);

        // 从SharedPreferences加载保存的位置和大小
        params.width = preferences.getWidth();
        params.height = preferences.getHeight();
        params.gravity = Gravity.TOP | Gravity.START;
        params.x = preferences.getX();
        params.y = preferences.getY();

        // 设置透明度
        float savedAlpha = preferences.getAlpha();
        setAlpha(savedAlpha);

        // 加载锁定状态
        isLocked = preferences.isLocked();

        setupTouchListener();
    }

    private void setupTouchListener() {
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // 如果锁定，则不允许拖动和调整大小
                if (isLocked) {
                    return false; // 让触摸事件传递给下层，不影响其他操作
                }

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initialX = event.getRawX();
                        initialY = event.getRawY();
                        initialTouchX = params.x;
                        initialTouchY = params.y;
                        initialWidth = params.width;
                        initialHeight = params.height;

                        // 检查是否在边缘（用于调整大小）
                        int edge = checkEdge(event);
                        if (edge >= 0) {
                            isResizing = true;
                            resizeEdge = edge;
                        }
                        break;

                    case MotionEvent.ACTION_MOVE:
                        if (isResizing) {
                            handleResize(event);
                        } else {
                            handleDrag(event);
                        }
                        break;

                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        if (isResizing || Math.abs(initialTouchX - params.x) > DRAG_THRESHOLD ||
                                Math.abs(initialTouchY - params.y) > DRAG_THRESHOLD) {
                            // 保存位置和大小
                            saveState();
                        }
                        isResizing = false;
                        resizeEdge = -1;
                        break;
                }
                return true;
            }
        });
    }

    private void handleDrag(MotionEvent event) {
        int dx = (int) (event.getRawX() - initialX);
        int dy = (int) (event.getRawY() - initialY);

        // 只有移动距离超过阈值才认为是拖动
        if (Math.abs(dx) > DRAG_THRESHOLD || Math.abs(dy) > DRAG_THRESHOLD) {
            int screenWidth = windowManager.getDefaultDisplay().getWidth();
            int screenHeight = windowManager.getDefaultDisplay().getHeight();

            params.x = initialTouchX + dx;
            params.y = initialTouchY + dy;

            // 限制在屏幕范围内
            params.x = Math.max(0, Math.min(params.x, screenWidth - params.width));
            params.y = Math.max(0, Math.min(params.y, screenHeight - params.height));

            windowManager.updateViewLayout(this, params);
        }
    }

    private void handleResize(MotionEvent event) {
        float currentX = event.getRawX();
        float currentY = event.getRawY();
        float deltaX = currentX - initialX;
        float deltaY = currentY - initialY;

        int screenWidth = windowManager.getDefaultDisplay().getWidth();
        int screenHeight = windowManager.getDefaultDisplay().getHeight();

        switch (resizeEdge) {
            case 0: // 右下角 - 增加宽度和高度
                int newWidth1 = initialWidth + (int) deltaX;
                int newHeight1 = initialHeight + (int) deltaY;
                if (newWidth1 >= MIN_WIDTH && newHeight1 >= MIN_HEIGHT) {
                    if (params.x + newWidth1 <= screenWidth) {
                        params.width = newWidth1;
                    }
                    if (params.y + newHeight1 <= screenHeight) {
                        params.height = newHeight1;
                    }
                }
                break;

            case 1: // 左下角 - 调整宽度（反向）和高度
                int newWidth2 = initialWidth - (int) deltaX;
                int newHeight2 = initialHeight + (int) deltaY;
                if (newWidth2 >= MIN_WIDTH && newHeight2 >= MIN_HEIGHT) {
                    int newX = initialTouchX + (int) deltaX;
                    if (newX >= 0 && params.y + newHeight2 <= screenHeight) {
                        params.x = newX;
                        params.width = newWidth2;
                        params.height = newHeight2;
                    }
                }
                break;

            case 2: // 左上角 - 调整宽度和高度（反向），同时移动位置
                int newWidth3 = initialWidth - (int) deltaX;
                int newHeight3 = initialHeight - (int) deltaY;
                if (newWidth3 >= MIN_WIDTH && newHeight3 >= MIN_HEIGHT) {
                    int newX = initialTouchX + (int) deltaX;
                    int newY = initialTouchY + (int) deltaY;
                    if (newX >= 0 && newY >= 0) {
                        params.x = newX;
                        params.y = newY;
                        params.width = newWidth3;
                        params.height = newHeight3;
                    }
                }
                break;

            case 3: // 右上角 - 调整宽度和高度（反向）
                int newWidth4 = initialWidth + (int) deltaX;
                int newHeight4 = initialHeight - (int) deltaY;
                if (newWidth4 >= MIN_WIDTH && newHeight4 >= MIN_HEIGHT) {
                    int newY = initialTouchY + (int) deltaY;
                    if (params.x + newWidth4 <= screenWidth && newY >= 0) {
                        params.y = newY;
                        params.width = newWidth4;
                        params.height = newHeight4;
                    }
                }
                break;

            case 4: // 右边缘 - 只调整宽度
                int newWidth5 = initialWidth + (int) deltaX;
                if (newWidth5 >= MIN_WIDTH && params.x + newWidth5 <= screenWidth) {
                    params.width = newWidth5;
                }
                break;

            case 5: // 左边缘 - 调整宽度（反向）和位置
                int newWidth6 = initialWidth - (int) deltaX;
                if (newWidth6 >= MIN_WIDTH) {
                    int newX = initialTouchX + (int) deltaX;
                    if (newX >= 0) {
                        params.x = newX;
                        params.width = newWidth6;
                    }
                }
                break;

            case 6: // 下边缘 - 只调整高度
                int newHeight7 = initialHeight + (int) deltaY;
                if (newHeight7 >= MIN_HEIGHT && params.y + newHeight7 <= screenHeight) {
                    params.height = newHeight7;
                }
                break;

            case 7: // 上边缘 - 调整高度（反向）和位置
                int newHeight8 = initialHeight - (int) deltaY;
                if (newHeight8 >= MIN_HEIGHT) {
                    int newY = initialTouchY + (int) deltaY;
                    if (newY >= 0) {
                        params.y = newY;
                        params.height = newHeight8;
                    }
                }
                break;
        }

        windowManager.updateViewLayout(this, params);
    }

    private int checkEdge(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        float width = getWidth();
        float height = getHeight();

        // 检查四个角
        boolean nearLeft = x < EDGE_THRESHOLD;
        boolean nearRight = x > width - EDGE_THRESHOLD;
        boolean nearTop = y < EDGE_THRESHOLD;
        boolean nearBottom = y > height - EDGE_THRESHOLD;

        // 四个角
        if (nearBottom && nearRight)
            return 0; // 右下角
        if (nearBottom && nearLeft)
            return 1; // 左下角
        if (nearTop && nearLeft)
            return 2; // 左上角
        if (nearTop && nearRight)
            return 3; // 右上角

        // 四条边
        if (nearRight)
            return 4; // 右边缘
        if (nearLeft)
            return 5; // 左边缘
        if (nearBottom)
            return 6; // 下边缘
        if (nearTop)
            return 7; // 上边缘

        return -1; // 不在边缘，可以拖动
    }

    private void saveState() {
        preferences.saveAll(params.x, params.y, params.width, params.height, getAlpha());
    }

    public void saveAlpha(float alpha) {
        setAlpha(alpha);
        preferences.saveAlpha(alpha);
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
        preferences.saveLocked(locked);
    }

    public boolean isLocked() {
        return isLocked;
    }

    public WindowManager.LayoutParams getParams() {
        return params;
    }
}
