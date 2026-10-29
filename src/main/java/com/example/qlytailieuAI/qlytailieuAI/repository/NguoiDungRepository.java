package com.example.qlytailieuAI.qlytailieuAI.repository;

import java.util.Optional;

import com.example.qlytailieuAI.qlytailieuAI.entity.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NguoiDungRepository extends JpaRepository<NguoiDung, Integer> {
    Optional<NguoiDung> findByEmailIgnoreCase(String email);
    boolean existsByEmailIgnoreCase(String email);
    boolean existsByEmailIgnoreCaseAndMaNguoiDungNot(String email, Integer maNguoiDung);
}
