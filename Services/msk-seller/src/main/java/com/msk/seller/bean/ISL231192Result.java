package com.msk.seller.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.msk.core.entity.SlPdArtno;

/**
 * Created by Administrator on 2016/3/16.
 */
@JsonIgnoreProperties(value={"crtId","crtTime","updId","updTime","delFlg","actId","actTime","artnoId","slCode","slPdArtno","slCodeDis"})
public class ISL231192Result extends SlPdArtno {

}
