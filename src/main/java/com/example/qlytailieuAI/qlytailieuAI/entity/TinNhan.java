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
public class TinNhan {

    @Id
    @Column(name = "MaTinNhan")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Integer maTinNhan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaCuocHoiThoai", referencedColumnName = "MaCuocHoiThoai",
            foreignKey = @ForeignKey(name = "FK_TinNhan_CuocHoiThoai"))
     CuocHoiThoai maCuocHoiThoai;

    @Column(name = "VaiTroNguoiGui")
     String vaiTroNguoiGui;

    @Column(name = "NoiDung", columnDefinition = "TEXT")
     String noiDung;

    @Column(name = "ThoiDiemGui")
     LocalDateTime thoiDiemGui;

}
