<template>
    <div class="header">
        <!-- 折叠按钮 -->
        <div class="collapse-btn" @click="collapseChage">
            <i class="el-icon-menu"></i>
        </div>
        <div class="logo">后台管理系统</div>
        <div class="header-right">
            <div class="header-user-con">
                
                <!-- 用户名下拉菜单 -->
                <el-dropdown class="user-name" trigger="click" @command="handleCommand">
                    <span class="el-dropdown-link">
                        {{nickname}} <i class="el-icon-caret-bottom"></i>
                    </span>
                    <el-dropdown-menu slot="dropdown">
						<el-dropdown-item divided  command="changePassword">修改密码</el-dropdown-item>
                        <el-dropdown-item divided  command="loginout">退出登录</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </div>
        </div>
		<!-- 弹出框 -->
		<el-dialog title="修改密码" :visible.sync="editVisible" width="37%">
	        <el-form ref="form" :model="form" label-width="100px">
				<el-form-item label="原密码">
					<el-input type="password" v-model="form.oldPassword"></el-input>
				</el-form-item>
				<el-form-item label="新密码">
					<el-input type="password" v-model="form.newPassword"></el-input>
				</el-form-item>
				<el-form-item label="确认密码">
					<el-input type="password" v-model="form.confirmPassword"></el-input>
				</el-form-item>			
			</el-form>
			<span slot="footer" class="dialog-footer">
			    <el-button @click="editVisible = false">取 消</el-button>
			    <el-button type="primary" @click="changePassword">确 定</el-button>
			</span>
		</el-dialog>
    </div>
	
</template>
<script>
    import bus from '../common/bus';
    export default {
        data() {
            return {
                collapse: false,
                fullscreen: false,
                nickname: '管理员',
                message: 2,
				headurl:'static/img/img.jpg',
				editVisible: false,
				form: {
					oldPassword:'',
					newPassword:'',
					confirmPassword:''
                },
                manageuser: {
                    role: 1
                }
            }
        },
        computed:{
            username(){
                let username = localStorage.getItem('ms_username');
                return username ? username : this.name;
            }
        },
		created:function(){
			this.refreshUserInfo();
		},
        methods:{
            // 用户名下拉菜单选择事件
            handleCommand(command) {
                if(command == 'loginout') {
					this.axios.get('/lygsbxt/logout')
					.then(function(response){}).catch(function(error){});
                    localStorage.removeItem('ms_username')
                    this.$router.push('/login');
                }
				if(command == 'changePassword') {
					this.form.oldPassword='';
					this.form.newPassword='';
					this.form.confirmPassword='';
					this.editVisible = true;
				}
            },
            // 侧边栏折叠
            collapseChage(){
                this.collapse = !this.collapse;
                bus.$emit('collapse', this.collapse);
            },
            // 全屏事件
            handleFullScreen(){
                let element = document.documentElement;
                if (this.fullscreen) {
                    if (document.exitFullscreen) {
                        document.exitFullscreen();
                    } else if (document.webkitCancelFullScreen) {
                        document.webkitCancelFullScreen();
                    } else if (document.mozCancelFullScreen) {
                        document.mozCancelFullScreen();
                    } else if (document.msExitFullscreen) {
                        document.msExitFullscreen();
                    }
                } else {
                    if (element.requestFullscreen) {
                        element.requestFullscreen();
                    } else if (element.webkitRequestFullScreen) {
                        element.webkitRequestFullScreen();
                    } else if (element.mozRequestFullScreen) {
                        element.mozRequestFullScreen();
                    } else if (element.msRequestFullscreen) {
                        // IE11
                        element.msRequestFullscreen();
                    }
                }
                this.fullscreen = !this.fullscreen;
            },
			//刷新个人信息
			refreshUserInfo() {
				var that = this;
				this.axios.get('/lygsbxt/manageuser/getPersonalUserInfo')
				.then(function(response){
					if(response.data.code == '200') {
                        that.manageuser.role = response.data.data.role;
                        if(response.data.data.role == 1) {
                            that.nickname = '维修申报管理员';
                        }
                        if(response.data.data.role == 2) {
                            that.nickname = '服务评价管理员';
                        }
                        if(response.data.data.role == 3) {
                            that.nickname = '超级管理员';
                        }
					}
				}).catch(function(error){
					console.info(error);
					that.$message.error("服务器繁忙,请稍后重试");
				});
			},
			//修改密码
			changePassword() {
				var that = this;
				var params = new URLSearchParams();
				params.append('oldPassword', this.form.oldPassword);
				params.append('newPassword', this.form.newPassword);
				params.append('confirmPassword', this.form.confirmPassword);
				this.axios.post(
				that.GLOBAL.proName+'/manageuser/changePassword',params)
				.then(function(res){
					if(res.data.code == '200') {
						that.$message.success(res.data.msg);
						that.editVisible = false;
					} else {
						that.$message.error(res.data.msg);
					}
				}).catch(function(error){
					console.info(error);
					that.$message.error("服务器繁忙,请稍后重试");
				});
			}
        }
    }
</script>
<style scoped>
    .header {
        position: relative;
        box-sizing: border-box;
        width: 100%;
        height: 70px;
        font-size: 22px;
        color: #fff;
    }
    .collapse-btn{
        float: left;
        padding: 0 21px;
        cursor: pointer;
        line-height: 70px;
    }
    .header .logo{
        float: left;
        width:250px;
        line-height: 70px;
    }
    .header-right{
        float: right;
        padding-right: 50px;
    }
    .header-user-con{
        display: flex;
        height: 70px;
        align-items: center;
    }
    .btn-fullscreen{
        transform: rotate(45deg);
        margin-right: 5px;
        font-size: 24px;
    }
    .btn-bell, .btn-fullscreen{
        position: relative;
        width: 30px;
        height: 30px;
        text-align: center;
        border-radius: 15px;
        cursor: pointer;
    }
    .btn-bell-badge{
        position: absolute;
        right: 0;
        top: -2px;
        width: 8px;
        height: 8px;
        border-radius: 4px;
        background: #f56c6c;
        color: #fff;
    }
    .btn-bell .el-icon-bell{
        color: #fff;
    }
    .user-name{
        margin-left: 10px;
    }
    .user-avator{
        margin-left: 20px;
    }
    .user-avator img{
        display: block;
        width:40px;
        height:40px;
        border-radius: 50%;
    }
    .el-dropdown-link{
        color: #fff;
        cursor: pointer;
    }
    .el-dropdown-menu__item{
        text-align: center;
    }
</style>
