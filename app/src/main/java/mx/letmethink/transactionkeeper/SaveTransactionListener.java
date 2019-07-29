package mx.letmethink.transactionkeeper;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import mx.letmethink.transactionkeeper.persistence.Transaction;
import mx.letmethink.transactionkeeper.persistence.TransactionsDatabase;

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

            TransactionsDatabase database = TransactionsDatabase.getInstance(activity);

            final Transaction transaction = new Transaction(Double.valueOf(amount), description);
            AsyncTask.execute(() -> database.transactionDao().insertTransaction(transaction));
            AsyncTask.execute(() -> {
                        for (Transaction t : database.transactionDao().getTransactions()) {
                            Log.i(TAG, "ID: " + t.getId() + " Amount: " + t.getAmount());
                        }
                    }
            );
            String text = activity.getText(R.string.new_transaction_save_confirmation).toString();
            Toast.makeText(activity, text, Toast.LENGTH_SHORT).show();
        } else {
            Log.e(TAG, "Invalid amount, please review");
            amountInputLayout.setError(activity.getString(R.string.new_transaction_invalid_amount));
        }
    }
}
