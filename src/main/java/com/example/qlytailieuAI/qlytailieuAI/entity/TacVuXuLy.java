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
public class TacVuXuLy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Integer maTacVu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaPhienBan", referencedColumnName = "MaPhienBan",
            foreignKey = @ForeignKey(name = "FK_TacVuXuLy_PhienBanTaiLieu"))
     PhienBanTaiLieu maPhienBan;

    @Column(name = "LoaiTacVu")
     String loaiTacVu;

    @Column(name = "TrangThai")
     String trangThai;

    @Column(name = "ThongBaoLoi", columnDefinition = "TEXT")
     String thongBaoLoi;

    @Column(name = "ThoiDiemBatDau")
     LocalDateTime thoiDiemBatDau;

    @Column(name = "ThoiDiemKetThuc")
     LocalDateTime thoiDiemKetThuc;

}
