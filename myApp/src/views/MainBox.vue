<template>
  <div>
    <el-container>
      <SideMenu />
      <el-container direction="vertical">
        <TopHeader />
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script setup>
import { useRouter } from "vue-router";
import { useRouterStore } from "../store/useRouterStore";
import { useUserStore } from "../store/useUserStore";
import TopHeader from "../components/MainBox/TopHeader.vue";
import SideMenu from "../components/MainBox/SideMenu.vue";

const { changeUser } = useUserStore();
const { changeRouter } = useRouterStore();
const router = useRouter();
const handleExit = () => {
  changeUser({});

  changeRouter(false);

  //跳转到登录
  router.push("/login");
};
</script>