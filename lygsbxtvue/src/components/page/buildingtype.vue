<template>
    <div class="table">
        <div class="container">
            <div class="handle-box">
                <el-button type="primary" icon="delete" class="handle-del mr10" @click="addBuildingtype">新增</el-button>
				<el-input v-model="select_word" placeholder="筛选关键词" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="search" @click="search">搜索</el-button>
            </div>
            <el-table :data="tableData" border style="width: 100%">
				<!--ref="multipleTable" @selection-change="handleSelectionChange">-->
                <!--<el-table-column type="selection" width="55"></el-table-column>-->
                <el-table-column prop="type" label="类型名称" width="200">
					<template slot-scope="scope">
						<span>{{ scope.row.typename}}</span>
					</template>
                </el-table-column>
                <el-table-column prop="name" label="排序号" width="80">
					<template slot-scope="scope">
						<span>{{ scope.row.sort}}</span>
					</template>
                </el-table-column>
                <el-table-column label="操作" min-width="148">
                    <template slot-scope="scope">
                        
						<el-button size="small" 
						@click="handleEdit(scope.row.typeid, scope.row.typename, scope.row.sort)">
						编辑</el-button>
                        <el-button size="small" type="danger" 
						@click="handleDelete(scope.row.typeid)">
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
				<el-form-item label="分类名称">
					<el-input v-model="form.typename"></el-input>
				</el-form-item>
				<el-form-item label="排序号">
					<el-input v-model="form.sort"></el-input>
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
    </div>
</template>

<script>
    export default {
        data() {
            return {
                paginationShow:true,
				isMultiple: true,
                url: this.GLOBAL.proName+'/buildingtype/pageBuildingtype',
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
					typeid:'',
					typename:'',
                    sort: ''
                },
                idx: -1,
				total:0,
				typeid:'',
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
				that.GLOBAL.proName+'/buildingtype/pageBuildingtype?size=10&page='
				+that.cur_page+'&keyword='+that.select_word)
                .then(function(res){
                	that.tableData = res.data.data.page;
                    that.total = res.data.data.total;
                    that.paginationShow=true;
                }).catch(function(error){
                	that.$message.error("服务器繁忙,请稍后重试");
                });
            },
			//点击搜索
            search() {
				this.cur_page = 1;
				this.getData();
                this.is_search = true;
            },
            formatter(row, column) {
                return row.address;
            },
            filterTag(value, row) {
                return row.tag === value;
            },
            handleEdit(typeid, typename, sort) {
				this.form.typeid = typeid;
				this.form.typename = typename;
				this.form.sort = sort;
                this.editVisible = true;
            },
            handleDelete(typeid) {
				this.typeid = typeid;
                this.delVisible = true;
            },
			//新增维修人员信息
			addBuildingtype() {
				this.form.typeid = '';
				this.form.typename = '';
				this.form.sort = '';
				this.editVisible = true;
			},
            // 保存编辑
            saveEdit() {
				var that = this;
				var params = new URLSearchParams();
				params.append('typeid', this.form.typeid);
				params.append('typename', this.form.typename);
				params.append('sort', this.form.sort);
				this.axios.post(
				that.GLOBAL.proName+'/buildingtype/saveBuildingtype',params)
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
				that.GLOBAL.proName+'/buildingtype/delBuildingtype?typeid='+that.typeid)
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
