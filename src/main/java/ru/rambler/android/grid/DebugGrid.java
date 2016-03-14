/*
 * Copyright 2016 Rambler&Co
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
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
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

/**
 * Debug grid drawing above android activities
 */
public class DebugGrid {
    private final List<Line> lines;
    private final Application application;
    private final Map<Activity, View> gridViews = new IdentityHashMap<>();
    private final LifecycleCallbacks lifecycleCallbacks;
    private boolean visible = true;


    private DebugGrid(Application application, List<Line> lines) {
        this.application = application;
        this.lines = new ArrayList<>(lines);
        inflateView(application);
        lifecycleCallbacks = new LifecycleCallbacks();
        application.registerActivityLifecycleCallbacks(lifecycleCallbacks);
    }

    /**
     * Hide debug grid
     */
    public void hide() {
        visible = false;
        if (lifecycleCallbacks.lastForegroundActivity != null) {
            removeGridView(lifecycleCallbacks.lastForegroundActivity);
        }
    }

    /**
     * Show debug grid
     */
    public void show() {
        visible = true;
        if (lifecycleCallbacks.lastForegroundActivity != null) {
            addGridView(lifecycleCallbacks.lastForegroundActivity);
        }
    }

    private void addGridView(Activity foregroundActivity) {
        if (visible) {
            WindowManager windowManager = (WindowManager) foregroundActivity.getSystemService(Context.WINDOW_SERVICE);
            WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.TYPE_APPLICATION,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                            | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                            | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                    PixelFormat.TRANSLUCENT);
            View gridView = inflateView(foregroundActivity);
            gridViews.put(foregroundActivity, gridView);
            windowManager.addView(gridView, params);
        }
    }

    private void removeGridView(Activity foregroundActivity) {
        WindowManager windowManager = (WindowManager) foregroundActivity.getSystemService(Context.WINDOW_SERVICE);
        if (gridViews.containsKey(foregroundActivity)) {
            windowManager.removeView(gridViews.get(foregroundActivity));
            gridViews.remove(foregroundActivity);
        }
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
            lineViewParams.gravity = line.getGravity();

            parent.addView(lineView, lineViewParams);
        }
        return parent;
    }

    /**
     * Builder class for creating debug grid
     */
    public static class Builder {
        private final List<Line> lines = new ArrayList<>();

        /**
         * Add new line to creating grid
         *
         * @param line new line
         * @return this instance for fluent interface
         */
        public Builder with(Line line) {
            lines.add(line);
            return this;
        }

        /**
         * Builds and show grid over activities of specified application
         *
         * @param application application instance
         * @return Builded debug grid
         */
        public DebugGrid build(Application application) {
            return new DebugGrid(application, lines);
        }
    }

    private class LifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
        private Activity lastForegroundActivity;


        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        }

        @Override
        public void onActivityStarted(Activity activity) {
            addGridView(activity);
            lastForegroundActivity = activity;
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
            if (lastForegroundActivity == activity) {
                lastForegroundActivity = null;
            }
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
        }
    }
}
