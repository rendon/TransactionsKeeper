package mx.letmethink.transactionkeeper;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;



import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import mx.letmethink.transactionkeeper.persistence.Transaction;
import mx.letmethink.transactionkeeper.persistence.TransactionsDatabase;


public class SaveTransactionListenerTest {
    @Test
    public void getText_ItShouldReturnEmptyStringWhenEditTextIsNull() {
        TextInputLayout layout = mock(TextInputLayout.class);
        doReturn(null).when(layout).getEditText();
        assertThat(SaveTransactionListener.getText(layout)).isEmpty();
    }

    @Test
    public void getText_ItShouldReturnStoredTextWhenEditTextIsNotNull() {
        TextInputLayout layout = mockInput("23.2");
        assertThat(SaveTransactionListener.getText(layout)).isEqualTo("23.2");
    }

    private TextInputLayout mockInput(final String text) {
        Editable editable = mock(Editable.class);
        doReturn(text).when(editable).toString();
        EditText editText = mock(EditText.class);
        doReturn(editable).when(editText).getText();

        TextInputLayout layout = mock(TextInputLayout.class);
        doReturn(editText).when(layout).getEditText();
        return layout;
    }

    @Test
    public void isValidAmount_ReturnsFalseWhenAmountIsNotANumber() {
        assertThat(SaveTransactionListener.isValidAmount("a3.5")).isFalse();
    }

    @Test
    public void isValidAmount_ReturnsFalseWhenAmountIsNegative() {
        assertThat(SaveTransactionListener.isValidAmount("-2")).isFalse();
    }

    @Test
    public void isValidAmount_ReturnsTrueWhenTheAmountIsAPositiveNumber() {
        assertThat(SaveTransactionListener.isValidAmount("10.85")).isTrue();
    }

    @Test
    public void onClick_ItShouldDisplayConfirmationMessage() {
        Activity activity = mock(Activity.class);
        TransactionsDatabase database = mock(TransactionsDatabase.class);
        SaveTransactionListener listener = spy(new SaveTransactionListener(activity, database));

        TextInputLayout amount = mockInput("10.85");
        doReturn(amount).when(activity).findViewById(eq(R.id.new_transaction_amount));

        TextInputLayout description = mockInput("Lunch");
        doReturn(description).when(activity).findViewById(eq(R.id.new_transaction_description));

        doNothing().when(listener).saveTransaction(any(Transaction.class));
        doNothing().when(listener).displayConfirmationMessage(any(Context.class));
        listener.onClick(mock(View.class));

        verify(listener, times(1)).saveTransaction(any(Transaction.class));
        verify(listener, times(1)).displayConfirmationMessage(any(Context.class));
    }
}
