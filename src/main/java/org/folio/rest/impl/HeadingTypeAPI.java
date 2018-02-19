package org.folio.rest.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Handler;
import org.folio.cataloging.Global;
import org.folio.cataloging.business.codetable.Avp;
import org.folio.cataloging.log.Log;
import org.folio.cataloging.log.MessageCatalog;
import org.folio.rest.jaxrs.model.HeadingType;
import org.folio.rest.jaxrs.model.HeadingTypeCollection;
import org.folio.rest.jaxrs.model.ItemType;
import org.folio.rest.jaxrs.resource.CatalogingHeadingTypesResource;

import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static org.folio.cataloging.integration.CatalogingHelper.doGet;

/**
 * Heading Types RESTful APIs.
 *
 * @author natasciab
 * @since 1.0
 */

public class HeadingTypeAPI implements CatalogingHeadingTypesResource {

    protected final Log logger = new Log(HeadingTypeAPI.class);

    private Function<Avp<String>, ItemType> toItemType = source -> {
        final ItemType itemType = new ItemType();
        itemType.setCode(Integer.parseInt(source.getValue()));
        itemType.setDescription(source.getLabel());
        return itemType;
    };

    private Function<Avp<String>, HeadingType> toHeadingType = source -> {
        final HeadingType headingType = new HeadingType();
        headingType.setMarcCategory(Integer.parseInt(source.getValue()));
        headingType.setDescription(source.getLabel());
        return headingType;
    };

    @Override
    public void getCatalogingHeadingTypes(final String marcCategory,
                                          final String lang,
                                          final Map<String, String> okapiHeaders,
                                          final Handler<AsyncResult<Response>> asyncResultHandler,
                                          final Context vertxContext) throws Exception {
        doGet((storageService, future) -> {
            try {

                final String category = (marcCategory.equals("17") ? Global.NAME_CATEGORY_DEFAULT : marcCategory);
                return ofNullable(Global.firstCorrelationHeadingClassMap.get(category))
                        .map(className -> {
                            final HeadingType headingType = new HeadingType();
                            headingType.setMarcCategory(Integer.parseInt(marcCategory));
                            headingType.setDescription(getDescription(category, storageService.getHeadingDescriptionByCode(category, lang)));

                            headingType.setItemTypes(storageService.getFirstCorrelation(lang, className)
                                    .stream()
                                    .map(toItemType)
                                    .collect(toList()));

                            return headingType;
                        }).orElse(null);
            } catch (final Exception exception) {
                logger.error(MessageCatalog._00010_DATA_ACCESS_FAILURE, exception);
                return null;
            }
        }, asyncResultHandler, okapiHeaders, vertxContext);
    }

    @Override
    public void getCatalogingHeadingTypesAll(final String lang,
                                             final Map<String, String> okapiHeaders,
                                             final Handler<AsyncResult<Response>> asyncResultHandler,
                                             final Context vertxContext) throws Exception {
        doGet((storageService, future) -> {
            try {
                final HeadingTypeCollection headingTypeCollection = new HeadingTypeCollection();
                headingTypeCollection.setHeadingTypes(
                            storageService.getHeadingTypesList(lang)
                                .stream()
                                .map(toHeadingType)
                                .collect(toList()));

                return headingTypeCollection;

            } catch (final Exception exception) {
                logger.error(MessageCatalog._00010_DATA_ACCESS_FAILURE, exception);
                return null;
            }
        }, asyncResultHandler, okapiHeaders, vertxContext);

    }


    @Override
    public void postCatalogingHeadingTypes(String lang, HeadingType entity, Map<String, String> okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context vertxContext) throws Exception {
        throw new IllegalArgumentException();
    }

    @Override
    public void postCatalogingHeadingTypesAll(String lang, HeadingType entity, Map<String, String> okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context vertxContext) throws Exception {
        throw new IllegalArgumentException();
    }

    private String getDescription(final String marcCategory, final String desc){
        final Optional<String> description = ofNullable(desc);
        return description.isPresent() ? description.get() : description.orElse("");
    }
}
