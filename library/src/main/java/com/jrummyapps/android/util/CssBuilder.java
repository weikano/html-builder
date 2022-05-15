package com.jrummyapps.android.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CssBuilder {
    private static final String OPEN_TAG = "{";
    private static final String CLOSE_TAG = "}";
    private static final String SEPARATOR = ":";
    private static final String END = ";";
    /**
     * 用于输出选择器内容
     */
    private final StringBuilder writer = new StringBuilder();
    /**
     * 选择器集合
     */
    private List<Selector> selectors = new ArrayList<>();

    /**
     * 创建一个id选择器
     * @param id
     * @return
     */
    @Nullable
    public Selector newIdSelector(@NonNull String id) {
        if(TextUtils.isEmpty(id)) {
            return null;
        }
        return new IdSelector(id);
    }

    /**
     * 创建一个class选择器
     * @param className
     * @param elemName
     * @return
     */
    @NonNull
    public Selector newClassSelector(@NonNull String className, @Nullable String elemName) {
        if(TextUtils.isEmpty(className)) {
            return null;
        }
        return new ClassSelector(className, elemName);
    }

    /**
     * 创建一个class选择器
     * @param className
     * @return
     */
    @NonNull
    public Selector newClassSelector(@NonNull String className) {
        if(TextUtils.isEmpty(className)) {
            return null;
        }
        return new ClassSelector(className);
    }

    public void addSelector(@NonNull Selector selector) {
        selectors.add(selector);
    }

    public StringBuilder getContent() {
        for (Selector selector : selectors) {
            selector.output(writer);
            writer.append(System.lineSeparator());
        }
        return writer;
    }

    /**
     * 样式
     */
    public static class Selector {
        /**
         * 样式集合
         */
        private static class Attributes extends HashMap<String, String> {
        }
        /**
         * selector name
         */
        @NonNull
        protected String name = "";
        @NonNull
        private Attributes attributes = new Attributes();

        void output(@NonNull StringBuilder writer) {
            if(!TextUtils.isEmpty(name)) {
                outputName(writer);
                writer.append(System.lineSeparator()).append(OPEN_TAG).append(System.lineSeparator());
                outputAttributes(writer);
                writer.append(CLOSE_TAG);
            }

        }

        void outputName(@NonNull StringBuilder writer) {
            writer.append(name);
        }
        protected void outputAttributes(@NonNull StringBuilder writer) {
            for (Map.Entry<String, String> entry : attributes.entrySet()) {
                writer.append(entry.getKey()).append(SEPARATOR).append(entry.getValue()).append(END).append(System.lineSeparator());
            }
        }

        Selector(@NonNull String name) {
            this.name = name;
        }

        public void addAttribute(@NonNull String name, @NonNull Object value) {
            if(TextUtils.isEmpty(name)) {
                return;
            }
            String v = String.valueOf(value);
            name = name.toLowerCase();
            if(attributes.containsKey(name)) {
                return;
            }
            attributes.put(name, v.toLowerCase());
        }

        public void removeAttribute(@NonNull String name) {
            if(TextUtils.isEmpty(name)) {
                return;
            }
            attributes.remove(name.toLowerCase());
        }

        public void replaceIfExists(@NonNull String name, @NonNull Object value) {
            if(TextUtils.isEmpty(name)) {
                return;
            }
            String v = String.valueOf(value);
            attributes.put(name, v.toLowerCase());
        }
    }

    /**
     * id选择器
     */
    private static class IdSelector extends Selector {
        IdSelector(@NonNull String id) {
            super(id);
        }

        @Override
        void outputName(@NonNull StringBuilder writer) {
            writer.append("#").append(name);
        }
    }

    /**
     * class 选择器
     */
    private static class ClassSelector extends Selector {
        ClassSelector(@NonNull String className) {
            this(className, null);
        }
        ClassSelector(@NonNull String className, @Nullable String elemName) {
            super((TextUtils.isEmpty(elemName) ? "" : elemName) +"." +className);
        }
    }

}
