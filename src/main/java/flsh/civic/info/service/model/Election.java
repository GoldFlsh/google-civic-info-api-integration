package flsh.civic.info.service.model;

import flsh.civic.info.client.dto.ElectionDto;
import io.leangen.graphql.annotations.types.GraphQLType;
import lombok.Data;

@Data
@GraphQLType
public class Election {

  private final String name;
  private final String electionDay;
  private final String ocdDivision;

  public Election(ElectionDto electionDto) {
    this.name = electionDto.getName();
    this.electionDay = electionDto.getElectionDay();
    this.ocdDivision = electionDto.getOcdDivisionId();
  }
}
