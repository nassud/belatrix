package co.com.belatrix.moneyxchange.conf;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 *
 * @author sebas
 */
@EnableWebSecurity
public class MultiHttpSecurityConfig {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        try {
            auth.inMemoryAuthentication()
                    .withUser("user").password("{noop}pass").roles("USER");
        } catch (Exception ex) {
            Logger.getLogger(MultiHttpSecurityConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
