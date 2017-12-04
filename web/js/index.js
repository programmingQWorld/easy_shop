$(function () {
    atoCart ();
    $("#loginsubmit").bind("click", loginUser);
});
/* 登录用户 */
function loginUser () {
    $("#formlogin").submit();
}
/* 添加到到购物车 */
function atoCart () {
    $(".ato-cart").bind("click", function () {
        var pid = $(this).attr("data-id");
        $.ajax({
            url: 'sorder-add/' + pid,
            async: false,
            success: function (data) {
                $(".num-in-cart").each(function () {
                    this.innerHTML = data;
                });
            }
        });
        return false;
    })
}

var cartController = function($scope){
    // 发送ajax请求到后台-->取出购物车的数据 url=shopcartinfo
    $.ajax( {
        url: "/ssm/shopcartinfo",
        async: false,
        success: function ( data ) {
           $scope.cart = data;
        }
    } );

    console.log($scope.cart);
    //总购买数量
    $scope.totalQuantity = function(){
        var total = 0;
        angular.forEach($scope.cart,function(item){
            total += parseInt(item.quantity);
        });
        return total;
    };
    //总购买价格
    $scope.totalPrice = function(){
        var total = 0;
        angular.forEach($scope.cart,function(item){
            total += parseInt(item.quantity*item.price);
        });
        return total;
    };
    //找一个项目
    $scope.findItem = function(id){
        var index = -1;
        angular.forEach($scope.cart, function(item, key){
            if(item.id === id){
                index = key;
                return;
            };
        });
        return index;
    };
    //移除table
    $scope.remove = function(id){
        var index = $scope.findItem(id);
        if(index !== -1){
            $scope.cart.splice(index,1);
        };
    };
    //减少一个商品数量
    $scope.reduce = function(id){
        var index = $scope.findItem(id);
        if(index !== -1){
            var item = $scope.cart[index]
            if(item.quantity>1){
                --item.quantity;
            } else{
                var returnKey = confirm("是否从购物车中删除该产品！");
                if(returnKey){
                    $scope.remove(id);
                }
            }
        }
    };
    //增加一个商品数量
    $scope.add = function(id){
        var index = $scope.findItem(id);
        if(index !== -1){
            ++$scope.cart[index].quantity;
        };
    };

    // 清空购物车
    $scope.clearCart = function () {
        alert("确定要清空购物车么？");
    }
    // 结账
    $scope.settleAccount = function () {
        window.location = "confirm";  // 跳至确认订单页面
    };
    $scope.$watch('cart',function(newvalue,oldvalue){
        angular.forEach(newvalue, function(item, key){
            if(item.quantity < 1 && item.quantity!==''){
                var returnKey = confirm("是否从购物车中删除该产品！");
                if(returnKey){
                    $scope.remove(id);
                }else{
                    item.quantity = oldvalue[key].quantity;
                };
            };
        });
    }, true);
}