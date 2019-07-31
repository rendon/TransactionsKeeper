package mx.letmethink.transactionkeeper;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.stream.Collectors;

import mx.letmethink.transactionkeeper.persistence.Transaction;
import mx.letmethink.transactionkeeper.persistence.TransactionsDatabase;

public class ListTransactionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_transactions);

        ListView items = findViewById(R.id.list_transactions_items_list);

        TransactionsDatabase database = TransactionsDatabase.getInstance(this);
        AsyncTask.execute(() -> {
            List<Transaction> transactions = database.transactionDao().getTransactions();
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this,
                    R.layout.activity_list_transactions,
                    R.id.list_transactions_view,
                    transactions.stream().map(Transaction::getSummary).collect(Collectors.toList())
            );
            items.setAdapter(adapter);
        });
    }
}
