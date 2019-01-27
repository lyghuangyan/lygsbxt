<template>
    <div class="table">
        <div class="container">
            <div class="handle-box">
                <el-button type="primary" icon="delete" class="handle-del mr10" @click="addOfficeuser">新增</el-button>
            </div>
            <el-table :data="tableData" border style="width: 100%">
                <el-table-column prop="username" label="登录名" width="188">
					<template slot-scope="scope">
						<span>{{ scope.row.username}}</span>
					</template>
                </el-table-column>
                <el-table-column prop="role" label="类型" width="180">
					<template slot-scope="scope">
						<span v-if="scope.row.role==1">维修申报管理员</span>
                        <span v-if="scope.row.role==2">服务评价管理员</span>
                        <span v-if="scope.row.role==3">超级管理员</span>
					</template>
                </el-table-column>
                <el-table-column label="操作" min-width="241">
                    <template slot-scope="scope">
                        
						<el-button size="small" 
						@click="handleEdit(
						scope.row.manageid, scope.row.username, 
						scope.row.role)">
						编辑</el-button>
						<el-button size="small" 
						@click="handleResetPassword(scope.row.manageid)">
						重置密码</el-button>
                        <el-button size="small" type="danger" 
						@click="handleDelete(scope.row.manageid)">
						删除</el-button>
						
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination 
				@current-change="handleCurrentChange" layout="prev, pager, next" :total="total"
				:current-page.sync="cur_page" v-if="paginationShow">
                </el-pagination>
            </div>
        </div>

        <!-- 编辑弹出框 -->
        <el-dialog title="编辑" :visible.sync="editVisible" width="37%">
            <el-form ref="form" :model="form" label-width="100px">
				<el-form-item label="登录名">
					<el-input v-model="form.username"></el-input>
				</el-form-item>
                <el-form-item label="管理员类型">
					<el-select v-model="form.role" placeholder="请选择" class="handle-select mr10" style="width:150px">
						<el-option label="维修申报管理员" value="1">
						</el-option>
                        <el-option label="服务评价管理员" value="2">
						</el-option>
                        <el-option label="超级管理员" value="3">
						</el-option>
					</el-select>
				</el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveEdit">确 定</el-button>
            </span>
        </el-dialog>

        <!-- 删除提示框 -->
        <el-dialog title="提示" :visible.sync="delVisible" width="300px" center>
            <div class="del-dialog-cnt">删除不可恢复，是否确定删除？</div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="delVisible = false">取 消</el-button>
                <el-button type="primary" @click="deleteRow">确 定</el-button>
            </span>
        </el-dialog>
		<!-- 重置密码提示框 -->
		<el-dialog title="提示" :visible.sync="resetPasswordVisible" width="300px" center>
			<div class="del-dialog-cnt">是否确定重置密码？</div>
			<span slot="footer" class="dialog-footer">
				<el-button @click="resetPasswordVisible = false">取 消</el-button>
				<el-button type="primary" @click="resetPassword">确 定</el-button>
			</span>
		</el-dialog>
    </div>
</template>

<script>
    export default {
        data() {
            return {
				paginationShow:true,
				dialogImageUrl: '',
				dialogVisible: false,
				productImgs: [],
				isMultiple: true,
				imgLimit: 1,
                url: this.GLOBAL.proName+'/officeuser/pageOfficeuser',
                tableData: [],
                cur_page: 1,
                multipleSelection: [],
                select_cate: '',
                select_word: '',
                del_list: [],
                is_search: false,
                editVisible: false,
                delVisible: false,
				resetPasswordVisible: false,
                form: {
					manageid:'',
					username:'',
                    role: ''
                },
                idx: -1,
				total:0,
				manageid:'',
				proName: this.GLOBAL.proName,
				realmName: this.GLOBAL.realmName
            }
        },
        created() {
            this.getData();
        },
        methods: {
            // 分页导航
            handleCurrentChange(val) {
                this.cur_page = val;
                this.getData();
            },
            // 获取 easy-mock 的模拟数据
            getData() {
				var that = this;
				that.paginationShow=false;
                this.axios.get(
				that.GLOBAL.proName+'/manageuser/pageManageuser?size=10&page='
				+that.cur_page)
                .then(function(res){
                	that.tableData = res.data.data.page;
					that.total = res.data.data.total;
					that.paginationShow=true;
                }).catch(function(error){
                	console.info(error);
                	that.$message.error("服务器繁忙,请稍后重试");
                });
            },
			//点击搜索
            search() {
				//console.info(this.tableData);
				this.cur_page = 1;
				this.getData();
                //this.is_search = true;
            },
            formatter(row, column) {
                return row.address;
            },
            filterTag(value, row) {
                return row.tag === value;
            },
            handleEdit(manageid, username, role) {
				this.form.manageid = manageid;
				this.form.username = username;
				this.form.role = role+'';
                this.editVisible = true;
            },
            handleDelete(manageid) {
				this.manageid = manageid;
                this.delVisible = true;
            },
			handleResetPassword(manageid) {
				this.manageid = manageid;
				this.resetPasswordVisible = true;
			},
			//新增维修人员信息
			addOfficeuser() {
				this.form.manageid = '';
				this.form.username = '';
				this.form.role = '';
				this.editVisible = true;
			},
			//重置密码
            resetPassword() {
				var that = this;
				this.axios.get(
				that.GLOBAL.proName+'/manageuser/resetPassword?manageid='
				+that.manageid)
				.then(function(res){
                    console.info(res);
					if(res.data.code == '200') {
						that.$message.success("密码重置成功");
						that.resetPasswordVisible = false;
						that.search();
					}
				}).catch(function(error){
					console.info(error);
					that.$message.error("服务器繁忙,请稍后重试");
				});
			},
            // 保存编辑
            saveEdit() {
				var that = this;
				this.axios.get(
				that.GLOBAL.proName+'/manageuser/saveManageuser?manageid='+that.form.manageid
				+'&username='+that.form.username+'&role='+that.form.role)
				.then(function(res){
					if(res.data.code == '200') {
						that.$message.success(res.data.msg);
						that.editVisible = false;
						that.search();
					} else {
						that.$message.error(res.data.msg);
					}
				}).catch(function(error){
					console.info(error);
					that.$message.error("服务器繁忙,请稍后重试");
				});
            },
            // 确定删除
            deleteRow(){
				var that = this;
				this.axios.get(
				that.GLOBAL.proName+'/manageuser/delManageuser?manageid='+that.manageid)
				.then(function(res){
					if(res.data.code == '200') {
						that.$message.success(res.data.msg);
						that.delVisible = false;
						that.search();
					} else {
						that.$message.error(res.data.msg);
					}
				}).catch(function(error){
					that.$message.error("服务器繁忙,请稍后重试");
				});
                
            },
			//预览图片时调用
			handlePictureCardPreview(file) {
				this.dialogImageUrl = file.url;
				this.dialogVisible = true;
			},
			handleRemove(file, fileList) {//移除图片
				this.form.photourl = '';
				this.productImgs = fileList;
			},
			//文件上传之前调用做一些拦截限制
			beforeAvatarUpload(file) {
				const isJPG = true;
				//const isJPG = file.type === 'image/jpeg';
				const isLt5M = file.size / 1024 / 1024 < 5;
				if (!isLt5M) {
					this.$message.error('上传图片大小不能超过 5MB!');
				}
				return isJPG && isLt5M;
			},
			//图片上传成功
			handleAvatarSuccess(res, file) {
				this.form.photourl = res;
				var photo = {};
				photo.name = res;
				photo.url = this.GLOBAL.realmName+this.GLOBAL.proName+res;
				this.productImgs.push(photo);
			},
			//图片上传超过数量限制
			handleExceed(files, fileList) {
				this.$message.error('上传图片不能超过'+this.imgLimit+'张!');
			},
			//图片上传失败调用
			imgUploadError(err, file, fileList) {
				this.$message.error('上传图片失败!');
			}
        }
    }

</script>

<style scoped>
    .handle-box {
        margin-bottom: 20px;
    }

    .handle-select {
        width: 120px;
    }

    .handle-input {
        width: 300px;
        display: inline-block;
    }
    .del-dialog-cnt{
        font-size: 16px;
        text-align: center
    }
</style>
