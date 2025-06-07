package com.example.lab7_gtics_20251_20216352.controller;

import com.example.lab7_gtics_20251_20216352.entity.proveedor;
import com.example.lab7_gtics_20251_20216352.repository.proveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin
public class proveedorController {

    @Autowired
    private proveedorRepository proveedorRepository;

    @GetMapping(value = "/proveedores", produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
    public List<HashMap<String, Object>> listarProveedores() {
        List<proveedor> proveedores = proveedorRepository.findAll();
        return proveedores.stream().map(proveedor -> {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", proveedor.getProveedorId());
            map.put("razonSocial", proveedor.getRazonSocial());
            map.put("nombreComercial", proveedor.getNombreComercial());
            map.put("ruc", proveedor.getRuc());
            map.put("telefono", proveedor.getTelefono());
            map.put("correoElectronico", proveedor.getCorreoElectronico());
            map.put("pais", proveedor.getPais());
            map.put("representanteLegal", proveedor.getRepresentanteLegal());
            map.put("tipoProveedor", proveedor.getTipoProveedor());
            map.put("categoria", proveedor.getCategoria());
            map.put("estado", proveedor.getEstado() ? "Activo" : "Inactivo");
            return map;
        }).toList();
    }

    @GetMapping("/proveedor/{id}")
    public ResponseEntity<HashMap<String, Object>> getProveedorById(@PathVariable("id") Integer id){

        HashMap<String, Object> responseJson = new HashMap<>();
        try {
             Optional<proveedor> optProveedor = proveedorRepository.findById(id);
             if(optProveedor.isPresent()){
                 responseJson.put("result", "success");
                 if(optProveedor.get().getEstado().equals(true)){
                     responseJson.put("estado", "Activo");
                 }
                 else{
                     responseJson.put("estado", "Inactivo");
                 }
                 responseJson.put("proveedor",optProveedor.get());
                 return ResponseEntity.ok(responseJson);
             }
             else{
                 responseJson.put("msg", "Proveedor no encontrado");
             }
         }catch (NumberFormatException e){
             responseJson.put("msg", "Id debe ser un numero entero");
         }
        responseJson.put("result", "error");
        return ResponseEntity.badRequest().body(responseJson);
    }

    @PostMapping(value = "/crearProveeedor")
    public ResponseEntity<HashMap<String, Object>> crearProveedor(
            @RequestBody proveedor proveedor, @RequestParam(value = "fetchId", required = false) boolean fetchId) {

        HashMap<String, Object> responseMap = new HashMap<>();

        proveedor.setEstado(true);
        proveedor.setFechaRegistro(LocalDateTime.now());
        proveedor.setUltimaActualizacion(null);

        proveedorRepository.save(proveedor);

        if (fetchId) {
            responseMap.put("id", proveedor.getProveedorId());
        }
        responseMap.put("estado", "creado");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMap);
    }

    @PatchMapping("/proveedor/{id}")
    public ResponseEntity<HashMap<String, Object>> actualizarProveedor(
            @PathVariable("id") Integer id,
            @RequestBody HashMap<String, Object> updates) {

        HashMap<String, Object> responseMap = new HashMap<>();
        Optional<proveedor> optProveedor = proveedorRepository.findById(id);

        if (optProveedor.isPresent()) {
            proveedor proveedorToUpdate = optProveedor.get();

            updates.forEach((key, value) -> {
                switch (key) {
                    case "razonSocial" -> proveedorToUpdate.setRazonSocial((String) value);
                    case "nombreComercial" -> proveedorToUpdate.setNombreComercial((String) value);
                    case "ruc" -> proveedorToUpdate.setRuc((String) value);
                    case "telefono" -> proveedorToUpdate.setTelefono((String) value);
                    case "correoElectronico" -> proveedorToUpdate.setCorreoElectronico((String) value);
                    case "pais" -> proveedorToUpdate.setPais((String) value);
                    case "representanteLegal" -> proveedorToUpdate.setRepresentanteLegal((String) value);
                    case "tipoProveedor" -> proveedorToUpdate.setTipoProveedor((String) value);
                    case "categoria" -> proveedorToUpdate.setCategoria((String) value);
                }
            });

            proveedorToUpdate.setUltimaActualizacion(LocalDateTime.now());
            proveedorRepository.save(proveedorToUpdate);

            responseMap.put("result", "success");
            responseMap.put("message", "Proveedor actualizado correctamente");
            return ResponseEntity.ok(responseMap);

        } else {
            responseMap.put("result", "error");
            responseMap.put("message", "Proveedor no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap);
        }
    }

    @DeleteMapping("/proveedor/{id}")
    public ResponseEntity<HashMap<String, Object>> eliminarProveedor(@PathVariable("id") Integer id) {
        HashMap<String, Object> responseMap = new HashMap<>();
        Optional<proveedor> optProveedor = proveedorRepository.findById(id);

        if (optProveedor.isPresent()) {
            proveedor proveedorToDelete = optProveedor.get();
            proveedorToDelete.setEstado(false);
            proveedorRepository.save(proveedorToDelete);

            responseMap.put("result", "success");
            responseMap.put("message", "Proveedor eliminado lógicamente");
            return ResponseEntity.ok(responseMap);

        } else {
            responseMap.put("result", "error");
            responseMap.put("message", "Proveedor no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap);
        }
    }
}
