package com.xll.needer.entity;

import android.app.usage.UsageStats;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.support.annotation.NonNull;

/**
 * Created by Administrator on 2018/1/1.
 */

public class AppUsageInfo implements Comparable{
    private UsageStats usageStats;
    private ResolveInfo resolveInfo;

    public AppUsageInfo(UsageStats usageStats, ResolveInfo resolveInfo) {
        this.resolveInfo = resolveInfo;
        this.usageStats = usageStats;
    }

    public UsageStats getUsageStats() {
        return usageStats;
    }

    public void setUsageStats(UsageStats usageStats) {
        this.usageStats = usageStats;
    }

    public ResolveInfo getResolveInfo() {
        return resolveInfo;
    }

    public void setResolveInfo(ResolveInfo resolveInfo) {
        this.resolveInfo = resolveInfo;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        if (o instanceof AppUsageInfo) {
            if (usageStats != null && ((AppUsageInfo) o).getUsageStats() != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    return (int) (((AppUsageInfo) o).getUsageStats().getTotalTimeInForeground()
                            - usageStats.getTotalTimeInForeground());
                }
            }
        }
        return 0;
    }
}