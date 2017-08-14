<%--
    Title:冻品管家组新增
    author:ren_qiang
    createDate:2016-09-13
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>

<style>
   /* ._sel{
        width: 175px;
    }*/
   ._input{
       width: 63%;
   }
   ._sel{
       width: 64%;
   }
   ._tr{
       width: 100%;
   }
   .td_value{
       width: 21%;
   }
    #b_save{
        float: left;margin-top:3px;
    }
</style>


<div class="page-content list-page">

    <form action="${ctx}/BS2102123/save" method="post" id="bs2102123FormId" enctype="multipart/form-data">
            <table width="100%"  cellspacing="10px">
                <tbody>
                <tr class="_tr">
                    <td align="right" >
                        冻品管家组名称<span style="color: red">(*必填)</span>
                    </td>
                    <td align="" class="td_value">
                        <input id="hkGroupName" name="hkGroupName" class="_input"/>
                        <span id="hkGroupName_sp"></span>
                    </td>
                </tr>

                <tr>
                    <td  class="td_name" align="right">物流区<span style="color: red">(*必选)</span></td>
                    <td class="td_value" >
                        <input type="hidden" name="lgcsAreaName" id="lgcsAreaName1" value=""/>
                        <select class="_sel" name="lgcsAreaCode" id="wl_select1" >
                            <option value="">请选择</option>
                            <c:forEach items="${lgcsAreaList}" var="lgcsAreaBean" varStatus="i">
                                <option value="${lgcsAreaBean.lgcsAreaCode}">${lgcsAreaBean.lgcsAreaName}</option>
                            </c:forEach>
                        </select>
                        <span id="lgcsAreaCode_sp"></span>
                    </td>

                </tr>

                <tr>
                    <td class="td_name" align="right">地区<span style="color: red">(*必选)</span></td>
                    <td class="td_value">
                        <input type="hidden" name="cityName" id="cityName"/>
                        <select class="_sel" name="cityCode" id="citySel1">
                            <option value="">请选择</option>
                            <c:forEach items="${cityList}" var="city" varStatus="i">
                                 <option value="${city.cityCode}">${city.cityName}</option>
                             </c:forEach>
                        </select>
                        <span id="cityCode_sp"></span>
                    </td>

                </tr>

                <tr>
                    <td class="td_name" align="right">买家类型<span style="color: red">(*必选)</span></td>
                    <td class="td_value">
                        <input type="hidden" name="buyerTypeName" id="buyerTypeName"/>
                        <select class="_sel" name="buyerType" id="buyerType_sel1">
                            <option value="">请选择</option>
                            <c:forEach items="${buyerTypeList}" var="buyerTypeDemo" varStatus="i">
                                <option value="${buyerTypeDemo.buyerType}">${buyerTypeDemo.buyerTypeName}</option>
                            </c:forEach>
                        </select>
                        <span id="buyerType_sp"></span>
                    </td>

                </tr>

                <tr>
                    <td class="td_name" align="right">产品一级分类<span style="color: red">(*必选)</span></td>
                    <td  class="td_value">
                        <input type="hidden" name="classesName" id="classesName"/>
                        <select class="_sel" name="classesCode" id="classesCode_sel1">
                            <option value="">请选择</option>
                            <c:forEach items="${pdClasseslst}" var="pdClasses" varStatus="i">
                                <option value="${pdClasses.classesCode}">${pdClasses.classesName}</option>
                            </c:forEach>
                        </select>
                        <span id="classesCode_sp"></span>
                    </td>

                </tr>

                <tr>
                    <td class="td_name" align="right">产品二级分类<span style="color: red">(*必选)</span></td>
                    <td class="td_value">
                        <input type="hidden" name="machiningNameU" id="machiningName"/>
                        <select class="_sel" name="machiningCodeU" id="machiningCode_sel1">
                            <option value="">请选择</option>
                        </select>
                        <span id="machiningCodeU_sp"></span>
                    </td>

                </tr>
                </tbody>
                <tr>
                    <td >
                        <msk:button buttonId="BS2102123.SAVE" buttonType="button" buttonValue="保存"></msk:button>
                    </td>
                    <td></td>
                </tr>
            </table>

      <%--  <div id="b_save">
            <msk:button buttonId="BS2102123.SAVE" buttonType="button" buttonValue="保存"></msk:button>
        </div>--%>
    </form>
</div>
<script src="${ctx}/static/bs/js/BS2102123.js"></script>
