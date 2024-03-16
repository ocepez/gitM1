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
        <h2>Ajouter un Lieux</h2>
        <form @submit.prevent="createLieux">
          
          <div class="form-group">
            <label for="nom">Nom:</label>
            <input type="text" id="nom" required v-model="nom">
          </div>
          <div class="form-group">
            <label for="adresse">Adresse:</label>
            <input type="text" id="adresse" required v-model="adresse">
            </div>
          <div class="form-group">
            <label for="email">Capacité:</label>
            <input type="text" id="capacite" required v-model="capacite">
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
import axios from 'axios';
export default {
  name: 'ModalAddEvent', 
  
props: {
    
    type:{
      type: Number,
      required: true
    }
  }, 
  setup() {
    const store = useStore();
    const nom = ref('');
    const adresse = ref('');
    const capacite = ref('');
    const listlieuxx = ref([]);


    const closeModalAddUser = () => {
      store.commit('toogleOpenModalUserAddFalse');
    }

    const createLieux = async()  => {
      const newLieux = {
        nom: nom.value,
        adresse:  adresse.value,
        capacite: capacite.value,
        
};
       
      
      // Ajoutez ici la logique pour envoyer les données de l'utilisateur au backend (par exemple, avec axios)
      console.log('Nouvel lieux :', newLieux);
    try {
        // Effectuer la requête POST avec Axios
        const response = await axios.post('http://localhost:8002/lieux', newLieux);
        console.log('Réponse du serveur :', response.data);
        
        // Réinitialiser les champs du formulaire après la soumission
        nom.value = '';
        adresse.value = '';
        capacite.value = '';
        
       
      } catch (error) {
        console.error('Erreur lors de la création de l\'utilisateur :', error);
      }


      
      // Réinitialiser les champs du formulaire après la soumission
        nom.value = '';
        adresse.value = '';
        capacite.value = '';
      // Fermer la modal
      await getLieux();

      closeModalAddUser();
    }

   const getLieux = async () => {
  try {
    console.log("kkfrf")
    const response = await axios.get(`http://localhost:8002/lieux`);
    const lieux = response.data;
    
   lieux.forEach(lieu => {
      listlieuxx.value.push(lieu);
   });
    //store.listMembres.push(listMembress)
    store.state.listLieux = listlieuxx.value;

    console.log('Noms des aliments par type:', listlieuxx.value);
    
    // Stockez les données récupérées dans une variable de données du composant si nécessaire
  } catch (error) {
    console.error('Erreur lors de la récupération des aliments par type:', error);
  }
};
    
    

    onMounted(() => {
      console.log('frfr');
    });

    return {
      nom,
      adresse,
      capacite,
      listlieuxx,
      closeModalAddUser,
      createLieux,
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
