package org.folio.cataloging.resources;

import io.swagger.annotations.Api;
import org.folio.cataloging.Global;
import org.folio.cataloging.ModCataloging;
import org.folio.cataloging.business.common.View;
import org.folio.cataloging.integration.StorageService;
import org.folio.cataloging.search.SearchEngineFactory;
import org.folio.cataloging.search.SearchResponse;
import org.folio.cataloging.search.engine.SearchEngine;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.folio.cataloging.F.locale;
import static org.folio.cataloging.integration.CatalogingHelper.doGet;

/**
 * Search Engine TEST RESTful APIs.
 *
 * @author agazzarini
 * @since 1.0
 */
@RestController
@Api(value = "modcat-api", description = "MARCCat Search API")
@RequestMapping(value = ModCataloging.BASE_URI, produces = "application/json")
public class SearchAPI extends BaseResource {

  @GetMapping("/search")
  public SearchResponse search(
    @RequestParam final String lang,
    @RequestHeader(Global.OKAPI_TENANT_HEADER_NAME) final String tenant,
    @RequestParam("q") final String q,
    @RequestParam(name = "from", defaultValue = "1") final int from,
    @RequestParam(name = "to", defaultValue = "10") final int to,
    @RequestParam(name = "view", defaultValue = View.DEFAULT_BIBLIOGRAPHIC_VIEW_AS_STRING) final int view,
    @RequestParam("ml") final int mainLibraryId,
    @RequestParam(name = "dpo", defaultValue = "1") final int databasePreferenceOrder,
    @RequestParam(name = "sortBy", required = false) final String[] sortAttributes,
    @RequestParam(name = "sortOrder", required = false) final String[] sortOrders) {
    return doGet((StorageService storageService, Map <String, String> configuration) -> {
      final SearchEngine searchEngine =
        SearchEngineFactory.create(
          SearchEngineFactory.EngineType.LIGHTWEIGHT,
          mainLibraryId,
          databasePreferenceOrder,
          storageService);
      SearchResponse response;

      response = searchEngine.fetchRecords(
        searchEngine.expertSearch(q, locale(lang), view, from, to, sortAttributes, sortOrders),
        "F",
        1,
        ((to - from) + 1));

      final int AUTHORITY_VIEW = -1;
      if (view == AUTHORITY_VIEW) {
        searchEngine.injectDocCount(response, storageService);
      }
      return response;
    }, tenant, configurator);
  }

  @GetMapping("/mergedSearch")
  public List<SearchResponse> mergedSearch(
    @RequestParam final String lang,
    @RequestHeader(Global.OKAPI_TENANT_HEADER_NAME) final String tenant,
    @RequestParam("q") final String q,
    @RequestParam(name = "from", defaultValue = "1") final int from,
    @RequestParam(name = "to", defaultValue = "10") final int to,
    @RequestParam(name = "ml", defaultValue = "170") final int mainLibraryId,
    @RequestParam(name = "dpo", defaultValue = "1") final int databasePreferenceOrder,
    @RequestParam(name = "sortBy", required = false) final String[] sortAttributes,
    @RequestParam(name = "sortOrder", required = false) final String[] sortOrders) {


    SearchResponse authRecords =  doGet((storageService, configuration) -> {
      final SearchEngine searchEngine =
        SearchEngineFactory.create(
          SearchEngineFactory.EngineType.LIGHTWEIGHT,
          mainLibraryId,
          databasePreferenceOrder,
          storageService);

      SearchResponse response = searchEngine.fetchRecords(
        (sortAttributes != null && sortOrders != null && sortAttributes.length == sortOrders.length)
          ? searchEngine.sort(searchEngine.expertSearch(q, locale(lang), View.AUTHORITY), sortAttributes, sortOrders)
          : searchEngine.expertSearch(q, locale(lang), View.AUTHORITY),
        "F",
        from,
        to);

      searchEngine.injectDocCount(response, storageService);

      return response;
    }, tenant, configurator);

    SearchResponse bibRecords =  doGet((storageService, configuration) -> {
      final SearchEngine searchEngine =
        SearchEngineFactory.create(
          SearchEngineFactory.EngineType.LIGHTWEIGHT,
          mainLibraryId,
          databasePreferenceOrder,
          storageService);

      SearchResponse response = searchEngine.fetchRecords(
        (sortAttributes != null && sortOrders != null && sortAttributes.length == sortOrders.length)
          ? searchEngine.sort(searchEngine.expertSearch(q, locale(lang), View.DEFAULT_BIBLIOGRAPHIC_VIEW), sortAttributes, sortOrders)
          : searchEngine.expertSearch(q, locale(lang), View.DEFAULT_BIBLIOGRAPHIC_VIEW),
        "F",
        from,
        to);

      return response;
    }, tenant, configurator);
    List<SearchResponse> mergedResult = new ArrayList<>();
    mergedResult.add(authRecords);
    mergedResult.add(bibRecords);

    return mergedResult;
  }

  @GetMapping("/searchVertical")
  public SearchResponse searchVertical(
    @RequestParam final String lang,
    @RequestHeader(Global.OKAPI_TENANT_HEADER_NAME) final String tenant,
    @RequestParam("q") final String q,
    @RequestParam(name = "from", defaultValue = "1") final int from,
    @RequestParam(name = "to", defaultValue = "10") final int to,
    @RequestParam(name = "view", defaultValue = View.DEFAULT_BIBLIOGRAPHIC_VIEW_AS_STRING) final int view,
    @RequestParam("ml") final int mainLibraryId,
    @RequestParam(name = "dpo", defaultValue = "1") final int databasePreferenceOrder,
    @RequestParam(name = "sortBy", required = false) final String[] sortAttributes,
    @RequestParam(name = "sortOrder", required = false) final String[] sortOrders) {
    return doGet((storageService, configuration) -> {
      final SearchEngine searchEngine =
        SearchEngineFactory.create(
          SearchEngineFactory.EngineType.LIGHTWEIGHT_VERTICAL,
          mainLibraryId,
          databasePreferenceOrder,
          storageService);

      return searchEngine.fetchRecords(
        (sortAttributes != null && sortOrders != null && sortAttributes.length == sortOrders.length)
          ? searchEngine.sort(searchEngine.expertSearch(q, locale(lang), view), sortAttributes, sortOrders)
          : searchEngine.expertSearch(q, locale(lang), view),
        "F",
        1,
        ((to - from) + 1));
    }, tenant, configurator);
  }

}
