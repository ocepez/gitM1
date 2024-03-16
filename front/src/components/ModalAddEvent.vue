<template>
  <!-- Modal Overlay -->
  <div class="modal-overlay">
    <!-- Modal Content -->
    <div class="modal-content">
      <!-- Close Button -->
      <span @click="closeModalAddUser()" class="close-btn">&times;</span>
      <!-- Modal Body -->
      <div class="modal-body">
        <!-- Content Here -->
        <h2>Ajouter un Evenement</h2>
        <form @submit.prevent="createEvent">
          <div class="form-group">
            <label for="picture">Photo d'envement:</label>
            <FileUpload mode="basic" name="demo[]" url="/api/upload" accept="image/*" :maxFileSize="1000000" @upload="onUpload" />
          </div>
          <div class="form-group">
            <label for="age">Nom:</label>
            <input type="text" id="nom" required v-model="nom">
          </div>
          <div class="form-group">
            <label for="dateDebut">Date de début:</label>
            <input type="date" id="dateDebut" required v-model="dateDebut">
            </div>
          <div class="form-group">
            <label for="email">Durée:</label>
            <input type="text" id="duree" required v-model="duree">
          </div>
          <div class="form-group">
            <label for="phone">nbmaxPersonne:</label>
            <input type="text" id="nbmaxPersonne" required v-model="nbmaxPersonne">
          </div>
          <div class="form-group">
            <label for="post">Description:</label>
            <input type="text" id="description" required v-model="description">
          </div>
          <button type="submit">Ajouter</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import { onMounted, ref } from 'vue';
import { useStore } from 'vuex';
import FileUpload from 'primevue/fileupload';
import axios from 'axios';
export default {
  name: 'ModalAddEvent', 
  
  components: { FileUpload },
props: {
    
    type:{
      type: Number,
      required: true
    }
  }, 
  setup() {
    const store = useStore();
    const nom = ref('');
    const dateDebut = ref('');
    const duree = ref('');
    const nbmaxPersonne = ref('');
    const description = ref('');
    const listEnventss = ref([]);


    const closeModalAddUser = () => {
      store.commit('toogleOpenModalUserAddFalse');
    }

    const createEvent = async()  => {
      const newEvent = {
        id_evenement: 2,
        nom_evenement: nom.value,
        duree_evenement: duree.value,
        date_evenement: dateDebut.value,
        desc_evenement: description.value,
        image_evenement: "hhfrhfyr",
        id_lieu: 3,
        nb_max_evenement: nbmaxPersonne.value,
};
       
      
      // Ajoutez ici la logique pour envoyer les données de l'utilisateur au backend (par exemple, avec axios)
      console.log('Nouvel utilisateur :', newEvent);
    try {
        // Effectuer la requête POST avec Axios
        const response = await axios.post('http://localhost:8000/evenements', newEvent);
        console.log('Réponse du serveur :', response.data);
        
        // Réinitialiser les champs du formulaire après la soumission
        nom.value = '';
        dateDebut.value = '';
        duree.value = '';
        nbmaxPersonne.value = '';
        description.value = '';
        
       
      } catch (error) {
        console.error('Erreur lors de la création de l\'utilisateur :', error);
      }


      
      // Réinitialiser les champs du formulaire après la soumission
      nom.value = '';
      dateDebut.value = '';
      duree.value = '';
      nbmaxPersonne.value = '';
      description.value = '';
 
      // Fermer la modal
      await getEvents();

      await getPhotos();
      console.log("toototoototototootooto")
      closeModalAddUser();
    }

   const getEvents = async () => {
  try {
    const response = await axios.get(`http://localhost:8000/evenements`);
    const envenements = response.data;
    
   envenements.forEach(event => {
      listEnventss.value.push(event);
   });
    //store.listMembres.push(listMembress)
    store.state.listEvents = listEnventss.value;

    
    // Stockez les données récupérées dans une variable de données du composant si nécessaire
  } catch (error) {
    console.error('Erreur lors de la récupération des aliments par type:', error);
  }
};
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
    

    onMounted(() => {
      console.log('frfr');
    });

    return {
      nom,
      dateDebut,
      duree,
      nbmaxPersonne,
      description,
      listEnventss,
      closeModalAddUser,
      createEvent
    };
  }
}
</script>

<style scoped>
/* Styles pour centrer la modal */
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5); /* Fond sombre semi-transparent */
  display: flex;
  justify-content: center;
  align-items: center;
}

/* Styles pour le contenu de la modal */
.modal-content {
  width: 400px; /* Largeur de la modal */
  background-color: white; /* Couleur de fond blanche */
  padding: 20px;
  color: black;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

/* Styles pour le bouton de fermeture */
.close-btn {
  position: absolute;
  top: 10px;
  right: 10px;
  cursor: pointer;
}

/* Styles pour le corps de la modal */
.modal-body {
  /* Styles pour le contenu du corps de la modal */
}

/* Styles pour les éléments du formulaire */
.form-group {
  margin-bottom: 10px;
}

label {
  font-weight: bold;
  display: block;
}

input {
  padding: 8px;
  border-radius: 4px;
  border: 1px solid #ccc;
}

button {
  padding: 8px 16px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}
</style>
