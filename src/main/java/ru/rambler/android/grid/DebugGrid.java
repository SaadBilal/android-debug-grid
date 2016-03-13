package ru.rambler.android.grid;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;


public class DebugGrid {
    private final List<Line> lines;
    private final Application application;
    private final View gridView;
    private final Application.ActivityLifecycleCallbacks lifecycleCallbacks;


    private DebugGrid(Application application, List<Line> lines) {
        this.application = application;
        this.lines = new ArrayList<>(lines);
        this.gridView = inflateView(application);
        lifecycleCallbacks = new LifecycleCallbacks();
        application.registerActivityLifecycleCallbacks(lifecycleCallbacks);
    }

    public void hide() {
        gridView.setVisibility(View.GONE);
    }

    public void show() {
        gridView.setVisibility(View.VISIBLE);
    }

    private void addGridView(Activity foregroundActivity) {
        WindowManager windowManager = (WindowManager) foregroundActivity.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.RIGHT | Gravity.BOTTOM;
        windowManager.addView(gridView, params);
    }

    private void removeGridView(Activity foregroundActivity) {
        WindowManager windowManager = (WindowManager) foregroundActivity.getSystemService(Context.WINDOW_SERVICE);
        windowManager.removeView(gridView);
    }

    private View inflateView(Context context) {
        FrameLayout parent = new FrameLayout(context);
        for (Line line : lines) {
            View lineView = new View(context);
            lineView.setBackgroundColor(line.getColor());
            boolean left = line.getGravity() == Gravity.LEFT;
            boolean right = line.getGravity() == Gravity.RIGHT;
            boolean top = line.getGravity() == Gravity.TOP;
            boolean bottom = line.getGravity() == Gravity.BOTTOM;
            boolean verticalLine = left || right;
            FrameLayout.LayoutParams lineViewParams = new FrameLayout.LayoutParams(
                    verticalLine ? line.getWidth() : ViewGroup.LayoutParams.MATCH_PARENT,
                    verticalLine ? ViewGroup.LayoutParams.MATCH_PARENT : line.getWidth()
            );

            int marginInPx = (int) TypedValue.applyDimension(
                    line.getMarginUnit(),
                    line.getMargin(),
                    context.getResources().getDisplayMetrics());

            lineViewParams.setMargins(
                    left ? marginInPx : 0,
                    top ? marginInPx : 0,
                    right ? marginInPx : 0,
                    bottom ? marginInPx : 0
            );

            parent.addView(lineView, lineViewParams);
        }
        return parent;
    }

    public static class Builder {
        private final List<Line> lines = new ArrayList<>();

        public Builder with(Line line) {
            lines.add(line);
            return this;
        }

        public DebugGrid build(Application application) {
            return new DebugGrid(application, lines);
        }
    }

    private class LifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        }

        @Override
        public void onActivityStarted(Activity activity) {
            addGridView(activity);
        }

        @Override
        public void onActivityResumed(Activity activity) {
        }

        @Override
        public void onActivityPaused(Activity activity) {
        }

        @Override
        public void onActivityStopped(Activity activity) {
            removeGridView(activity);
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
        }
    }
}
