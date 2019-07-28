package mx.letmethink.transactionkeeper;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NewTransactionActivity extends AppCompatActivity {
    private static final String TAG = SaveTransactionListener.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_transaction);
        FloatingActionButton saveTransactionButton = findViewById(R.id.new_transaction_save_btn);
        saveTransactionButton.setOnClickListener(new SaveTransactionListener(this));
    }
}
