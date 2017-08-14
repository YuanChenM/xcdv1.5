<%--
  买家基本信息
  User: zhangqiang1
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<div class="group-accordion" active="true"  >
    <h3>
        <label>买家基本信息</label>
    </h3>
    <table style="width: 100%" CellSpacing=4>
        <tr>
            <td width="25%" align="right">买家编码 : </td>
            <td width="25%" align="left">${buyerCode }</td>
            <td width="25%" align="right">买家名称 : </td>
            <td width="25%" align="left">${buyerName }</td>
        </tr>
        <tr>
            <td width="25%" align="right">买家类型 : </td>
            <td colspan="3" align="left">
                <c:if test="${ not empty buyerType}">
                    <msk:codemaster codeType="OrderBuyerType" viewType="label" modelName="ORDER" codeValue="${buyerType}"/>
                </c:if>
            </td>
        </tr>
        <tr>
            <td width="25%" align="right">收货人姓名 : </td>
            <td width="25%" align="left">${orderBuyers.receiverName }</td>
            <td width="25%" align="right">收货人电话 : </td>
            <td width="25%" align="left">${orderBuyers.receiverTel }</td>
        </tr>
        <tr>
            <td width="25%" align="right">收货人微信 : </td>
            <td width="25%" align="left">${orderBuyers.receiverWeixin }</td>
            <td width="25%" align="right">收货人QQ : </td>
            <td width="25%" align="left">${orderBuyers.receiverQq }</td>
        </tr>
        <tr>
            <td width="25%" align="right">收货人省份 : </td>
            <td width="25%" align="left">${orderBuyers.receiverProvince }</td>
            <td width="25%" align="right">收货人城市 : </td>
            <td width="25%" align="left">${orderBuyers.receiverCity }</td>
        </tr>
        <tr>
            <td width="25%" align="right">收货人区/街道 : </td>
            <td align="left">${orderBuyers.receiverDistrict }</td>
            <td width="25%" align="right">地址详细信息 : </td>
            <td width="25%" align="left">${orderBuyers.receiverAddr }</td>
        </tr>
        <tr>
            <td width="25%" align="right">辅助收货地址 : </td>
            <td colspan="3" align="left">${orderBuyers.receiverAddr2}</td>
            <td/>
        </tr>
    </table>
</div>

