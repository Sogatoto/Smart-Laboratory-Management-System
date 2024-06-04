import axios from 'axios'
import { ElLoading } from 'element-plus'
let loadingInstance1 = null
// Add a request interceptor
axios.interceptors.request.use(function (config) {

    const token = localStorage.getItem("token")
    config.headers.token = token
    
    //显示loading
    loadingInstance1 = ElLoading.service({fullscreen: true, text: '加载中...'})
    return config;



  }, function (error) {

    return Promise.reject(error);
  });

// Add a response interceptor
axios.interceptors.response.use(function (response) {
    const res = response.data
    // console.log(res?.data?.token)
    res?.data?.token && localStorage.setItem("token", res?.data?.token)

    //隐藏loading
    loadingInstance1 && loadingInstance1.close()
    return response;

  }, function (error) {

     //隐藏loading
     loadingInstance1 && loadingInstance1.close()
    const {status} = error.response
    if(status === 401){
        localStorage.removeItem("token")
        //重定向
        window.location.href = "#/login"

    }
    return Promise.reject(error);
  });