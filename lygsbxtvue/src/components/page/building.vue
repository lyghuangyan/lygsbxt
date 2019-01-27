<template>
    <div class="table">
        <div class="container" id="buildingPage" v-if="buildingPageVisible">
            <div class="handle-box">
                <el-button type="primary" icon="delete" class="handle-del mr10" @click="addBuilding">新增</el-button>
                <el-select 
					v-model="select_cate" placeholder="服务分类"
					class="handle-select mr10" @change="search">
					<el-option key="all" label="全部" value="all"></el-option>
                    <el-option v-for="item in typeList"
					 :key="item.typeid" :label="item.typename" :value="item.typeid"></el-option>
                    
                </el-select>
				<el-input v-model="select_word" placeholder="筛选关键词" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="search" @click="search">搜索</el-button>
            </div>
            <el-table :data="tableData" border style="width: 100%">
				<!--ref="multipleTable" @selection-change="handleSelectionChange">-->
                <!--<el-table-column type="selection" width="55"></el-table-column>-->
                <el-table-column prop="type" label="类型" width="80">
					<template slot-scope="scope">
						<span>{{ scope.row.typename}}</span>
					</template>
                </el-table-column>
                <el-table-column prop="name" label="名称" width="80">
					<template slot-scope="scope">
						<span>{{ scope.row.name}}</span>
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
				<el-table-column prop="type" label="平均得分" width="80">
					<template slot-scope="scope">
						<span>{{ scope.row.averagestars}}</span>
					</template>
				</el-table-column>
				<el-table-column prop="type" label="评论次数" width="80">
					<template slot-scope="scope">
						<span>{{ scope.row.commentcount}}</span>
					</template>
				</el-table-column>
                <el-table-column label="操作" min-width="245">
                    <template slot-scope="scope">
                        
						<el-button size="small" 
						@click="handleEdit(scope.row.buildid, scope.row.type, scope.row.name, scope.row.pictureurls)">
						编辑</el-button>
                        <el-button size="small" type="danger" 
						@click="handleDelete(scope.row.buildid)">
						删除</el-button>
						<el-button size="small" 
						@click="handleComments(scope.row.buildid, scope.row.name)">
						查看评论</el-button>
						
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination
				v-if="paginationShow"
				@current-change="handleCurrentChange"
				layout="prev, pager, next"
				:total="total"
				:current-page.sync="cur_page">
                </el-pagination>
            </div>
        </div>

        <!-- 编辑弹出框 -->
        <el-dialog title="编辑" :visible.sync="editVisible" width="37%">
            <el-form ref="form" :model="form" label-width="100px">
				<el-form-item label="服务分类">
					<el-select v-model="form.type" placeholder="请选择" class="handle-select mr10">
						<el-option v-for="item in typeList"
						:key="item.typeid" :label="item.typename" :value="item.typeid">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="名称">
					<el-input v-model="form.name"></el-input>
				</el-form-item>
				<el-form-item label="图片">
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

		<div class="container" id="commentsPage" v-if="commentsPageVisible">
            <div class="handle-box">
                <el-button type="success" icon="back" class="handle-del mr10" @click="backBuildingPage">返回</el-button>
				<el-tag>{{ buildnameC }}</el-tag>
            </div>
            <el-table :data="tableDataC" border style="width: 100%">
				<!--ref="multipleTable" @selection-change="handleSelectionChange">-->
                <!--<el-table-column type="selection" width="55"></el-table-column>-->
                <el-table-column prop="type" label="评论内容" width="480">
					<template slot-scope="scope">
						<span>{{ scope.row.content}}</span>
					</template>
                </el-table-column>
				<el-table-column prop="type" label="评论星级" width="80">
					<template slot-scope="scope">
						<span>{{ scope.row.stars}}</span>
					</template>
				</el-table-column>
				<el-table-column prop="type" label="评论人" width="80">
					<template slot-scope="scope">
						<span>{{ scope.row.wxuser.nickname}}</span>
					</template>
				</el-table-column>
                <el-table-column label="评论时间" min-width="135">
                    <template slot-scope="scope">
						<span>{{ scope.row.createtimeStr}}</span>
					</template>
                </el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination
				v-if="paginationShowC"
				@current-change="handleCurrentChangeC"
				layout="prev, pager, next"
				:total="totalC"
				:current-page.sync="cur_page_c">
                </el-pagination>
            </div>
        </div>
    </div>
</template>

<script>
	import EXIF from 'exif-js'
    export default {
        data() {
            return {
				paginationShow:true,
				paginationShowC:true,
				typeList:[],
				dialogImageUrl: '',
				dialogVisible: false,
				buildingPageVisible:true,
				commentsPageVisible:false,
				productImgs: [],
				isMultiple: true,
				imgLimit: 4,
                url: this.GLOBAL.proName+'/building/pageBuilding',
				tableData: [],
				tableDataC: [],
				cur_page: 1,
				cur_page_c: 1,
                multipleSelection: [],
                select_cate: '',
                select_word: '',
                del_list: [],
                editVisible: false,
                delVisible: false,
				resetPasswordVisible: false,
                form: {
					buildid:'',
					type:'',
                    name: '',
                    pictureurlList: []
                },
                idx: -1,
				total:0,
				totalC:0,
				buildid:'',
				proName: this.GLOBAL.proName,
				realmName: this.GLOBAL.realmName,
				buildidC:'',
				buildnameC:''
            }
        },
        created() {
            this.getData();
			this.getTypeList();
        },
        methods: {
			// 查看评论
			handleComments(buildid,name) {
				this.buildidC = buildid;
				this.buildnameC = name;
				this.cur_page_c = 1;
				this.getDataC();
				this.buildingPageVisible=false;
				this.commentsPageVisible=true;
			},
            // 分页导航
            handleCurrentChange(val) {
                this.cur_page = val;
                this.getData();
			},
			// 分页导航
            handleCurrentChangeC(val) {
                this.cur_page_c = val;
                this.getDataC();
			},
			// 返回服务分页
			backBuildingPage() {
				this.commentsPageVisible=false;
				this.buildingPageVisible=true;
			},
            // 获取 easy-mock 的模拟数据
            getData() {
				var that = this;
				that.paginationShow=false;
                this.axios.get(
				that.GLOBAL.proName+'/building/pageBuilding?size=10&page='
				+that.cur_page+'&keyword='+that.select_word+'&type='+that.select_cate)
                .then(function(res){
                	that.tableData = res.data.data.page;
					that.total = res.data.data.total;
					that.paginationShow=true;
                }).catch(function(error){
                	that.$message.error("服务器繁忙,请稍后重试");
                });
			},
			// 获取 easy-mock 的模拟数据
            getDataC() {
				var that = this;
				that.paginationShowC=false;
                this.axios.get(
				that.GLOBAL.proName+'/comments/pageComments?size=10&page='
				+that.cur_page_c+'&relationid='+that.buildidC)
                .then(function(res){
                	that.tableDataC = res.data.data.page;
					that.totalC = res.data.data.total;
					that.paginationShowC=true;
                }).catch(function(error){
                	that.$message.error("服务器繁忙,请稍后重试");
                });
            },
			//获取服务分类
			getTypeList() {
				var that = this;
				this.axios.get(
				that.GLOBAL.proName+'/buildingtype/getBuildingtypeList')
				.then(function(res){
					that.typeList = res.data.data;
				}).catch(function(error){
					that.$message.error("服务器繁忙,请稍后重试");
				});
			},
			//点击搜索
            search() {
				this.cur_page = 1;
				this.getData();
            },
            formatter(row, column) {
                return row.address;
            },
            filterTag(value, row) {
                return row.tag === value;
            },
            handleEdit(buildid, type, name, pictureurls) {
				this.form.buildid = buildid;
				this.form.type = type;
				this.form.name = name;
				this.form.pictureurls = pictureurls;
				this.productImgs = [];
				
				if(pictureurls != null && pictureurls.length > 0) {
					for(var i in pictureurls) {
						var photo = {};
						photo.name = pictureurls[i];
						photo.url = this.GLOBAL.realmName+this.GLOBAL.proName+pictureurls[i];
						this.productImgs.push(photo);
					}
				}
				
                this.editVisible = true;
            },
            handleDelete(buildid) {
				this.buildid = buildid;
                this.delVisible = true;
            },
			//新增维修人员信息
			addBuilding() {
				this.form.buildid = '';
				this.form.type = '';
				this.form.name = '';
				this.form.pictureurls = [];
				this.productImgs = [];
				this.editVisible = true;
			},
            // 保存编辑
            saveEdit() {
				var that = this;
				this.form.pictureurls = [];
				if(this.productImgs != null && this.productImgs.length > 0) {
					for(var i in this.productImgs) {
						this.form.pictureurls.push(this.productImgs[i].name);
					}
				}
				var params = new URLSearchParams();
				params.append('buildid', this.form.buildid);
				params.append('type', this.form.type);
				params.append('name', this.form.name);
				params.append('pictureurlList[]', this.form.pictureurls);
				this.axios.post(
				that.GLOBAL.proName+'/building/saveBuilding',params)
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
				that.GLOBAL.proName+'/building/delBuilding?buildid='+that.buildid)
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
			getOrientation: (file) => {
				console.info(EXIF);
				return new Promise((resolve)=>{
					EXIF.getData(file, function() {
						const orient = EXIF.getTag(this, 'Orientation')
						resolve(orient)
					})
				});
			},
			dataURLtoFile: (dataurl, filename) => {
				const arr = dataurl.split(',')
				const mime = arr[0].match(/:(.*?);/)[1]
				const bstr = atob(arr[1])
				let n = bstr.length
				let u8arr = new Uint8Array(n);
				while (n--) {
					u8arr[n] = bstr.charCodeAt(n);
				}
				return new File([u8arr], filename, {type: mime});
			},
			rotateImage: (image, width, height) => {
				let canvas = document.createElement('canvas')
				let ctx = canvas.getContext('2d')
				ctx.save()
				canvas.width = height
				canvas.height = width
				ctx.rotate(90 * Math.PI / 180)
				ctx.drawImage(image, 0, -height)
				ctx.restore()
				return canvas.toDataURL("image/jpeg")
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
				var that = this;
				//const isJPG = true;
				//const isJPG = file.type === 'image/jpeg';
				const isLt5M = file.size / 1024 / 1024 < 5;
				if (!isLt5M) {
					this.$message.error('上传图片大小不能超过 5MB!');
				}
				console.info('isLt5M='+isLt5M);
				return isLt5M;
				//return isJPG && isLt5M;
				console.info(EXIF);
				if(isLt5M) {
					var obj = new Promise((resolve) => {
						that.getOrientation(file).then((orient) => {
							console.info('orient='+orient);
							if (orient && orient === 6) {
								let reader = new FileReader()
								let img = new Image()
								reader.onload = (e) => {
									img.src = e.target.result
									img.onload = function () {
										const data = that.rotateImage(img, img.width, img.height)
										const newFile = that.dataURLtoFile(data, file.name)
										resolve(newFile)
									}
								}
								reader.readAsDataURL(file)
							} else {
								resolve(file)
							}
						})
					});
					console.info(123);
					console.info(obj);
					return obj;
				} else {
					return isLt5M;
				}
			},
			//图片上传进度
			handleProgress() {
			},
			//图片上传成功
			handleAvatarSuccess(res, file) {
				console.info(res);
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
