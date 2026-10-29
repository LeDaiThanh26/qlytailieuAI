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
public class ThongBao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Integer maThongBao;

    @Column(name = "MaNguoiTao")
     Integer maNguoiTao;

    @Column(name = "TieuDe", length = 200)
     String tieuDe;

    @Column(name = "NoiDung", columnDefinition = "TEXT")
     String noiDung;

    @Column(name = "NgayBatDau")
     LocalDateTime ngayBatDau;

    @Column(name = "NgayKetThuc")
     LocalDateTime ngayKetThuc;

    @Column(name = "TrangThai")
     String trangThai;

    @Column(name = "NgayTao")
     LocalDateTime ngayTao;

    @Column(name = "NgayCapNhat")
     LocalDateTime ngayCapNhat;

}
