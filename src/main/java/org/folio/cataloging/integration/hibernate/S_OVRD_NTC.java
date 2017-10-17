/*
 * (c) LibriCore
 * 
 * Created on 27-may-2005
 * 
 * S_OBRD_NTC.java
 */
package org.folio.cataloging.integration.hibernate;

import java.io.Serializable;
import java.util.Date;

 /**
 * @author Ruben
 * @version $Revision: 1.2 $, $Date: 2005/12/21 13:33:33 $
 * @since 1.0
 */
public class S_OVRD_NTC implements Serializable{
    
    private int OverdueNoticeNumber;
    private int copyIdNumber;
    private int borrowerNumber; 
    private short overdueNoticeType;
    private Date ActivityTimeDate;
    private char overdueNoticePrintInd;
    private char emailInd;
    
    
    
    

    /**
     * @return Returns the activityTimeDate.
     * @exception
     * @since 1.0
     */
    public Date getActivityTimeDate() {
        return ActivityTimeDate;
    }
    /**
     * @param activityTimeDate The activityTimeDate to set.
     * @exception
     * @since 1.0
     */
    public void setActivityTimeDate(Date activityTimeDate) {
        ActivityTimeDate = activityTimeDate;
    }
    /**
     * @return Returns the borrowerNumber.
     * @exception
     * @since 1.0
     */
    public int getBorrowerNumber() {
        return borrowerNumber;
    }
    /**
     * @param borrowerNumber The borrowerNumber to set.
     * @exception
     * @since 1.0
     */
    public void setBorrowerNumber(int borrowerNumber) {
        this.borrowerNumber = borrowerNumber;
    }
    
    /**
     * @return Returns the copyIdNumber.
     * @exception
     * @since 1.0
     */
    public int getCopyIdNumber() {
        return copyIdNumber;
    }
    /**
     * @param copyIdNumber The copyIdNumber to set.
     * @exception
     * @since 1.0
     */
    public void setCopyIdNumber(int copyIdNumber) {
        this.copyIdNumber = copyIdNumber;
    }
    /**
     * @return Returns the emailInd.
     * @exception
     * @since 1.0
     */
    public char getEmailInd() {
        return emailInd;
    }
    /**
     * @param emailInd The emailInd to set.
     * @exception
     * @since 1.0
     */
    public void setEmailInd(char emailInd) {
        this.emailInd = emailInd;
    }
    /**
     * @return Returns the overdueNoticeNumber.
     * @exception
     * @since 1.0
     */
    public int getOverdueNoticeNumber() {
        return OverdueNoticeNumber;
    }
    /**
     * @param overdueNoticeNumber The overdueNoticeNumber to set.
     * @exception
     * @since 1.0
     */
    public void setOverdueNoticeNumber(int overdueNoticeNumber) {
        OverdueNoticeNumber = overdueNoticeNumber;
    }
    /**
     * @return Returns the overdueNoticePrintInd.
     * @exception
     * @since 1.0
     */
    public char getOverdueNoticePrintInd() {
        return overdueNoticePrintInd;
    }
    /**
     * @param overdueNoticePrintInd The overdueNoticePrintInd to set.
     * @exception
     * @since 1.0
     */
    public void setOverdueNoticePrintInd(char overdueNoticePrintInd) {
        this.overdueNoticePrintInd = overdueNoticePrintInd;
    }
    /**
     * @return Returns the overdueNoticeType.
     * @exception
     * @since 1.0
     */
    public short getOverdueNoticeType() {
        return overdueNoticeType;
    }
    /**
     * @param overdueNoticeType The overdueNoticeType to set.
     * @exception
     * @since 1.0
     */
    public void setOverdueNoticeType(short overdueNoticeType) {
        this.overdueNoticeType = overdueNoticeType;
    }
}
