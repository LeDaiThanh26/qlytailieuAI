package com.example.qlytailieuAI.qlytailieuAI.repository;

import java.util.Optional;

import com.example.qlytailieuAI.qlytailieuAI.entity.VaiTro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaiTroRepository extends JpaRepository<VaiTro, Integer> {
    Optional<VaiTro> findByTenVaiTroIgnoreCase(String tenVaiTro);
}
