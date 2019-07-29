package mx.letmethink.transactionkeeper;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.VisibleForTesting;

import com.google.android.material.textfield.TextInputLayout;

import mx.letmethink.transactionkeeper.persistence.Transaction;
import mx.letmethink.transactionkeeper.persistence.TransactionsDatabase;

public class SaveTransactionListener implements View.OnClickListener {
    private static final String TAG = SaveTransactionListener.class.getSimpleName();
    private final Activity activity;
    private final TransactionsDatabase database;

    public SaveTransactionListener(Activity activity, TransactionsDatabase database) {
        this.activity = activity;
        this.database = database;
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
        final Context context = activity;
        TextInputLayout amountInputLayout = activity.findViewById(R.id.new_transaction_amount);
        String amount = getText(amountInputLayout);
        TextInputLayout descriptionInputLayout = activity.findViewById(R.id.new_transaction_description);
        String description = getText(descriptionInputLayout);
        if (isValidAmount(amount)) {
            String details = String.format("Amount = %s Description = %s", amount, description);
            Log.i(TAG, "Saving transaction(" + details + ")");
            amountInputLayout.setError(null);

            final Transaction transaction = new Transaction(Double.valueOf(amount), description);
            saveTransaction(transaction);
            displayConfirmationMessage(context);
        } else {
            amountInputLayout.setError(context.getString(R.string.new_transaction_invalid_amount));
        }
    }

    @VisibleForTesting
    void saveTransaction(Transaction transaction) {
        AsyncTask.execute(() -> database.transactionDao().insertTransaction(transaction));
    }

    @VisibleForTesting
    void displayConfirmationMessage(final Context context) {
        String text = context.getText(R.string.new_transaction_save_confirmation).toString();
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
