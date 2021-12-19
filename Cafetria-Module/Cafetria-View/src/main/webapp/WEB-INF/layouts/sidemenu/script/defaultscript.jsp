<%-- 
    Document   : includes
    Created on : May 17, 2017, 5:44:58 PM
    Author     : MotYim <mohamed.motyim@gmail.com>
--%>
 <!-- jQuery 2.2.3 -->
        <script src="${contextPath}/resources/plugins/jQuery/jquery-2.2.3.min.js"></script>
        <!-- jQuery UI 1.11.4 -->
        <script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
        <!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
        <script>
            $.widget.bridge('uibutton', $.ui.button);
        </script>
        <script src="${contextPath}/resources/bootstrap/js/jquery-2.2.3.min.js"></script>
        <!-- Bootstrap 3.3.6 -->
        <script src="${contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
        <!-- Sparkline -->
        <script src="${contextPath}/resources/plugins/sparkline/jquery.sparkline.min.js"></script>
        <!-- jvectormap -->
        <script src="${contextPath}/resources/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
        <script src="${contextPath}/resources/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
        <!-- jQuery Knob Chart -->
        <script src="${contextPath}/resources/plugins/knob/jquery.knob.js"></script>
        <!-- daterangepicker -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
        <!-- datepicker -->
        <script src="${contextPath}/resources/plugins/datepicker/bootstrap-datepicker.js"></script>
        <!-- Bootstrap WYSIHTML5 -->
        <script src="${contextPath}/resources/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
        <!-- Slimscroll -->
        <script src="${contextPath}/resources/plugins/slimScroll/jquery.slimscroll.min.js"></script>
        <!-- FastClick -->
        <script src="${contextPath}/resources/plugins/fastclick/fastclick.js"></script>
        <!-- AdminLTE App -->
        <script src="${contextPath}/resources/dist/js/app.min.js"></script>
        <!-- AdminLTE for demo purposes -->
        <script src="${contextPath}/resources/dist/js/demo.js"></script>
        <!-- colorpicker -->
        <script src="${contextPath}/resources/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
        <!-- timepicker -->
        <script src="${contextPath}/resources/plugins/timepicker/bootstrap-timepicker.min.js"></script>
        <!-- DataTables -->
        <script src="${contextPath}/resources/plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="${contextPath}/resources/plugins/datatables/dataTables.bootstrap.min.js"></script>
        <!-- FLOT CHARTS -->
        <script src="${contextPath}/resources/plugins/flot/jquery.flot.min.js"></script>
        <!-- FLOT RESIZE PLUGIN - allows the chart to redraw when the window is resized -->
        <script src="${contextPath}/resources/plugins/flot/jquery.flot.resize.min.js"></script>
        <!-- FLOT PIE PLUGIN - also used to draw donut charts -->
        <script src="${contextPath}/resources/plugins/flot/jquery.flot.pie.min.js"></script>
        <!-- FLOT CATEGORIES PLUGIN - Used to draw bar charts -->
        <script src="${contextPath}/resources/plugins/flot/jquery.flot.categories.min.js"></script>

        <!-- Tablepage script -->
        <script>
            $(function () {
                $("#example1").DataTable();
                $('#example2').DataTable({
                    "paging": true,
                    "lengthChange": false,
                    "searching": false,
                    "ordering": true,
                    "info": true,
                    "autoWidth": false
                });
            });
        </script>

        <!-- FormPage script -->
        <script>
            $(function () {
                //Date picker
                $('#datepicker').datepicker({
                    autoclose: true
                });

                //Colorpicker
                $(".my-colorpicker1").colorpicker();
                //color picker with addon
                $(".my-colorpicker2").colorpicker();

                //Timepicker
                $(".timepicker").timepicker({
                    showInputs: false
                });
            });
        </script>

        <!-- ChartPage script -->
        <script>
            $(function () {

                /*
                 * LINE CHART
                 * ----------
                 */
                //LINE randomly generated data

                var sin = [], cos = [];
                for (var i = 0; i < 14; i += 0.5) {
                    sin.push([i, Math.sin(i)]);
                    cos.push([i, Math.cos(i)]);
                }
                var line_data1 = {
                    data: sin,
                    color: "#3c8dbc"
                };
                var line_data2 = {
                    data: cos,
                    color: "#00c0ef"
                };
                $.plot("#line-chart", [line_data1, line_data2], {
                    grid: {
                        hoverable: true,
                        borderColor: "#f3f3f3",
                        borderWidth: 1,
                        tickColor: "#f3f3f3"
                    },
                    series: {
                        shadowSize: 0,
                        lines: {
                            show: true
                        },
                        points: {
                            show: true
                        }
                    },
                    lines: {
                        fill: false,
                        color: ["#3c8dbc", "#f56954"]
                    },
                    yaxis: {
                        show: true,
                    },
                    xaxis: {
                        show: true
                    }
                });
                //Initialize tooltip on hover
                $('<div class="tooltip-inner" id="line-chart-tooltip"></div>').css({
                    position: "absolute",
                    display: "none",
                    opacity: 0.8
                }).appendTo("body");
                $("#line-chart").bind("plothover", function (event, pos, item) {

                    if (item) {
                        var x = item.datapoint[0].toFixed(2),
                                y = item.datapoint[1].toFixed(2);

                        $("#line-chart-tooltip").html(item.series.label + " of " + x + " = " + y)
                                .css({top: item.pageY + 5, left: item.pageX + 5})
                                .fadeIn(200);
                    } else {
                        $("#line-chart-tooltip").hide();
                    }

                });
                /* END LINE CHART */

                /*
                 * BAR CHART
                 * ---------
                 */

                var bar_data = {
                    data: [["January", 10], ["February", 8], ["March", 4], ["April", 13], ["May", 17], ["June", 9]],
                    color: "#3c8dbc"
                };
                $.plot("#bar-chart", [bar_data], {
                    grid: {
                        borderWidth: 1,
                        borderColor: "#f3f3f3",
                        tickColor: "#f3f3f3"
                    },
                    series: {
                        bars: {
                            show: true,
                            barWidth: 0.5,
                            align: "center"
                        }
                    },
                    xaxis: {
                        mode: "categories",
                        tickLength: 0
                    }
                });
                /* END BAR CHART */

                /*
                 * DONUT CHART
                 * -----------
                 */

                var donutData = [
                    {label: "Series2", data: 30, color: "#3c8dbc"},
                    {label: "Series3", data: 20, color: "#0073b7"},
                    {label: "Series4", data: 50, color: "#00c0ef"}
                ];
                $.plot("#donut-chart", donutData, {
                    series: {
                        pie: {
                            show: true,
                            radius: 1,
                            innerRadius: 0.5,
                            label: {
                                show: true,
                                radius: 2 / 3,
                                formatter: labelFormatter,
                                threshold: 0.1
                            }

                        }
                    },
                    legend: {
                        show: false
                    }
                });
                /*
                 * END DONUT CHART
                 */


                /*
                 * Custom Label formatter
                 * ----------------------
                 */
                function labelFormatter(label, series) {
                    return '<div style="font-size:13px; text-align:center; padding:2px; color: #fff; font-weight: 600;">'
                            + label
                            + "<br>"
                            + Math.round(series.percent) + "%</div>";
                }
            });
        </script>