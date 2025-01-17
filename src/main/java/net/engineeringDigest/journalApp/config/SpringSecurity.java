    package net.engineeringDigest.journalApp.config;

    import net.engineeringDigest.journalApp.service.UserDetailsServiceImpl;
    import net.engineeringDigest.journalApp.service.UserService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.config.Customizer;
    import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.security.web.SecurityFilterChain;
    import org.springframework.web.servlet.config.annotation.CorsRegistry;
    import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

    @Configuration
    @EnableWebSecurity
        public class SpringSecurity {
            @Autowired
            private UserDetailsServiceImpl userDetailsService;

            // A configuration SecurityConfig to integrate everything with spring security
            @Bean
            protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
                return http
                        .csrf(csrf -> csrf.disable())
                       // .cors(obj->obj.disable())
                        .authorizeHttpRequests(requests -> requests
                                .requestMatchers("/journal/**","/user/**").authenticated()
                                .requestMatchers("/admin/**","/create-admin-user/**").hasRole("ADMIN")
                                .anyRequest().permitAll()

                        )
                        .httpBasic(Customizer.withDefaults())
                        .formLogin(Customizer.withDefaults())
                        .build();

            }

            //Authentication mananger is class , creating instance  so we can use methods of class
            //here we are checking db password with encoded password by using this class
            // while first time user creation passsword is Bcrypted then save
            //both login and db user password and username check by using this method
            @Bean
            public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
                AuthenticationManagerBuilder authenticationManagerBuilder =
                        http.getSharedObject(AuthenticationManagerBuilder.class);

                authenticationManagerBuilder
                            .userDetailsService(userDetailsService)
                        .passwordEncoder(passwordEncoder());  // Password encoder

                return authenticationManagerBuilder.build();
            }


            //this method is used to encyrpt password and return some string which is same as password of db
            //bcrypt is website there i can check how string is converting to hashing string
            //
            @Bean
            public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
            }


        }

