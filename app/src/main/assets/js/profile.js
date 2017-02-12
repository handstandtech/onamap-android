var TIME = 0;

var usMap = {
    R: undefined,
    defaultFillColor: "#d3d3d3",
    paths: {},
    init: function () {
        usMap.R = new ScaleRaphael("us-map-svg", 959, 593);
        var attr = {
            "fill": usMap.defaultFillColor,
            "stroke": "#333",
            "stroke-opacity": "1",
            "stroke-linejoin": "round",
            "stroke-miterlimit": "4",
            "stroke-width": ".75",
            "stroke-dasharray": "none"
        };

        var container = $("#content");

        function resizePaper() {

        var win = $(this);
            var width = container.width();
            var height = container.height();
            usMap.R.changeSize(width, height, true, false);
        }

        resizePaper();
        $(window).resize(resizePaper);
        // Draw Map and store Raphael paths
        for (var stateAbbr in svgUS) {
            usMap.paths[stateAbbr] = usMap.R.path(svgUS[stateAbbr]).attr(attr)
                .data('id', stateAbbr);
        }

        usMap.addListeners();
    },
    fadeOut:function(st, stateAbbr) {
            st.attr({"fill": "#d3d3d3"});
    },
    fadeIn:function(st, stateAbbr) {
            st.attr({"fill": "#555"});
    },
    addListeners: function () {
        // Do Work on Map
        for (var stateAbbr in svgUS) {
            (function (st, stateAbbr) {
                st[0].style.cursor = "pointer";

                st[0].selected = false;

                st[0].onclick = function () {
                    var stateAbbr = st.data('id').toUpperCase();
                    Android.stateClicked(stateAbbr);
                };
            })(usMap.paths[stateAbbr], stateAbbr);
        }
    }
};

$(function () {
    usMap.init();
});


window.WebView = {
    stateClickedExternal:function(stateAbbr, selected) {
        console.log("WebView::stateClickedExternal("+stateAbbr+ ", "+ selected+")");
        var path = usMap.paths[stateAbbr];
        if(selected){
            usMap.fadeIn(path, stateAbbr);
        } else{
            usMap.fadeOut(path, stateAbbr);
        }
    }
};


if(window.Android==undefined){
    console.log("Using Placeholder for Android Provided Interface");
    var boolFlip = true;
    window.Android = {
        stateClicked: function(stateAbbr){
            console.log("Android::stateClicked("+stateAbbr+")");
            WebView.stateClickedExternal(stateAbbr, boolFlip);
            boolFlip = !boolFlip;
        }
    };
}