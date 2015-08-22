$(".confirmDelete").bind("submit",function() {
    var conf = confirm("Are you sure you want to delete this?");

    return conf;
});

