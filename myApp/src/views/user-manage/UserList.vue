<template>
  <div>
    <el-button type="primary" @click="handleAdd()">添加用户</el-button>
    <el-table :data="tableData" style="width: 100%">
      <!-- <el-table-column prop="roleName" label="角色名称" width="180" /> -->
      <el-table-column label="角色名称">
        <template #default="scope">
          <div>{{ scope.row.role.roleName }}</div>
        </template>
      </el-table-column>
      <el-table-column prop="username" label="用户名" width="180" />

      <el-table-column label="操作">
        <template #default="scope">
          <el-button
            type="primary"
            round
            @click="handleUpdata(scope.row)"
            :disabled="scope.row.is_default ? true : false"
            >更新</el-button
          >
          <el-popconfirm
            title="你确定要删除吗?"
            @confirm="handleDelete(scope.row)"
            cancel-button-text="取消"
            confirm-button-text="确定"
          >
            <template #reference>
              <el-button
                type="danger"
                round
                :disabled="scope.row.is_default ? true : false"
                >删除</el-button
              >
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加对话框 -->
    <el-dialog v-model="dialogVisible" title="添加用户">
      <el-form
        ref="addFormRef"
        label-width="80px"
        :model="addForm"
        :rules="rules"
        class="ruleForm"
        status-icon
      >
        <el-form-item label="用户名" prop="username">
          <el-input v-model="addForm.username" />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="addForm.password" />
        </el-form-item>
        <el-form-item label="角色" prop="roleId">
          <el-select
            v-model="addForm.roleId"
            placeholder="请选择角色"
            style="width: 180px"
          >
            <el-option
              v-for="item in roleList"
              :key="item.id"
              :label="item.roleName"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="handleAddRules()"> 确定 </el-button>
        </div>
      </template>
    </el-dialog>
    <!-- 更新对话框 -->
    <el-dialog v-model="dialogUpdateVisible" title="更新用户">
      <el-form
        ref="updateFormRef"
        label-width="80px"
        :model="updateForm"
        :rules="rules"
        class="ruleForm"
        status-icon
      >
        <el-form-item label="用户名" prop="username">
          <el-input v-model="updateForm.username" />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="updateForm.password" />
        </el-form-item>
        <el-form-item label="角色" prop="roleId">
          <el-select
            v-model="updateForm.roleId"
            placeholder="请选择角色"
            style="width: 180px"
          >
            <el-option
              v-for="item in roleList"
              :key="item.id"
              :label="item.roleName"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="dialogUpdateVisible = false">取消</el-button>
          <el-button type="primary" @click="handleUpdateConfirm()"> 更新 </el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, onMounted, reactive } from "vue";
import axios from "axios";

const tableData = ref([]);
const addFormRef = ref();
const dialogVisible = ref(false);
const roleList = ref([]);

onMounted(() => {
  // 获取权限列表数据
  getList();
  getRoleList();
});

const getList = async () => {
  var res = await axios.get("/adminapi/users");
  tableData.value = res.data;
};
const getRoleList = async () => {
  var res = await axios.get("/adminapi/roles");
  roleList.value = res.data;
};

const handleAdd = () => {
  dialogVisible.value = true;
};

const addForm = reactive({
  username: "",
  password: "",
  roleId: "",
});
//校验规则
const rules = reactive({
  username: [{ required: true, message: "请输入用户名", trigger: "blur" }],
  password: [{ required: true, message: "请输入密码", trigger: "blur" }],
  roleId: [{ required: true, message: "请选择角色", trigger: "blur" }],
});

const handleAddRules = () => {
  addFormRef.value.validate(async (valid) => {
    if (valid) {
      dialogVisible.value = false;
      await axios.post("/adminapi/users", addForm);
      await getList();

      addForm.username = "";
      addForm.password = "";
      addForm.roleId = "";
    } else {
      console.log(valid);
    }
  });
};

const dialogUpdateVisible = ref(false);
const updateFormRef = ref();
const currentItem = ref({});
const updateForm = reactive({
  username: "",
  password: "",
  roleId: "",
});

//更新
const handleUpdata = (item) => {
  //更新对话框显示
  dialogUpdateVisible.value = true;
  //获取当前行数据
  updateForm.username = item.username;
  updateForm.password = item.password;
  updateForm.roleId = item.roleId;
  //currentItem id
  currentItem.value = item.id;
};

//更新按钮
const handleUpdateConfirm = async () => {
    dialogUpdateVisible.value = false
    await axios.put(`/adminapi/users/${currentItem.value}`,updateForm)
    await getList()
}

//删除
const handleDelete = async ({id}) => {
    await axios.delete(`/adminapi/users/${id}`)
    await getList()
}
</script>

<style lang="scss" scoped>
.el-table {
  --el-table-border: none;
  --el-table-border-color: white;
}
</style>