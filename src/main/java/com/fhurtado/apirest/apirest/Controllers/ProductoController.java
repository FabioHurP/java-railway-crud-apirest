package com.fhurtado.apirest.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fhurtado.apirest.apirest.Repositories.ProductoRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.fhurtado.apirest.apirest.Entities.Producto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;
    
    @GetMapping
    public List<Producto> getAllProduct() {
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Producto getProductById(@PathVariable Long id) {
        return productoRepository.findById(id)
        .orElseThrow( () -> new RuntimeException("Doesn't find the ID product: " + id));
    }
    

    @PostMapping
    public Producto createProduct(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    @PutMapping("/{id}")
    public Producto updateProduct(@PathVariable Long id, @RequestBody Producto productDetails) {
        Producto producto = productoRepository.findById(id)
        .orElseThrow( () -> new RuntimeException("Doesn't find the ID product: " + id));

        producto.setNombre(productDetails.getNombre());
        producto.setPrecio(productDetails.getPrecio());

        return productoRepository.save(producto);
    }
    
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        Producto producto = productoRepository.findById(id)
        .orElseThrow( () -> new RuntimeException("Doesn't find the ID product: " + id));

        productoRepository.delete(producto);
        return "The product with id: " + id + " it was delete";
    }

}


