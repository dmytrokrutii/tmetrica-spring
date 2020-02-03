package com.project.timetracking.domain.entity;

import com.project.timetracking.domain.enums.Action;
import com.project.timetracking.domain.enums.OrderStatus;
import com.sun.istack.NotNull;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

/**
 * The type Order.
 */
@TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class
)

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activityOrder;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userOrder;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    @Column(name = "status")
    private OrderStatus status;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "action")
    @Type(type = "pgsql_enum")
    private Action action;

    /**
     * Instantiates a new Order.
     *
     * @param activity the activity
     * @param user     the user
     * @param status   the status
     * @param action   the action
     */
    public Order(Activity activity, User user, OrderStatus status, Action action) {
        this.activityOrder = activity;
        this.userOrder = user;
        this.status = status;
        this.action = action;
    }
}
