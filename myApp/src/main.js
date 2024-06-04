import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import router from './router'
import {createPinia} from 'pinia'
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'
//引入element-plus组件库
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import zhCn from 'element-plus/es/locale/lang/zh-cn'
//particles粒子组件
import Particles from "@tsparticles/vue3";
import {loadFull} from "tsparticles";
//导入axios配置
import'./util/axios.config'

const pinia = createPinia()

pinia.use(piniaPluginPersistedstate)

createApp(App)
    .use(router)
    .use(pinia)
    .use(Particles, {
        init: async engine => {
            await loadFull(engine); 
        },
    })
    .use(ElementPlus,{
        locale: zhCn
    })
    .mount('#app')
