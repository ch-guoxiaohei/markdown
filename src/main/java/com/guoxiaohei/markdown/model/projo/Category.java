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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "t_category")
@Entity
public class Category {

  @Id
  @Column(name = "c_id" ,length = 32)
  private String id;

  @Column(name = "c_name" , length = 300)
  private String name;

  @Column(name = "dt_create_time")
  private Date createTime;

  @Column(name = "dt_update_time")
  private Date updateTime;

}
