package com.llh.moneykeep.common;

/**
 * 上下文容器
 **/
public class ContextHolder {
    public static ThreadLocal<ContextObject> context = new ThreadLocal<>();

    public static ContextObject getContext() {
        return context.get();
    }

    public static void setContext(ContextObject object) {
        context.set(object);
    }

    public static void removeContext() {
        context.remove();
    }
}
