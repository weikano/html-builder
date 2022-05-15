package com.jrummyapps.android.util;

import android.support.annotation.NonNull;

/**
 * html生成器
 */
public class HtmlBuilder {
    /**
     * head
     */
    @NonNull
    private HtmlHeadBuilder headBuilder = new HtmlHeadBuilder();
    /**
     * body
     */
    @NonNull
    private HtmlBodyBuilder bodyBuilder = new HtmlBodyBuilder();

    @NonNull
    public HtmlHeadBuilder getHeadBuilder() {
        return headBuilder;
    }

    public void setHeadBuilder(@NonNull HtmlHeadBuilder headBuilder) {
        this.headBuilder = headBuilder;
    }

    @NonNull
    public HtmlBodyBuilder getBodyBuilder() {
        return bodyBuilder;
    }

    public void setBodyBuilder(@NonNull HtmlBodyBuilder bodyBuilder) {
        this.bodyBuilder = bodyBuilder;
    }

    private static final String DOCTYPE = "<!DOCTYPE html>";

    public StringBuilder getContent() {
        StringBuilder writer = new StringBuilder();
        writer.append(DOCTYPE).append(System.lineSeparator());
        writer.append("<html>").append(System.lineSeparator());
        writer.append("<head>").append(System.lineSeparator());
        writer.append(headBuilder.getContent()).append(System.lineSeparator());
        writer.append("</head>").append(System.lineSeparator());
        writer.append("<body>").append(System.lineSeparator());
        writer.append(bodyBuilder.getContent()).append(System.lineSeparator());
        writer.append("</body>").append(System.lineSeparator());
        writer.append("</html>").append(System.lineSeparator());
        return writer;
    }
}
