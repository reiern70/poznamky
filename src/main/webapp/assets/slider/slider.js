var max = 5000;
var min = 0;
var maxBezVyn = max;
var maxSVyn = max;
var absoluteWidth;
var rangeSlider;
var vynimkaCheck = false;
var oldMaxBez;

function initSlider() {
    rangeSlider = document.getElementById('slider');
    noUiSlider.create(rangeSlider, {
        start: [max],
        range: {
            'min': [min],
            'max': [max]
        }
    });

    inputNumber = document.getElementById('input');
    rangeSlider.noUiSlider.on('update', function (values, handle) {
        console.log("event");
        var value = values[handle];
        console.log("handle");
        inputNumber.value = value;
    });

    $("#minValueLabel").text(min);
    $("#maxValueLabel").text(maxBezVyn);
}

function updateSlider(newMin, newMax) {
    rangeSlider.noUiSlider.updateOptions({
        range: {
            'min': [Number(newMin)],
            'max': [Number(newMax)]
        },
        step: 50
    });
    $("#minValueLabel").text(min);
    $("#maxValueLabel").text(maxBezVyn);
}

function setMin(newMin) {
    if (newMin > 0 && newMin < max) {
        min = newMin;
        var newPosition = (newMin / max) * absoluteWidth;
        $("#slider").css("width", (maxBezVyn / max) * absoluteWidth - newPosition);
        $("#vynimka").css("margin-left", newPosition);
        $("#vynimka").css("width", (maxSVyn / max) * absoluteWidth - newPosition);
        updateSlider(min, maxBezVyn);
    }
}

function setMaxBezVyn(newMax) {
    $('#myCheckbox').attr('checked', true);
    if (newMax > min && newMax <= max) {
        var newMaxPosition = (newMax / max) * absoluteWidth;
        maxBezVyn = newMax;
        $("#slider").css("width", newMaxPosition - (min / max) * absoluteWidth);
        updateSlider(min, maxBezVyn);
    }
}

function setMaxSVyn(newMax) {
    if (newMax > min && newMax > maxBezVyn && newMax <= max) {
        var newMaxPos = (newMax / max) * absoluteWidth;
        maxSVyn = newMax;
        $("#vynimka").css("width", newMaxPos - (min / max) * absoluteWidth);
    }
}

function setSelectedValue(value){
    rangeSlider.noUiSlider.set(value);
    $("#input").val(value);
}

function initButtonActions() {
    $("#setMin").click(function () {
        setMin(Number($("#setMinVal").val()));
    });

    $("#setMaxBez").click(function () {
        setMaxBezVyn(Number($("#setMaxBezVal").val()));
    });

    $("#setMaxVyn").click(function () {
        setMaxSVyn(Number($("#setMaxVynVal").val()));
    });

    $("#vynimkaCheck").change(function () {
        vynimkaCheck = $("#vynimkaCheck").is(':checked');
        $("#vynimkaCheckVal").text(vynimkaCheck + " ");

        if (vynimkaCheck) {
            oldMaxBez = maxBezVyn;
            setMaxBezVyn(maxSVyn);
        } else {
            maxBezVyn = oldMaxBez;
            setMaxBezVyn(oldMaxBez);
        }
    });

    $("#input").change(function () {
        setSelectedValue(Number($("#input").val()));
    });

    $(".slider_wrapper").click(function (e) {
        var posX = $(this).position().left;
        var position = (e.pageX - posX); // Relative (slider's) clicked position

        if ($('.slider').has($(e.target)).length == 0) {
            // if (clicked part of slider is not in slider range), tak jedem
            if (position < absoluteWidth / 2) {
                rangeSlider.noUiSlider.set(-1);
            } else {
                rangeSlider.noUiSlider.set(99999);
            }
        }
    });

}

$(document).ready(function () {
    absoluteWidth = $(".slider_wrapper").outerWidth();
    initSlider();
    initButtonActions();
});