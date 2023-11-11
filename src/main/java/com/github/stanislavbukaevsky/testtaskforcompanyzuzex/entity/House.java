package com.github.stanislavbukaevsky.testtaskforcompanyzuzex.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Сущность всех домов
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "homes")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "address")
    private String address;
    @OneToMany(mappedBy = "house", cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private List<User> users;
}
