/**
 * 物流区选择JS
 *
 * @author yuan_chen
 */
var PD141122 = {
    formId: "PD141122Form",
    init: function () {

        var R = Raphael("map", 600, 500);
        //调用绘制地图方法
        paintMap(R);

        var textAttr = {
            "fill": "#000",
            "font-size": "12px",
            "cursor": "pointer"
        };

        for (var state in china) {
            china[state]['path'].color = Raphael.getColor(0.9);

            (function (st, state) {

                //获取当前图形的中心坐标
                var xx = st.getBBox().x + (st.getBBox().width / 2);
                var yy = st.getBBox().y + (st.getBBox().height / 2);

                //***修改部分地图文字偏移坐标
                switch (china[state]['name']) {
                    case "江苏":
                        xx += 5;
                        yy -= 10;
                        break;
                    case "河北":
                        xx -= 10;
                        yy += 20;
                        break;
                    case "天津":
                        xx += 10;
                        yy += 10;
                        break;
                    case "上海":
                        xx += 12;
                        break;
                    case "广东":
                        yy -= 10;
                        break;
                    case "澳门":
                        yy += 10;
                        break;
                    case "香港":
                        xx += 20;
                        yy += 5;
                        break;
                    case "甘肃":
                        xx -= 40;
                        yy -= 30;
                        break;
                    case "陕西":
                        xx += 5;
                        yy += 10;
                        break;
                    case "内蒙古":
                        xx -= 15;
                        yy += 65;
                        break;
                    default:
                }

                china[state]['text'] = R.text(xx, yy, china[state]['name']).attr(textAttr);

                st[0].onmouseover = function () {
                    st.animate({fill: st.color, stroke: "#eee"}, 500);
                    china[state]['text'].toFront();
                    R.safari();
                };
                st[0].onmouseout = function () {
                    st.animate({fill: "#97d6f5", stroke: "#eee"}, 500);
                    china[state]['text'].toFront();
                    R.safari();
                };
                st[0].onmousedown = function(){
                    $.pdialog.close();
                    if(china[state]['name'] == "上海"){
                        $('#main-content').postUrl(Main.contextPath+"/PD141121/init/01");
                    }
                    if(china[state]['name'] == "江苏"){
                        $('#main-content').postUrl(Main.contextPath+"/PD141121/init/03");
                    }
                }

            })(china[state]['path'], state);
        }
    }
};
$(document).ready(function () {
    // 初始化调用
    PD141122.init();
});