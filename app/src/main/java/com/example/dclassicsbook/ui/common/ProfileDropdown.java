package com.example.dclassicsbook.ui.common;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.dclassicsbook.R;
import com.example.dclassicsbook.data.session.UserSession;

/**
 * Account details shown as a dropdown anchored under the home header avatar.
 * A PopupWindow is used instead of a dialog so the page behind it stays undimmed.
 */
public final class ProfileDropdown {

    private ProfileDropdown() { }

    public static void showUnder(View anchor) {
        View content = LayoutInflater.from(anchor.getContext())
                .inflate(R.layout.popup_profile, null, false);

        UserSession session = UserSession.getInstance();
        ((TextView) content.findViewById(R.id.tvProfileUsername)).setText(session.getUsername());
        ((TextView) content.findViewById(R.id.tvProfileEmail)).setText(session.getEmail());

        PopupWindow popup = new PopupWindow(content,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true);

        // Transparent window background: the rounded card comes from the layout itself,
        // and a non-null background is what makes an outside tap dismiss the popup.
        popup.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popup.setElevation(dp(anchor, 12));
        popup.setAnimationStyle(R.style.Animation_DClassicsBook_Popup);

        // Right edge of the card lined up with the right edge of the avatar. Measuring the
        // content is what makes that exact; Gravity.END alone lets the shadow push it off-screen.
        int available = anchor.getResources().getDisplayMetrics().widthPixels;
        content.measure(
                View.MeasureSpec.makeMeasureSpec(available, View.MeasureSpec.AT_MOST),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        int xOffset = anchor.getWidth() - content.getMeasuredWidth();

        popup.showAsDropDown(anchor, xOffset, dp(anchor, 8));
    }

    private static int dp(View view, int value) {
        return Math.round(value * view.getResources().getDisplayMetrics().density);
    }
}
