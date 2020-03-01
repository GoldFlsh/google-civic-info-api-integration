package flsh.civic.info.service;

import static org.junit.jupiter.api.Assertions.*;

import flsh.civic.info.client.ElectionQueryClient;
import flsh.civic.info.client.dto.ElectionDto;
import flsh.civic.info.client.dto.ElectionsDto;
import flsh.civic.info.service.model.Election;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

class ElectionsServiceTest {

  private ElectionQueryClient electionQueryClient = () -> {
    final ElectionDto electionDto = ElectionDto.builder()
        .id(1000L)
        .electionDay("election-day")
        .ocdDivisionId("ocd-division-id")
        .name("name").build();

    final ElectionsDto electionsDto = ElectionsDto.builder()
        .kind("kind")
        .elections(Collections.singletonList(electionDto)).build();
    return Mono.just(electionsDto);
  };

  ElectionsService electionsService = new ElectionsService(electionQueryClient);

  @Test
  void testElectionServiceReturnsTransformedClientResponse() {
    final Flux<Election> elections = electionsService.getElections();
    assertEquals(elections.blockFirst(),
        new Election("name", "election-day", "ocd-division-id"));
  }
}
