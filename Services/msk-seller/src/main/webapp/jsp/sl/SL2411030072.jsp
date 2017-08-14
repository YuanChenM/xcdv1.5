<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
    function callbackFun(message){
        $.alertMessage.info(message);
        HDF.closeLoadingMask();
        $('#main-content').postUrl(Main.contextPath + "/SL241103000/init");
    }
</script>
<div class="group-accordion" active="false">
    <h3>
        <label>卖家品牌</label>
    </h3>
    <div>
<div>
    <input type="radio" name="brandType" value="1" onchange="myBrand()"/>自有品牌
    <input type="radio" name="brandType" value="2" onchange="proxyBrand()"/>代理品牌
</div>

<div id="myBrand" style="display:block">

    <form:form action="${ctx}/SL241103007/insert" id="SL241103007Form"
               metdod="post" enctype="multipart/form-data">
        <table width="100%" border="0" cellpadding="0" cellspacing="0">

            <tr>
                <td align="right" width="50%">品牌名称</td>
                <td align="left" width="50%">
                    <input type="text" id="brandName" name="brandName"/>
                </td>
            </tr>

            <tr>
                <td align="right" width="50%">商标注册证编码</td>
                <td align="left" width="50%">
                    <input type="text" id="brandNo" name="brandNo"/>
                </td>
            </tr>

            <tr>
                <td align="right" width="50%">荣誉证书编号</td>
                <td align="left" width="50%">
                    <input type="text" id="honorNo" name="honorNo"/>
                </td>
            </tr>

            <tr>
                <td align="right" width="50%">发证单位</td>
                <td align="left" width="50%">
                    <input type="text" id="certIssuer" name="certIssuer"/>
                </td>
            </tr>

            <tr>
                <td align="right" width="50%">发证日期</td>
                <td align="left" width="50%">
                    <input type="text" name="certDate" id="certDate" readonly="readonly"/>
                </td>
            </tr>

            <tr>
                <td align="right" width="50%">品牌荣誉证书</td>
                <td align="left" width="50%">
                    <input type="file" name="file" id="honorCert"/>
                </td>
            </tr>

            <tr>
                <td align="center" width="50%">
                    <msk2:button buttonType="button" buttonId="SL241103007.SAVE" buttonValue="保存"/>
                    <%--<msk:button buttonValue="保存" buttonId="SL241103007.SAVE" buttonType="button"/>--%>
                </td>
                <td align="center" width="50%">
                    <msk2:button buttonType="button" buttonId="SL241103007.ADD" buttonValue="添加"/>
                    <%--<msk:button buttonValue="添加" buttonId="SL241103007.ADD" buttonType="button"/>--%>
                </td>
            </tr>
        </table>
    </form:form>
</div>


<div id="proxyBrand" style="display:none">

    <form:form action="${ctx}/SL241103007/insert" id="SL241103007Form"
               metdod="post" enctype="multipart/form-data">
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td align="right" width="50%">生产商列表</td>
                <td align="left" width="50%">
                    <input type="text" id="xx"/>
                </td>
            </tr>

            <tr>
                <td align="right" width="50%">品牌列表</td>
                <td align="left" width="50%">
                    <input type="text" id="oo"/>
                </td>
            </tr>


            <tr>
                <td align="center" width="50%">
                    <msk2:button buttonType="button" buttonId="SL241103007.SAVE" buttonValue="保存"/>
                    <%--<msk:button buttonValue="保存" buttonId="SL241103007.SAVE" buttonType="button"/>--%>
                </td>
                <td align="center" width="50%">
                    <msk2:button buttonType="button" buttonId="SL241103007.ADD" buttonValue="添加"/>
                    <%--<msk:button buttonValue="添加" buttonId="SL241103007.ADD" buttonType="button"/>--%>
                </td>
            </tr>
        </table>
    </form:form>
</div>
    </div>
</div>
<script type="text/javascript">
    function myBrand(){
        document.getElementById("myBrand").style.display = "block";
        document.getElementById("proxyBrand").style.display = "none";
    }
    function proxyBrand(){
        document.getElementById("myBrand").style.display = "none";
        document.getElementById("proxyBrand").style.display = "block";
    }
</script>
<script src="${ctx}/static/sl/js/SL2411030072.js"></script>
