<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: xhy
  Date: 15/3/15
  Time: 上午11:11
  卖家产品一览
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/comm/taglib.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" href="${ctx}/static/js/treegrid/css/jquery.treegrid.css">
<script type="text/javascript" src="${ctx}/static/js/treegrid/js/jquery.treegrid.js"></script>
<script type="text/javascript">
    var slShowName = '${slShowName}';
</script>
<div class="page-content list-page">
    <form action="${ctx}/PD141404/save" method="post" id="PD141404Form">
        <input type="hidden" value="${beans.slId}" name="slId"/>
        <input type="hidden" value="${beans.slPdId}" name="slPdId"/>
        <input type="hidden" value="${beans.slCodeDis}" name="slCodeDis"/>
        <input type="hidden" value="${beans.standardId}" name="standardId"/>
        <input type="hidden" value="${beans.classestreeCode}" name="classestreeCode"/>
        <input type="hidden" value="${showTable}" name="showTable"/>
        <navigation:header title="卖家单个产品及状态管控详细" backTitleArray="卖家一览,卖家产品一览,卖家单个产品及状态在线管控一览"
                           backUrlArray="${ctx}/PD141401/init,${ctx}/PD141402/init?slCode=${beans.slId}&slCodeDis=${beans.slCodeDis},${ctx}/PD141403/init?slCode=${beans.slId}&slCodeDis=${beans.slCodeDis}&slPdId=${beans.slPdId}&standardId=${beans.standardId}&pdClassesName=${beans.pdClassesName}&machiningName=${beans.machiningName}&pdBreedName=${beans.pdBreedName}&pdFeatureName=${beans.pdFeatureName}&weightName=${beans.weightName}&classestreeCode=${beans.classestreeCode}"/>
        <div class="page-content list-page">
            <div>
                <table width="100%">
                    <tr width="100%">
                        <td align="right" width="50px">一级分类:</td>
                        <td align="left" width="8px">
                            <input type="text" value="${beans.pdClassesName}" readonly/>
                        </td>
                        <td align="right" width="40px">产品批次:</td>
                        <td align="left" width="25px">
                            <input class="" type="datetime" name="pdLot" value="${beans.pdLot}"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="50px">二级分类:</td>
                        <td align="left" width="8px">
                            <input type="text" value="${beans.machiningName}" readonly/>
                        </td>
                        <td align="right" width="30px">检测日期:</td>
                        <td align="left" width="8px">
                            <input type="text" name="showDate" id="checkDate" required value="${beans.showDate}"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="50px">品种:</td>
                        <td align="left" width="8px">
                            <input type="text" value="${beans.pdBreedName}" readonly/>
                        </td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td align="right" width="50px">特征:</td>
                        <td align="left" width="8px">
                            <input type="text" value="${beans.pdFeatureName}" readonly/>
                        </td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td align="right" width="50px">净重:</td>
                        <td align="left" width="8px">
                            <input type="text" value="${beans.weightName}" readonly/>
                        </td>
                        <td></td>
                        <td></td>
                    </tr>
                </table>
            </div>
            <c:if test="${showTable=='1'}">
                <div class="group-accordion" collapsible="true" active="false" style="width: 100%;overflow: auto">
                    <h3>
                        <label>产品原种种源标准指标</label>
                    </h3>
                    <div>
                    <table class="tree dataTable no-footer">
                        <tr>
                            <td width="100%" colspan="8" align="left"><p>　产品特征(包括产品规格、口味、加工方法)：</p></td>
                        </tr>
                        <tr style="background: #DBDBDB;">
                            <td width="20%" rowspan="3" align="center">指标类容</td>
                            <td colspan="6" width="50%" align="center">等级</td>
                            <td rowspan="3" width="30%" align="center">备注</td>
                        </tr>
                        <tr style="background: #DBDBDB;">
                            <td align="center" colspan="2">优良</td>
                            <td align="center" colspan="2">一般</td>
                            <td align="center" colspan="2">合格</td>
                        </tr>
                        <tr style="background: #DBDBDB;">
                            <td align="center" width="20%">标准</td>
                            <td align="center">是否符合</td>
                            <td align="center" width="20%">标准</td>
                            <td align="center">是否符合</td>
                            <td align="center" width="20%">标准</td>
                            <td align="center">是否符合</td>
                        </tr>

                        <c:forEach items="${listOrg}" var="org" varStatus="orgPd">
                            <tr>
                                <td width="25%" align="center">${org.orgStdItemName}<input type="hidden"
                                                                                           name="orgStdItemName"
                                                                                           value="${org.orgStdItemName}"/>
                                </td>
                                <td width="25%"><textarea rows="3" cols="30" readonly
                                                          style="resize: none;">${org.orgGoodVal}</textarea></td>
                                <td align="center">符合:<input name="orgCheckBox" type="checkbox"
                                                             class="orgCheckBox${orgPd.index+1}"
                                                             value="1" ${org.resultFlg eq "1"?"checked":""}/>
                                </td>
                                <td width="25%"><textarea rows="3" cols="30" readonly
                                                          style="resize: none;">${org.orgNormalVal}</textarea></td>
                                <td align="center">符合:<input name="orgCheckBox" type="checkbox"
                                                             class="orgCheckBox${orgPd.index+1}"
                                                             value="2" ${org.resultFlg eq "2"?"checked":""}/>
                                </td>
                                <td><textarea rows="3" cols="30" readonly
                                              style="resize: none;">${org.orgBadVal}</textarea></td>
                                <td align="center">符合:<input name="orgCheckBox" type="checkbox"
                                                             class="orgCheckBox${orgPd.index+1}"
                                                             value="3" ${org.resultFlg eq "3"?"checked":""}/>
                                </td>
                                <td style="display:none;"><input name="orgCheckBox" type="checkbox"
                                                                 class="orgCheckBox${orgPd.index+1}" value="0"
                                                                 <c:if test="${org.resultFlg !='1' and org.resultFlg != '2' and org.resultFlg !='3'}">checked</c:if>
                                        <c:if test="${org.resultFlg =='1' or org.resultFlg == '2' or org.resultFlg =='3'}"></c:if>/>
                                </td>

                                <td><textarea id="orgCheckBox${orgPd.index+1}" name="orgResultInfo" rows="3" cols="30"
                                        <c:if test="${org.resultFlg !='1' and org.resultFlg != '2' and org.resultFlg !='3'}"></c:if>
                                              <c:if test="${org.resultFlg =='1' or org.resultFlg == '2' or org.resultFlg =='3'}">readonly</c:if>
                                              style="resize: none;">${org.resultInfo}</textarea></td>
                                <td style="display:none;"><input type="hidden" name="orgStdItemId"
                                                                 value="${org.orgStdItemId}"/></td>
                            </tr>
                        </c:forEach>
                    </table>
                    </div>
                </div>

                <div class="group-accordion" collapsible="true" active="false" style="width: 100%;overflow: auto">
                    <h3>
                        <label>产品原种饲养标准指标</label>
                    </h3>
                    <div>
                    <table class="tree dataTable no-footer" WIDTH="100%">
                        <tr>
                            <td width=100%" colspan="8" align="left"><p>　产品特征(包括产品规格、口味、加工方法)：</p></td>
                        </tr>
                        <tr style="background: #DBDBDB;width:100%">
                            <td width="20%" rowspan="3" align="center">指标类容</td>
                            <td colspan="6" width="50%" align="center">等级</td>
                            <td rowspan="3" width="30%" align="center">备注</td>
                        </tr>
                        <tr style="background: #DBDBDB;width:100%">
                            <td align="center" colspan="2">优良</td>
                            <td align="center" colspan="2">一般</td>
                            <td align="center" colspan="2">合格</td>
                        </tr>
                        <tr style="background: #DBDBDB;width:100%">
                            <td align="center" width="20%">标准</td>
                            <td align="center">是否符合</td>
                            <td align="center" width="20%">标准</td>
                            <td align="center">是否符合</td>
                            <td align="center" width="20%">标准</td>
                            <td align="center">是否符合</td>
                        </tr>
                        <c:forEach items="${listFed}" var="fed" varStatus="fedPd">
                            <tr>
                                <td width="25%" align="center">${fed.fedStdItemName}<input type="hidden"
                                                                                           name="fedStdItemName"
                                                                                           value="${fed.fedStdItemName}"/>
                                </td>
                                <td width="25%"><textarea rows="3" cols="30" readonly
                                                          style="resize: none;">${fed.fedGoodVal}</textarea></td>
                                <td align="center">符合:<input type="checkbox" class="fedCheckBox${fedPd.index+1}"
                                                             name="fedCheckBox"
                                                             value="1" ${fed.resultFlg eq "1"?"checked":""}/>
                                </td>
                                <td width="25%"><textarea rows="3" cols="30" readonly
                                                          style="resize: none;">${fed.fedNormalVal}</textarea></td>
                                <td align="center">符合:<input type="checkbox" class="fedCheckBox${fedPd.index+1}"
                                                             name="fedCheckBox"
                                                             value="2" ${fed.resultFlg eq "2"?"checked":""}/>
                                </td>
                                <td><textarea rows="3" cols="30" readonly
                                              style="resize: none;">${fed.fedBadVal}</textarea></td>
                                <td align="center">符合:<input type="checkbox" class="fedCheckBox${fedPd.index+1}"
                                                             name="fedCheckBox"
                                                             value="3" ${fed.resultFlg eq "3"?"checked":""}/>
                                </td>
                                <td style="display:none;"><input name="fedCheckBox" type="checkbox"
                                                                 class="fedCheckBox${fedPd.index+1}" value="0"
                                                                 <c:if test="${fed.resultFlg !='1' and fed.resultFlg != '2' and fed.resultFlg !='3'}">checked</c:if>
                                        <c:if test="${fed.resultFlg =='1' or fed.resultFlg == '2' or fed.resultFlg =='3'}"></c:if>/>
                                </td>
                                <td><textarea id="fedCheckBox${fedPd.index+1}" name="fedResultInfo" rows="3" cols="30"
                                        <c:if test="${fed.resultFlg !='1' and fed.resultFlg != '2' and fed.resultFlg !='3'}"></c:if>
                                              <c:if test="${fed.resultFlg =='1' or fed.resultFlg == '2' or fed.resultFlg =='3'}">readonly</c:if>
                                              style="resize: none;">${fed.resultInfo}</textarea></td>
                                <td style="display:none;"><input type="hidden" name="fedStdItemId"
                                                                 value="${fed.fedStdItemId}"/></td>
                            </tr>
                        </c:forEach>
                    </table>
                    </div>
                </div>
            </c:if>

            <div class="group-accordion" collapsible="true" active="false" style="width: 100%;overflow: auto">
                <h3>
                    <label>产品加工技术标准指标</label>
                </h3>
                <div>
                <table class="tree dataTable no-footer" WIDTH="100%">
                    <tr>
                        <td width="100%" colspan="7" align="left"><p>　产品特征(包括产品规格、口味、加工方法)：</p></td>
                    </tr>
                    <tr style="background: #DBDBDB;width:100%">
                        <td width="20%" rowspan="3" align="center">指标类容</td>
                        <td width="3%" rowspan="3" align="center">序号</td>
                        <td colspan="3" width="50%" align="center">等级</td>
                        <td width="3%" rowspan="3" align="center">论证中技术</td>
                        <td rowspan="3" width="30%" align="center">备注</td>
                    </tr>
                    <tr style="background: #DBDBDB;width:100%">
                        <td align="center" colspan="2" width="30%">合格状态描述</td>
                        <td align="center" rowspan="2" width="30%">不合格状态描述</td>
                    </tr>
                    <tr style="background: #DBDBDB;width:100%">
                        <td align="center" width="20%">标准</td>
                        <td align="center">是否符合</td>
                    </tr>
                    <c:forEach items="${listMct}" var="mct" varStatus="mctPd">
                        <tr>
                            <td width="25%" align="center">${mct.mctStdItemName}<input type="hidden"
                                                                                       name="mctStdItemName"
                                                                                       value="${mct.mctStdItemName}"/>
                            </td>
                            <td>${mctPd.index+1}</td>
                            <td width="25%"><textarea rows="3" cols="30" readonly
                                                      style="resize: none;">${mct.mctOkVal}</textarea></td>
                            <td align="center">符合:<input type="checkbox" name="mctCheckBox"
                                                         class="mctCheckBox${mctPd.index+1}"
                                                         value="1" ${mct.resultFlg eq "1"?"checked":""}/>
                            </td>
                            <td style="display:none;"><input name="mctCheckBox" type="checkbox"
                                                             class="mctCheckBox${mctPd.index+1}" value="0"
                                                             <c:if test="${mct.resultFlg !='1'}">checked</c:if>
                                    <c:if test="${mct.resultFlg =='1'}"></c:if>/>
                            </td>
                            <td width="25%"><textarea rows="3" cols="30" readonly
                                                      style="resize: none;">${mct.mctNgVal}</textarea></td>
                            <td id="mctProNeed">
                                <a class="mctProNeedBtn${mctPd.index}" name="mctProNeedBtn" href="javascript:void(0);"
                                   col="10"><img
                                        src="${ctx}/static/core/images/action/details_open.png"
                                        id="mctProNeedImg${mctPd.index}"/></a>

                                <div style="display:none;margin-left:15px;margin-right:0px;width:350px"
                                     id="divMctProNeed${mctPd.index}">
                                    <table class="tree dataTable no-footer" style="min-width:250px" width="250px"
                                           showAddBtn="true">
                                        <tr style="background:#DBDBDB">
                                            <td align="center">供应商编码</td>
                                            <td align="center">内容</td>
                                            <td align="center">提出日</td>
                                            <td align="center">结案日</td>
                                        </tr>
                                        <c:forEach items="${mct.mctProList}" var="mctPro">
                                            <tr>
                                                <td align="center">${mctPro.slPdId}</td>
                                                <td align="center">${mctPro.mctStdVal}</td>
                                                <td align="center">${mctPro.raiseDateShow}</td>
                                                <td align="center">${mctPro.fixDateShow}</td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </td>
                            <td><textarea id="mctCheckBox${mctPd.index+1}" name="mctResultInfo" rows="3" cols="30"
                                    <c:if test="${mct.resultFlg !='1'}"></c:if>
                                          <c:if test="${mct.resultFlg =='1'}">readonly</c:if>
                                          style="resize: none;">${mct.resultInfo}</textarea></td>
                            <td style="display:none;"><input type="hidden" name="mctStdItemId"
                                                             value="${mct.mctStdItemId}"/></td>
                        </tr>
                    </c:forEach>
                </table>
                </div>
            </div>


            <div class="group-accordion" collapsible="true" active="false" id="pdTnc141404accordion" style="width: 100%;overflow: auto">
                <h3>
                    <label>产品加工质量标准指标</label>
                </h3>
                <div>
                <table class="tree dataTable no-footer" WIDTH="100%" id="PD141149_list_grid">
                    <tr>
                        <td width="100%" colspan="13" align="left"><p>　产品特征(包括产品规格、口味、加工方法)：</p></td>
                    </tr>
                    <tr style="background:#DBDBDB;width:100%">
                        <td width="8%" rowspan="2" align="center">指标类容</td>
                        <td width="3%" rowspan="2" align="center">序号</td>
                        <td width="12%" colspan="2" align="center">A1级技术标准<br/>(准入日)</td>
                        <td width="12%" colspan="2" align="center">A2级技术标准<br/>(准入日)</td>
                        <td width="12%" colspan="2" align="center">A3级技术标准<br/>(准入日)</td>
                        <td width="20%" align="center" colspan="2" width="40px">论证中技术标准</td>
                        <td width="20%" align="center" colspan="2" width="40px">禁止准入技术标准</td>
                        <td align="center" rowspan="2">备注</td>
                    </tr>
                    <tr style="background:#DBDBDB">
                        <td align="center">标准</td>
                        <td align="center">是否符合</td>
                        <td align="center">标准</td>
                        <td align="center">是否符合</td>
                        <td align="center">标准</td>
                        <td align="center">是否符合</td>
                        <td align="center">市场需求标准<br/>(提出日、结案日)</td>
                        <td align="center">供应商习惯性标准<br/>(提出日、结案日)</td>
                        <td align="center">市场提出<br/>(禁止准入日)</td>
                        <td align="center">供应商提出<br/>(禁止准入日)</td>
                    </tr>
                    <c:forEach items="${listTnc}" var="pdTncTd" varStatus="tncPd">
                        <tr class="treegrid-${pdTncTd.tncStdItemId}" style="background-color:#F8F8FF">
                            <td>${pdTncTd.tncStdItemName}<input type="hidden" name="tncStdItemName"
                                                                value="${pdTncTd.tncStdItemName}"></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr class="treegrid-${tncPd.index+1.1} treegrid-parent-${pdTncTd.tncStdItemId}">
                            <td>
                                <input type="hidden" name="tncStdItemId" value="${pdTncTd.tncStdItemId}">
                            </td>
                            <td>${tncPd.index+1}</td>
                            <td><textarea rows="3" cols="25" readonly
                                          style="resize: none;">${pdTncTd.tncStdVal1}</textarea></td>
                            <td align="center">符合:<input type="checkbox" class="tncCheckBox${tncPd.index+1}"
                                                         name="tncCheckBox"
                                                         value="1" ${pdTncTd.resultFlg eq "1"?"checked":""}/></td>
                            <td><textarea rows="3" cols="25" readonly
                                          style="resize: none;">${pdTncTd.tncStdVal2}</textarea></td>
                            <td align="center">符合:<input type="checkbox" class="tncCheckBox${tncPd.index+1}"
                                                         name="tncCheckBox"
                                                         value="2" ${pdTncTd.resultFlg eq "2"?"checked":""}/></td>
                            <td><textarea rows="3" cols="25" readonly
                                          style="resize: none;">${pdTncTd.tncStdVal3}</textarea></td>
                            <td align="center">符合:<input type="checkbox" class="tncCheckBox${tncPd.index+1}"
                                                         name="tncCheckBox"
                                                         value="3" ${pdTncTd.resultFlg eq "3"?"checked":""}/>
                            </td>
                            <td style="display:none;"><input name="tncCheckBox" type="checkbox"
                                                             class="tncCheckBox${tncPd.index+1}" value="0"
                                                             <c:if test="${pdTncTd.resultFlg !='1' and pdTncTd.resultFlg != '2' and pdTncTd.resultFlg !='3'}">checked</c:if>
                                    <c:if test="${pdTncTd.resultFlg =='1' or pdTncTd.resultFlg == '2' or pdTncTd.resultFlg =='3'}"></c:if>/>
                            </td>
                            <td id="argMarNeed" valign="top">
                                <a class="argMarNeedBtn${tncPd.index}" name="argMarNeedBtn" href="javascript:void(0);"
                                   col="10"><img src="${ctx}/static/core/images/action/details_open.png"
                                                 id="argMarNeedImg${tncPd.index}"/></a>

                                <div style="display:none;margin-left:20px;margin-right:0px;width:300px"
                                     id="divArgMarNeed${tncPd.index}">
                                    <table class="tree dataTable no-footer" style="min-width:250px" width="250px"
                                           showAddBtn="true">
                                        <tr style="background:#DBDBDB">
                                            <td align="center">内容</td>
                                            <td align="center">提出日</td>
                                            <td align="center">结案日</td>
                                        </tr>
                                        <c:forEach items="${pdTncTd.pdTncMarkeyList}" var="tncMarkey">
                                            <tr>
                                                <td align="center">${tncMarkey.tncStdVal}</td>
                                                <td align="center">${tncMarkey.raiseDateShow}</td>
                                                <td align="center">${tncMarkey.fixDateShow}</td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </td>
                            <!-- 供应商列表 -->
                            <td id="argProNeed" valign="top">
                                <a class="argProNeedBtn${tncPd.index}" name="argProNeedBtn" href="javascript:void(0);"
                                   col="10"><img src="${ctx}/static/core/images/action/details_open.png"
                                                 id="argProNeedImg${tncPd.index}"/></a>

                                <div style="display:none;margin-left:20px;margin-right:0px;width:350px"
                                     id="divArgProNeed${tncPd.index}">
                                    <table class="tree dataTable no-footer" style="min-width:250px" width="250px"
                                           showAddBtn="true">
                                        <tr style="background:#DBDBDB">
                                            <td align="center">供应商编码</td>
                                            <td align="center">内容</td>
                                            <td align="center">提出日</td>
                                            <td align="center">结案日</td>
                                        </tr>
                                        <c:forEach items="${pdTncTd.pdTncProviderList}" var="tncProkey">
                                            <tr>
                                                <td align="center">${tncProkey.slPdId}</td>
                                                <td align="center">${tncProkey.tncStdVal}</td>
                                                <td align="center">${tncProkey.proRaiseDateShow}</td>
                                                <td align="center">${tncProkey.proFixDateShow}</td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </td>
                            <!-- 市场禁止日 -->
                            <td id="argMarNeedNo" valign="top">
                                <a class="argMarNeedNoBtn${tncPd.index}" name="argMarNeedNoBtn"
                                   href="javascript:void(0);"
                                   col="10"><img src="${ctx}/static/core/images/action/details_open.png"
                                                 id="argMarNeedNoImg${tncPd.index}"/></a>

                                <div style="display:none;margin-left:20px;margin-right:0px;width:260px"
                                     id="divArgMarNoNeed${tncPd.index}">
                                    <table class="tree dataTable no-footer" style="min-width:250px" width="250px"
                                           showAddBtn="true">
                                        <tr style="background:#DBDBDB">
                                            <td align="center">内容</td>
                                            <td align="center">禁止准入日</td>
                                        </tr>
                                        <c:forEach items="${pdTncTd.pdTncMarkeyNoList}" var="tncMarkeyNo">
                                            <tr>
                                                <td align="center">${tncMarkeyNo.tncStdVal}</td>
                                                <td align="center">${tncMarkeyNo.fixDateShow}</td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </td>
                            <!-- 供应商禁止日 -->
                            <td valign="top">
                                <a class="argProNeedNoBtn${tncPd.index}" name="argProNeedNoBtn"
                                   href="javascript:void(0);"
                                   col="10"><img src="${ctx}/static/core/images/action/details_open.png"
                                                 id="argProNeedNoImg${tncPd.index}"/></a>

                                <div style="display:none;margin-left:20px;margin-right:0px;width:260px"
                                     id="divArgProNoNeed${tncPd.index}">
                                    <table class="tree dataTable no-footer" style="min-width:250px" width="250px"
                                           showAddBtn="true">
                                        <tr style="background:#DBDBDB">
                                            <td align="center">生产商编码</td>
                                            <td align="center">内容</td>
                                            <td align="center">禁止准入日</td>
                                        </tr>
                                        <c:forEach items="${pdTncTd.pdTncProviderNoList}" var="tncProkeyNo">
                                            <tr>
                                                <td align="center">${tncProkeyNo.slPdId}</td>
                                                <td align="center">${tncProkeyNo.tncStdVal}</td>
                                                <td align="center">${tncProkeyNo.proFixDateShow}</td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </td>
                            <td><textarea id="tncCheckBox${tncPd.index+1}" name="tncResultInfo" rows="3" cols="30"
                                    <c:if test="${pdTncTd.resultFlg !='1' and pdTncTd.resultFlg != '2' and pdTncTd.resultFlg !='3'}"></c:if>
                                          <c:if test="${pdTncTd.resultFlg =='1' or pdTncTd.resultFlg == '2' or pdTncTd.resultFlg =='3'}">readonly</c:if>
                                          style="resize: none;">${pdTncTd.resultInfo}</textarea></td>
                        </tr>
                    </c:forEach>
                </table>
                </div>
            </div>

            <div class="group-accordion" collapsible="true" active="false" style="width: 100%;overflow: auto">
                <h3>
                    <label>产品通用质量标准</label>
                </h3>
                <div>
                <table class="tree dataTable no-footer" style="width: 100%">
                    <tr>
                        <td width="100%" colspan="5" align="left"><p>　产品特征(包括产品规格、口味、加工方法)：</p></td>
                    </tr>
                    <tr style="background: #DBDBDB;width:100%">
                        <td width="20%" rowspan="3" align="center">指标类容</td>
                        <td colspan="3" width="50%" align="center">等级</td>
                        <td rowspan="3" width="30%" align="center">备注</td>
                    </tr>
                    <tr style="background: #DBDBDB;width:100%">
                        <td align="center" colspan="2">合格</td>
                        <td align="center" rowspan="2">不合格</td>
                    </tr>
                    <tr style="background: #DBDBDB;width:100%">
                        <td align="center">标准</td>
                        <td align="center">是否符合</td>
                    </tr>
                    <c:forEach items="${listGnq}" var="listParent" varStatus="listGnqPd">
                        <tr class="treegrid-${listParent.gnqStdItemId}-${listGnqPd.index}"
                            style="background-color:#F8F8FF">
                            <td width="25%">${listParent.gnqStdItemName}</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <c:forEach items="${listParent.pdGnqChildList}" var="gnqBean" varStatus="gnqPd">
                            <tr class="treegrid-${gnqPd.index+1.2} treegrid-parent-${listParent.gnqStdItemId}-${listGnqPd.index}">
                                <td>${gnqBean.gnqStdItemName}<input type="hidden" name="gnqStdItemName"
                                                                    value="${gnqBean.gnqStdItemName}"/></td>
                                <td style="display:none;"></td>
                                <td><textarea rows="3" cols="30" readonly
                                              style="resize: none;">${gnqBean.gnqOkVal}</textarea></td>
                                <td align="center">符合:<input type="checkbox" name="gnqCheckBox" value="1"
                                                             class="gnqCheckBox-${listGnqPd.index}-${gnqPd.index+1}" ${gnqBean.resultFlg eq "1"?"checked":""}/>
                                </td>
                                <td style="display:none;"><input name="gnqCheckBox" type="checkbox"
                                                                 class="gnqCheckBox-${listGnqPd.index}-${gnqPd.index+1}"
                                                                 value="0"
                                                                 <c:if test="${gnqBean.resultFlg !='1'}">checked</c:if>
                                        <c:if test="${gnqBean.resultFlg =='1'}"></c:if>/></td>
                                <td><textarea rows="3" cols="30" readonly
                                              style="resize: none;">${gnqBean.gnqNgVal}</textarea></td>
                                <td><textarea id="gnqCheckBox-${listGnqPd.index}-${gnqPd.index+1}" name="gnqResultInfo"
                                              rows="3" cols="30"
                                        <c:if test="${gnqBean.resultFlg !='1'}"></c:if>
                                              <c:if test="${gnqBean.resultFlg =='1'}">readonly</c:if>
                                              style="resize: none;">${gnqBean.resultInfo}</textarea></td>
                                <td style="display:none;"><input type="hidden" name="gnqStdItemId"
                                                                 value="${gnqBean.gnqStdItemId}"/></td>
                            </tr>
                        </c:forEach>
                    </c:forEach>
                </table>
                </div>
            </div>

            <div class="group-accordion" collapsible="true" active="false" style="width: 100%;overflow: auto">
                <h3>
                    <label>安全标准指标</label>
                </h3>
                <div>
                <table class="tree dataTable no-footer" style="width: 100%">
                    <tr>
                        <td width="100%" colspan="5" align="left"><p>　产品特征(包括产品规格、口味、加工方法)：</p></td>
                    </tr>
                    <tr style="background: #DBDBDB;width:100%">
                        <td width="20%" rowspan="3" align="center">指标类容</td>
                        <td colspan="3" width="50%" align="center">等级</td>
                        <td rowspan="3" width="30%" align="center">备注</td>
                    </tr>
                    <tr style="background: #DBDBDB;width:100%">
                        <td align="center" colspan="2">合格</td>
                        <td align="center" rowspan="2">不合格</td>
                    </tr>
                    <tr style="background: #DBDBDB;width:100%">
                        <td align="center">标准</td>
                        <td align="center">是否符合</td>
                    </tr>
                    <c:forEach items="${listSft}" var="listParent" varStatus="sftListPd">
                        <tr class="treegrid-${listParent.sftStdItemId}-${sftListPd.index+3}"
                            style="background-color:#F8F8FF">
                            <td width="25%">${listParent.sftStdItemName}</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <c:forEach items="${listParent.pdSftChildList}" var="sftBean" varStatus="sftChild">
                            <tr class="treegrid-${sftBean.sftStdItemId}-${sftChild.index+1.3} treegrid-parent-${listParent.sftStdItemId}-${sftListPd.index+3}">
                                <td>${sftBean.sftStdItemName}<input type="hidden" name="sftStdItemName"
                                                                    value="${sftBean.sftStdItemName}"/></td>
                                <td style="display:none;"><input type="hidden" name="sftStdItemId"
                                                                 value="${sftBean.sftStdItemId}"/></td>
                                <td><textarea rows="3" cols="30" readonly
                                              style="resize: none;">${sftBean.sftOkVal}</textarea></td>
                                <td align="center">符合:<input type="checkbox" name="sftCheckBox" value="1"
                                                             class="sftCheckBox${sftListPd.index}-${sftChild.index+1}" ${sftBean.resultFlg eq "1"?"checked":""}/>
                                </td>
                                <td style="display:none;"><input name="sftCheckBox" type="checkbox"
                                                                 class="sftCheckBox${sftListPd.index}-${sftChild.index+1}"
                                                                 value="0"
                                                                 <c:if test="${sftBean.resultFlg !='1'}">checked</c:if>
                                        <c:if test="${sftBean.resultFlg =='1'}"></c:if>/></td>
                                <td><textarea rows="3" cols="30" readonly
                                              style="resize: none;">${sftBean.sftNgVal}</textarea></td>
                                <td><textarea id="sftCheckBox${sftListPd.index}-${sftChild.index+1}"
                                              name="sftResultInfo"
                                              rows="3" cols="30"
                                        <c:if test="${sftBean.resultFlg !='1'}"></c:if>
                                              <c:if test="${sftBean.resultFlg =='1'}">readonly</c:if>
                                              style="resize: none;">${sftBean.resultInfo}</textarea></td>
                            </tr>
                        </c:forEach>
                    </c:forEach>
                </table>
                </div>
            </div>


            <div class="group-accordion" collapsible="true" active="false" style="width: 100%;overflow: auto">
                <h3>
                    <label>存储运输标准指标</label>
                </h3>
                <div>
                <table class="tree dataTable no-footer" style="width: 100%">
                    <tr>
                        <td width="100%" colspan="5" align="left"><p>　产品特征(包括产品规格、口味、加工方法)：</p></td>
                    </tr>
                    <tr style="background: #DBDBDB;width:100%">
                        <td width="20%" rowspan="3" align="center">指标类容</td>
                        <td colspan="3" width="50%" align="center">等级</td>
                        <td rowspan="3" width="30%" align="center">备注</td>
                    </tr>
                    <tr style="background: #DBDBDB;width:100%">
                        <td align="center" colspan="2">合格</td>
                        <td align="center" rowspan="2">不合格</td>
                    </tr>
                    <tr style="background: #DBDBDB;width:100%">
                        <td align="center">标准</td>
                        <td align="center">是否符合</td>
                    </tr>
                    <c:forEach items="${listTsp}" var="listParent" varStatus="tspPd">
                        <tr class="treegrid-${listParent.tspStdItemId}-${tspPd.index+5}"
                            style="background-color:#F8F8FF">
                            <td width="25%">${listParent.tspStdItemName}</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <c:forEach items="${listParent.pdTspChildList}" var="tspChildPd" varStatus="tspChild">
                            <tr class="treegrid-${tspChildPd.tspStdItemId}-${tspChild.index+1.4} treegrid-parent-${listParent.tspStdItemId}-${tspPd.index+5}">
                                <td>${tspChildPd.tspStdItemName}<input type="hidden" name="tspStdItemName"
                                                                       value="${tspChildPd.tspStdItemName}"/></td>
                                <td style="display:none;"><input type="hidden" name="tspStdItemId"
                                                                 value="${tspChildPd.tspStdItemId}"/></td>
                                <td width="25%"><textarea rows="3" cols="30" readonly
                                                          style="resize: none;">${tspChildPd.tspOkVal}</textarea></td>
                                <td align="center">符合:<input type="checkbox" name="tspCheckBox" value="1"
                                                             class="tspCheckBox${tspPd.index}-${tspChild.index+1}" ${tspChildPd.resultFlg eq "1"?"checked":""}/>
                                </td>
                                <td style="display:none;"><input name="tspCheckBox" type="checkbox"
                                                                 class="tspCheckBox${tspPd.index}-${tspChild.index+1}"
                                                                 value="0"
                                                                 <c:if test="${tspChildPd.resultFlg !='1'}">checked</c:if>
                                        <c:if test="${tspChildPd.resultFlg =='1'}"></c:if>/></td>
                                <td width="25%"><textarea rows="3" cols="30" readonly
                                                          style="resize: none;">${tspChildPd.tspNgVal}</textarea></td>
                                <td><textarea id="tspCheckBox${tspPd.index}-${tspChild.index+1}" name="tspResultInfo"
                                              rows="3" cols="30"
                                        <c:if test="${tspChildPd.resultFlg !='1'}"></c:if>
                                              <c:if test="${tspChildPd.resultFlg =='1'}">readonly</c:if>
                                              style="resize: none;">${tspChildPd.resultInfo}</textarea></td>
                            </tr>
                        </c:forEach>
                    </c:forEach>
                </table>
                </div>
            </div>

            <div class="group-accordion" collapsible="true" active="false" id="normsTable" style="width: 100%;overflow: auto">
                <h3>
                    <label>产品包装信息编辑</label>
                </h3>
                <div>
                <table class="tree dataTable no-footer" style="width: 100%">
                    <tr>
                        <td width="20%" colspan="${fn:length(listNorms)*2+1}" align="left"><p>　产品特征(包括产品规格、口味、加工方法)：</p></td>
                    </tr>
                    <tr style="background: #DBDBDB;width:100%">
                        <td width="5%" rowspan="3" align="center">指标类容</td>
                        <td colspan="${fn:length(listNorms)*2}" align="center">准入标准</td>
                    </tr>

                    <tr style="background: #DBDBDB;width:100%">
                        <c:forEach items="${listNorms}" var="norms" varStatus="i">
                            <td align="center" colspan="2">特征${i.index+1}</td>
                        </c:forEach>
                    </tr>
                    <tr style="background: #DBDBDB;">
                        <c:forEach items="${listNorms}" var="norms">
                            <td align="center">标准</td>
                            <td align="center">是否符合</td>
                        </c:forEach>
                    </tr>
                    <tr class="treegrid-102" style="background-color:#F8F8FF;width:100%">
                        <td>内包装</td>
                        <c:forEach items="${listNorms}">
                            <td></td>
                            <td></td>
                        </c:forEach>
                    </tr>
                    <tr class="treegrid-103 treegrid-parent-102">
                        <td>单个产品规格净重</td>
                        <c:forEach items="${listNorms}" var="normsBeans" varStatus="normsIndex">
                            <td style="display:none;"><input name="normsCode" type="checkbox"
                                                             class="resultNormsFlg${normsIndex.index+1}"
                                                             value="${normsBeans.normsCode}"/>
                                <input name="normsName" type="checkbox"
                                       class="resultNormsFlg${normsIndex.index+1}"
                                       value="${normsBeans.normsOut}"/>
                                <c:if test="${normsBeans.normsCodeOld != null and normsBeans.normsCodeOld !=''}">
                                    <input type="hidden" name="normsCodeOld" value="${normsBeans.normsCodeOld}"/>
                                    <input type="hidden" name="normsName" value="${normsBeans.normsOut}"
                                           id="normsNames"/>
                                </c:if>
                            </td>
                        </c:forEach>
                        <input type="hidden" name="normsCode" value="0" id="defalutValue">
                        <c:forEach items="${listNorms}" var="normsBeans" varStatus="normsIndex">
                            <td><textarea rows="3" cols="30" readonly
                                          style="resize: none;">${normsBeans.normsSuttle}</textarea></td>
                            <td align="center">符合:<input type="checkbox" name="resultInnerweightFlg"
                                                         value="${normsIndex.index+1}"
                                                         class="resultNormsFlg${normsIndex.index+1}"
                                                         <c:if test="${normsBeans.resultInnerweightFlg eq normsIndex.index+1}">checked</c:if>/>
                            </td>
                        </c:forEach>
                        <td style="display:none;"><input name="resultInnerweightFlg" type="checkbox"
                                                         class="resultNormsFlg"
                                                         value="0"
                                                         <c:if test="${normsBeans.resultInnerweightFlg =='0' or normsBeans.resultInnerweightFlg == null}">checked</c:if>
                                /></td>
                    </tr>
                    <tr class="treegrid-104 treegrid-parent-102">
                        <td>单个产品净重误差范围</td>
                        <c:forEach items="${listNorms}" var="normsBeans" varStatus="normsIndex">
                            <td><textarea rows="3" cols="30" readonly
                                          style="resize: none;">${normsBeans.normsError}</textarea></td>
                            <td align="center">符合:<input type="checkbox" name="resultInnererrorFlg"
                                                         class="resultNormsFlg${normsIndex.index+1}"
                                                         value="${normsIndex.index+1}" ${normsBeans.resultInnererrorFlg eq normsIndex.index+1?"checked":""}/>
                            </td>
                        </c:forEach>
                        <td style="display:none;"><input name="resultInnererrorFlg" type="checkbox"
                                                         class="resultNormsFlg"
                                                         value="0"
                                                         <c:if test="${normsBeans.resultInnererrorFlg =='0' or normsBeans.resultInnererrorFlg==null}">checked</c:if>
                                /></td>
                    </tr>
                    <tr class="treegrid-105 treegrid-parent-102">
                        <td>内包装净重/个数</td>
                        <c:forEach items="${listNorms}" var="normsBeans" varStatus="normsIndex">
                            <td><textarea rows="3" cols="30" readonly
                                          style="resize: none;">${normsBeans.normsNumber}</textarea></td>
                            <td align="center">符合:<input type="checkbox" name="resultInnercountFlg"
                                                         class="resultNormsFlg${normsIndex.index+1}"
                                                         value="${normsIndex.index+1}" ${normsBeans.resultInnercountFlg eq normsIndex.index+1?"checked":""}/>
                            </td>
                        </c:forEach>
                        <td style="display:none;"><input name="resultInnercountFlg" type="checkbox"
                                                         class="resultNormsFlg"
                                                         value="0"
                                                         <c:if test="${normsBeans.resultInnercountFlg =='0' or normsBeans.resultInnercountFlg==null}">checked</c:if>
                                /></td>
                    </tr>
                    <tr class="treegrid-106 treegrid-parent-102">
                        <td>内包装尺寸</td>
                        <c:forEach items="${listNorms}" var="normsBeans" varStatus="normsIndex">
                            <td><textarea rows="3" cols="30" readonly
                                          style="resize: none;">${normsBeans.normsSize}</textarea></td>
                            <td align="center">符合:<input type="checkbox" name="resultInnersizeFlg"
                                                         class="resultNormsFlg${normsIndex.index+1}"
                                                         value="${normsIndex.index+1}" ${normsBeans.resultInnersizeFlg eq normsIndex.index+1?"checked":""}/>
                            </td>
                        </c:forEach>
                        <td style="display:none;"><input name="resultInnersizeFlg" type="checkbox"
                                                         class="resultNormsFlg"
                                                         value="0"
                                                         <c:if test="${normsBeans.resultInnersizeFlg =='0' or normsBeans.resultInnersizeFlg==null}">checked</c:if>
                                /></td>
                    </tr>
                    <tr class="treegrid-107 treegrid-parent-102">
                        <td>内包装材质</td>
                        <c:forEach items="${listNorms}" var="normsBeans" varStatus="normsIndex">
                            <td><textarea rows="3" cols="30" readonly
                                          style="resize: none;">${normsBeans.normsTexture}</textarea></td>
                            <td align="center">符合:<input type="checkbox" name="resultInnermatFlg"
                                                         class="resultNormsFlg${normsIndex.index+1}"
                                                         value="${normsIndex.index+1}" ${normsBeans.resultInnermatFlg eq normsIndex.index+1?"checked":""}/>
                            </td>
                        </c:forEach>
                        <td style="display:none;"><input name="resultInnermatFlg" type="checkbox"
                                                         class="resultNormsFlg"
                                                         value="0"
                                                         <c:if test="${normsBeans.resultInnermatFlg =='0' or normsBeans.resultInnermatFlg==null}">checked</c:if>
                                /></td>
                    </tr>
                    <tr class="treegrid-122" style="background-color:#F8F8FF">
                        <td>外包装</td>
                        <c:forEach items="${listNorms}" var="norms">
                            <td></td>
                            <td></td>
                        </c:forEach>
                    </tr>
                    <tr class="treegrid-125 treegrid-parent-122">
                        <td>外包装规格</td>
                        <c:forEach items="${listNorms}" var="normsBeans" varStatus="normsIndex">
                            <td><textarea rows="3" cols="30" readonly
                                          style="resize: none;">${normsBeans.normsOut}</textarea></td>
                            <td align="center">符合:<input type="checkbox" name="resultOutspecFlg"
                                                         class="resultNormsFlg${normsIndex.index+1}"
                                                         value="${normsIndex.index+1}" ${normsBeans.resultOutspecFlg eq normsIndex.index+1?"checked":""}/>
                            </td>
                        </c:forEach>
                        <td style="display:none;"><input name="resultOutspecFlg" type="checkbox"
                                                         class="resultNormsFlg"
                                                         value="0"
                                                         <c:if test="${normsBeans.resultOutspecFlg =='0' or normsBeans.resultOutspecFlg==null}">checked</c:if>
                                /></td>
                    </tr>
                    <tr class="treegrid-126 treegrid-parent-122">
                        <td>外包装净重/毛重</td>
                        <c:forEach items="${listNorms}" var="normsBeans" varStatus="normsIndex">
                            <td><textarea rows="3" cols="30" readonly
                                          style="resize: none;">${normsBeans.normsKg}</textarea></td>
                            <td align="center">符合:<input type="checkbox" name="resultOutweightFlg"
                                                         class="resultNormsFlg${normsIndex.index+1}"
                                                         value="${normsIndex.index+1}" ${normsBeans.resultOutweightFlg eq normsIndex.index+1?"checked":""}/>
                            </td>
                        </c:forEach>
                        <td style="display:none;"><input name="resultOutweightFlg" type="checkbox"
                                                         class="resultNormsFlg"
                                                         value="0"
                                                         <c:if test="${normsBeans.resultOutweightFlg =='0' or normsBeans.resultOutweightFlg==null}">checked</c:if>
                                /></td>
                    </tr>
                    <tr class="treegrid-127 treegrid-parent-122">
                        <td>外包装尺寸</td>
                        <c:forEach items="${listNorms}" var="normsBeans" varStatus="normsIndex">

                            <td><textarea rows="3" cols="30" readonly
                                          style="resize: none;">${normsBeans.normsOutSize}</textarea></td>
                            <td align="center">符合:<input type="checkbox" name="resultOutsizeFlg"
                                                         class="resultNormsFlg${normsIndex.index+1}"
                                                         value="${normsIndex.index+1}" ${normsBeans.resultOutsizeFlg eq normsIndex.index+1?"checked":""}/>
                            </td>
                        </c:forEach>
                        <td style="display:none;"><input name="resultOutsizeFlg" type="checkbox"
                                                         class="resultNormsFlg"
                                                         value="0"
                                                         <c:if test="${normsBeans.resultOutsizeFlg =='0' or normsBeans.resultOutsizeFlg==null}">checked</c:if>
                                                         class="resultNormsFlg"
                                /></td>
                    </tr>
                    <tr class="treegrid-128 treegrid-parent-122">
                        <td>包装材质</td>
                        <c:forEach items="${listNorms}" var="normsBeans" varStatus="normsIndex">
                            <td><textarea rows="3" cols="30" readonly
                                          style="resize: none;">${normsBeans.normsOutTexture}</textarea></td>
                            <td align="center">符合:<input type="checkbox" name="resultOutmatFlg"
                                                         class="resultNormsFlg${normsIndex.index+1}"
                                                         value="${normsIndex.index+1}" ${normsBeans.resultOutmatFlg eq normsIndex.index+1?"checked":""}/>
                            </td>
                        </c:forEach>
                        <td style="display:none;"><input name="resultOutmatFlg" type="checkbox"
                                                         class="resultNormsFlg"
                                                         value="0"
                                                         <c:if test="${normsBeans.resultOutmatFlg =='0' or normsBeans.resultOutmatFlg==null}">checked</c:if>
                                /></td>
                    </tr>
                </table>
                </div>
            </div>
            <div class="group-accordion" collapsible="false" active="false">
                <h3>
                    <label>综合评价</label>
                </h3>
                <table style="width: 100%">
                    <tr>
                        <td style="width: 100%;padding:5px">
                            &emsp;等级评定:&emsp;<input name="resultGrade"
                                                    type="radio" ${beans.resultGrade eq "A1"?"checked":""}
                                                    VALUE="1"/>A1&emsp;&emsp;<input name="resultGrade"
                                                                                    type="radio" ${beans.resultGrade eq "A2"?"checked":""}
                                                                                    VALUE="2"/>A2&emsp;&emsp;<input
                                name="resultGrade" type="radio" ${beans.resultGrade eq "A3"?"checked":""} VALUE="3"/>A3
                        </td>
                        <td style="display:none;"><input name="resultGrade"
                                                         type="radio"
                                                         <c:if test="${beans.resultGrade ==null or beans.resultGrade==''}">checked</c:if>
                                                         value="0"/></td>
                    </tr>
                    <tr>
                        <td style="width: 100%;padding:5px">
                            &emsp;评定总结:&emsp;<textarea name="resultInfo" rows="5" cols="50"
                                                       style="resize: none;">${beans.resultInfo}</textarea>
                        </td>
                    </tr>
                </table>
            </div>
            <div>
                <msk:button buttonValue="保存" buttonType="button" buttonId="PD141404.SAVE"/>
            </div>
        </div>
    </form>
</div>
<script src="${ctx}/static/js/pd/PD141404.js"></script>
