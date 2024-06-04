<template>
  <div>
    <el-table :data="tableData" style="width: 100%">
      <!-- <el-table-column prop="roleName" label="角色名称" width="180" /> -->
      <el-table-column label="角色名称">
        <template #default="scope">
          <el-popover
            placement="right"
            title="权限详情"
            :width="200"
            trigger="hover"
            @before-enter="handleHover(scope.row)"
          >
            <template #reference>
              <el-button class="m-2">{{ scope.row.roleName }}</el-button>
            </template>
            <template #default>
              <el-tree
                :data="rightlist"
                :props="defaultProps"
                :default-expand-all="true"
                :render-content="renderContent"
              />
            </template>
          </el-popover>
        </template>
      </el-table-column>

      <el-table-column label="操作">
        <template #default="scope">
          <el-button type="primary" round @click="handleUpdata(scope.row)"
            >更新</el-button
          >
          <el-popconfirm
            title="你确定要删除吗?"
            @confirm="handleDelete(scope.row)"
            cancel-button-text="取消"
            confirm-button-text="确定"
          >
            <template #reference>
              <el-button type="danger" round>删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog v-model="dialogVisible" title="更新角色">
      <el-form
        ref="updateFormRef"
        label-width="80px"
        :model="updateForm"
        :rules="rules"
        class="ruleForm"
        status-icon
      >
        <el-form-item label="角色名称" prop="roleName">
          <el-input v-model="updateForm.roleName" />
        </el-form-item>
        <el-form-item label="角色权限" prop="rights">
          <el-tree
            :data="rightlist"
            :props="defaultProps"
            :default-expand-all="true"
            show-checkbox
            node-key="path"
            ref="treeRef"
            :check-strictly="true"
          />
        </el-form-item>
      </el-form>

      <template #footer>
        <div class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="handleConfirm()"> 更新 </el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, onMounted, reactive, nextTick } from "vue";
import axios from "axios";

const tableData = ref([]);
const rightlist = ref([]);

onMounted(() => {
  // 获取权限列表数据
  getList();
  getRightList();
});

const defaultProps = {
  children: "children",
  label: "title",
};

const getList = async () => {
  var res = await axios.get("/adminapi/roles");
  console.log(res.data,'asfdas');
  tableData.value = res.data;
};

const getRightList = async () => {
  var res = await axios.get("/adminapi/rights");
  rightlist.value = res.data;
};

const currentRights = ref([]);

const handleHover = ({ rights }) => {
  currentRights.value = rights
};

//自定义tree节点回调函数
const renderContent = (h, { node, data, store }) => {
  return h(
    "span",
    {
      class: `custom-tree-node ${
        currentRights.value.includes(data.path) ? "active" : ""
      }`,
    },
    h("span", null, node.label)
  );
};

//更新回调
const handleUpdata = (item) => {
  // console.log(item,'asdfas');
  dialogVisible.value = true
  updateForm.roleName = item.roleName
  currentItem.value = item
  

  //设置rights给tree
  nextTick(() => {
    treeRef.value.setCheckedKeys(JSON.parse(item.rights));
  })
  

};
//更新对话框
const dialogVisible = ref(false);

const updateFormRef = ref();

const currentItem = ref({});
//绑定数据
const updateForm = reactive({
  roleName: "",
});
//校验规则
const rules = reactive({
  roleName: [{ required: true, message: "请输入角色名称", trigger: "blur" }],
});

//更新
const treeRef = ref()

//弹出框确定方法
const handleConfirm = async () => {
     await axios.put(`/adminapi/roles/${currentItem.value.id}`,{
      roleName:updateForm.roleName,
      rights:JSON.stringify(treeRef.value.getCheckedKeys()),
     }) 

     await getList()
     dialogVisible.value = false
}


//删除
const handleDelete = async ({id})=>{
  await axios.delete(`/adminapi/roles/${id}`)

  await getList()
}


</script>


<style lang="scss" scoped>
.el-table {
  --el-table-border: none;
  --el-table-border-color: white;
}
:deep(.active) { 
  color: #409eff;
}
</style>