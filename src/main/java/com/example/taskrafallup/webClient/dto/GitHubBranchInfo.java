package com.example.taskrafallup.webClient.dto;

public record GitHubBranchInfo(String name,
                               Commit commit) {

    public record Commit(String sha) {

    }
}
