package in.dragonbra.bptfserver.config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lngtr
 * @since 7/16/2017
 */
@Configuration
public class CommonConfig {

    @Bean
    public Gson gson() {
        return new Gson();
    }

}
