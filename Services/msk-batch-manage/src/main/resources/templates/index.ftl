<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Simple Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="layout" content="main"/>
    <script src="static/js/jquery/jquery-1.8.2.min.js" type="text/javascript" ></script>
    <link href="static/css/customize-template.css" type="text/css" media="screen, projection" rel="stylesheet" />
    <script src="static/js/bootstrap/bootstrap-transition.js" type="text/javascript" ></script>
    <script src="static/js/bootstrap/bootstrap-alert.js" type="text/javascript" ></script>
    <script src="static/js/bootstrap/bootstrap-modal.js" type="text/javascript" ></script>
    <script src="static/js/bootstrap/bootstrap-dropdown.js" type="text/javascript" ></script>
    <script src="static/js/bootstrap/bootstrap-scrollspy.js" type="text/javascript" ></script>
    <script src="static/js/bootstrap/bootstrap-tab.js" type="text/javascript" ></script>
    <script src="static/js/bootstrap/bootstrap-tooltip.js" type="text/javascript" ></script>
    <script src="static/js/bootstrap/bootstrap-popover.js" type="text/javascript" ></script>
    <script src="static/js/bootstrap/bootstrap-button.js" type="text/javascript" ></script>
    <script src="static/js/bootstrap/bootstrap-collapse.js" type="text/javascript" ></script>
    <script src="static/js/bootstrap/bootstrap-carousel.js" type="text/javascript" ></script>
    <script src="static/js/bootstrap/bootstrap-typeahead.js" type="text/javascript" ></script>
    <script src="static/js/bootstrap/bootstrap-affix.js" type="text/javascript" ></script>
    <script src="static/js/bootstrap/bootstrap-datepicker.js" type="text/javascript" ></script>
    <script src="static/js/jquery/jquery-tablesorter.js" type="text/javascript" ></script>
    <script src="static/js/jquery/jquery-chosen.js" type="text/javascript" ></script>
    <script src="static/js/jquery/virtual-tour.js" type="text/javascript" ></script>
    <script src="static/js/comm/core.js" type="text/javascript" ></script>

</head>
<body>
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <button class="btn btn-navbar" data-toggle="collapse" data-target="#app-nav-top-bar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="#" class="brand"><i class="icon-leaf">Batch Manager管理</i></a>
        </div>
    </div>
</div>

<div id="body-container">
    <div id="body-content">

        <div class="body-nav body-nav-horizontal body-nav-fixed">
            <div class="container">
                <ul>
                    <li>
                        <a href="#">
                            <i class="icon-dashboard icon-large"></i>Manager
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <i class="icon-calendar icon-large"></i>Executing
                        </a>
                    </li>
                    <#--<li>-->
                        <#--<a href="#">-->
                            <#--<i class="icon-map-marker icon-large"></i> Map It-->
                        <#--</a>-->
                    <#--</li>-->
                    <#--<li>-->
                        <#--<a href="#">-->
                            <#--<i class="icon-tasks icon-large"></i> Widgets-->
                        <#--</a>-->
                    <#--</li>-->
                    <li>
                        <a href="#">
                            <i class="icon-cogs icon-large"></i>Record
                        </a>
                    </li>
                    <#--<li>-->
                        <#--<a href="#">-->
                            <#--<i class="icon-list-alt icon-large"></i> Forms-->
                        <#--</a>-->
                    <#--</li>-->
                    <#--<li>-->
                        <#--<a href="#">-->
                            <#--<i class="icon-bar-chart icon-large"></i> Charts-->
                        <#--</a>-->
                    <#--</li>-->
                </ul>
            </div>
        </div>
        <section class="page container">
            <div class="row">
                <div class="span4">
                    <div class="blockoff-right">
                        <ul id="person-list" class="nav nav-list">
                            <li class="nav-header">菜单</li>
                            <li class="active">
                                <a href="#Person-1">
                                    <i class="icon-chevron-right pull-right"></i>
                                    <b>Batch执行中列表</b>
                                </a>
                            </li>

                            <li>
                                <a target="ajax" href="batch/def/list" ref="mainContext">
                                    <i class="icon-chevron-right pull-right"></i>
                                    Batch列表
                                </a>
                            </li>
                            <li>
                                <a href="#Person-5">
                                    <i class="icon-chevron-right pull-right"></i>
                                    Batch执行完了履历
                                </a>
                            </li>
                            <li>
                                <a href="#Person-5">
                                    <i class="icon-chevron-right pull-right"></i>
                                    新增Batch
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="span12" id="mainContext">

                    <div id="Person-1" class="box">
                        <div class="box-header">
                            <i class="icon-list-alt icon-large"></i>
                            <h5>Batch执行中列表</h5>

                        </div>
                        <div class="box-content box-table">
                            <table class="table table-hover tablesorter">
                                <thead>
                                <tr>
                                    <th>Batch编码</th>
                                    <th>Batch参数</th>
                                    <th>状态</th>
                                    <th>执行时间</th>
                                    <th>执行人</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#if pageResult ??>
                                  <#if pageResult.data ??>
                                    <#list pageResult.data as batchRecord>
                                    <tr>
                                        <td>${batchRecord.batchCode!}</td>
                                        <td>${batchRecord.parameter!}</td>
                                        <td>
                                            <#if batchRecord.status?? && batchRecord.status == 1>
                                                待执行
                                            </#if>
                                            <#if batchRecord.status?? && batchRecord.status == 2>
                                                执行中
                                            </#if>
                                        </td>
                                        <td>${batchRecord.crtTime!}</td>
                                        <td>${batchRecord.crtId!}</td>
                                    </tr>
                                    </#list>
                                  </#if>
                                </#if>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</div>
<div id="spinner" class="spinner" style="display:none;">
    Loading&hellip;
</div>
<footer class="application-footer">
    <div class="container">
        <div class="disclaimer">
            <p>Copyright © 2016 Xian Chi Da Group. All Rights Reserved.</p>
            <p>网站备案/许可证号：沪ICP备14048034号-1</p>
        </div>
    </div>
</footer>
<script type="text/javascript">
//    $(function() {
//        $('#person-list.nav > li > a').click(function(e){
//            if($(this).attr('id') == "view-all"){
//                $('div[id*="Person-"]').fadeIn('fast');
//            }else{
//                var aRef = $(this);
//                var tablesToHide = $('div[id*="Person-"]:visible').length > 1
//                        ? $('div[id*="Person-"]:visible') : $($('#person-list > li[class="active"] > a').attr('href'));
//
//                tablesToHide.hide();
//                $(aRef.attr('href')).fadeIn('fast');
//            }
//            $('#person-list > li[class="active"]').removeClass('active');
//            $(this).parent().addClass('active');
//            e.preventDefault();
//        });
//
//        $(function(){
//            $('table').tablesorter();
//            $("[rel=tooltip]").tooltip();
//        });
//    });
</script>
</body>
</html>