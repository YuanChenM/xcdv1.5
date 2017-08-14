<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/comm/taglib.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
            <table id="SL24110301_data" class="tree dataTable no-footer" style="width: 100%">
	            <thead>
	                <tr align="center" style="background:#DBDBDB">
	                    <th coltype="text" width="25%" name="pdSpec">证照</th>
	                    <th coltype="text" width="25%" name="pdSpec">图片</th>
	                    <th coltype="text" width="50%" name="pdSpec">资质描述</th>
	                </tr>
	            </thead>
                    <c:if test="${sL0301Bean.licType!=1}">
	                <tr>
	                     <td coltype="text" width="20%" height="50px" >营业执照</td>
                         <td coltype="text" width="20%" height="50px"><a class="tooltip" title="营业执照" href="${sL0301Bean.licPath}" target="_blank" src="${sL0301Bean.licPath}" id="licPathId" name="imgSrc"><%--<img src="${sL0301Bean.licPath}" height="100px" width="200px"/>--%></a></td>
	                     <td coltype="text" width="20%" height="60px">${sL0301Bean.licSpeck}</td>
	                </tr>
	                <tr>
	                    <td coltype="text" width="20%" height="50px" name="pdSpec">税务登记证</td>
	                    <td coltype="text" width="20%" height="50px" name="pdSpec"><a class="tooltip" title="税务登记证" href="${sL0301Bean.taxPath}" target="_blank" src="${sL0301Bean.taxPath}" id="taxPathId" name="imgSrc"><%--<img src="${sL0301Bean.taxPath}" height="100px" width="200px"/>--%></a></td>
	                    <td coltype="text" width="20%" height="60px" name="pdSpec">${sL0301Bean.taxSpeck}</td>
	                </tr>
	                <tr>
	                    <td coltype="text" width="20%" height="50px" name="pdSpec">组织代码证</td>
	                    <td coltype="text" width="20%" height="50px" name="pdSpec"><a class="tooltip" title="组织代码证" href="${sL0301Bean.orgNoPath}" target="_blank" src="${sL0301Bean.orgNoPath}" id="orgNoPathId" name="imgSrc"><%--<img src="${sL0301Bean.orgNoPath}" height="100px" width="200px"/>--%></a></td>
	                    <td coltype="text" width="20%" height="60px" name="pdSpec">${sL0301Bean.orgSpeck}</td>
	                </tr>
                    </c:if>
	                <tr>
	                    <td coltype="text" width="20%" height="50px" name="pdSpec">银行开户许可证</td>
	                    <td coltype="text" width="20%" height="50px" name="pdSpec"><a class="tooltip" title="银行开户许可证" href="${sL0301Bean.balPath}" target="_blank" src="${sL0301Bean.balPath}" id="balPathId" name="imgSrc"><%--<img src="${sL0301Bean.balPath}" height="100px" width="200px"/>--%></a></td>
	                    <td coltype="text" width="20%" height="60px" name="pdSpec">${sL0301Bean.balSpeck}</td>
	                </tr>
                <c:if test="${sL0301Bean.licType==1}">
                <tr>
                    <td coltype="text" width="20%" height="50px" name="pdSpec">三证合一营业执照</td>
                    <td coltype="text" width="20%" height="50px" name="pdSpec"><a class="tooltip" title="三证合一营业执照" href="${sL0301Bean.licTypePath}" target="_blank" src="${sL0301Bean.licTypePath}" id="licTypePathId" name="imgSrc"><%--<img src="${sL0301Bean.licTypePath}" height="100px" width="200px"/>--%></a></td>
                    <td coltype="text" width="20%" height="60px" name="pdSpec">${sL0301Bean.licTypeSpeck}</td>
                </tr>
                </c:if>
                <c:choose>
                <c:when test="${selfFlg==1 and proxyFlg==1 and oemFlg==1}">
                    <tr>
                    <td coltype="text" width="20%" height="50px" name="pdSpec">食品流通许可证</td>
                    <td coltype="text" width="20%" height="50px" name="pdSpec"><a title="食品流通许可证" href="${sL0301Bean.fdlPath}" target="_blank" src="${sL0301Bean.fdlPath}" id="fdlPathId"><%--<img src="${sL0301Bean.fdlPath}" height="100px" width="200px"/>--%></a></td>
                    <td coltype="text" width="20%" height="60px" name="pdSpec">许可证编码：${sL0301Bean.fdlNo}<br/>有效期限：${sL0301Bean.fdlTermEnds}</td>
                    </tr>
                    <tr>
                    <td coltype="text" width="20%" height="50px" name="pdSpec">代理及分销授权书</td>
                    <td coltype="text" width="20%" height="50px" name="pdSpec"><a title="代理及分销授权书" href="${sL0301Bean.authPath}" target="_blank" src="${sL0301Bean.authPath}" id="authPathId"><%--<img src="${sL0301Bean.authPath}" height="100px" width="200px"/>--%></a></td>
                    <td coltype="text" width="20%" height="60px" name="pdSpec">授权单位：${sL0301Bean.atuhEpName}</td>
                    </tr>
                    <tr>
                    <td coltype="text" width="20%" height="50px" name="pdSpec">OEM委托授权书</td>
                    <td coltype="text" width="20%" height="50px" name="pdSpec"><a title="OEM委托授权书" href="${sL0301Bean.oemAuthPath}" target="_blank" src="${sL0301Bean.oemAuthPath}" id="oemAuthPathId"><%--<img src="${sL0301Bean.oemAuthPath}" height="100px" width="200px"/>--%></a></td>
                    <td coltype="text" width="20%" height="60px" name="pdSpec">委托单位：${sL0301Bean.atuhEpName2}</td>
                    </tr>
                </c:when>
                <c:when test="${proxyFlg==1 and oemFlg==1}">
                     <tr>
                    <td coltype="text" width="20%" height="50px" name="pdSpec">食品流通许可证</td>
                    <td coltype="text" width="20%" height="50px" name="pdSpec"><a title="食品流通许可证" href="${sL0301Bean.fdlPath}" target="_blank" src="${sL0301Bean.fdlPath}" class="fdlPathId"><%--<img src="${sL0301Bean.fdlPath}" height="100px" width="200px"/>--%></a></td>
                    <td coltype="text" width="20%" height="60px" name="pdSpec">许可证编码：${sL0301Bean.fdlNo}<br/>有效期限：${sL0301Bean.fdlTermEnds}</td>
                    </tr>
                     <tr>
                    <td coltype="text" width="20%" height="50px" name="pdSpec">代理及分销授权书</td>
                    <td coltype="text" width="20%" height="50px" name="pdSpec"><a title="代理及分销授权书" href="${sL0301Bean.authPath}" target="_blank" src="${sL0301Bean.authPath}" class="authPathId"><%--<img src="${sL0301Bean.authPath}" height="100px" width="200px"/>--%></a></td>
                    <td coltype="text" width="20%" height="60px" name="pdSpec">授权单位：${sL0301Bean.atuhEpName}</td>
                    </tr>
                    <tr>
                    <td coltype="text" width="20%" height="50px" name="pdSpec">OEM委托授权书</td>
                    <td coltype="text" width="20%" height="50px" name="pdSpec"><a title="OEM委托授权书" href="${sL0301Bean.oemAuthPath}" target="_blank" src="${sL0301Bean.oemAuthPath}" class="oemAuthPathId"><%--<img src="${sL0301Bean.oemAuthPath}" height="100px" width="200px"/>--%></a></td>
                    <td coltype="text" width="20%" height="60px" name="pdSpec">委托单位：${sL0301Bean.atuhEpName2}</td>
                    </tr>
                </c:when>
                 <c:when test="${selfFlg==1 and oemFlg==1}">
                     <tr>
                    <td coltype="text" width="20%" height="50px" name="pdSpec">食品流通许可证</td>
                    <td coltype="text" width="20%" height="50px" name="pdSpec"><a title="食品流通许可证" href="${sL0301Bean.fdlPath}" target="_blank" src="${sL0301Bean.fdlPath}" class="fdlPathId"><%--<img src="${sL0301Bean.fdlPath}" height="100px" width="200px"/>--%></a></td>
                    <td coltype="text" width="20%" height="60px" name="pdSpec">许可证编码：${sL0301Bean.fdlNo}<br/>有效期限：${sL0301Bean.fdlTermEnds}</td>
                    </tr>
                   <tr>
                    <td coltype="text" width="20%" height="50px" name="pdSpec">OEM委托授权书</td>
                    <td coltype="text" width="20%" height="50px" name="pdSpec"><a title="OEM委托授权书" href="${sL0301Bean.oemAuthPath}" target="_blank" src="${sL0301Bean.oemAuthPath}" class="oemAuthPathId"><%--<img src="${sL0301Bean.oemAuthPath}" height="100px" width="200px"/>--%></a></td>
                    <td coltype="text" width="20%" height="60px" name="pdSpec">委托单位：${sL0301Bean.atuhEpName2}</td>
                    </tr>
                </c:when>
                 <c:when test="${selfFlg==1 and proxyFlg==1}">
                     <tr>
                    <td coltype="text" width="20%" height="50px" name="pdSpec">食品流通许可证</td>
                    <td coltype="text" width="20%" height="50px" name="pdSpec"><a title="食品流通许可证" href="${sL0301Bean.fdlPath}" target="_blank" src="${sL0301Bean.fdlPath}" class="fdlPathId"><%--<img src="${sL0301Bean.fdlPath}" height="100px" width="200px"/>--%></a></td>
                    <td coltype="text" width="20%" height="60px" name="pdSpec">许可证编码：${sL0301Bean.fdlNo}<br/>有效期限：${sL0301Bean.fdlTermEnds}</td>
                    </tr>
                    <tr>
                    <td coltype="text" width="20%" height="50px" name="pdSpec">代理及分销授权书</td>
                    <td coltype="text" width="20%" height="50px" name="pdSpec"><a title="代理及分销授权书" href="${sL0301Bean.authPath}" target="_blank" src="${sL0301Bean.authPath}" class="authPathId"><%--<img src="${sL0301Bean.authPath}" height="100px" width="200px"/>--%></a></td>
                    <td coltype="text" width="20%" height="60px" name="pdSpec">授权单位：${sL0301Bean.atuhEpName}</td>
                    </tr>
                </c:when>
                 <c:when test="${proxyFlg==1}">
                     <tr>
                    <td coltype="text" width="20%" height="50px" name="pdSpec">食品流通许可证</td>
                    <td coltype="text" width="20%" height="50px" name="pdSpec"><a title="食品流通许可证" href="${sL0301Bean.fdlPath}" target="_blank" src="${sL0301Bean.fdlPath}" class="fdlPathId"><%--<img src="${sL0301Bean.fdlPath}" height="100px" width="200px"/>--%></a></td>
                    <td coltype="text" width="20%" height="60px" name="pdSpec">许可证编码：${sL0301Bean.fdlNo}<br/>有效期限：${sL0301Bean.fdlTermEnds}</td>
                    </tr>
                    <tr>
                    <td coltype="text" width="20%" height="50px" name="pdSpec">代理及分销授权书</td>
                    <td coltype="text" width="20%" height="50px" name="pdSpec"><a title="代理及分销授权书" href="${sL0301Bean.authPath}" target="_blank" src="${sL0301Bean.authPath}" class="authPathId"><%--<img src="${sL0301Bean.authPath}" height="100px" width="200px"/>--%></a></td>
                    <td coltype="text" width="20%" height="60px" name="pdSpec">授权单位：${sL0301Bean.atuhEpName}</td>
                    </tr>
                </c:when>
                <c:when test="${oemFlg==1}">
                    <tr>
                    <td coltype="text" width="20%" height="50px" name="pdSpec">食品流通许可证</td>
                    <td coltype="text" width="20%" height="50px" name="pdSpec"><a title="食品流通许可证" href="${sL0301Bean.fdlPath}" target="_blank" src="${sL0301Bean.fdlPath}" class="fdlPathId"><%--<img src="${sL0301Bean.fdlPath}" height="100px" width="200px"/>--%></a></td>
                    <td coltype="text" width="20%" height="60px" name="pdSpec">许可证编码：${sL0301Bean.fdlNo}<br/>有效期限：${sL0301Bean.fdlTermEnds}</td>
                    </tr>
                    <tr>
                    <td coltype="text" width="20%" height="50px" name="pdSpec">OEM委托授权书</td>
                    <td coltype="text" width="20%" height="50px" name="pdSpec"><a title="OEM委托授权书" href="${sL0301Bean.oemAuthPath}" target="_blank" src="${sL0301Bean.oemAuthPath}" class="oemAuthPathId"><%--<img src="${sL0301Bean.oemAuthPath}" height="100px" width="200px"/>--%></a></td>
                    <td coltype="text" width="20%" height="60px" name="pdSpec">委托单位：${sL0301Bean.atuhEpName2}</td>
                    </tr>
                </c:when>
                </c:choose>
            </table>
<script type="text/javascript" src="${ctx}/static/sl/js/SL24110301.js"></script>
