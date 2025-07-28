package com.kernotec.test.controller;

import com.kernotec.test.model.Vehiculo;
import com.kernotec.test.repository.VehiculoRepository;
import com.kernotec.test.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoRepository vehiculoRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    //listar todos los vehiculos
    @GetMapping
    public List<Vehiculo> obtenerTodosLosVehiculos(){
        return vehiculoRepository.findAll();
    }

    //listar vehiculos por estado y numero de documento del cliente
    @PostMapping("/filtrar")
    public List<Vehiculo> filtrarVehiculosPorEstadoYDocumento(@RequestBody Map<String, Object> body) {
        Object estadoObj = body.get("habilitado");
        Object documentoObj = body.get("numeroDocumento");

        // Si no hay filtro de estado ni documento, retorna todos los vehiculos y clientes
        if (estadoObj == null && documentoObj == null) {
            return vehiculoRepository.findAll();
        }

        Boolean habilitado = null;
        if (estadoObj != null) {
            habilitado = Integer.parseInt(estadoObj.toString()) == 1;
        }

        // Buscar por número de documento si está en el body
        if (documentoObj != null) {
            String documento = documentoObj.toString();
            if (habilitado != null) {
                return vehiculoRepository.findByClienteNumeroDocumentoAndHabilitado(documento, habilitado);
            } else {
                return vehiculoRepository.findByClienteNumeroDocumento(documento);
            }
        }

        // Si no se envió el número de documento, solo filtra por estado
        return vehiculoRepository.findByHabilitado(habilitado);
    }

    //vehiculos por cliente
    @GetMapping("/cliente/{clienteId}")
    public List<Vehiculo> obtenerVehiculosPorCliente(@PathVariable Long clienteId) {
        return vehiculoRepository.findByClienteId(clienteId);
    }

    //registrar vehiculo
    @PostMapping
    public Vehiculo createVehiculo(@RequestBody Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    //actualizar
    @PutMapping("/{id}")
    public Vehiculo actualizarVehiculo(@PathVariable Long id, @RequestBody Vehiculo detallesVehiculo){
        Vehiculo vehiculo = vehiculoRepository.findById(id).orElseThrow();
        vehiculo.setMarca(detallesVehiculo.getMarca());
        vehiculo.setModelo(detallesVehiculo.getModelo());
        vehiculo.setAnio(detallesVehiculo.getAnio());
        vehiculo.setPlaca(detallesVehiculo.getPlaca());
        vehiculo.setCliente(detallesVehiculo.getCliente());
        return vehiculoRepository.save(vehiculo);
    }

    //cambiar estado
    @PutMapping("/{id}/estado/toggle")
    public String alternarEstadoVehiculo(@PathVariable Long id) {
        Vehiculo vehiculo = vehiculoRepository.findById(id).orElse(null);

        if (vehiculo == null) {
            return "Vehículo no encontrado";
        }

        vehiculo.setHabilitado(!vehiculo.estaHabilitado());
        vehiculoRepository.save(vehiculo);

        return "Nuevo estado del vehículo: " + (vehiculo.estaHabilitado() ? "Habilitado" : "Inhabilitado");
    }

    //Eliminar
    @DeleteMapping("/{id}")
    public void deleteVehiculo(@PathVariable Long id){
        vehiculoRepository.deleteById(id);
    }
}