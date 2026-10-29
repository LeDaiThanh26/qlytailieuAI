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
public class NguonThamKhao {

    @Id
    @Column(name = "MaNguon")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Integer maNguon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaTinNhan", referencedColumnName = "MaTinNhan",
            foreignKey = @ForeignKey(name = "FK_NguonThamKhao_TinNhan"))
    TinNhan maTinNhan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaDoan", referencedColumnName = "MaDoan",
            foreignKey = @ForeignKey(name = "FK_NguonThamKhao_DoanVanBan"))
    DoanVanBan maDoan;

    @Column(name = "DiemLienQuan")
     Float diemLienQuan;
}
