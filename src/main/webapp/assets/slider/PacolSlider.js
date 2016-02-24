/**
 * Created by te071784 on 23. 2. 2016.
 */
/**
 * Depends on JQuery and NoUISlider plugin
 * @param min
 * @param max
 * @param sliderId
 * @constructor
 */
//function PacolSlider(min, max, maxBezVyn, maxSVyn, absoluteWidth, vynimkaCheck, sliderId, inputId, minValueLabelId, maxValueLabelId) {
//    this.min = min;
//    this.max = max;
//    this.maxBezVyn = maxBezVyn;
//    this.maxSVyn = maxSVyn;
//    this.absoluteWidth = absoluteWidth;
//    this.vynimkaCheck = vynimkaCheck;
//    this.rangeSlider = document.getElementById(sliderId);
//    this.inputField = document.getElementById(inputId);
//
//    this.minValueLabel = document.getElementById(minValueLabelId);
//    this.maxValueLabel = document.getElementById(maxValueLabelId);
//}

function PacolSlider(data) {
    this.min = data["min"];
    this.max = data["max"];
    this.maxBezVyn = data["maxBezVyn"];
    this.maxSVyn = data["maxSVyn"];
    this.absoluteWidth = data["absoluteWidth"];
    this.vynimkaCheck = data["vynimkaCheck"];
    this.rangeSlider = document.getElementById(data["sliderId"]);

    this.inputField = document.getElementById(data["inputId"]);
    this.minValueLabel = document.getElementById(data["minValueLabelId"]);
    this.maxValueLabel = document.getElementById(data["maxValueLabelId"]);
}

PacolSlider.prototype = {
    constructor: PacolSlider,

    getMin: function () {
        return this.min;
    },
    getMax: function () {
        return this.max;
    },
    getMaxBezVyn: function (maxBezVyn) {
        return this.maxBezVyn;
    },
    getMaxSVyn: function (maxSVyn) {
        return this.maxSVyn;
    },
    getAbsoluteWidth: function (absoluteWidth) {
        return this.absoluteWidth;
    },
    getVynimkaCheck: function (vynimkaCheck) {
        return this.vynimkaCheck;
    },
    setMax: function (max) {
        this.max = max;
    },
    setMin: function (min) {
        this.min = min;
    },
    setMaxBezVyn: function (maxBezVyn) {
        this.maxBezVyn = maxBezVyn;
    },
    setMaxSVyn: function (maxSVyn) {
        this.maxSVyn = maxSVyn;
    },
    setAbsoluteWidth: function (absoluteWidth) {
        this.absoluteWidth = absoluteWidth;
    },
    setVynimkaCheck: function (vynimkaCheck) {
        this.vynimkaCheck = vynimkaCheck;
    },
    initSlider: function () {
        console.log(this.rangeSlider);
        console.log(this.inputField);

        // range slider element init
        noUiSlider.create(this.rangeSlider, {
            start: [this.max],
            range: {
                'min': [this.min],
                'max': [this.max]
            }
        });

        // updating input field values
        this.rangeSlider.noUiSlider.on('update', function (values, handle) {
            var value = values[handle];
            this.inputField.value = value;
            $(this.inputField).trigger('inputchange');
        }.bind(this));
    }
}

