<?xml version="1.0" encoding="utf-8"?>
<DATACOLLECTION>
    <DATA>
    <#list entity as field>
        <RETURNID>${field.returnId!''}</RETURNID>
        <RETURNCODE>${field.returnCode!''}</RETURNCODE>
        <ORDERID>${field.orderId!''}</ORDERID>
        <TARGETCOMPANY>${field.buyersCode!''}</TARGETCOMPANY>
        <COMPANYTYPE>${field.buyersType!''}</COMPANYTYPE>
        <BUYERSNAME>${field.buyersName!''}</BUYERSNAME>
        <BUYERSID>${field.buyersId!''}</BUYERSID>
        <VER>${field.ver!''}</VER>
        <CONSIGNEE>${field.sellerCode!''}</CONSIGNEE>
        <REQUESTEDDATE>${field.strForecastReceiveTime!''}</REQUESTEDDATE>
        <LINES>
            <#if (field.returnDetailList?size>0)>
                <#list field.returnDetailList as detail>
                    <LINE>
                        <RETURNLINE>${detail.returnLine!''}</RETURNLINE>
                        <ORDERLINE>${detail.orderLine!''}</ORDERLINE>
                        <SKU>${detail.pdCode!''}</SKU>
                        <UOM>${detail.unit!''}</UOM>
                        <QTYORIGINAL>${detail.orderQty!''}</QTYORIGINAL>
                        <QTYRETUREN>${detail.returnQty!''}</QTYRETUREN>
                        <INPUTQTY>${detail.pdPrice!''}</INPUTQTY>
                        <ATTRIBUTES></ATTRIBUTES>
                    </LINE>
                </#list>
            </#if>
        </LINES>
    </#list>
    </DATA>
</DATACOLLECTION>