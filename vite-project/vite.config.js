/*
 * @Author: Ry-yf 2960247871@qq.com
 * @Date: 2023-10-11 22:29:01
 * @LastEditors: Ry-yf 2960247871@qq.com
 * @LastEditTime: 2023-10-14 20:36:13
 * @FilePath: \easypan-front\vite-project\vite.config.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  server:{
    port:1024,
    hmr:true,
    proxy:{
      '/api':{
        target:"http://localhost:7090",
        changeOrigin:true,
        pathRewrite:{
          "^api":"/api"
        }
      }
    }
  }
})
