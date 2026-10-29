package com.example.qlytailieuAI.qlytailieuAI.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class TaiLieuDanhMuc {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaTaiLieu", referencedColumnName = "MaTaiLieu",
            foreignKey = @ForeignKey(name = "FK_TaiLieuDanhMuc_TaiLieu"))
    TaiLieu maTaiLieu;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaDanhMuc", referencedColumnName = "MaDanhMuc",
            foreignKey = @ForeignKey(name = "FK_TaiLieuDanhMuc_DanhMuc"))
    DanhMucTaiLieu maDanhMuc;


}
