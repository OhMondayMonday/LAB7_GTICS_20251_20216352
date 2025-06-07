package com.example.lab7_gtics_20251_20216352.controller;

import com.example.lab7_gtics_20251_20216352.entity.proveedor;
import com.example.lab7_gtics_20251_20216352.repository.proveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
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
    public List<proveedor> listarProveedores (){
        return proveedorRepository.findAll();
    }

    @GetMapping("/proveedor/{id}")
    public ResponseEntity<HashMap<String, Object>> getProveedorById(@PathVariable("id") Integer id){

        HashMap<String, Object> responseJson = new HashMap<>();
        try {
             Optional<proveedor> optProveedor = proveedorRepository.findById(id);
             if(optProveedor.isPresent()){
                 responseJson.put("result", "success");
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
}
