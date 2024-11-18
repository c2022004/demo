package com.example.projectsale.token;

import com.example.projectsale.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`token`")
public class Token {

    @Id
    @UuidGenerator
    private UUID id;

    @Column
    private String token;

    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    @Column
    private Boolean expired;

    @Column
    private Boolean revoked;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
