package mx.letmethink.transactionkeeper.persistence;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TransactionDao {
    @Query("SELECT * FROM transactions")
    List<Transaction> getTransactions();

    @Insert
    void insertTransaction(Transaction transaction);
}
