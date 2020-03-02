package flsh.civic.info.service.model;

import flsh.civic.info.client.dto.ElectionDto;
import io.leangen.graphql.annotations.types.GraphQLType;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@GraphQLType
@RequiredArgsConstructor
public class Election {

  String name;
  String electionDay;
  String ocdDivision;

  public Election(ElectionDto electionDto) {
    this.name = electionDto.getName();
    this.electionDay = electionDto.getElectionDay();
    this.ocdDivision = electionDto.getOcdDivisionId();
  }
}
