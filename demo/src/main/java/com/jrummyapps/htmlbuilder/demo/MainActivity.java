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

package com.jrummyapps.htmlbuilder.demo;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.jrummyapps.android.util.CssBuilder;
import com.jrummyapps.android.util.HtmlBodyBuilder;
import com.jrummyapps.android.util.HtmlBuilder;
import com.jrummyapps.android.util.HtmlHeadBuilder;

public class MainActivity extends AppCompatActivity {

  private HtmlBodyBuilder bodyBuilder = new HtmlBodyBuilder();
  private HtmlHeadBuilder headBuilder = new HtmlHeadBuilder();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    TextView textView = (TextView) findViewById(R.id.textView);
    textView.setMovementMethod(LinkMovementMethod.getInstance());
    textView.setText(buildDemoHtml());

    TextView css = (TextView) findViewById(R.id.css);
    css.setText(buildCss());

    TextView html = (TextView) findViewById(R.id.html);
    headBuilder.addCssLink("zybooknote.css");
    headBuilder.setTitle("zybooknote");
    headBuilder.addMeta("keywords", "bookname, note");
    headBuilder.addMeta("author", "szzy");

    HtmlBuilder builder = new HtmlBuilder();
    builder.setBodyBuilder(bodyBuilder);
    builder.setHeadBuilder(headBuilder);
    html.setText(builder.getContent());
  }

  private String buildCss() {
    CssBuilder builder = new CssBuilder();
    CssBuilder.Selector para1 = builder.newIdSelector("para1");
    para1.addAttribute("text-align", "center");
    para1.addAttribute("color", "red");
    builder.addSelector(para1);

    CssBuilder.Selector center = builder.newClassSelector("center");
    center.addAttribute("text-align", "center");
    builder.addSelector(center);

    CssBuilder.Selector pcenter = builder.newClassSelector("center", "p");
    pcenter.addAttribute("text-align", "center");
    builder.addSelector(pcenter);

    return builder.getContent().toString();
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    menu.add(0, Menu.FIRST, 0, "GitHub").setIcon(R.drawable.ic_github).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
    return super.onCreateOptionsMenu(menu);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case Menu.FIRST:
        try {
          startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/jaredrummler/html-builder")));
        } catch (ActivityNotFoundException ignored) {
        }
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }



  private Spanned buildDemoHtml() {
    bodyBuilder = new HtmlBodyBuilder();
    bodyBuilder.h1("Example Usage");

    bodyBuilder.h3().font("cursive", "Code:").close();
    bodyBuilder.font(0xFFCAE682, "HtmlBuilder")
        .append(' ')
        .font(0xFFD4C4A9, "html")
        .append(' ')
        .font(0xFF888888, "=")
        .append(" ")
        .font(0xFF33B5E5, "new")
        .append(" ")
        .font(0xFFCAE682, "HtmlBuilder")
        .append("()")
        .br();
    bodyBuilder.font(0xFFD4C4A9, "html")
        .append(".strong(")
        .font(0xFF95E454, "\"Strong text\"")
        .append(").br();")
        .br();
    bodyBuilder.font(0xFFD4C4A9, "html")
        .append(".font(")
        .font(0xFFCAE682, "Color")
        .append('.')
        .font(0xFF53DCCD, "RED")
        .append(", ")
        .font(0xFF95E454, "\"This will be red text\"")
        .append(");")
        .br();
    bodyBuilder.font(0xFFCAE682, "textView")
        .append(".setText(")
        .font(0xFFD4C4A9, "html")
        .append(".build());")
        .close()
        .br();

    bodyBuilder.h3().font("cursive", "Result:").close();
    bodyBuilder.strong("Strong text").br().font(Color.RED, "This will be red text");

    bodyBuilder.h1("Supported Tags");
    bodyBuilder.append("&lt;a href=&quot;...&quot;&gt;").br();
    bodyBuilder.append("&lt;b&gt;").br();
    bodyBuilder.append("&lt;big&gt;").br();
    bodyBuilder.append("&lt;blockquote&gt;").br();
    bodyBuilder.append("&lt;br&gt;").br();
    bodyBuilder.append("&lt;cite&gt;").br();
    bodyBuilder.append("&lt;dfn&gt;").br();
    bodyBuilder.append("&lt;div align=&quot;...&quot;&gt;").br();
    bodyBuilder.append("&lt;em&gt;").br();
    bodyBuilder.append("&lt;font color=&quot;...&quot; face=&quot;...&quot;&gt;").br();
    bodyBuilder.append("&lt;h1&gt;").br();
    bodyBuilder.append("&lt;h2&gt;").br();
    bodyBuilder.append("&lt;h3&gt;").br();
    bodyBuilder.append("&lt;h4&gt;").br();
    bodyBuilder.append("&lt;h5&gt;").br();
    bodyBuilder.append("&lt;h6&gt;").br();
    bodyBuilder.append("&lt;i&gt;").br();
    bodyBuilder.append("&lt;img src=&quot;...&quot;&gt;").br();
    bodyBuilder.append("&lt;p&gt;").br();
    bodyBuilder.append("&lt;small&gt;").br();
    bodyBuilder.append("&lt;strike&gt;").br();
    bodyBuilder.append("&lt;strong&gt;").br();
    bodyBuilder.append("&lt;sub&gt;").br();
    bodyBuilder.append("&lt;sup&gt;").br();
    bodyBuilder.append("&lt;tt&gt;").br();
    bodyBuilder.append("&lt;u&gt;").br();
    bodyBuilder.append("&ul;u&gt;").br();
    bodyBuilder.append("&li;u&gt;").br();

    bodyBuilder.h1("Links");
    bodyBuilder.p()
        .strong().a("https://twitter.com/jrummy16", "Twitter").close()
        .append("&nbsp;&nbsp;|&nbsp;&nbsp;")
        .strong().a("https://github.com/jaredrummler", "GitHub").close()
        .close();

    return bodyBuilder.build();
  }

}
