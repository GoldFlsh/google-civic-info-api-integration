package flsh.civic.info.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

  @Bean
  public WebClient webClient(ObjectMapper objectMapper) {
    return WebClient.builder()
        .exchangeStrategies(ExchangeStrategies.builder()
            .codecs(config -> {
              config.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(objectMapper));
              config.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(objectMapper));
            }).build())
        .build();
  }

}
