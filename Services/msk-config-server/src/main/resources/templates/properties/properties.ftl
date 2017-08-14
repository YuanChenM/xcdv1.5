    <div class="divLeft" style="padding-top: 15px;">
        <ul id="treeDemo" class="ztree range divLeft" style="width: 400px;"></ul>
        <div class="divLeft">
            <div style="padding-top: 10px; padding-left: 10px;">
                <input id="expand_js" type="button" value="全展开树" class="divLeft black_button"/>
                <input id="save_js" type="button" value="保存节点" class="divLeft black_button"/>
                <input id="delete_js" type="button" value="删除节点" class="divLeft black_button"/>
            </div>
            <div style="padding-left: 10px;" class="divLeft">
                <div>
                    <div class="pad" id="properties_div" hidden="true">
                        <div><b>节点配置</b></div>
                        <ul>
                            <li style="list-style-type:none;padding-top: 10px;"><label for="node_key"
                                                                                       style="padding-right: 40px;">key:</label>
                                <input id="node_key" type="text" style="width: 350px;" onchange="keyChange(this)"/>
                            </li>
                            <li style="list-style-type:none;padding-top: 10px;"><label for="node_value"
                                                                                       style="padding-right: 32px;">value:</label><input
                                    id="node_value" type="text" style="width: 350px;" onchange="valueChange(this)"/>
                            </li>
                            <li style="list-style-type:none;padding-top: 10px;"><label for="node_comment"
                                                                                       style="padding-right: 8px;">comment:</label><input
                                    id="node_comment" type="text" style="width: 350px;"/></li>
                            </br>
                        </ul>
                    </div>
                    <div class="pad" id="map_div" hidden="true">
                        <div><b>节点配置</b></div>
                        <li style="list-style-type:none;padding-top: 10px;"><label for="hash_key"
                                                                                   style="padding-right: 40px;">key:</label>
                            <input id="hash_key" type="text" style="width: 350px;" onchange="keyChange(this)"/></li>
                        <div style="padding-left: 25px;display: inline;">
                            <input id="add_hp" type="button" value="增加键值对" class="divLeft img"/>
                            <input id="save_hp" type="button" value="应用" class="divLeft img"/>
                        </div>
                        <ul id="map_ul" style="display: inline;">
                            <li style="list-style-type:none;padding-top: 10px;display: inline;">
                                <label style="padding-right: 60px;width: 350px;display: inline;">key</label>
                                <label style="padding-right: 60px;width: 350px;display: inline;">value</label>
                            </li>

                        </ul>
                    </div>
                    <input type="hidden" id="node_tId"/>

                    <div class="pad" id="string_div" hidden="true">
                        <div><b>节点配置</b></div>
                        <ul>
                            <li style="list-style-type:none;padding-top: 10px;"><label for="string_key"
                                                                                       style="padding-right: 40px;">key:</label>
                                <input id="string_key" type="text" style="width: 350px;"
                                       onchange="keyChange(this)"/></li>
                            <li style="list-style-type:none;padding-top: 10px;"><label for="string_value"
                                                                                       style="padding-right: 32px;">value:</label><input
                                    id="string_value" type="text" style="width: 350px;"
                                    onchange="valueChange(this)"/></li>
                        </ul>
                    </div>
                </div>
                <div style="padding-left: 10px;">
                    <div><b>上传初始化数据</b></div>
                <#include "../uploadFile.ftl"/>
                </div>
                <div id="tree_msg" class="msg_box">
                    <ul>
                    </ul>
                </div>
            </div>
        </div>
    <#--<iframe src="uploadFile.ftl" width="800px" height="200px"></iframe>-->
    </div>
<script type="text/javascript">
    // msgBox效果
    $.msgbox = {
        ok: function (msg, id) {
            if (id == undefined || id == null) {
                id = "";
            } else {
                id = "#" + id;
            }
            $(id + ".msg_box").removeClass("error_msg").addClass("ok_msg");
            $(id + ".msg_box ul").html(this.createHtml(msg));
            $(id + ".msg_box").show();
        },
        ng: function (msg, id) {
            if (id == undefined || id == null) {
                id = "";
            } else {
                id = "#" + id;
            }
            $(id + ".msg_box").removeClass("ok_msg").addClass("error_msg");
            $(id + ".msg_box ul").html(this.createHtml(msg));
            $(id + ".msg_box").show();
        },
        createHtml: function (msg) {
            var msgHtml = "";
            if (typeof msg == 'string') {
                msgHtml = "<li>" + msg + "</li>";
            } else {
                for (i = 0; i < msg.length; i++) {
                    msgHtml += "<li>" + msg[i] + "</li>";
                }
            }
            return msgHtml;
        }
    };
    var setting = {
        view: {
            selectedMulti: false
        },

        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            beforeDrag: beforeDrag,
            onClick: setNodeVal
        }
    };
    function beforeDrag(treeId, treeNodes) {
        return false;
    }

    function setNodeVal(event, treeId, node) {
        $("#node_tId").val(node.tId);
        if (node.type == "properties") {
            $("#string_div").hide();
            $("#map_div").hide();
            $("#properties_div").show();
            $("#node_key").val(node.name);
            $("#node_value").val(node.value);
            $("#node_comment").val(node.comment);
        }
        else if (node.type == "hash") {
            $("#properties_div").hide();
            $("#string_div").hide();
            $("#map_div").show();
            $("#hash_key").val(node.name);
            var map = node.map;
            var mapUl = $("#map_ul");
            mapUl.children("li[name='li_p']").remove();
            if (map != null && !$.isEmptyObject(map)) {
                for (var p in map) {
                    var content = "<li name='li_p' style='list-style-type:none;padding-top: 10px;'><input name='map_key' type='text' style='width: 350px;' value='"
                            + p
                            + "'/><input name='map_value' type='text' style='width: 350px;' value='"
                            + map[p]
                            + "'/></li>";
                    $(content).appendTo(mapUl);
                }
            }
        }
        else if (node.type == "string") {
            $("#properties_div").hide();
            $("#map_div").hide();
            $("#string_div").show();
            $("#string_key").val(node.name);
            $("#string_value").val(node.value);
        }
    }
    //var ztree = null;
    var treeLoad = function (type) {
        // 树显示
        var obj = new Object();
        obj['type'] = type;
        $.ajax({
            url: "getTree",
            contentType: 'application/json',
            type: "post",
            dataType: "json",
            data: JSON.stringify({"siteCode": "111", "auth": "111", "loginId": "111", "param": obj}),
            success: function (data) {
                var zTree = $.fn.zTree.init(
                        $("#treeDemo"), setting,
                        JSON.parse(data.result)
                );
                var nodes = zTree.getNodes();
                if (nodes.length > 0) {
                    zTree.selectNode(nodes[0]);
                    setNodeVal(null, null, nodes[0]);
                    zTree.expandAll(false);
                }
            },
            error: function () {
                $.msgbox.ng('error!');
            }
        });


    };

    var newCount = 1;
    function addHoverDom(treeId, treeNode) {
        var sObj = $("#" + treeNode.tId + "_span");

        if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0 || !treeNode.isParent) return;
        var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
                + "' title='add node' onfocus='this.blur();'></span>";
        sObj.after(addStr);
        var btn = $("#addBtn_" + treeNode.tId);
        if (btn) btn.bind("click", function () {
            zTree.addNodes(treeNode, {id: (100 + newCount), pId: treeNode.id, name: "new node" + (newCount++)});
            return false;
        });
    }
    ;
    function removeHoverDom(treeId, treeNode) {
        $("#addBtn_" + treeNode.tId).unbind().remove();
    }
    ;
    var checkName = function (value, selects) {
        for (var i = 0; i < selects.length; i++) {
            if (value == selects[i].name) {
                $.msgbox.ng("同层级节点名不能重复！");
                return false;
            }
        }
        return true;
    }

    //properties begin
    $("#node_comment").change(function (event) {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        var node = zTree.getNodeByTId($("#node_tId").val());
        node.comment = this.value;
        node.unsave = true;
        zTree.updateNode(node);
    });
    //properties end

    //common begin
    var keyChange = function (obj) {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        var node = zTree.getNodeByTId($("#node_tId").val());
        if (this.value == '') {
            $.msgbox.ng("节点名不能为空！");
            return;
        }
        var selects = zTree.getNodesByParam("pId", node.pId);
        if (!checkName(obj.value, selects)) {
            return;
        }
        node.name = obj.value;
        node.unsave = true;
        zTree.updateNode(node);
    };
    var valueChange = function (obj) {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        var node = zTree.getNodeByTId($("#node_tId").val());
        node.value = obj.value;
        node.unsave = true;
        zTree.updateNode(node);
    };
    var padLeft = function (value, length, pad) {

        var tmpStr = value;
        if (tmpStr == null) {
            tmpStr = "";
        }

        var tmpPad = "";

        if (tmpStr.length < length) {
            for (var i = 1; i <= length - tmpStr.length; i++) {
                tmpPad = pad.concat(tmpPad);
            }
        }
        return tmpPad.concat(tmpStr);
    }
    var createNodes = function (pNode, type) {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        var nodeCode = '';
        if (pNode == null) {
            $.msgbox.ng("请选择节点！");
            return;
        } else {
            nodeCode = "";
        }
        var selects = zTree.getNodesByParam("pId", pNode.id);
        if (type == "properties") {
            var selectSize;
            var name = '';
            if (selects.length == 0) {
                name = "default";
                selectSize = pNode.id + "0001";
            } else {
                name = "新节点";
                var maxId = selects[selects.length - 1].id;
                if(maxId == null){
                    $.msgbox.ng("不能在新增此种节点！");
                    return;
                }
                if (Number(maxId.substring(maxId.length - 4, maxId.length)) + 1 > 9999) {
                    $.msgbox.ng("同层级最多不能超过9999！");
                    return;
                }
                selectSize = maxId.substring(0, maxId.length - 4) + padLeft((Number(maxId.substring(maxId.length - 4, maxId.length)) + 1) + "", 4, "0");
            }
            var map = {};
            var newNode = zTree.addNodes(pNode, {
                id: selectSize,
                pId: pNode.id,
                name: name,
                value: nodeCode,
                unsave: true,
                comment: '',
                type: type,
                map: map
            });
            //zTree.selectNode(newNode[0]);
            setNodeVal(null, null, newNode[0]);
        }
        else if (type == "hash" || type == "string") {
            var name = "新节点";
            var map = {};
            var newNode = zTree.addNodes(pNode, {
                id: "",
                pId: pNode.id,
                name: name,
                value: "",
                unsave: true,
                comment: '',
                type: type,
                map: map
            });
            //zTree.selectNode(newNode[0]);
            setNodeVal(null, null, newNode[0]);
        }
    };

    var createRootNodes = function () {
        var map = {};
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        var selects = zTree.getNodesByParam("pId", null);
        var selectSize;
        var name = '';
        if (selects.length == 0) {
            name = 'default';
            selectSize = 1000;
        }
        else {
            name = '新节点';
            selectSize = Number(selects[selects.length - 1].id) + 1;
        }
        var newNode = zTree.addNodes(null, {
            id: selectSize,
            pId: '',
            name: name,
            value: '',
            unsave: true,
            comment: '',
            map: map,
            type: 'properties'
        });
        zTree.selectNode(newNode[0]);
        setNodeVal(null, null, newNode[0]);
    }
    var addPnodeFunc = function () {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        var node = zTree.getNodeByTId($("#node_tId").val());

        //var level = node.level;
        //if(level >= 2) {
        //  $.msgbox.ng("不能再增加子节点！");
        //  return;
        //}
        if (node.type != "properties") {
            $.msgbox.ng("不能再增加子节点！");
            return;
        }
        createNodes(zTree.getSelectedNodes()[0], "properties");
    }

    var addHnodeFunc = function () {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        var node = zTree.getNodeByTId($("#node_tId").val());

        //var level = node.level;
        //if(level >= 2) {
        //  $.msgbox.ng("不能再增加子节点！");
        //  return;
        //}
        if (node.type != "properties") {
            $.msgbox.ng("不能再增加子节点！");
            return;
        }
        createNodes(zTree.getSelectedNodes()[0], "hash");
    }
    var addSnodeFunc = function () {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        var node = zTree.getNodeByTId($("#node_tId").val());

        //var level = node.level;
        //if(level >= 2) {
        //  $.msgbox.ng("不能再增加子节点！");
        //  return;
        //}
        if (node.type != "properties") {
            $.msgbox.ng("不能再增加子节点！");
            return;
        }
        createNodes(zTree.getSelectedNodes()[0], "string");
    }

    $("#expand_js").click(function () {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        zTree.expandAll(true);
    });

    var addRootNodeFunc = function () {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        createRootNodes();
    }
    var getSaveData = function () {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        // 树属性
        // 节点属性
        var data = new Array();
        var nodes = zTree.getNodesByParam("unsave", true);
        if (nodes.length > 0) {
            for (var i = 0; i < nodes.length; i++) {
                data.push({
                    id: nodes[i].id,
                    name: nodes[i].name,
                    pId: nodes[i].pId,
                    value: nodes[i].value,
                    comment: nodes[i].comment,
                    map: nodes[i].map,
                    type: nodes[i].type
                });
            }
        }
        return data;
    };
    $("#save_js").click(function () {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        var nodes = getSaveData();
        for (var i = 0; i < nodes.length; i++) {
            var newNodes = zTree.getNodesByParam("pId", nodes[i].pId);
            for (var j = 0; j < newNodes.length; j++) {
                if (nodes[i].name == newNodes[j].name && nodes[i].id != newNodes[j].id) {
                    $.msgbox.ng("同层级节点名不能重复！");
                    return;
                }
            }
        }
        $.ajax({
            url: "setTreeNodes",
            contentType: 'application/json',
            type: "post",
            dataType: "json",
            data: JSON.stringify({"siteCode": "111", "auth": "111", "loginId": "111", "param": nodes}),
            success: function (data) {
                if (data.status == 'S') {
                    zTree.destroy();
                    treeLoad();
                    $.msgbox.ok("保存成功!");
                } else {
                    $.msgbox.ng(data.message);
                }
            }
        });
    });
    $("#delete_js").click(function () {
        if (!confirm("是否继续")) {
            return;
        }
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        var dataNodes = new Array();
        var node = zTree.getNodeByTId($("#node_tId").val());
        var unsaves = zTree.getNodesByParam("unsave", true);
        if (node == null) {
            $.msgbox.ng("请选择节点！");
        }
        var data = zTree.transformToArray(node);
        if (data.length > 0) {
            for (var i = 0; i < data.length; i++) {
                dataNodes.push({
                    id: data[i].id,
                    name: data[i].name,
                    pId: data[i].pId,
                    value: data[i].value,
                    comment: data[i].comment,
                    type: data[i].type,
                    map: data[i].map
                });
            }
        }

        $.ajax({
            url: "delTreeNodes",
            contentType: 'application/json',
            dataType: "json",
            data: JSON.stringify({"siteCode": "111", "auth": "111", "loginId": "111", "param": dataNodes}),
            type: "post",
            success: function (data) {
                if (data.status == 'S') {
                    zTree.removeNode(node);
                    zTree.removeChildNodes(node);
                    $.msgbox.ok("删除成功！");
                } else {
                    $.msgbox.ng("删除失败！");
                }
            }
        });
    });
    //common end

    //hash begin
    $("#add_hp").click(function () {
        debugger;
        var mapUl = $("#map_ul");
        var content = "<li name='li_p' style='list-style-type:none;padding-top: 10px;'><input name='map_key' type='text' style='width: 350px;'"
                + "/><input name='map_value' type='text' style='width: 350px;'"
                + "/></li>";
        $(content).appendTo(mapUl);
    });
    $("#save_hp").click(function () {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        var node = zTree.getNodeByTId($("#node_tId").val());

        var mapKeys = $("input[name='map_key']");
        var mapValues = $("input[name='map_value']");
        var map = {};
        for (var i = 0; i < mapKeys.length; i++) {

            if (mapKeys[i].value == '' || mapKeys[i].value == null) {
                $.msgbox.ng("key不能为空！");
                return;
            }
            if (mapValues[i].value == '' || mapValues[i].value == null) {
                $.msgbox.ng("value不能为空！");
                return;
            }
            map[mapKeys[i].value] = mapValues[i].value;
        }
        node.map = map;
        node.unsave = true;
        zTree.updateNode(node);
        $.msgbox.ok("应用成功!");
    });
    //hash end
    //string begin
    //$("#string_value").change(valueChange());
    //string end
</script>