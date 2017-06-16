// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Vuex from 'vuex'
import navigationMenu from './components/layout/navigationMenu.vue'
require('../node_modules/bootstrap/dist/css/bootstrap.css');


Vue.use(Vuex);
Vue.config.productionTip = false;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  template: '<navigation-menu></navigation-menu>',
  components: { navigationMenu }
})
