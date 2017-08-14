package com.msk.product.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.msk.common.bean.RsPageParam;
import io.swagger.annotations.ApiModel;

/**
 * 封装参数
 * IPD141112RsParam.
 *
 * @author xhy
 */
@ApiModel(value = "IPD141112RsParam", description = "param")
@JsonIgnoreProperties(value = {"delFlg", "crtId", "crtTime", "updId", "updTime", "ver", "actId"})
public class IPD141112RsParam extends RsPageParam {

    private static final long serialVersionUID = 1L;

}