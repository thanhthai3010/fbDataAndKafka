package com.fb.crawlData;

import java.io.Serializable;

public class StatusData implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String status_id;
  private String status_message;
  private String status_author;
  private String link_name;
  private String status_type;
  private String status_link;
  private String status_permalink_url;
  private String status_published;
  private Long num_reactions;
  private Long num_comments;
  private Long num_shares;
  private int num_likes;
  private int num_loves;
  private int num_wows;
  private int num_hahas;
  private int num_sads;
  private int num_angrys;

  public String getStatus_id() {
    return status_id;
  }

  public void setStatus_id(String status_id) {
    this.status_id = status_id;
  }

  public String getStatus_message() {
    return status_message;
  }

  public void setStatus_message(String status_message) {
    this.status_message = status_message;
  }

  public String getStatus_author() {
    return status_author;
  }

  public void setStatus_author(String status_author) {
    this.status_author = status_author;
  }

  public String getLink_name() {
    return link_name;
  }

  public void setLink_name(String link_name) {
    this.link_name = link_name;
  }

  public String getStatus_type() {
    return status_type;
  }

  public void setStatus_type(String status_type) {
    this.status_type = status_type;
  }

  public String getStatus_link() {
    return status_link;
  }

  public void setStatus_link(String status_link) {
    this.status_link = status_link;
  }

  public String getStatus_permalink_url() {
    return status_permalink_url;
  }

  public void setStatus_permalink_url(String status_permalink_url) {
    this.status_permalink_url = status_permalink_url;
  }

  public String getStatus_published() {
    return status_published;
  }

  public void setStatus_published(String status_published) {
    this.status_published = status_published;
  }

  public Long getNum_reactions() {
    return num_reactions;
  }

  public void setNum_reactions(Long num_reactions) {
    this.num_reactions = num_reactions;
  }

  public Long getNum_comments() {
    return num_comments;
  }

  public void setNum_comments(Long num_comments) {
    this.num_comments = num_comments;
  }

  public Long getNum_shares() {
    return num_shares;
  }

  public void setNum_shares(Long num_shares) {
    this.num_shares = num_shares;
  }

  public int getNum_likes() {
    return num_likes;
  }

  public void setNum_likes(int num_likes) {
    this.num_likes = num_likes;
  }

  public int getNum_loves() {
    return num_loves;
  }

  public void setNum_loves(int num_loves) {
    this.num_loves = num_loves;
  }

  public int getNum_wows() {
    return num_wows;
  }

  public void setNum_wows(int num_wows) {
    this.num_wows = num_wows;
  }

  public int getNum_hahas() {
    return num_hahas;
  }

  public void setNum_hahas(int num_hahas) {
    this.num_hahas = num_hahas;
  }

  public int getNum_sads() {
    return num_sads;
  }

  public void setNum_sads(int num_sads) {
    this.num_sads = num_sads;
  }

  public int getNum_angrys() {
    return num_angrys;
  }

  public void setNum_angrys(int num_angrys) {
    this.num_angrys = num_angrys;
  }

  public StatusData(String status_id, String status_message, String status_author, String link_name,
      String status_type, String status_link, String status_permalink_url, String status_published,
      Long num_reactions, Long num_comments, Long num_shares, int num_likes, int num_loves,
      int num_wows, int num_hahas, int num_sads, int num_angrys) {
    super();
    this.status_id = status_id;
    this.status_message = status_message;
    this.status_author = status_author;
    this.link_name = link_name;
    this.status_type = status_type;
    this.status_link = status_link;
    this.status_permalink_url = status_permalink_url;
    this.status_published = status_published;
    this.num_reactions = num_reactions;
    this.num_comments = num_comments;
    this.num_shares = num_shares;
    this.num_likes = num_likes;
    this.num_loves = num_loves;
    this.num_wows = num_wows;
    this.num_hahas = num_hahas;
    this.num_sads = num_sads;
    this.num_angrys = num_angrys;
  }

  public StatusData() {
    super();
    // TODO Auto-generated constructor stub
  }
}
