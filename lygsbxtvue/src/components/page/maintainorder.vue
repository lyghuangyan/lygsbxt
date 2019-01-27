<template>
    <div class="table">
        <div class="container">
            <div class="handle-box">
                <el-select 
                	v-model="select_maintainid" placeholder="维修人员"
                	class="handle-select mr10" @change="search">
					<el-option key="all" label="全部" value="all"></el-option>
                	<el-option v-for="item in maintainuserList"
                	:key="item.maintainid" :label="item.realname"
					 :value="item.maintainid"></el-option>
                	
                </el-select>
				<el-select 
					v-model="select_status" placeholder="状态"
					class="handle-select mr10" @change="search">
					<el-option key="all" label="全部" value="all"></el-option>
					<el-option key="未派单" 
					label="未派单" value="未派单"></el-option>
					<el-option key="维修中" 
					label="维修中" value="维修中"></el-option>
					<el-option key="待评价" 
					label="待评价" value="待评价"></el-option>
					<el-option key="已完成" 
					label="已完成" value="已完成"></el-option>
				</el-select>
                
                <el-button type="primary" icon="search"
				 @click="search">刷新</el-button>
				 
            </div>
            <el-table :data="tableData" border style="width: 100%">
				<!--ref="multipleTable" @selection-change="handleSelectionChange">-->
                <!--<el-table-column type="selection" width="55"></el-table-column>-->
                <!--<el-table-column prop="$index" label="序号" width="46">
                	<template slot-scope="scope">
                		<span>{{ scope.$index + 1 }}</span>
                	</template>
                </el-table-column>-->
				<el-table-column prop="title" label="标题" width="105">
					<template slot-scope="scope">
						<span>{{ scope.row.title}}</span>
					</template>
                </el-table-column>
                <el-table-column prop="content" label="内容" width="200">
					<template slot-scope="scope">
						<span>{{ scope.row.content}}</span>
					</template>
                </el-table-column>
				<el-table-column prop="pictureurls" label="图片" width="320">
					<template slot-scope="scope">
						<span v-for="pictureurl in scope.row.pictureurls">
							<a v-bind:href="[realmName+proName+pictureurl]" 
							target="view_window">{{ pictureurl }}</a><br/>
						</span>
					</template>
				</el-table-column>
				<el-table-column prop="status" label="状态" width="68">
					<template slot-scope="scope">
						<font color="red" v-if="scope.row.status=='未派单'">
						{{ scope.row.status}}</font>
						<font color="orange" v-if="scope.row.status=='维修中'">
						{{ scope.row.status}}</font>
						<font color="blue" v-if="scope.row.status=='待评价'">
						{{ scope.row.status}}</font>
						<font color="green" style="cursor: pointer"
						@click="handleEdit(scope.row.comments.content, 
						scope.row.comments.stars)"
						 v-if="scope.row.status=='已完成'">
						{{ scope.row.status}}</font>
					</template>
				</el-table-column>
				<el-table-column prop="nickname" label="申请人" width="68">
					<template slot-scope="scope">
						<span>{{ scope.row.wxuser.nickname}}</span>
					</template>
				</el-table-column>
				<el-table-column prop="createtimeStr" label="申请时间" width="134">
					<template slot-scope="scope">
						<span>{{ scope.row.createtimeStr}}</span>
					</template>
				</el-table-column>
				<el-table-column prop="realname" label="维修人员" width="72">
					<template slot-scope="scope">
						<span>{{ scope.row.maintainuser.realname}}</span>
					</template>
				</el-table-column>
				<el-table-column prop="realname" label="评价得分" width="70">
					<template slot-scope="scope">
						<span>{{ scope.row.comments.stars}}</span>
					</template>
				</el-table-column>
				<el-table-column prop="realname" label="评价内容" width="172">
					<template slot-scope="scope">
						<span>{{ scope.row.comments.content}}</span>
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
        <el-dialog title="评价信息" :visible.sync="editVisible" width="37%" top="15%">
			<el-row>
				<el-col :span="5">评分</el-col>
				<el-col :span="19">
					<el-rate v-model="form.stars" :disabled="true"></el-rate>
				</el-col>
			</el-row>
			<el-row>
				<el-col :span="5">内容</el-col>
				<el-col :span="19">
					{{form.content}}
				</el-col>
			</el-row>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">关 闭</el-button>
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
                url: this.GLOBAL.proName+'/maintainorder/pageWebMaintainorder',
                tableData: [],
				maintainuserList: [],
                cur_page: 1,
                multipleSelection: [],
                select_maintainid: '',
                select_status: '',
                del_list: [],
                is_search: false,
                editVisible: false,
                delVisible: false,
				resetPasswordVisible: false,
                form: {
					content: '',
					stars: 1
                },
                idx: -1,
				total:0,
				maintainid:'',
				proName: this.GLOBAL.proName,
				realmName: this.GLOBAL.realmName
            }
        },
        created() {
            this.getData();
			this.getMaintainuserList();
        },
        methods: {
            // 分页导航
            handleCurrentChange(val) {
                this.cur_page = val;
                this.getData();
            },
			//获取维修员列表
			getMaintainuserList() {
				var that = this;
				this.axios.get(
				that.GLOBAL.proName+'/maintainuser/listMaintainuser')
				.then(function(res){
					if(res.data.code == '200') {
						that.maintainuserList = res.data.data;
					} else {
						that.$message.error(res.data.msg);
					}
				}).catch(function(error){
					console.info(error);
					that.$message.error("服务器繁忙,请稍后重试");
				});
			},
            // 获取 easy-mock 的模拟数据
            getData() {
				var that = this;
				that.paginationShow=false;
                this.axios.get(that.url+'?size=10&page='
				+that.cur_page+'&status='+that.select_status
				+'&maintainid='+that.select_maintainid)
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
            handleEdit(content, stars) {
				this.form.content = content;
				this.form.stars = stars;
                this.editVisible = true;
            },
            handleDelete(maintainid) {
				this.maintainid = maintainid;
                this.delVisible = true;
            },
			handleResetPassword(maintainid) {
				this.maintainid = maintainid;
				this.resetPasswordVisible = true;
			},
			//新增维修人员信息
			addMaintainuser() {
				this.form.maintainid = '';
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
				that.GLOBAL.proName+'/maintainuser/resetPassword?maintainid='
				+that.maintainid)
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
				that.GLOBAL.proName+'/maintainuser/saveMaintainuser?maintainid='+that.form.maintainid
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
				that.GLOBAL.proName+'/maintainuser/delMaintainuser?maintainid='+that.maintainid)
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
				const isJPG = file.type === 'image/jpeg';
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
