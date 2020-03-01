package flsh.civic.info.client.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ElectionDto {
  private final Long id;
  private final String name;
  private final String electionDay;
  private final String ocdDivisionId;
}