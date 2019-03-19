$(function() {
    Highcharts.setOptions({
        lang: {
            decimalPoint: '.',
            thousandsSep: ','
        }
    });

    drawSalesByTypeChart();
    drawSalesByRegionChart();
});

function drawSalesByRegionChart() {
    Highcharts.chart('salesByRegion', {
        chart: {
            type: 'pie',
            margin: 40,
            options3d: {
                enabled: true,
                alpha: 45,
                beta: 0
            }
        },
        title: {
            text: 'Candidates at stages'
        },
        tooltip: {
            pointFormat: "{point.y:,.0f}"
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                depth: 35
            }
        },
        series: [{
            name: 'Regions',
            colorByPoint:true,
            data: [{
                name: 'Ground School',
                y: /*[[${amtGroundSchool}]]*/ 0
            },{
                name: 'Operations Manual',
                y: /*[[${amtOpsManual}]]*/ 0
            },{
                name: 'Flight Assessment',
                y: /*[[${amtFlightAssessment}]]*/ 0
            }]
        }]
    });
}

function drawSalesByTypeChart() {
    Highcharts.chart('salesByType', {
        chart: {
            type: 'column',
            margin: 75,
            options3d: {
                enabled: true,
                alpha: 15,
                beta: 15,
                depth: 110
            }
        },
        title: {
            text: 'Candidate per stage per month'
        },
        xAxis: {
            categories: ['November', 'December']
        },
        yAxis: {
            title: {
                text: 'Number of Candidates'
            }
        },
        tooltip: {
            pointFormat: '<span style="color:{series.color}">\u25CF</span> {series.name}:{point.y} / {point.stackTotal}'
        },
        plotOptions: {
            column: {
                depth: 60,
                stacking: true,
                grouping: false,
                groupZPadding: 10
            }
        },
        series: [{
            name: 'Ground School',
            data: /*[[${amtGroundSchoolList}]]*/ []
        }, {
            name: 'Operations Manual',
            data: /*[[${amtOpsManualList}]]*/ []
        }, {
            name: 'Flight Assessment',
            data: /*[[${amtFlightAssessmentList}]]*/ []
        }]
    });
}