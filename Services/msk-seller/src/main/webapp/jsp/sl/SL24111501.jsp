<%--
  Created by IntelliJ IDEA.
  User: guan_zhongheng
  Date: 2016/10/8
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="msk2" uri="/msk" %>
<script type="text/javascript">
    function checkAll(obj) {

        $("input[name='checkbox']").prop('checked', $(obj).prop('checked'));
        //$("input[name='checkbox']").attr("checked",'true');//全选
    }

</script>
<div class="page-content list-page">
        <div>
            <table id="sl24111501_list_grid" width="100%" class="tree dataTable no-footer">
                <input type="hidden" id="slAccount" name="slAccount" value="${slAccount}"/>
                <input type="hidden" id="slCodeDis" name="slCodeDis" value="${slCodeDis}"/>
                <input type="hidden" id="slCode" name="slCode" value="${slCode}"/>
                <input type="hidden" id="slShowName" name="slShowName" value="${slShowName}"/>
                <thead></thead>
                <tbody>
                <tr>
                    <td ><input type="checkbox" id="allCheck" name="allCheck" onclick="checkAll(this)">全选</td>
                </tr>
                <tr>
                    <td><input type="checkbox" name="olCheckbox" value="11_" checked disabled="disabled"/>卖家账号&nbsp;
                        <input type="checkbox" name="onCheckbox" value="12_" checked disabled="disabled"/>卖家基本信息&nbsp;
                    </td>
                </tr>
                <tr>
                    <td >
                        <input type="checkbox" name="checkbox" value="24_"/>产品信息
                        <input type="checkbox" name="checkbox" value="13_"/>企业专业资质&nbsp;
                        <input type="checkbox" name="checkbox" value="14_"/>企业荣誉描述&nbsp;
                        <input type="checkbox" name="checkbox" value="15_"/>企业厂区描述&nbsp;
                        <input type="checkbox" name="checkbox" value="16_"/>企业工艺流程描述&nbsp;
                        <input type="checkbox" name="checkbox" value="17_"/>企业库容概括&nbsp;</td>
                </tr>
                <tr>
                    <td >
                        <input type="checkbox" name="checkbox" value="18_"/>企业产品质量控制系统描述&nbsp;
                        <input type="checkbox" name="checkbox" value="19_"/>检测设备&nbsp;
                        <input type="checkbox" name="checkbox" value="20_"/>公司团队&nbsp;
                        <input type="checkbox" name="checkbox" value="21_"/>电商团队&nbsp;
                        <input type="checkbox" name="checkbox" value="22_"/>卖家品牌&nbsp;
                        <input type="checkbox" name="checkbox" value="23_"/>生产商/OEM商信息&nbsp;
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div>
            <br/>
            <msk2:button buttonValue="确认选择" buttonId="SL24111501.DOWNLOAD" buttonType="button"
                         class="h-button ui-button ui-widget ui-state-default ui-corner-all"/>
        </div>
</div>

<script src="${ctx}/static/sl/js/SL24111501.js"></script>
<script type="text/javascript" src="${ctx}/static/js/loading/download.js"></script>

