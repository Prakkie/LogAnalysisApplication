<%@ page import="com.visualpath.hadoop.loganalysis.dto.BrowsersInfo" %>
<%@ page import="com.visualpath.hadoop.loganalysis.dto.MonthlyHits" %>
<%@ page isELIgnored="false" %>

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
<script type="text/javascript">
	var chromeBrowser = ${browserDeatils.getChrome()};
	var firefoxBrowser = ${browserDeatils.getFirefox()};
	var safariBrowser = ${browserDeatils.getSafari()};
	var ieBrowser = ${browserDeatils.getIe()};
	var geckoBrowser = ${browserDeatils.getGecko()};
	var appleBrowser = ${browserDeatils.getAppleWebKit()};
	var totalBrowserCount = chromeBrowser+firefoxBrowser+safariBrowser+ieBrowser+geckoBrowser+appleBrowser;
	var splunk = ${hits.getSplunkYearly()};
	var www = ${hits.getWwwYearly()};
	
	$(function() {
		var splunkMonth = ${hits.getSplunk()};
		var wwwMonth = ${hits.getWww()};
		var splunk_Month = JSON.parse("[" + splunkMonth + "]");
		var www_Month = JSON.parse("[" + wwwMonth + "]");
		$('#plottedGraph')
				.highcharts(
						{
							title : {
								text : 'Monthly Average Hits Server',
								x : -20
							//center
							},
							subtitle : {
								x : -20
							},
							xAxis : {
								categories : [ 'Jan', 'Feb', 'Mar', 'Apr',
										'May', 'Jun', 'Jul', 'Aug', 'Sep',
										'Oct', 'Nov', 'Dec' ]
							},
							yAxis : {
								title : {
									text : 'Number of Hits'
								},
								plotLines : [ {
									value : 0,
									width : 1,
									color : '#808080'
								} ]
							},
							tooltip : {
								valueSuffix : 'hits'
							},
							legend : {
								layout : 'vertical',
								align : 'right',
								verticalAlign : 'middle',
								borderWidth : 0
							},
							series : [
									{
										name : 'Apache Server',
										data : [ 7.0, 6.9, 9.5, 14.5, 18.2,
												21.5, 25.2, 26.5, 23.3, 18.3,
												13.9, 9.6 ]
									},
									{
										name : 'WWW',
										data : www_Month
									},
									{
										name : 'Websphere Server',
										data : [ -0.9, 0.6, 3.5, 8.4, 13.5,
												17.0, 18.6, 17.9, 14.3, 9.0,
												3.9, 1.0 ]
									},
									{
										name : 'Splunk',
										data : splunk_Month
									} ]
						});
	});
	$(function() {
		$('#pieChart')
				.highcharts(
						{
							chart : {
								plotBackgroundColor : null,
								plotBorderWidth : null,
								plotShadow : false
							},
							title : {
								text : 'Browser share '
							},
							tooltip : {
								pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
							},
							plotOptions : {
								pie : {
									allowPointSelect : true,
									cursor : 'pointer',
									dataLabels : {
										enabled : true,
										color : '#000000',
										connectorColor : '#000000',
										format : '<b>{point.name}</b>: {point.percentage:.1f} %'
									}
								}
							},
							series : [ {
								type : 'pie',
								name : 'Browser share',
								data : [ [ 'Safari', (safariBrowser/totalBrowserCount)*100 ],
										[ 'Chrome', (chromeBrowser/totalBrowserCount)*100 ], {
											name : 'Firefox',
											y : (firefoxBrowser/totalBrowserCount)*100,
											sliced : true,
											selected : true
										}, [ 'IE', (ieBrowser/totalBrowserCount)*100 ],
										[ 'Gecko', (geckoBrowser/totalBrowserCount)*100 ],
										[ 'AppleweKit', (appleBrowser/totalBrowserCount)*100 ],]
							} ]
						});
	});
	$(function() {
		var splunkAvg = ${hits.getSplunkAvg()};
		var wwwAvg = ${hits.getWwwAvg()};
		var splunk_Avg = JSON.parse("[" + splunkAvg + "]");
		var www_Avg = JSON.parse("[" + wwwAvg + "]");
		$('#barChart')
				.highcharts(
						{
							chart : {
								type : 'column'
							},
							title : {
								text : 'Monthly Average Hits'
							},
							subtitle : {
							//text: 'Source: WorldClimate.com'
							},
							xAxis : {
								categories : [ 'Jan', 'Feb', 'Mar', 'Apr',
										'May', 'Jun', 'Jul', 'Aug', 'Sep',
										'Oct', 'Nov', 'Dec' ]
							},
							yAxis : {
								min : 0,
								title : {
									text : 'Hits (hundreds)'
								}
							},
							tooltip : {
								headerFormat : '<span style="font-size:10px">{point.key}</span><table>',
								pointFormat : '<tr><td style="color:{series.color};padding:0">{series.name}: </td>'
										+ '<td style="padding:0"><b>{point.y:.1f} hits</b></td></tr>',
								footerFormat : '</table>',
								shared : true,
								useHTML : true
							},
							plotOptions : {
								column : {
									pointPadding : 0.2,
									borderWidth : 0
								}
							},
							series : [
									{
										name : 'Apache Server',
										data : [ 0, 0.5, 1.4, 0.2,
												0, 0, 0, 0,
												9, 8, 1, 4 ]

									},
									{
										name : 'WWW',
										data : www_Avg

									},
									{
										name : 'Splunk',
										data : splunk_Avg

									},
									{
										name : 'Websphere Server',
										data : [ 0.4, 0, 0, 2, 3,
												4, 5, 6, 7, 1,
												3, 2 ]

									},
									{
										name : 'Tomact Server',
										data : [ 3, 2, 2, 2, 2,
												4, 4, 4, 4, 4,
												2, 4 ]

									} ]
						});
	});
	$(function() {
		var splunkList = JSON.parse("[" + splunk + "]");
		var wwwList = JSON.parse("[" + www + "]");
		
		$('#stackedArea').highcharts({
			chart : {
				type : 'area'
			},
			title : {
				text : 'Yearly Server Hits'
			},
			subtitle : {
			// text: 'Source: LogAnalysis'
			},
			xAxis : {
				categories : [ '2010', '2011', '2012', '2013', '2014' ],
				tickmarkPlacement : 'on',
				title : {
					enabled : false
				}
			},
			yAxis : {
				min : 0,
				title : {
					text : 'Total Server Hits (Thousands)'
				},
				labels : {
					formatter : function() {
						return this.value / 1000;
					}
				}
			},
			tooltip : {
				shared : true,
				valueSuffix : ' Thousands'
			},
			plotOptions : {
				area : {
					stacking : 'normal',
					lineColor : '#666666',
					lineWidth : 1,
					marker : {
						lineWidth : 1,
						lineColor : '#666666'
					}
				}
			},
			
			series : [ {
				name : 'Apache Server',
				data : [0,0,0,0,0]
			}, {
				name : 'WWW',
				data : wwwList
			}, {
				name : 'Splunk',
				data : splunkList
			}, {
				name : 'Websphere Server',
				data : [0,0,0,0,0]
			}, {
				name : 'Tomact Server',
				data : [0,0,0,0,0]
			} ]
		});
	});

	function doOnValueSubmit() {
		var selectedServer = $("#dropdown").val();
		//alert(selectedServer);
		document.location.href = "SelectedServer?param=" + selectedServer + "";
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
		<select id="dropdown" name="dropdown" >
			<option value=" " selected>Select Server</option>
			<option value="apacheServer">Apache Server</option>
			<option value="www">WWW</option>
			<option value="splunk">Splunk</option>
			<option value="websphereServer">Websphere Server</option>
			<option value="tomactServer">Tomact Server</option>
		</select>
		<div style="margin-top: 0px;margin-left: 425px;width: 12%;">
		<!-- <select id="prodcuts" name="prodcuts">
			<option value=" " selected>Select Top Products</option>
			<option value="apacheServer">Apache Server</option>
			<option value="www">WWW</option>
			<option value="splunk">Splunk</option>
			<option value="websphereServer">Websphere Server</option>
			<option value="tomactServer">Tomact Server</option>
		</select> -->
		</div>
	</div>
	<input id="submit" type="submit" value="Submit"  onclick="doOnValueSubmit()"/>
	
	<div id="dashboards" style="width: 1300px; height: 400px;margin-top: 20px;">
		<div id="plottedGraph" class="right" style="width: 620px; height: 400px;margin-left: 35px;"></div>
		
		<div id="pieChart" class="right" style="width: 620px; height: 400px; margin-left: 20px;"></div>
		<div id="barChart" class="right" style="width: 620px; height: 400px; margin-left: 33px;"></div>
		<div id="stackedArea" class="right" style="width: 620px; height: 400px; margin-left: 20px;"></div>
	</div>
</div>
<div id="copyright">
	<p>Copyright (c) 2014 anilhadoopinfo.com. All rights reserved.</p>
</div>

</body>

</html>
