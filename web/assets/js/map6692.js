var Map = function() {
    return Map.fn.init();
};

Map.fn = Map.prototype = {

    init: function() {
        this.initMap();
    },

    initMap: function () {
        var _this = this;

        if (typeof _active_lang === 'undefined') {
            _active_lang = 'vn';
        }

        if (_is_demo) {
            _api_key = 'AIzaSyDyxNK6shHjCa0a2ATwbs2kbsur0QTcMMI';
        } else {
            if (typeof _api_key === 'undefined' || _api_key === '') {
                _api_key = 'AIzaSyBUqJrD80qnxzg3_L99iwCcba8g9xfzOrQ';
            }
        }

        $.getScript('https://maps.google.com/maps/api/js?key=' + _api_key + '&amp;libraries=places&amp;region=' + _active_lang + '&amp;language=' + _active_lang, function(){
            var map_canvass = $('.w30s__map-item-canvas');

            map_canvass.each(function (idx, item) {
                var dataMap = $(this).data();

                dataMap = $.extend({}, {
                    map: null,
                    marker: null,
                    geocoder: null,
                    infoWindow: null,
                    map_canvas: item,
                }, dataMap);

                _this.mapInitialize(dataMap);

            });
        });
    },

    mapInitialize: function (dataMap) {
        try {
            // Default PA Vietnam location
            if (dataMap.latitude === '' || dataMap.longitude === '' ||
                dataMap.latitude === null || dataMap.longitude === null ||
                dataMap.latitude === '0' || dataMap.longitude === '0') {
                dataMap.latitude = '10.777067';
                dataMap.longitude = '106.688700';
                dataMap.address = 'Công ty P.A Việt Nam';
            }

            if (typeof dataMap.zoom != 'undefined') {
                dataMap.zoom = parseInt(dataMap.zoom);
            } else {
                dataMap.zoom = 15;
            }

            dataMap.map = new google.maps.Map(dataMap.map_canvas, {
                scrollwheel: false,
                zoom: dataMap.zoom,
                center: new google.maps.LatLng(dataMap.latitude, dataMap.longitude),
                mapTypeId: google.maps.MapTypeId.ROADMAP
            });

            var position = new google.maps.LatLng(dataMap.latitude, dataMap.longitude);
            dataMap.marker = new google.maps.Marker({
                map: dataMap.map,
                position: position,
                draggable: !dataMap.readonly
            });
            dataMap.map.setCenter(position);

            dataMap.infoWindow = new google.maps.InfoWindow;
            dataMap.infoWindow.setContent('<span id="address"><b>Địa chỉ:</b> ' + dataMap.address + '</span>');
            dataMap.infoWindow.open(dataMap.map, dataMap.marker);

            dataMap.geocoder = new google.maps.Geocoder;
        } catch (error) {
            console.error(error);
        }
    }
};

$(function() {
    var map = new Map();
});

