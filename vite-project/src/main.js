/*
 * @Author: Ry-yf 2960247871@qq.com
 * @Date: 2023-10-11 22:29:01
 * @LastEditors: Ry-yf 2960247871@qq.com
 * @LastEditTime: 2023-10-14 20:42:30
 * @FilePath: \easypan-front\vite-project\src\main.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */


import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
// 引入elementPlus
import ElementPlus from 'element-plus'
// import 'element-plus/disk/index.css'
// 图标
import './assets/icon/iconfont.css'
import './assets/base.scss'
// 引入cookies
import  VueCookies  from 'vue-cookies'

const app = createApp(App)

app.use(router)

app.mount('#app')
