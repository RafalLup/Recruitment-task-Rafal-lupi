package com.example.taskrafallup;

import com.example.taskrafallup.exception.GitHubNameNotFoundException;
import com.example.taskrafallup.model.RepositoryDTO;
import com.example.taskrafallup.webClient.GitHubClient;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GitHubClientTest {

    private static WireMockServer wireMockServer;

    @BeforeClass
    public static void setup() {
        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().dynamicPort());
        wireMockServer.start();
        WireMock.configureFor(wireMockServer.port());

    }
    @AfterClass
    public static void teardown() {
        wireMockServer.stop();
    }

    @Test
    public void testGetRepositoryInfoByName() {
       final GitHubClient gitHubClient = new GitHubClient();

       final List<RepositoryDTO> repositories = gitHubClient.getRepositoryInfoByName("rafalLup");


        assertNotNull(repositories);
        assertEquals(2, repositories.size());
        assertTrue(repositories.get(0).ownerLogin().equals("RafalLup"));
    }

    @Test
    public void testGetRepositoryInfoByName_NotFound() {
        final GitHubClient gitHubClient = new GitHubClient();

        assertThrows(GitHubNameNotFoundException.class, () -> {
            gitHubClient.getRepositoryInfoByName("nonexistentUser");
        });
    }
}