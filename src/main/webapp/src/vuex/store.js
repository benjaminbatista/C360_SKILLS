/**
 * Created by NBE3663 on 15/06/2017.
 */
import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    collaborator: []
  },
  actions: {



  },
  mutations: {
   getCollaborator(state, collaborator){
     state.collaborator = collaborator
   }
  },
  getters: {
  }
})
export default store
