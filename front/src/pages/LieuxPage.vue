<template>    
  <div class="lieux-page">
    <HeaderComponentV2></HeaderComponentV2>
        <h1>gestion Lieux </h1>

   <section class="p-3">

        <div class="row">
            <div class="col-12">
                <button class="btn btn-primary newUser" data-bs-toggle="modal" data-bs-target="#userForm" @click="openModalLieuxAddClick()">Ajouter Lieux <i class="bi bi-people"></i></button>
            </div>
        </div>

        <div class="row">
            <div class="col-12">
                <table class="table table-striped table-hover mt-3 text-center table-bordered">

                    <thead>
                        <tr>
                            <th>Id</th>                           
                            <th>Nom</th>
                            <th>Adresse</th>
                            <th>Capacité</th>
                            <th>Action</th>

                        </tr>
                    </thead>

                   <tbody id="data">
    <tr v-for="(lieu, index) in store.state.listLieux" :key="index ">
        <td>{{lieu.id_lieu}}</td>
        <td>{{lieu.nom}}</td>
        <td>{{lieu.adresse}}</td>
        <td>{{lieu.capacite}}</td>                                     
        <td> 
            <button @click="openModalUpdateLieux(lieu.id_lieu)" class="btn btn-primary"><i class="bi bi-pencil-square"></i></button>
            <button @click="openModalConfirmDeletelieu(lieu.id_lieu)" class="btn btn-danger"><i class="bi bi-trash"></i></button>
        </td> 
    </tr>

       
</tbody>


                </table>
            </div>
        </div>

    </section>
        <ModalAddLieux v-if="store.getters.getOpenModalUserAdd" ></ModalAddLieux>
        <ModalSupp v-if="store.getters.getOpenModalConfirmDelete" :idMembre="idLieu" :type="3"></ModalSupp>
        <ModalUpdateLieux v-if="store.getters.getOpenModalUpdateUser" :idMembre="idLieu"></ModalUpdateLieux>

  </div>
</template>

<script>
import { onMounted, ref, watchEffect } from 'vue';
import HeaderComponentV2 from '@/components/HeaderComponentV2.vue';
import ModalSupp from '@/components/ModalSupp.vue';

//import L from 'leaflet';


import { useStore } from 'vuex';
import axios from 'axios';
import ModalAddLieux from '@/components/ModalAddLieux.vue';
import ModalUpdateLieux from '@/components/ModalUpdateLieux.vue';
//import axios from 'axios';
export default {
  components: { HeaderComponentV2, ModalSupp, ModalAddLieux, ModalUpdateLieux },
    name: 'LieuxPage',
    setup() {  

        const store = useStore();
        const listLieuxx = ref([]);
        const idLieu = ref();
    

    
     const openModalUpdateLieux = (idLieux) => {
            store.commit('toogleOpenModalUserUpdateTrue');
            idLieu.value = idLieux

        }
   const openModalLieuxAddClick =  () => {
            store.commit('toogleOpenModalUserAddTrue');
        };

    const openModalConfirmDeletelieu =  (idLieux) => {
            store.commit('toggleopenModalConfirmDeleteTrue');
            idLieu.value = idLieux
        };
       const getLieux = async () => {
  try {
    const response = await axios.get(`http://localhost:8002/lieux`);
    const lieux = response.data;
    
   lieux.forEach(lieu => {
      listLieuxx.value.push(lieu);
   });
    //store.listMembres.push(listMembress)
    store.state.listLieux = listLieuxx.value;

    
    // Stockez les données récupérées dans une variable de données du composant si nécessaire
  } catch (error) {
    console.error('Erreur lors de la récupération des aliments par type:', error);
  }
};
    onMounted(async () => {
    
       await getLieux()



      });
        watchEffect(() => {
      console.log('Changements détectés dans store.state.listMembres :', store.state.listEvents);
    });
   
      
    
      return {
        //openModalUserAddClick,
        //openModalConfirmDelete,
        //openModalUpdateUser,
        openModalUpdateLieux,
        store,
        listLieuxx,
        openModalLieuxAddClick,
        idLieu,
        openModalConfirmDeletelieu,
        
      };
    }
}

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.lieux-page{
    color: #fff;
}
.leaflet-map {
  height: 400px; /* Ajustez la hauteur de la carte selon vos besoins */
}
th{ 
    color: #fff;
}
td{
    color: #fff;
}
table tr td{
    vertical-align: middle;
}
td button{
    margin: 5px;
}
td button i{
    font-size: 20px;
}
</style>
<!-- LoginPage.vue -->


