$(function () {
    //初始化消息提示位置
    toastr.options = {
        //是否显示关闭按钮（提示框右上角关闭按钮）
        closeButton: false,
        //是否为调试
        debug: false,
        //是否显示进度条（设置关闭的超时时间进度条）
        progressBar: false,
        positionClass: "toast-bottom-right",
        //点击消息框自定义事件
        onclick: null,
        //显示动作时间
        showDuration: "300",
        //隐藏动作时间
        hideDuration: "1000",
        //自动关闭超时时间
        timeOut: "2000",
        extendedTimeOut: "1000",
        showEasing: "swing",
        hideEasing: "linear",
        //显示的方式，和jquery相同
        showMethod: "fadeIn",
        //隐藏的方式，和jquery相同
        hideMethod: "fadeOut"
    };
});