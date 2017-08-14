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
            <a href="#" class="brand"><i class="icon-leaf">mq消息管理</i></a>
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
                            <li class="nav-header">队列列表</li>
                            <#if pageResult ??>
                                 <#if pageResult.data ??>
                                   <#list pageResult.data as queueRecord>
                                      <li >
                                         <a target="ajax" href="detail?queueName=${queueRecord.queueName!}" ref="mainContext"id="${queueRecord.queueName!}_detail">
                                           <i class="icon-chevron-right pull-right"></i>
                                             <b>${queueRecord.queueName!}</b>
                                         </a>
                                       </li>
                                   </#list>
                                 </#if>
                            </#if>
                        </ul>
                    </div>
                </div>
                <div class="span12" id="mainContext">

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

</body>
</html>