package com.example.dclassicsbook.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.dclassicsbook.R;

public class BottomNavBar extends LinearLayout {

    public static final int HOME   = 0;
    public static final int BOOKS  = 1;
    public static final int STORES = 2;
    public static final int LOGOUT = 3;

    public interface OnItemSelectedListener {
        void onItemSelected(int index);
    }

    private final View[]      items  = new View[4];
    private final ImageView[] icons  = new ImageView[4];
    private final TextView[]  labels = new TextView[4];

    private int activeIndex = HOME;
    @Nullable private OnItemSelectedListener listener;

    public BottomNavBar(Context context) {
        super(context);
        init(context);
    }

    public BottomNavBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BottomNavBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOrientation(HORIZONTAL);
        setBackgroundResource(R.drawable.bg_bottom_nav);

        LayoutInflater.from(context).inflate(R.layout.view_bottom_nav, this, true);

        bind(HOME,   R.id.navHome,   R.id.navHomeIcon,   R.id.navHomeLabel);
        bind(BOOKS,  R.id.navBooks,  R.id.navBooksIcon,  R.id.navBooksLabel);
        bind(STORES, R.id.navStores, R.id.navStoresIcon, R.id.navStoresLabel);
        bind(LOGOUT, R.id.navLogout, R.id.navLogoutIcon, R.id.navLogoutLabel);

        setActiveItem(HOME);
    }

    private void bind(int index, int containerId, int iconId, int labelId) {
        items[index]  = findViewById(containerId);
        icons[index]  = findViewById(iconId);
        labels[index] = findViewById(labelId);
        items[index].setOnClickListener(v -> {
            if (index != LOGOUT) {
                setActiveItem(index);
            }
            if (listener != null) {
                listener.onItemSelected(index);
            }
        });
    }

    public void setActiveItem(int index) {
        activeIndex = index;
        int active   = ContextCompat.getColor(getContext(), R.color.menu_icon_active);
        int inactive = ContextCompat.getColor(getContext(), R.color.menu_icon_default);
        for (int i = 0; i < items.length; i++) {
            int color = (i == index) ? active : inactive;
            icons[i].setColorFilter(color);
            labels[i].setTextColor(color);
        }
    }

    public int getActiveItem() {
        return activeIndex;
    }

    public void setOnItemSelectedListener(@Nullable OnItemSelectedListener listener) {
        this.listener = listener;
    }
}
