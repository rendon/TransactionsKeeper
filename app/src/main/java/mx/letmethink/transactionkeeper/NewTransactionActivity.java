package mx.letmethink.transactionkeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

public class NewTransactionActivity extends AppCompatActivity {
    private static final String TAG = "NewTransaction";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_transaction);
        FloatingActionButton saveTransactionButton = findViewById(R.id.new_transaction_save_btn);
        saveTransactionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextInputLayout amount = findViewById(R.id.new_transaction_amount);
                TextInputLayout description = findViewById(R.id.new_transaction_description);
                String details = String.format(
                        "Amount = %s Description = %s",
                        getText(amount), getText(description)
                );
                Log.i(TAG, "Saving transaction(" + details + ")");
            }

            private String getText(final TextInputLayout inputLayout) {
                if (inputLayout.getEditText() == null) {
                    return "";
                }
                return inputLayout.getEditText().getText().toString();
            }
        });
    }
}
