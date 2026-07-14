package com.example.dclassicsbook.ui.common;

import android.app.Activity;
import android.content.Intent;

import com.example.dclassicsbook.R;
import com.example.dclassicsbook.ui.auth.LoginActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

/** Confirmation popup shown before the bottom-nav Log Out action is carried out. */
public final class LogoutDialog {

    private LogoutDialog() { }

    public static void show(Activity activity) {
        new MaterialAlertDialogBuilder(activity, R.style.ThemeOverlay_DClassicsBook_Dialog)
                .setTitle(R.string.logout_dialog_title)
                .setMessage(R.string.logout_dialog_message)
                .setNegativeButton(R.string.logout_dialog_cancel, (dialog, which) -> dialog.dismiss())
                .setPositiveButton(R.string.logout_dialog_confirm, (dialog, which) -> logOut(activity))
                .show();
    }

    private static void logOut(Activity activity) {
        Intent login = new Intent(activity, LoginActivity.class);
        login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(login);
        activity.finish();
    }
}
