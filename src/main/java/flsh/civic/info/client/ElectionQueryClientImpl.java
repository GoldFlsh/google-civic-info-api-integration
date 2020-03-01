package flsh.civic.info.client;

import flsh.civic.info.client.dto.ElectionsDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * SEE: https://developers.google.com/civic-information/docs/v2/elections/electionQuery
 */
@Component
public class ElectionQueryClientImpl implements ElectionQueryClient {

  @Value("${API_KEY}")
  private String apiKey;

  //Cutting a corner in this case instead of adding this to an ext. configuration
  private static final String GOOGLE_API_URI = "https://www.googleapis.com/civicinfo/v2/elections?key={API_KEY}";

  final WebClient client;

  public ElectionQueryClientImpl(WebClient client) {
    this.client = client;
  }

  @Override
  public Mono<ElectionsDto> elections() {
    return client.get().
        uri(GOOGLE_API_URI, apiKey)
        .retrieve()
        .bodyToMono(ElectionsDto.class);
  }
}
