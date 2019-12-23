/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.project.app.client;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

/**
 *
 * @author armena
 */
@Slf4j
@Service
public class ApiCircuitBreakerService {

    @Autowired
    private ApiCallerService apiCallerService;


    @Retryable(RemoteAccessException.class)
    public Optional<ImportantData> getEvent(Long id) {
        Optional<ImportantData> fetchData = apiCallerService.fetchData(id);

        if (!fetchData.isPresent()) {
            log.error("Data empty from server");
            return Optional.empty();
        }
        return fetchData;
    }

    @Recover
    public Optional<ImportantData> getEventFailBack(Long id, RemoteAccessException throwable) {
        log.error("ENTER TO ERROR ZONE");
        if (throwable instanceof RemoteAccessException) {
            //log.info("{}", throwable.getMessage());
        }
        log.error("Return empty data");
        return Optional.empty();
    }

}
