<?xml version="1.0" encoding="utf-8"?>
<DATACOLLECTION>
    <DATA>
        <#list entity as field>
            <ORDERID>${field.orderId!''}</ORDERID>
            <ORDERCODE>${field.orderCode!''}</ORDERCODE>
            <ORDERTYPE>${field.orderType!''}</ORDERTYPE>
            <TARGETCOMPANY>${field.buyersCode!''}</TARGETCOMPANY>
            <COMPANYTYPE>${field.buyersType!''}</COMPANYTYPE>
            <COMPANYNAME>${field.buyersName!''}</COMPANYNAME>
            <REQUESTEDDATE>${field.strForecastReceiveTime!''}</REQUESTEDDATE>
            <SCHEDULEDDATE>${field.strForecastSendTime!''}</SCHEDULEDDATE>
            <VER>${field.ver!''}</VER>
            <PAYMENTTYPE>${field.paymentType!''}</PAYMENTTYPE>
            <RECEIVER>
                <NAME>${field.receiverName!''}</NAME>
                <TEL>${field.receiverTel!''}</TEL>
                <WX>${field.receiverWeixin!''}</WX>
                <QQ>${field.receiverQq!''}</QQ>
                <EMAIL>${field.receiverMail!''}	</EMAIL>
                <PROVINCE>${field.receiverProvince!''}	</PROVINCE>
                <CITY>${field.receiverCity!''}</CITY>
                <DISTRICT>${field.receiverDistrict!''}</DISTRICT>
                <ADDR>${field.receiverAddr!''}</ADDR>
                <DELIVERY_TYPE>${field.receiverDeType!''}</DELIVERY_TYPE>
            </RECEIVER>
            <LINES>
            <#if (field.productDetailList?size>0)>
                <#list field.productDetailList as detail>
                    <LINE>
                        <ORDERLINE>${detail.orderLine!''}</ORDERLINE>
                        <SKU>${detail.sku!''}</SKU>
                        <UOM>${detail.unit!''}</UOM>
                        <CONSIGNEE>${field.sellerCode!''}</CONSIGNEE>
                        <AREA>${field.districtCode!''}</AREA>
                        <COMPANY>${detail.supplierCode!''}</COMPANY>
                        <QTYORIGINAL>${detail.suppQty!''}</QTYORIGINAL>
                        <QTYMODIFIED>${detail.sendQty!''}</QTYMODIFIED>
                        <INPUTQTY>${detail.pdPrice!''}</INPUTQTY>
                        <ATTRIBUTES>${detail.orderSource!''}</ATTRIBUTES>
                        <SUPPLIERCODE>${detail.supplierCode!''}</SUPPLIERCODE>
                        <PDCODE>${detail.pdCode!''}</PDCODE>
                        <ORDERQTY>${detail.orderQty!''}</ORDERQTY>
                    </LINE>
                </#list>
            </#if>
            </LINES>
        </#list>
    </DATA>
</DATACOLLECTION>
