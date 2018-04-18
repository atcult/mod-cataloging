package org.folio.cataloging.resources;


import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Handler;
import org.folio.cataloging.business.codetable.Avp;
import org.folio.cataloging.log.Log;
import org.folio.cataloging.log.MessageCatalog;
import org.folio.rest.jaxrs.model.Subscription;
import org.folio.rest.jaxrs.model.SubscriptionCollection;
import org.folio.rest.jaxrs.resource.CatalogingSubscriptionsResource;

import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * Subscription RESTful APIs.
 *
 * @author aguercio
 * @since 1.0
 */

public class SubscriptionsAPI implements CatalogingSubscriptionsResource {
    protected final Log logger = new Log(SubscriptionsAPI.class);

    private Function<Avp<String>, Subscription> toSubscription = source -> {
        final Subscription subscription = new Subscription();
        subscription.setCode(source.getValue());
        subscription.setDescription(source.getLabel());
        return subscription;
    };

    @Override
    public void getCatalogingSubscriptions (final String lang,
                                final Map<String, String> okapiHeaders,
                                final Handler<AsyncResult<Response>> asyncResultHandler,
                                final Context vertxContext) throws Exception {
        doGet((storageService, configuration, future) -> {
            try {
                final SubscriptionCollection container = new SubscriptionCollection();
                container.setSubscriptions (
                        storageService.getSubscriptions(lang)
                                .stream()
                                .map(toSubscription)
                                .collect(toList()));
                return container;
            } catch (final Exception exception) {
                logger.error(MessageCatalog._00010_DATA_ACCESS_FAILURE, exception);
                return null;
            }
        }, asyncResultHandler, okapiHeaders, vertxContext);
    }

    @Override
    public void postCatalogingSubscriptions (String lang, Subscription entity, Map<String, String> okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context vertxContext) throws Exception {
        throw new IllegalArgumentException();
    }
}
