/**
 * Main画面のJS
 *
 * @author ma_b
 */

var Main = {
		
	outerLayout: null,
	innerLayout: null,

	// contextPath
    contextPath : "",
    // 语言(默认简体中文，目前用于日历控件的国际化)
    language : "zh_CN",
	//子窗口编号
	count : 1,
	resizePaneAccordions: $.layout.callbacks.resizePaneAccordions,
	resizeTabLayout: $.layout.callbacks.resizeTabLayout,

	// Main画面初期化
	init : function() {
		// JS FW 初期化
		HDF.init();

		// main layout 初期化設定
	    Main.outerLayout = $('body').layout({
			applyDefaultStyles:     false
	    ,   center__paneSelector:   ".main-container"
	    ,   north__paneSelector:    ".main-header"
	    ,   west__paneSelector:     ".main-menu"
	    ,   south__paneSelector:    ".main-copyright"
	    ,   west__size:             200
	    ,   north__size:            118
	    ,   south__size:            22
	    ,   spacing_open:           4 // ALL panes
	    ,   spacing_closed:         4 // ALL panes
	    ,   north__spacing_open:    0
	    ,   south__spacing_open:    0
		,   west__togglerLength_open:    40
		,   west__togglerLength_closed:  40
		,   north__resizable:       false
		,   north__slidable:        false
	    ,   center__onresize:       "Main.innerLayout.resizeAll"
	    ,	west__onresize:		    Main.resizeTabLayout // west accordion a child of pane
	    }); 

	    Main.innerLayout = $('div.main-container').layout({
	        center__paneSelector:   ".main-content"
	    ,   south__paneSelector:    ".main-message"
	    ,   south__size:            35
	    ,   spacing_open:           2  // ALL panes
	    ,   spacing_closed:         8  // ALL panes
	    ,   center__onresize_end:   "Main.resizeMainContent"
	    });
	    
	    /*
	    Main.outerLayout.panes.west.tabs({
			activate:			Main.resizePaneAccordions 
		});
		*/

		$( ".favourite_content .sub_menu" ).menu();
	    
		// get menu data
		Main.createMenu();
		
		//修改密码事件
		$("#head_passwordchange").click(function(){
			var url = Main.contextPath + "/comm/password";
			$.pdialog.open("修改密码", url, {resizable:false, width: 500, height: 290}, "");
		});
		
		//用户配置维护
		$("#head_modifyProfile").click(function(){
			var url = Main.contextPath + "/sy01s03/init";
			var messageTitle = $(this).children("input").val();
			//$('#main-content').postUrl(url);
			$.pdialog.open(messageTitle, url, {resizable:false, width: 400, height: 300}, "");
		});
		
		// logout
		$("#head_logout").click(function(){
			var url = Main.contextPath + "/logout";
			window.location = url;
		});

		// switch
		$("#head_switch").click(function(){
			var url = Main.contextPath + "/switch/system?continueFlag=true";
			window.location = url;
		});

		// 收藏
		$("#favorites").click(function(){

			var pageUrl = $(".ui-menu-item[selected=selected]").attr("href");
			var pageName = $.trim($(".ui-menu-item[selected=selected]").text());
			if(pageUrl == undefined){
				$.alertMessage.info("请选择想要收藏的页面！");
				return;
			}
			$("#main-content").postUrl(Main.contextPath +"/favorites/save",
				{pageUrl:pageUrl,pageName:pageName,sysCode:$("#sysCode").val()},
				function(result){
					if(result=="HAS") {
						$.alertMessage.info("该页面已被收藏！");
						return;
					} else {
						if($.trim($(".toolbar").text()) == '点击收藏夹，添加你想收藏的一览画面！'){
							$(".toolbar").empty();
						}
						$(".toolbar").append('<li style="color: skyblue;" class="last"><a target="ajax" rel="main-content" href="'
						+ pageUrl
						+ '"><span style="background: url( '+ Main.contextPath + '/static/images/action/lost.png) no-repeat center;"></span>'
						+ pageName + '</a><span><img style="margin-left: 3px;margin-top: -2px;" src="'+ Main.contextPath + '/static/images/action/close.png" alt="删除"></span></li>');

						// 收藏链接点击
						$("a[target=ajax]").each(function() {
							$(this).click(function(event) {
								var $this = $(this);
								var rel = $this.attr("rel");
								var paramStr = $this.attr("param");
								var param = {};
								if(paramStr != undefined){
									param=eval("("+paramStr+")");//转换为json对象
								}

								if (rel) {
									var $rel = $("#" + rel);

									$rel.postUrl($this.attr("href"), param);
								}

								event.preventDefault();
							});
						});
						// 收藏删除
						$(".toolbar img").click(function(){

							var that = $(this).parent().parent();
							var pageUrl = that.find("a").attr("href");
							$("#main-content").postUrl(Main.contextPath +"/favorites/delete",
								{pageUrl:pageUrl,sysCode:$("#sysCode").val()},
								function(result){
									that.remove();
									$.alertMessage.info("删除成功！");
								},{refreshHtml: false});
						});
					}
				},{refreshHtml: false});
		});

		// 收藏删除
		$(".toolbar img").click(function(){

			var that = $(this).parent().parent();
			var pageUrl = that.find("a").attr("href");
			$("#main-content").postUrl(Main.contextPath +"/favorites/delete",
				{pageUrl:pageUrl,sysCode:$("#sysCode").val()},
				function(result){
					that.remove();
					$.alertMessage.info("删除成功！");
				},{refreshHtml: false});
		});
		
		// 下拉菜单的处理
		$(".icon-select ul").hide().menu();
		$(".icon-select div").click(function() {
			$(".icon-select ul").hide();
			Main.outerLayout.resetOverflow(this);
			Main.outerLayout.allowOverflow('north');
	        var menu = $( this ).next().show().position({
			    my: "right top",
				at: "right bottom",
				of: this
			});
			$( document ).one( "click", function() {
				Main.outerLayout.resetOverflow(this);
				menu.hide();
			});
			return false;
		});

		// theme 選択項目初期化
		// addThemeSwitcher( 'body > .ui-layout-north > #header', { top: '10px', right: '6px' });
		
		// 显示初始内容：系统支持信息
		//$("#main-content").postUrl(Main.contextPath +"/main/message");
	},

	// 创建菜单
	createMenu : function() {
		Main.initMenu();
	},

	// 初始化菜单
	initMenu : function() {

		$( ".menu_content[active]" ).accordion({
			active: 0
		,   collapsible: true
		,   heightStyle: "content"
		});
		$( ".menu_content:not([active])" ).accordion({
			active: false
		,   collapsible: true
		,   heightStyle: "content"
		});
		
		$( ".menu_content .sub_menu" ).menu();
		
		$( ".menu_content .sub_menu li[href][rel]" ).click(function(){
			$that = $(this);
			var $pageId = $that.attr("pageId");

			var url = $that.attr("href");
			if(url != undefined && url != "" && url != "#"){
				// 清除检索条件的cookie
				HGRID.hfilter.clear();

				$(".menu_content .sub_menu .ui-menu-item[selected=selected]").each(function() {
				    $(this).removeAttr("selected");
				});
				$that.attr("selected", "selected");
				
				var rel = $that.attr("rel");
				$("#"+rel).postUrl(url,{pageId:$pageId});
			}
		});
	},
	
	logout : function() {
		$("#logout").loadUrl(Main.contextPath + "/login.logout", function(res) {
			if (res == "success") {
				window.location.href = Main.contextPath +"/login";
			}
		});
	},
	
	// resize main-content
	resizeMainContent : function() {
    	$('.main-content').each(function(){
            
            // page-content height
            var $mainContent = $(this);
            var $pageHeader = $mainContent.find('div.page-header').first();
            var $pageContent = $pageHeader.nextAll('div.page-content').first();
            if($pageHeader.size() > 0 && $pageContent.size() > 0) {
	            var paddingHeight = parseInt($pageHeader.css('paddingTop').replace('px', ''))
	                + parseInt($pageHeader.css('paddingBottom').replace('px', ''))
	                + parseInt($pageContent.css('paddingTop').replace('px', ''))
	                + parseInt($pageContent.css('paddingBottom').replace('px', ''));
	            var borderHeight = 1;
	            $pageContent.height($mainContent.height() - $pageHeader.height() - paddingHeight - borderHeight);
            }
            
    	});
	},
	detailWindow : function(url, data, title){
		Main.count = Main.count + 1;
		var windowDiv=$('<form></form>');
		windowDiv.append(
			'<input type="hidden" name="url" value="'+url+'"/>');
		windowDiv.append(
			'<input type="hidden" name="data"'+'>');
		windowDiv.append(
			'<input type="hidden" name="title" value="'+title+'"/>');
		windowDiv.attr('style', 'display:none');
		windowDiv.attr("target", "detailWindow_" + url + "_" + Main.count);
		windowDiv.attr("action", Main.contextPath + "/main/mainDetail");
		windowDiv.attr("method", "post");
		$('body').append(windowDiv);  //将表单放置在web中
		windowDiv.find("[name='data']").first().val(JSON.stringify(data));
		window.open("", "detailWindow_" + url + "_" + Main.count);
		windowDiv.submit();
		windowDiv.remove();
		//window.open("/bms-web/bms/mainDetail?url=/SSC11302/init/2&data=" + JSON.stringify(data) + "&title=" + "中标成交确认书详细");

	},
	hiddenHeader : function(){$("#headBreadCrumb").hide()}
};
