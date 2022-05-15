package com.jrummyapps.android.util;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * html头部head
 */
public class HtmlHeadBuilder {
    @NonNull
    private final StringBuilder writer = new StringBuilder();
    @NonNull
    private final List<HeadElement> elements = new ArrayList<>();
    private Title title = new Title("");
    public void addCssLink(@NonNull String href) {
        if(TextUtils.isEmpty(href)) {
            return;
        }
        elements.add(new CssStyle(href));
    }

    public void addLink(@NonNull String rel, @NonNull String type, @NonNull String href) {
        if(TextUtils.isEmpty(rel) || TextUtils.isEmpty(type) || TextUtils.isEmpty(href)) {
            return;
        }
        elements.add(new Link(rel, type, href));
    }

    public void setTitle(@NonNull String title) {
        this.title.setTitle(title);
    }

    public void addMeta(@NonNull String name, @NonNull String content) {
        elements.add(new Meta(name, content));
    }

    public StringBuilder getContent() {
        writer.append("<meta charset=\"utf-8\">").append(System.lineSeparator());
        title.output(writer);
        for (HeadElement e : elements) {
            e.output(writer);
        }
        return writer;
    }



    private static abstract class HeadElement {
        abstract void output(@NonNull StringBuilder writer);
    }

    private static class Title extends HeadElement {
        @NonNull
        private String title;

        public Title(@NonNull String title) {
            this.title = title;
        }

        public void setTitle(@NonNull String title) {
            this.title = title;
        }

        @Override
        void output(@NonNull StringBuilder writer) {
            writer.append("<title>").append(title).append("</title>").append(System.lineSeparator());
        }
    }

    private static class Link extends HeadElement {
        private static final String FORMAT = "<link rel=\"%s\" type=\"%s\" href=\"%s\">";
        @NonNull
        private String rel;
        @NonNull
        private String type;
        @NonNull
        private String href;

        public Link(@NonNull String rel, @NonNull String type, @NonNull String href) {
            this.rel = rel.toLowerCase();
            this.type = type.toLowerCase();
            this.href = href;
        }

        @Override
        void output(@NonNull StringBuilder writer) {
            writer.append(String.format(FORMAT, rel, type, href)).append(System.lineSeparator());
        }
    }

    private static class CssStyle extends Link {
        public CssStyle(@NonNull String href) {
            super("stylesheet", "text/css", href);
        }
    }

    private static class Meta extends HeadElement {

        @NonNull
        private String name;
        @NonNull
        private String content;

        public Meta(@NonNull String name, @NonNull String content) {
            this.name = name;
            this.content = content;
        }

        private static final String FORMAT = "<meta name=\"%s\" content=\"%s\">";

        @Override
        void output(@NonNull StringBuilder writer) {
            writer.append(String.format(FORMAT, name, content)).append(System.lineSeparator());
        }
    }
}
