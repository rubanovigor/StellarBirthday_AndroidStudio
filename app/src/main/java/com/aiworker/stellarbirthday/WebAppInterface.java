package com.aiworker.stellarbirthday;

import android.content.Context;
import android.webkit.JavascriptInterface;

/** passing a class instance to bind to your JavaScript and an interface name that your JavaScript can call to access the class.
 * see more at http://developer.android.com/guide/webapps/webview.html */
public class WebAppInterface {
    Context mContext;
    static String StarName;

    /** Instantiate the interface and set the context */
    WebAppInterface(Context c) {
        mContext = c;
    }
    /** Get the value 
     * @return */
    @JavascriptInterface
    static void setValue(String star) {
        StarName = star;
    }

    /** Get the value */
    @JavascriptInterface
    public String getValue() {
        return StarName;
    }
}
