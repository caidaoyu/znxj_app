
//±ýÍ¼chart
if ($("#piechart").length) {
    $.plot($("#piechart"), piedata,
        {
            series: {
                pie: {
                    show: true
                }
            },
            grid: {
                hoverable: true,
                clickable: true
            },
            legend: {
                show: false
            }
        });

    function pieHover(event, pos, obj) {
        if (!obj)
            return;
        percent = parseFloat(obj.series.percent).toFixed(2);
        $("#hover").html('<span style="font-weight: bold; color: ' + obj.series.color + '">' + obj.series.label + ' (' + percent + '%)</span>');
    }

    $("#piechart").bind("plothover", pieHover);
}

//»·ÐÎÍ¼chart
if ($("#donutchart").length) {
    $.plot($("#donutchart"), piedata,
        {
            series: {
                pie: {
                    innerRadius: 0.5,
                    show: true
                }
            },
            legend: {
                show: false
            }
        });
}




// Öù×´Í¼£¬ÕÛÏßÍ¼
if ($("#stackchart").length) {
	
    var d1 = [];
    for (var i = 0; i <= 4; i += 1){
    	var v = i;
        d1.push([v, parseInt(Math.random() * 30)]);
    }

   

    var stack = 0, bars = true, lines = false, steps = false;

    function plotWithOptions() {
		//console.info([ d1 ]);
        $.plot($("#stackchart"), [ d1 ], {
            series: {
                stack: null,
                lines: { show: lines, fill: true, steps: false },
                bars: { show: bars, barWidth: 0.6 }
            }
        });
    }

    plotWithOptions();

    
    
    $(".graphControls input").click(function (e) {
        e.preventDefault();
        bars = $(this).val().indexOf("Bars") != -1;
        lines = $(this).val().indexOf("Lines") != -1;
        
        plotWithOptions();
    });
    
}





