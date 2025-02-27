/*
 * Copyright (C) 2016 JRummy Apps Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * Raw
 */

package com.jrummyapps.android.util;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Html;
import android.text.Spanned;
import android.widget.TextView;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * <p>Build a valid HTML string for a {@link TextView}.</p>
 *
 * <p>Example usage:</p>
 *
 * <pre>
 * <code>
 * HtmlBuilder html = new HtmlBuilder()
 * .p()
 * .b()
 * .font().color("red").face("sans-serif-condensed").text("Hello").close()
 * .close()
 * .close();
 *
 * // html.toString():
 * // &lt;p&gt;&lt;b&gt;&lt;font color=&quot;red&quot; face=&quot;sans-serif-condensed&quot;&gt;Hello&lt;/font&gt;&lt;/b&gt;&lt;/p&gt;
 *
 * yourEditText.setText(html.toSpan());
 * </code>
 * </pre>
 *
 * <p>HTML Tags Supported by {@link TextView}:</p>
 *
 * <ul>
 * <li><code>&lt;a href=&quot;...&quot;&gt;</code></li>
 * <li><code>&lt;b&gt;</code></li>
 * <li><code>&lt;big&gt;</code></li>
 * <li><code>&lt;blockquote&gt;</code></li>
 * <li><code>&lt;br&gt;</code></li>
 * <li><code>&lt;cite&gt;</code></li>
 * <li><code>&lt;dfn&gt;</code></li>
 * <li><code>&lt;div align=&quot;...&quot;&gt;</code></li>
 * <li><code>&lt;em&gt;</code></li>
 * <li><code>&lt;font color=&quot;...&quot; face=&quot;...&quot;&gt;</code></li>
 * <li><code>&lt;h1&gt;</code></li>
 * <li><code>&lt;h2&gt;</code></li>
 * <li><code>&lt;h3&gt;</code></li>
 * <li><code>&lt;h4&gt;</code></li>
 * <li><code>&lt;h5&gt;</code></li>
 * <li><code>&lt;h6&gt;</code></li>
 * <li><code>&lt;i&gt;</code></li>
 * <li><code>&lt;img src=&quot;...&quot;&gt;</code></li>
 * <li><code>&lt;p&gt;</code></li>
 * <li><code>&lt;small&gt;</code></li>
 * <li><code>&lt;strike&gt;</code></li>
 * <li><code>&lt;strong&gt;</code></li>
 * <li><code>&lt;sub&gt;</code></li>
 * <li><code>&lt;sup&gt;</code></li>
 * <li><code>&lt;tt&gt;</code></li>
 * <li><code>&lt;u&gt;</code></li>
 * <li><code>&lt;ul&gt;</code> (Android 7.0+)</li>
 * <li><code>&lt;li&gt;</code> (Android 7.0+)</li>
 * </ul>
 */
public class HtmlBodyBuilder {

  private final StringBuilder html = new StringBuilder();

  private final LinkedList<String> tags = new LinkedList<>();

  public HtmlBodyBuilder open(String element, String data) {
    tags.add(element);
    html.append('<');
    html.append(element);
    if (data != null) {
      html.append(' ').append(data);
    }
    html.append('>');
    return this;
  }

  public HtmlBodyBuilder open(String element) {
    return open(element, null);
  }

  public HtmlBodyBuilder close(String element) {
    html.append("</").append(element).append('>');
    for (Iterator<String> iterator = tags.iterator(); iterator.hasNext(); ) {
      if (iterator.next().equals(element)) {
        iterator.remove();
        break;
      }
    }
    return this;
  }

  public HtmlBodyBuilder close() {
    if (tags.isEmpty()) {
      return this;
    }
    html.append("</").append(tags.removeLast()).append('>');
    return this;
  }

  public HtmlBodyBuilder close(char element) {
    return close(String.valueOf(element));
  }

  public HtmlBodyBuilder append(boolean b) {
    html.append(b);
    return this;
  }

  public HtmlBodyBuilder append(char c) {
    html.append(c);
    return this;
  }

  public HtmlBodyBuilder append(int i) {
    html.append(i);
    return this;
  }

  public HtmlBodyBuilder append(long l) {
    html.append(l);
    return this;
  }

  public HtmlBodyBuilder append(float f) {
    html.append(f);
    return this;
  }

  public HtmlBodyBuilder append(double d) {
    html.append(d);
    return this;
  }

  public HtmlBodyBuilder append(Object obj) {
    html.append(obj);
    return this;
  }

  public HtmlBodyBuilder append(String str) {
    html.append(str);
    return this;
  }

  public HtmlBodyBuilder append(StringBuffer sb) {
    html.append(sb);
    return this;
  }

  public HtmlBodyBuilder append(char[] chars) {
    html.append(chars);
    return this;
  }

  public HtmlBodyBuilder append(char[] str, int offset, int len) {
    html.append(str, offset, len);
    return this;
  }

  public HtmlBodyBuilder append(CharSequence csq) {
    html.append(csq);
    return this;
  }

  public HtmlBodyBuilder append(CharSequence csq, int start, int end) {
    html.append(csq, start, end);
    return this;
  }

  public HtmlBodyBuilder append(Tag tag) {
    html.append(tag.toString());
    return this;
  }

  public HtmlBodyBuilder a(String href, String text) {
    return append(String.format("<a href=\"%s\">%s</a>", href, text));
  }

  public HtmlBodyBuilder b() {
    return open("b");
  }

  public HtmlBodyBuilder b(String text) {
    html.append("<b>").append(text).append("</b>");
    return this;
  }

  public HtmlBodyBuilder big() {
    return open("big");
  }

  public HtmlBodyBuilder big(String text) {
    html.append("<big>").append(text).append("</big>");
    return this;
  }

  public HtmlBodyBuilder blockquote() {
    return open("blockquote");
  }

  public HtmlBodyBuilder blockquote(String text) {
    html.append("<blockquote>").append(text).append("</blockquote>");
    return this;
  }

  public HtmlBodyBuilder br() {
    html.append("<br>");
    return this;
  }

  public HtmlBodyBuilder cite() {
    return open("cite");
  }

  public HtmlBodyBuilder cite(String text) {
    html.append("<cite>").append(text).append("</cite>");
    return this;
  }

  public HtmlBodyBuilder dfn() {
    return open("dfn");
  }

  public HtmlBodyBuilder dfn(String text) {
    html.append("<dfn>").append(text).append("</dfn>");
    return this;
  }

  public HtmlBodyBuilder div() {
    return open("div");
  }

  public HtmlBodyBuilder div(String align) {
    html.append(String.format("<div align=\"%s\">", align));
    tags.add("div");
    return this;
  }

  public HtmlBodyBuilder em() {
    return open("em");
  }

  public HtmlBodyBuilder em(String text) {
    html.append("<em>").append(text).append("</em>");
    return this;
  }

  public Font font() {
    return new Font(this);
  }

  public HtmlBodyBuilder font(int color, String text) {
    return font().color(color).text(text).close();
  }

  public HtmlBodyBuilder font(String face, String text) {
    return font().face(face).text(text).close();
  }

  public HtmlBodyBuilder h1() {
    return open("h1");
  }

  public HtmlBodyBuilder h1(String text) {
    html.append("<h1>").append(text).append("</h1>");
    return this;
  }

  public HtmlBodyBuilder h2() {
    return open("h2");
  }

  public HtmlBodyBuilder h2(String text) {
    html.append("<h2>").append(text).append("</h2>");
    return this;
  }

  public HtmlBodyBuilder h3() {
    return open("h3");
  }

  public HtmlBodyBuilder h3(String text) {
    html.append("<h3>").append(text).append("</h3>");
    return this;
  }

  public HtmlBodyBuilder h4() {
    return open("h4");
  }

  public HtmlBodyBuilder h4(String text) {
    html.append("<h4>").append(text).append("</h4>");
    return this;
  }

  public HtmlBodyBuilder h5() {
    return open("h5");
  }

  public HtmlBodyBuilder h5(String text) {
    html.append("<h5>").append(text).append("</h5>");
    return this;
  }

  public HtmlBodyBuilder h6() {
    return open("h6");
  }

  public HtmlBodyBuilder h6(String text) {
    html.append("<h6>").append(text).append("</h6>");
    return this;
  }

  public HtmlBodyBuilder i() {
    return open("i");
  }

  public HtmlBodyBuilder i(String text) {
    html.append("<i>").append(text).append("</i>");
    return this;
  }

  public Img img() {
    return new Img(this);
  }

  public HtmlBodyBuilder img(String src) {
    return img().src(src).close();
  }

  public HtmlBodyBuilder p() {
    return open("p");
  }

  public HtmlBodyBuilder p(String text) {
    html.append("<p>").append(text).append("</p>");
    return this;
  }

  public HtmlBodyBuilder small() {
    return open("small");
  }

  public HtmlBodyBuilder small(String text) {
    html.append("<small>").append(text).append("</small>");
    return this;
  }

  public HtmlBodyBuilder strike() {
    return open("strike");
  }

  public HtmlBodyBuilder strike(String text) {
    html.append("<strike>").append(text).append("</strike>");
    return this;
  }

  public HtmlBodyBuilder strong() {
    return open("strong");
  }

  public HtmlBodyBuilder strong(String text) {
    html.append("<strong>").append(text).append("</strong>");
    return this;
  }

  public HtmlBodyBuilder sub() {
    return open("sub");
  }

  public HtmlBodyBuilder sub(String text) {
    html.append("<sub>").append(text).append("</sub>");
    return this;
  }

  public HtmlBodyBuilder sup() {
    return open("sup");
  }

  public HtmlBodyBuilder sup(String text) {
    html.append("<sup>").append(text).append("</sup>");
    return this;
  }

  public HtmlBodyBuilder tt() {
    return open("tt");
  }

  public HtmlBodyBuilder tt(String text) {
    html.append("<tt>").append(text).append("</tt>");
    return this;
  }

  public HtmlBodyBuilder u() {
    return open("u");
  }

  public HtmlBodyBuilder u(String text) {
    html.append("<u>").append(text).append("</u>");
    return this;
  }

  @RequiresApi(Build.VERSION_CODES.N)
  public HtmlBodyBuilder ul() {
    return open("ul");
  }

  @RequiresApi(Build.VERSION_CODES.N)
  public HtmlBodyBuilder li() {
    return open("li");
  }

  @RequiresApi(Build.VERSION_CODES.N)
  public HtmlBodyBuilder li(String text) {
    html.append("<li>").append(text).append("</li>");
    return this;
  }

  @RequiresApi(Build.VERSION_CODES.N)
  public Spanned build(int flags) {
    return Html.fromHtml(html.toString(), flags);
  }

  public StringBuilder getContent() {
    return html;
  }

  public Spanned build() {
    //noinspection deprecation
    return Html.fromHtml(html.toString());
  }

  @Override public String toString() {
    return html.toString();
  }

  public static class Tag {

    final HtmlBodyBuilder builder;
    final String element;
    String separator = "";

    public Tag(HtmlBodyBuilder builder, String element) {
      this.builder = builder;
      this.element = element;
      open();
    }

    protected void open() {
      builder.append('<').append(element).append(' ');
    }

    public HtmlBodyBuilder close() {
      return builder.append("</").append(element).append('>');
    }

    @Override public String toString() {
      return builder.toString();
    }

  }

  public static class Font extends Tag {

    public Font() {
      this(new HtmlBodyBuilder());
    }

    public Font(HtmlBodyBuilder builder) {
      super(builder, "font");
    }

    public Font size(int size) {
      builder.append(separator).append("size=\"").append(size).append('\"');
      separator = " ";
      return this;
    }

    public Font size(String size) {
      builder.append(separator).append("size=\"").append(size).append('\"');
      separator = " ";
      return this;
    }

    public Font color(int color) {
      return color(String.format("#%06X", (0xFFFFFF & color)));
    }

    public Font color(String color) {
      builder.append(separator).append("color=\"").append(color).append('\"');
      separator = " ";
      return this;
    }

    public Font face(String face) {
      builder.append(separator).append("face=\"").append(face).append('\"');
      separator = " ";
      return this;
    }

    public Font text(String text) {
      builder.append('>').append(text);
      return this;
    }

  }

  public static class Img extends Tag {

    public Img() {
      this(new HtmlBodyBuilder());
    }

    public Img(HtmlBodyBuilder builder) {
      super(builder, "img");
    }

    public Img src(String src) {
      builder.append(separator).append("src=\"").append(src).append('\"');
      separator = " ";
      return this;
    }

    public Img alt(String alt) {
      builder.append(separator).append("alt=\"").append(alt).append('\"');
      separator = " ";
      return this;
    }

    public Img height(String height) {
      builder.append(separator).append("height=\"").append(height).append('\"');
      separator = " ";
      return this;
    }

    public Img height(int height) {
      builder.append(separator).append("height=\"").append(height).append('\"');
      separator = " ";
      return this;
    }

    public Img width(String width) {
      builder.append(separator).append("width=\"").append(width).append('\"');
      separator = " ";
      return this;
    }

    public Img width(int width) {
      builder.append(separator).append("width=\"").append(width).append('\"');
      separator = " ";
      return this;
    }

    @Override public HtmlBodyBuilder close() {
      return builder.append('>');
    }

  }

}
