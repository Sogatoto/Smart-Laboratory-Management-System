<template>
  <div>
    <el-table :data="tableData" style="width: 100%">
      <!-- <el-table-column prop="roleName" label="角色名称" width="180" /> -->
      <el-table-column label="实验室名称">
        <template #default="scope">
          <el-link type="primary" @click="handleLocation(scope.row)">{{
            scope.row.title
          }}</el-link>
        </template>
      </el-table-column>

      <el-table-column prop="capacity" label="容纳人数" />

      <el-table-column label="实验室类型">
        <template #default="scope">
          <el-tag :color="LabColorType[scope.row.lab_type]">{{
            lab_method(scope.row.lab_type)
          }}</el-tag>
        </template>
      </el-table-column>

      <el-table-column label="所属学院">
        <template #default="scope">
          <el-tag :color="CollegesColorType[scope.row.college_type]">{{
            college_method(scope.row.college_type)
          }}</el-tag>
        </template>
      </el-table-column>

      

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
                >删除</el-button
              >
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加对话框 -->
    <el-dialog
      v-model="dialogVisible"
      title="实验室位置"
      fullscreen=true
      v-if="dialogVisible"
    >
      <LabMap
        :x="currentItem.x"
        :y="currentItem.y"
        :t="currentItem.title"
      ></LabMap>
    </el-dialog>

    <!-- 添加对话框 -->
    <el-dialog v-model="dialogUpdateVisible" title="更新实验室">
      <el-form
        ref="updateFormRef"
        label-width="120px"
        :model="updateForm"
        :rules="rules"
        class="ruleForm"
        status-icon
      >
        <el-form-item label="实验室名称" prop="title">
          <el-input v-model="updateForm.title" />
        </el-form-item>
        <el-form-item label="容纳人数" prop="capacity">
          <el-input v-model.number="updateForm.capacity" type="number" />
        </el-form-item>
        <el-form-item label="实验室类型" prop="lab_type">
          <el-select
            v-model="updateForm.lab_type"
            placeholder="请选择实验室类型"
            style="width: 180px"
          >
            <el-option
              v-for="item in LabType"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-form-item>

        <el-form-item label="所属学院" prop="college_type">
          <el-select
            v-model="updateForm.college_type"
            placeholder="请选择学院"
            style="width: 180px"
          >
            <el-option
              v-for="item in CollegeType"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="dialogUpdateVisible = false">取消</el-button>
          <el-button type="primary" @click="handleUpdateConfirm()">
            确定
          </el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, onMounted, reactive } from "vue";
import axios from "axios";
import LabMap from "../../components/lab-manage/LabMap.vue";
import {
  CollegeType,
  LabType,
  LabColorType,
  CollegesColorType,
} from "../../util/type";


const tableData = ref([]);

onMounted(() => {
  getList();
});

const getList = async () => {
  var res = await axios.get("/adminapi/labs");
  tableData.value = res.data;
};

const college_method = (value) => {
  return CollegeType.find((item) => item.value === value).label
  
};

const lab_method = (value) => {
  console.log(LabType.find((item) => item.value == value).label,'liwei')
  return LabType.find((item) => item.value == value).label
};

//预览实验室功能
const currentItem = ref({});
const dialogVisible = ref(false);
const handleLocation = (item) => {
  dialogVisible.value = true;
  currentItem.value = item;
};

//添加
const dialogUpdateVisible = ref(false);
const updateFormRef = ref();
const updateForm = reactive({
  title: "",
  capacity: "",
  lab_type: "",
  college_type: ""
});
//校验规则
const rules = reactive({
  title: [{ required: true, message: "请输入实验室名称", trigger: "blur" }],
  capacity: [{ required: true, message: "请输入容纳人数", trigger: "blur" }],
  lab_type: [{ required: true, message: "请选择实验室类型", trigger: "blur" }],
  college_type: [
    { required: true, message: "请选择所属学院", trigger: "blur" },
  ],
});

const handleUpdata = (item) => {
  dialogUpdateVisible.value = true
  updateForm.title = item.title
  updateForm.capacity = item.capacity
  updateForm.lab_type = item.lab_type
  updateForm.college_type = item.college_type
  currentItem.value = item
}

const handleUpdateConfirm = () => {
  updateFormRef.value.validate(async(valid) => {
    if(valid){
      dialogUpdateVisible.value = false
      await axios.put(`/adminapi/labs/${currentItem.value.id}`,updateForm)
      await getList()
    }
  })
}

//删除功能
const handleDelete = async({id}) => {
  await axios.delete(`/adminapi/labs/${id}`)
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