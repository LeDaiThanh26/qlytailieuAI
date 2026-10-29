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
public class DanhMucTaiLieu {

    @Id
    @Column(name = "MaDanhMuc")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Integer maDanhMuc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaDanhMucCha", referencedColumnName = "MaDanhMuc",
            foreignKey = @ForeignKey(name = "FK_DanhMucTaiLieu_DanhMucTaiLieu"))
    DanhMucTaiLieu maDanhMucCha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaNguoiTao", referencedColumnName = "MaNguoiDung",
            foreignKey = @ForeignKey(name = "FK_DanhMucTaiLieu_NguoiDung"))
    NguoiDung maNguoiTao;

    @Column(name = "TenDanhMuc", length = 150)
     String tenDanhMuc;

    @Column(name = "MoTa", length = 255)
     String moTa;

    @Column(name = "TrangThai")
     String trangThai;

    @Column(name = "NgayTao")
     LocalDateTime ngayTao;

    @Column(name = "NgayCapNhat")
     LocalDateTime ngayCapNhat;


}
