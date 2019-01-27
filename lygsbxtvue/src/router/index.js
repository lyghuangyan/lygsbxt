import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            redirect: '/maintainorder'
        },
        {
            path: '/',
            component: resolve => require(['../components/common/Home.vue'], resolve),
            meta: { title: '自述文件' },
            children:[
                {
                    path: '/dashboard',
                    component: resolve => require(['../components/page/Dashboard.vue'], resolve),
                    meta: { title: '系统首页' }
                },
                {
                    path: '/building',
                    component: resolve => require(['../components/page/building.vue'], resolve),
                    meta: { title: '服务管理' }
                },
                {
                    path: '/maintainuser',
                    component: resolve => require(['../components/page/maintainuser.vue'], resolve),
                    meta: { title: '维修人员管理' }
                },
                {
                    path: '/officeuser',
                    component: resolve => require(['../components/page/officeuser.vue'], resolve),
                    meta: { title: '派单人员管理' }
                },
				{
					path: '/buildingtype',
					component: resolve => require(['../components/page/buildingtype.vue'], resolve),
					meta: { title: '服务分类管理' }
				},
				{
					path: '/maintainorder',
					component: resolve => require(['../components/page/maintainorder.vue'], resolve),
					meta: { title: '维修任务' }
                },
                {
					path: '/manageuser',
					component: resolve => require(['../components/page/manageuser.vue'], resolve),
					meta: { title: '管理员管理' }
                }
            ]
        },
        {
            path: '/login',
            component: resolve => require(['../components/page/Login.vue'], resolve)
        },
        {
            path: '/404',
            component: resolve => require(['../components/page/404.vue'], resolve)
        },
        {
            path: '/403',
            component: resolve => require(['../components/page/403.vue'], resolve)
        },
        {
            path: '*',
            redirect: '/404'
        }
    ]
})
