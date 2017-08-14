<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<navigation:header title="调查详情" backTitleArray="批发市场定性定级列表" backUrlArray="${ctx}/BY121401/init/"></navigation:header>
<div class="page-content detail-page">
    <input type="hidden" id="marketId"  name="marketId" value="${marketId}"/>

    <div class="group-accordion" collapsible="true" active="true">
        <h3>批发市场目标买家</h3>
        <div>
            <form action="${ctx}/BY121413/isTargetSearch/${marketId}" id="BY12141301Form" method="post">
                <table width="100%" id="BY12141301_list_grid">
                    <thead>
                    <tr>  
                        <th coltype="sno" align="center">编号</th>
                        <th coltype="text" name="buyerStoreNo" width="7%" filter="true" align="center">买家门牌/摊位号</th>
                        <th coltype="text" name="pdChicken"  align="center">鸡产品</th>
                        <th coltype="text" name="pdDuck"  align="center">鸭产品</th>
                        <th coltype="text" name="pdPork"  align="center">猪产品</th>
                        <th coltype="text" name="pdBeefMutton"  align="center">牛羊产品</th>
                        <th coltype="text" name="pdSea"  align="center">海产品</th>
                        <th coltype="text" name="pdMeatballs"  align="center">丸肠水发品</th>
                        <th coltype="text" name="pdBacon"  align="center">腌腊产品</th>
                        <th coltype="text" name="frozenFood"  align="center">冰品</th>
                        <th coltype="text" name="quicklyFreezedSnack"  align="center">速冻点心</th>
                        <th coltype="text" name="quicklyFreezedVegetables"  align="center">速冻蔬菜</th>
                        <th coltype="text" name="convenientFood"   align="center">调理水煮包与方便菜</th>
                        <th coltype="text" name="grainOil"   align="center">粮油产品</th>
                        <th coltype="text" name="flavoring"   align="center">调味品</th>
                        <th coltype="text" name="drysaltery"   align="center">干货产品</th>
                        <th coltype="text" name="cole"   align="center">小菜产品</th>
                        <th coltype="text" name="childFood"   align="center">儿童食品</th>
                        <th coltype="text" name="remark" width="7%" filter="false"  align="center">备注</th>
                        <th coltype="action" align="center">操作
                            <msk:button buttonValue="编辑" buttonId="BY121413.TARGET.EDIT" buttonType="hidden" coltype="edit" class="h-button"/>
                            <msk:button buttonValue="删除" buttonId="BY121413.TARGET.DELETE" buttonType="hidden" coltype="delete" class="h-button"/>
                        </th>
                    </tr>
                    </thead>
                    <tbody id="BR12141301Tbody">
                    </tbody>
                </table>
                </form>
                <msk:button buttonValue="新增" buttonId="BY121413.TARGET.ADD" buttonType="button"/>
        </div>
    </div>


    <div class="group-accordion" collapsible="true" active="true">
        <h3> 批发市场非目标买家</h3>
        <form action="${ctx}/BY121413/nonTargetSearch/${marketId}" id="BY12141302Form" method="post">
        <div>
            <table width="100%" id="BR12141302_list_grid">
                <thead>
                <tr>
                    <th coltype="sno" align="center">编号</th>
                    <th coltype="text" name="buyerStoreNo" width="7%" filter="true" align="center">买家门牌/摊位号</th>
                    <th coltype="text" name="freshMeat"  align="center">鲜肉产品</th>
                    <th coltype="text" name="freshFish"  align="center">鲜鱼产品</th>
                    <th coltype="text" name="poultry"  align="center">活禽产品</th>
                    <th coltype="text" name="eggs"  align="center">禽蛋产品</th>
                    <th coltype="text" name="fruitsVegetables"  align="center">果蔬产品</th>
                    <th coltype="text" name="pdBean"   align="center">豆制品</th>
                    <th coltype="text" name="otherFood"  align="center">其它食品</th>
                    <th coltype="text" name="nonFood"  align="center">非食品</th>
                    <th coltype="text" name="remark" width="7%" filter="false" align="center">备注</th>
                    <th coltype="action" align="center">操作
                        <msk:button buttonValue="编辑" buttonId="BY121413.NOTTARGET.EDIT" buttonType="hidden" coltype="edit" class="h-button"/>
                        <msk:button buttonValue="删除" buttonId="BY121413.NOTTARGET.DELETE" buttonType="hidden" coltype="delete" class="h-button"/>
                    </th>
                </tr>
                </thead>
                <tbody id="BR12141302Tbody">
                </tbody>
            </table>
            <msk:button buttonValue="新增" buttonId="BY121413.NOTTARGET.ADD" buttonType="button"/>
        </div>
        </form>

    </div>

</div>
<script type="text/javascript" src="${ctx}/static/buyers/js/BY121413.js"></script>


