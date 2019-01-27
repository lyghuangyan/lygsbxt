//分页
var totalpage, pagesize, cpage, count,  outstr;
//初始化
cpage = 1;
pagesize = 10;
outstr = "";
function gotopage(target,callback) {

    cpage = target;        //把页面计数定位到第几页
    reloadpage(callback);    //调用显示页面函数显示第几页
}
function setpage(totalData) {
    totalpage = Math.ceil(totalData / pagesize);            // 应该上取整，防止少一页
    if (totalpage <= 5) {        //总页数小于5页
        for (count = 1; count <= totalpage; count++) {
            if (count != cpage) {           // 这里应该是!=，少了!
                outstr = outstr + "<a href='javascript:void(0)' class='jump' dataCount='" + count + "'>" + count + "</a>";
            } else {
                outstr = outstr + "<span class='current' >" + count + "</span>";
            }
        }
    }
    if (totalpage > 5) {        //总页数大于5页
        if (parseInt((cpage - 1) / 5) == 0) {
            for (count = 1; count <= 5; count++) {
                if (count != cpage) {

                    outstr = outstr + "<a href='javascript:void(0)' class='jump' dataCount='" + count + "'>" + count + "</a>";
                } else {

                    outstr = outstr + "<span class='current'>" + count + "</span>";
                }
            }
            outstr = outstr + "<a href='javascript:void(0)' class='jump' dataCount='" + count + "'> next </a>";
        }
        else if (parseInt((cpage - 1) / 5) == parseInt(totalpage / 5)) {
            outstr = outstr + "<a href='javascript:void(0)' class='jump' dataCount='" + (parseInt((cpage - 1) / 5) * 5) + "''>prev</a>";
            for (count = parseInt(totalpage / 5) * 5 + 1; count <= totalpage; count++) {
                if (count != cpage) {
                    outstr = outstr + "<a href='javascript:void(0)' class='jump' dataCount='" + count + "'>" + count + "</a>";
                } else {
                    outstr = outstr + "<span class='current'>" + count + "</span>";
                }
            }
        } 
        else {
            outstr = outstr + "<a href='javascript:void(0)' class='jump' dataCount='" + (parseInt((cpage - 1) / 5) * 5) + "'>prev</a>";
            for (count = parseInt((cpage - 1) / 5) * 5 + 1; count <= parseInt((cpage - 1) / 5) * 5 + 5; count++) {
                if (count != cpage) {
                    outstr = outstr + "<a href='javascript:void(0)' class='jump' dataCount='" + count + "'>" + count + "</a>";
                } else {
                    outstr = outstr + "<span class='current'>" + count + "</span>";
                }
            }
            if (Math.ceil(cpage / 5) != Math.ceil(totalpage / 5)) {
                outstr = outstr + "<a href='javascript:void(0)' class='jump' dataCount='" + count + "'> next </a>";
            }
            
        }
    }
    document.getElementById("page").innerHTML = "<span id='info'>共" + totalData + "条数据|第" + cpage + "页</span>" + outstr ;
    outstr = "";



}
//调用分页

//发送ajax,获取当前页的数据,调用bind
function reloadpage(callback) {
    callback();

}

