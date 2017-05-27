<%-- 页面公用部分 --%>


<script>
    $.ajax({
        url: "/listTags?userId=${user.id}",
        async: true,
        success: function (result) {
            $(result).each(function () {
                $(".tagcloud").append('<a href="/archives?name=' + this.name + '&userId=${user.id}" style="font-size: 15px;">' + this.name + '(' + this.size + ')' + '</a>')
            })
        }
    });
</script>