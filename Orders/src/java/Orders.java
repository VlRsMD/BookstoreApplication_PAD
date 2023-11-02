import jakarta.persistence.*;

@Entity
@Table
public class Orders {
    @Id
    @SequenceGenerator(
            name = "orders_sequence",
            sequenceName = "orders_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "orders_sequence"
    )
    private Long id;
    private Long orderingPersonId;
    private Long bookId;
    private String address;
    private String timestamp;

    public Orders() {
    }

    public Orders(Long orderingPersonId, Long bookId, String address, String timestamp) {
        this.orderingPersonId = orderingPersonId;
        this.bookId = bookId;
        this.address = address;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderingPersonId() {
        return orderingPersonId;
    }

    public void setOrderingPersonId(Long orderingPersonId) {
        this.orderingPersonId = orderingPersonId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderingPersonId=" + orderingPersonId +
                ", bookId=" + bookId +
                ", address='" + address + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
