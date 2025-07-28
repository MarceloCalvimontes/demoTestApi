package com.kernotec.test.controller;

import com.kernotec.test.model.Cliente;
import com.kernotec.test.repository.ClienteRepository;
import com.kernotec.test.repository.VehiculoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Map;

@RestController
@RequestMapping("/api/clientes")

public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    //Obtener todos los clientes
    @GetMapping
    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }

    //listar clientes habilitados / inhabilitados
    @PostMapping("/filtrar")
    public List<Cliente> filtrarClientesPorEstado(@RequestBody Map<String, Object> body) {
        Object estado = body.get("habilitado");

        // Si no se envía el estado, retorna todos
        if (estado == null) {
            return clienteRepository.findAll();
        }

        boolean habilitado = Integer.parseInt(estado.toString()) == 1;
        return clienteRepository.findByHabilitado(habilitado);
    }

    //obtener cliente por ID
    @GetMapping("/{id}")
    public Optional<Cliente> getClienteById(@PathVariable Long id) {
        return clienteRepository.findById(id);
    }

    //crear cliente
    @PostMapping
    public Cliente createCliente(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    //actualizar
    @PutMapping("/{id}")
    public Cliente actualizarCliente(@PathVariable Long id, @RequestBody Cliente detallesCliente) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow();
        cliente.setNombre(detallesCliente.getNombre());
        cliente.setPaterno(detallesCliente.getPaterno());
        cliente.setMaterno(detallesCliente.getMaterno());
        cliente.setFechaNacimiento(detallesCliente.getFechaNacimiento());
        cliente.setTipoDocumento(detallesCliente.getTipoDocumento());
        cliente.setNumeroDocumento(detallesCliente.getNumeroDocumento());
        cliente.setFechaNacimiento(detallesCliente.getFechaNacimiento());
        cliente.setGenero(detallesCliente.getGenero());
        return clienteRepository.save(cliente);
    }

    @PutMapping("/{id}/estado/toggle")
    public String alternarEstadoCliente(@PathVariable Long id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);

        if (cliente == null) {
            return "Cliente no encontrado";
        }

        cliente.setHabilitado(!cliente.estaHabilitado());
        clienteRepository.save(cliente);

        return "Nuevo estado del cliente: " + (cliente.estaHabilitado() ? "Habilitado" : "Inhabilitado");
    }

    //eliminar
    @DeleteMapping("/{id}")
    @Transactional
    public String eliminarCliente(@PathVariable Long id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);

        if (cliente == null) {
            return "Cliente no encontrado";
        }

        vehiculoRepository.deleteByClienteId(id);
        clienteRepository.delete(cliente);

        return "Cliente y sus vehículos eliminados correctamente";
    }

}
