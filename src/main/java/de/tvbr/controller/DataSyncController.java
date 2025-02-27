package de.tvbr.controller;

import de.tvbr.service.DataSyncService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class DataSyncController {

    private final DataSyncService dataSyncService;
    public DataSyncController(DataSyncService dataSyncService) {
        this.dataSyncService = dataSyncService;
    }

    @GetMapping("/sync")
    public Mono<String> triggerSync () {
        dataSyncService.syncALlData();
        return Mono.just("Veri senkronizasyonu basladi ");
    }
}
