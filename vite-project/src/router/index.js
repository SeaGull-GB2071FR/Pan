/*
 * @Author: Ry-yf 2960247871@qq.com
 * @Date: 2023-10-11 22:29:01
 * @LastEditors: Ry-yf 2960247871@qq.com
 * @LastEditTime: 2023-10-16 21:01:56
 * @FilePath: \easypan-front\vite-project\src\router\index.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'Login',
      component: () => import("@/views/Login.vue")
    },
  ]
})

export default router
