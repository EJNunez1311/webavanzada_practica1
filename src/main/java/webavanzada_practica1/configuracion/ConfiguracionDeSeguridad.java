package webavanzada_practica1.configuracion;

import webavanzada_practica1.servicios.SeguridadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configurable
@EnableGlobalMethodSecurity(securedEnabled = true) // Implementando springsecurity
public class ConfiguracionDeSeguridad extends WebSecurityConfigurerAdapter { // esta clase nos exige sobresscribir un metodo, que sera el metodo donde se especificara el usuario

    //Configuracion para jpa, implementar el servicio usuario para trabajar con el user details service
    @Autowired
    private SeguridadService seguridadService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //Encriptando contraseña
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
    // Carga de usuario a memoria, es parecido a cuando se crea el usuario en aplication properties
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //Configuracion y carga de usuarios metodo JPA agregando nuestro usuario a BD
        auth
                .userDetailsService(seguridadService)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    //Reglas para especificar las rutas a las que podra acceder los usuarios

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()

                //Cualquiera podra entrar
                .antMatchers("/","/css/**", "/js/**").permitAll()
                .antMatchers("/dbconsole/**").permitAll()

                // Rol admin y user podran entrar
                .antMatchers("/usuario/**").hasAnyRole("ADMIN")
                .antMatchers("/cliente/**").hasAnyRole("ADMIN", "EMPLEADO")
                .antMatchers("/plan/**").hasAnyRole("ADMIN", "EMPLEADO", "CLIENT")
               // .anyRequest().authenticated() //cualquier llamada debe ser validada
                .and()
                .formLogin()
                .loginPage("/login") //indicando que usario mi propio login no el que viene por defecto.
                //.failureUrl("/login?error") //en caso de fallar puedo indicar otra pagina, esta url la utilizare para
                // indicar cuando hay algun error en la aplicacion y mandare la pagina que aqui designe
                .permitAll()
                .and()
                .logout()
                .permitAll();

        //deshabilitando las seguridad contra los frame internos.
        //Necesario para H2.
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}
