<script>
    $.ajax({
        url: "/latestArticle?userId=${user.id}",
        async: true,
        success: function (result) {
            $(result).each(function () {
                <%--$(".tagcloud").append('<a href="/archives?name=' + this.name + '&userId=${user.id}" style="font-size: 15px;">' + this.name + '</a>')--%>
                $(".post-list").append('<li class="post-list-item"><a class="post-list-link" href="/article/' + this.id + '">' + this.title + '</a></li>')
            })
        }
    });
</script>