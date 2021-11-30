$(document).ready(function() {
    $(".list1").click();
    function changes() {
        console.log('hereeee');
        var elements = document.getElementsByName('myslot');
        $(".modal-background").css("display","none");
        $(".choose-slot").html("CHANGE YOUR SLOT");
        $(".remove").remove();
    }
    // For each item slots have different functions
    $(".list1").click(function() {
        $.get('/get-slots?dateReserve='+$("#dateStart").val(), function(response) {
            $(".slots").empty();
            for (var count = 1; count <= 50; count++) {
                $(".slots").append('<div class="slot-item" id="slot-item-A' +count+ '">'+ '<i class="count">' + count + '</i>' +'</div>');
            }
            $(".slot-item").each(function(index) {
                $(this).on("click", function(){
                    changes();
                    // $(".result").html("YOU CHOOSE " + "A" + $(this).text());
                    $("#slotNo").val("A" + $(this).text());
                });
            });
            $.each(response, function(index) {
                let slot = response[index];
                $("#slot-item-" + slot).css("pointer-events", "none")
                $("#slot-item-" + slot).css("background-color", "red")
            });
        }, 'json');
    });
    $(".list2").click(function() {
        $.get('/get-slots?dateReserve='+$("#dateStart").val(), function(response) {
            $(".slots").empty();
            for (var count = 1; count <= 50; count++) {
                $(".slots").append('<div class="slot-item" id="slot-item-B' +count+ '">'+ '<i class="count">' + count + '</i>' +'</div>');
            }
            $(".slot-item").each(function(index) {
                $(this).on("click", function(){
                    var elements = document.getElementsByName('myslot');
                    changes();
                    //$(".result").html("YOU CHOOSE " + "B" + $(this).text());
                    $("#slotNo").val("B" + $(this).text());
                });
            });
            $.each(response, function(index) {
                let slot = response[index];
                $("#slot-item-" + slot).css("pointer-events", "none")
                $("#slot-item-" + slot).css("background-color", "red")
            });
        }, 'json');
    });
    $(".list3").click(function() {
        $.get('/get-slots?dateReserve='+$("#dateStart").val(), function(response) {
            $(".slots").empty();
            for (var count = 1; count <= 50; count++) {
                $(".slots").append('<div class="slot-item" id="slot-item-C' +count+ '">'+ '<i class="count">' + count + '</i>' +'</div>');
            }
            $(".slot-item").each(function(index) {
                $(this).on("click", function(){
                    var elements = document.getElementsByName('myslot');
                    changes();
                    //$(".result").html("YOU CHOOSE " + "C" + $(this).text());
                    $("#slotNo").val("C" + $(this).text());
                });
            });
            $.each(response, function(index) {
                let slot = response[index];
                $("#slot-item-" + slot).css("pointer-events", "none")
                $("#slot-item-" + slot).css("background-color", "red")
            });
        }, 'json');
    });
    $(".list4").click(function() {
        $.get('/get-slots?dateReserve='+$("#dateStart").val(), function(response) {
            $(".slots").empty();
            for (var count = 1; count <= 50; count++) {
                $(".slots").append('<div class="slot-item" id="slot-item-D' +count+ '">'+ '<i class="count">' + count + '</i>' +'</div>');
            }
            $(".slot-item").each(function(index) {
                $(this).on("click", function(){
                    var elements = document.getElementsByName('myslot');
                    changes();
                    //$(".result").html("YOU CHOOSE " + "D" + $(this).text());
                    $("#slotNo").val("D" + $(this).text());
                });
            });
            $.each(response, function(index) {
                let slot = response[index];
                $("#slot-item-" + slot).css("pointer-events", "none")
                $("#slot-item-" + slot).css("background-color", "red")
            });
        }, 'json');
    });
    $(".slot-item").each(function(index) {
        $(this).on("click", function(){
            var elements = document.getElementsByName('myslot');
            changes();
            $(".result").html("A" + $(this).text());
        });
    });

    // Modal functions
    $("#choose-slot").click(function() {
        $(".slots").empty();
        $(".modal-background").css("display","block");
    });

    $(".hide-modal").click(function() {
        $(".modal-background").css("display","none");
    });

    $("#dateStart").change(function() {
        console.log($("#dateStart").val());
        if ($("#dateStart").val() == '') {
            $("#choose-slot").prop("disabled",true);
        } else {
            $("#choose-slot").prop("disabled",false);
        }
    });

});