$(function () {

    var $applications = $('#applications');
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