package flsh.civic.info.client;

import flsh.civic.info.client.dto.ElectionsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * SEE: https://developers.google.com/civic-information/docs/v2/elections/electionQuery
 */
@Component
public class ElectionQueryClientImpl implements ElectionQueryClient {

  @Value("${application.election.api.key}")
  private String apiKey;

  @Value("${application.election.uri}")
  private String electionQueryUri;

  final WebClient client;

  @Autowired
  public ElectionQueryClientImpl(WebClient client) {
    this.client = client;
  }

  @Override
  public Mono<ElectionsDto> elections() {
    return client.get().
        uri(electionQueryUri, apiKey)
        .retrieve()
        .bodyToMono(ElectionsDto.class);
  }
}
