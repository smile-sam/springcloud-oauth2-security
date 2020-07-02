package com.ms.domain.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 
 * @Date: Created in 2020-05-22 19:17:29
 * @Author: sam
 * @Modified By:
 */
@Data
@ToString
@ApiModel(value="用户表", description="用户表")
public class User implements Serializable {

  private static final long serialVersionUID =  5668727401353106028L;

  /**主键*/
  @ApiModelProperty(value="主键", name = "id", dataType = "Long")
  private Long id;

  /**账号*/
  @ApiModelProperty(value="账号", name = "account", dataType = "String")
  private String account;

  /**密码*/
  @ApiModelProperty(value="密码", name = "password", dataType = "String")
  private String password;

  /**盐值*/
  @ApiModelProperty(value="盐值", name = "salt", dataType = "String")
  private String salt;

  /**名字*/
  @ApiModelProperty(value="名字", name = "userName", dataType = "String")
  private String userName;

  /**别名*/
  @ApiModelProperty(value="别名", name = "alias", dataType = "String")
  private String alias;

  /**头像*/
  @ApiModelProperty(value="头像", name = "avatar", dataType = "String")
  private String avatar;

  /**性别 1 男 2 女 */
  @ApiModelProperty(value="性别 1 男 2 女 ", name = "sex", dataType = "Integer")
  private Integer sex;

  /**生日*/
  @ApiModelProperty(value="生日", name = "birthday", dataType = "Date")
  private Date birthday;

  /**联系方式*/
  @ApiModelProperty(value="联系方式", name = "phone", dataType = "String")
  private String phone;

  /**邮箱*/
  @ApiModelProperty(value="邮箱", name = "email", dataType = "String")
  private String email;

  /**类型 先冗余*/
  @ApiModelProperty(value="类型 先冗余", name = "userType", dataType = "Integer")
  private Integer userType;

  /**是否删除 0 未删除 1 已删除*/
  @ApiModelProperty(value="是否删除 0 未删除 1 已删除", name = "deleted", dataType = "Integer")
  private Integer deleted;

  /**状态*/
  @ApiModelProperty(value="状态", name = "state", dataType = "Integer")
  private Integer state;

  /**创建人*/
  @ApiModelProperty(value="创建人", name = "createUserId", dataType = "Long")
  private Long createUserId;

  /**创建时间*/
  @ApiModelProperty(value="创建时间", name = "createTime", dataType = "Date")
  private Date createTime;

  /**修改人*/
  @ApiModelProperty(value="修改人", name = "updateUserId", dataType = "Long")
  private Long updateUserId;

  /**修改时间*/
  @ApiModelProperty(value="修改时间", name = "updateTime", dataType = "Date")
  private Date updateTime;

}
