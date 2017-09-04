$(function () {

    var $applications = $('#applications');
    var $categories = $('#categories');

    $applications.delegate('.download', 'click', function () {
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

    $categories.delegate('.pointer', 'click', function () {
        var idCategory = $(this).attr('data-id');
        console.log("reached method");
        window.location.replace("/webshop/category?categoryId=" + idCategory);
    })
});