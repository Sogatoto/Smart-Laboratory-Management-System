<template>
    <vue-particles id="tsparticles" :options="particlesOption" />

  <div class="formContainer">
    <h3>智慧实验室管理平台</h3>
    <el-form
      ref="ruleFormRef"
      label-width="120px"
      :model="ruleForm"
      :rules="rules"
      class="ruleForm"
      status-icon
    >
      <!-- 表单用户名模块 -->
      <el-form-item label="用户名" prop="username">
        <el-input v-model="ruleForm.username" />
      </el-form-item>
      <!-- 表单密码模块 -->
      <el-form-item label="密码" prop="password">
        <el-input v-model="ruleForm.password" type="password" />
      </el-form-item>
      <!-- 按钮模块 -->
      <el-form-item>
        <el-button
          class="loginButton"
          type="primary"
          @click="submitForm(ruleFormRef)"
        >
          登录
        </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup>
import { useUserStore } from "../store/useUserStore";
import { useRouter } from "vue-router";
import { reactive, ref } from "vue";
import Particles from "@tsparticles/vue3";
import {particlesOption} from "../util/config"
import axios from "axios";
import { ElMessage } from "element-plus";



const particlesLoaded = async container => {
    console.log("Particles container loaded", container);
};

//ref获取表单对象
const ruleFormRef = ref();
//绑定数据
const ruleForm = reactive({
  username: "",
  password: "",
});
//校验规则
const rules = reactive({
  username: [{ required: true, message: "请输入用户名", trigger: "blur" }],
  password: [{ required: true, message: "请输入密码", trigger: "blur" }],
});

const { changeUser } = useUserStore();

const router = useRouter();

//登录回调
const submitForm = async (formEl) => {
  if (!formEl) return;
  await formEl.validate(async(valid, fields) => {
    if (valid) {
      const res = await axios.post(`/adminapi/users/login`,ruleForm)
      // console.log(res)
      let {code,data} = res.data
      if(code === 0){
        changeUser(data)
        router.push("/")
      }else{
        ElMessage.error('用户名或密码错误！')
      }
    } else {
      console.log("error submit!", fields);
    }
  });
};



</script>
<style lang="scss" scoped>
.formContainer {
  width: 600px;
  height: 300px;
  position: fixed;
  left: 50%;
  top: 50%;
  border-radius: 10px;
  transform: translate(-50%, -50%);
  color: #fff;
  margin-right: 40px;
  text-shadow: 2px 2px 5px black;
  text-align: center;
  .ruleForm {
    margin-top: 50px;
  }
  h3 {
    font-size: 40px;
  }
}
:deep(.el-form-item__label) {
  color: #fff;
  font-size: 16px;
  font-weight: 700;
}
.loginButton {
  margin-top: 8px;
  margin: 10px 0 0 150px;
}
:deep(.el-input__wrapper) {
  margin-right: 40px;
}
</style>