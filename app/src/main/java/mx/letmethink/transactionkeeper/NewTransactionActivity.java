package mx.letmethink.transactionkeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

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
