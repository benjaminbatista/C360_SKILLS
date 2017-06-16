// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Vuex from 'vuex'
import App from './App'
import CustomCircle from './components/customcircle.vue'
import ContainerSvg from './components/Container-svg'
import store from '@/stores/indexStore'
import router from './router'

Vue.use(Vuex);
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  template: '<div><ContainerSvg/>' +
  ' <CustomCircle cx="100" cy="100" content="hello"/></div>',
  components: { App, CustomCircle, ContainerSvg}
})
