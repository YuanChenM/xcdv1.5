<%-- 
    Title:冻品管家自我介绍
    author:whc
    createDate:2016-8-19
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/comm/taglib.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<navigation:header title="冻品管家自我介绍" backTitleArray="冻品管家一览,冻品管家管控"
                   backUrlArray="${ctx}/BS2102102/init,${ctx}/BS2102112/init?slCode=${slCode}&houseCode=${houseCode}"
></navigation:header>
<script type="text/javascript" src="${ctx}/static/upload/fileupload.js"></script>
<style>
    .uploadFiles img{
        cursor: pointer;
    }
</style>

<div class="page-content list-page" style="height:100%">
    <form action="${ctx}/BS2102113/save" id="BS2102113FormInfo" method="post" enctype="multipart/form-data">
        <input type="hidden" id="slCode" value="${slCode}"/>
        <input type="hidden" id="houseCode" value="${houseCode}"/>
        <input type="hidden" id="uploadUrl1" value=""/>
        <input type="hidden" id="uploadUrl2" value=""/>
        <div>
            <div class="group-accordion" active="true">
                <h3>
                    <label>冻品管家自我介绍管理</label>
                </h3>
                <form action="" id="introduceForm" method="post" enctype="multipart/form-data">
                <table style="border-collapse:separate;border-spacing:10px;width: 100%;border: 0px;">
                    <tr class="uploadFiles">
                        <td>上传附件</td>
                        <td >
                            <img id="uploadUrl1_img" src="${houseIntroduce.uploadUrl1}" <c:if test="${empty houseIntroduce.uploadUrl1}">style="display: none;"</c:if> <c:if test="${!empty houseIntroduce.uploadUrl1}">style="width: 100px;height: 100px;"</c:if>>
                            <msk:uploadFile fileLinkId="fileLinkIdFirst" fileName="uploadUrl1" uploadButtonId="BS2102113_SAVE"
                                            fileSize="2" fileSizeMessage="文件不能大于2M" fileType="jpg,png,jpeg,bmp"
                                            fileTypeMessage="文件格式错误" callbackFunction="uploadCallback" bindUpload ="false"></msk:uploadFile>
                        </td>
                    </tr>
                    <tr class="uploadFiles">
                        <td></td>
                        <td>
                            <img id="uploadUrl2_img" src="${houseIntroduce.uploadUrl2}" <c:if test="${empty houseIntroduce.uploadUrl2}">style="display: none;"</c:if> <c:if test="${!empty houseIntroduce.uploadUrl2}">style="width: 100px;height: 100px;"</c:if>>
                            <msk:uploadFile fileLinkId="fileLinkIdSecond" fileName="uploadUrl2" uploadButtonId="BS2102113_SAVE"
                                            fileSize="2" fileSizeMessage="文件不能大于2M" fileType="jpg,png,jpeg,bmp"
                                            fileTypeMessage="文件格式错误" callbackFunction="uploadCallback"></msk:uploadFile>
                        </td>
                        <td>
                            <img id="big_img" src="" style="margin-left: -60%;margin-top: -10%;position: absolute;display: none;width: 300px;height: 300px;">
                        </td>
                    </tr>
                    <tr>
                        <td>服务心得</td>
                        <td><textarea id="serviceCommit" max="500" name="serviceCommit"
                                      style="width: 700px;height: 170px;max-width: 700px;max-height: 170px;resize: none;">${houseIntroduce.serviceCommit}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>感悟理想</td>
                        <td><textarea id="introduce" max="500" name="introduce"
                                      style="width: 700px;height: 170px;max-width: 700px;max-height: 170px;resize: none;">${houseIntroduce.introduce}</textarea>
                        </td>
                    </tr>
                </table>
                </form>
            </div>
            <div>
                <table style="border-collapse:separate;border-spacing:10px;width: 100%;border: 0px;">
                    <tr>
                        <td><msk:button buttonValue="保存" buttonType="button" buttonId="BS2102113.SAVE"/></td>
                    </tr>
                </table>
            </div>
            <div id="workExperience"></div>
            <div id="eduExperience"></div>
            <div id="trainExperience"></div>
        </div>
    </form>
    <div style="display: none;">
        <table style="border-collapse:separate;border-spacing:10px;width: 100%;border: 0px;">
            <tr>
                <td><msk:button buttonValue="更新履历" buttonType="button" buttonId="BS2102113.UPDATE"/></td>
            </tr>
        </table>
    </div>
</div>
<script src="${ctx}/static/bs/js/BS2102113.js"></script>