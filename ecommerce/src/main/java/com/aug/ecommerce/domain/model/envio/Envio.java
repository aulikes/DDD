package com.aug.ecommerce.domain.model.envio;

import java.util.Objects;

public class Envio {

    private final Long id;
    private final Long ordenId;
    private final String direccionEnvio;
    private EstadoEnvio estado;
    private String trackingNumber;

    public static Envio create(Long ordenId, String direccionEnvio) {
        return new Envio(null, ordenId, direccionEnvio, EstadoEnvio.PENDIENTE, null);
    }

    public static Envio fromPersistence(Long id, Long ordenId, String direccionEnvio,
                                        EstadoEnvio estado, String trackingNumber) {
        if (id == null) throw new IllegalArgumentException("El id no puede ser nulo");
        return new Envio(id, ordenId, direccionEnvio, estado, trackingNumber);
    }

    private Envio(Long id, Long ordenId, String direccionEnvio, EstadoEnvio estado, String trackingNumber) {
        this.id = id;
        this.ordenId = Objects.requireNonNull(ordenId, "La orden no puede ser nula");
        this.direccionEnvio = Objects.requireNonNull(direccionEnvio, "La dirección no puede ser nula");
        this.estado = estado;
        this.trackingNumber = trackingNumber;
    }

    public Long getId() { return id; }
    public Long getOrdenId() { return ordenId; }
    public String getDireccionEnvio() { return direccionEnvio; }
    public EstadoEnvio getEstado() { return estado; }
    public String getTrackingNumber() { return trackingNumber; }

    //Se cambia el estado cuando el proveeddor de envíos recibió la petición
    public void prepararEnvio(String trackingNumber) {
        if (estado != EstadoEnvio.PENDIENTE)
            throw new IllegalStateException("Solo puede preparar un envío que está pendiente");
        this.trackingNumber = Objects.requireNonNull(trackingNumber, "El número de seguimiento no puede ser nulo");
        this.estado = EstadoEnvio.PREPARANDO;
    }

    //Solo se pueden cambiar los estados desde el proveedor de envios si es Preparando o Despachando
    public void actualizarEstadoFromSupplier(EstadoEnvio estadoEnvio) {
        if (estado == EstadoEnvio.PREPARANDO || estado == EstadoEnvio.DESPACHADO)
            throw new IllegalStateException("Solo se puede entregar un envío despachado");
        this.estado = estadoEnvio;
    }

}
