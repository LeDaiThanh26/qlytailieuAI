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
public class ChiaSeTaiLieu {

    @Id
    @Column(name = "MaChiaSe")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Integer maChiaSe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaTaiLieu", referencedColumnName = "MaTaiLieu",
            foreignKey = @ForeignKey(name = "FK_ChiaSeTaiLieu_TaiLieu"))
    TaiLieu maTaiLieu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaNguoiChiaSe", referencedColumnName = "MaNguoiDung",
            foreignKey = @ForeignKey(name = "FK_NguoiDung_ChiaSeTaiLieu1"))
    NguoiDung maNguoiChiaSe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaNguoiNhan", referencedColumnName = "MaNguoiDung",
            foreignKey = @ForeignKey(name = "FK_NguoiDung_ChiaSeTaiLieu2"))
    NguoiDung maNguoiNhan;

    @Column(name = "QuyenTruyCap")
     String quyenTruyCap;

    @Column(name = "MaLienKet", length = 64)
     String maLienKet;

    @Column(name = "NgayTao")
     LocalDateTime ngayTao;

    @Column(name = "NgayHetHan")
     LocalDateTime ngayHetHan;
}
