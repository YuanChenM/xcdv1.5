<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Title:产品总控目录在线管理表
    author:pxg
    createDate:2016-02-22
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<div class="page-content detail-page" style="height:100%">
    <div class="group-accordion" collapsible="false" active="false" >
        <h3 style="position: relative">
            <label>查询条件</label>
        </h3>
       <div>
           <table>
               <tr>
                   <td>
                       &nbsp;&nbsp;&nbsp;物流区域：<select id="oneClasss" style="width:135px;">
                       <option>--请选择--</option>
                       <option>上海</option>
                       <option>天津</option>
                   </select>
                   </td>
               </tr>
               <tr>
                   <td>
                       第一级分类：<select id="oneClass" style="width:135px;">
                       <option>--请选择--</option>
                       <c:forEach items="${listOne}" var="list">
                           <option value="${list.classestreeCode}">${list.levelName}</option>
                       </c:forEach>
                   </select>
                   </td>
                   <td>
                       &emsp;&emsp;第二级分类：<select id="twoClass" style="width:135px;">
                       <option>--请选择--</option>
                   </select>
                   </td>
                   <td>
                       &emsp;&emsp;第三级分类：<select id="threeClass" style="width:135px;">
                       <option>--请选择--</option>
                   </select>
                   </td>
                   <td>
                       &emsp;&emsp;第四级分类：<select id="fourClass" style="width:135px;">
                       <option>--请选择--</option>
                   </select>
                   </td>
               </tr>
               <tr>
                   <td>
                       第五级分类：<select id="fiveClass" style="width:135px;">
                       <option>--请选择--</option>
                   </select>
                   </td>
                   <c:if test="${empty tcBuyinvestListId}">
                   <td>&nbsp;&nbsp;&nbsp;买家编码：<input type="text" name="normsSuttle"/> </td>
                   <td>&emsp;&emsp;&emsp;买家名称：<input type="text" name="normsSuttle"/> </td>
                   <td colspan="5">
                       &emsp;&emsp;&emsp;&nbsp;调查日期：<input type="text" id="orderTime" name="filterMap['orderTime']"/>
                   </td>
               <tr>
                     <td>&emsp;&emsp;&emsp;&emsp;&nbsp;调研人：<input type="text" name="normsSuttle"/> </td>
                     <td><msk:button buttonValue="保存" buttonId="PD141131.SAVE" buttonType="button"/></td>
               </c:if>
                <c:if test="${not empty tcBuyinvestListId}">
                    <td>&nbsp;&nbsp;&nbsp;买家编码:</td>
                    <td>&emsp;&emsp;&emsp;买家名称:</td>
                    <td>&emsp;&emsp;&emsp;&nbsp;调查日期:</td>
                    <td>&emsp;&emsp;&emsp;&emsp;调研人:</td>
                </c:if>
               </tr>
               </table>
           </div>
        </div>
        <div class="group-accordion" collapsible="false" active="false" style="height:80%" id="pd141131accordion">
           <h3 style="position: relative">
               <label>产品总控目录在线管理表</label>
           </h3>
        <div style="padding-left: 0px;padding-top: 0px;padding-bottom: 0px;padding-right: 0px;">
            <p>
                原料描述：
            </p>
            <p>
                原料学名：AA白羽鸡，俗名：肉鸡，标准市场销售名：白羽鸡
            </p>
            <p>
                原料原产地：美国，现产地：河北
            </p>
            <p>
                原料种源：AA白羽鸡，雏类：春雏、晚春雏、梅雨雏、夏雏、秋雏、冬雏，饲养方式：圈养
            </p>
            <p>
                饲养周期：50天以内
            </p>
            <div>
            <table class="tree dataTable no-footer" style="width: 100%">
                <tr style="background:#DBDBDB">
                    <td name=""  align="center" rowspan="2">序号</td>
                    <td width="40px" align="center" colspan="3">品种名</td>
                    <td align="center" rowspan="2">市场需求审核注册线产品</td>
                    <td align="center" rowspan="2">正式上线产品</td>
                    <td align="center" rowspan="2" width="40px">买家现销售产品及品牌、产地</td>
                    <td align="center" colspan="2" width="40px">产品销售对象</td>
                    <td align="center" colspan="2" width="40px">产品加工方向</td>
                    <td align="center" rowspan="2">备注(产地)</td>
                </tr>
                <tr style="background:#DBDBDB">
                    <td width="15px" align="center">标准市场销售名</td>
                    <td align="center" width="10px">学名</td>
                    <td align="center" width="15px">俗名</td>
                    <td align="center">标准</td>
                    <td align="center">调研</td>
                    <td align="center">标准</td>
                    <td align="center">调研</td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>单冻琵琶腿</td>
                    <td>鸡琵琶腿</td>
                    <td>小鸡腿，中鸡腿</td>
                    <td id="shen">
                        <a id="shenBtn" href="javascript:void(0);" col="10"><img src="${ctx}/static/core/images/action/details_open.png" id="shenImg"/></a>
                        <div style="display:none;margin-left:20px;margin-right:0px;width:160px" id="divshen" >
                            <table class="tree dataTable no-footer" style="min-width:230px" width="230px" showAddBtn="true">
                                <tr style="background:#DBDBDB">
                                    <td align="center">俗名</td>
                                    <td align="center">市场需求审核注册</td>
                                </tr>
                                <c:forEach items="${list}" var="li">
                                    <tr>
                                        <td>小鸡腿</td>
                                        <td>${li}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </td>
                    <td id="shang">
                        <a id="shangBtn" href="javascript:void(0);" col="10"><img src="${ctx}/static/core/images/action/details_open.png" id="shangImg"/></a>
                        <div style="display:none;margin-left:20px;margin-right:0px;width:40px" id="divshang">
                        <table class="tree dataTable no-footer" style="min-width:80px" width="80px" showAddBtn="true">
                            <tr style="background:#DBDBDB">
                                <td align="center">正式上线</td>
                            </tr>
                            <c:forEach items="${list}" var="li">
                                <tr>
                                    <td>${li}</td>
                                </tr>
                            </c:forEach>
                        </table>
                        </div>
                    </td>
                    <td id="buyer">
                        <a id="buyerBtn" href="javascript:void(0);" col="10"><img src="${ctx}/static/core/images/action/details_open.png" id="buyerImg"/></a>
                        <div style="display:none;margin-left:20px;margin-right:0px;width:350px" id="divbuyer" >
                            <table class="tree dataTable no-footer" style="min-width:350px" width="350px" showAddBtn="true">
                                <tr style="background:#DBDBDB">
                                    <td align="center">销售产品</td>
                                    <td align="center">品牌</td>
                                    <td align="center">产地</td>
                                    <td align="center">操作</td>
                                </tr>
                                <c:forEach items="${list}" var="li">
                                    <tr>
                                        <td>${li}</td>
                                        <td>西餐</td>
                                        <td>加拿大</td>
                                        <td width="10px">
                                            <a class="buyerButtonadd" id="add" title="添加" href="javascript:void(0);" col="10"><img src="${ctx}/static/core/images/action/add.png" style="width:13px;height:13px"></a>
                                            <a class="buyerButtonedit" id="edit" title="修改" href="javascript:void(0);" col="10"><img src="${ctx}/static/core/images/action/edit.png" style="width:13px;height:13px"></a>
                                            <a class="buyerButtondelete" id="delete" title="删除" href="javascript:void(0);" col="10"><img src="${ctx}/static/core/images/action/delete.png" style="width:13px;height:13px"></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </td>
                    <td colspan="2">
                        <a id="objectBtn" href="javascript:void(0);" col="10"><img src="${ctx}/static/core/images/action/details_open.png" id="objectImg"/></a>
                        <div style="display:none;margin-left:20px;margin-right:0px;width:150px" id="divObject">
                            <table class="tree dataTable no-footer" style="min-width:80px" width="80px" showAddBtn="true">
                                <tr style="background:#DBDBDB">
                                    <td align="center">标准</td>
                                    <td align="center">调研</td>
                                    <td align="center">操作</td>
                                </tr>
                                <c:forEach items="${list}" var="li">
                                    <tr>
                                        <td>${li}</td>
                                        <td>${li}</td>
                                        <td width="10px">
                                            <a class="objectButtonadd" id="addObject" title="添加" href="javascript:void(0);" col="10"><img src="${ctx}/static/core/images/action/add.png" style="width:13px;height:13px"></a>
                                            <a class="objectButtonedit" id="editObject" title="修改" href="javascript:void(0);" col="10"><img src="${ctx}/static/core/images/action/edit.png" style="width:13px;height:13px"></a>
                                            <a class="objectButtondelete" id="deleteObject" title="删除" href="javascript:void(0);" col="10"><img src="${ctx}/static/core/images/action/delete.png" style="width:13px;height:13px"></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </td>
                    <td colspan="2">
                        <a id="directionBtn" href="javascript:void(0);" col="10"><img src="${ctx}/static/core/images/action/details_open.png" id="directionImg"/></a>
                        <div style="display:none;margin-left:20px;margin-right:0px;width:150px" id="divDirection">
                            <table class="tree dataTable no-footer" style="min-width:80px" width="80px" showAddBtn="true">
                                <tr style="background:#DBDBDB">
                                    <td align="center">标准</td>
                                    <td align="center">调研</td>
                                    <td align="center">操作</td>
                                </tr>
                                <c:forEach items="${list}" var="li">
                                    <tr>
                                        <td>${li}</td>
                                        <td>${li}</td>
                                        <td width="10px">
                                            <a class="directionButtonadd" id="addDirection" title="添加" href="javascript:void(0);" col="10"><img src="${ctx}/static/core/images/action/add.png" style="width:13px;height:13px"></a>
                                            <a class="directionButtonedit" id="editDirection" title="修改" href="javascript:void(0);" col="10"><img src="${ctx}/static/core/images/action/edit.png" style="width:13px;height:13px"></a>
                                            <a class="directionButtondelete" id="deleteDirection" title="删除" href="javascript:void(0);" col="10"><img src="${ctx}/static/core/images/action/delete.png" style="width:13px;height:13px"></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </td>
                    <td>山东</td>
                </tr>
            </table></div>
        </div>
    </div>
    </div>
<script src="${ctx}/static/js/pd/PD141131.js"></script>
