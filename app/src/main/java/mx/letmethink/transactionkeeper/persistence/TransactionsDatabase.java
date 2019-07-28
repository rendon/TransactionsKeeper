package mx.letmethink.transactionkeeper.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Transaction.class}, exportSchema = false, version = 1)
public abstract class TransactionsDatabase extends RoomDatabase {
    private static TransactionsDatabase INSTANCE;

    public static TransactionsDatabase getInstance(final Context context) {
       if (INSTANCE == null) {
           synchronized (TransactionsDatabase.class) {
               INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                       TransactionsDatabase.class, "transactions.db")
                       .build();
           }
       }
       return INSTANCE;
    }

    public abstract TransactionDao transactionDao();
}
