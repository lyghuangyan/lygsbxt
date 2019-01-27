//封装的ajax
function ajaxSubmit(params){
    $.ajax({
        type :params.type|| "POST",
        url : params.url,
        dataType : params.dataType || "json",
        data:params.data,
        beforeSend:params.beforeSend,
        async: params.async || true,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success : params.successHandler,
        error: function(XMLHttpRequest, textStatus, errorThrown) {

            if(textStatus=="error")
                alert(XMLHttpRequest.responseJSON.ErrText);
            if(params.errorHandler)
                params.errorHandler();
        }
    });
}

//获取表格数据
function refreshTable(url,paraData,cb){

    var para={
        url:url,
        data: paraData,
        successHandler: function (data) {
            cb(data.Data);
        }
    };
    ajaxSubmit(para);
}
//获取列表
function getList(url,para,pageNum,callback) {
    para.pageNum=pageNum;
    refreshTable(url, para, function (list) {
        cpage = pageNum;
        bindList(url,para,list,callback)
    });
}
//绑定列表
function bindList(url,para,list,callback) {

    if(list==null){return false}
    var data = list.Data;
    var str = callback(data);
    $('#tBody').html(str);
    setpage(list.Total);
    //点击页码 重新加载数据
    $('.jump').click(function () {
        var tar = $(this).attr('dataCount');
        gotopage(tar, jump);
        function jump() {
            getList(url, para, tar, callback);
        }
    });
}
