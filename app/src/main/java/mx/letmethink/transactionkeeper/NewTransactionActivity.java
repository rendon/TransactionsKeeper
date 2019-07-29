package mx.letmethink.transactionkeeper;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import mx.letmethink.transactionkeeper.persistence.TransactionsDatabase;

public class NewTransactionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_transaction);
        FloatingActionButton saveTransactionButton = findViewById(R.id.new_transaction_save_btn);

        TransactionsDatabase database = TransactionsDatabase.getInstance(this);
        saveTransactionButton.setOnClickListener(new SaveTransactionListener(this, database));
    }
}
