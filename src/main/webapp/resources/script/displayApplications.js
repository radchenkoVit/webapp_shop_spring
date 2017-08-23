$(function () {

    var $applications = $('#applications');

    function addApplication(app) {
        $applications.append('<li>app name: ' + app.name + ', app id: ' + app.id + '</li>' +
            '<br/><button id="download" class="download" data-id=' + app.id + '> DownloadApp</button>');
    }


    $.ajax({
        type: 'GET',
        url: "/webshop/applications/all",
        success: function (applications) {
            $.each(applications, function (i, app) {
                addApplication(app);
            });
        },
        error: function (message) {
            console.log('Failed to load application, error: ' + message);
        }
    });


    $applications.delegate('.download', 'click', function () {
        var value = $(this).attr('data-id');
        $.ajax({
            type: "GET",
            url: '/webshop/applications/download/' + value,
            success: function () {
                window.location = '/webshop/applications/download/' + value;
            }
        });
    })


});