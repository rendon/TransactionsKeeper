package mx.letmethink.transactionkeeper;

import android.util.Log;
import android.view.View;

import com.google.android.material.textfield.TextInputLayout;

public class SaveTransactionListener implements View.OnClickListener {
    private static final String TAG = SaveTransactionListener.class.getSimpleName();
    private final NewTransactionActivity activity;

    public SaveTransactionListener(NewTransactionActivity activity) {
        this.activity = activity;
    }


    static String getText(TextInputLayout layout) {
        if (layout.getEditText() == null) {
            return "";
        }
        return layout.getEditText().getText().toString();
    }

    static boolean isValidAmount(String amount) {
        try {
            return Double.valueOf(amount) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void onClick(View view) {
        TextInputLayout amountInputLayout = activity.findViewById(R.id.new_transaction_amount);
        String amount = getText(amountInputLayout);
        TextInputLayout descriptionInputLayout = activity.findViewById(R.id.new_transaction_description);
        String description = getText(descriptionInputLayout);
        if (isValidAmount(amount)) {
            String details = String.format("Amount = %s Description = %s", amount, description);
            Log.i(TAG, "Saving transaction(" + details + ")");
            amountInputLayout.setError(null);
            amountInputLayout.setErrorEnabled(false);
        } else {
            Log.e(TAG, "Invalid amount, please review");
            amountInputLayout.setError(activity.getString(R.string.new_transaction_invalid_amount));
        }
    }
}
