package com.example.qlytailieuAI.qlytailieuAI.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class VaiTro {

    @Id
    @Column(name = "MaVaiTro")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Integer maVaiTro;

    @Column(name = "TenVaiTro", length = 50)
     String tenVaiTro;

    @Column(name = "MoTa", length = 255)
     String moTa;

}
