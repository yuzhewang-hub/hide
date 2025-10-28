package com.example.subtitleblocker;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.AppCompatSeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_OVERLAY_PERMISSION = 100;
    private Button btnToggle;
    private SwitchCompat switchOverlay;
    private AppCompatSeekBar seekBarAlpha;
    private TextView tvAlpha;
    private boolean overlayActive = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnToggle = findViewById(R.id.btnToggle);
        switchOverlay = findViewById(R.id.switchOverlay);
        seekBarAlpha = findViewById(R.id.seekBarAlpha);
        tvAlpha = findViewById(R.id.tvAlpha);

        // 设置透明度滑动条
        seekBarAlpha.setOnSeekBarChangeListener(new AppCompatSeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(AppCompatSeekBar seekBar, int progress, boolean fromUser) {
                tvAlpha.setText("透明度: " + progress + "%");
            }

            @Override
            public void onStartTrackingTouch(AppCompatSeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(AppCompatSeekBar seekBar) {
                if (overlayActive && OverlayService.serviceInstance != null) {
                    OverlayView overlayView = OverlayService.serviceInstance.getOverlayView();
                    if (overlayView != null) {
                        float alpha = seekBar.getProgress() / 100f;
                        overlayView.setAlpha(alpha);
                    }
                }
            }
        });

        // 切换悬浮窗
        switchOverlay.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                startOverlay();
            } else {
                stopOverlay();
            }
        });

        // 检查悬浮窗权限
        checkOverlayPermission();

        // 设置按钮点击
        btnToggle.setOnClickListener(v -> {
            if (switchOverlay.isChecked()) {
                switchOverlay.setChecked(false);
            } else {
                switchOverlay.setChecked(true);
            }
        });
    }

    private void checkOverlayPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, REQUEST_OVERLAY_PERMISSION);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_OVERLAY_PERMISSION) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (Settings.canDrawOverlays(this)) {
                    Toast.makeText(this, "悬浮窗权限已授予", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "需要悬浮窗权限才能使用此功能", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void startOverlay() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            Toast.makeText(this, "请先授予悬浮窗权限", Toast.LENGTH_SHORT).show();
            checkOverlayPermission();
            switchOverlay.setChecked(false);
            return;
        }

        Intent serviceIntent = new Intent(this, OverlayService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(serviceIntent);
        } else {
            startService(serviceIntent);
        }
        overlayActive = true;
        Toast.makeText(this, "遮挡块已启动", Toast.LENGTH_SHORT).show();
    }

    private void stopOverlay() {
        Intent serviceIntent = new Intent(this, OverlayService.class);
        stopService(serviceIntent);
        overlayActive = false;
        Toast.makeText(this, "遮挡块已关闭", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (overlayActive) {
            stopOverlay();
        }
    }
}

