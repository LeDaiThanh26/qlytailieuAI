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
public class TaiLieu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Integer maTaiLieu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaNguoiTaiLen", referencedColumnName = "MaNguoiDung",
            foreignKey = @ForeignKey(name = "FK_TaiLieu_NguoiDung"))
     NguoiDung maNguoiTaiLen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaThuMuc", referencedColumnName = "MaThuMuc",
            foreignKey = @ForeignKey(name = "FK_TaiLieu_ThuMuc"))
     ThuMuc maThuMuc;

    @Column(name = "TenTaiLieu", length = 255)
     String tenTaiLieu;

    @Column(name = "TrangThai")
     String trangThai;

    @Column(name = "LoaiTaiLieu", length = 100)
     String loaiTaiLieu;

    @Column(name = "SieuDuLieu", columnDefinition = "JSON")
     String sieuDuLieu;

    @Column(name = "DaTinCayPhanLoai", precision = 4, scale = 2)
     BigDecimal daTinCayPhanLoai;

    @Column(name = "DaXoa")
     Boolean daXoa;

    @Column(name = "NgayXoa")
     LocalDateTime ngayXoa;

    @Column(name = "NgayTaiLen")
     LocalDateTime ngayTaiLen;

    @Column(name = "NgayCapNhat")
     LocalDateTime ngayCapNhat;

}
