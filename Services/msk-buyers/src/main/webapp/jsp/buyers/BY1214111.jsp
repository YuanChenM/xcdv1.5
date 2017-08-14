<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<div class="page-content list-page">
    <table style="width: 100%;" CellSpacing=8>
        <tr>
            <td width="12.5%" align="left">1.市场分类产品买家数最多</td>
        </tr>
        <tr>
            <td width="12.5%" align="left">【判定标准】菜场[5个以下],农贸市场[5个或以上]</td>
        </tr>
        <tr>
            <td width="12.5%" align="left">2.市场辐射范围</td>
        </tr>
        <tr>
            <td width="12.5%" align="left">【判定标准】菜场[周边居民],农贸市场[本区(县)1/4或以上区域]</td>
        </tr>
        <tr>
            <td width="12.5%" align="left">3.市场买家定价平均水平</td>
        </tr>
        <tr>
            <td width="12.5%" align="left">【判定标准】菜场[本区(县)平均水平],农贸市场[较本区(县)平均水平低2%-3%]</td>
        </tr>
        <tr>
            <td width="12.5%" align="left">4.符合以上3项中的任意一项的可定性为农贸市场,否则为菜场</td>
        </tr>
        <tr>
            <td width="12.5%" align="left">5.菜场编码生成规则</td>
        </tr>
        <tr>
            <td width="12.5%" align="left">【生成准则】物流区2位编码,地区3位编码,区县2位编码,菜场3位区县顺序码共10位编码</td>
        </tr>
    </table>
</div>