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
public class DoanVanBan {

    @Id
    @Column(name = "MaDoan")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Integer maDoan;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaNoiDung", referencedColumnName = "MaNoiDung",
            foreignKey = @ForeignKey(name = "FK_DoanVanBan_NoiDungTrichXuat"))
     NoiDungTrichXuat maNoiDung;

    @Column(name = "SoThuTu")
     Integer soThuTu;

    @Column(name = "NoiDungDoan", columnDefinition = "TEXT")
     String noiDungDoan;

    @Column(name = "MaVector", length = 100)
     String maVector;

    @Column(name = "SieuDuLieu", columnDefinition = "JSON")
     String sieuDuLieu;

}
