package com.example.dclassicsbook.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dclassicsbook.R;
import com.example.dclassicsbook.data.model.Book;
import com.example.dclassicsbook.ui.main.BooksActivity;
import com.example.dclassicsbook.ui.main.MainActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class BookDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_detail_page);

        // Get book from intent
        Book book = (Book) getIntent().getSerializableExtra("BOOK_DATA");
        if (book == null) {
            Toast.makeText(this, R.string.book_not_found, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        ImageView btnBack       = findViewById(R.id.btnBack);
        TextView tvPageTitle    = findViewById(R.id.tvPageTitle);
        ImageView imgBookCover  = findViewById(R.id.imgBookCover);
        TextView tvBookTitle    = findViewById(R.id.tvBookTitle);
        TextView tvBookAuthor   = findViewById(R.id.tvBookAuthor);
        TextView tvBookYear     = findViewById(R.id.tvBookYear);
        TextView tvBookGenres   = findViewById(R.id.tvBookGenres);
        TextView tvBookSynopsis = findViewById(R.id.tvBookSynopsis);

        LinearLayout bookingForm  = findViewById(R.id.bookingForm);
        EditText etAddress        = findViewById(R.id.etAddress);
        EditText etPhoneNumber    = findViewById(R.id.etPhoneNumber);
        TextView btnAddToCart     = findViewById(R.id.btnAddToCart);
        ScrollView mainScrollView = findViewById(R.id.mainScrollView);
        LinearLayout scrollContainer = findViewById(R.id.scrollContainer);

        // ── Keyboard detection: auto scroll & reset padding ───────────────────
        View rootView = findViewById(android.R.id.content);
        boolean[] isKeyboardShowing = {false};

        rootView.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            int heightDiff = rootView.getRootView().getHeight() - rootView.getHeight();
            boolean isShowing = heightDiff > (int) (200 * getResources().getDisplayMetrics().density);

            if (isShowing != isKeyboardShowing[0]) {
                isKeyboardShowing[0] = isShowing;
                if (isShowing) {
                    scrollContainer.setPadding(
                            scrollContainer.getPaddingLeft(),
                            scrollContainer.getPaddingTop(),
                            scrollContainer.getPaddingRight(),
                            (int) (300 * getResources().getDisplayMetrics().density)
                    );
                    mainScrollView.postDelayed(
                            () -> mainScrollView.smoothScrollTo(0, scrollContainer.getHeight()), 300);
                } else {
                    scrollContainer.setPadding(
                            scrollContainer.getPaddingLeft(),
                            scrollContainer.getPaddingTop(),
                            scrollContainer.getPaddingRight(),
                            (int) (40 * getResources().getDisplayMetrics().density)
                    );
                    etAddress.clearFocus();
                    etPhoneNumber.clearFocus();
                }
            }
        });

        // ── Populate views ────────────────────────────────────────────────────
        tvPageTitle.setText(book.getTitle());
        tvBookTitle.setText(book.getTitle());
        tvBookAuthor.setText(book.getAuthor());
        tvBookYear.setText(book.getYear());

        String formattedGenres = TextUtils.join("   |   ", book.getGenres());
        tvBookGenres.setText(formattedGenres);

        tvBookSynopsis.setText(book.getDescription());
        imgBookCover.setImageResource(book.getCoverResId());

        btnBack.setOnClickListener(v -> finish());

        // ── Submit button logic ───────────────────────────────────────────────
        btnAddToCart.setOnClickListener(v -> {
            if (bookingForm.getVisibility() == View.GONE) {
                // First click: show the form
                bookingForm.setVisibility(View.VISIBLE);
                btnAddToCart.setText("Submit");
            } else {
                // Second click: validate then submit
                String address = etAddress.getText().toString().trim();
                String phone   = etPhoneNumber.getText().toString().trim();

                // Validation 1: fields must not be empty
                if (address.isEmpty() || phone.isEmpty()) {
                    showBookingDialog(R.string.booking_incomplete_title,
                            R.string.booking_incomplete_message, false);
                    return;
                }

                // Validation 2: phone must be numeric
                if (!phone.matches("[0-9]+")) {
                    showBookingDialog(R.string.booking_invalid_title,
                            R.string.booking_invalid_message, false);
                    return;
                }

                // Validation passed: show confirmation dialog, then redirect
                showBookingDialog(R.string.booking_success_title,
                        R.string.booking_success_message, true);
            }
        });
    }

    /** Shows a booking dialog styled like the shared logout confirmation popup. */
    private void showBookingDialog(@StringRes int title, @StringRes int message, boolean isSuccess) {
        MaterialAlertDialogBuilder builder =
                new MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_DClassicsBook_Dialog)
                        .setTitle(title)
                        .setMessage(message);

        if (isSuccess) {
            builder.setPositiveButton(R.string.booking_dialog_ok, (dialog, which) -> {
                // Reset form
                LinearLayout bookingForm = findViewById(R.id.bookingForm);
                TextView btnAddToCart = findViewById(R.id.btnAddToCart);
                EditText etAddress = findViewById(R.id.etAddress);
                EditText etPhoneNumber = findViewById(R.id.etPhoneNumber);

                bookingForm.setVisibility(View.GONE);
                btnAddToCart.setText("Add to Cart");
                etAddress.setText("");
                etPhoneNumber.setText("");
                etAddress.clearFocus();
                etPhoneNumber.clearFocus();

                // Redirect to item/main page
                Intent intent = new Intent(BookDetailActivity.this, BooksActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            });
            builder.setCancelable(false);
        } else {
            builder.setPositiveButton(R.string.booking_dialog_ok, null);
        }

        builder.show();
    }
}
