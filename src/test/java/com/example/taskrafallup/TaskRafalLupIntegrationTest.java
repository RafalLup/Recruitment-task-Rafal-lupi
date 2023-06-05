package com.example.taskrafallup;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.github.tomakehurst.wiremock.client.WireMock.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskRafalLupIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient webTestClient;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(WireMockConfiguration.wireMockConfig().dynamicPort());

    @Before
    public void setup() {
        webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:" + port).build();
        WireMock.configureFor(wireMockRule.port());
    }

    @Test
    public void testGetInfo_NotAcceptable() {
        wireMockRule.stubFor(get(urlPathMatching("/users/" + TestDataProvider.USERNAME + "/repos"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/xml")
                        .withBody("Invalid response")));

        webTestClient.get()
                .uri("/git?name=" + TestDataProvider.USERNAME)
                .accept(MediaType.APPLICATION_XML)
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody()
                .jsonPath("$.status").isEqualTo(406)
                .jsonPath("$.message").isEqualTo("No acceptable response");
    }

    @Test
    public void testGetInfo_GitHubNameNotFoundException() {

        wireMockRule.stubFor(get(urlPathMatching("/users/" + TestDataProvider.NONEXISTENT_USERNAME + "/repos"))
                .willReturn(aResponse()
                        .withStatus(404)));

        webTestClient.get()
                .uri("/git?name=" + TestDataProvider.NONEXISTENT_USERNAME)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath("$.status").isEqualTo(404)
                .jsonPath("$.message").isEqualTo("username not found " + TestDataProvider.NONEXISTENT_USERNAME);
    }
}
