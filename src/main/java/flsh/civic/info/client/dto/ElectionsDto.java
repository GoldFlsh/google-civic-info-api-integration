package flsh.civic.info.client.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElectionsDto {
  private String kind;
  private List<ElectionDto> elections;
}
