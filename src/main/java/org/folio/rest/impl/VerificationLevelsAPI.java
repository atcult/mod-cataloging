package org.folio.rest.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Handler;
import org.folio.cataloging.business.codetable.Avp;
import org.folio.cataloging.log.Log;
import org.folio.cataloging.log.MessageCatalog;
import org.folio.rest.jaxrs.model.VerificationLevel;
import org.folio.rest.jaxrs.model.VerificationLevelCollection;
import org.folio.rest.jaxrs.resource.VerificationLevelsResource;

import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;
import static org.folio.cataloging.integration.CatalogingHelper.doGet;

public class VerificationLevelsAPI implements VerificationLevelsResource {

    protected final Log logger = new Log(VerificationLevelsAPI.class);

    private Function<Avp<String>, VerificationLevel> toVerificationLevel = source -> {
        final VerificationLevel verificationLevel = new VerificationLevel();
        verificationLevel.setCode(source.getValue());
        verificationLevel.setDescription(source.getLabel());
        return verificationLevel;
    };

    @Override
    public void getVerificationLevels(final String lang,
                                      final Map<String, String> okapiHeaders,
                                      final Handler<AsyncResult<Response>> asyncResultHandler,
                                      final Context vertxContext) throws Exception {
        doGet((storageService, future) -> {
            try {
                final VerificationLevelCollection container = new VerificationLevelCollection();
                container.setVerificationLevels(
                        storageService.getVerificationLevels(lang)
                                .stream()
                                .map(toVerificationLevel)
                                .collect(toList()));
                return container;
            } catch (final Exception exception) {
                logger.error(MessageCatalog._00010_DATA_ACCESS_FAILURE, exception);
                return null;
            }
        }, asyncResultHandler, okapiHeaders, vertxContext);
    }

    @Override
    public void postVerificationLevels(String lang, VerificationLevel entity, Map<String, String> okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context vertxContext) throws Exception {
        throw new IllegalArgumentException();
    }
}
