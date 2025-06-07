package com.example.lab7_gtics_20251_20216352.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer proveedorId;

    @Column(name = "razon_social")
    private String razonSocial;

    @Column(name = "nombre_comercial")
    private String nombreComercial;

    @Column(name = "ruc")
    private String ruc;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "correo_electronico")
    private String correoElectronico;

    @Column(name = "web_url")
    private String webUrl;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "pais")
    private String pais;

    @Column(name = "representante_legal")
    private String representanteLegal;

    @Column(name = "tipo_proveedor")
    private String tipoProveedor;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "facturacion_anual")
    private Double facturacionAnual;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @Column(name = "ultima_actualizacion")
    private LocalDateTime ultimaActualizacion;

    @Column(name = "estado")
    private Boolean estado;
}
