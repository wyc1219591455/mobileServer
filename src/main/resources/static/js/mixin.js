var mixin = {
  methods: {
      goBack: function () {
          //console.log("回退");
          window.history.back(-1);
      },
      goTo: function (url) {
          if(url){
              if(location.origin == "https://ued.justech.com"){
                  var split_arr = url.split("/");
                  if(split_arr.length == 1){
                      // 同级别路由
                      var cur_url_split = location.href.split("/");
                      cur_url_split[cur_url_split.length-1] = split_arr[0];
                      window.location = cur_url_split.join("/");
                      return false;
                  }


                  url = url.replace("../", "/"); // 判断父级 去掉
                  url = url.replace("./", "/");
                  var url = url.startsWith("/") ? url : "/" + url; // 判断有没有 / 没有加上
                  window.location.href= location.origin + "/demo" + url;
                  return false;
              }
              window.location.href=url;
          }
      }
  }
};
