<template>
	<div>
		<div class="form-box">
			<el-form ref="form" label-width="80px">
				<el-form-item label="角色">
					<el-select v-model="role.id" placeholder="请选择" @change="changeRole">
						<el-option :label="role.roleName" :value="role.id" v-for="role in rolelist"></el-option>
					</el-select>
				</el-form-item>
			</el-form>
		</div>
	</div>
</template>

<script>
    export default {
        data(){
            return {
				role:{
					
				},
				rolelist:[]
			}
        },
		created(){
			var that = this;
			this.$axios.get('/jinge/sysrole/getRoleList')
			.then(function(response) {
				let data = response.data;
				if(data.code == '200') {
					that.rolelist = data.data;
				}
			}).catch(function(error) {
				console.info(error);
				Message.error("服务器繁忙,请稍后重试");
			});
		},
		methods:{
			changeRole(roleId) {
				console.info(roleId);
				this.$message.success("当前选择id："+roleId);
			}
		}
		
    }
</script>

<style scoped>
h1{
    text-align: center;
    margin: 30px 0;
}
p{
    line-height: 30px;
    margin-bottom: 10px;
    text-indent: 2em;
}
.logout{
    color: #409EFF;
}
</style>