package com.example.taskrafallup.webClient.dto;

public record GitHubRepositoryDto(String name,
                                  String login,
                                  Owner owner) {


    public record Owner(String login) {
    }


}
