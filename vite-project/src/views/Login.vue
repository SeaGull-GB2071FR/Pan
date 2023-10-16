<!--
 * @Author: Ry-yf 2960247871@qq.com
 * @Date: 2023-10-16 19:46:39
 * @LastEditors: Ry-yf 2960247871@qq.com
 * @LastEditTime: 2023-10-16 21:01:01
 * @FilePath: \easypan-front\vite-project\src\views\Test.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
    <div class="login-body">
        
    </div>
</template>

<script setup>

</script>

<style lang=less scoped>
// calc
.login-body{
    height: calc(100vh);
    background-size: cover;
    background: url("../assets/login-bg.jpg");
    display: flex;
    .bg{
        flex: 1;
        background-size: cover;
        background-position: center;
        background-size: 800px;
        background-repeat: no-repeat;
        background: url("../assets/login-te.webp");
    }
    .login-panel{
        width: 430px;
        margin-right: 15%;
        margin-top: calc((100vh-500px)/2);
        .login-register{
            padding: 25px;
            background: #fff;
            border-radius: 5px;
            .login-title{
                text-align: center;
                font-size: 18px;
                font-weight: bold;
                margin-bottom: 20px;
            }
            .send-email-panel{
                display: flex;
                width: 100%;
                justify-content: space-between;
                .send-mail-btn{
                    margin-left: 5px;
                }
            }
            .remember-panel{
                width: 100%;
            }
            .no-account{
                width: 100%;
                display: flex;
                justify-content: space-between;
            }
            .op-btn{
                width: 100%;
            }
        }
    }
    .check-code-panel{
        width: 100%;
        display: flex;
        .check-code{
            margin-left: 5px;
            cursor: pointer;
        }
    }
    .login-btn-qq{
        margin-top: 20px;
        text-align: center;
        display: flex;
        align-items: center;
        justify-content: center;
        img{
            cursor: pointer;
            margin-right: 10px;
            width: 20px;
        }
    }

}
</style>