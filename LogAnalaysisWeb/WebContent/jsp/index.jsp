<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Log Analysis DashBoards</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="http://fonts.googleapis.com/css?family=Didact+Gothic" rel="stylesheet" />
<link href="css/default.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/fonts.css" rel="stylesheet" type="text/css" media="all" />
<!--[if IE 6]><link href="default_ie6.css" rel="stylesheet" type="text/css" /><![endif]-->

<script src="js/jquery-1.9.1.js" type="text/javascript"></script>
<script src="js/highcharts/highcharts.js" type="text/javascript"></script>
<script src="js/highcharts/highcharts.js" type="text/javascript"></script>
<script type="text/javascript">
$(function (){
	$('#plottedGraph').highcharts({
        title: {
            text: 'Monthly Average Hits Server',
            x: -20 //center
        },
        subtitle: {
            x: -20
        },
        xAxis: {
            categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
                'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
        },
        yAxis: {
            title: {
                text: 'Number of Hits'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix: 'hits'
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: [{
            name: 'Apache Server',
            data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
        }, {
            name: 'WWW',
            data: [-0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8, 24.1, 20.1, 14.1, 8.6, 2.5]
        }, {
            name: 'Websphere Server',
            data: [-0.9, 0.6, 3.5, 8.4, 13.5, 17.0, 18.6, 17.9, 14.3, 9.0, 3.9, 1.0]
        }, {
            name: 'Splunk',
            data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8]
        }]
    });
});
$(function () {
    $('#pieChart').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: 'Browser share '
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: [{
            type: 'pie',
            name: 'Browser share',
            data: [
                ['Safari',   45.0],
                ['Chrome',       26.8],
                {
                    name: 'Firefox',
                    y: 12.8,
                    sliced: true,
                    selected: true
                },
                ['IE',    8.5],
            ]
        }]
    });
});
$(function () {
    $('#barChart').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: 'Monthly Average Hits'
        },
        subtitle: {
            //text: 'Source: WorldClimate.com'
        },
        xAxis: {
            categories: [
                'Jan',
                'Feb',
                'Mar',
                'Apr',
                'May',
                'Jun',
                'Jul',
                'Aug',
                'Sep',
                'Oct',
                'Nov',
                'Dec'
            ]
        },
        yAxis: {
            min: 0,
            title: {
                text: 'Hits (hundreds)'
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
            name: 'Apache Server',
            data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4]

        }, {
            name: 'WWW',
            data: [83.6, 78.8, 98.5, 93.4, 106.0, 84.5, 105.0, 104.3, 91.2, 83.5, 106.6, 92.3]

        }, {
            name: 'Splunk',
            data: [48.9, 38.8, 39.3, 41.4, 47.0, 48.3, 59.0, 59.6, 52.4, 65.2, 59.3, 51.2]

        }, {
            name: 'Websphere Server',
            data: [42.4, 33.2, 34.5, 39.7, 52.6, 75.5, 57.4, 60.4, 47.6, 39.1, 46.8, 51.1]

        },{
            name: 'Tomact Server',
            data: [42.4, 33.2, 34.5, 39.7, 52.6, 75.5, 57.4, 60.4, 47.6, 39.1, 46.8, 51.1]

        }
        ]
    });
});
  function doOnValueSubmit(){
	  var selectedServer = $("#dropdown").val();
	  alert(selectedServer);
  }

</script>
<style type="text/css">

</style>
</head>
<body>
<div id="header-wrapper">
	<div id="header" class="container">
		<div id="logo">
			<h1><a href="#">Log Analysis DashBoard</a></h1>
		</div>
		<select id="dropdown" name="dropdown">
			<option value=" " selected>Select Server</option>
			<option value="apacheServer">Apache Server</option>
			<option value="www">WWW</option>
			<option value="splunk">Splunk</option>
			<option value="websphereServer">Websphere Server</option>
			<option value="tomactServer">Tomact Server</option>
		</select>
		<div style="margin-top: 0px;margin-left: 425px;width: 12%;">
		<select id="prodcuts" name="prodcuts">
			<option value=" " selected>Select Top Products</option>
			<option value="apacheServer">Apache Server</option>
			<option value="www">WWW</option>
			<option value="splunk">Splunk</option>
			<option value="websphereServer">Websphere Server</option>
			<option value="tomactServer">Tomact Server</option>
		</select>
		</div>
	</div>
	<input id="submit" type="submit" value="Submit" onclick="doOnValueSubmit()"/>
	
	<div id="dashboards" style="width: 1300px; height: 400px;margin-top: 20px;">
		<div id="plottedGraph" class="right" style="width: 620px; height: 400px;margin-left: 35px;"></div>
		
		<div id="pieChart" class="right" style="width: 620px; height: 400px; margin-left: 20px;"></div>
		<div id="barChart" class="right" style="width: 620px; height: 400px; margin-left: 33px;"></div>
	</div>
</div>
<div id="copyright">
	<p>Copyright (c) 2014 anilhadoopinfo.com. All rights reserved.</p>
</div>

</body>

</html>
