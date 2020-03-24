package com.liuqitech.demo.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class User {

  @GeneratedValue
  @Id
  private Integer userId;
  @Column
  private String username;
  @Column
  private String password;
  @Column
  private String role;

}
