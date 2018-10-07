package org.folio.cataloging.dao.persistence;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * @author Hibernate CodeGenerator
 */
public class CasFascicoli implements Serializable {

  /**
   * identifier field
   */
  private Long idFascicolo;

  /**
   * nullable persistent field
   */
  private Long bibItmNbrMadre;

  /**
   * nullable persistent field
   */
  private Long bibItmNbrFiglia;

  /**
   * nullable persistent field
   */
  private String anno;

  /**
   * nullable persistent field
   */
  private String volume;

  /**
   * nullable persistent field
   */
  private String fascicolo;

  /**
   * full constructor
   */
  public CasFascicoli(Long idFascicolo, Long bibItmNbrMadre, Long bibItmNbrFiglia, String anno, String volume, String fascicolo) {
    this.idFascicolo = idFascicolo;
    this.bibItmNbrMadre = bibItmNbrMadre;
    this.bibItmNbrFiglia = bibItmNbrFiglia;
    this.anno = anno;
    this.volume = volume;
    this.fascicolo = fascicolo;
  }

  /**
   * default constructor
   */
  public CasFascicoli() {
  }

  /**
   * minimal constructor
   */
  public CasFascicoli(Long idFascicolo) {
    this.idFascicolo = idFascicolo;
  }

  public Long getIdFascicolo() {
    return this.idFascicolo;
  }

  public void setIdFascicolo(Long idFascicolo) {
    this.idFascicolo = idFascicolo;
  }

  public Long getBibItmNbrMadre() {
    return this.bibItmNbrMadre;
  }

  public void setBibItmNbrMadre(Long bibItmNbrMadre) {
    this.bibItmNbrMadre = bibItmNbrMadre;
  }

  public Long getBibItmNbrFiglia() {
    return this.bibItmNbrFiglia;
  }

  public void setBibItmNbrFiglia(Long bibItmNbrFiglia) {
    this.bibItmNbrFiglia = bibItmNbrFiglia;
  }

  public String getAnno() {
    return this.anno;
  }

  public void setAnno(String anno) {
    this.anno = anno;
  }

  public String getVolume() {
    return this.volume;
  }

  public void setVolume(String volume) {
    this.volume = volume;
  }

  public String getFascicolo() {
    return this.fascicolo;
  }

  public void setFascicolo(String fascicolo) {
    this.fascicolo = fascicolo;
  }

  public String toString() {
    return new ToStringBuilder (this)
      .append ("idFascicolo", getIdFascicolo ( ))
      .toString ( );
  }

}
