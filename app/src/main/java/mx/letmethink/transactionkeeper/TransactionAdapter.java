package mx.letmethink.transactionkeeper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import mx.letmethink.transactionkeeper.persistence.Transaction;

public class TransactionAdapter extends ArrayAdapter<Transaction> {
    private final Context context;
    private final List<Transaction> transactions;

    public TransactionAdapter(final Context context, final List<Transaction> transactions) {
        super(context, R.layout.list_transactions_list_view_item, transactions);
        this.context = context;
        this.transactions = transactions;
    }

    @Override
    public @NonNull
    View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.list_transactions_list_view_item, parent, false);
        TextView dateView = row.findViewById(R.id.list_transactions_view_date);
        dateView.setText(transactions.get(position).getCreationDate());
        TextView summaryView = row.findViewById(R.id.list_transactions_view_summary);
        summaryView.setText(transactions.get(position).getSummary());
        return row;
    }
}
