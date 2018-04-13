package org.folio.cataloging.integration.log;

/**
 * Storage layer Message Catalog.
 *
 * @author nbianchini
 * @author agazzarini
 * @since 1.0
 */
public interface MessageCatalogStorage {
    String MODULE_NAME = "MODCAT-STORAGE";

    String _00010_DATA_ACCESS_FAILURE = "<" + MODULE_NAME + "-00010> : Data access failure. Please check the stacktrace below for further information.";
    String _00011_NWS_FAILURE = "<" + MODULE_NAME + "-00011> : Not well known failure. Please check the stacktrace below for further information.";
    String _00012_NULL_RESULT = "<" + MODULE_NAME + "-00012> : Not well known failure. The service did return a nullable (not allowed) stringValue.";
    String _00013_IO_FAILURE = "<" + MODULE_NAME + "-00013> : I/O failure. Please check the stacktrace below for further details.";

    String _00014_NO_VALIDATION_FOUND = "<" + MODULE_NAME + "-00014> : Not well known failure. No validation found for category %s and values %s.";
    String _00015_CFG_KEY_FAILURE = "<" + MODULE_NAME + "-00015> : Unable to retrieve the configuration attribute associated with >%s< key. Please check the stacktrace below for further details.";
}
