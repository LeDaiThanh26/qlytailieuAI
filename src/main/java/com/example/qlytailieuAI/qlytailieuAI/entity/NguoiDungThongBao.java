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
public class NguoiDungThongBao {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaNguoiDung", referencedColumnName = "MaNguoiDung",
            foreignKey = @ForeignKey(name = "FK_NguoiDungThongBao_NguoiDung"))
    NguoiDung maNguoiDung;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaThongBao", referencedColumnName = "MaThongBao",
            foreignKey = @ForeignKey(name = "FK_NguoiDungThongBao_ThongBao"))
    ThongBao maThongBao;

    @Column(name = "DaDoc")
     Boolean daDoc;

    @Column(name = "NgayDoc")
     LocalDateTime ngayDoc;

}
