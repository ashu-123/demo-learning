package com.learning.demo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.logout.ServerLogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.function.Predicate;

@Component
public class KeycloakLogoutHandler implements ServerLogoutHandler {
    private static final Logger logger = LoggerFactory.getLogger(KeycloakLogoutHandler.class);

    @Override
    public Mono<Void> logout(WebFilterExchange exchange, Authentication auth) {
        return logoutFromKeycloak((OidcUser) auth.getPrincipal());
    }

    private Mono<Void> logoutFromKeycloak(OidcUser user) {
        return WebClient.create().get()
                   .uri(uriBuilder -> uriBuilder.path(user.getIssuer() + "/protocol/openid-connect/logout")
                                          .queryParam("id_token_hint", user.getIdToken().getTokenValue())
                                          .build())
                   .retrieve()
                   .onStatus(Predicate.not(HttpStatusCode::is2xxSuccessful), c -> Mono.error(new RuntimeException()))
                   .bodyToMono(Void.class)
                   .doOnNext(res -> logger.info("Successfully logged out from Keycloak"))
                   .doOnError(err -> logger.error("Could not propagate logout to Keycloak"));
    }
}