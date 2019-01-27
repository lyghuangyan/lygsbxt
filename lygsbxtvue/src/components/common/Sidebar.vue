<template>
    <div class="sidebar">
        <el-menu class="sidebar-el-menu" :default-active="onRoutes" :collapse="collapse" background-color="#324157"
            text-color="#bfcbd9" active-text-color="#20a0ff" unique-opened router>
            <template v-for="item in items">
                <template v-if="item.subs">
                    <el-submenu :index="item.index" :key="item.index">
                        <template slot="title">
                            <i :class="item.icon"></i><span slot="title">{{ item.title }}</span>
                        </template>
                        <el-menu-item v-for="(subItem,i) in item.subs" :key="i" :index="subItem.index">
                            {{ subItem.title }}
                        </el-menu-item>
                    </el-submenu>
                </template>
                <template v-else>
                    <el-menu-item :index="item.index" :key="item.index">
                        <i :class="item.icon"></i><span slot="title">{{ item.title }}</span>
                    </el-menu-item>
                </template>
            </template>
        </el-menu>
    </div>
</template>

<script>
    import bus from '../common/bus';
    export default {
        data() {
            return {
                collapse: false,
                items: [
					{
						icon: 'el-icon-tickets',
						index: 'maintainorder',
						title: '维修任务'
					},
                    {
                        icon: 'el-icon-setting',
                        index: 'building',
                        title: '服务管理'
                    },
					{
						icon: 'el-icon-setting',
						index: 'buildingtype',
						title: '服务分类管理'
					},
                    {
                        icon: 'el-icon-setting',
                        index: 'maintainuser',
                        title: '维修人员管理'
                    },
                    {
                        icon: 'el-icon-setting',
                        index: 'officeuser',
                        title: '派单人员管理'
                    }
                ]
            }
        },
        computed:{
            onRoutes(){
                return this.$route.path.replace('/','');
            }
        },
        created(){
            var that = this;
			this.axios.get('/lygsbxt/manageuser/getPersonalUserInfo')
				.then(function(response){
                    console.info(response.data.data.role);
					if(response.data.code == '200') {
                        var role = response.data.data.role;
                        if(role == 1) {
                            that.items = [];
                            that.items.push(
                                {
                                    icon: 'el-icon-tickets',
                                    index: 'maintainorder',
                                    title: '维修任务'
                                }
                            );
                            that.items.push(
                                {
                                    icon: 'el-icon-setting',
                                    index: 'maintainuser',
                                    title: '维修人员管理'
                                }
                            );
                            that.items.push(
                                {
                                    icon: 'el-icon-setting',
                                    index: 'officeuser',
                                    title: '派单人员管理'
                                }
                            );
                        }
                        if(role == 2) {
                            that.items = [];
                            that.items.push(
                                {
                                icon: 'el-icon-setting',
                                index: 'building',
                                title: '服务管理'
                            }
                            );
                            that.items.push(
                                {
                                    icon: 'el-icon-setting',
                                    index: 'buildingtype',
                                    title: '服务分类管理'
                                }
                            );
                            
                        }
                        if(role == 3) {
                            that.items = [{
                                icon: 'el-icon-tickets',
                                index: 'maintainorder',
                                title: '维修任务'
                            },
                            {
                                icon: 'el-icon-setting',
                                index: 'building',
                                title: '服务管理'
                            },
                            {
                                icon: 'el-icon-setting',
                                index: 'buildingtype',
                                title: '服务分类管理'
                            },
                            {
                                icon: 'el-icon-setting',
                                index: 'maintainuser',
                                title: '维修人员管理'
                            },
                            {
                                icon: 'el-icon-setting',
                                index: 'officeuser',
                                title: '派单人员管理'
                            },
                            {
                                icon: 'el-icon-setting',
                                index: 'manageuser',
                                title: '管理员管理'
                            }];
                        }
					}
				}).catch(function(error){
					console.info(error);
					that.$message.error("服务器繁忙,请稍后重试");
				});
            // 通过 Event Bus 进行组件间通信，来折叠侧边栏
            bus.$on('collapse', msg => {
                this.collapse = msg;
            });
        }
    }
</script>

<style scoped>
    .sidebar{
        display: block;
        position: absolute;
        left: 0;
        top: 70px;
        bottom:0;
    }
    .sidebar-el-menu:not(.el-menu--collapse){
        width: 150px;
    }
    .sidebar > ul {
        height:100%;
    }
</style>
