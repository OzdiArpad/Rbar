package com.example.rbar_bcknd.SuccessHandler;

import com.example.rbar_bcknd.Service.UserHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Component
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final UserHandler userHandler;
    private final OAuth2AuthorizedClientRepository authorizedClientRepository;
    public OAuth2LoginSuccessHandler(UserHandler userHandler, OAuth2AuthorizedClientRepository authorizedClientRepository){
        this.userHandler=userHandler;
        this.authorizedClientRepository=authorizedClientRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException{
        OAuth2AuthenticationToken oauthToken=(OAuth2AuthenticationToken) authentication;
        OAuth2User oAuthUser=oauthToken.getPrincipal();

        String sub = oAuthUser.getAttribute("sub");
        String email = oAuthUser.getAttribute("email");
        String fullName = oAuthUser.getAttribute("name");

        //--------------------------------------------------------------------------------------------------------------
        /* Szülinap kinyerése google People API hozzáadásával */

        //Token megszerzése.
        OAuth2AuthorizedClient authorizedClient = authorizedClientRepository.loadAuthorizedClient(
                oauthToken.getAuthorizedClientRegistrationId(),
                authentication,
                request);

        if(userHandler.UserCheck(sub))
        {
            response.sendRedirect("http://localhost:4200/");
        }

        LocalDate birthday = null;

        if (authorizedClient != null) {
            //Tiszta string megszerzése.
            String accessToken = authorizedClient.getAccessToken().getTokenValue();
            try {
                //http kérés létrehozása csak head-el
                RestTemplate restTemplate = new RestTemplate();
                HttpHeaders headers = new HttpHeaders();
                headers.setBearerAuth(accessToken);
                HttpEntity<Void> entity = new HttpEntity<>(headers);

                //Mit akarunk lekérni.
                String url = "https://people.googleapis.com/v1/people/me?personFields=birthdays";

                //becsomagolás és elküldés
                ResponseEntity<Map> peopleResponse = restTemplate.exchange(
                        url, HttpMethod.GET, entity, Map.class);

                //Válasz lekérése a body-ból.
                List<Map<String, Object>> birthdays = (List<Map<String, Object>>)
                        peopleResponse.getBody().get("birthdays");

                if (birthdays != null) {
                    for (Map<String, Object> b : birthdays) {
                        Map<String, Object> date = (Map<String, Object>) b.get("date");
                        if (date != null
                                && date.containsKey("year")
                                && date.containsKey("month")
                                && date.containsKey("day")) {

                            birthday = LocalDate.of(((Number) date.get("year")).intValue(), ((Number) date.get("month")).intValue(), ((Number) date.get("day")).intValue());
                            break;
                        }
                    }
                }
            } catch (Exception e) {
            }
        }

        userHandler.UserCreateInDB(sub, email,fullName,birthday);

        response.sendRedirect("http://localhost:4200/");
    }
}
