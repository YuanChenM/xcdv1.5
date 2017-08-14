<%--
  买家基本信息
  User: zhangqiang1
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<div class="group-accordion" active="true">
    <h3>
        <label>配送要求信息</label>
    </h3>
    <table style="width: 100%" CellSpacing=4>
        <tr>
            <td width="25%" align="right">习惯正常收货时间段 :</td>
            <td width="25%" align="left">${orderDelivery.receiveTime }</td>
            <td width="25%" align="right">习惯收货最早时间要求 :</td>
            <td width="25%" align="left">${orderDelivery.receiveEarliest }</td>
        </tr>
        <tr>
            <td width="25%" align="right">习惯收货最晚时间要求 :</td>
            <td align="left" colspan="3">${orderDelivery.receiveLast }</td>
        </tr>
        <%-- <tr>
             <td width="25%" align="right">预计发货时间:</td>
             <td width="25%" align="left"><fmt:formatDate value="${orderDelivery.forecastSendTime}" type="both"
                                                          dateStyle="default"/></td>
             <td width="25%" align="right">预计到货时间:</td>
             <td width="25%" align="left"><fmt:formatDate value="${orderDelivery.forecastReceiveTime}" type="both"
                                                          dateStyle="default"/></td>
         </tr>--%>
        <tr>
            <td width="25%" align="right">发货时间 :</td>
            <td width="25%" align="left">
                <fmt:formatDate value="${sendDateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td width="25%" align="right">收货时间 :</td>
            <td width="25%" align="left">
                <fmt:formatDate value="${receiveDateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
        </tr>
        <%--   <tr>
               <td width="25%" align="right">收货等待时间:</td>
               <td width="25%" align="left">${orderDelivery.receiveWaitTime }</td>
               <td width="25%" align="right">提前通知时间:</td>
               <td width="25%" align="left">${orderDelivery.advanceNoticeTime }</td>
           </tr>--%>
        <tr>
            <td width="25%" align="right">动检证要求 :</td>
            <td width="25%" align="left">${orderDelivery.vicFlg }</td>
            <td width="25%" align="right">装卸要求 :</td>
            <td width="25%" align="left">${orderDelivery.unloadReq }</td>
        </tr>
        <tr>
            <td width="25%" align="right">包装要求 :</td>
            <td width="25%" align="left">${orderDelivery.packingReq }</td>
            <td width="25%" align="right">距离门店最近停车距离(米) :</td>
            <td width="25%" align="left">${orderDelivery.parkingDistance }</td>
        </tr>
        <tr>
            <td width="25%" align="right">其他配送服务要求 :</td>
            <td width="25%" align="left">${orderDelivery.otherDeliveryReq }</td>
            <td width="25%" align="right">本次配送服务要求 :</td>
            <td width="25%" align="left">${orderDelivery.thisDeliveryReq }</td>
        </tr>
    </table>
</div>     

