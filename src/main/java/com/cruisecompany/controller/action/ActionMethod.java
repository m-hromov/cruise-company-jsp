package com.cruisecompany.controller.action;

public class ActionMethod {
    private String path;
    private Method method;

    public ActionMethod(String path, Method method) {
        this.path = path;
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public Method getMethod() {
        return method;
    }
}
