package com.example.qlytailieuAI.qlytailieuAI.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "PhanHoiAI")
public class AiFeedback {

    @Id
    @Column(name = "MaPhanHoi")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaTinNhan", referencedColumnName = "MaTinNhan",
            foreignKey = @ForeignKey(name = "FK_ai_feedbacks_messages"))
    Message message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaNguoiDung", referencedColumnName = "MaNguoiDung",
            foreignKey = @ForeignKey(name = "FK_ai_feedbacks_users"))
    User user;

    @Column(name = "DanhGia")
    String rating;

    @Column(name = "DiemChinhXac")
    Boolean isAccurate;

    @Column(name = "BinhLuan", columnDefinition = "TEXT")
    String comment;

    @Column(name = "NgayTao")
    LocalDateTime createdAt;

}
