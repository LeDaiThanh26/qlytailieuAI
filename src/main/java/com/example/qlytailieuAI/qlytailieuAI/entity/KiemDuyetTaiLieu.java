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
public class KiemDuyetTaiLieu {

    @Id
    @Column(name = "MaKiemDuyet")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Integer maKiemDuyet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaPhienBan", referencedColumnName = "MaPhienBan",
            foreignKey = @ForeignKey(name = "FK_KiemDuyetTaiLieu_PhienBanTaiLieu"))
     PhienBanTaiLieu maPhienBan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaNguoiKiemDuyet", referencedColumnName = "MaNguoiDung",
            foreignKey = @ForeignKey(name = "FK_KiemDuyetTaiLieu_NguoiDung"))
     NguoiDung maNguoiKiemDuyet;

    @Column(name = "KetQuaTuDong")
     String ketQuaTuDong;

    @Column(name = "KetQuaKiemDuyet")
     String ketQuaKiemDuyet;

    @Column(name = "LyDo", columnDefinition = "TEXT")
     String lyDo;

    @Column(name = "NgayKiemDuyet")
     LocalDateTime ngayKiemDuyet;

}
