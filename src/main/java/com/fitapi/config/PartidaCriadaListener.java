package com.fitapi.config;

import com.fitapi.services.events.PartidaCriadaEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PartidaCriadaListener {

    private static final Logger log = LoggerFactory.getLogger(PartidaCriadaListener.class);

    @EventListener
    public void registrarPartidaCriada(PartidaCriadaEvent event) {
        log.info("Partida criada: id={}, placar={}", event.partidaId(), event.placar());
    }
}
