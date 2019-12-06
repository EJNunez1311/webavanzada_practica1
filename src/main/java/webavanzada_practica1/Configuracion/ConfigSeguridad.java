package webavanzada_practica1.Configuracion;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import webavanzada_practica1.Servicios.SeguridadService;

@Configurable
@EnableGlobalMethodSecurity (securedEnabled = true) //Implementando SpringSecurity
public class ConfigSeguridad extends WebSecurityConfigurerAdapter {

    //Configuracion JPA para implementar Servicio Usuario
    @Autowired
    private SeguridadService seguridadService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //Encriptando password
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    //Permite la carga de usurio en memoria
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {


        //Conf para cargar usuario JPA asi agrego en BD
        auth
                .userDetailsService(seguridadService)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    //Permisos a usuarios que puede acceder y que no
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()

                //Cualquiera puede ver
                .antMatchers("/","/css/**", "/js/**").permitAll()
                .antMatchers("/dbconsole/**").permitAll()

                // A que ruta accede cada rol
                .antMatchers("/usuario/**").hasAnyRole("ADMIN")
                .antMatchers("/cliente/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/equipo/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/familia/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/alquiler/**").hasAnyRole("ADMIN", "USER")
                // .anyRequest().authenticated() //cualquier llamada debe ser validada
                .and()
                .formLogin()
                .loginPage("/login") //estableciend ruta del loggin
                // .failureUrl("/login?error")

                .permitAll()
                .and()
                .logout()
                .permitAll();

        // Es necesario deshabilitar seguridad de los frame int para H2.
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

}
