<template>
    <div class="table">
        <div class="container">
            <div class="handle-box">
                <el-button type="primary" icon="delete" class="handle-del mr10" @click="addOfficeuser">新增</el-button>
                <el-input v-model="select_word" placeholder="筛选关键词" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="search" @click="search">搜索</el-button>
            </div>
            <el-table :data="tableData" border style="width: 100%">
				<!--ref="multipleTable" @selection-change="handleSelectionChange">-->
                <!--<el-table-column type="selection" width="55"></el-table-column>-->
                <el-table-column prop="username" label="手机" width="106">
					<template slot-scope="scope">
						<span>{{ scope.row.username}}</span>
					</template>
                </el-table-column>
                <el-table-column prop="realname" label="姓名" width="80">
					<template slot-scope="scope">
						<span>{{ scope.row.realname}}</span>
					</template>
                </el-table-column>
				<el-table-column prop="photourl" label="照片" width="320">
					<template slot-scope="scope">
						<a v-bind:href="[realmName+proName+scope.row.photourl]" target="view_window">{{ scope.row.photourl }}</a>
						
					</template>
				</el-table-column>
				<el-table-column prop="remark" label="备注" width="200">
					<template slot-scope="scope">
						<span>{{ scope.row.remark}}</span>
					</template>
				</el-table-column>
                <el-table-column label="操作" min-width="241">
                    <template slot-scope="scope">
                        
						<el-button size="small" 
						@click="handleEdit(
						scope.row.officerid, scope.row.username, 
						scope.row.realname, scope.row.photourl,
						scope.row.remark)">
						编辑</el-button>
						<el-button size="small" 
						@click="handleResetPassword(scope.row.officerid)">
						重置密码</el-button>
                        <el-button size="small" type="danger" 
						@click="handleDelete(scope.row.officerid)">
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
				<el-form-item label="手机(登录名)">
					<el-input v-model="form.username"></el-input>
				</el-form-item>
                <el-form-item label="姓名">
                    <el-input v-model="form.realname"></el-input>
                </el-form-item>
				<el-form-item label="备注">
					<el-input v-model="form.remark"></el-input>
				</el-form-item>
                <!--<el-form-item label="地址">
                    <el-input v-model="form.address"></el-input>
                </el-form-item>-->
				<el-form-item label="照片">
					<el-upload action="/lygsbxt/upload/uploadFile"
					list-type="picture-card"
					accept="image/*"
					:limit="imgLimit"
					:file-list="productImgs"
					:multiple="isMultiple"
					:on-preview="handlePictureCardPreview"
					:on-remove="handleRemove"
					:on-success="handleAvatarSuccess"
					:before-upload="beforeAvatarUpload"
					:on-exceed="handleExceed"
					:on-error="imgUploadError">
					</el-upload>
					<el-dialog :visible.sync="dialogVisible">
						<img width="100%" :src="dialogImageUrl" alt="">
					</el-dialog>
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
					officerid:'',
					username:'',
                    realname: '',
                    photourl: '',
					remark: ''
                },
                idx: -1,
				total:0,
				officerid:'',
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
				that.GLOBAL.proName+'/officeuser/pageOfficeuser?size=10&page='
				+that.cur_page+'&keyword='+that.select_word)
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
            handleEdit(officerid, username, realname, photourl, remark) {
				this.form.officerid = officerid;
				this.form.username = username;
				this.form.realname = realname;
				this.form.photourl = photourl;
				this.form.remark = remark;
				this.productImgs = [];
				
				if(photourl != null && photourl != '') {
					var photo = {};
					photo.name = photourl;
					photo.url = this.GLOBAL.realmName+this.GLOBAL.proName+photourl;
					this.productImgs.push(photo);
				}
				
                this.editVisible = true;
            },
            handleDelete(officerid) {
				this.officerid = officerid;
                this.delVisible = true;
            },
			handleResetPassword(officerid) {
				this.officerid = officerid;
				this.resetPasswordVisible = true;
			},
			//新增维修人员信息
			addOfficeuser() {
				this.form.officerid = '';
				this.form.username = '';
				this.form.realname = '';
				this.form.photourl = '';
				this.form.remark = '';
				this.productImgs = [];
				this.editVisible = true;
			},
			//重置密码
            resetPassword() {
				var that = this;
				this.axios.get(
				that.GLOBAL.proName+'/officeuser/resetPassword?officerid='
				+that.officerid)
				.then(function(res){
					if(res.data.data == true) {
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
				that.GLOBAL.proName+'/officeuser/saveOfficeuser?officerid='+that.form.officerid
				+'&username='+that.form.username+'&realname='+that.form.realname
				+'&photourl='+that.form.photourl+'&remark='+that.form.remark)
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
				that.GLOBAL.proName+'/officeuser/delOfficeuser?officerid='+that.officerid)
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
