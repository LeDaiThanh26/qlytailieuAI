package com.example.qlytailieuAI.qlytailieuAI.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class NoiDungTrichXuat {

    @Id
    @Column(name = "MaNoiDung")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Integer maNoiDung;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaPhienBan", referencedColumnName = "MaPhienBan",
            foreignKey = @ForeignKey(name = "FK_NoiDungTrichXuat_PhienBanTaiLieu"))
     PhienBanTaiLieu maPhienBan;

    @Column(name = "NoiDung", columnDefinition = "LONGTEXT")
     String noiDung;

    @Column(name = "PhuongPhapTrichXuat")
     String phuongPhapTrichXuat;

    @Column(name = "NgonNgu", length = 10)
     String ngonNgu;

    @Column(name = "NgayTao")
     LocalDateTime ngayTao;
}
