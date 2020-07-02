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
@ApiModel(value="角色表", description="角色表")
public class Role implements Serializable {

  private static final long serialVersionUID =  2753745390313911700L;

  /**主键*/
  @ApiModelProperty(value="主键", name = "id", dataType = "Long")
  private Long id;

  /**类型*/
  @ApiModelProperty(value="类型", name = "type", dataType = "Integer")
  private Integer type;

  /**编码*/
  @ApiModelProperty(value="编码", name = "code", dataType = "String")
  private String code;

  /**名称*/
  @ApiModelProperty(value="名称", name = "name", dataType = "String")
  private String name;

  /**父ID*/
  @ApiModelProperty(value="父ID", name = "parentId", dataType = "Long")
  private Long parentId;

  /**状态*/
  @ApiModelProperty(value="状态", name = "state", dataType = "Integer")
  private Integer state;

  /**是否删除 0未删除 1已删除*/
  @ApiModelProperty(value="是否删除 0未删除 1已删除", name = "deleted", dataType = "Integer")
  private Integer deleted;

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
