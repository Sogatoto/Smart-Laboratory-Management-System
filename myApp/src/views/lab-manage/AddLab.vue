<template>
  <div>
    <div
      style="min-height: 810px; justify-content: center; position: relative"
      id="map"
    />
    <!-- 添加对话框 -->
    <el-dialog v-model="dialogVisible" title="添加实验室">
      <el-form
        ref="addFormRef"
        label-width="120px"
        :model="addForm"
        :rules="rules"
        class="ruleForm"
        status-icon
      >
        <el-form-item label="实验室名称" prop="title">
          <el-input v-model="addForm.title" />
        </el-form-item>
        <el-form-item label="容纳人数" prop="capacity">
          <el-input v-model="addForm.capacity" type="number" />
        </el-form-item>
        <el-form-item label="实验室类型" prop="lab_type">
          <el-select
            v-model="addForm.lab_type"
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
            v-model="addForm.college_type"
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
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="handleAddConfirm()">
            确定
          </el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { onMounted, ref, reactive } from "vue";
import { ImageLayer, PointLayer, Scene, Popup } from "@antv/l7";
import { Map } from "@antv/l7-maps";
import axios from "axios";
import { LabType, CollegeType } from "../../util/type";
let scene;
let popup;
onMounted(() => {
  // 初始化地图
  scene = new Scene({
    id: "map",
    map: new Map({
      center: [500, 500],
      zoom: 4,
      version: "SIMPLE",
      mapSize: 1000,
      maxZoom: 5,
      minZoom: 2,
      pitchEnabled: false,
      rotateEnabled: false,
    }),
  });
  scene.setBgColor("rgb(94, 182, 140)");

  const imagelayer = new ImageLayer({}).source("/map.jpg", {
    parser: {
      type: "image",
      extent: [360, 400, 640, 600],
    },
  });
  imagelayer.on("click", (e) => {
    let { lng, lat } = e.lngLat;
    popup = new Popup({
      offsets: [0, 0],
      closeButton: false,
    })
      .setLnglat(e.lngLat)
      .setHTML(
        `<button class="el-button el-button--primary"
        onclick="add_Popup(${lng},${lat})">选择此位置</button>`
      );
    scene.addPopup(popup);
  });

  scene.on("loaded", () => {
    // fetch(
    //   "https://gw.alipayobjects.com/os/bmw-prod/7dc0d454-fabc-4461-a5d5-d404dadb49a9.json"
    // )
    //   .then((res) => res.json())
    //   .then((data) => {
    //     const textlayer = new PointLayer({ zIndex: 2 })
    //       .source(data, {
    //         parser: {
    //           type: "json",
    //           x: "x",
    //           y: "y",
    //         },
    //       })
    //       .shape("t", "text")
    //       .size(12)
    //       .active({
    //         color: "#00f",
    //         mix: 0.9,
    //       })
    //       .color("rgb(86, 156, 214)")
    //       .style({
    //         textAnchor: "center", // 文本相对锚点的位置 center|left|right|top|bottom|top-left
    //         spacing: 2, // 字符间距
    //         fontWeight: "800",
    //         padding: [1, 1], // 文本包围盒 padding [水平，垂直]，影响碰撞检测结果，避免相邻文本靠的太近
    //         stroke: "#ffffff", // 描边颜色
    //         strokeWidth: 2, // 描边宽度
    //         textAllowOverlap: true,
    //       });
    //     scene.addLayer(textlayer);
    //   });
    getList()
    scene.addLayer(imagelayer);
  });
});

window.add_Popup = (x, y) => {
  dialogVisible.value = true;

  //将popup隐藏
  popup.close();
  //x,y
  addForm.x = x * 2.8 + 500;
  addForm.y = y * 2.8 + 500;
};
//添加
const dialogVisible = ref(false);
const addFormRef = ref();
const addForm = reactive({
  title: "",
  capacity: "",
  lab_type: "",
  college_type: "",
  x: 0,
  y: 0,
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


const handleAddConfirm = () => {
  addFormRef.value.validate(async(valid) => {
    if (valid) {
      dialogVisible.value = false
      //发 axios.post
      await axios.post("/adminapi/labs",addForm)
      //添加文字标注
      addTextLayer(
      [
        {
          "x": addForm.x,
          "y": addForm.y,
          "t": addForm.title
        },
      ]
      )
    }
  });
};

const getList = async () => {
  var res = await axios.get("/adminapi/labs")

  var list = res.data.map(item=>({
    x: item.x,
    y: item.y,
    t: item.title
  }))
  addTextLayer(list)
}

const addTextLayer = (data) => {
  const textlayer = new PointLayer({ zIndex: 2 })
    .source(
      data,
      {
        parser: {
          type: "json",
          x: "x",
          y: "y",
        },
      }
    )
    .shape("t", "text")
    .size(14)
    .active({
      color: "#00f",
      mix: 0.9,
    })
    .color("rgb(102, 131, 169)")
    .style({
      textAnchor: "center", // 文本相对锚点的位置 center|left|right|top|bottom|top-left
      spacing: 2, // 字符间距
      fontWeight: "800",
      padding: [1, 1], // 文本包围盒 padding [水平，垂直]，影响碰撞检测结果，避免相邻文本靠的太近
      stroke: "#ffffff", // 描边颜色
      strokeWidth: 2, // 描边宽度
      textAllowOverlap: true,
    });
  scene.addLayer(textlayer);
};


</script>