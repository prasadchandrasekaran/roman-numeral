package org.adobe.interview.romannumeral.config.actuator.health;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

import java.util.Optional;

@RequiredArgsConstructor
public class HealthClientAdapter implements HealthIndicator {
    private final HealthClient healthClient;

    @Override
    public Health health() {
        try {
            return Optional.ofNullable(healthClient.health())
                .map(HealthResponse::toHealth)
                .orElse(Health.down()
                    .withDetail("error", "null response")
                    .build()
                );
        }
        catch (Exception exception) {
            return Health.down(exception).build();
        }
    }
}
