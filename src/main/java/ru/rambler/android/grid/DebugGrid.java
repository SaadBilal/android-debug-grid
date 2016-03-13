package ru.rambler.android.grid;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

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
    }

    public void hide() {

    }

    public void show() {

    }

    private View inflateView(Context context) {
        return null;
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

    private static class LifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }
    }
}
