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
    String _00011_CACHE_UPDATE_FAILURE = "<" + MODULE_NAME + "-00011> : An error occurred during execution of AMICUS.CFN_PR_CACHE_UPDATE procedure. ErrorCollection code: %d.";
    String _00012_TARGET_DESCRIPTOR_NULL = "<" + MODULE_NAME + "-00012> : Target descriptor is null. Can not transfer an heading to another.";
    String _00013_DIFFERENT_TARGET_SOURCE = "<" + MODULE_NAME + "-00013> : Target and Source descriptor are different classes. Can not transfer an heading to another.";
    String _00014_NO_VALIDATION_FOUND = "<" + MODULE_NAME + "-00014> : Not well known failure. No validation found for category %s and values %s.";
    String _00016_NO_HEADING_FOUND = "<" + MODULE_NAME + "-00016> : No heading found for heading number: %s";
    String _00017_MARC_CORRELATION_SORTING = "<" + MODULE_NAME + "-00017> : Marc correlation exception. ErrorCollection during sort tags.";
    String _00018_NO_HEADING_TYPE_CODE = "<" + MODULE_NAME + "-00018> : No heading type code selected for tag %s.";
    String _00019_SAVE_RECORD_FAILURE = "<" + MODULE_NAME + "-00019> : Error during save or update record %d.";
    String _00020_LOCK_FAILURE = "<" + MODULE_NAME + "-00020> : Lock record failure. Record already in use (-id:%d -username:%s).";
    String _00021_UNLOCK_FAILURE = "<" + MODULE_NAME + "-00021> : Unlock record failure (-id:%d -username:%s).";
    String _00022_DELETE_RECORD_FAILURE = "<" + MODULE_NAME + "-00022> : Error during record delete %d.";

    String _00030_LOAD_RECORDS_FAILURE = "<" + MODULE_NAME + "-00030> : Error during records loading.";
    String _00031_LOAD_FROM_FILE_FAILURE = "<" + MODULE_NAME + "-00031> : Error during load from file procedure. File %s not loaded.";
    String _00032_LOAD_REC_BY_REC_FAILURE = "<" + MODULE_NAME + "-00032> : Error during load record from file procedure. Record %s not loaded.";

}
