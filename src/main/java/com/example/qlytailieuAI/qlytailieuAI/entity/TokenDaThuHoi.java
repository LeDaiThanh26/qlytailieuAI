package com.example.qlytailieuAI.qlytailieuAI.entity;

import java.time.Instant;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TokenDaThuHoi")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDaThuHoi {
    @Id
    private String jti;

    private Instant ngayHetHan;
}
