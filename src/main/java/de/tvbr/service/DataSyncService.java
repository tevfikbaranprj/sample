package de.tvbr.service;


import de.tvbr.entities.api1.Api1Data;
import de.tvbr.entities.api2.Api2Data;
import de.tvbr.entities.api3.Api3Data;
import de.tvbr.entities.repositories.api1.Api1Repository;
import de.tvbr.entities.repositories.api2.Api2Repository;
import de.tvbr.entities.repositories.api3.Api3Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class DataSyncService {

    private final WebClient webClient;
    private final Api1Repository api1Repo;
    private final Api2Repository api2Repo;
    private final Api3Repository api3Repo;

    public DataSyncService(Api1Repository api1Repo, Api2Repository api2Repo, Api3Repository api3Repo) {
        this.webClient = WebClient.create();
        this.api1Repo = api1Repo;
        this.api2Repo = api2Repo;
        this.api3Repo = api3Repo;
    }

    public void syncALlData() {
        Mono<Api1Data> api1Data = fetchApi1Data();
        Mono<Api2Data> api2Data = fetchApi2Data();
        Mono<Api3Data> api3Data = fetchApi3Data();

        // Tüm istekleri paralel çalıştır ve sonuçları birleştir
        Flux.merge(api1Data,api2Data, api3Data)
                .parallel()
                .runOn(Schedulers.boundedElastic())
                .doOnNext(data -> {
                    if (data instanceof Api1Data) api1Repo.save((Api1Data) data);
                    else if (data instanceof Api2Data) api2Repo.save((Api2Data) data);
                    else if (data instanceof Api3Data) api3Repo.save((Api3Data) data);
                })
                .sequential()
                .subscribe(
                        success -> System.out.println("Kayıt başarılı: " + success),
                        error -> System.err.println("Hata oluştu: " + error),
                        () -> System.out.println("Senkronizasyon tamamlandı!")
                );
    }

    private Mono<Api1Data> fetchApi1Data() {
        return webClient.get()
                .uri("https://api1.com/data")
                .retrieve()
                .bodyToMono(Api1Data.class)
                .onErrorResume(e -> {
                    System.err.println("API1 Error: " + e.getMessage());
                    return Mono.empty();
                });
    }

    private Mono<Api2Data> fetchApi2Data() {
        return webClient.get()
                .uri("https://api2.com/data")
                .retrieve()
                .bodyToMono(Api2Data.class)
                .onErrorResume(e -> {
                    System.err.println("API2 Error: " + e.getMessage());
                    return Mono.empty();
                });
    }

    private Mono<Api3Data> fetchApi3Data() {
        return webClient.get()
                .uri("https://api2.com/data")
                .retrieve()
                .bodyToMono(Api3Data.class)
                .onErrorResume(e -> {
                    System.err.println("API3 Error: " + e.getMessage());
                    return Mono.empty();
                });
    }
}
