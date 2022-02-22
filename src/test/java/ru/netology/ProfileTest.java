package ru.netology;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.netology.profile.DevProfile;
import ru.netology.profile.ProductionProfile;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers(disabledWithoutDocker = true)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class ProfileTest {
    private static final String devContainerName = "profile:dev";
    private static final String prodContainerName = "profile:prod";

    private static final int devContainerPort = 8080;
    private static final int prodContainerPort = 8081;

    @Container
    private static final GenericContainer<?> devContainer = new GenericContainer<>(devContainerName)
            .withExposedPorts(devContainerPort);
    @Container
    private static final GenericContainer<?> prodContainer = new GenericContainer<>(prodContainerName)
            .withExposedPorts(prodContainerPort);

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeAll
    public static void setUp() {
        devContainer.start();
        prodContainer.start();
    }

    @Test
    public void testDevProfile() {
        ResponseEntity<String> response = restTemplate.getForEntity(
                "http://localhost:" + devContainer.getMappedPort(devContainerPort),
                String.class
                );
        assertEquals(response.getBody(), DevProfile.class.getSimpleName());
    }

    @Test
    public void testProdProfile() {
        ResponseEntity<String> response = restTemplate.getForEntity(
                "http://localhost:" + prodContainer.getMappedPort(prodContainerPort),
                String.class
        );
        assertEquals(response.getBody(), ProductionProfile.class.getSimpleName());
    }
}
