<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Title:产品原种饲养标准指标
    autdor:zhou_ling
    createDate:2016-03-01
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<script type="text/javascript">
    function callbackFun(message){
        $.alertMessage.info(message);
        HDF.closeLoadingMask();
    }
</script>

<navigation:header title="产品原种饲养标准指标修改" backTitleArray="产品分类目录管理" backUrlArray="${ctx}/PD141124/init?classesCode=${classesCode}&machiningCode=${machiningCode}&breedCode=${breedCode}&featureCode=${featureCode}&weightCode=${weightCode}"></navigation:header>
<div class="page-content list-page" style="height:100%">
    <form action="${ctx}/pd14114701/save" method="post" id="PD14114701Form" enctype="multipart/form-data">
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
                    <label>产品原种饲养标准指标修改</label>
                </h3>

                <div>


                    <table class="tree dataTable no-footer" id="PD141146_list_grid" WIDTH="100%">
                        <tr style="background: #DBDBDB;width:100%" >
                            <td  width="25%" rowspan="2" align="center" height="">指标内容</td>
                            <td colspan="6" align="center">等级</td>
                        </tr>
                        <tr style="background: #DBDBDB;width:100%">
                            <td  align="center"  >优良</td>

                            <td  align="center" >标准图片</td>

                            <td  align="center" >一般</td>

                            <td  align="center" >标准图片</td>

                            <td  align="center" >差</td>

                            <td  align="center" >标准图片</td>
                        </tr>
                        <c:forEach items="${list}" var="item" >
                            <tr >
                                <td width="25%"  align="center">${item.fedStdItemName}</td>



                                <td width="25%">
                                    <textarea name="fedGoodValArray"  rows="3" cols="30" style="resize: none;">${item.fedGoodVal}</textarea>
                                </td>

                                <td>

                                    <a href="${item.fGoodValFile1}" target="_blank">
                                        <img width="100px" height="100px" src="${item.fGoodValFile1}"/>
                                    </a>
                                    <input name="fedGoodValFile1"  type="file" /><br>

                                    <a href="${item.fGoodValFile2}" target="_blank">
                                        <img width="100px" height="100px" src="${item.fGoodValFile2}"/>
                                    </a>
                                    <input name="fedGoodValFile2"  type="file" /><br>

                                    <a href="${item.fGoodValFile3}" target="_blank">
                                        <img width="100px" height="100px" src="${item.fGoodValFile3}"/>
                                    </a>
                                    <input name="fedGoodValFile3"  type="file" /><br>

                                    <a href="${item.fGoodValFile4}" target="_blank">
                                        <img width="100px" height="100px" src="${item.fGoodValFile4}"/>
                                    </a>
                                    <input name="fedGoodValFile4"  type="file" />

                                </td>


                                <td width="25%"><textarea name="fedNormalValArray"  rows="3" cols="30" style="resize: none;">${item.fedNormalVal}</textarea></td>

                                <td>
                                    <a href="${item.fNormalValFile1}" target="_blank">
                                        <img width="100px" height="100px" src="${item.fNormalValFile1}"/>
                                    </a><input name="fedNormalValFile1"  type="file" /><br>
                                    <a href="${item.fNormalValFile2}" target="_blank">
                                        <img width="100px" height="100px" src="${item.fNormalValFile2}"/>
                                    </a><input name="fedNormalValFile2"  type="file" /><br>
                                    <a href="${item.fNormalValFile3}" target="_blank">
                                        <img width="100px" height="100px" src="${item.fNormalValFile3}"/>
                                    </a><input name="fedNormalValFile3"  type="file" /><br>
                                    <a href="${item.fNormalValFile4}" target="_blank">
                                        <img width="100px" height="100px" src="${item.fNormalValFile4}"/>
                                    </a>
                                    <input name="fedNormalValFile4"  type="file" /></td>


                                <td ><textarea name="fedBadValArray"  rows="3" cols="30" style="resize: none;">${item.fedBadVal}</textarea></td>

                                <td>
                                    <a href="${item.fBadValFile1}" target="_blank">
                                        <img width="100px" height="100px" src="${item.fBadValFile1}"/>
                                    </a><input name="fedBadValFile1"  type="file" /><br>
                                    <a href="${item.fBadValFile2}" target="_blank">
                                        <img width="100px" height="100px" src="${item.fBadValFile2}"/>
                                    </a><input name="fedBadValFile2"  type="file" /><br>
                                    <a href="${item.fBadValFile3}" target="_blank">
                                        <img width="100px" height="100px" src="${item.fBadValFile3}"/>
                                    </a><input name="fedBadValFile3"  type="file" /><br>
                                    <a href="${item.fBadValFile4}" target="_blank">
                                        <img width="100px" height="100px" src="${item.fBadValFile4}"/>
                                    </a>
                                    <input name="fedBadValFile4"  type="file" /></td>


                                <td style="display:none;"><input type="hidden"  name="fedStdItemIdArray" id="fedStdItemIdArray" value="${item.fedStdItemId}"/></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>

            </div>

            <div>
                <msk:button buttonValue="保存" buttonId="PD14114701.SAVE" buttonType="button"/>
            </div>
        </div>
    </form>



    <%--<form action="${ctx}/pd14114701/upload" id="PD141147ImgForm" method="post" enctype ="multipart/form-data" runat="server">
        <input id="File1" runat="server" name="UpLoadFile" type="file" />
        <input type="submit" name="Button1" value="Button" id="Button1" />
    </form>--%>
</div>
<script type="text/javascript" src="${ctx}/static/js/pd/PD14114701.js"></script>
