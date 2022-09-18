
export default {
    state: {
        isCollapsed: false,
        tabList:[
            {
                path: '/',
                name: 'home',
                label: '首页',
                icon: 'home'
            }
        ],
        currentMenu: null
    },
    mutations: {
        collapseMenu(state){
            state.isCollapsed = !state.isCollapsed
        },
        selectMenu(state,item){
            if(item.name!== 'home') {
                state.currentMenu = item
                const result = state.tabList.findIndex(i => i.name === item.name)
                console.log(result);

                if (result === -1) {
                    state.tabList.push(item)
                }
            }else {
                state.currentMenu = null
            }

        },
        deleteMenu(state,val){
            const result = state.tabList.findIndex(item=>item.name === val.name)
            state.tabList.splice(result, 1)
        }
    }
}
