package com.example.lab7_gtics_20251_20216352.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "proveedor")
@JsonIgnoreProperties({"webUrl, direccion, facturacionAnual, fechaRegistro, ultimaActualizacion"})
public class proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer proveedorId;

    @Column(name = "razon_social", length = 100)
    private String razonSocial;

    @Column(name = "nombre_comercial", length = 100)
    private String nombreComercial;

    @Column(name = "ruc", length = 11)
    private String ruc;

    @Column(name = "telefono", length = 9, nullable = false, unique = true)
    private String telefono;

    @Column(name = "correo_electronico", nullable = false, unique = true)
    private String correoElectronico;

    @Column(name = "web_url", nullable = false)
    private String webUrl;

    @Column(name = "direccion", length = 150, nullable = false)
    private String direccion;

    @Column(name = "pais", nullable = false)
    private String pais;

    @Column(name = "representante_legal", length = 100, nullable = false)
    private String representanteLegal;

    @Column(name = "tipo_proveedor", nullable = false)
    private String tipoProveedor;

    @Column(name = "categoria" , nullable = false)
    private String categoria;

    @Column(name = "facturacion_anual", nullable = false)
    private Double facturacionAnual;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;

    @Column(name = "ultima_actualizacion", nullable = false)
    private LocalDateTime ultimaActualizacion;

    @Column(name = "estado", nullable = false)
    private Boolean estado;
}
