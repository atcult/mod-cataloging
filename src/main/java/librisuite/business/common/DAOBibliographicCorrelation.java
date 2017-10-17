/*
 * (c) LibriCore
 * 
 * Created on Aug 6, 2004
 * 
 * $Author: Paulm $
 * $Date: 2007/02/13 09:17:59 $
 * $Locker:  $
 * $Name:  $
 * $Revision: 1.12 $
 * $Source: /source/LibriSuite/src/librisuite/business/common/DAOBibliographicCorrelation.java,v $
 * $State: Exp $
 */
package librisuite.business.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import librisuite.business.searching.DAOIndexList;
import librisuite.hibernate.AutMarcTagDisplay;
import librisuite.hibernate.BibliographicCorrelation;
import librisuite.hibernate.ClassificationFunction;
import librisuite.hibernate.Correlation;
import librisuite.hibernate.CorrelationKey;
import librisuite.hibernate.LabelTagDisplay;
import librisuite.hibernate.MarcTagDisplay;
import librisuite.hibernate.RdaMarcTagDisplay;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.type.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Manages access to table S_BIB_MARC_IND_DB_CRLTN -- the correlation between AMICUS
 * database encoding and MARC21 encoding
 * @author paulm
 * @version $Revision: 1.12 $, $Date: 2007/02/13 09:17:59 $
 */
@SuppressWarnings("unchecked")
public class DAOBibliographicCorrelation extends DAOCorrelation {
	private static final Log logger =
		LogFactory.getLog(DAOBibliographicCorrelation.class);
	private static final String ALPHABETICAL_ORDER = " order by ct.longText ";
	private static final String SEQUENCE_ORDER = " order by ct.sequence ";
	private String defaultListOrder = Defaults.getBoolean("labels.alphabetical.order", true)?ALPHABETICAL_ORDER:SEQUENCE_ORDER;

	/**
	 * Returns the BibliographicCorrelation from BibliographicCorrelationKey
	 * @param bibliographicCorrelationKey -- the database bibliographicCorrelationKey
	 * @return a BibliographicCorrelation object containing or null when none found
	 *
	 */
	public Correlation getBibliographicCorrelation(CorrelationKey bibliographicCorrelationKey)
		throws DataAccessException {
		return getBibliographicCorrelation(
			bibliographicCorrelationKey.getMarcTag(),
			bibliographicCorrelationKey.getMarcFirstIndicator(),
			bibliographicCorrelationKey.getMarcSecondIndicator(),
			bibliographicCorrelationKey.getMarcTagCategoryCode());
	}

	/**
	 * Returns the BibliographicCorrelation based on MARC encoding and category code
	 * @param tag -- marc tag
	 * @param firstIndicator -- marc first indicator
	 * @param secondIndicator -- marc second indicator
	 * @param categoryCode -- category code
	 * @return a BibliographicCorrelation object or null when none found
	 *
	 */
	public Correlation getBibliographicCorrelation(
		String tag,
		char firstIndicator,
		char secondIndicator,
		short categoryCode)
		throws DataAccessException {
        List l=null;
        if(categoryCode!=0){
        	l =
			find(
				"from BibliographicCorrelation as bc "
					+ "where bc.key.marcTag = ? and "
					+ "(bc.key.marcFirstIndicator = ? or bc.key.marcFirstIndicator='S' )and "
					//Natascia 13/06/2007: scommentate chiocciole
					+ "bc.key.marcFirstIndicator <> '@' and "
					+ "(bc.key.marcSecondIndicator = ? or bc.key.marcSecondIndicator='S')and "
					+ "bc.key.marcSecondIndicator <> '@' and "
					+ "bc.key.marcTagCategoryCode = ?",
				new Object[] {
					new String(tag),
					new Character(firstIndicator),
					new Character(secondIndicator),
					new Short(categoryCode)},
				new Type[] {
					Hibernate.STRING,
					Hibernate.CHARACTER,
					Hibernate.CHARACTER,
					Hibernate.SHORT });
		}
        else {
         l =
 			find(
 				"from BibliographicCorrelation as bc "
 					+ "where bc.key.marcTag = ? and "
 					+ "(bc.key.marcFirstIndicator = ? or bc.key.marcFirstIndicator='S' )and "
 					//Natascia 13/06/2007: scommentate chiocciole
 					+ "bc.key.marcFirstIndicator <> '@' and "
 					+ "(bc.key.marcSecondIndicator = ? or bc.key.marcSecondIndicator='S')and "
 					+ "bc.key.marcSecondIndicator <> '@' order by bc.key.marcTagCategoryCode asc",
 					
 				new Object[] {
 					new String(tag),
 					new Character(firstIndicator),
 					new Character(secondIndicator)},
 				new Type[] {
 					Hibernate.STRING,
 					Hibernate.CHARACTER,
 					Hibernate.CHARACTER});
          }
        
		//if (l.size() == 1) {
        if (l.size() >=1) {
            return (Correlation) l.get(0);
		} 
		else
			return null;
	}

	/**
	 * Returns the MARC encoding based on the input database encodings
	 * @param category -- the database category (1-name, etc...)
	 * @param value1 -- the first database code
	 * @param value2 -- the second database code
	 * @param value3 -- the third database code
	 * @return a BibliographicCorrelationKey object containing 
	 * the MARC encoding (tag and indicators) or null when none found
	 *
	 */
	public CorrelationKey getMarcEncoding(
		short category,
		short value1,
		short value2,
		short value3)
		throws DataAccessException {

		List l =
			find(
				"from BibliographicCorrelation as bc "
					+ "where bc.key.marcTagCategoryCode = ? and "
					+ "bc.databaseFirstValue = ? and "
					+ "bc.databaseSecondValue = ? and "
					+ "bc.databaseThirdValue = ?",
				new Object[] {
					new Short(category),
					new Short(value1),
					new Short(value2),
					new Short(value3)},
				new Type[] {
					Hibernate.SHORT,
					Hibernate.SHORT,
					Hibernate.SHORT,
					Hibernate.SHORT });

		if (l.size() == 1) {
			return ((Correlation) l.get(0)).getKey();
		} else {
			return null;
		}
	}

	public List getSecondCorrelationList(short category,short value1,Class codeTable) throws DataAccessException 
	{
		return find("Select distinct ct from "
					+ codeTable.getName()
					+ " as ct, BibliographicCorrelation as bc "
					+ " where bc.key.marcTagCategoryCode = ? and "
					//Natascia 13/06/2007: scommentate chiocciole
					+ " bc.key.marcFirstIndicator <> '@' and "
					+ " bc.key.marcSecondIndicator <> '@' and "
					+ " bc.databaseFirstValue = ? and "
					+ " bc.databaseSecondValue = ct.code and  "
					+ "ct.obsoleteIndicator = 0  order by ct.sequence ",
				new Object[] { new Short(category), new Short(value1)},
				new Type[] { Hibernate.SHORT, Hibernate.SHORT});
	}
	
	/* Bug 4121 inizio */
	public static final String SELECT_CLASSIFICATION_TAG_LABELS = 
		"SELECT AA.TBL_SEQ_NBR, AA.TYP_VLU_CDE, aa.FNCTN_VLU_CDE, AA.TBL_VLU_OBSLT_IND, AA.SHORT_STRING_TEXT, AA.STRING_TEXT, AA.LANGID"
		+ " FROM "
		+ System.getProperty(com.atc.weloan.shared.Global.SCHEMA_SUITE_KEY) +".S_BIB_CLSTN_TYP_FNCTN AA," 
		+ System.getProperty(com.atc.weloan.shared.Global.SCHEMA_SUITE_KEY) +".S_BIB_MARC_IND_DB_CRLTN BC"
		+ " WHERE BC.MARC_TAG_CAT_CDE = ?"
		+ " AND BC.MARC_TAG_1ST_IND <> '@'" 
		+ " AND BC.MARC_TAG_2ND_IND <> '@'" 
		+ " AND BC.MARC_TAG_IND_VLU_1_CDE = ?"
		+ " AND BC.MARC_TAG_IND_VLU_2_CDE = AA.FNCTN_VLU_CDE"
		+ " AND BC.MARC_TAG_IND_VLU_1_CDE = AA.TYP_VLU_CDE"
		+ " AND AA.TBL_VLU_OBSLT_IND = 0"  
		+ " ORDER BY AA.TBL_SEQ_NBR";

	public List<ClassificationFunction> getClassificationTagLabels(short category, short value1) throws DataAccessException
	{
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Session session = currentSession();
		List<ClassificationFunction> list = new ArrayList<ClassificationFunction>();
		ClassificationFunction item = null;
		
		try {
			connection = session.connection();
		    stmt = connection.prepareStatement(SELECT_CLASSIFICATION_TAG_LABELS);
		    stmt.setInt(1, category);
		    stmt.setInt(2, value1);
			rs = stmt.executeQuery();
			while (rs.next()) {
				item = new ClassificationFunction();
				item.setSequence(rs.getInt("TBL_SEQ_NBR"));
//				item.setCode(rs.getShort("TYP_VLU_CDE"));
				item.setCode(rs.getShort("FNCTN_VLU_CDE"));
				item.setObsoleteIndicator(rs.getString("TBL_VLU_OBSLT_IND").equals("1")?true:false);
				item.setLanguage(rs.getString("LANGID"));
				item.setShortText(rs.getString("SHORT_STRING_TEXT"));
				item.setLongText(rs.getString("STRING_TEXT"));
				list.add(item);
			}
			
		} catch (HibernateException e) {
			logAndWrap(e);
		} catch (SQLException e) {
			logAndWrap(e);			
			
		} finally {
			try{ rs.close(); } catch(Exception ex){}
			try{ stmt.close(); } catch(Exception ex){}
		}
		return list;
	}
	/* Bug 4121 fine */

	public List getThirdCorrelationList(
			short category,
			short value1,
			short value2,
			Class codeTable)
			throws DataAccessException {
			
			return find(
				" select distinct ct from "
					+ codeTable.getName()
					+ " as ct, BibliographicCorrelation as bc "
					+ " where bc.key.marcTagCategoryCode = ? and "
//Natascia 13/06/2007: scommentate chiocciole					
					+ " bc.key.marcFirstIndicator <> '@' and "
					+ " bc.key.marcSecondIndicator <> '@' and "
					+ " bc.databaseFirstValue = ? and "
					+ " bc.databaseSecondValue = ? and "					
					+ " bc.databaseThirdValue = ct.code and "
//Natascia 25/06/2007: non visualizzo nella lista gli obsoleti
					+ " ct.obsoleteIndicator = 0  order by ct.sequence ",
				new Object[] {
					new Short(category),
					new Short(value1),
					new Short(value2)},
				new Type[] { Hibernate.SHORT, Hibernate.SHORT, Hibernate.SHORT });
		}

	public short getFirstAllowedValue2(
			short category,
			short value1,
			short value3)
			throws DataAccessException {

			List l = find(
				" from BibliographicCorrelation as bc "
					+ " where bc.key.marcTagCategoryCode = ? and "
//Natascia 13/06/2007: scommentate chiocciole					
					+ " bc.key.marcFirstIndicator <> '@' and "
					+ " bc.key.marcSecondIndicator <> '@' and "
					+ " bc.databaseFirstValue = ? and "
					+ " bc.databaseThirdValue = ? ",
				new Object[] {
					new Short(category),
					new Short(value1),
					new Short(value3)},
				new Type[] { Hibernate.SHORT, Hibernate.SHORT, Hibernate.SHORT });
			
			if (l.size() > 0) {
				return ((BibliographicCorrelation)l.get(0)).getDatabaseSecondValue();
			}
			else {
				return -1;
			}
		}

	public String getClassificationIndexByShelfType(short shelfType) throws DataAccessException 
	{
		List l = find("from BibliographicCorrelation as bc "
					+ " where bc.key.marcTagCategoryCode = 13 and "
					+ " bc.databaseFirstValue = ? ",
				new Object[] { new Short(shelfType)},
				new Type[] { Hibernate.SHORT });
		if (l.size() == 1) {
			String s = ((Correlation) l.get(0)).getSearchIndexTypeCode();
			return new DAOIndexList().getIndexByEnglishAbreviation(s);
		} else {
			return null;
		}
	}

	/*modifica barbara 13/04/2007 PRN 127 - nuova intestazione su lista vuota default maschera inserimento intestazione nome*/
	public CorrelationKey getMarcTagCodeBySelectedIndex(String selectedIndex)
	throws DataAccessException {
	
		if(selectedIndex == null)
		{
			return null;
		}
		
	List l =
		find(
			"from BibliographicCorrelation as bc "
				+ " where bc.searchIndexTypeCode = ?" 
				+" or bc.searchIndexTypeCode = ?" ,
	new Object[] { new String(selectedIndex.substring(0, 2)),
				new String(selectedIndex.substring(0, 2).toLowerCase())},
	new Type[] { Hibernate.STRING,  Hibernate.STRING});
	
	if(l.size()>0)
		return ((Correlation) l.get(0)).getKey();
	else	
		return null;
	}

	public CorrelationKey getMarcTagCodeBySelectedIndex(String selectedIndex, String tagNumber) throws DataAccessException 
	{
		List l =
			find(
				"from BibliographicCorrelation as bc "
					+ " where (bc.searchIndexTypeCode = ?" 
					+" or bc.searchIndexTypeCode = ?)" 
					+" and bc.key.marcTag = ?",
					new Object[] { new String(selectedIndex.substring(0, 2)),
					new String(selectedIndex.substring(0, 2).toLowerCase()),
					new String(tagNumber)},
		new Type[] { Hibernate.STRING,  Hibernate.STRING,  Hibernate.STRING});
	
	if(l.size()>0)
		return ((Correlation) l.get(0)).getKey();
	else	
		return null;
	}
	
	/**
	 * Return the label for the tag to display
	 * @param tag
	 * @param firstIndicator
	 * @param secondIndicator
	 * @return MarcTagDisplay
	 * @throws DataAccessException
	 */
	public List<LabelTagDisplay> getMarcTagDisplay(String language){
		List l = new ArrayList<MarcTagDisplay>();
		try {
			l = find(
				"from MarcTagDisplay as bc "
					+ "where bc.language = ? ",
				new Object[] {
					new String(language)
					},
				new Type[] {
					Hibernate.STRING});
		} catch (DataAccessException e) {
			logger.debug("DataAccessException for list of MarcTagDisplay");
		}

		if (l.size()> 0) {
			
			return l;
		}
		return l;
	}

	/*  Bug 4775 inizio */
	public List<RdaMarcTagDisplay> getRdaMarcTagDisplay(String language)
	{
		List l = new ArrayList<MarcTagDisplay>();
		try {
			l = find(
				"from RdaMarcTagDisplay as bc where bc.language = ? ",
				new Object[] {new String(language)}, new Type[] {Hibernate.STRING});
		} catch (DataAccessException e) {
			logger.debug("DataAccessException for list of RdaMarcTagDisplay");
		}

		if (l.size()> 0) {
			return l;
		}
		return l;
	}
	/*  Bug 4775 fine */
	
	/**
	 * Label per authority
	 * @param language
	 * @return
	 */
	public List<LabelTagDisplay> getAutorityMarcTagDisplay(String language)
	{
		List l = new ArrayList<AutMarcTagDisplay>();
		try {
			
			l = find("from AutMarcTagDisplay as bc where bc.language = ? ",
				new Object[] {new String(language)},new Type[] {Hibernate.STRING});
			
		} catch (DataAccessException e) {
			logger.debug("DataAccessException for list of MarcTagDisplay");
		}

		if (l.size()> 0) {
			return l;
		}
		return l;
	}

	
	public List<BibliographicCorrelation> getFirstAllowedValue2List(short category, short value1, short value3) throws DataAccessException 
	{
		List l = find(" from BibliographicCorrelation as bc "
					+ " where bc.key.marcTagCategoryCode = ? and "
					/* Natascia 13/06/2007: scommentate chiocciole */					
					+ " bc.key.marcFirstIndicator <> '@' and "
					+ " bc.key.marcSecondIndicator <> '@' and "
					+ " bc.databaseFirstValue = ? and "
					+ " bc.databaseThirdValue = ? ",
		new Object[] {
			new Short(category),
			new Short(value1),
			new Short(value3)},
			new Type[] { Hibernate.SHORT, Hibernate.SHORT, Hibernate.SHORT });
		
		return l;
	}
	
	/**
	 * Checking validity of the second correlation
	 * @param l
	 * @param value2
	 * @return
	 * @throws DataAccessException
	 */
	public boolean isPresentSecondCorrelation(List l, short value2) throws DataAccessException 
	{
		boolean isPresent = false;
		if (l.size() > 0) {
			Iterator<BibliographicCorrelation> ite = l.iterator();
			while (ite.hasNext()) {
				short secondCorr = ((BibliographicCorrelation) ite.next()).getDatabaseSecondValue();
				if (secondCorr == value2) {
					isPresent = true;
				}
			}
		}
		return isPresent;
	}
	
	/**
	 * returns a filtered list for groups of tags as 2xx,3xx....
	 * @param c
	 * @param alphabeticOrder
	 * @param rangTag
	 * @return
	 * @throws DataAccessException
	 */
	public  List getFirstCorrelationListFilter(Class c, boolean alphabeticOrder,short rangTag) throws DataAccessException {
		List listCodeTable = null;
		String rangeTagFrom="";
		short rangeTagTo=0;
		//Only Tag 0XX
		if(rangTag==10)
		  rangeTagFrom="010";
		else
		 rangeTagFrom=""+rangTag;
		//Delta of 99 tag
		if(rangTag!=0)
			 rangeTagTo = (short) (rangTag+99);
		//Filters
		String filtro=" and bc.key.marcSecondIndicator <> '@' and bc.databaseFirstValue = ct.code ";
		String filterTag=" and bc.key.marcTag between '" +rangeTagFrom+ "' and '"+rangeTagTo+"'";
		if(rangTag==0)
			filterTag="";
		String order = SEQUENCE_ORDER;
		if (alphabeticOrder)
			order = ALPHABETICAL_ORDER;
		try {
			Session s = currentSession();
			listCodeTable =
				s.find("select distinct ct from "
						+ c.getName()
						+ " as ct, BibliographicCorrelation as bc "
						+ " where ct.obsoleteIndicator = 0 and bc.key.marcTagCategoryCode= 7"
						+ filterTag
						+ filtro
						+ order);
		} catch (HibernateException e) {
			logAndWrap(e);
		}
		logger.debug("Got codetable for " + c.getName());
		return listCodeTable;
	}

}