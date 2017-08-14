<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
    Title:卖家产品品种图片
    author:gyh
    createDate:2016-3-4
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<div class="page-content list-page">
    <div>
        <table width="100%" class="tree dataTable no-footer">
            <thead></thead>
            <tbody>
            <tr>
                <td coltype="text" width="20%">盘装图</td>
                <td coltype="text"><a name="inTrayFigure" class="tooltip" title="盘装图"
                                      href="${fileSerUrl}/sl/${sl241116Bean.prodEpId}/pd/${sl241116Bean.pdClassesCode}/${sl241116Bean.machiningCode}/${sl241116Bean.pdBreedCode}/inTrayFigure."
                                      target="_blank"
                                      src="${fileSerUrl}/sl/${sl241116Bean.prodEpId}/pd/${sl241116Bean.pdClassesCode}/${sl241116Bean.machiningCode}/${sl241116Bean.pdBreedCode}/inTrayFigure."></a></td>
            </tr>
            <tr>
                <td coltype="text">内袋图</td>
                <td coltype="text"><a name="insideOfFigure" class="tooltip" title="内袋图"
                                      href="${fileSerUrl}/sl/${sl241116Bean.prodEpId}/pd/${sl241116Bean.pdClassesCode}/${sl241116Bean.machiningCode}/${sl241116Bean.pdBreedCode}/insideOfFigure."
                                      target="_blank"
                                      src="${fileSerUrl}/sl/${sl241116Bean.prodEpId}/pd/${sl241116Bean.pdClassesCode}/${sl241116Bean.machiningCode}/${sl241116Bean.pdBreedCode}/insideOfFigure."></a></td>
            </tr>
            <tr>
                <td coltype="text">外箱开箱图</td>
                <td coltype="text"><a name="outsideBoxFigure" class="tooltip" title="外箱开箱图"
                                      href="${fileSerUrl}/sl/${sl241116Bean.prodEpId}/pd/${sl241116Bean.pdClassesCode}/${sl241116Bean.machiningCode}/${sl241116Bean.pdBreedCode}/outsideBoxFigure."
                                      target="_blank"
                                      src="${fileSerUrl}/sl/${sl241116Bean.prodEpId}/pd/${sl241116Bean.pdClassesCode}/${sl241116Bean.machiningCode}/${sl241116Bean.pdBreedCode}/outsideBoxFigure."></a></td>
            </tr>
            <tr>
                <td coltype="text">产品外箱外观图</td>
                <td coltype="text"><a name="cartonAppearanceFigure" class="tooltip" title="外箱开箱图"
                                      href="${fileSerUrl}/sl/${sl241116Bean.prodEpId}/pd/${sl241116Bean.pdClassesCode}/${sl241116Bean.machiningCode}/${sl241116Bean.pdBreedCode}/cartonAppearanceFigure."
                                      target="_blank"
                                      src="${fileSerUrl}/sl/${sl241116Bean.prodEpId}/pd/${sl241116Bean.pdClassesCode}/${sl241116Bean.machiningCode}/${sl241116Bean.pdBreedCode}/cartonAppearanceFigure." ></a></td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<script  src="<c:url value="/static/js/ssc/SSC1133001.js"/>"/>