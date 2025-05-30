package com.aug.ecommerce.application.event;

import java.time.Instant;

public record PagoConfirmadoEvent(
        Long ordenId, Long pagoId, Instant fecha, boolean exitoso,
        String codigoTransaccion, String mensajeError) {}