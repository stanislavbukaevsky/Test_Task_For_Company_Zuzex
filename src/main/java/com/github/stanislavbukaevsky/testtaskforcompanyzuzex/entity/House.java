package com.github.stanislavbukaevsky.testtaskforcompanyzuzex.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
