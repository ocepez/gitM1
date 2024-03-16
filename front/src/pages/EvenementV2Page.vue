<template>    
  <div class="event-page">
    <HeaderComponentV2></HeaderComponentV2>
        <h1>gestion evenements </h1>
   <section class="p-3">

        <div class="row">
            <div class="col-12">
                <button class="btn btn-primary newUser" data-bs-toggle="modal" data-bs-target="#userForm" @click="openModalEventAddClick()">Ajouter Envenement <i class="bi bi-people"></i></button>
            </div>
        </div>

        <div class="row">
            <div class="col-12">
                <table class="table table-striped table-hover mt-3 text-center table-bordered">

                    <thead>
                        <tr>
                            <th>Id</th>                           
                            <th>idLieu</th>
                            <th>Image</th>
                            <th>Nom</th>
                            <th>dateDébut</th>
                            <th>Durée</th>
                            <th>nbmaxPersonne</th>
                            <th>Description</th>
                            <th>Action</th>

                        </tr>
                    </thead>

                   <tbody id="data">
    <tr v-for="(event, index) in store.state.listEvents" :key="index ">
        <td>{{ event.id_evenement }}</td>
        <td>{{ event.id_lieu }}</td>
        <td><img :src="store.state.photoEvents[index]" alt="" width="50" height="50"></td>
        <td>{{ event.nom_evenement }}</td>
        <td>{{ event.date_evenement }}</td>
        <td>{{ event.duree_evenement }}</td>  
                <td>{{ event.nb_max_evenement }}</td>                            
        <td>{{ event.desc_evenement }}</td>                            
                          
        <td> 
            <button @click="openModalInscrire(event.id_evenement)" class="btn btn-primary"><i class="bi bi-trash"></i></button>
            <button class="btn btn-primary"><i class="bi bi-pencil-square"></i></button>
            <button @click="openModalConfirmDeleteEvent(event.id_evenement)" class="btn btn-danger"><i class="bi bi-trash"></i></button>
        </td> 
    </tr>

       
</tbody>


                </table>
            </div>
        </div>

    </section>
        <ModalAddEvent v-if="store.getters.getOpenModalUserAdd" :type="2"></ModalAddEvent>
        <ModalSupp v-if="store.getters.getOpenModalConfirmDelete" :idMembre="eventId" :type="2"></ModalSupp>

  </div>
</template>

<script>
import { onMounted, ref, watchEffect } from 'vue';
import HeaderComponentV2 from '@/components/HeaderComponentV2.vue';
import ModalSupp from '@/components/ModalSupp.vue'
import ModalAddEvent from '@/components/ModalAddEvent.vue'

import { useStore } from 'vuex';
import axios from 'axios';
export default {
  components: { HeaderComponentV2, ModalSupp, ModalAddEvent },
    name: 'EvenementV2Page',
    setup() {  

        const store = useStore();
        const listEventss = ref([]);
        const listPhotos = ref([]);

        const eventId = ref();
        
        const getPhotos = async () => {
         try {
            
            const response = await axios.get(`https://api.unsplash.com/search/photos?query=event&client_id=nhr9wJnIHxnWeDBWu7E6HT8qMZhFcGZaGDwi7H6S-YA`);
            const photos = response.data.results; 

            const smallImageUrls = photos.map(item => item.urls.small);

            store.state.photoEvents = smallImageUrls.slice(0, store.state.listEvents.length);
            
     
            } catch (error) {
                console.error('Erreur lors de la récupération des photos via api:', error);
            }
        }
        const openModalEventAddClick =  () => {
            store.commit('toogleOpenModalUserAddTrue');
        };
        const openModalConfirmDeleteEvent =  (evenetId) => {
            store.commit('toggleopenModalConfirmDeleteTrue');
            console.log(eventId)
            eventId.value = evenetId
        };

/*
        const openModalUpdateUser = (membreid) => {
            store.commit('toogleOpenModalUserUpdateTrue');
            idMembre.value = membreid

        }
  */    
        const getEvents = async () => {
  try {
    const response = await axios.get(`http://localhost:8000/evenements`);
    const envenements = response.data;
    
   envenements.forEach(event => {
      listEventss.value.push(event);
   });
    //store.listMembres.push(listMembress)
    store.state.listEvents = listEventss.value;

    
    // Stockez les données récupérées dans une variable de données du composant si nécessaire
  } catch (error) {
    console.error('Erreur lors de la récupération des aliments par type:', error);
  }
};
    onMounted(async () => {
    
       await getEvents()
               await getPhotos()



      });
        watchEffect(() => {
      console.log('Changements détectés dans store.state.listMembres :', store.state.listEvents);
    });
   
      
    
      return {
        //openModalUserAddClick,
        //openModalConfirmDelete,
        //openModalUpdateUser,
        store,
        openModalEventAddClick,
        eventId,
        openModalConfirmDeleteEvent,
        listEventss,
        listPhotos,
        
      };
    }
}

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.event-page{
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
<!-- LoginPage.vue -->


