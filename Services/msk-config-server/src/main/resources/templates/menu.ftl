<div class="page-sidebar nav-collapse collapse">
    <ul class="page-sidebar-menu">
        <li>
            <div class="sidebar-toggler hidden-phone"></div>
        </li>
        <li>
            <form class="sidebar-search">
                <div class="input-box">
                    <a href="javascript:;" class="remove"></a>
                    <input type="text" placeholder="Search..." />
                    <input type="button" class="submit" value=" " />
                </div>
            </form>
        </li>
        <li class="start active ">
            <a href="index.html">
                <i class="icon-home"></i>
                <span class="title">Properties</span>
                <span class="selected"></span>
            </a>
            <ul class="sub-menu">
                <li >
                    <a id="add_pnode_js" href="#" onclick="addPnodeFunc()">
                        添加</a>
                </li>
                <li>
                    <a id="add_root_node_js" href="#" onclick="addRootNodeFunc();">
                        添加根节点</a>
                </li>
                <li >
                    <a href="load/properties" target="ajax"  func='treeLoad("properpties")'>查看</a>
                </li>
            </ul>
        </li>
        <li class="">
            <a href="javascript:;">
                <i class="icon-cogs"></i>
                <span class="title">CodeMaster</span>
                <span class="arrow "></span>
            </a>
            <ul class="sub-menu">
                <li >
                    <a href="sync/code/master" target="ajax">
                        同步</a>
                </li>
                <li >
                    <a href="view/code/master" target="ajax" func='TableEditable.init()'>查看</a>
                </li>
            </ul>
        </li>
        <li class="">
            <a href="javascript:;">
                <i class="icon-bookmark-empty"></i>
                <span class="title">Config Constant</span>
                <span class="arrow "></span>
            </a>
            <ul class="sub-menu">
                <li>
                    <a id="add_snode_js" href="#" onclick="addSnodeFunc();">
                        添加</a>
                </li>
                <li>
                    <a href="load/properties" target="ajax"  func='treeLoad("properpties")'>查看</a>
                </li>
            </ul>
        </li>
    </ul>
</div>