// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Vuex from 'vuex'
import store from './vuex/store'
import router from './config/router.js'
import Dropdown from 'vue-js-dropdown'
import config from './config/config.js'
require('jwt-decode');
require('../node_modules/bootstrap/dist/css/bootstrap.css');

Vue.use(Vuex);
Vue.use(Dropdown);
Vue.use(VueResource);
Vue.config.productionTip = false;

// axios.defaults.baseURL = 'http://localhost:8080'
//
// axios.interceptors.response.use((response) => {
//   return response
// }, function (error) {
//   // Do something with response error
//   if (error.response.status === 401) {
//     console.log('unauthorized, logging out ...')
//     auth.logout()
//     router.replace('/login')
//   }
//   return Promise.reject(error)
// })

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  config,
  template: `<div><router-view></router-view></div>`
});
