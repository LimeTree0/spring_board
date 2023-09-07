package com.limecoding.limecoding.domain.user;

import com.limecoding.limecoding.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Users extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)    // JPA로 데이터베이스로 저장할 때 Enum 값을 어떤 형태로 저장할지 결정, 기본은 int
    @Column(nullable = false)
    private Role role;

    @Builder
    public Users(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public Users update(String name, String pictue) {
        this.name = name;
        this.picture = pictue;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
