package com.example.lab7_gtics_20251_20216352.repository;

import com.example.lab7_gtics_20251_20216352.entity.proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface proveedorRepository extends JpaRepository<proveedor, Integer> {

}