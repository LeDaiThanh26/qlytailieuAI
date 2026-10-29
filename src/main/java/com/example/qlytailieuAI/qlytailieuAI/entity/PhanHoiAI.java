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
public class PhanHoiAI {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Integer maPhanHoi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaTinNhan", referencedColumnName = "MaTinNhan",
            foreignKey = @ForeignKey(name = "FK_PhanHoiAI_TinNhan"))
     TinNhan maTinNhan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaNguoiDung", referencedColumnName = "MaNguoiDung",
            foreignKey = @ForeignKey(name = "FK_PhanHoiAI_NguoiDung"))
    NguoiDung maNguoiDung;

    @Column(name = "DanhGia")
     String danhGia;

    @Column(name = "DiemChinhXac")
     Boolean diemChinhXac;

    @Column(name = "BinhLuan", columnDefinition = "TEXT")
     String binhLuan;

    @Column(name = "NgayTao")
     LocalDateTime ngayTao;

}
