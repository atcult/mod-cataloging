package org.folio.marccat.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.folio.marccat.Global;
import org.folio.marccat.ModMarccat;
import org.folio.marccat.business.codetable.Avp;
import org.folio.marccat.resources.domain.LogicalViewCollection;
import org.folio.marccat.resources.domain.View;
import org.springframework.web.bind.annotation.*;

import java.util.function.Function;

import static java.util.stream.Collectors.toList;
import static org.folio.marccat.integration.CatalogingHelper.doGet;

/**
 * Logical views RESTful APIs.
 *
 * @author cchiama
 * @since 1.0
 */
@RestController
@Api(value = "modcat-api", description = "Logical view resource API")
@RequestMapping(value = ModMarccat.BASE_URI, produces = "application/json")
public class LogicalViewAPI extends BaseResource {

  private Function <Avp <String>, View> adapter = source -> {
    final View logicalView = new View();
    logicalView.setCode(source.getValue());
    logicalView.setLongDescription(source.getLabel());
    return logicalView;
  };

  @ApiOperation(value = "Returns all logical views associated with a given language")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Method successfully returned the requested logical views."),
    @ApiResponse(code = 400, message = "Bad Request"),
    @ApiResponse(code = 414, message = "Request-URI Too Long"),
    @ApiResponse(code = 500, message = "System internal failure occurred.")
  })
  @GetMapping("/logical-views")
  public LogicalViewCollection getLogicalViews(
    @RequestParam final String lang,
    @RequestHeader(Global.OKAPI_TENANT_HEADER_NAME) final String tenant) {
    return doGet((storageService, configuration) -> {
      final LogicalViewCollection container = new LogicalViewCollection();
      container.setViews(
        storageService.getLogicalViews(lang)
          .stream()
          .map(adapter)
          .collect(toList()));
      return container;
    }, tenant, configurator);
  }
}
