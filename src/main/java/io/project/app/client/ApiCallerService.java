/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.project.app.client;

import io.vavr.control.Try;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author armena
 */
@Slf4j
@Service
public class ApiCallerService {

    @Autowired
    private RestTemplate restTemplate;

    @Retryable(
            value = {Exception.class},
            maxAttempts = 4,
            backoff = @Backoff(delay = 2000))
    public Optional<ImportantData> fetchData(Long id) {

        String url = "http://localhost:9999/api/v2/info/important?id=" + id;

        log.info("URL: TRY " + url);

        Try<ImportantData> result = Try.of(() -> this.restTemplate.getForObject(url, ImportantData.class));

        if (!result.isSuccess()) {

            log.error("Errors ? , if error will be called again");

        }

        if (result.isSuccess()) {
            log.info("Success");
        }

        return Optional.ofNullable(result.get());
    }

}
