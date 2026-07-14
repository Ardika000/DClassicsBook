package com.example.dclassicsbook.ui.common;

import android.app.Activity;
import android.content.Intent;

import com.example.dclassicsbook.R;

/** Cross-fade between the bottom-nav tabs: the outgoing page fades out as the new one fades in. */
public final class TabTransition {

    private static final String EXTRA_TAB_SWITCH = "com.example.dclassicsbook.TAB_SWITCH";

    private TabTransition() { }

    /** Leaves {@code from} for another tab. */
    public static void switchTo(Activity from, Class<? extends Activity> destination) {
        Intent intent = new Intent(from, destination);
        intent.putExtra(EXTRA_TAB_SWITCH, true);
        from.startActivity(intent);
        from.finish();
    }

    /**
     * Applies the cross-fade to the activity being opened. Call from {@code onCreate()}; it is a
     * no-op unless the activity was opened by {@link #switchTo}, so arriving from the login screen
     * keeps the default transition.
     */
    public static void apply(Activity activity) {
        if (!activity.getIntent().getBooleanExtra(EXTRA_TAB_SWITCH, false)) {
            return;
        }
        activity.overrideActivityTransition(
                Activity.OVERRIDE_TRANSITION_OPEN,
                R.anim.tab_fade_in,
                R.anim.tab_fade_out);
    }
}
