package com.example.projectsale.order.entity;

import com.example.projectsale.orderdetail.entity.OrderDetail;
import com.example.projectsale.user.entity.User;
import com.example.projectsale.utils.base.BaseEntityUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`order`")
public class Order extends BaseEntityUtil {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "email")
    private String email;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "order")
    private Set<OrderDetail> orderDetails;

}
