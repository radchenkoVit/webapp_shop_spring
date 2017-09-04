$(function () {
    var $application = $('#application');

    $application.delegate('.download', 'click', function () {
        var value = $(this).attr('data-id');
        $.ajax({
            type: "POST",
            url: '/webshop/applications/download/' + value,
            success: function(data) {
                var blob = new Blob([data]);
                var link = document.createElement('a');
                link.href = window.URL.createObjectURL(blob);
                link.download = "application.zip";
                link.click();
            }
        });
    })

})