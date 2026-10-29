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
public class CuocHoiThoai {

    @Id
    @Column(name = "MaCuocHoiThoai")
    String maCuocHoiThoai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaNguoiDung", referencedColumnName = "MaNguoiDung",
            foreignKey = @ForeignKey(name = "FK_CuocHoiThoai_NguoiDung"))
     NguoiDung maNguoiDung;

    @Column(name = "TieuDe", length = 200)
     String tieuDe;

    @Column(name = "NgayTao")
     LocalDateTime ngayTao;

    @Column(name = "NgayCapNhat")
     LocalDateTime ngayCapNhat;

}
