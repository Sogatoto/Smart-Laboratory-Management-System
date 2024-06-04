<template>
    <div>
      <div>
        <el-table :data="filterTableData" style="width: 100%">
          <!-- <el-table-column prop="roleName" label="角色名称" width="180" /> -->
          <el-table-column label="名称">
            <template #default="scope">
              <el-link type="primary" @click="handleLocation(scope.row)">
                {{ scope.row.lab.title }}</el-link
              >
            </template>
          </el-table-column>
  
          <el-table-column label="预约人">
            <template #header>
              <el-input
                v-model="search"
                size="small"
                placeholder="请搜索用户名"
              />
            </template>
            <template #default="scope">
              <div>{{ scope.row.book_username }}</div>
            </template>
          </el-table-column>
          <el-table-column label="预约时间">
            <template #default="scope">
              <el-tag style="height: 40px; line-height: 20px">
                {{ moment(scope.row.book_time).format("YYYY-MM-DD") }}
                <br />
                {{ class_method(scope.row.book_class) }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column label="预约状态">
            <template #default="scope">
              <el-tag :type="StateColorType[scope.row.book_state]">
                {{ state_method(scope.row.book_state) }}
              </el-tag>
            </template>
          </el-table-column>
  
          <el-table-column label="操作">
            <template #default="scope">
                <el-popconfirm
                title="你确定要批准吗?"
                @confirm="handleUpdate(scope.row,APPROV)"
                cancel-button-text="取消"
                confirm-button-text="确定"
              >
                <template #reference>
                  <el-button type="primary" round>批准</el-button>
                </template>
              </el-popconfirm>
              <el-popconfirm
                title="你确定要驳回预约吗?"
                @confirm="handleUpdate(scope.row,REJECT)"
                cancel-button-text="取消"
                confirm-button-text="确定"
              >
                <template #reference>
                  <el-button type="danger" round>驳回</el-button>
                </template>
              </el-popconfirm>
            </template>
          </el-table-column>
        </el-table>
  
        <!-- 添加对话框 -->
        <el-dialog
          v-model="dialogVisible"
          title="实验室位置"
          fullscreen="true"
          v-if="dialogVisible"
        >
          <LabMap
            :x="currentItem.x"
            :y="currentItem.y"
            :t="currentItem.title"
          ></LabMap>
        </el-dialog>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from "vue";
  import axios from "axios";
  import LabMap from "../../components/lab-manage/LabMap.vue";
  import { useUserStore } from "../../store/useUserStore";
  import moment from "moment";
  import { computed } from "@vue/reactivity";
  import {
    ClassType,
    StateType,
    StateColorType,
    AUDIT,
    APPROV,
    REJECT
  } from "../../util/type";
  
  const tableData = ref([]);
  onMounted(() => {
    getList();
  });
  
  const search = ref("")
  
  const filterTableData = computed(() =>{
    return tableData.value.filter((item) =>
        !search.value ||
        item.book_username.toLowerCase().includes(search.value.toLowerCase()))
  })
  // const filterTableData = computed(()=>tableData.value)
  const { user } = useUserStore();
  
  const class_method = (value) => {
    return ClassType.find((item) => item.value === value).label;
  };
  const state_method = (value) => {
    return StateType.find((item) => item.value === value).label;
  };
  
  const getList = async () => {
    var res;
        res = await axios.get(`/adminapi/books?book_state=${AUDIT}`)
    tableData.value = res.data
  }

  const currentItem = ref({})
  const dialogVisible = ref(false)
  const handleLocation = (item) => {
    (dialogVisible.value = true), (currentItem.value = item.lab)
  };


  const handleUpdate = async ({id},state)=> {
    await axios.put(`/adminapi/books/${id}`,{
        book_state:state
    })
    await getList()
  }
  </script>
  
  <style lang="scss" scoped>
  .el-table {
    --el-table-border: none;
    --el-table-border-color: white;
  }
  .el-tag.el-tag--primary {
    --el-tag-text-color: #4d4d4d;
  }
  </style>