//实验室类型
const LabType = [
    { label: "机械实验室", value: 1 },
    { label: "生物科学实验室", value: 2 },
    { label: "药学实验室", value: 3 },
    { label: "计算机实验室", value: 4 },
    { label: "大数据实验室", value: 5 },
    { label: "航天飞行器实验室", value: 6 },
    { label: "3D打印实验室", value: 7 },
    { label: "电子设计实验室", value: 8 },
    { label: "经济管理实验室", value: 9 },
    { label: "电气实验室", value: 10 },
    { label: "土木工程实验室", value: 11 },
    { label: "机械自动化实验室", value: 12 },
  ];
  //所属学院类型
  const CollegeType = [
    { label: "计算机学院", value: 1 },
    { label: "中医药学院", value: 2 },
    { label: "智能制造学院", value: 3 },
    { label: "经济管理学院", value: 4 },
    { label: "生命科学学院", value: 5 },
    { label: "航天航空学院", value: 6 },
    { label: "材料科学学院", value: 7 },
    { label: "机械智能学院", value: 8 },
    { label: "建筑学院", value: 9 },
    { label: "电气学院", value: 10 },
  ];

  const LabColorType = {
    1: "#FBEAFF",
    2: "#FFE1F9",
    3: "#FFD9DE",
    4: "#FFD9B4",
    5: "#FFE589",
    6: "#F9F871",
    7: "#CFCEEB",
    8: "#C1FCF5",
    9: "#FFF7D0",
    10: "#FFFADE",
    11: "#CDF8F2",
    12: "#D0E7FF"
  }

  const CollegesColorType ={
    1: "#80E0FF",
    2: "#61ECF6",
    3: "#63F6DD",
    4: "#8AFCB9",
    5: "#BFFD91",
    6: "#F88F6F",
    7: "#87F9BB",
    8: "#4DBF85",
    9: "#FFC89F",
    10: "#00F4F3",
  }

  const ClassType = [
    { label: "第一节课", value: 1 },
    { label: "第二节课", value: 2 },
    { label: "第三节课", value: 3 },
    { label: "第四节课", value: 4 },
    { label: "第五节课", value: 5 },
    { label: "第六节课", value: 6 },
  ]

  const StateType = [
    { label: "审核中", value: 0 },
    { label: "已批准", value: 1 },
    { label: "被驳回", value: 2 }
  ]

  const StateColorType = ["warning","success","danger"]

  const ADMIN = 1;
  const TEACHER = 2;

  const AUDIT = 0;
  const APPROV = 1;
  const REJECT = 2;
  export { 
    LabType,
    CollegeType, 
    LabColorType, 
    CollegesColorType, 
    ClassType, 
    StateType, 
    StateColorType, 
    ADMIN, 
    TEACHER,
    AUDIT,
    APPROV,
    REJECT
   };