<template>
  <div class="membrePage">
<HeaderComponentV2></HeaderComponentV2>
    <h1>gestion membre </h1>
   <section class="p-3">

        <div class="row">
            <div class="col-12">
                <button class="btn btn-primary newUser" data-bs-toggle="modal" data-bs-target="#userForm" @click="openModalUserAddClick()">New User <i class="bi bi-people"></i></button>
            </div>
        </div>

        <div class="row">
            <div class="col-12">
                <table class="table table-striped table-hover mt-3 text-center table-bordered">

                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Image</th>
                            <th>Nom</th>
                            <th>Prenom</th>
                            <th>Adresse</th>
                            <th>Email</th>
                            <th>Action</th>
                        </tr>
                    </thead>

                   <tbody id="data">
    <tr v-for="(membre, index) in store.state.listMembres" :key="index ">
        <td>{{ membre.id }}</td>
        <td><img :src="store.state.photoMembres[index]" alt="" width="50" height="50"></td>
        <td>{{ membre.nom }} + {{index}}</td>
        <td>{{ membre.prenom }}</td>
        <td>{{ membre.adresse }}</td>
        <td>{{ membre.email }}</td>                            
        <td> 
            <button @click="openModalUpdateUser(membre.id)" class="btn btn-primary"><i class="bi bi-pencil-square"></i></button>
            <button @click="openModalConfirmDelete(membre.id)" class="btn btn-danger"><i class="bi bi-trash"></i></button>
        </td> 
    </tr>

       
</tbody>


                </table>
            </div>
        </div>

    </section>
   


    <ModalComponent v-if="store.getters.getOpenModalUserAdd"></ModalComponent>
    <!--<ModalSupp v-if="store.getters.getOpenModalConfirmDelete" ></ModalSupp>-->
    <ModalSupp v-if="store.getters.getOpenModalConfirmDelete" :idMembre="idMembre" :type="1"></ModalSupp>
    <ModalUpdate v-if="store.getters.getOpenModalUpdateUser" :idMembre="idMembre"></ModalUpdate>

  </div>
</template>

<script>
import HeaderComponentV2 from '@/components/HeaderComponentV2.vue'
import ModalComponent from '@/components/ModalComponent.vue'
import ModalSupp from '@/components/ModalSupp.vue'
import ModalUpdate from '@/components/ModalUpdate.vue'

import { onMounted, ref, watchEffect } from 'vue';
//import { BButton, BModal } from 'bootstrap-vue'
      import { useStore } from 'vuex';
import axios from 'axios';

export default {
    name: 'MembrePage',
    components: {
        HeaderComponentV2,
        ModalComponent,
        ModalSupp,
        ModalUpdate,
    },
    setup() {  
        const store = useStore();
        const listMembress = ref([]);
        const listPhotos = ref([]);

        const idMembre = ref();
        
        const getPhotos = async () => {
         try {
            
            const response = await axios.get(`https://api.unsplash.com/search/photos?query=user&client_id=nhr9wJnIHxnWeDBWu7E6HT8qMZhFcGZaGDwi7H6S-YA`);
            const photos = response.data.results; 

            const smallImageUrls = photos.map(item => item.urls.small);

            store.state.photoMembres = smallImageUrls.slice(0, store.state.listMembres.length);
            
     
            } catch (error) {
                console.error('Erreur lors de la récupération des photos via api:', error);
            }
        }
        const openModalUserAddClick =  () => {
            store.commit('toogleOpenModalUserAddTrue');
        };
        const openModalConfirmDelete =  (membreid) => {
            store.commit('toggleopenModalConfirmDeleteTrue');
            idMembre.value = membreid
        };

        const openModalUpdateUser = (membreid) => {
            store.commit('toogleOpenModalUserUpdateTrue');
            idMembre.value = membreid

        }
      
        const getMembre = async () => {
  try {
    console.log("kkfrf")
    const response = await axios.get(`http://localhost:8080/membres`);
    const membres = response.data;
    
   membres.forEach(membre => {
      listMembress.value.push(membre);
   });
    //store.listMembres.push(listMembress)
    store.state.listMembres = listMembress.value;

    console.log('Noms des aliments par type:', listMembress.value);
    
    // Stockez les données récupérées dans une variable de données du composant si nécessaire
  } catch (error) {
    console.error('Erreur lors de la récupération des aliments par type:', error);
  }
};
    onMounted(async () => {
    
       await getMembre()
               await getPhotos()

        console.log('frfrffeee -> ' + store.state.count);


      });
        watchEffect(() => {
      console.log('Changements détectés dans store.state.listMembres :', store.state.listMembres);
    });
   
      
    
      return {
        openModalUserAddClick,
        openModalConfirmDelete,
        openModalUpdateUser,
        store,
        idMembre,
        listMembress,
        listPhotos,
        
      };
    }

       
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.membrePage{
    color: #fff;
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
