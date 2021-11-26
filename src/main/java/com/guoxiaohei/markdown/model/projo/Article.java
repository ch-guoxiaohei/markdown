package com.guoxiaohei.markdown.model.projo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@Entity
@Table(name = "t_post")
@NoArgsConstructor
@AllArgsConstructor
public class Article {

  @Id
  @Column(name = "c_id", length = 32)
  private String id;

  @Column(name = "c_title", length = 300)
  private String title;

  @Column(name = "c_content")
  private String content;

  @Column(name = "c_origin_content")
  private String originContent;

  @Column(name = "c_user_id")
  private String userId;

  @Column(name = "dt_create_time")
  private Date createTime;

  @Column(name = "dt_update_time")
  private Date updateTime;

}
