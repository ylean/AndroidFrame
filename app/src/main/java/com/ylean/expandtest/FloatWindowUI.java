package com.ylean.expandtest;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.VideoView;


import com.ylean.expand.floatwindow.ActivityBind;
import com.ylean.expand.swipeback.base.SwipeBackUI;

/**
 * ================================================
 * 作    者：maojunxian
 * 版    本：1.0
 * 创建日期：2017/3/27
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class FloatWindowUI extends SwipeBackUI {
    public static int OVERLAY_PERMISSION_REQ_CODE = 1234;

    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        ActivityBind.getInstance().dismissFloat(FloatWindowUI.this);


        VideoView vv = (VideoView) findViewById(R.id.vv);
        vv.setVideoURI(Uri.parse("http://video.jiecao.fm/8/17/bGQS3BQQWUYrlzP1K4Tg4Q__.mp4"));
        vv.start();
            WebView video_webview = (WebView) findViewById(R.id.video_webview);
        video_webview.getSettings().setBuiltInZoomControls(true);// 隐藏缩放按钮
        video_webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);// 排版适应屏幕
        video_webview.getSettings().setUseWideViewPort(true);// 可任意比例缩放
        video_webview.getSettings().setLoadWithOverviewMode(true);// setUseWideViewPort方法设置webview推荐使用的窗口。setLoadWithOverviewMode方法是设置webview加载的页面的模式。
        video_webview.getSettings().setSavePassword(true);
        video_webview.getSettings().setSaveFormData(true);// 保存表单数据
        video_webview.getSettings().setJavaScriptEnabled(true);
        video_webview.getSettings().setGeolocationEnabled(true);// 启用地理定位
        video_webview.getSettings().setGeolocationDatabasePath("/data/data/org.itri.html5webview/databases/");// 设置定位的数据库路径
        video_webview.getSettings().setDomStorageEnabled(true);
        video_webview.loadUrl("file:///android_asset/test.html");


    }

    @Override
    protected void onResume() {
        super.onResume();
        ActivityBind.getInstance().onResume(FloatWindowUI.this);
    }


    @Override
    protected void onStop() {
        super.onStop();
        ActivityBind.getInstance().onStop(FloatWindowUI.this);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                askForPermission();
            } else {
                ActivityBind.getInstance().showFloat(FloatWindowUI.this, "http://video.jiecao.fm/8/17/bGQS3BQQWUYrlzP1K4Tg4Q__.mp4");
                finish();
            }
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_MENU) {
            ActivityBind.getInstance().funPress(FloatWindowUI.this);
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    /**
     * 请求用户给予悬浮窗的权限
     */

    @TargetApi(Build.VERSION_CODES.M)
    public void askForPermission() {
        if (!Settings.canDrawOverlays(this)) {
            Toast.makeText(FloatWindowUI.this, "当前无权限，请授权！", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
        } else {
            ActivityBind.getInstance().showFloat(FloatWindowUI.this, "http://video.jiecao.fm/8/17/bGQS3BQQWUYrlzP1K4Tg4Q__.mp4");
            finish();

        }
    }

    /**
     * 用户返回
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
            if (!Settings.canDrawOverlays(this)) {
                Toast.makeText(FloatWindowUI.this, "权限授予失败，无法开启悬浮窗", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(FloatWindowUI.this, "权限授予成功！", Toast.LENGTH_SHORT).show();
                //启动FxService
                ActivityBind.getInstance().showFloat(FloatWindowUI.this, "http://video.jiecao.fm/8/17/bGQS3BQQWUYrlzP1K4Tg4Q__.mp4");

            }
            finish();

        }
    }


}
