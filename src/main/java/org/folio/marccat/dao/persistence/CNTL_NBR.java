package org.folio.marccat.dao.persistence;

import static org.folio.marccat.config.Global.EMPTY_STRING;

import org.folio.marccat.business.cataloguing.authority.AuthorityControlNumberAccessPoint;
import org.folio.marccat.business.descriptor.SortFormParameters;
import org.folio.marccat.business.descriptor.SortformUtils;
import org.folio.marccat.config.log.Log;
import org.folio.marccat.dao.AbstractDAO;
import org.folio.marccat.dao.ControlNumberDescriptorDAO;
import org.folio.marccat.model.Subfield;
import org.folio.marccat.shared.CorrelationValues;
import org.folio.marccat.util.StringText;

/**
 * Hibernate class for table CNTL_NBR.
 *
 * @author paulm
 * @author carment
 */
public class CNTL_NBR extends Descriptor {

  private static final Log logger = new Log(CNTL_NBR.class);

  /**
   * The type code.
   */
  private int typeCode;

  /**
   * Instantiates a new cntl nbr.
   */
  public CNTL_NBR() {
    super();
  }

  /* (non-Javadoc)
   * @see Descriptor#getReferenceClass(java.lang.Class)
   */
  public Class getReferenceClass(Class targetClazz) {
    return null;
  }

  /**
   * Gets the dao.
   *
   * @return the dao
   */
  public AbstractDAO getDAO() {
    return new ControlNumberDescriptorDAO();
  }

  /* (non-Javadoc)
   * @see Descriptor#getAccessPointClass()
   */
  public Class getAccessPointClass() {
    return ControlNumberAccessPoint.class;
  }

  /* (non-Javadoc)
   * @see Descriptor#getAuthorityAccessPointClass()
   */
  @Override
  public Class getAuthorityAccessPointClass() {
    return AuthorityControlNumberAccessPoint.class;
  }

  /* (non-Javadoc)
   * @see Descriptor#getDefaultBrowseKey()
   */
  public String getDefaultBrowseKey() {
    return "16P30";
  }

  /* (non-Javadoc)
   * @see Descriptor#getNextNumberKeyFieldCode()
   */
  public String getNextNumberKeyFieldCode() {
    return "RN";
  }

  /* (non-Javadoc)
   * @see Descriptor#getCorrelationValues()
   */
  public CorrelationValues getCorrelationValues() {
    return new CorrelationValues(
      getTypeCode(),
      CorrelationValues.UNDEFINED,
      CorrelationValues.UNDEFINED);
  }


  /* (non-Javadoc)
   * @see Descriptor#setCorrelationValues(CorrelationValues)
   */
  public void setCorrelationValues(CorrelationValues v) {
    setTypeCode(v.getValue(1));
  }

  /* (non-Javadoc)
   * @see Descriptor#getSortFormParameters()
   */
  public SortFormParameters getSortFormParameters() {
    return new SortFormParameters(300, getTypeCode(), 0, 0, 0);
  }

  /* (non-Javadoc)
   * @see Descriptor#getCategory()
   */
  public int getCategory() {
    return 19;
  }

  /**
   * Gets the type code.
   *
   * @return the type code
   */
  public int getTypeCode() {
    return typeCode;
  }

  /**
   * Sets the type code.
   *
   * @param s the new type code
   */
  public void setTypeCode(int s) {
    typeCode = s;
  }

  /* (non-Javadoc)
   * @see Descriptor#getHeadingNumberSearchIndexKey()
   */
  public String getHeadingNumberSearchIndexKey() {
    return "231P";
  }

  /* (non-Javadoc)
   * @see Descriptor#getLockingEntityType()
   */
  public String getLockingEntityType() {
    return "RN";
  }

  @Override
  public void calculateAndSetSortForm() {
    if (ControlNumberType.isISBN((short) getTypeCode())) {
      setSortForm(calculateIsbnSortForm());
    } else if (ControlNumberType.isISSN((short) getTypeCode())) {
      setSortForm(calculateIssnSortForm());
    } else if (ControlNumberType.isISMN((short) getTypeCode())) {
      setSortForm(calculateIsmnSortForm());
    } else if (ControlNumberType.isPublisherNumber((short) getTypeCode())) {
      setSortForm(calculatePublisherNumberSortForm());
    } else {
      super.calculateAndSetSortForm();
    }

  }

  private String calculateIsmnSortForm() {
    String result = SortformUtils.get().defaultSortform(getStringText());
    result = SortformUtils.get().replacePunctuationMark2(result, EMPTY_STRING);
    return result;
  }

  private String calculatePublisherNumberSortForm() {
    StringText st = new StringText(getStringText());
    if (st.getNumberOfSubfields() > 0) {
      Subfield first = st.getSubfield(0);
      if (first.getCode().equals("a")) {
        first.setContent(first.getContent().replace(" ", EMPTY_STRING));
      }
    }
    String result = SortformUtils.get().defaultSortform(st.toString());
    result = SortformUtils.get().replacePunctuationMark2(result, EMPTY_STRING);
    return result;
  }

  private String calculateIssnSortForm() {
    String result = getDisplayText().toUpperCase();
    result = SortformUtils.get().stripAccents(result);
    result = SortformUtils.get().deleteAlfalam(result);
    if (result.charAt(result.length() - 1) == '*') {
      logger.debug("removing trailing *");
      return result.substring(0, result.length() - 1);
    }
    /*
     * The below is a port of the C program that performed this function.
     */
    StringBuilder s = new StringBuilder();
    for (int i = 0; i < result.length(); i++) {
      if (!Character.isLetter(result.charAt(i))) {
        s.append(result.charAt(i));
      }
    }
    result = s.toString();
    result = result.replace(" ", EMPTY_STRING);
    if (result.length() > 4 && result.charAt(4) != '-') {
      result = result.substring(0, 4) + '-' + result.substring(4);
    }
    return result;
  }

  private String calculateIsbnSortForm() {
    String result = getDisplayText().toUpperCase();
    result = SortformUtils.stripAccents(result);
    result = SortformUtils.deleteAlfalam(result);
    if (result.charAt(result.length() - 1) == '*') {
      logger.debug("removing trailing *");
      return result.substring(0, result.length() - 1);
    }
    if (!new StringText(getStringText()).getSubfield(0).getCode().equals("a")) {
      logger.debug("adding blank because first sub is not a");
      return " " + result;
    }

    // attempt to find an ISBN number at the beginning of $a
    /*
     * The below is a port of the C program that performed this function.
     * As I understand it, the approach is:
     * 		If there is no $a, just return the normalized string as is.
     * 		Otherwise, strip anything up to the first digit
     *      then remove any spaces or hyphens up to the 13th digit
     *      then copy everything else
     */
    int i = 0;
    while (i < result.length() && !Character.isDigit(result.charAt(i))) {
      i++;
    }
    if (i < result.length()) {
      StringBuilder s = new StringBuilder();
      int digitCount = 0;
      for (char c : result.toCharArray()) {
        if (digitCount < 13) {
          if (Character.isDigit(c) || (digitCount == 9 && c == 'X')) {
            digitCount++;
            s.append(c);
          } else if (c == ' ' || c == '-') {
            // do nothing
          } else {
            digitCount = 13;
            s.append(c);
          }
        } else {
          s.append(c);
        }
      }
      logger.debug("done scan: '" + s.toString() + "'");
      result = s.toString();
    }
    return result;
  }

}
