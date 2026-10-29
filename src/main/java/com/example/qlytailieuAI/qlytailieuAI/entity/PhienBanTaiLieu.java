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
public class PhienBanTaiLieu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Integer maPhienBan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaTaiLieu", referencedColumnName = "MaTaiLieu",
            foreignKey = @ForeignKey(name = "FK_PhienBanTaiLieu_TaiLieu"))
     TaiLieu maTaiLieu;

    @Column(name = "MaNguoiTaoPhienBan")
     Integer maNguoiTaoPhienBan;

    @Column(name = "SoPhienBan")
     Integer soPhienBan;

    @Column(name = "TenFileGoc", length = 255)
     String tenFileGoc;

    @Column(name = "DuongDanLuuTru", length = 500)
     String duongDanLuuTru;

    @Column(name = "KichThuoc", columnDefinition = "BIGINT")
     Long kichThuoc;

    @Column(name = "MaBamSha256", length = 64)
     String maBamSha256;

    @Column(name = "GhiChu", length = 500)
     String ghiChu;

    @Column(name = "LaHienHanh")
     Boolean laHienHanh;

    @Column(name = "NgayTao")
     LocalDateTime ngayTao;

}
