// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Vuex from 'vuex'
import VueResource from 'vue-resource'
import store from '@/stores/indexStore'
import router from './router/index.js'
import Dropdown from 'vue-js-dropdown'
require('jwt-decode');
require('../node_modules/bootstrap/dist/css/bootstrap.css');

Vue.use(Vuex);
Vue.use(Dropdown);
Vue.use(VueResource);
Vue.config.productionTip = false;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  template: `<div><router-view></router-view></div>`
});
