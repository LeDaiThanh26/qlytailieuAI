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
public class ThuMuc {

    @Id
    @Column(name = "MaThuMuc")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Integer maThuMuc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaNguoiDung", referencedColumnName = "MaNguoiDung",
            foreignKey = @ForeignKey(name = "FK_ThuMuc_NguoiDung"))
     NguoiDung maNguoiDung;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaThuMucCha", referencedColumnName = "MaThuMuc",
            foreignKey = @ForeignKey(name = "FK_ThuMuc_ThuMuc"))
     ThuMuc maThuMucCha;

    @Column(name = "TenThuMuc", length = 150)
     String tenThuMuc;

    @Column(name = "NgayTao")
     LocalDateTime ngayTao;

    @Column(name = "NgayCapNhat")
     LocalDateTime ngayCapNhat;

}
