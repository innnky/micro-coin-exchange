
export const data = {
    students:{
        menu:[
            {
                "menuId": 6,
                "vuePath": "/StudentHomePage",
                "menuName": "首页",
                "parentId": null,
                "icon": "user",
                "children": []
            },
            {
                "menuId": 7,
                "vuePath": "/StudentCourses",
                "menuName": "我的课程",
                "parentId": null,
                "icon": "user",
                "children": []
            },
            {
                "menuId": 8,
                "vuePath": "/StudentExercise",
                "menuName": "作业",
                "parentId": null,
                "icon": "user",
                "children": []
            },
            {
                "menuId": 9,
                "vuePath": "/StudentAccount",
                "menuName": "账号管理",
                "parentId": null,
                "icon": "user",
                "children": []
            },
            {
                "menuId": 10,
                "vuePath": "/StudentDevices",
                "menuName": "设备",
                "parentId": null,
                "icon": "user",
                "children": []
            },
            {
                "menuId": 11,
                "vuePath": "/StudentMaterial",
                "menuName": "资料",
                "parentId": null,
                "icon": "user",
                "children": []
            }
        ]
    },
    teacher:{
        menu:[
            {
                path: '/TeacherHomePage',
                label: '首页',
                icon: 'user'
            },
            {
                path: '/TeacherCourses',
                label: '我的课程',
                icon: 'user'
            },
            {
                path: '/TeacherExercise',
                label: '作业',
                icon: 'user'
            },{
                label: '设备',
                icon: 'user',
                children:[
                    {
                        path: "/DeviceManagement",
                        label: '设备管理',
                        icon: 'user'
                    },
                    {
                        path: '/SceneManagement',
                        label: '场景管理',
                        icon: 'user'
                    },{
                        path: '/ImageManagement',
                        label: '镜像管理',
                        icon: 'user'
                    }
                ]
            },
            {
                path: '/TeacherAccount',
                label: '账号',
                icon: 'user'
            }
        ]
    },
    admin:{
        menu:[
            {
                path: '/AdminHomePage',
                label: '首页',
                icon: 'user',
            },
            {
                path: "/ClassManagement",
                label: '班级管理',
                icon: 'user',
            },
            {
                path: '/DepartmentManagement',
                label: '部门管理',
                icon: 'user',
            },
            {
                path: '/CourseManagement',
                label: '课程管理',
                icon: 'user',
            },
            {
                label: "人员管理",
                icon: 'user',
                path: "people",
                children: [
                    {
                        path: '/StudentManagement',
                        label: '学生管理',
                        icon: 'user',
                    },
                    {
                        path: '/TeacherManagement',
                        label: '教师管理',
                        icon: 'user',
                    },

                    {
                        path: '/AccountManagement',
                        label: '账号管理',
                        icon: 'user',
                    },
                ]
            },
            {
                label: '设备',
                icon: 'user',
                path: "device",
                children:[
                    {
                        path: "/DeviceManagement",
                        label: '设备管理',
                        icon: 'user'
                    },
                    {
                        path: '/SceneManagement',
                        label: '场景管理',
                        icon: 'user'
                    },{
                        path: '/ImageManagement',
                        label: '镜像管理',
                        icon: 'user'
                    }
                ]
            }
        ]
    }
}