console.log("JQuery version = " + $().jquery);

function displayHealthChecks(data){
    $.each(data, function(i, item) {
        var healthClass = (item.healthy) ? "success" : "alert";
        var healthLabel = (item.healthy) ? "OK" : "KO";
        
        $("#healths tbody").append("<tr>" 
                + "<td>" + i + "</td>" 
                + "<td>" + item.message + "</td>"
                + "<td><span class=\"label "+ healthClass + "\">" + healthLabel + "</span></td>" 
                + "</tr>");
    });
}

/*
 * Health Checks
 */
$.getJSON("monitoring/metrics/healthcheck")
.done(function(data) {
    displayHealthChecks(data);
})
.fail(function( jqxhr, textStatus, error ) {
    displayHealthChecks(jqxhr.responseJSON);
});


/*
 * Metrics
 */
$.getJSON("monitoring/metrics/metrics", function(data) {
    console.log(data);

    // Meters
    if (!$.isEmptyObject(data.meters)) {
        $.each(data.meters, function(i, item) {
            $("#meters tbody").append("<tr>" 
                    + "<td>" + i + "</td>" 
                    + "<td>" + item.count + "</td>"
                    + "<td>" + item.mean_rate.toFixed(3) + "</td>"
                    + "<td>" + item.m1_rate.toFixed(3) + "</td>"
                    + "<td>" + item.m5_rate.toFixed(3) + "</td>"
                    + "<td>" + item.m15_rate.toFixed(3) + "</td>"
                    + "</tr>");
        });
    } else {
        console.log("There is no Meter");
        $("#meters").hide();
    }

    // Timers
    if (!$.isEmptyObject(data.timers)) {
        $.each(data.timers, function(i, item) {
            var meanClass = (item.mean.toFixed(3) < 0.050) ? "fast" : ((item.mean.toFixed(3) < 0.200) ? "medium" : "slow"); 
            var minClass = (item.min.toFixed(3) < 0.050) ? "fast" : ((item.min.toFixed(3) < 0.200) ? "medium" : "slow"); 
            var maxClass = (item.max.toFixed(3) < 0.050) ? "fast" : ((item.max.toFixed(3) < 0.200) ? "medium" : "slow"); 
            
            $("#timers tbody").append("<tr>" 
                    + "<td width=\"100\">" + i + "</td>" 
                    + "<td>" + item.count + "</td>"
                    + "<td>" + item.mean_rate.toFixed(3) + "</td>"
                    + "<td>" + item.m1_rate.toFixed(3) + "</td>"
                    + "<td>" + item.m5_rate.toFixed(3) + "</td>"
                    + "<td>" + item.m15_rate.toFixed(3) + "</td>"
                    + "<td><span class=\"" + meanClass + "\">" + item.mean.toFixed(3) + "</span></td>"
                    + "<td><span class=\"" + minClass + "\">" + item.min.toFixed(3) + "</span></td>"
                    + "<td><span class=\"" + maxClass + "\">" + item.max.toFixed(3) + "</span></td>"
                    + "<td>" + item.p50.toFixed(3) + "</td>"
                    + "<td>" + item.p75.toFixed(3) + "</td>"
                    + "<td>" + item.p99.toFixed(3) + "</td>"
                    + "</tr>");
        });
    } else {
        console.log("There is no Timer");
        $("#timers").hide();
    }
    
    // Counters
    if (!$.isEmptyObject(data.counters)) {
        $.each(data.counters, function(i, item) {
            $("#counters tbody").append("<tr>" 
                    + "<td>" + i + "</td>" 
                    + "<td>" + item.count + "</td>" 
                    + "</tr>");
        });
    } else {
        console.log("There is no Counter");
        $("#counters").hide();
    }

    // Gauges
    if (!$.isEmptyObject(data.gauges)) {
        $.each(data.gauges, function(i, item) {
            $("#gauges tbody").append("<tr>" 
                    + "<td>" + i + "</td>" 
                    + "<td>" + item.count + "</td>" 
                    + "</tr>");
        });
    } else {
        console.log("There is no Gauge");
        $("#gauges").hide();
    }

    // Histograms
    if (!$.isEmptyObject(data.histograms)) {
        $.each(data.histograms, function(i, item) {
            $("#histograms tbody").append("<tr>" 
                    + "<td>" + i + "</td>" 
                    + "<td>" + item.count + "</td>"
                    + "<td>" + item.mean.toFixed(3) + "</td>"
                    + "<td>" + item.min.toFixed(3) + "</td>"
                    + "<td>" + item.max.toFixed(3) + "</td>"
                    + "<td>" + item.p50.toFixed(3) + "</td>"
                    + "<td>" + item.p75.toFixed(3) + "</td>"
                    + "<td>" + item.p99.toFixed(3) + "</td>"
                    + "</tr>");
        });
    } else {
        console.log("There is no Histogram");
        $("#histograms").hide();
    }
    
});
