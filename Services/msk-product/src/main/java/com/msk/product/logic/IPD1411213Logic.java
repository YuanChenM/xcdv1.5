package com.msk.product.logic;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsResponse;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.ByBuyerBasicInfo;
import com.msk.core.entity.PdReportInfo;
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.LgcsAreaBean;
import com.msk.product.bean.*;
import com.msk.product.utils.RestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by gao_min on 2016/10/10.
 */
@Service
public class IPD1411213Logic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId {
        // 查询举报类型是否存在.
        static final String SQL_ID_QUERY_REPORT_TYPE = "queryReportType";
    }

    @Autowired
    private CommonLogic commonLogic;

    /**
     * @return IPD1411213RsResult 举报类型
     * @author gao_min
     */
    @Transactional
    public RsResponse<IPD1411213RsResult> insertReportInfo(IPD1411213RsParam param) {

        RsResponse<IPD1411213RsResult> rsResponse = new RsResponse<IPD1411213RsResult>();
        IPD1411213RsResult rsResult = new IPD1411213RsResult();
        // 数据库表映射模型.
        PdReportInfo pdReportInfo = new PdReportInfo();
        // 用户ID请求参数校验.
        IPD1411213Param iPD1411213Param = new IPD1411213Param();

        boolean ifExit = true;
        switch (param.getUserType()) {
            case "1":
                // 买家ID校验.
                iPD1411213Param.setBuyerId(param.getUserId());
                RsResponse<ByBuyerBasicInfo> byBuyerBasicInfo = RestUtil.getBuyersInfo(iPD1411213Param);
                if (byBuyerBasicInfo.getStatus().equals("F") || null == byBuyerBasicInfo.getResult().getBuyerId()) {
                    ifExit = false;
                }
                break;
            case "2":
                // 查询买手信息表中请求的买手编码是否存在.
                iPD1411213Param.setSlCode(param.getUserId());
                RsResponse<IPD1411213BsParam> bsBasicInfo = RestUtil.getBsBasicInfo(iPD1411213Param);
                if (bsBasicInfo.getStatus().equals("F") || 0 == bsBasicInfo.getResult().getBuyershopList().size()) {
                    ifExit = false;
                }
                break;
            case "3":
                // 查询冻品管家信息表中请求的买手编码是否存在.
                iPD1411213Param.setHouseCode(param.getUserId());
                RsResponse<IPD1411213HouseParam> houseAccountInfo = RestUtil.getHouseAccountInfo(iPD1411213Param);
                if (houseAccountInfo.getStatus().equals("F") || 0 == houseAccountInfo.getResult().getHouseList().size()) {
                    ifExit = false;
                }
                break;
            default:
                rsResponse.setStatus(SystemConst.RsStatus.FAIL);
                rsResponse.setMessage("用户类型错误！");
                return rsResponse;
        }
        if (!ifExit) {
            rsResponse.setStatus(SystemConst.RsStatus.FAIL);
            rsResponse.setMessage("用户ID不存在！");
            return rsResponse;
        }
        // 物流区编码校验.
        DistrictParam districtParam = new DistrictParam();
        districtParam.setLgcsAreaCode(param.getLgcsCode());
        List<LgcsAreaBean> districtList = RestUtil.findDistrict(districtParam);

        if (0 == districtList.size()) {
            rsResponse.setStatus(SystemConst.RsStatus.FAIL);
            rsResponse.setMessage("物流区编码不存在！");
            return rsResponse;
        }
        int count = 0;
        // 举报类型校验.
        count = this.getCount(SqlId.SQL_ID_QUERY_REPORT_TYPE, param);
        if (0 == count) {
            rsResponse.setStatus(SystemConst.RsStatus.FAIL);
            rsResponse.setMessage("举报类型不存在！");
            return rsResponse;
        }
        // 产品编码校验.
        // 产品编码是十位.
        if (NumberConst.IntDef.INT_TEN > param.getProductCode().length()) {
            rsResponse.setStatus(SystemConst.RsStatus.FAIL);
            rsResponse.setMessage("请传入十位产品编码！");
            return rsResponse;
        }
        PDInfoParam pDInfoParam = new PDInfoParam();
        pDInfoParam.setPdCode(param.getProductCode());
        RsResponse<PDInfoResult> rsPdInfoResult = RestUtil.findPdCode(pDInfoParam);
        if (null == rsPdInfoResult.getResult().getPdCode()) {
            rsResponse.setStatus(SystemConst.RsStatus.FAIL);
            rsResponse.setMessage("产品编码不存在！");
            return rsResponse;
        }

        // 举报凭证.
        if (null != param.getReportImgList()) {
            for (int i = param.getReportImgList().size(); i > 0; i--) {
                switch (i) {
                    case 1:
                        pdReportInfo.setReportImg1(param.getReportImgList().get(0));
                        break;
                    case 2:
                        pdReportInfo.setReportImg2(param.getReportImgList().get(1));
                        break;
                    case 3:
                        pdReportInfo.setReportImg3(param.getReportImgList().get(2));
                        break;
                    case 4:
                        pdReportInfo.setReportImg4(param.getReportImgList().get(3));
                        break;
                    case 5:
                        pdReportInfo.setReportImg5(param.getReportImgList().get(4));
                        break;
                    default:
                        rsResponse.setStatus(SystemConst.RsStatus.FAIL);
                        rsResponse.setMessage("最多上传五张图片！");
                        return rsResponse;
                }
            }
        }
        // 用户类型.
        pdReportInfo.setUserType(param.getUserType());
        // 用户ID.
        pdReportInfo.setUserId(param.getUserId());
        // 物流区Code.
        pdReportInfo.setLgcsCode(param.getLgcsCode());
        // 产品编码.
        pdReportInfo.setPdCode(param.getProductCode());
        // 举报类型.
        pdReportInfo.setReportTypeCode(Long.parseLong(param.getReportType()));
        // 举报内容.
        pdReportInfo.setReportDesc(param.getReportDescription());

        Integer result;
        if (null != param.getReportId()) {
            pdReportInfo.setReportId(Long.parseLong(param.getReportId()));
            pdReportInfo.setUpdId(param.getUpdId());
            pdReportInfo.setUpdTime(param.getUpdTime());
            result = this.modify(pdReportInfo);
            if (0 == result) {
                rsResponse.setStatus(SystemConst.RsStatus.FAIL);
                rsResponse.setMessage("新增修改举报信息接口失败！");
            }
        } else {
            // 新增生成新的举报单号.
            pdReportInfo.setCrtId(param.getCrtId());
            pdReportInfo.setCrtTime(param.getCrtTime());
            pdReportInfo.setActTime(param.getActTime());
            pdReportInfo.setActId(param.getActId());
            pdReportInfo.setUpdTime(param.getUpdTime());
            pdReportInfo.setUpdId(param.getUpdId());
            // 审核状态默认为0：未审核.
            pdReportInfo.setReportStatus("0");
            pdReportInfo.setDelFlg("0");
            pdReportInfo.setVer(0);
            pdReportInfo.setReportId(commonLogic.maxId("PD_REPORT_INFO", "REPORT_ID"));
            result = this.save(pdReportInfo);
            if (0 < result) {
                param.setReportId(String.valueOf(pdReportInfo.getReportId()));
            }
        }
        PdReportInfo pdReportInfo1 = this.findOne(param);
        if (null != pdReportInfo1) {
            rsResult.setReportId(String.valueOf(pdReportInfo1.getReportId()));
            rsResponse.setResult(rsResult);
        } else {
            rsResponse.setStatus(SystemConst.RsStatus.FAIL);
            rsResponse.setMessage("新增或修改举报信息接口失败！");
        }
        rsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
        rsResponse.setMessage("新增修改举报信息成功！");
        return rsResponse;
    }
}
