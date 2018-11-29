//app.js
App({
  onLaunch: function () {
    console.log('App Launch')
    //调用API从本地缓存中获取数据
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)
  },
  getUserInfo: function (cb) {
    console.log("准备登陆");
    var that = this
    if (this.globalData.userInfo) {
      typeof cb == "function" && cb(this.globalData.userInfo)
    } else {
      console.log("准备调用登陆接口");
      //调用登录接口
      wx.login({
        success: function () {
          console.log("登陆--成功");          
          wx.getUserInfo({
            success: function (res) {
              console.log("getUserInfo--成功");
              that.globalData.userInfo = res.userInfo;
              typeof cb == "function" && cb(that.globalData.userInfo)
            },
            fail:function(res){
              console.log("getUserInfo--失败");
              console.log(res); 
            }
          })
        }
      })
    }
  },
  onShow: function () {
    console.log('App Show')
  },
  onHide: function () {
    console.log('App Hide')
  },
  globalData:{
    userInfo:null
  }
})