package flsh.civic.info.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElectionDto {
  private Long id;
  private String name;
  private String electionDay;
  private String ocdDivisionId;
}