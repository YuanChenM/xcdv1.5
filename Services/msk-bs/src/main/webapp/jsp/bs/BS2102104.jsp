<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Title:冻品管家组管理一览
    author:ren_qiang
    createDate:2016-8-1
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/codemaster" %>
<style>
    .td_name{
        width: 8%;
    }
    .td_value{
        align:left;
        width: 12%;
    }
    ._input{
        width: 80%;
    }
    ._sel{
        width: 80%;
    }
/*    .td_address{
        width: 45px;
    }*/
    #b_save{
        float: left;margin-top:3px;
    }
    #BS2102104_SEARCH{
        width: 50px;
    }
    #BS2102104_SAVE{
        width: 50px;
    }
</style>
<%--<msk2:codemaster codeType="BuyerType" viewType="json"/>--%>
        <navigation:header title="冻品管家组一览" backTitleArray=""
                           backUrlArray=""></navigation:header>

<div class="page-content list-page">

    <form action="${ctx}/BS2102104/search" method="post" id="bs2102104FormId">
        <div class="group-accordion" collapsible="false" active="false" >
            <h3>
                <label>冻品管家组查询条件</label>
            </h3>
            <table width="100%">
                <tr>
                    <td  align="right" width="150px">物流区</td>
                    <td width="150px">
                        <select name="filterMap[lgcsAreaCode]" id="wl_select">
                            <option value="">请选择</option>
                            <c:forEach items="${lgcsAreaList}" var="lgcsAreaBean" varStatus="i">
                                <option value="${lgcsAreaBean.lgcsAreaCode}">${lgcsAreaBean.lgcsAreaName}</option>
                            </c:forEach>
                        </select>
                    </td>

                    <td align="right" width="150px">地区</td>
                    <td width="150px">
                        <select name="filterMap[cityCode]" id="citySel">
                            <option value="">请选择</option>
                            <c:forEach items="${cityList}" var="city" varStatus="i">
                                 <option value="${city.cityCode}">${city.cityName}</option>
                             </c:forEach>
                        </select>
                    </td>
                    <td align="right" width="150px">买家类型</td>
                    <td width="150px">
                        <select name="filterMap[buyerType]" id="buyerType_sel">
                            <option value="">请选择</option>
                            <c:forEach items="${buyerTypeList}" var="buyerTypeDemo" varStatus="i">
                                <option value="${buyerTypeDemo.buyerType}">${buyerTypeDemo.buyerTypeName}</option>
                            </c:forEach>
                        </select>

                    </td>
                    <td align="right" width="150px">产品一级分类</td>
                    <td width="150px">
                        <select name="filterMap[classesCode]" id="classesCode_sel">
                            <option value="">请选择</option>
                            <c:forEach items="${pdClasseslst}" var="pdClasses" varStatus="i">
                                <option value="${pdClasses.classesCode}">${pdClasses.classesName}</option>
                            </c:forEach>
                        </select>

                    </td>
                    <td width="150px" align="right">产品二级分类</td>
                    <td width="150px">
                        <select name="filterMap[machiningCode]" id="machiningCode_sel">
                            <option value="">请选择</option>
                        </select>
                    </td>
                    <td>
                        <msk:button buttonId="BS2102104.SEARCH" buttonType="button"  buttonValue="查询"></msk:button>
                    </td>
              </tr>

            </table>

        </div>

            <table id="bs2102104_list_grid" showAddBtn="true" width="100%">
                <thead>
                <tr>
                    <th coltype="sno" width="20px">序号</th>
                    <th coltype="text" name="lgcsAreaName" >物流区</th>
                    <th coltype="text"  name="cityName" >地区</th>
                    <th coltype="text" name="buyerTypeName">买家类型</th>
                    <th coltype="text"  name="classesName" >产品一级分类</th>
                    <th coltype="text"  name="machiningNameU">产品二级分类</th>
                    <th coltype="text" cellEdit="true" edit="true" name="hkGroupName" maxLength="30">冻品管家组名称</th>
                    <th coltype="action">操作
                        <msk:button buttonId="BS2102104.EDIT" coltype="edit" class="h-button" useable="can_edit" buttonType="hidden" buttonValue="编辑"/>
                    </th>

                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>

        <div id="b_save">
            <msk:button buttonId="BS2102104.NEW" buttonType="button" buttonValue="新增"></msk:button>
            <msk:button buttonId="BS2102104.SAVE" buttonType="button" buttonValue="保存"></msk:button>
        </div>
    </form>
</div>
<script src="${ctx}/static/bs/js/BS2102104.js"></script>
