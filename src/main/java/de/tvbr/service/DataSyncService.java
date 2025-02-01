package de.tvbr.service;


import de.tvbr.entities.repositories.api1.Api1Repository;
import de.tvbr.entities.repositories.api2.Api2Repository;
import de.tvbr.entities.repositories.api3.Api3Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class DataSyncService {

    private final WebClient webClient;
    private final Api1Repository api1Repo;
    private final Api2Repository api2Repo;
    private final Api3Repository api3Repo;

    public DataSyncService(Api1Repository api1Repo, Api2Repository api2Repo, Api3Repository api3Repo) {
        this.api1Repo = api1Repo;
        this.api2Repo = api2Repo;
        this.api3Repo = api3Repo;
    }
}
