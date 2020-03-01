package flsh.civic.info.client;

import flsh.civic.info.client.dto.ElectionsDto;
import reactor.core.publisher.Mono;

public interface ElectionQueryClient {

  Mono<ElectionsDto> elections();
}
