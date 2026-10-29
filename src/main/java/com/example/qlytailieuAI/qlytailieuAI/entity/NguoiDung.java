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
@Table(name = "NguoiDung")
public class NguoiDung {

    @Id
    @Column(name = "MaNguoiDung")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Integer maNguoiDung;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaVaiTro", referencedColumnName = "MaVaiTro",
            foreignKey = @ForeignKey(name = "FK_NguoiDung_VaiTro"))
    VaiTro vaiTro;


    @Column(name = "HoTen", length = 100)
     String hoTen;

    @Column(name = "Email", length = 100)
     String email;

    @Column(name = "MatKhau", length = 255)
     String matKhau;

    @Column(name = "TrangThai")
     String trangThai;

    @Column(name = "NgayTao")
     LocalDateTime ngayTao;

    @Column(name = "NgayCapNhat")
     LocalDateTime ngayCapNhat;

}
