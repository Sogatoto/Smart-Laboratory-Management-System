<template>
  <el-scrollbar>
    <el-aside width="200px">
      <el-menu
        :default-active="route.fullPath"
        class="el-menu-vertical-demo"
        :router="true"
      >
        <template v-for="data in datalist" :key="data.path">
          <!-- 首页 -->
          <el-sub-menu
            :index="data.path"
            v-if="data.children.length && checkAuth(data.path)"
          >
            <template #title>
              <el-icon>
                <component :is="mapIcons[data.icon]"></component>
              </el-icon>
              <span>{{ data.title }}</span>
            </template>

            <template v-for="item in data.children" :key="item.path">
              <el-menu-item :index="item.path" v-if="checkAuth(item.path)">
                <el-icon>
                  <component :is="mapIcons[item.icon]"></component>
                </el-icon>
                <span>{{ item.title }}</span>
              </el-menu-item>
            </template>
          </el-sub-menu>

          <el-menu-item :index="data.path" v-else-if="checkAuth(data.path)">
            <el-icon>
              <component :is="mapIcons[data.icon]"></component>
            </el-icon>
            <span>{{ data.title }}</span>
          </el-menu-item>
        </template>
      </el-menu>
    </el-aside>
  </el-scrollbar>
</template>

<script setup>
import { onMounted, ref } from "vue";
import axios from "axios";
import { useRoute } from "vue-router";
import { useUserStore } from "../../store/useUserStore";
import {
  HomeFilled,
  Key,
  OfficeBuilding,
  UploadFilled,
  List,
  User,
} from "@element-plus/icons-vue";

onMounted(() => {
  getList();
});

const datalist = ref([]);
const route = useRoute();

const getList = async () => {
  var res = await axios.get("/adminapi/rights");
  // console.log(res.data);
  datalist.value = res.data;
};

//图标映射
const mapIcons = {
  HomeFilled,
  Key,
  OfficeBuilding,
  UploadFilled,
  List,
  User,
};

const { user } = useUserStore();
const checkAuth = (path) => {
  return user.role.rights.includes(path);
};
</script>


<style lang="scss" scoped>
.el-aside {
  height: 100vh;
  .el-menu {
    height: 100vh;
  }
}
:deep(span) {
  font-size: 15px;
  font-weight: 600;
}
</style>