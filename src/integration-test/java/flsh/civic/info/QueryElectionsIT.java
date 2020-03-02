package flsh.civic.info;

import static com.github.tomakehurst.wiremock.stubbing.StubMapping.buildFrom;
import static com.google.common.io.Resources.getResource;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.google.common.io.Resources;
import io.leangen.graphql.spqr.spring.web.dto.GraphQLRequest;
import java.io.IOException;
import java.nio.charset.Charset;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.lanwen.wiremock.config.WiremockConfigFactory;
import ru.lanwen.wiremock.ext.WiremockResolver;
import ru.lanwen.wiremock.ext.WiremockUriResolver;

@ExtendWith({
    SpringExtension.class,
    WiremockResolver.class,
    WiremockUriResolver.class
})
@SpringBootTest(classes = GoogleCivicInfoApiIntegrationApplication.class,
    webEnvironment = WebEnvironment.RANDOM_PORT)
class QueryElectionsIT {

  @LocalServerPort
  private Integer springPort;

  public static class WireMockConfigFactory implements WiremockConfigFactory {
    @Override
    public WireMockConfiguration create() {
      return WireMockConfiguration.options().port(9090);
    }
  }

  @Test
  void testElectionsQuery_Happy(@WiremockResolver.Wiremock(factory = WireMockConfigFactory.class) WireMockServer wireMockServer)
      throws IOException {
    wireMockServer.addStubMapping(buildFrom(readResource("wiremock-elections-stub-mapping.json")));
    String graphqlQuery = readResource("graphql-query-elections-all-fields.graphql");

    given()
        .body(new GraphQLRequest(graphqlQuery, null, null))
        .when()
        .baseUri("http://localhost")
        .port(springPort)
        .basePath("/graphql")
        .contentType("application/json")
        .post()
        .then()
        .statusCode(200)
        .header("Content-Type", "application/json;charset=utf-8")
        .body("data.elections[0].name", equalTo("FAKE_TEST_ELECTION"))
        .body("data.elections[0].electionDay", equalTo("2021-06-06"))
        .body("data.elections[0].ocdDivision", equalTo("ocd-division"));
  }

  private String readResource(String filePath) throws IOException {
    return Resources.toString(getResource(filePath), Charset.defaultCharset());
  }

}
