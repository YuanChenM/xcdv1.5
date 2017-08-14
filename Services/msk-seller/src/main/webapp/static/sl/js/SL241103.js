/**
 *JS
 *@author puxigui 
 */
var $List_Grid;
var SL241103 = {
		initData: function(){
			$.core.bindImgView();
			$( "#SL24110301accordion" ).accordion({ heightStyle: "content" });
			$( "#SL24110302accordion" ).accordion({ heightStyle: "content" });
			$( "#SL24110303accordion" ).accordion({ heightStyle: "content" });
			$( "#SL24110304accordion" ).accordion({ heightStyle: "content" });
			$( "#SL24110305accordion" ).accordion({ heightStyle: "content" });
			$( "#SL24110306accordion" ).accordion({ heightStyle: "content" });
			$( "#SL24110307accordion" ).accordion({ heightStyle: "content" });
			$( "#SL24110308accordion" ).accordion({ heightStyle: "content" });
			$( "#SL24110312accordion" ).accordion({ heightStyle: "content" });
			$('#SL24110301accordion').on( "accordionactivate", function( event, ui ) {
				if(ui.newHeader.length==1){
					$("#initSL24110301").postUrl(Main.contextPath+"/SL241103/initSL24110301",{slCode:slCode,epId:epId});
				}
			} );
			$('#SL24110302accordion').on( "accordionactivate", function( event, ui ) {
				if(ui.newHeader.length==1){
					$("#initSL24110302").postUrl(Main.contextPath+"/SL241103/initSL24110302",{slCode:slCode,epId:epId});
				}
			} );
			$('#SL24110303accordion').on( "accordionactivate", function( event, ui ) {
				if(ui.newHeader.length==1){
					$("#initSL24110303").postUrl(Main.contextPath+"/SL241103/initSL24110303",{slCode:slCode,epId:epId});
				}
			} );
			$('#SL24110304accordion').on( "accordionactivate", function( event, ui ) {
				if(ui.newHeader.length==1){
					$("#initSL24110304").postUrl(Main.contextPath+"/SL241103/initSL24110304",{slCode:slCode,epId:epId});
				}
			} );
			$('#SL24110305accordion').on( "accordionactivate", function( event, ui ) {
				if(ui.newHeader.length==1){
					$("#initSL24110305").postUrl(Main.contextPath+"/SL241103/initSL24110305",{slCode:slCode,epId:epId});
				}
			} );
			$('#SL24110306accordion').on( "accordionactivate", function( event, ui ) {
				if(ui.newHeader.length==1){
					$("#initSL24110306").postUrl(Main.contextPath+"/SL241103/initSL24110306",{slCode:slCode,epId:epId});
				}
			} );
			$('#SL24110307accordion').on( "accordionactivate", function( event, ui ) {
				if(ui.newHeader.length==1){
					$("#initSL24110307").postUrl(Main.contextPath+"/SL241103/initSL24110307",{slCode:slCode,epId:epId});
				}
			} );
			$('#SL24110308accordion').on( "accordionactivate", function( event, ui ) {
				if(ui.newHeader.length==1){
					$("#initSL24110308").postUrl(Main.contextPath+"/SL241103/initSL24110308",{slCode:slCode,epId:epId});
				}
			} );
			$('#SL24110312accordion').on( "accordionactivate", function( event, ui ) {
				if(ui.newHeader.length==1){
					$("#initSL24110312").postUrl(Main.contextPath+"/SL241103/initSL24110312",{epId:epId});
				}
			} );
		},
}
$(document).ready(function() {
	//初始化调用
	SL241103.initData();
});