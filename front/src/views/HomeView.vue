<template>
  <el-container style="min-height: 100vh">
      <el-aside :width="sideWidth" style="background-color: rgb(238, 241, 246);box-shadow: 2px 0 6px rgb(0 21 41 / 35%)">
        <el-menu :default-openeds="['1', '3']" style="min-height:100%;overflow-x:hidden"
                  background-color="rgb(48,65,86)"
                  text-color="#fff"
                  active-text-color="#ffd04b"
                  :collapse-transition="false"
                  :collapse="isCollapse"
        >
          <div style="height: 60px;line-height: 60px;text-align: center">
            <img src="../assets/logo.png" alt="" style="width: 20px;position: relative;top: 5px;margin-right: 5px">
            <b style="color: aliceblue" v-show="!isCollapse">后台管理系统</b>
          </div>
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-message"></i>
              <span slot="title">导航一</span>
            </template>
            <el-menu-item-group title="分组2">
              <el-menu-item index="1-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="1-4">
              <template slot="title">选项4</template>
              <el-menu-item index="1-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-menu"></i>
              <span slot="title">导航二</span>
            </template>

            <el-submenu index="2-4">
              <template slot="title">选项4</template>
              <el-menu-item index="2-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title">
              <i class="el-icon-setting"></i>
              <span slot="title">导航三</span>
            </template>
            <el-submenu index="3-4">
              <template slot="title">选项4</template>
              <el-menu-item index="3-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
        </el-menu>
      </el-aside>

      <el-container>
        <el-header style="font-size: 12px;border-bottom: 1px solid #cccccc;line-height: 60px;display: flex">
          <div style="flex: 1;font-size: 18px">
            <span :class="collapseBtnClass" style="cursor: pointer" @click="collapse"></span>
          </div>
          <el-dropdown style="width: 70px;cursor: pointer">
            <span>王小虎</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>个人信息</el-dropdown-item>
              <el-dropdown-item>退出</el-dropdown-item>
              <el-dropdown-item>待定</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>

        </el-header>

        <el-main>

          <div style="margin: 10px 0">
            <el-input style="width: 200px" v-model:value="username" placeholder="请输入名称" suffix-icon="el-icon-search" v-model="name"></el-input>
<!--            <el-input style="width: 200px" placeholder="请输入邮箱" suffix-icon="el-icon-message" class="ml-5"></el-input>-->
<!--            <el-input style="width: 200px" placeholder="请输入地址" suffix-icon="el-icon-position" class="ml-5"></el-input>-->
            <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
          </div>

          <div style="margin: 10px 0">
            <el-button type="primary">新增<li class="el-icon-circle-plus-outline"></li></el-button>
            <el-button type="danger">批量删除<li class="el-icon-remove-outline"></li></el-button>
            <el-button type="primary">导入<li class="el-icon-bottom"></li></el-button>
            <el-button type="primary">导出<li class="el-icon-top"></li></el-button>
          </div>

          <el-table :data="tableData" border stripe header-cell-class-name="headerBg" >
            <el-table-column prop="uid" label="ID" width="140">
            </el-table-column>
            <el-table-column prop="name" label="姓名" width="120">
            </el-table-column>
            <el-table-column prop="age" label="年龄">
            </el-table-column>
            <el-table-column prop="email" label="邮箱">
            </el-table-column>
            <el-table-column label="操作">
              <el-button type="success">编辑<li class="el-icon-edit"></li></el-button>
              <el-button type="danger">删除<li class="el-icon-remove-outline"></li></el-button>
            </el-table-column>
          </el-table>
          <div style="padding: 10px 0">
            <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="pageNum"
                :page-sizes="[2, 5, 10, 20]"
                :page-size="pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
              //这三个是从上面删除的
              <!--                @size-change="handleSizeChange"-->
              <!--                @current-change="handleCurrentChange"-->
              <!--                :current-page="currentPage4"-->
            </el-pagination>
          </div>
        </el-main>
      </el-container>
    </el-container>
</template>

<script>
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  },
  data() {
    return {
      tableData: [],
      total:0, //分页的总数
      collapseBtnClass:'el-icon-s-fold',
      isCollapse:false,
      sideWidth:200,
      username:"",
      pageNum:1,
      pageSize:2,
      name:"", //搜索框的绑定属性
    }
  },
  created() {
    //请求分页查询数据
    this.load()
  },
  methods:{
    collapse(){//点击收缩按钮触发
      this.isCollapse = !this.isCollapse
      if(this.sideWidth){//收缩
        this.sideWidth=64
        this.collapseBtnClass='el-icon-s-unfold'
      }else {//展示
        this.sideWidth=200
        this.collapseBtnClass='el-icon-s-fold'
      }
    },
    load(){
      fetch("http://localhost:9090/user/page?pageNum="+this.pageNum+"&pageSize="+this.pageSize+"&name="+this.name).then(res=>res.json()).then(res=>{
        console.log(res)
        this.tableData=res.data
        this.total=res.total
      })
    },
    handleSizeChange(pageSize){
      this.pageSize=pageSize
      this.load()
    },
    handleCurrentChange(pageNum){
      this.pageNum=pageNum
      this.load()
    }
  }
}
</script>
