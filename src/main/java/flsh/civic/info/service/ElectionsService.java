package flsh.civic.info.service;

import flsh.civic.info.client.ElectionQueryClient;
import flsh.civic.info.client.dto.ElectionsDto;
import flsh.civic.info.service.model.Election;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@GraphQLApi
public class ElectionsService {

  private final ElectionQueryClient client;

  public ElectionsService(ElectionQueryClient client) {
    this.client = client;
  }

  @GraphQLQuery
  public Flux<Election> getElections() {
    return client.elections()
        .flatMapIterable(ElectionsDto::getElections)
        .map(Election::fromElectionDto);
  }
}
