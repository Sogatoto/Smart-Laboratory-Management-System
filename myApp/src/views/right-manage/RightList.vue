<template>
  <div>
    <el-table :data="tableData" style="width: 100%" row-key="id">
      <el-table-column prop="title" label="权限名称" width="180" />
      <el-table-column label="图标" width="180">
        <template #default="scope">
          <div style="display: flex; align-items: center">
            <el-icon size="20px">
              <component :is="mapIcons[scope.row.icon]"></component>
            </el-icon>
            <span style="margin-left: 10px">{{ scope.row.date }}</span>
          </div>
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

    <el-dialog v-model="dialogVisible" title="更新权限">
      <el-form
        ref="updateFormRef"
        label-width="80px"
        :model="updateForm"
        :rules="rules"
        class="ruleForm"
        status-icon
      >
        <el-form-item label="权限名称" prop="title">
          <el-input v-model="updateForm.title" />
        </el-form-item>
      </el-form>

      <template #footer>
        <div class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="handleRules()"> 更新 </el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>


<script  setup>
import { ref, onMounted, reactive } from "vue";
import axios from "axios";
import {
  HomeFilled,
  Key,
  OfficeBuilding,
  UploadFilled,
  List,
  User,
} from "@element-plus/icons-vue";

//图标映射
const mapIcons = {
  HomeFilled,
  Key,
  OfficeBuilding,
  UploadFilled,
  List,
  User,
};

const tableData = ref([]);

onMounted(() => {
  // 获取权限列表数据
  getList();
});

const getList = async () => {
  var res = await axios.get("/adminapi/rights");
  tableData.value = res.data;
};

//更新对话框
const dialogVisible = ref(false);

const updateFormRef = ref();

const currentItem = ref({});
//绑定数据
const updateForm = reactive({
  title: "",
});
//校验规则
const rules = reactive({
  title: [{ required: true, message: "请输入权限名称", trigger: "blur" }],
});
//更新回调
const handleUpdata = (item) => {
  // console.log(item);
  currentItem.value = item;
  updateForm.title = item.title;
  dialogVisible.value = true;
  // console.log(updateForm.title);
};

// 更新按钮校验
const handleRules = () => {
  updateFormRef.value.validate(async (valid, fields) => {
    if (valid) {
      axios.put(`/adminapi/rights/${currentItem.value.id}`, updateForm);
      dialogVisible.value = false;
      await getList();
    } else {
    }
  });
};

// put /adminapi/rights/3 {title:"",icon:""}

//删除权限
const handleDelete = async ({ id }) => {
  // console.log(id)
  await axios.delete(`/adminapi/rights/${id}`);
  await getList();
};
</script>




<style lang="scss" scoped>
.el-table {
  --el-table-border: none;
  --el-table-border-color: white;
}
</style>