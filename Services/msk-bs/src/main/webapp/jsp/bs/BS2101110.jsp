<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<div class="group-accordion" active="true">
    <h3>
        <label>买手店铺基本信息编辑</label>
    </h3>

    <div id="" style="display:block">
        <form:form action="${ctx}/BS2101110/update" id="BS2101110Form" method="post" enctype="multipart/form-data">
            <input type="hidden" id="loginId" name="loginId" value="${loginUser.emplId}"/>
            <table width="100%" border="0" cellpadding="0" cellspacing="10px">
                <tr>
                    <td align="right" width="18%">店铺名称</td>
                    <td align="left" width="">
                        <input style="width:200px;" type="text" id="shopName" name="shopName"
                               value="${slShopInfo.shopName}" onblur=""/>
                        <input type="hidden" id="bsSlCode" name="slCode" value="${slShopInfo.slCode}"/>
                    </td>
                </tr>
                    <%--  <tr>
                          <c:if test="${flagNum =='1'}">
                              <td/>
                          </c:if>
                          <c:if test="${flagNum ==''|| flagNum ==null}">
                              <td align="right" width="">店铺Id　</td>
                              <td align="left" width="">
                                  <input style="width:200px;" type="text" id="shopId" name="shopId"
                                         value="${slShopInfo.shopId}" onblur=""/>
                              </td>
                          </c:if>
                      </tr>--%>
                <tr>
                    <td align="right" width="">店铺logo</td>
                    <td align="left" width="">
                        <c:if test="${not empty slShopInfo.shopLogo}">
                            <img src="${slShopInfo.shopLogo}" alt="店铺logo" height="100px" width="100px">
                        </c:if>
                        <input type="hidden" id="shopLogo" name="shopLogo" value="${slShopInfo.shopLogo}"/>
                        <input type="file" name="file"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="">经营特色1</td>
                    <td align="left" width="">
                        <textarea style="width:200px;" type="text" id="managingCharact1" name="managingCharact1">${slShopInfo.managingCharact1}</textarea>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="">经营特色2</td>
                    <td align="left" width="">
                        <textarea style="width:200px;" type="text" id="managingCharact2" name="managingCharact2">${slShopInfo.managingCharact2}</textarea>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="">经营特色3</td>
                    <td align="left" width="">
                        <textarea style="width:200px;" type="text" id="managingCharact3" name="managingCharact3">${slShopInfo.managingCharact3}</textarea>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td align="left">
                        <msk:button buttonValue="保存" buttonId="BS2101110.SAVE" buttonType="button"/>
                    </td>
                </tr>
            </table>
        </form:form>
    </div>
</div>
<script src="${ctx}/static/bs/js/BS2101110.js"></script>
