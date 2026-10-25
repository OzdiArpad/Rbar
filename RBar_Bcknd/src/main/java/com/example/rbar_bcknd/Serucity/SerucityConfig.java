package com.example.rbar_bcknd.Serucity;
import com.example.rbar_bcknd.SuccessHandler.OAuth2LoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration /* - Rögtön a program elindulásakor betöltődnek a memóriába. */
@EnableWebSecurity /* Spring security engedélyezése. */
public class SerucityConfig {
    private final OAuth2LoginSuccessHandler successHandler;

    public SerucityConfig(OAuth2LoginSuccessHandler successHandler){this.successHandler=successHandler;}

    @Bean /* Háttérben előkészített backend szolgáltatás ami a memóriába töltödik be a program
    futtatásakor. */
    /* A SecurityFilterChain egy összeépített szürőláncolat amelyen http ellenőrzés átvizsgál */
    /* httpsecurity pedig maga a konfiguráció*/
    /* röviden talán a SecFilterChain a lehetőségek a szabályozásra,
    míg a HttpSec a szabályozott jellemző.
     */


    //Identity providers.
    //Docker - keycloak for rules.
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
    http /*Módosítjuk ugye*/
            .csrf(csrf -> csrf.disable())
            /*.csrf elvileg egyedi azonosításokkor használjuk pl jwt*/
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            /*A cors, betudható egy szabályzat listának, amely különböző metódusokat
            * pl get-post vagy más reactek vagy vuek kérést küldjenek a backendnek
            * talán ez a szíve lelke a fő konfigurációnak*/
            .authorizeHttpRequests(auth->auth
                    .requestMatchers("/**").permitAll() //Bejelentkezetlen felhasználói hozzáférések.
                    .anyRequest().authenticated() //Bejelentkezett felhasználók hatóköre. Ide jöhet az időpont foglalás.
            )
            .oauth2Login(s ->s
                    .successHandler(successHandler));
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration config=new CorsConfiguration(); //Új cors-szabály létrehozása.
        config.setAllowedOrigins(List.of("http://localhost:4200")); //Honnan fogadjuk a http kéréseket talán.
        config.setAllowedMethods(List.of("GET", "POST", "PUT")); //Mely mapping metódusokat nem tiltjuk le.
        config.setAllowCredentials(true); //Felhasználói azonosítás elfogadott.
        config.setAllowedHeaders(List.of("*")); //Bármilyen fejlécet elküldhet a frontend, ennek a pontos
        //módosítása akkor lenne fontos ha egyedi fejlécekkel dolgoznék.


        //címtár/kezelő létrehozása
        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        /*Majd a cors-t elfogadtatjuk ezzel az összes http kérésre. Talán úgy nézne ki külön-külön
        * hogy "/login",config és akkor egyre lenne érvényes*/
        return source;
    }
}
