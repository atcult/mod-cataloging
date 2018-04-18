package org.folio.cataloging.resources;

import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Handler;
import org.folio.cataloging.business.codetable.Avp;
import org.folio.cataloging.log.Log;
import org.folio.cataloging.log.MessageCatalog;
import org.folio.rest.jaxrs.model.SeriesTreatmentType;
import org.folio.rest.jaxrs.model.SeriesTreatmentTypeCollection;
import org.folio.rest.jaxrs.resource.CatalogingSeriesTreatmentTypesResource;

import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * Series treatment type RESTful APIs.
 *
 * @author natasciab
 * @since 1.0
 */
public class SeriesTreatmentTypesAPI implements CatalogingSeriesTreatmentTypesResource {

    protected final Log logger = new Log(SeriesTreatmentTypesAPI.class);

    private Function<Avp<String>, SeriesTreatmentType> toSeriesTreatmentType = source -> {
        final SeriesTreatmentType seriesTreatmentType = new SeriesTreatmentType();
        seriesTreatmentType.setCode(source.getValue());
        seriesTreatmentType.setDescription(source.getLabel());
        return seriesTreatmentType;
    };

    @Override
    public void getCatalogingSeriesTreatmentTypes(final String lang,
                                        final Map<String, String> okapiHeaders,
                                        final Handler<AsyncResult<Response>> asyncResultHandler,
                                        final Context vertxContext) throws Exception {
        doGet((storageService, configuration, future) -> {
            try {
                final SeriesTreatmentTypeCollection container = new SeriesTreatmentTypeCollection();
                container.setSeriesTreatmentTypes(
                        storageService.getSeriesTreatmentTypes(lang)
                                .stream()
                                .map(toSeriesTreatmentType)
                                .collect(toList()));
                return container;
            } catch (final Exception exception) {
                logger.error(MessageCatalog._00010_DATA_ACCESS_FAILURE, exception);
                return null;
            }
        }, asyncResultHandler, okapiHeaders, vertxContext);
    }

    @Override
    public void postCatalogingSeriesTreatmentTypes(String lang, SeriesTreatmentType entity, Map<String, String> okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context vertxContext) throws Exception {
        throw new IllegalArgumentException();
    }
}
