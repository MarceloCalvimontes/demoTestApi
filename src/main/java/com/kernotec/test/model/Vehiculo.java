package com.kernotec.test.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vehiculo")

public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String marca;
    private String modelo;
    private String anio;
    private String placa;

    @Column(nullable = false)
    private boolean habilitado = true;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getAnio() {
        return anio;
    }
    public void setAnio(String anio) {
        this.anio = anio;
    }
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public boolean estaHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }
}