package mx.letmethink.transactionkeeper.persistence;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;


@Entity(tableName = "transactions")
public class Transaction {
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss",
            Locale.US
    );
    public Transaction(Double amount, String description) {
        id = UUID.randomUUID().toString();
        this.amount = amount;
        this.description = description;
        createdAt = updatedAt = System.currentTimeMillis();
    }

    public String getSummary() {
        String date = DATE_FORMATTER.format(new Date(createdAt));
        return String.format(Locale.getDefault(), "%s - %f - %s", date, amount, description);
    }

    @NonNull
    @PrimaryKey
    private String id;

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    private Double amount;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private Long createdAt;

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    private Long updatedAt;

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }
}
