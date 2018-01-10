package org.folio.rest.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Handler;
import org.folio.cataloging.business.codetable.Avp;
import org.folio.cataloging.log.Log;
import org.folio.cataloging.log.MessageCatalog;
import org.folio.rest.jaxrs.model.NoteType;
import org.folio.rest.jaxrs.model.NoteTypeCollection;
import org.folio.rest.jaxrs.model.RecordDisplayFormat;
import org.folio.rest.jaxrs.model.RecordDisplayFormatCollection;
import org.folio.rest.jaxrs.resource.RecordDisplayFormatsResource;

import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;
import static org.folio.cataloging.integration.CatalogingHelper.doGet;


/**
 * Record Display Format RESTful APIs.
 *
 * @author aguercio
 * @since 1.0
 */

public class RecordDisplayFormatsAPI implements RecordDisplayFormatsResource{
    protected final Log logger = new Log(RecordDisplayFormatsAPI.class);

    private Function<Avp<String>, RecordDisplayFormat> toRecordDisplayFormat = source -> {
        final RecordDisplayFormat recordDisplayFormat = new RecordDisplayFormat();
        recordDisplayFormat.setCode(Integer.parseInt(source.getValue()));
        recordDisplayFormat.setDescription(source.getLabel());
        return recordDisplayFormat;
    };

    @Override
    public void getRecordDisplayFormats(final String lang,
                                        final Map<String, String> okapiHeaders,
                                        final Handler<AsyncResult<Response>> asyncResultHandler,
                                        final Context vertxContext) throws Exception {
        doGet((storageService, future) -> {
            try {
                final RecordDisplayFormatCollection container = new RecordDisplayFormatCollection();
                container.setRecordDisplayFormats(
                        storageService.getRecordDisplayFormats(lang)
                                .stream()
                                .map(toRecordDisplayFormat)
                                .collect(toList()));
                return container;
            } catch (final Exception exception) {
                logger.error(MessageCatalog._00010_DATA_ACCESS_FAILURE, exception);
                return null;
            }
        }, asyncResultHandler, okapiHeaders, vertxContext);

    }

    @Override
    public void postRecordDisplayFormats(String lang, RecordDisplayFormat entity, Map<String, String> okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context vertxContext) throws Exception {
        throw new IllegalArgumentException();
    }
}
