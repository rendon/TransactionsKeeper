package mx.letmethink.transactionkeeper;

import android.text.Editable;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;



import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import org.junit.Test;


public class SaveTransactionListenerTest {
    @Test
    public void getText_ItShouldReturnEmptyStringWhenEditTextIsNull() {
        TextInputLayout layout = mock(TextInputLayout.class);
        doReturn(null).when(layout).getEditText();
        assertThat(SaveTransactionListener.getText(layout)).isEmpty();
    }

    @Test
    public void getText_ItShouldReturnStoredTextWhenEditTextIsNotNull() {
        Editable editable = mock(Editable.class);
        doReturn("23.2").when(editable).toString();
        EditText editText = mock(EditText.class);
        doReturn(editable).when(editText).getText();

        TextInputLayout layout = mock(TextInputLayout.class);
        doReturn(editText).when(layout).getEditText();
        assertThat(SaveTransactionListener.getText(layout)).isEqualTo("23.2");
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
}
