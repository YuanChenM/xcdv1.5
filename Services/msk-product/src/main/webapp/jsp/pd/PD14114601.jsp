<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Title:品种种源标准指标
    autdor:xhy
    createDate:2016-2-26
    updateDate:2016-2-26
    updateAutdor:xhy
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<navigation:header title="产品原种种源标准指标修改" backTitleArray="产品分类目录管理"
                   backUrlArray="${ctx}/PD141124/init?classesCode=${classesCode}&machiningCode=${machiningCode}&breedCode=${breedCode}&featureCode=${featureCode}&weightCode=${weightCode}"></navigation:header>
<script type="text/javascript">
    function callbackFun(message) {
        $.alertMessage.info(message);
        HDF.closeLoadingMask();
    }
</script>
<div class="page-content list-page" style="height:100%">
    <form action="${ctx}/pd14114601/saveAndEdit" method="post" id="PD14114601Form" enctype="multipart/form-data">
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
            <div class="group-accordion" collapsible="false" active="false" id="pd14114601accordion">
                <h3>
                    <label>产品原种种源标准指标修改</label>
                </h3>

                <div>
                    <table class="tree dataTable no-footer" WIDTH="100%">
                        <tr style="background: #DBDBDB;width:100%">
                            <td width="10%" rowspan="3" align="center" height="">指标类容</td>
                            <td colspan="6" align="center">等级</td>
                        </tr>
                        <tr style="background: #DBDBDB;width:100%">
                            <td align="center" colspan="2" width="33%">优良</td>
                            <td align="center" colspan="2" width="33%">一般</td>
                            <td align="center" colspan="2" width="34%">差</td>
                        </tr>
                        <tr style="background: #DBDBDB;width:100%">
                            <td align="center" width="15%">判定标准</td>
                            <td align="center" width="15%">标准照片</td>
                            <td align="center" width="15%">判定标准</td>
                            <td align="center" width="15%">标准照片</td>
                            <td align="center" width="15%">判定标准</td>
                            <td align="center" width="15%">标准照片</td>
                        </tr>
                        <c:forEach items="${list}" var="org">
                            <tr>
                                <td align="center">${org.orgStdItemName}</td>
                                <td><textarea name="orgGoodValArray" rows="3" cols="30"
                                              style="resize: none;">${org.orgGoodVal}</textarea></td>
                                <td>
                                    <c:if test="${org.orgGoodValFile1 !=null and org.orgGoodValFile1 != ''}">
                                        <a class="tooltip" title="优良标准图片" href="${org.orgGoodValFile1}" target="_blank"><img
                                                src="${org.orgGoodValFile1}" height="40px" width="40px"/></a>
                                    </c:if>
                                    <input type="file" name="orgMGoodValFile1" class="orgMGoodValFile1"><br/>
                                    <c:if test="${org.orgGoodValFile2 !=null and org.orgGoodValFile2 != ''}">
                                        <a class="tooltip" title="优良标准图片" href="${org.orgGoodValFile2}" target="_blank"><img
                                                src="${org.orgGoodValFile2}" height="40px" width="40px"/></a>
                                    </c:if>
                                    <input type="file" name="orgMGoodValFile2"><br/>
                                    <c:if test="${org.orgGoodValFile3 !=null and org.orgGoodValFile3 != ''}">
                                        <a class="tooltip" title="优良标准图片" href="${org.orgGoodValFile3}" target="_blank"><img
                                                src="${org.orgGoodValFile3}" height="40px" width="40px"/></a>
                                    </c:if>
                                    <input type="file" name="orgMGoodValFile3"><br/>
                                    <c:if test="${org.orgGoodValFile4 !=null and org.orgGoodValFile4 != ''}">
                                        <a class="tooltip" title="优良标准图片" href="${org.orgGoodValFile4}" target="_blank"><img
                                                src="${org.orgGoodValFile4}" height="40px" width="40px"/></a>
                                    </c:if>
                                    <input type="file" name="orgMGoodValFile4">
                                </td>
                                <td><textarea name="orgNormalValArray" rows="3" cols="30"
                                              style="resize: none;">${org.orgNormalVal}</textarea></td>
                                <td>
                                    <c:if test="${org.orgNormalValFile1 !=null and org.orgNormalValFile1 != ''}">
                                        <a class="tooltip" title="合格标准图片" href="${org.orgNormalValFile1}"
                                           target="_blank" src="${org.orgNormalValFile1}"><img
                                                src="${org.orgNormalValFile1}" height="40px" width="40px"/></a>
                                    </c:if>
                                    <input type="file" name="orgMNormalValFile1"><br/>
                                    <c:if test="${org.orgNormalValFile2 !=null and org.orgNormalValFile2 != ''}">
                                        <a class="tooltip" title="合格标准图片" href="${org.orgNormalValFile2}"
                                           target="_blank" src="${org.orgNormalValFile2}"><img
                                                src="${org.orgNormalValFile2}" height="40px" width="40px"/></a>
                                    </c:if>
                                    <input type="file" name="orgMNormalValFile2"><br/>
                                    <c:if test="${org.orgNormalValFile3 !=null and org.orgNormalValFile3 != ''}">
                                        <a class="tooltip" title="合格标准图片" href="${org.orgNormalValFile3}"
                                           target="_blank" src="${org.orgNormalValFile3}"><img
                                                src="${org.orgNormalValFile3}" height="40px" width="40px"/></a>
                                    </c:if>
                                    <input type="file" name="orgMNormalValFile3"><br/>
                                    <c:if test="${org.orgNormalValFile4 !=null and org.orgNormalValFile4 != ''}">
                                        <a class="tooltip" title="合格标准图片" href="${org.orgNormalValFile4}"
                                           target="_blank" src="${org.orgNormalValFile4}"><img
                                                src="${org.orgNormalValFile4}" height="40px" width="40px"/></a>
                                    </c:if>
                                    <input type="file" name="orgMNormalValFile4">
                                </td>
                                <td><textarea name="orgBadValArray" rows="3" cols="30"
                                              style="resize: none;">${org.orgBadVal}</textarea></td>
                                <td>
                                    <c:if test="${org.orgBadValFile1 !=null and org.orgBadValFile1 != ''}">
                                        <a class="tooltip" title="不合格标准图片" href="${org.orgBadValFile1}" target="_blank"
                                           src="${org.orgBadValFile1}"><img src="${org.orgBadValFile1}" height="40px"
                                                                            width="40px"/></a>
                                    </c:if>
                                    <input type="file" name="orgMBadValFile1"><br/>
                                    <c:if test="${org.orgBadValFile2 !=null and org.orgBadValFile2 != ''}">
                                        <a class="tooltip" title="不合格标准图片" href="${org.orgBadValFile2}" target="_blank"
                                           src="${org.orgBadValFile2}"><img src="${org.orgBadValFile2}" height="40px"
                                                                            width="40px"/></a>
                                    </c:if>
                                    <input type="file" name="orgMBadValFile2"><br/>
                                    <c:if test="${org.orgBadValFile3 !=null and org.orgBadValFile3 != ''}">
                                        <a class="tooltip" title="不合格标准图片" href="${org.orgBadValFile3}" target="_blank"
                                           src="${org.orgBadValFile3}"><img src="${org.orgBadValFile3}" height="40px"
                                                                            width="40px"/></a>
                                    </c:if>
                                    <input type="file" name="orgMBadValFile3"><br/>
                                    <c:if test="${org.orgBadValFile4 !=null and org.orgBadValFile4 != ''}">
                                        <a class="tooltip" title="不合格标准图片" href="${org.orgBadValFile4}" target="_blank"
                                           src="${org.orgBadValFile4}"><img src="${org.orgBadValFile4}" height="40px"
                                                                            width="40px"/></a>
                                    </c:if>
                                    <input type="file" name="orgMBadValFile4">
                                </td>
                                <td style="display:none;"><input type="hidden" name="orgStdItemIdArray"
                                                                 id="orgStdItemIdArray" value="${org.orgStdItemId}"/>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>

            <div>
                <msk:button buttonValue="保存" buttonId="PD14114601.SAVE" buttonType="button"/>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript" src="${ctx}/static/js/pd/PD14114601.js"></script>
