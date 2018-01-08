package org.folio.rest.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Handler;
import org.folio.cataloging.business.codetable.ValueLabelElement;
import org.folio.cataloging.log.Log;
import org.folio.cataloging.log.MessageCatalog;
import org.folio.rest.jaxrs.model.LogicalViewCollection;
import org.folio.rest.jaxrs.model.View;
import org.folio.rest.jaxrs.resource.LogicalViewsResource;

import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;
import static org.folio.cataloging.integration.CatalogingHelper.doGet;

/**
 * Logical views RESTful APIs.
 *
 * @author agazzarini
 * @since 1.0
 */
public class LogicalViewsAPI implements LogicalViewsResource {
    protected final Log logger = new Log(LogicalViewsAPI.class);

    // This is the adapter that converts existing value objects (logical views in this case)
    // in OKAPI resources.
    private Function<ValueLabelElement, View> adapter = source -> {
        final View logicalView = new View();
        logicalView.setCode(source.getValue());
        logicalView.setLongDescription(source.getLabel());
        return logicalView;
    };

    @Override
    public void getLogicalViews(
            final String lang,
            final Map<String, String> okapiHeaders,
            final Handler<AsyncResult<Response>> resultHandler,
            final Context vertxContext) throws Exception {
        doGet((storageService, future) -> {
            try {
                final LogicalViewCollection container = new LogicalViewCollection();
                container.setViews(
                        storageService.getLogicalViews(lang)
                                .stream()
                                .map(adapter)
                                .collect(toList()));
                return container;
            } catch (final Exception exception) {
                logger.error(MessageCatalog._00010_DATA_ACCESS_FAILURE, exception);
                return null;
            }
        }, resultHandler, okapiHeaders, vertxContext);
    }

    @Override
    public void postLogicalViews(String lang, View entity, Map<String, String> okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context vertxContext) throws Exception {
        throw new IllegalArgumentException();
    }
}