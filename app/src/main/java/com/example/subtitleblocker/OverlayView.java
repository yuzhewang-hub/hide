package com.example.subtitleblocker;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

public class OverlayView extends FrameLayout {
    private WindowManager.LayoutParams params;
    private WindowManager windowManager;
    private float initialX, initialY;
    private int initialTouchX, initialTouchY;
    private boolean isResizing = false;
    private int resizeEdge = -1;
    private static final int DRAG_THRESHOLD = 10;

    public OverlayView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        
        // 创建一个黑色背景的视图
        View backgroundView = new View(context);
        backgroundView.setBackgroundColor(Color.BLACK);
        addView(backgroundView, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
        ));

        // 设置窗口参数
        params = new WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                | WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
            PixelFormat.TRANSLUCENT
        );

        params.width = 300;
        params.height = 100;
        params.gravity = Gravity.TOP | Gravity.START;
        params.x = 200;
        params.y = 500;

        setupTouchListener();
    }

    private void setupTouchListener() {
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initialX = event.getRawX();
                        initialY = event.getRawY();
                        initialTouchX = params.x;
                        initialTouchY = params.y;
                        
                        // 检查是否在边缘（用于调整大小）
                        float edge = checkEdge(event);
                        if (edge > 0) {
                            isResizing = true;
                            resizeEdge = (int) edge;
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
            params.x = initialTouchX + dx;
            params.y = initialTouchY + dy;
            
            // 限制在屏幕范围内
            params.x = Math.max(0, Math.min(params.x, windowManager.getDefaultDisplay().getWidth() - params.width));
            params.y = Math.max(0, Math.min(params.y, windowManager.getDefaultDisplay().getHeight() - params.height));
            
            windowManager.updateViewLayout(this, params);
        }
    }

    private void handleResize(MotionEvent event) {
        float x = event.getRawX();
        float y = event.getRawY();
        
        switch (resizeEdge) {
            case 0: // 右下角
                int newWidth = (int) (x - params.x);
                int newHeight = (int) (y - params.y);
                if (newWidth > 100 && newHeight > 50) {
                    params.width = newWidth;
                    params.height = newHeight;
                }
                break;
            // 可以添加其他边缘的调整逻辑
        }
        
        windowManager.updateViewLayout(this, params);
    }

    private float checkEdge(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        float touchThreshold = 50; // 边缘触摸阈值
        
        // 检查右下角
        if (x > getWidth() - touchThreshold && y > getHeight() - touchThreshold) {
            return 0;
        }
        
        return -1;
    }

    public WindowManager.LayoutParams getParams() {
        return params;
    }
}

