package ba.klika.unfpa;

import android.content.Context;
import android.os.Build;
import android.webkit.WebSettings;

/**
 * Created by ensarsarajcic on 12/10/17.
 */

public final class PWASettingsFactory {

    private PWASettingsFactory() {}

    public static void addDefaultSettings(Context context, WebSettings webSettings) {
        setDefaultSettings(context, webSettings, true);
    }

    public static void addNoJsOptions(Context context, WebSettings webSettings) {
        setDefaultSettings(context, webSettings, false);
    }

    private static void setDefaultSettings(Context context,
                                                       WebSettings webSettings,
                                                       boolean jsEnabled) {
        webSettings.setJavaScriptEnabled(jsEnabled);

        // PWA settings
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            webSettings.setDatabasePath(context.getApplicationContext().getFilesDir().getAbsolutePath());
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
            webSettings.setAppCacheMaxSize(Long.MAX_VALUE);
        }
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCachePath(context.getApplicationContext().getCacheDir().getAbsolutePath());
        webSettings.setAppCacheEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);

        // enable mixed content mode conditionally
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }
    }

}
