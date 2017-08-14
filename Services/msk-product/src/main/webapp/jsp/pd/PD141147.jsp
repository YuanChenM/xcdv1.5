<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Title:产品原种饲养标准指标
    autdor:zhou_ling
    createDate:2016-03-01
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<navigation:header title="产品原种种源标准指标修改" backTitleArray="产品分类目录管理" backUrlArray="${ctx}/PD141124/init?classesCode=${classesCode}&machiningCode=${machiningCode}&breedCode=${breedCode}&featureCode=${featureCode}&weightCode=${weightCode}"></navigation:header>
<div class="page-content list-page" style="height:100%">
<form action="${ctx}/pd141147/save" metdod="post" id="PD141147Form">
    <input type="hidden" name="standardId" value="${standardId}"/>
    <input type="hidden" name="classesCode" value="${classesCode}"/>
    <div class="page-content list-page">
        <div>
            </p>
            <table WIDTH="100%">
                <tr>
                    <td width="50%">产品品种:${breedName}</td>
                    <c:if test="${feaNames != null and feaNames!=''}">
                        <td width="50%">产品特征:${feaNames}</td>
                    </c:if>
                </tr>
            </table>
            </p>
        </div>
        <div class="group-accordion" collapsible="false" active="false">
            <h3>
                <label产品原种种源标准指标修改</label>
            </h3>


            <table class="tree dataTable no-footer" id="PD141146_list_grid" WIDTH="100%">
                <tr style="background: #DBDBDB;width:100%" >
                    <td  width="25%" rowspan="2" align="center" height="">指标内容</td>
                    <td colspan="3" align="center">等级</td>
                </tr>
                <tr style="background: #DBDBDB;width:100%">
                    <td  align="center" width="25%" >优良</td>
                    <td  align="center" width="25%">一般</td>
                    <td  align="center" width="25%">差</td>
                </tr>
                <c:forEach items="${list}" var="item" >
                    <tr >
                        <td width="25%"  align="center">${item.fedStdItemName}</td>

                        <td width="25%">
                            <textarea name="fedGoodValArray"  rows="3" cols="30" style="resize: none;">${item.fedGoodVal}</textarea>
                        </td>

                        <td width="25%">
                            <textarea name="fedNormalValArray"  rows="3" cols="30" style="resize: none;">${item.fedNormalVal}</textarea>
                        </td>

                        <td >
                            <textarea name="fedBadValArray"  rows="3" cols="30" style="resize: none;">${item.fedBadVal}</textarea>
                        </td>

                        <td style="display:none;"><input type="hidden"  name="fedStdItemIdArray" id="fedStdItemIdArray" value="${item.fedStdItemId}"/></td>
                    </tr>
                </c:forEach>
            </table>
            </div>

        <div>
            <msk:button buttonValue="保存" buttonId="PD141147.SAVE" buttonType="button"/>
        </div>
     </div>
</form>
</div>
<script type="text/javascript" src="${ctx}/static/js/pd/PD141147.js"></script>
