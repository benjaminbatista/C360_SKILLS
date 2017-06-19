// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Vuex from 'vuex'
import navigationMenu from './components/layout/navigationMenu.vue'
import HeaderSkills from './components/HeaderComponent/HeaderSkills.vue'
import store from '@/stores/indexStore'
import router from './router'
import Dropdown from 'vue-js-dropdown'
require('../node_modules/bootstrap/less/bootstrap.less')

Vue.use(Vuex)
Vue.use(Dropdown)
Vue.component('headerSkills', require('./components/HeaderComponent/HeaderSkills.vue'));

Vue.config.productionTip = false;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  template: '<HeaderSkills></HeaderSkills><navigation-menu></navigation-menu>',
  components: { HeaderSkills, navigationMenu }
})
