package com.aug.ecommerce.application.listener;

import com.aug.ecommerce.application.event.OrdenCreadaEvent;
import com.aug.ecommerce.application.service.ClienteValidacionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClienteEventListener {

    private final ClienteValidacionService clienteValidacionService;

    @EventListener
    public void validarCliente(OrdenCreadaEvent event) throws Exception {
        log.debug("---> Entrando al Listener ValidarClienteListener - OrdenCreadaEvent {}", event.getOrdenId());
        clienteValidacionService.validarClienteCreacionOrden(event.getOrdenId(), event.getClienteId());
    }
}
