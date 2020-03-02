package flsh.civic.info.client.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ElectionDto {
  Long id;
  String name;
  String electionDay;
  String ocdDivisionId;
}