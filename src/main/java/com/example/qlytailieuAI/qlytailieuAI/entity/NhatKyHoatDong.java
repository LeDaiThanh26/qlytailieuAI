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
public class NhatKyHoatDong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Integer maNhatKy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaNguoiDung", referencedColumnName = "MaNguoiDung",
            foreignKey = @ForeignKey(name = "FK_NhatKyHoatDong_NguoiDung"))
     NguoiDung maNguoiDung;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaPhienBan", referencedColumnName = "MaPhienBan",
            foreignKey = @ForeignKey(name = "FK_NhatKyHoatDong_PhienBanTaiLieu"))
     PhienBanTaiLieu maPhienBan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaChiaSe", referencedColumnName = "MaChiaSe",
            foreignKey = @ForeignKey(name = "FK_NhatKyHoatDong_ChiaSeTaiLieu"))
     ChiaSeTaiLieu maChiaSe;

    @Column(name = "HanhDong")
     String hanhDong;

    @Column(name = "ChiTiet", columnDefinition = "TEXT")
     String chiTiet;

    @Column(name = "ThoiDiem")
     LocalDateTime thoiDiem;

}
