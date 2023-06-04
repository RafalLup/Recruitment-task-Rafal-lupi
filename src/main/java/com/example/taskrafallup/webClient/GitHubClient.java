package com.example.taskrafallup.webClient;

import com.example.taskrafallup.exception.GitHubNameNotFoundException;
import com.example.taskrafallup.exception.StringHolder;
import com.example.taskrafallup.mapper.GitHubInfoMapper;
import com.example.taskrafallup.model.RepositoryDTO;
import com.example.taskrafallup.webClient.dto.GitHubBranchInfo;
import com.example.taskrafallup.webClient.dto.GitHubRepositoryDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class GitHubClient {

    private final WebClient webClient;

    public GitHubClient() {
        this.webClient = WebClient.create(StringHolder.EXTERNAL_API_BASE_URL);
    }
    public List<RepositoryDTO> getRepositoryInfoByName(final String name) {
        final List<RepositoryDTO> repositories = webClient.get()
                .uri(StringHolder.USER_REPOS_ENDPOINT, name)
                .header("Content-Type", "application/json")
                .retrieve()
                .onStatus(httpStatusCode -> httpStatusCode.equals(HttpStatus.NOT_FOUND),
                        response -> Mono.error(new GitHubNameNotFoundException(StringHolder.USERNAME_NOT_FOUND + name)))
                .bodyToFlux(GitHubRepositoryDto.class)
                .flatMap(repository -> getBranchInfo(name, repository.name())
                        .map(branchInfo -> {
                            final String sha = branchInfo.commit() != null ? branchInfo.commit().sha() : null;
                            return GitHubInfoMapper.toResponse(
                                    repository.name(),
                                    repository.owner().login(),
                                    branchInfo.name(),
                                    sha
                            );
                        }))
                .collectList()
                .block();
        return repositories;
    }
    private Mono<GitHubBranchInfo> getBranchInfo(final String name, final String repositoryName) {
        return webClient.get()
                .uri(StringHolder.BRANCHES_ENDPOINT, name, repositoryName)
                .retrieve()
                .bodyToFlux(GitHubBranchInfo.class)
                .next();
    }

}