<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Chart demo</title>
        <script>
            var model=[];
            model.tokyo="${tokyo}".split(",").map(Number);
            model.newYork="${newYork}".split(",").map(Number);
            model.berlin="${berlin}".split(",").map(Number);
            model.london="${london}".split(",").map(Number);
        </script>
    </head>
    <body>
        <div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

        <script src="https://code.highcharts.com/highcharts.js"></script>
        <script src="https://code.highcharts.com/modules/exporting.js"></script>
        <script
            src="https://code.jquery.com/jquery-3.1.1.min.js"
            integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
            crossorigin="anonymous">
        </script>

        <script type="text/javascript">
            $(function () {
                var chart = new Highcharts.chart('container', {
                    title: {
                        text: 'Monthly Average Temperature',
                        x: -20 //center
                    },
                    subtitle: {
                        text: 'Source: WorldClimate.com',
                        x: -20
                    },
                    xAxis: {
                        categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
                            'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
                    },
                    yAxis: {
                        title: {
                            text: 'Temperature (°C)'
                        },
                        plotLines: [{
                            value: 0,
                            width: 1,
                            color: '#808080'
                        }]
                    },
                    tooltip: {
                        valueSuffix: '°C'
                    },
                    legend: {
                        layout: 'vertical',
                        align: 'right',
                        verticalAlign: 'middle',
                        borderWidth: 0
                    },
                    series: [{
                        name: 'Tokyo',
                        data: model.tokyo
                    }, {
                        name: 'New York',
                        data: model.newYork
                    }, {
                        name: 'Berlin',
                        data: model.berlin
                    }, {
                        name: 'London',
                        data: model.london
                    }]
                });
            });
        </script>
    </body>
</html>
