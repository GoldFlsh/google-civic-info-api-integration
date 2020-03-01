package flsh.civic.info.client.dto;

import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ElectionsDto {
  private final String kind;
  private final List<ElectionDto> elections;
}
