<template>
    <div class="login-wrap">
        <div class="ms-title">后台管理系统</div>
        <div class="ms-login">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="0px" class="demo-ruleForm">
                <el-form-item prop="username">
                    <el-input v-model="ruleForm.username" placeholder="username"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input type="password" placeholder="password" v-model="ruleForm.password" @keyup.enter.native="submitForm('ruleForm')"></el-input>
                </el-form-item>
                <div class="login-btn">
                    <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
                </div>
				<!--
				<div class="login-btn">
					<el-button type="primary" @click="isLogin()">查看登录</el-button>
				</div>
				-->
                <p style="font-size:12px;line-height:30px;color:#999;">Tips : 用户名和密码随便填。</p>
            </el-form>
        </div>
    </div>
</template>

<script>
    export default {
        data: function(){
            return {
                ruleForm: {
                    username: 'admin',
                    password: '123456'
                },
                rules: {
                    username: [
                        { required: true, message: '请输入用户名', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '请输入密码', trigger: 'blur' }
                    ]
                }
            }
        },
        methods: {
            submitForm(formName) {
				let that = this;
                this.$refs[formName].validate((valid) => {
					localStorage.setItem('ms_username', 'admin');
					var params = new FormData();
					params.append('username',this.ruleForm.username);
					params.append('password',this.ruleForm.password);
					if (valid) {
						console.info(this);
						var that = this;
						this.axios.post('/lygsbxt/login',params)
						.then(function(response){
							console.info(response.data);
							if(response.data.code == '200') {
                                //loginCallBack(response.data);
                                var role = response.data.data.role;
                                const routes = [
                                {
                                    path: '/login',
                                    component: resolve => require(['../../components/page/Login.vue'], resolve)
                                },
                                {
                                    path: '/404',
                                    component: resolve => require(['../../components/page/404.vue'], resolve)
                                },
                                {
                                    path: '/403',
                                    component: resolve => require(['../../components/page/403.vue'], resolve)
                                },
                                {
                                    path: '*',
                                    redirect: '/404'
                                }];
                                const dashboard = {
                                    path: '/',
                                    component: resolve => require(['../../components/common/Home.vue'], resolve),
                                    meta: { title: '自述文件' }};
                                const moren = {
                                    path: '/'
                                };
                                if(role == 1) {
                                    moren.redirect = '/maintainorder';
                                    dashboard.children = [
                                        {
                                            path: '/maintainuser',
                                            component: resolve => require(['../../components/page/maintainuser.vue'], resolve),
                                            meta: { title: '维修人员管理' }
                                        },
                                        {
                                            path: '/officeuser',
                                            component: resolve => require(['../../components/page/officeuser.vue'], resolve),
                                            meta: { title: '派单人员管理' }
                                        },
                                        {
                                            path: '/maintainorder',
                                            component: resolve => require(['../../components/page/maintainorder.vue'], resolve),
                                            meta: { title: '维修任务' }
                                        }
                                    ];
                                    routes.push(moren);
                                    routes.push(dashboard);
                                    //that.$router.addRoutes(routes);
                                    that.$router.routes = routes;
                                }
                                if(role == 2) {
                                    moren.redirect = '/building';
                                    dashboard.children = [
                                        {
                                            path: '/building',
                                            component: resolve => require(['../../components/page/building.vue'], resolve),
                                            meta: { title: '服务管理' }
                                        },
                                        {
                                            path: '/buildingtype',
                                            component: resolve => require(['../../components/page/buildingtype.vue'], resolve),
                                            meta: { title: '服务分类管理' }
                                        }
                                    ];
                                    routes.push(moren);
                                    routes.push(dashboard);
                                    //that.$router.addRoutes(routes);
                                    that.$router.routes = routes;
                                }
                                if(role == 3) {
                                    moren.redirect = '/maintainorder';
                                    dashboard.children = [
                                        {
                                            path: '/dashboard',
                                            component: resolve => require(['../../components/page/Dashboard.vue'], resolve),
                                            meta: { title: '系统首页' }
                                        },
                                        {
                                            path: '/building',
                                            component: resolve => require(['../../components/page/building.vue'], resolve),
                                            meta: { title: '服务管理' }
                                        },
                                        {
                                            path: '/maintainuser',
                                            component: resolve => require(['../../components/page/maintainuser.vue'], resolve),
                                            meta: { title: '维修人员管理' }
                                        },
                                        {
                                            path: '/officeuser',
                                            component: resolve => require(['../../components/page/officeuser.vue'], resolve),
                                            meta: { title: '派单人员管理' }
                                        },
                                        {
                                            path: '/buildingtype',
                                            component: resolve => require(['../../components/page/buildingtype.vue'], resolve),
                                            meta: { title: '服务分类管理' }
                                        },
                                        {
                                            path: '/maintainorder',
                                            component: resolve => require(['../../components/page/maintainorder.vue'], resolve),
                                            meta: { title: '维修任务' }
                                        },
                                        {
                                            path: '/manageuser',
                                            component: resolve => require(['../../components/page/manageuser.vue'], resolve),
                                            meta: { title: '管理员管理' }
                                        }
                                    ];
                                    routes.push(moren);
                                    routes.push(dashboard);
                                    //that.$router.addRoutes(routes);
                                    that.$router.routes = routes;
                                }
                                localStorage.setItem('ms_username',that.ruleForm.username);
                                
								that.$router.push('/');
							} else {
								that.$message.error("用户名或密码错误，请重新输入！");
							}
							
						}).catch(function(error){
							console.info(error);
							that.$message.error("服务器繁忙,请稍后重试");
						});
					} else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
			isLogin() {
				this.axios.get('/lygsbxt/security/isLogin')
				.then(function(response){
					console.info(response.data);
				}).catch(function(error){
					console.info(error);
					that.$message.error("服务器繁忙,请稍后重试");
				});
			}
			
        }
    }
</script>

<style scoped>
    .login-wrap{
        position: relative;
        width:100%;
        height:100%;
		background-image: url(../../assets/login-bg.jpg);
		background-size: 100%;
    }
    .ms-title{
        position: absolute;
        top:50%;
        width:100%;
        margin-top: -230px;
        text-align: center;
        font-size:30px;
        color: #fff;

    }
    .ms-login{
        position: absolute;
        left:50%;
        top:50%;
        width:300px;
        height:160px;
        margin:-150px 0 0 -190px;
        padding:40px;
        border-radius: 5px;
        background: #fff;
    }
    .login-btn{
        text-align: center;
    }
    .login-btn button{
        width:100%;
        height:36px;
    }
</style>