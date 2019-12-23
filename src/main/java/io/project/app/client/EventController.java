package io.project.app.client;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/api/v2/events")
public class EventController {

    @Autowired
    private ApiCircuitBreakerService apiCircuitBreakerService;

    @CrossOrigin
    @GetMapping(path = "/event", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> get(@RequestParam Long id) {
        Optional<ImportantData> data = apiCircuitBreakerService.getEvent(id);
        if (data.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(data.get());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Could not process the request");
    }

}
