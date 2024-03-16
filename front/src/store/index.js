// store/index.js

import { createStore } from 'vuex';

export default createStore({
  state: {
    openModalUserAdd: false,
    openModalConfirmDelete: false,
    openModalUpdateUser: false,
    listMembres: [],
    photoMembres: [],
    photoEvents: [],
    listEvents: [],
    listLieux: [],
    idUser : -1, 
    count: 0
  },
  mutations: {
    increment(state) {
      state.count++;
    },
    decrement(state) {
      state.count--;
    }, 
    toogleOpenModalUserAddTrue(state){
        state.openModalUserAdd = true;
    },
    toogleOpenModalUserAddFalse(state){
        state.openModalUserAdd = false;
    },
    toggleopenModalConfirmDeleteTrue(state){
        state.openModalConfirmDelete = true ;
    },
    toggleopenModalConfirmDeleteFalse(state){
        state.openModalConfirmDelete = false;
    },
    toogleOpenModalUserUpdateTrue(state){
      state.openModalUpdateUser = true;
    },
    toogleOpenModalUserUpdateFalse(state){
      state.openModalUpdateUser = false;
    },

  },
  actions: {
    increment(context) {
      context.commit('increment');
    },
    decrement(context) {
      context.commit('decrement');
    },

  },
  getters: {
    getCount: state => state.count,
    getIdUser: state => state.idUser,

    getOpenModalUserAdd: state => state.openModalUserAdd,
    getOpenModalConfirmDelete: state => state.openModalConfirmDelete,
    getListMembres: state => state.listMembres,
    getOpenModalUpdateUser: state => state.openModalUpdateUser,


  }
});
