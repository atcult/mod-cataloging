package org.folio.cataloging.dao;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.folio.cataloging.business.common.DataAccessException;
import org.folio.cataloging.business.common.Defaults;
import org.folio.cataloging.dao.common.HibernateUtil;
import org.folio.cataloging.dao.persistence.LIB;
import org.folio.cataloging.dao.persistence.ORG_HRCHY;
import org.folio.cataloging.dao.persistence.ORG_NME;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

/**
 * Handles data access to ORG
 *
 * @author paulm, elena
 * @author natasciab
 * @version %I%, %G%
 * @since 1.0
 */
public class DAOOrganisationHierarchy extends HibernateUtil {
  private Log logger = LogFactory.getLog(DAOOrganisationHierarchy.class);

  public ORG_HRCHY load(final Session session, final int branchLibrary) throws DataAccessException {
    return (ORG_HRCHY) get(session, ORG_HRCHY.class, new Integer(branchLibrary));
  }

  /**
   * @param orgNbr is the organisation number of the branch or library
   * @return the name of the library or branch with this number access to
   * ORG_NME table
   * @since 1.0
   */
  public String getLibOrBranchName(int orgNbr, Locale locale)
    throws DataAccessException {

    String result = "";

    ORG_NME orgNmeRegister =
      (ORG_NME) load(ORG_NME.class, new Integer(orgNbr));
    if (locale
      .getLanguage()
      .equals(Defaults.getString("amicus.codeTable.language1"))) {
      result = orgNmeRegister.getOrganisationEnglishName();
    } else {
      result = orgNmeRegister.getOrganisationFrenchName();
    }
    return result;
  }

  /**
   * @param orgNbr is the organisation number of the branch or library
   * @return the symbol of the library or branch with this number to LIB table
   * @since 1.0
   */
  public String getLibOrBranchSymbol(int orgNbr) throws DataAccessException {
    String result = new String("");

    LIB libRegister = (LIB) load(LIB.class, new Integer(orgNbr));
    result = libRegister.getLibrarySymbolCode();

    return result;
  }

  /**
   * @param parentOrgNbr identity number of the library
   * @return a list of branches belong to a library access to ORG_HRCHY
   * @since 1.0
   */
  public List getListOfBranchesFromALibrary(int parentOrgNbr) {
    List listList = null;
    List result = new Vector();

    try {
      Session s = currentSession();

      listList =
        s.find(
          "from ORG_HRCHY as o where o.PARNT_ORG_NBR = "
            + new Integer(parentOrgNbr)
            + "and o.ORG_NBR <> "
            + new Integer(parentOrgNbr));
      Iterator iter = listList.iterator();
      while (iter.hasNext()) {
        ORG_HRCHY rawList = (ORG_HRCHY) iter.next();

        ORG_NME orgNmeRegister =
          (ORG_NME) s.get(
            ORG_NME.class,
            new Integer(rawList.getORG_NBR()));

        result.add(orgNmeRegister);
      }
    } catch (HibernateException e) {
      // TODO Treat exception
      logger.warn("HibernateException loading cross references");
      //				return null;
    } catch (DataAccessException e) {
      // TODO e.printStackTrace() is evil. If you catch, handle the
      // exception.
      e.printStackTrace();

    }
    return result;
  }

	/*

	TODO: COMMENTATO DA ANDREA PERCHE' CONTENEVA ROBA DI CIRCOLAZIONE
	public List getListOfBranches(int parentOrgNbr, Locale locale)
		throws ModCatalogingException {
		List result = new Vector();

		try {
			Session s = currentSession();

			List listList =
				s.find(
					" SELECT FROM ORG_HRCHY as o,ORG_NME as on "
						+ "       WHERE o.ORG_NBR=on.organisationNumber "
						+ "         AND o.PARNT_ORG_NBR = ?"
						+ "         AND o.ORG_NBR <> ? ",
					new Object[] {
						new Integer(parentOrgNbr),
						new Integer(parentOrgNbr)},
					new Type[] { Hibernate.INTEGER, Hibernate.INTEGER });

			Iterator iter = listList.iterator();
			while (iter.hasNext()) {

				Object[] iterObject = (Object[]) iter.next();
				ORG_NME orgName = ((ORG_NME) ((Object[]) iterObject)[1]);
				CirculationSelectValuesBean csvb =
					new CirculationSelectValuesBean();
				csvb.setOrgNumber(orgName.getOrganisationNumber());

				if (locale.getLanguage().equals("en")) {
					csvb.setOrgName(orgName.getOrganisationEnglishName());
				} else
					csvb.setOrgName(orgName.getOrganisationFrenchName());

				result.add(csvb);
			}
		} catch (HibernateException e) {
			// TODO Treat exception
			logger.warn("HibernateException loading cross references");
			//				return null;
		} catch (DataAccessException e) {
			// TODO e.printStackTrace() is evil. If you catch, handle the
			// exception.
			e.printStackTrace();
		}
		return result;
	}

	public List getListOfBranchesFromALibrary(int parentOrgNbr, Locale locale)
		throws ModCatalogingException {
		List listList = null;
		List result = new Vector();

		try {
			Session s = currentSession();

			listList =
				s.find(
					" SELECT FROM ORG_HRCHY as o,ORG_NME as on "
						+ " WHERE o.ORG_NBR=on.organisationNumber "
						+" AND o.ORG_NBR <> "
						+ new Integer(parentOrgNbr)
						+ " AND o.PARNT_ORG_NBR = "
						+ new Integer(parentOrgNbr));

			Iterator iter = listList.iterator();
			while (iter.hasNext()) {

				Object[] iterObject = (Object[]) iter.next();
				ORG_NME orgName = ((ORG_NME) ((Object[]) iterObject)[1]);
				CirculationSelectValuesBean csvb =
					new CirculationSelectValuesBean();
				csvb.setOrgNumber(orgName.getOrganisationNumber());

				if (locale.getLanguage().equals("en")) {
					csvb.setOrgName(orgName.getOrganisationEnglishName());
				} else
					csvb.setOrgName(orgName.getOrganisationFrenchName());

				result.add(csvb);
			}
		} catch (HibernateException e) {
			// TODO Treat exception
			logger.warn("HibernateException loading cross references");
			//				return null;
		} catch (DataAccessException e) {
			// TODO e.printStackTrace() is evil. If you catch, handle the
			// exception.
			e.printStackTrace();

		}
		return result;
	}

	public List getListOfLibrary(Locale locale) throws ModCatalogingException {
		List listList = null;
		List result = new Vector();

		try {
			Session s = currentSession();

			listList =
				s.find(
					" SELECT FROM ORG_HRCHY  oh,"
						+ "             ORG_NME    om "
						+ "  WHERE oh.ORG_NBR=oh.PARNT_ORG_NBR "
						+ "    AND  oh.ORG_NBR=om.organisationNumber "
						+ "    AND oh.ORG_NBR<>2147483647  ");
			// the number 2147483647 is the max stringValue for number in DB
			logger.warn(" Liburutegiak : " + listList.size());
			Iterator iter = listList.iterator();
			while (iter.hasNext()) {

				Object[] iterObject = (Object[]) iter.next();
				ORG_NME orgName = ((ORG_NME) ((Object[]) iterObject)[1]);
				CirculationSelectValuesBean csvb =
					new CirculationSelectValuesBean();
				csvb.setOrgNumber(orgName.getOrganisationNumber());

				if (locale.getLanguage().equals("en")) {
					csvb.setOrgName(orgName.getOrganisationEnglishName());
				} else
					csvb.setOrgName(orgName.getOrganisationFrenchName());

				result.add(csvb);
			}
		} catch (HibernateException e) {
			// TODO Treat exception
			logger.warn("HibernateException loading cross references");
			//				return null;
		} catch (DataAccessException e) {
			// TODO e.printStackTrace() is evil. If you catch, handle the
			// exception.
			e.printStackTrace();

		}
		return result;
	}
*/

  /**
   * @param locale to select the language and library name
   * @return org numer access to ORG_HRCHY
   * @since 1.0
   */

  public int getOrgNumberByName(String libName, Locale locale) {
    List listList = null;
    int result = 0;
    String field = new String("");

    if (locale.getLanguage().equals("en")) {
      field = "organisationEnglishName";
    } else
      field = "organisationFrenchName";

    try {
      Session s = currentSession();

      listList =
        s.find("from ORG_NME as o where o." + field + "= " + libName);

      Iterator iter = listList.iterator();
      while (iter.hasNext()) {
        ORG_NME rawList = (ORG_NME) iter.next();
        result = rawList.getOrganisationNumber();
      }
    } catch (HibernateException e) {
      // TODO Treat exception
      logger.warn("HibernateException loading cross references");
      //				return null;
    } catch (DataAccessException e) {
      // TODO e.printStackTrace() is evil. If you catch, handle the
      // exception.
      e.printStackTrace();

    }
    return result;
  }

}
