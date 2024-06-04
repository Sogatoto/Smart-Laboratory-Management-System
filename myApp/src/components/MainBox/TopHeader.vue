<template>
    <el-header>
            <div class="left">
                <span style="margin-left: 10px;">智慧实验室管理系统</span>
            </div>
            <div class="right">
                <span>欢迎 {{ user.username }} 回来！</span>
                <el-dropdown>
                    <el-avatar :size="40" :src="circleUrl" />
                    <template #dropdown>
                        <el-dropdown-menu>
                            <el-dropdown-item >{{ user.role.roleName }}</el-dropdown-item>
                            <el-dropdown-item @click="handleExit">退出</el-dropdown-item>
                        </el-dropdown-menu>
                    </template>
                </el-dropdown>
            </div>
        </el-header>
</template>

<script setup>
import {useRouterStore} from "../../store/useRouterStore"
import {useUserStore} from "../../store/useUserStore"
import {useRouter} from "vue-router"
const {changeUser, user} = useUserStore()
const {changeRouter} = useRouterStore()
const router = useRouter()

// const circleUrl = '/avatar.png'
const circleUrl = new URL ('../../assets/avatar.png', import.meta.url).href



//点击退出
const handleExit = () => {
    changeUser({})

    changeRouter(false)

    router.push("/login")
}

</script>

<style lang="scss" scoped>
.el-header{
    background-color:#6683a9 ;
    color: white;
    width: 100%;
    height: 60px;
    line-height: 60px;
    display: flex;
    justify-content: space-between;
    align-items: center;
}
.left,.right{
    display: flex;
}
.left{
    i{
        margin: auto;
    }
    span{
        font-size: 25px;
    }
}
.right{
    .el-dropdown{
        margin: auto;
    }
    span{
        font-size: 20px;
    }
}
</style>