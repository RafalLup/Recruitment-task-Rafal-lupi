package com.example.taskrafallup.service;


import com.example.taskrafallup.model.RepositoryDTO;
import com.example.taskrafallup.webClient.GitHubClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GitHubService {

    private final GitHubClient gitHubClient;

    public GitHubService(GitHubClient gitHubClient) {
        this.gitHubClient = gitHubClient;
    }

    public List<RepositoryDTO> getRepositoryInfo(final String name) {
        return gitHubClient.getRepositoryInfoByName(name);
    }


}
